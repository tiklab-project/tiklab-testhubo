package io.thoughtware.teston.test.app.scene.execute.service;

import com.alibaba.fastjson.JSONObject;
import io.thoughtware.teston.instance.service.InstanceService;
import io.thoughtware.teston.support.agentconfig.model.AgentConfig;
import io.thoughtware.teston.support.agentconfig.model.AgentConfigQuery;
import io.thoughtware.teston.support.agentconfig.service.AgentConfigService;
import io.thoughtware.teston.support.variable.service.VariableService;
import io.thoughtware.teston.test.app.scene.cases.service.AppSceneCaseService;
import io.thoughtware.teston.test.app.scene.cases.service.AppSceneStepService;
import io.thoughtware.teston.test.app.scene.execute.model.AppSceneTestRequest;
import io.thoughtware.teston.test.app.scene.execute.model.AppSceneTestResponse;
import io.thoughtware.teston.test.app.scene.instance.model.AppSceneInstance;
import io.thoughtware.teston.test.app.scene.instance.model.AppSceneInstanceQuery;
import io.thoughtware.teston.test.app.scene.instance.service.AppSceneInstanceService;
import io.thoughtware.teston.test.app.utils.RpcClientAppUtil;
import io.thoughtware.teston.test.common.stepcommon.model.StepCommon;
import io.thoughtware.teston.test.common.stepcommon.model.StepCommonQuery;
import io.thoughtware.teston.test.common.stepcommon.service.StepCommonService;
import io.thoughtware.core.exception.ApplicationException;
import io.thoughtware.rpc.client.router.lookup.FixedLookup;
import io.thoughtware.teston.agent.app.scene.AppSceneTestService;
import io.thoughtware.teston.common.MagicValue;
import io.thoughtware.teston.support.environment.service.AppEnvService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * app场景测试调度 服务
 */
@Service
public class AppSceneTestDispatchServiceImpl implements AppSceneTestDispatchService {

    private static Logger logger = LoggerFactory.getLogger(AppSceneTestDispatchServiceImpl.class);

    @Autowired
    AppSceneStepService appSceneStepService;

    @Autowired
    AppSceneInstanceService appSceneInstanceService;

    @Autowired
    AgentConfigService agentConfigService;

    @Autowired
    AppEnvService appEnvService;

    @Autowired
    RpcClientAppUtil rpcClientAppUtil;


    @Autowired
    AppSceneTestService appSceneTestService;

    @Autowired
    VariableService variableService;


    @Autowired
    StepCommonService stepCommonService;



    /**
     *  环境中获取是否是内嵌agent
     */
    @Value("${teston-agent.embbed.enable:false}")
    Boolean enable;

    /**
     * rpc 调用
     */
    AppSceneTestService appSceneTestServiceRPC(String agentUrl){
        return rpcClientAppUtil.rpcClient().getBean(AppSceneTestService.class, new FixedLookup(agentUrl));
    }

    private AgentConfig agentConfig = null;



    @Override
    public void execute(AppSceneTestRequest appSceneTestRequest) {
        try {
            executeStart(appSceneTestRequest);
        }catch (Exception e){
            throw new ApplicationException(e);
        }
    }

    private void executeStart(AppSceneTestRequest appSceneTestRequest){
        String appSceneId = appSceneTestRequest.getAppSceneId();

        //先查询所有场景步骤
        StepCommonQuery stepCommonQuery = new StepCommonQuery();
        stepCommonQuery.setCaseId(appSceneId);
        stepCommonQuery.setCaseType(MagicValue.CASE_TYPE_APP);
        List<StepCommon> stepCommonList = stepCommonService.findStepCommonList(stepCommonQuery);
        //设置步骤数据
        appSceneTestRequest.setStepCommonList(stepCommonList);
        //设置变量数据
        JSONObject variable = variableService.getVariable(appSceneId);
        appSceneTestRequest.setVariableJson(variable);

        //根据环境配置是否为内嵌
        //如果不是内嵌走rpc
        if(enable) {
            appSceneTestService.execute(appSceneTestRequest);
        }else {
            List<AgentConfig> agentConfigList = agentConfigService.findAgentConfigList(new AgentConfigQuery());
            if(CollectionUtils.isNotEmpty(agentConfigList)){
                AgentConfig agentConfig = agentConfigList.get(0);

                appSceneTestServiceRPC(agentConfig.getUrl()).execute(appSceneTestRequest);
            }
        }
    }



    @Override
    public Integer status(String appSceneId) {

        int status;
        //根据环境配置是否为内嵌
        //如果不是内嵌走rpc
        try {
            if(enable) {
                //调用执行方法返回结果数据
                status = appSceneTestService.status(appSceneId);
            }else {
                List<AgentConfig> agentConfigList = agentConfigService.findAgentConfigList(new AgentConfigQuery());
                if(CollectionUtils.isNotEmpty(agentConfigList)) {
                    agentConfig = agentConfigList.get(0);
                    status = appSceneTestServiceRPC(agentConfig.getUrl()).status(appSceneId);
                }else {
                    throw new ApplicationException("未设置agent，请到设置中配置agent");
                }
            }
        }catch (Exception e){
            throw new ApplicationException(e);
        }

        return status;
    }

    @Override
    public AppSceneTestResponse result(AppSceneTestRequest appSceneTestRequest) {
        AppSceneTestResponse appSceneTestResponse = new AppSceneTestResponse();
        //根据环境配置是否为内嵌
        //如果不是内嵌走rpc

        try{
            if(enable) {
                //调用执行方法返回结果数据
                appSceneTestResponse = appSceneTestService.result(appSceneTestRequest);
            }else {
                appSceneTestResponse = appSceneTestServiceRPC(agentConfig.getUrl()).result(appSceneTestRequest);
            }
        }catch (Exception e){
            appSceneTestResponse.setStatus(0);
            throw new ApplicationException(e);
        }


        //测试计划中设置了值
        if(appSceneTestRequest.getExeType()==null){

            if(appSceneTestResponse!=null&&appSceneTestResponse.getStatus()==0&&appSceneTestResponse.getAppSceneInstance()!=null) {
                String appSceneId = appSceneTestRequest.getAppSceneId();
                saveToSQL(appSceneTestResponse, appSceneId);
            }
        }

        return appSceneTestResponse;
    }

    /**
     * 历史记录 存入数据库
     * @param appSceneTestResponse
     * @param appSceneId
     */
    private void saveToSQL(AppSceneTestResponse appSceneTestResponse, String appSceneId) {
        //保存历史总详情
        AppSceneInstance appSceneInstance = appSceneTestResponse.getAppSceneInstance();
        appSceneInstance.setAppSceneId(appSceneId);

        appSceneInstanceService.saveAppSceneInstanceToSql(appSceneInstance,appSceneTestResponse);
    }
}