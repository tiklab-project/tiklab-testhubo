package io.thoughtware.teston.test.app.scene.execute.service;

import com.alibaba.fastjson.JSONObject;
import io.thoughtware.teston.instance.model.Instance;
import io.thoughtware.teston.instance.service.InstanceService;
import io.thoughtware.teston.support.agentconfig.service.AgentConfigService;
import io.thoughtware.teston.support.environment.model.AppEnv;
import io.thoughtware.teston.support.variable.service.VariableService;
import io.thoughtware.teston.test.app.scene.cases.model.AppSceneCase;
import io.thoughtware.teston.test.app.scene.cases.service.AppSceneCaseService;
import io.thoughtware.teston.test.app.scene.execute.model.AppSceneTestRequest;
import io.thoughtware.teston.test.app.scene.execute.model.AppSceneTestResponse;
import io.thoughtware.teston.test.app.scene.instance.model.AppSceneInstance;
import io.thoughtware.teston.test.app.scene.instance.service.AppSceneInstanceService;
import io.thoughtware.teston.test.app.utils.RpcClientAppUtil;
import io.thoughtware.teston.test.common.stepcommon.model.StepCommon;
import io.thoughtware.teston.test.common.stepcommon.model.StepCommonInstance;
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

    @Autowired
    AppSceneCaseService appSceneCaseService;

    @Autowired
    InstanceService instanceService;


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


    @Override
    public void execute(AppSceneTestRequest appSceneTestRequest) {
        String appSceneId = appSceneTestRequest.getAppSceneId();
        String appSceneInstanceId = createInitInstance(appSceneId);

        // 执行
        try {
            executeStart(appSceneTestRequest);
        }catch (Exception e){
            updateStatus(appSceneInstanceId,MagicValue.TEST_STATUS_FAIL);
            throw new ApplicationException(e);
        }finally {
            try {
                //执行完，保存最总结果
                AppSceneTestResponse appSceneTestResponse = result(appSceneTestRequest);
                createStepInstanceList(appSceneTestResponse,appSceneInstanceId);
                Thread.sleep(3000);

                logger.info("------------------------------------------数据清理------------------------------------------");

                //清理数据
                cleanUpData(appSceneId);
            }catch (Exception e){
                throw new ApplicationException(e);
            }
        }
    }

    @Override
    public void executeStart(AppSceneTestRequest appSceneTestRequest){
        String appSceneId = appSceneTestRequest.getAppSceneId();

        //环境
        String appEnvId = appSceneTestRequest.getAppEnvId();
        AppEnv appEnv = appEnvService.findAppEnv(appEnvId);
        appSceneTestRequest.setAppEnv(appEnv);

        //设置变量数据
        JSONObject variable = variableService.getVariable(appSceneId);
        appSceneTestRequest.setVariableJson(variable);

        //先查询所有场景步骤
        StepCommonQuery stepCommonQuery = new StepCommonQuery();
        stepCommonQuery.setCaseId(appSceneId);
        stepCommonQuery.setCaseType(MagicValue.CASE_TYPE_APP);
        List<StepCommon> stepCommonList = stepCommonService.findStepCommonList(stepCommonQuery);
        //设置步骤数据
        appSceneTestRequest.setStepCommonList(stepCommonList);

        //根据环境配置是否为内嵌
        //如果不是内嵌走rpc
        if(enable) {
            appSceneTestService.execute(appSceneTestRequest);
        }else {
            String agentUrl = agentConfigService.getAgent();
            appSceneTestServiceRPC(agentUrl).execute(appSceneTestRequest);

        }
    }

    @Override
    public AppSceneTestResponse result(AppSceneTestRequest appSceneTestRequest) {
        AppSceneTestResponse appSceneTestResponse = getResult(appSceneTestRequest);

        //测试计划中设置了值
        updateInstance(appSceneTestResponse,appSceneTestRequest.getAppSceneId());

        return appSceneTestResponse;
    }

    @Override
    public AppSceneTestResponse getResult(AppSceneTestRequest appSceneTestRequest) {
        AppSceneTestResponse appSceneTestResponse = new AppSceneTestResponse();

        //根据环境配置是否为内嵌
        //如果不是内嵌走rpc
        try{
            if(enable) {
                //调用执行方法返回结果数据
                appSceneTestResponse = appSceneTestService.result(appSceneTestRequest);
            }else {
                String agentUrl = agentConfigService.getAgent();
                appSceneTestResponse = appSceneTestServiceRPC(agentUrl).result(appSceneTestRequest);
            }
        }catch (Exception e){
            AppSceneInstance appSceneInstance = getAppSceneInstance(appSceneTestRequest.getAppSceneId());
            appSceneInstance.setStatus(MagicValue.TEST_STATUS_FAIL);
            appSceneTestResponse.setAppSceneInstance(appSceneInstance);
            throw new ApplicationException(e);
        }

        return appSceneTestResponse;
    }



    /**
     * 开始执行创建初始历史
     * @param appSceneId
     * @return
     */
    private String createInitInstance(String appSceneId){
        AppSceneInstance appSceneInstance = getAppSceneInstance(appSceneId);
        String appSceneInstanceId = appSceneInstanceService.createAppSceneInstance(appSceneInstance);

        // 创建公共实例
        Instance instance = new Instance();
        instance.setId(appSceneInstanceId);

        instance.setBelongId(appSceneId);
        instance.setType(MagicValue.CASE_TYPE_APP);

        AppSceneCase appSceneCase = appSceneCaseService.findAppSceneCase(appSceneId);
        instance.setName(appSceneCase.getTestCase().getName());
        instance.setRepositoryId(appSceneCase.getTestCase().getRepositoryId());

        //获取当前执行次数
        int executeNum = instanceService.getRecentExecuteNum(appSceneId);
        instance.setExecuteNumber(executeNum);

        //设置状态,开始执行
        instance.setStatus(MagicValue.TEST_STATUS_START);

        JSONObject instanceMap = new JSONObject();
        instanceMap.put("stepNum",appSceneInstance.getStepNum().toString());
        instanceMap.put("passNum",appSceneInstance.getPassNum().toString());
        instanceMap.put("passRate",appSceneInstance.getPassRate());
        instanceMap.put("failNum",appSceneInstance.getFailNum().toString());
        instance.setContent(instanceMap.toString());

        instanceService.createInstance(instance);

        return appSceneInstanceId;
    }

    private AppSceneInstance getAppSceneInstance(String caseId){
        int stepNum = stepCommonService.findStepNum(caseId);
        AppSceneInstance appSceneInstance = new AppSceneInstance();
        appSceneInstance.setAppSceneId(caseId);
        appSceneInstance.setStepNum(stepNum);
        appSceneInstance.setFailNum(0);
        appSceneInstance.setPassNum(0);
        appSceneInstance.setPassRate("0.00%");

        return appSceneInstance;
    }




    private void updateInstance(AppSceneTestResponse appSceneTestResponse,String appSceneId){
        String instanceId = instanceService.getRecentExecuteInstanceId(appSceneId);
        if(appSceneTestResponse.getAppSceneInstance()!=null&&instanceId!=null){
            AppSceneInstance appSceneInstance = appSceneTestResponse.getAppSceneInstance();
            appSceneInstance.setAppSceneId(appSceneId);
            appSceneInstance.setId(instanceId);
            appSceneInstanceService.updateAppSceneInstance(appSceneInstance);

            Instance instance = instanceService.findInstance(instanceId);
            JSONObject instanceMap = new JSONObject();
            instanceMap.put("stepNum",appSceneInstance.getStepNum().toString());
            instanceMap.put("passNum",appSceneInstance.getPassNum().toString());
            instanceMap.put("passRate",appSceneInstance.getPassRate());
            instanceMap.put("failNum",appSceneInstance.getFailNum().toString());
            instance.setContent(instanceMap.toString());

            instance.setStatus(appSceneInstance.getStatus());
            instanceService.updateInstance(instance);

            appSceneInstance.setInstance(instance);
        }else {
            updateStatus(instanceId,MagicValue.TEST_STATUS_FAIL);
        }
    }

    private void createStepInstanceList(AppSceneTestResponse appSceneTestResponse,String instanceId){
        if(CollectionUtils.isNotEmpty(appSceneTestResponse.getStepCommonInstanceList())){
            List<StepCommonInstance> stepCommonInstanceList = appSceneTestResponse.getStepCommonInstanceList();

            appSceneInstanceService.createAppSceneStepInstance(stepCommonInstanceList,instanceId);
        }
    }

    /**
     * 状态更改
     * @param instanceId
     */
    private void updateStatus(String instanceId,String status){
        AppSceneInstance appSceneInstance = appSceneInstanceService.findAppSceneInstance(instanceId);
        appSceneInstance.setStatus(status);
        appSceneInstanceService.updateAppSceneInstance(appSceneInstance);
    }

    @Override
    public void cleanUpData(String appSceneId) {
        if(enable){
            appSceneTestService.cleanUpData(appSceneId);
        }else {
            String agentUrl = agentConfigService.getAgent();
            appSceneTestServiceRPC(agentUrl).cleanUpData(appSceneId);
        }
    }



}