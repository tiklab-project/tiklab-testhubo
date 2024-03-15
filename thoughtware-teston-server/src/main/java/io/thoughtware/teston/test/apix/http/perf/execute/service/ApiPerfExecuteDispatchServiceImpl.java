package io.thoughtware.teston.test.apix.http.perf.execute.service;

import com.alibaba.fastjson.JSONObject;
import io.thoughtware.core.exception.ApplicationException;
import io.thoughtware.teston.common.TestOnUnit;
import io.thoughtware.teston.common.TestUtil;
import io.thoughtware.teston.instance.model.Instance;
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

    @Autowired
    TestUtil testUtil;


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


    @Override
    public void execute(ApiPerfTestRequest apiPerfTestRequest) {
        String apiPerfId = apiPerfTestRequest.getApiPerfCase().getId();
        String apiPerfInstanceId = createInitApiPerfInstance(apiPerfId);

        try {
            executeStart(apiPerfTestRequest);
        }catch (Exception e){
            //更新状态为失败
            updateApiPerfInstanceStatus(apiPerfInstanceId,MagicValue.TEST_STATUS_FAIL);
            throw new ApplicationException(e);
        }

    }


    @Override
    public void executeStart(ApiPerfTestRequest apiPerfTestRequest) {
        String apiPerfId = apiPerfTestRequest.getApiPerfCase().getId();
        ApiPerfCase apiPerfCase = apiPerfCaseService.findApiPerfCase(apiPerfId);

        //执行的数据 处理
        List<ApiSceneTestRequest> apiSceneTestRequestList = processApiPerfTestData(apiPerfTestRequest);

        //构造数据
        apiPerfTestRequest.setApiPerfCase(apiPerfCase);
        apiPerfTestRequest.setApiSceneTestRequestList(apiSceneTestRequestList);

        //执行次数
        Integer executeCount = apiPerfCase.getExecuteCount();

        //执行测试
        //是否内嵌
        if(enable){
            apiPerfTestRequest.setExeNum(executeCount);
            apiPerfTestService.execute(apiPerfTestRequest);
        } else {
            agentConfigList = getAgentList();
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

        try {
            //程序执行完，最后一次获取数据
            result(apiPerfTestRequest);
            Thread.sleep(3000);

            logger.info("------------------------------------------数据清理------------------------------------------");

            //清理数据
            if(enable){
                apiPerfTestService.cleanUpData(apiPerfId);
            }else {
                //agent数量
                int agentSize = agentConfigList.size();
                for (int i = 0; i < agentSize; i++) {
                    //获取agentId，agentList index 从0开始
                    AgentConfig agentConfig = agentConfigList.get(i);
                    String agentUrl = agentConfig.getUrl();

                    apiPerfTestServiceRPC(agentUrl).cleanUpData(apiPerfId);
                }
            }
        }catch (Exception e){
            throw new ApplicationException(e);
        }
    }

    /**
     * 创建初始历史，获取历史id
     * @param apiPerfId
     * @return
     */
    private String createInitApiPerfInstance(String apiPerfId){
        ApiPerfCase apiPerfCase = apiPerfCaseService.findApiPerfCase(apiPerfId);

        ApiPerfInstance apiPerfInstance = new ApiPerfInstance();
        apiPerfInstance.setTotal(apiPerfCase.getExecuteCount());
        apiPerfInstance.setPassNum(0);
        apiPerfInstance.setPassRate("0.00%");
        apiPerfInstance.setFailNum(0);
        apiPerfInstance.setErrorRate("0.00%");
        String apiPerfInstanceId = apiPerfInstanceService.createApiPerfInstance(apiPerfInstance);

        Instance instance = new Instance();
        instance.setId(apiPerfInstanceId);
        instance.setRepositoryId(apiPerfCase.getTestCase().getRepositoryId());
        instance.setBelongId(apiPerfId);
        instance.setType(MagicValue.CASE_TYPE_API_PERFORM);
        instance.setName(apiPerfCase.getTestCase().getName());

        //获取当前执行次数
        int executeNum = instanceService.getRecentExecuteNum(apiPerfId);
        instance.setExecuteNumber(executeNum);

        //设置状态,开始执行
        instance.setStatus(MagicValue.TEST_STATUS_START);

        JSONObject instanceMap = new JSONObject();
        instanceMap.put("total",apiPerfInstance.getTotal().toString());
        instanceMap.put("passNum",apiPerfInstance.getPassNum().toString());
        instanceMap.put("passRate",apiPerfInstance.getPassRate());
        instanceMap.put("failNum",apiPerfInstance.getFailNum().toString());
        instanceMap.put("errorRate",apiPerfInstance.getErrorRate());
        instance.setContent(instanceMap.toString());

        instanceService.createInstance(instance);

        return apiPerfInstanceId;
    }

    @Override
    public ApiPerfTestResponse result(ApiPerfTestRequest apiPerfTestRequest) {

        ApiPerfTestResponse apiPerfTestResponse = getResult(apiPerfTestRequest);

        String apiPerfId = apiPerfTestRequest.getApiPerfCase().getId();
        updateApiPerfInstance(apiPerfTestResponse,apiPerfId);

        return apiPerfTestResponse;
    }


    @Override
    public ApiPerfTestResponse getResult(ApiPerfTestRequest apiPerfTestRequest){
        ApiPerfTestResponse apiPerfTestResponse = new ApiPerfTestResponse();
        try {
            //是否内嵌
            if (enable) {
                apiPerfTestResponse = apiPerfTestService.exeResult(apiPerfTestRequest);
            } else {
                agentConfigList = getAgentList();

                if (CollectionUtils.isNotEmpty(agentConfigList)) {
                    ArrayList<ApiPerfTestResponse> apiPerfTestResponseList = new ArrayList<>();
                    for (int i = 0; i < agentConfigList.size(); i++) {
                        ApiPerfTestResponse response = apiPerfTestServiceRPC(agentConfigList.get(i).getUrl()).exeResult(apiPerfTestRequest);
                        apiPerfTestResponseList.add(response);
                    }

                    apiPerfTestResponse = multiAgentProcess(apiPerfTestResponseList);
                }
            }

        }catch (Exception e){
            apiPerfTestResponse.setStatus(0);
            throw new ApplicationException(e);
        }

        return apiPerfTestResponse;
    }


    /**
     * 处理多个agent数据
     * @param apiPerfTestResponseList
     */
    private ApiPerfTestResponse multiAgentProcess(ArrayList<ApiPerfTestResponse> apiPerfTestResponseList){
        if(apiPerfTestResponseList==null||apiPerfTestResponseList.isEmpty()){
            ApiPerfInstance apiPerfInstance = new ApiPerfInstance();
            apiPerfInstance.setTotal(0);
            apiPerfInstance.setPassNum(0);
            apiPerfInstance.setPassRate("0.00%");
            apiPerfInstance.setFailNum(0);
            apiPerfInstance.setErrorRate("0.00%");

            ApiPerfTestResponse allApiPerfTestResponse = new ApiPerfTestResponse();
            allApiPerfTestResponse.setApiPerfInstance(apiPerfInstance);

            return allApiPerfTestResponse;
        }


        int total = 0;
        Integer failNum=0;
        Integer passNum=0;

        ArrayList<ApiSceneInstance> apiSceneInstanceList = new ArrayList<>();

        for(ApiPerfTestResponse apiPerfTestResponse:apiPerfTestResponseList){
            ApiPerfInstance apiPerfInstanceItem = apiPerfTestResponse.getApiPerfInstance();
            total += apiPerfInstanceItem.getTotal();
            failNum += apiPerfInstanceItem.getFailNum();
            passNum += apiPerfInstanceItem.getPassNum();

            apiSceneInstanceList.addAll(apiPerfTestResponse.getApiSceneInstanceList());
        }

        ApiPerfInstance apiPerfInstance = new ApiPerfInstance();
        apiPerfInstance.setTotal(total);
        apiPerfInstance.setPassNum(passNum);
        apiPerfInstance.setPassRate(testUtil.processRate(passNum,total));
        apiPerfInstance.setFailNum(failNum);
        apiPerfInstance.setErrorRate(testUtil.processRate(failNum,total));

        ApiPerfTestResponse allApiPerfTestResponse = new ApiPerfTestResponse();
        allApiPerfTestResponse.setApiPerfInstance(apiPerfInstance);
        allApiPerfTestResponse.setApiSceneInstanceList(apiSceneInstanceList);

        return allApiPerfTestResponse;
    }

    /**
     * 更新实例
     * @param apiPerfTestResponse
     * @param apiPerfId
     */
    private void updateApiPerfInstance(ApiPerfTestResponse apiPerfTestResponse,String apiPerfId) {
        String apiPerfInstanceId = instanceService.getRecentExecuteInstanceId(apiPerfId);
        if(apiPerfInstanceId!=null&&apiPerfTestResponse.getApiPerfInstance()!=null){
            //请求一次获取一次结果，存入数据库
            ApiPerfInstance apiPerfInstance = apiPerfTestResponse.getApiPerfInstance();
            apiPerfInstance.setApiPerfId(apiPerfId);
            apiPerfInstance.setId(apiPerfInstanceId);
            apiPerfInstanceService.updateApiPerfInstance(apiPerfInstance);

            Instance instance = instanceService.findInstance(apiPerfInstanceId);
            // 更新公共实例
            JSONObject instanceMap = new JSONObject();
            instanceMap.put("total",apiPerfInstance.getTotal().toString());
            instanceMap.put("passNum",apiPerfInstance.getPassNum().toString());
            instanceMap.put("passRate",apiPerfInstance.getPassRate());
            instanceMap.put("failNum",apiPerfInstance.getFailNum().toString());
            instanceMap.put("errorRate",apiPerfInstance.getErrorRate());
            instance.setContent(instanceMap.toString());

            Integer status = apiPerfTestResponse.getStatus();
            if(status==0){
                instance.setStatus(MagicValue.TEST_STATUS_SUCCESS);
            }

            instanceService.updateInstance(instance);
        }else {
            updateApiPerfInstanceStatus(apiPerfId,MagicValue.TEST_STATUS_FAIL);
        }
    }

    /**
     * 状态更改
     * @param apiPerfInstanceId
     */
    private void updateApiPerfInstanceStatus(String apiPerfInstanceId,String status){
        Instance instance = instanceService.findInstance(apiPerfInstanceId);
        instance.setStatus(status);
        instanceService.updateInstance(instance);
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
