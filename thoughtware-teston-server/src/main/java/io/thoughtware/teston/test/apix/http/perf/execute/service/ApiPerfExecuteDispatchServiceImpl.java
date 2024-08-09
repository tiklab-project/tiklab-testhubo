package io.thoughtware.teston.test.apix.http.perf.execute.service;

import com.alibaba.fastjson.JSONObject;
import io.thoughtware.core.exception.ApplicationException;
import io.thoughtware.teston.common.TestUtil;
import io.thoughtware.teston.instance.model.Instance;
import io.thoughtware.teston.instance.service.InstanceService;
import io.thoughtware.teston.test.apix.http.perf.execute.model.ApiPerfStepTestData;
import io.thoughtware.teston.test.apix.http.perf.instance.model.*;
import io.thoughtware.teston.test.apix.http.perf.instance.service.ApiPerfStepInstanceService;
import io.thoughtware.teston.test.apix.http.perf.instance.service.ApiPerfStepUnitCalcService;
import io.thoughtware.teston.test.apix.http.unit.cases.model.ApiUnitCase;
import io.thoughtware.teston.test.apix.http.unit.cases.model.ApiUnitCaseDataConstruction;
import io.thoughtware.teston.test.apix.http.unit.cases.service.ApiUnitCaseService;
import io.thoughtware.teston.test.apix.http.unit.execute.model.ApiUnitTestRequest;
import io.thoughtware.teston.test.common.stepcommon.model.StepCommon;
import io.thoughtware.teston.test.common.stepcommon.model.StepCommonQuery;
import io.thoughtware.teston.test.common.stepcommon.service.StepCommonService;
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
import io.thoughtware.teston.test.apix.http.perf.instance.service.ApiPerfInstanceService;
import io.thoughtware.teston.test.apix.http.scene.execute.model.ApiSceneTestRequest;

import io.thoughtware.teston.test.common.wstest.WsTestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.*;

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
    VariableService variableService;

    @Autowired
    ApiUnitCaseService apiUnitCaseService;

    @Autowired
    StepCommonService stepCommonService;

    @Autowired
    InstanceService instanceService;

    @Autowired
    TestUtil testUtil;

    @Autowired
    WsTestService wsTestService;

    @Autowired
    ApiPerfStepInstanceService apiPerfStepInstanceService;

    @Autowired
    ApiPerfStepUnitCalcService apiPerfStepUnitCalcService;


    private Map<String, ScheduledFuture<?>> scheduleFutureMap = new ConcurrentHashMap<>();
    //定时器，不断更新数据库结果的
    private final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
    //定时器，不断获取agent发回来的数据
    private ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);

    //是否存在正在执行的性能测试
    private static Set<String> apiPerfIdSet = new HashSet<>();
    //apiPerfId：对应的响应结果
    private final static Map<String, ApiPerfTestResponse> perfTestResponseMap = new ConcurrentHashMap<>();
    //用例id : agentId
    private  HashMap<String, String> apiPerfIdAndAgentIdMap = new HashMap<>();

    @Override
    public Boolean execute(ApiPerfTestRequest apiPerfTestRequest) {
        String apiPerfId = apiPerfTestRequest.getApiPerfId();

        // 检查 apiPerfId 是否已存在，如果存在则直接返回
        if (!apiPerfIdSet.add(apiPerfId)) {
            return true;
        }

        String apiPerfInstanceId = createInitApiPerfInstance(apiPerfId);
        try {
            long startTime =  System.currentTimeMillis();

            // 执行性能测试
            executeStart(apiPerfTestRequest);

            //防止还没执行就开始获取结果保存
            Thread.sleep(1100);

            //不断获取性能测试结果存入更新数据库
            ScheduledFuture<?> scheduledFuture = scheduledExecutorService.scheduleAtFixedRate(() -> {
                //获取数据
                ApiPerfTestResponse apiPerfTestResponse = getResult(apiPerfTestRequest);

                long endTime = System.currentTimeMillis();
                long elapsedTime = endTime - startTime - 1100;
                apiPerfTestResponse.getApiPerfInstance().setElapsedTime((int)elapsedTime);
                updateApiPerfInstance(apiPerfTestResponse,apiPerfId);

                if(!Objects.equals(apiPerfTestResponse.getApiPerfInstance().getStatus(), MagicValue.TEST_STATUS_START)){
                    closeThread(apiPerfId);
                }
            }, 1, 2, TimeUnit.SECONDS);
            scheduleFutureMap.put(apiPerfId, scheduledFuture);
        }catch (Exception e){
            //更新状态为失败
            updateApiPerfInstanceStatus(apiPerfInstanceId,MagicValue.TEST_STATUS_FAIL);

            if (e instanceof ApplicationException) {
                int errorCode = ((ApplicationException) e).getErrorCode();
                throw new ApplicationException(errorCode, e.getMessage());
            } else {
                throw new ApplicationException(10001, e.getMessage());
            }
        }

        return false;
    }

    private void closeThread(String caseId) {
        ScheduledFuture<?> scheduledFuture = scheduleFutureMap.remove(caseId);
        if (scheduledFuture != null) {
            scheduledFuture.cancel(true);
        }
    }


    @Override
    public void executeStart(ApiPerfTestRequest apiPerfTestRequest) {
        String apiPerfId = apiPerfTestRequest.getApiPerfId();

        //执行的数据 处理
        List<ApiPerfStepTestData> apiPerfStepTestData = processApiPerfTestData(apiPerfTestRequest);
        apiPerfTestRequest.setApiPerfStepTestData(apiPerfStepTestData);

        //获取agentId
        String agentId = apiPerfTestRequest.getAgentId();
        apiPerfIdAndAgentIdMap.put(apiPerfId,agentId);

        //执行测试
        JSONObject apiUnitObject = new JSONObject();
        apiUnitObject.put("apiPerfTestRequest",apiPerfTestRequest);
        apiUnitObject.put("type",MagicValue.CASE_TYPE_API_PERFORM);
        apiUnitObject.put("caseId",apiPerfId);

        wsTestService.sendMessageExe(agentId,apiUnitObject,null);
    }

    /**
     * 创建初始历史，获取历史id
     * @param apiPerfId
     * @return
     */
    private String createInitApiPerfInstance(String apiPerfId){
        ApiPerfCase apiPerfCase = apiPerfCaseService.findApiPerfCase(apiPerfId);

        ApiPerfInstance apiPerfInstance = getInitApiPerfInstance(apiPerfId);
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
    public ApiPerfInstance result(String apiPerfId) {
        String apiPerfInstanceId = instanceService.getRecentExecuteInstanceId(apiPerfId);
        ApiPerfInstance apiPerfInstance = apiPerfInstanceService.findApiPerfInstance(apiPerfInstanceId);
        return apiPerfInstance;
    }


    @Override
    public ApiPerfTestResponse getResult(ApiPerfTestRequest apiPerfTestRequest){
        String apiPerfId = apiPerfTestRequest.getApiPerfId();
        ApiPerfTestResponse apiPerfTestResponse = new ApiPerfTestResponse();
        try {
            ArrayList<ApiPerfTestResponse> responseList = new ArrayList<>();

            synchronized (perfTestResponseMap) {
                perfTestResponseMap.forEach((caseId, response) -> {
                    if (caseId.equals(apiPerfId)) {
                        responseList.add(response);
                    }
                });
            }

            apiPerfTestResponse = multiAgentProcess(responseList);

            // If test has finished, remove the corresponding entries
            if (!Objects.equals(apiPerfTestResponse.getApiPerfInstance().getStatus(), MagicValue.TEST_STATUS_START)) {
                synchronized (perfTestResponseMap) {
                    perfTestResponseMap.remove(apiPerfId);
                }
                apiPerfIdSet.remove(apiPerfId);
            }
        } catch (Exception e) {
            synchronized (perfTestResponseMap) {
                perfTestResponseMap.remove(apiPerfId);
            }
            apiPerfIdSet.remove(apiPerfId);

            logger.error("getResult error:{}",e.getMessage());
            ApiPerfInstance apiPerfInstance = getInitApiPerfInstance(apiPerfId);
            apiPerfInstance.setStatus(MagicValue.TEST_STATUS_FAIL);
            apiPerfTestResponse.setApiPerfInstance(apiPerfInstance);
        }

        return apiPerfTestResponse;
    }

    @Override
    public void stopTest(String apiPerfId) {
        JSONObject apiUnitObject = new JSONObject();
        apiUnitObject.put("type",MagicValue.TEST_API_PERFORM_STOP);
        apiUnitObject.put("caseId",apiPerfId);

        //获取agentId，agentList index 从0开始
        String agentId = apiPerfIdAndAgentIdMap.get(apiPerfId);
        wsTestService.sendMessageExe(agentId,apiUnitObject,null);
    }

    /**
     * 初始实例
     */
    private ApiPerfInstance getInitApiPerfInstance (String caseId){
        ApiPerfInstance apiPerfInstance = new ApiPerfInstance();
        apiPerfInstance.setTotal(0);
        apiPerfInstance.setApiPerfId(caseId);
        apiPerfInstance.setPassNum(0);
        apiPerfInstance.setPassRate("0.00%");
        apiPerfInstance.setFailNum(0);
        apiPerfInstance.setErrorRate("0.00%");

        return apiPerfInstance;
    }


    /**
     * 处理多个agent数据
     * @param apiPerfTestResponseList
     */
    private ApiPerfTestResponse multiAgentProcess(ArrayList<ApiPerfTestResponse> apiPerfTestResponseList){

        int total = 0;
        Integer failNum=0;
        Integer passNum=0;
        List<String> statusList = new ArrayList<>();

        ArrayList<ApiPerfStepInstance> allCalcList = new ArrayList<>();

        for(ApiPerfTestResponse apiPerfTestResponse:apiPerfTestResponseList){
            ApiPerfInstance apiPerfInstanceItem = apiPerfTestResponse.getApiPerfInstance();
            total += apiPerfInstanceItem.getTotal();
            failNum += apiPerfInstanceItem.getFailNum();
            passNum += apiPerfInstanceItem.getPassNum();

            statusList.add(apiPerfInstanceItem.getStatus());

            allCalcList.addAll(apiPerfTestResponse.getApiPerfStepInstanceList());
        }

        ApiPerfInstance apiPerfInstance = new ApiPerfInstance();
        apiPerfInstance.setTotal(total);
        apiPerfInstance.setPassNum(passNum);
        apiPerfInstance.setPassRate(testUtil.processRate(passNum,total));
        apiPerfInstance.setFailNum(failNum);
        apiPerfInstance.setErrorRate(testUtil.processRate(failNum,total));

        if(statusList.contains(MagicValue.TEST_STATUS_START)){
            apiPerfInstance.setStatus(MagicValue.TEST_STATUS_START);
        }else {
            apiPerfInstance.setStatus(MagicValue.TEST_STATUS_COMPLETE);
        }

        ApiPerfTestResponse allApiPerfTestResponse = new ApiPerfTestResponse();
        allApiPerfTestResponse.setApiPerfInstance(apiPerfInstance);
        allApiPerfTestResponse.setApiPerfStepInstanceList(allCalcList);

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
            instanceMap.put("elapsedTime",apiPerfInstance.getElapsedTime());
            instance.setContent(instanceMap.toString());
            instance.setStatus(apiPerfInstance.getStatus());
            instanceService.updateInstance(instance);

            //创建/更新 总和数据
            ApiPerfStepInstanceQuery apiPerfStepInstanceQuery = new ApiPerfStepInstanceQuery();
            apiPerfStepInstanceQuery.setApiPerfInstanceId(apiPerfInstanceId);
            List<ApiPerfStepInstance> apiPerfStepInstanceList = apiPerfStepInstanceService.findApiPerfStepInstanceList(apiPerfStepInstanceQuery);
            if(apiPerfStepInstanceList.size()>0){
                for (ApiPerfStepInstance ApiPerfStepInstance:apiPerfStepInstanceList){
                    apiPerfStepInstanceService.deleteApiPerfStepInstance(ApiPerfStepInstance.getId());
                }
            }

            if(apiPerfTestResponse.getApiPerfStepInstanceList()!=null && apiPerfTestResponse.getApiPerfStepInstanceList().size()>0){
                for(ApiPerfStepInstance apiPerfStepInstance:apiPerfTestResponse.getApiPerfStepInstanceList()){
                    apiPerfStepInstance.setApiPerfInstanceId(apiPerfInstanceId);
                    String apiPerfStepInstanceId = apiPerfStepInstanceService.createApiPerfStepInstance(apiPerfStepInstance);

                    if (apiPerfStepInstance.getApiPerfStepUnitCalcList() != null && apiPerfStepInstance.getApiPerfStepUnitCalcList().size() > 0) {
                        for (ApiPerfStepUnitCalc apiPerfStepUnitCalc : apiPerfStepInstance.getApiPerfStepUnitCalcList()) {
                            apiPerfStepUnitCalc.setApiPerfStepInstanceId(apiPerfStepInstanceId);
                            apiPerfStepUnitCalcService.createApiPerfStepUnitCalc(apiPerfStepUnitCalc);
                        }
                    }
                }
            }
        }else {
            updateApiPerfInstanceStatus(apiPerfId,MagicValue.TEST_STATUS_FAIL);
        }
    }

    /**
     * 状态更改
     * @param apiPerfInstanceId
     */
    private void updateApiPerfInstanceStatus(String apiPerfInstanceId,String status){
        ApiPerfInstance apiPerfInstance = apiPerfInstanceService.findApiPerfInstance(apiPerfInstanceId);
        apiPerfInstance.setStatus(status);
        apiPerfInstanceService.updateApiPerfInstance(apiPerfInstance);

        Instance instance = instanceService.findInstance(apiPerfInstanceId);
        instance.setStatus(status);
        instanceService.updateInstance(instance);
    }


    /**
     * 数据构造
     * @param apiPerfTestRequest
     */
    private List<ApiPerfStepTestData> processApiPerfTestData(ApiPerfTestRequest apiPerfTestRequest) {
        String apiPerfId = apiPerfTestRequest.getApiPerfId();
        String apiEnv = apiPerfTestRequest.getApiEnv();

        //查询所有关联的用例
        ApiPerfStepQuery apiPerfStepQuery = new ApiPerfStepQuery();
        apiPerfStepQuery.setApiPerfId(apiPerfId);
        List<ApiPerfStep> apiPerfStepList = apiPerfStepService.findApiPerfStepList(apiPerfStepQuery);

        //如果没有场景直接结束
        if(apiPerfStepList==null||apiPerfStepList.size()==0){return null;}

        List<ApiPerfStepTestData> ApiPerfStepTestDataList = new ArrayList<>();

        //循环所有用例构造数据
        for(ApiPerfStep apiPerfStep:apiPerfStepList){
            ApiPerfStepTestData apiPerfStepTestData = new ApiPerfStepTestData();
            apiPerfStepTestData.setApiPerfStep(apiPerfStep);

            if(apiPerfStep.getCaseType().equals(MagicValue.CASE_TYPE_API_UNIT)){
                ApiUnitTestRequest apiUnitTestRequest = processApiUnitData(apiPerfStep, apiEnv);
                apiPerfStepTestData.setApiUnitTestRequest(apiUnitTestRequest);
            }

            if(apiPerfStep.getCaseType().equals(MagicValue.CASE_TYPE_API_SCENE)){
                ApiSceneTestRequest apiSceneTestRequest = processApiSceneData(apiPerfStep, apiEnv);
                apiPerfStepTestData.setApiSceneTestRequest(apiSceneTestRequest);
            }

            ApiPerfStepTestDataList.add(apiPerfStepTestData);
        }

        return ApiPerfStepTestDataList;
    }


    /**
     * 构造 apiscene 测试数据
     * @param apiPerfStep
     * @param apiEnv
     * @return
     */
    private ApiSceneTestRequest processApiSceneData(ApiPerfStep apiPerfStep,String apiEnv){
        ApiSceneTestRequest apiSceneTestRequest = new ApiSceneTestRequest();

        StepCommonQuery stepCommonQuery = new StepCommonQuery();
        stepCommonQuery.setCaseId(apiPerfStep.getApiScene().getId());
        stepCommonQuery.setCaseType(MagicValue.CASE_TYPE_API_SCENE);
        List<StepCommon> stepCommonList = stepCommonService.findStepCommonList(stepCommonQuery);
        apiSceneTestRequest.setStepCommonList(stepCommonList);

        //环境变量设置
        JSONObject variable = processVariable(apiPerfStep.getId());
        apiSceneTestRequest.setVariableJson(variable);

        //设置pre url
        apiSceneTestRequest.setApiEnv(apiEnv);

        return apiSceneTestRequest;
    }

    /**
     * apiunit数据构造
     * @param apiPerfStep
     * @param apiEnv
     * @return
     */
    private ApiUnitTestRequest processApiUnitData(ApiPerfStep apiPerfStep,String apiEnv){
        String caseId = apiPerfStep.getCaseId();

        ApiUnitTestRequest apiUnitTestRequest = new ApiUnitTestRequest();
        ApiUnitCase apiUnitCase = apiUnitCaseService.findApiUnitCase(caseId);
        ApiUnitCaseDataConstruction apiUnitCaseDataConstruction = apiUnitCaseService.findApiUnitCaseExt(apiUnitCase);

        apiUnitTestRequest.setApiUnitCaseDataConstruction(apiUnitCaseDataConstruction);
        apiUnitTestRequest.setApiUnitCase(apiUnitCase);
        apiUnitTestRequest.setApiEnv(apiEnv);

        //环境变量设置
        JSONObject variable = processVariable(caseId);
        apiUnitTestRequest.setVariableJson(variable);

        return apiUnitTestRequest;
    }


    /**
     *  获取测试数据
     * @return
     */
    private JSONObject processVariable(String apiPerfStepId) {
        //获取测试数据
        List<JSONObject> testDataList = apiPerfTestDataService.getTestData(apiPerfStepId);

        //环境变量
        JSONObject variable = variableService.getVariable(apiPerfStepId);

        if(testDataList!=null&&testDataList.size()>0){
            //  name,age    //属性
            //  name1,18    //获取当前行的值
            JSONObject testData = testDataList.get(0);
            variable.putAll(testData);
        }

        return variable;
    }


    @Override
    public void cleanUpData(String apiPerfId){

    }

    @Override
    public void getAgentTestData(JSONObject message) {
        final Future<?>[] future = new Future<?>[1];

        future[0] = executorService.submit(() -> {
            try {
                ApiPerfTestResponse response = message.getJSONObject("apiPerfTestResponse").toJavaObject(ApiPerfTestResponse.class);
                String caseId = message.getString("caseId");
                perfTestResponseMap.put(caseId,response);
                if (!MagicValue.TEST_STATUS_START.equals(message.getString("status"))) {
                    // 取消任务
                    future[0].cancel(true);
                }
            } catch (Exception e) {
                logger.error("apiPerfTestResponse---getAgentTestData: " + e.getMessage());
                future[0].cancel(true);
            }
        });
    }


}
