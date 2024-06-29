package io.thoughtware.teston.test.apix.http.unit.execute.service;

import com.alibaba.fastjson.JSONObject;
import io.thoughtware.core.exception.ApplicationException;
import io.thoughtware.teston.common.MagicValue;
import io.thoughtware.teston.instance.model.Instance;
import io.thoughtware.teston.instance.service.InstanceService;
import io.thoughtware.teston.support.agentconfig.model.AgentConfigQuery;
import io.thoughtware.teston.support.variable.service.VariableService;
import io.thoughtware.teston.test.apix.http.unit.cases.model.ApiUnitCase;
import io.thoughtware.teston.test.apix.http.unit.cases.model.ApiUnitCaseDataConstruction;
import io.thoughtware.teston.test.apix.http.unit.cases.service.ApiUnitCaseService;
import io.thoughtware.teston.test.apix.http.unit.cases.service.AssertService;
import io.thoughtware.teston.test.apix.http.unit.execute.model.ApiUnitTestRequest;
import io.thoughtware.teston.test.apix.http.unit.instance.model.ApiUnitInstance;
import io.thoughtware.teston.test.apix.http.unit.instance.service.ApiUnitInstanceService;
import io.thoughtware.teston.test.apix.http.unit.instance.service.AssertInstanceService;
import io.thoughtware.teston.support.utils.RpcClientApixUtil;
import io.thoughtware.teston.support.agentconfig.model.AgentConfig;
import io.thoughtware.teston.support.agentconfig.service.AgentConfigService;
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
 * 接口单元测试调度 服务
 */
@Service
public class ApiUnitExecuteDispatchServiceImpl implements ApiUnitExecuteDispatchService {

    private static Logger logger = LoggerFactory.getLogger(ApiUnitExecuteDispatchServiceImpl.class);


    @Autowired
    ApiUnitCaseService apiUnitCaseService;

    @Autowired
    ApiUnitInstanceService apiUnitInstanceService;

    @Autowired
    VariableService variableService;

    @Autowired
    AgentConfigService agentConfigService;

    @Autowired
    InstanceService instanceService;

    @Autowired
    WsTestService wsTestService;

    @Override
    public ApiUnitInstance execute(ApiUnitTestRequest apiUnitTestRequest) {
        String apiUnitId = apiUnitTestRequest.getApiUnitCase().getId();

        ApiUnitInstance apiUnitInstance;

        try {
             apiUnitInstance = executeStart(apiUnitTestRequest);
        }catch (Exception e){
            throw new ApplicationException(e);
        }

        if(apiUnitInstance==null){
            return null;
        }

        saveInstance(apiUnitInstance,apiUnitId);

        return apiUnitInstance;
    }


    @Override
    public ApiUnitInstance executeStart(ApiUnitTestRequest apiUnitTestRequest) {
        String apiUnitId = apiUnitTestRequest.getApiUnitCase().getId();
        //准备测试的数据
        ApiUnitTestRequest processData = setApiUnitTestRequestData(apiUnitId, apiUnitTestRequest.getApiEnv());

        ApiUnitInstance apiUnitInstance;

        List<AgentConfig> agentConfigList = agentConfigService.getAgentList();
        AgentConfig agentConfig = agentConfigList.get(0);
        String agentId = agentConfig.getId();

        JSONObject apiUnitObject = new JSONObject();
        apiUnitObject.put("apiUnitTestRequest",processData);
        apiUnitObject.put("type",MagicValue.CASE_TYPE_API_UNIT);
        apiUnitObject.put("caseId",apiUnitId);

        String futureId = agentId + "_" + MagicValue.CASE_TYPE_API_UNIT + "_" + apiUnitId;
        wsTestService.sendMessageExe(agentId,apiUnitObject,futureId);

        try {
             // 从futureMap中获取CompletableFuture实例并获取结果
             CompletableFuture<JSONObject> future =  WebSocketServiceImpl.futureMap.get(futureId);
             JSONObject jsonObject = future.get();
             JSONObject apiUnitInstanceObj = jsonObject.getJSONObject("apiUnitInstance");
             apiUnitInstance = apiUnitInstanceObj.toJavaObject(ApiUnitInstance.class);
            return apiUnitInstance;
        } catch (InterruptedException | ExecutionException e) {
            logger.info("执行出错");
        }

        return null;
    }



    private void saveInstance(ApiUnitInstance apiUnitInstance, String apiUnitId){
        //执行后数据保存
        String apiUnitInstanceId = apiUnitInstanceService.saveApiUnitInstanceToSql(apiUnitInstance);

        Instance instance = new Instance();
        instance.setId(apiUnitInstanceId);
        instance.setBelongId(apiUnitId);
        instance.setType(MagicValue.CASE_TYPE_API_UNIT);

        ApiUnitCase apiUnitCase = apiUnitCaseService.findApiUnitCase(apiUnitId);
        instance.setName(apiUnitCase.getTestCase().getName());
        instance.setRepositoryId(apiUnitCase.getTestCase().getRepositoryId());

        //获取当前执行次数
        int executeNum = instanceService.getRecentExecuteNum(apiUnitId);
        instance.setExecuteNumber(executeNum);

        //根据result设置成功还是失败
        if(apiUnitInstance.getResult()==1){
            instance.setStatus(MagicValue.TEST_STATUS_SUCCESS);
        }else {
            instance.setStatus(MagicValue.TEST_STATUS_FAIL);
        }

        JSONObject instanceMap = new JSONObject();
        instanceMap.put("url",apiUnitInstance.getRequestInstance().getRequestUrl());
        instanceMap.put("requestType",apiUnitInstance.getRequestInstance().getRequestType());
        instanceMap.put("result",apiUnitInstance.getResult().toString());

        if(apiUnitInstance.getErrMessage()==null){
            if(apiUnitInstance.getStatusCode()!=null){
                instanceMap.put("statusCode",apiUnitInstance.getStatusCode().toString());
            }

            if(apiUnitInstance.getElapsedTime()!=null){
                instanceMap.put("elapsedTime",apiUnitInstance.getElapsedTime().toString());
            }

        }else {
            instanceMap.put("errMessage",apiUnitInstance.getErrMessage());
        }

        instance.setContent(instanceMap.toString());

        instanceService.createInstance(instance);

    }


    /**
     * 设置测试数据
     * @param apiUnitId
     * @param apiEnv
     * @return
     */
    public ApiUnitTestRequest setApiUnitTestRequestData(String apiUnitId,String apiEnv){
        ApiUnitTestRequest apiUnitTestRequest = new ApiUnitTestRequest();

        ApiUnitCase apiUnitCase = apiUnitCaseService.findApiUnitCase(apiUnitId);
        ApiUnitCaseDataConstruction apiUnitCaseDataConstruction = apiUnitCaseService.findApiUnitCaseExt(apiUnitCase);
        JSONObject variable = variableService.getVariable(apiUnitCase.getTestCase().getRepositoryId());

        apiUnitTestRequest.setVariableJson(variable);
        apiUnitTestRequest.setApiUnitCase(apiUnitCase);
        apiUnitTestRequest.setApiUnitCaseDataConstruction(apiUnitCaseDataConstruction);
        apiUnitTestRequest.setApiEnv(apiEnv);

        return apiUnitTestRequest;
    }

}
