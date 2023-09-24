package io.tiklab.teston.test.app.scene.execute.service;

import com.alibaba.fastjson.JSONObject;
import io.tiklab.rpc.client.router.lookup.FixedLookup;
import io.tiklab.teston.agent.app.scene.AppSceneTestService;
import io.tiklab.teston.support.agentconfig.model.AgentConfig;
import io.tiklab.teston.support.agentconfig.model.AgentConfigQuery;
import io.tiklab.teston.support.agentconfig.service.AgentConfigService;
import io.tiklab.teston.support.environment.service.AppEnvService;
import io.tiklab.teston.support.variable.service.VariableService;
import io.tiklab.teston.test.app.scene.cases.model.AppSceneStep;
import io.tiklab.teston.test.app.scene.cases.model.AppSceneStepQuery;
import io.tiklab.teston.test.app.scene.cases.service.AppSceneStepService;
import io.tiklab.teston.test.app.scene.execute.model.AppSceneTestRequest;
import io.tiklab.teston.test.app.scene.execute.model.AppSceneTestResponse;
import io.tiklab.teston.test.app.scene.instance.model.AppSceneInstance;
import io.tiklab.teston.test.app.scene.instance.model.AppSceneInstanceQuery;
import io.tiklab.teston.test.app.scene.instance.service.AppSceneInstanceService;
import io.tiklab.teston.test.app.utils.RpcClientAppUtil;
import io.tiklab.teston.test.web.scene.execute.model.WebSceneTestRequest;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

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

    private Integer status;

    @Override
    public Integer execute(AppSceneTestRequest appSceneTestRequest) {
        status=1;

        executeStart(appSceneTestRequest);

        return 1;
    }

    private void executeStart(AppSceneTestRequest appSceneTestRequest){
        String appSceneId = appSceneTestRequest.getAppSceneId();

        //先查询所有场景步骤
        AppSceneStepQuery appSceneStepQuery = new AppSceneStepQuery();
        appSceneStepQuery.setAppSceneId(appSceneId);
        List<AppSceneStep> appSceneStepList = appSceneStepService.findAppSceneStepList(appSceneStepQuery);

        appSceneTestRequest.setAppSceneStepList(appSceneStepList);
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
    public Integer status() {
        //根据环境配置是否为内嵌
        //如果不是内嵌走rpc
        if(enable) {
            //调用执行方法返回结果数据
            status = appSceneTestService.status();
        }else {
            List<AgentConfig> agentConfigList = agentConfigService.findAgentConfigList(new AgentConfigQuery());
            if(CollectionUtils.isNotEmpty(agentConfigList)) {
                agentConfig = agentConfigList.get(0);
                status = appSceneTestServiceRPC(agentConfig.getUrl()).status();
            }
        }


        return status;
    }

    @Override
    public AppSceneTestResponse result(AppSceneTestRequest appSceneTestRequest) {
        AppSceneTestResponse appSceneTestResponse;
        //根据环境配置是否为内嵌
        //如果不是内嵌走rpc
        if(enable) {
            //调用执行方法返回结果数据
            appSceneTestResponse = appSceneTestService.result();
        }else {
            appSceneTestResponse = appSceneTestServiceRPC(agentConfig.getUrl()).result();
        }

        String appSceneId = appSceneTestRequest.getAppSceneId();
        //测试计划中设置了值
//        if(appSceneTestRequest.getExeType()==null){
//            //errorMessage 是启动系统失败
//            if(appSceneTestResponse!=null&&ObjectUtils.isEmpty(appSceneTestResponse.getErrMsg())){
        if(status==0) {
            saveToSQL(appSceneTestResponse, appSceneId);
        }
//            }
//        }


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

        //设置次数
        AppSceneInstanceQuery appSceneInstanceQuery = new AppSceneInstanceQuery();
        appSceneInstanceQuery.setAppSceneId(appSceneId);
        List<AppSceneInstance> appSceneInstanceList = appSceneInstanceService.findAppSceneInstanceList(appSceneInstanceQuery);
        if(appSceneInstanceList!=null&&appSceneInstanceList.size()>0){
            Integer executeNumber = appSceneInstanceList.get(0).getExecuteNumber();

            appSceneInstance.setExecuteNumber(++executeNumber);
        }else {
            appSceneInstance.setExecuteNumber(1);
        }

        appSceneInstanceService.saveAppSceneInstanceToSql(appSceneInstance,appSceneTestResponse);
    }
}