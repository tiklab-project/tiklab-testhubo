package io.thoughtware.teston.test.apix.http.perf.execute.service;

import com.alibaba.fastjson.JSONObject;
import io.thoughtware.core.exception.ApplicationException;
import io.thoughtware.teston.instance.model.Instance;
import io.thoughtware.teston.instance.model.InstanceQuery;
import io.thoughtware.teston.instance.service.InstanceService;
import io.thoughtware.teston.support.agentconfig.model.AgentConfig;
import io.thoughtware.teston.support.agentconfig.model.AgentConfigQuery;
import io.thoughtware.teston.support.agentconfig.service.AgentConfigService;
import io.thoughtware.teston.test.common.stepcommon.model.StepCommon;
import io.thoughtware.teston.test.common.stepcommon.model.StepCommonQuery;
import io.thoughtware.teston.test.common.stepcommon.service.StepCommonService;
import io.thoughtware.rpc.client.router.lookup.FixedLookup;
import io.thoughtware.teston.agent.api.http.perf.ApiPerfTestService;
import io.thoughtware.teston.common.MagicValue;
import io.thoughtware.teston.support.variable.service.VariableService;
import io.thoughtware.teston.test.apix.http.perf.cases.model.ApiPerfCase;
import io.thoughtware.teston.test.apix.http.perf.cases.model.ApiPerfStep;
import io.thoughtware.teston.test.apix.http.perf.cases.model.ApiPerfStepQuery;
import io.thoughtware.teston.test.apix.http.perf.cases.service.ApiPerfCaseService;
import io.thoughtware.teston.test.apix.http.perf.cases.service.ApiPerfStepService;
import io.thoughtware.teston.test.apix.http.perf.cases.service.ApiPerfTestDataService;
import io.thoughtware.teston.test.apix.http.perf.execute.model.ApiPerfTestRequest;
import io.thoughtware.teston.test.apix.http.perf.execute.model.ApiPerfTestResponse;
import io.thoughtware.teston.test.apix.http.perf.instance.model.ApiPerfInstance;
import io.thoughtware.teston.test.apix.http.perf.instance.service.ApiPerfInstanceService;
import io.thoughtware.teston.test.apix.http.scene.execute.model.ApiSceneTestRequest;
import io.thoughtware.teston.test.apix.http.scene.instance.model.ApiSceneInstance;
import io.thoughtware.teston.support.utils.RpcClientApixUtil;
import io.thoughtware.teston.support.utils.TestApixUtil;


import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * api性能测试执行调度 服务
 */
@Service
public class ApiPerfExecuteDispatchServiceImpl implements ApiPerfExecuteDispatchService {

    public static final Logger logger = LoggerFactory.getLogger(ApiPerfExecuteDispatchServiceImpl.class);

    @Autowired
    ApiPerfCaseService apiPerfCaseService;

    @Autowired
    ApiPerfTestDataService apiPerfTestDataService;

    @Autowired
    ApiPerfStepService apiPerfStepService;

    @Autowired
    ApiPerfInstanceService apiPerfInstanceService;

    @Autowired
    AgentConfigService agentConfigService;

    @Autowired
    RpcClientApixUtil rpcClientApixUtil;

    @Autowired
    TestApixUtil testApixUtil;

    @Autowired
    ApiPerfTestService apiPerfTestService;

    @Autowired
    VariableService variableService;

    @Autowired
    StepCommonService stepCommonService;

    @Autowired
    InstanceService instanceService;


    /**
     * rpc 远程调用
     */
    ApiPerfTestService apiPerfTestServiceRPC(String agentUrl){
        return rpcClientApixUtil.rpcClient().getBean(ApiPerfTestService.class, new FixedLookup(agentUrl));
    }

    /**
     *  环境中获取是否是内嵌agent
     */
    @Value("${teston-agent.embbed.enable:false}")
    Boolean enable;

    private List<AgentConfig> agentConfigList;
    //执行次数
    private Integer executeCount;

    /**
     * 执行的状态：0：未执行，1：正在进行
     */
    public Integer status=0;

    /**
     *  执行后  性能测试历史Id用于实时保存的历史
     */
    private String apiPerfInstanceId;

    @Override
    public void execute(ApiPerfTestRequest apiPerfTestRequest) {
        //开始执行
        status=1;
        apiPerfInstanceId=null;

        //查询当前压力测试
        String apiPerfId = apiPerfTestRequest.getApiPerfCase().getId();
        ApiPerfCase apiPerfCase = apiPerfCaseService.findApiPerfCase(apiPerfId);

        //执行的数据 处理
        List<ApiSceneTestRequest> apiSceneTestRequestList = processApiPerfTestData(apiPerfTestRequest);

        //如果没有场景直接结束
        if(apiSceneTestRequestList==null|| apiSceneTestRequestList.isEmpty()){
            status=0;
            return;
        }

        //构造数据
        apiPerfTestRequest.setApiPerfCase(apiPerfCase);
        apiPerfTestRequest.setApiSceneTestRequestList(apiSceneTestRequestList);

        //执行次数
        executeCount = apiPerfCase.getExecuteCount();

        //执行测试
        //是否内嵌
        if(enable){
            apiPerfTestRequest.setExeNum(executeCount);
            apiPerfTestService.execute(apiPerfTestRequest);
        } else {

            if( CollectionUtils.isNotEmpty(agentConfigList)) {
                //agent数量
                int agentSize = agentConfigList.size();

                //执行方式,循环或随机
                Integer executeType = apiPerfCase.getExecuteType();

                //先分配好各个agent所需的次数
                List<Integer> distributionList = new ArrayList<>();

                //执行方式循环
                if (executeType == 1) {
                    distributionList = testApixUtil.loop(executeCount, agentSize);
                }

                if (executeType == 2) {
                    distributionList = testApixUtil.random(executeCount, agentSize);
                }

                for (int i = 0; i < agentSize; i++) {
                    //
                    apiPerfTestRequest.setExeNum(distributionList.get(i));

                    //获取agentId，agentList index 从0开始
                    AgentConfig agentConfig = agentConfigList.get(i);
                    String agentUrl = agentConfig.getUrl();

                    //执行测试
                    apiPerfTestServiceRPC(agentUrl).execute(apiPerfTestRequest);
                }

            }else {
                throw new ApplicationException("不是内嵌agent，请到设置中配置agent");
            }
        }
    }


    @Override
    public ApiPerfTestResponse result(ApiPerfTestRequest apiPerfTestRequest) {
        ApiPerfTestResponse apiPerfTestResponse = new ApiPerfTestResponse();

        ArrayList<ApiSceneInstance> arrayList = new ArrayList<>();

        ApiPerfTestResponse response = null;
        //是否内嵌
        if(enable){
            response = apiPerfTestService.exeResult();
            arrayList.addAll(response.getApiSceneInstanceList());
        }else {
            agentConfigList = getAgentList();
            if(CollectionUtils.isNotEmpty(agentConfigList)){
                for(int i = 0;i<agentConfigList.size();i++){
                    response = apiPerfTestServiceRPC(agentConfigList.get(i).getUrl()).exeResult();

                    //获取每个agent的实例
                    if(CollectionUtils.isNotEmpty(response.getApiSceneInstanceList())){
                        arrayList.addAll(response.getApiSceneInstanceList());
                    }
                }
            }
        }


        //把所有实例设置到apiPerfTestResponse
        apiPerfTestResponse.setApiSceneInstanceList(arrayList);

        if(apiPerfTestResponse.getApiSceneInstanceList().size()==executeCount){
            status=0;
        }else {
            status=1;
        }


        ApiPerfInstance apiPerfInstance = processPerfTestData(apiPerfTestResponse);
        apiPerfTestResponse.setApiPerfInstance(apiPerfInstance);

        //测试计划中设置了值
        if(apiPerfTestRequest.getExeType()==null){
            //请求一次获取一次结果，存入数据库
            String apiPerfId = apiPerfTestRequest.getApiPerfCase().getId();
            apiPerfInstance.setApiPerfId(apiPerfId);

            //判断是否存在
            if(apiPerfInstanceId==null&&apiPerfInstance.getTotal()>0){

                apiPerfInstanceId = apiPerfInstanceService.createApiPerfInstance(apiPerfInstance);
                ApiPerfCase apiPerfCase = apiPerfCaseService.findApiPerfCase(apiPerfId);

                Instance instance = new Instance();
                instance.setId(apiPerfInstanceId);
                instance.setRepositoryId(apiPerfCase.getTestCase().getRepositoryId());
                instance.setBelongId(apiPerfId);
                instance.setType(MagicValue.CASE_TYPE_API_PERFORM);
                instance.setName(apiPerfCase.getTestCase().getName());

                InstanceQuery instanceQuery = new InstanceQuery();
                instanceQuery.setBelongId(apiPerfId);
                List<Instance> instanceList = instanceService.findInstanceList(instanceQuery);
                if(instanceList!=null&& !instanceList.isEmpty()){
                    Integer executeNumber = instanceList.get(0).getExecuteNumber();
                    instance.setExecuteNumber(++executeNumber);
                }else {
                    instance.setExecuteNumber(1);
                }

                JSONObject instanceMap = new JSONObject();
                instanceMap.put("result",apiPerfInstance.getResult().toString());
                instanceMap.put("total",apiPerfInstance.getTotal().toString());
                instanceMap.put("passNum",apiPerfInstance.getPassNum().toString());
                instanceMap.put("passRate",apiPerfInstance.getPassRate());
                instanceMap.put("failNum",apiPerfInstance.getFailNum().toString());
                instanceMap.put("errorRate",apiPerfInstance.getErrorRate());
                instance.setContent(instanceMap.toString());

                instanceService.createInstance(instance);
            }else {
                apiPerfInstance.setId(apiPerfInstanceId);
                apiPerfInstanceService.updateApiPerfInstance(apiPerfInstance);

                // 更新公共实例
                Instance instance = new Instance();
                instance.setId(apiPerfInstanceId);

                JSONObject instanceMap = new JSONObject();
                instanceMap.put("result",apiPerfInstance.getResult().toString());
                instanceMap.put("total",apiPerfInstance.getTotal().toString());
                instanceMap.put("passNum",apiPerfInstance.getPassNum().toString());
                instanceMap.put("passRate",apiPerfInstance.getPassRate());
                instanceMap.put("failNum",apiPerfInstance.getFailNum().toString());
                instanceMap.put("errorRate",apiPerfInstance.getErrorRate());
                instance.setContent(instanceMap.toString());

                instanceService.updateInstance(instance);
            }
        }


        return apiPerfTestResponse;
    }

    @Override
    public Integer status() {
        if(enable) {
            //调用执行方法返回结果数据
            status = apiPerfTestService.status();
        }else {
            int processStatus=0;

            try {
                agentConfigList = getAgentList();
                if(CollectionUtils.isNotEmpty(agentConfigList)){
                    for(int i = 0;i<agentConfigList.size();i++) {
                        Integer status = apiPerfTestServiceRPC(agentConfigList.get(i).getUrl()).status();
                        processStatus=status;
                    }
                }else {
                    throw new ApplicationException("未设置agent，请到设置中配置agent");
                }

            }catch (Exception e){
                status=0;
                throw new ApplicationException(e);
            }


            status=processStatus;
        }


        return status;
    }

    /**
     * 数据构造
     * @param apiPerfTestRequest
     */
    private List<ApiSceneTestRequest> processApiPerfTestData(ApiPerfTestRequest apiPerfTestRequest) {
        String apiPerfId = apiPerfTestRequest.getApiPerfCase().getId();
        //查询所有场景
        ApiPerfStepQuery apiPerfStepQuery = new ApiPerfStepQuery();
        apiPerfStepQuery.setApiPerfId(apiPerfId);
        List<ApiPerfStep> apiPerfStepList = apiPerfStepService.findApiPerfStepList(apiPerfStepQuery);

        //如果没有场景直接结束
        if(apiPerfStepList==null||apiPerfStepList.size()==0){return null;}

        List<ApiSceneTestRequest> apiSceneTestRequestList = new ArrayList<>();

        // 测试数据索引
        int dataIndex = 0;
        //获取测试数据
        List<JSONObject> testDataList = apiPerfTestDataService.getTestData(apiPerfId);

        //循环所有场景构造数据
        for(ApiPerfStep apiPerfStep:apiPerfStepList){
            ApiSceneTestRequest apiSceneTestRequest = new ApiSceneTestRequest();

            StepCommonQuery stepCommonQuery = new StepCommonQuery();
            stepCommonQuery.setCaseId(apiPerfStep.getApiScene().getId());
            stepCommonQuery.setCaseType(MagicValue.CASE_TYPE_API_SCENE);
            List<StepCommon> stepCommonList = stepCommonService.findStepCommonList(stepCommonQuery);
            apiSceneTestRequest.setStepCommonList(stepCommonList);

            //环境变量设置
            JSONObject variable = processVariable(apiSceneTestRequest, testDataList,dataIndex);
            apiSceneTestRequest.setVariableJson(variable);

            //设置pre url
            apiSceneTestRequest.setApiEnv(apiPerfTestRequest.getApiEnv());

            // 测试数据索引+1
            dataIndex++;
            if (dataIndex >= testDataList.size()) {
                dataIndex = 0;
            }

            apiSceneTestRequestList.add(apiSceneTestRequest);
        }


        return apiSceneTestRequestList;

    }


    /**
     * 处理测试数据
     * @param apiPerfTestResponse
     * @return
     */
    private ApiPerfInstance processPerfTestData(ApiPerfTestResponse apiPerfTestResponse) {
        ApiPerfInstance apiPerfInstance = new ApiPerfInstance();

        int size = apiPerfTestResponse.getApiSceneInstanceList().size();
        apiPerfInstance.setTotal(size);

        int passCount = 0;
        for (ApiSceneInstance apiSceneInstance : apiPerfTestResponse.getApiSceneInstanceList()) {
            if(apiSceneInstance.getResult().equals(1)){
                passCount++;
            }
        }

        String passRate = testApixUtil.processRate(passCount, size);
        apiPerfInstance.setPassRate(passRate);
        apiPerfInstance.setPassNum(passCount);
        apiPerfInstance.setFailNum(size-passCount);
        String errorRate = testApixUtil.processRate(size - passCount, size);
        apiPerfInstance.setErrorRate(errorRate);

        if(size==passCount){
            apiPerfInstance.setResult(1);
        }else {
            apiPerfInstance.setResult(0);
        }

        return apiPerfInstance;
    }

    /**
     *
     * @param apiSceneTestRequest
     * @param dataIndex
     * @return
     */
    private JSONObject processVariable(
            ApiSceneTestRequest apiSceneTestRequest,
            List<JSONObject> testDataList,
            int dataIndex) {
        //环境变量
        JSONObject variable = variableService.getVariable(apiSceneTestRequest.getRepositoryId());


        if(testDataList!=null&&testDataList.size()>0){
            // 获取当前测试数据
            JSONObject testData = testDataList.get(dataIndex);
            variable.putAll(testData);
        }

        return variable;
    }

    /**
     * 获取agent
     */
    private List<AgentConfig> getAgentList(){
        AgentConfigQuery agentConfigQuery = new AgentConfigQuery();
//        agentConfigQuery.setRepositoryId(repositoryId);
        List<AgentConfig> agentConfigList = agentConfigService.findAgentConfigList(agentConfigQuery);
        return agentConfigList;
    }

}
