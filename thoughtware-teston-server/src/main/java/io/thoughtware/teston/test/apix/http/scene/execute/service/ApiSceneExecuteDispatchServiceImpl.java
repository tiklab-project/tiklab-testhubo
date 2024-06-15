package io.thoughtware.teston.test.apix.http.scene.execute.service;

import com.alibaba.fastjson.JSONObject;
import io.thoughtware.core.exception.ApplicationException;
import io.thoughtware.teston.common.MagicValue;
import io.thoughtware.teston.support.agentconfig.model.AgentConfig;
import io.thoughtware.teston.support.agentconfig.model.AgentConfigQuery;
import io.thoughtware.teston.support.agentconfig.service.AgentConfigService;
import io.thoughtware.teston.support.variable.service.VariableService;
import io.thoughtware.teston.test.apix.http.scene.execute.model.ApiSceneTestRequest;
import io.thoughtware.teston.test.apix.http.scene.execute.model.ApiSceneTestResponse;
import io.thoughtware.teston.test.apix.http.scene.instance.model.ApiSceneInstance;
import io.thoughtware.teston.test.apix.http.scene.instance.service.ApiSceneInstanceService;
import io.thoughtware.teston.test.common.stepcommon.model.StepCommon;
import io.thoughtware.teston.test.common.stepcommon.model.StepCommonQuery;
import io.thoughtware.teston.test.common.stepcommon.service.StepCommonService;

import io.thoughtware.teston.test.common.wsTest.service.WebSocketServiceImpl;
import io.thoughtware.teston.test.common.wstest.WsTestService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 接口场景测试调度 服务
 */
@Service
public class ApiSceneExecuteDispatchServiceImpl implements ApiSceneExecuteDispatchService {

    static Logger logger = LoggerFactory.getLogger(ApiSceneExecuteDispatchServiceImpl.class);


    @Autowired
    ApiSceneInstanceService apiSceneInstanceService;

    @Autowired
    AgentConfigService agentConfigService;

    @Autowired
    VariableService variableService;

    @Autowired
    StepCommonService stepCommonService;

    @Autowired
    WsTestService wsTestService;


    /**
     * 测试用例执行
     * @param apiSceneTestRequest
     * @return
     */
    @Override
    public ApiSceneTestResponse execute(ApiSceneTestRequest apiSceneTestRequest) {
        //执行
        ApiSceneTestResponse apiSceneTestResponse = executeStart(apiSceneTestRequest);

        String apiSceneId = apiSceneTestRequest.getApiSceneCase().getId();
        //保存实例，存入数据库
        saveInstance( apiSceneTestResponse,apiSceneId);

        return apiSceneTestResponse;
    }

    @Override
    public ApiSceneTestResponse executeStart(ApiSceneTestRequest apiSceneTestRequest){
        String apiSceneId = apiSceneTestRequest.getApiSceneCase().getId();
        StepCommonQuery stepCommonQuery = new StepCommonQuery();
        stepCommonQuery.setCaseId(apiSceneId);
        stepCommonQuery.setCaseType(MagicValue.CASE_TYPE_API_SCENE);
        List<StepCommon> stepCommonList = stepCommonService.findStepCommonList(stepCommonQuery);
        apiSceneTestRequest.setStepCommonList(stepCommonList);

        JSONObject variable = variableService.getVariable(apiSceneTestRequest.getRepositoryId());
        apiSceneTestRequest.setVariableJson(variable);

        List<AgentConfig> agentConfigList = agentConfigService.findAgentConfigList(new AgentConfigQuery());
        if( CollectionUtils.isNotEmpty(agentConfigList)){
            AgentConfig agentConfig = agentConfigList.get(0);
            String agentId = agentConfig.getId();

            JSONObject apiUnitObject = new JSONObject();
            apiUnitObject.put("apiSceneTestRequest",apiSceneTestRequest);
            apiUnitObject.put("type",MagicValue.CASE_TYPE_API_SCENE);

            String futureId = agentId + "_" + MagicValue.CASE_TYPE_API_SCENE;
            wsTestService.sendMessageExe(agentId,apiUnitObject,futureId);

            try {
                // 从futureMap中获取CompletableFuture实例并获取结果
                CompletableFuture<JSONObject> future =  WebSocketServiceImpl.futureMap.get(futureId);
                JSONObject jsonObject = future.get();
                JSONObject apiSceneTestResponseObj = jsonObject.getJSONObject("apiSceneTestResponse");
                ApiSceneTestResponse apiSceneTestResponse = apiSceneTestResponseObj.toJavaObject(ApiSceneTestResponse.class);
                return apiSceneTestResponse;
            } catch (InterruptedException | ExecutionException e) {
                throw new ApplicationException(("执行出错"));
            }
        }else {
            throw new ApplicationException("不是内嵌agent，请到设置中配置agent");
        }
    }

    /**
     * 保存历史
     * @return
     * @param apiSceneTestResponse
     * @param apiSceneId
     */
    private void saveInstance(ApiSceneTestResponse apiSceneTestResponse, String apiSceneId){
        ApiSceneInstance apiSceneInstance = apiSceneTestResponse.getApiSceneInstance();
        apiSceneInstance.setApiSceneId(apiSceneId);

        apiSceneInstanceService.saveApiSceneInstanceToSql(apiSceneInstance,apiSceneTestResponse);
    }




}



