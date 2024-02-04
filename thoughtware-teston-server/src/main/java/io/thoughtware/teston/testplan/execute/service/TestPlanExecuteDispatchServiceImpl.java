package io.thoughtware.teston.testplan.execute.service;

import com.alibaba.fastjson.JSONObject;
import io.thoughtware.core.exception.ApplicationException;
import io.thoughtware.rpc.annotation.Exporter;
import io.thoughtware.teston.common.MagicValue;
import io.thoughtware.teston.common.TestUtil;
import io.thoughtware.teston.instance.model.Instance;
import io.thoughtware.teston.instance.service.InstanceService;
import io.thoughtware.teston.testplan.cases.model.PlanCase;
import io.thoughtware.teston.testplan.cases.model.TestPlan;
import io.thoughtware.teston.testplan.cases.service.TestPlanService;
import io.thoughtware.teston.testplan.execute.model.TestPlanTestData;
import io.thoughtware.teston.testplan.execute.model.TestPlanTestResponse;
import io.thoughtware.teston.testplan.instance.model.TestPlanCaseInstanceBind;
import io.thoughtware.teston.testplan.instance.model.TestPlanInstance;
import io.thoughtware.teston.testplan.instance.service.TestPlanInstanceService;
import io.thoughtware.teston.testplan.cases.model.TestPlanCaseQuery;
import io.thoughtware.teston.testplan.cases.service.TestPlanCaseService;
import io.thoughtware.eam.common.context.LoginContext;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 测试计划测试调度
 */
@Service
@Exporter
public class TestPlanExecuteDispatchServiceImpl implements TestPlanExecuteDispatchService {

    static Logger logger = LoggerFactory.getLogger(TestPlanExecuteDispatchServiceImpl.class);

    @Autowired
    TestPlanCaseService testPlanCaseService;

    @Autowired
    TestPlanExecuteApiDispatch testPlanExecuteApiDispatch;

    @Autowired
    TestPlanExecuteWebDispatch testPlanExecuteWebDispatch;

    @Autowired
    TestPlanExecuteAppDispatch testPlanExecuteAppDispatch;

    @Autowired
    TestUtil testUtil;

    @Autowired
    TestPlanInstanceService testPlanInstanceService;

    @Autowired
    TestPlanService testPlanService;

    @Autowired
    InstanceService instanceService;


    //任务线程池
    public final ExecutorService executorService = Executors.newCachedThreadPool();

    //测试计划id: 可执行用例
    public static final Map<String, List<PlanCase>> testPlanIdOrPlanCaseList = new ConcurrentHashMap<>();
    //测试计划id: 测试计划实例Id
    public static final Map<String, String> testPlanIdOrPlanInstanceId = new ConcurrentHashMap<>();
    //测试计划实例id: 测试计划实例列表
    public static final Map<String, ArrayList<TestPlanCaseInstanceBind>> planInstanceIdOrPlanCaseInstanceList = new ConcurrentHashMap<>();


    @Override
    public void execute(TestPlanTestData testPlanTestData) {
        String testPlanId = testPlanTestData.getTestPlanId();
        String repositoryId = testPlanTestData.getRepositoryId();

        if(!isExistExecute(testPlanId)){
            //查询出当前计划的关联的，可执行用例
            List<PlanCase> executableCaseList = findExecutableCaseList(testPlanId);
            testPlanIdOrPlanCaseList.put(testPlanId,executableCaseList);

            //执行的时候先创建一个初始历史，用于获取Id
            String testPlanInstanceId = createPlanInstance(repositoryId,testPlanId,executableCaseList.size());
            testPlanIdOrPlanInstanceId.put(testPlanId,testPlanInstanceId);

            if(CollectionUtils.isNotEmpty(executableCaseList)){
                //并发执行
                executorService.submit(() -> {
                    try{
                        executeTestPlanCases(testPlanTestData,testPlanInstanceId,executableCaseList);
                    }catch (Exception e) {
                        updateStatus(0,testPlanId);

                        throw new ApplicationException(e);
                    }
                });
            }
        }
    }


    /**
     * 是否存在执行
     */
    private boolean isExistExecute(String testPlanId){
        return testPlanIdOrPlanCaseList.containsKey(testPlanId);
    }

    /**
     *  查询出当前计划的关联的，可执行用例
     */
    private List<PlanCase> findExecutableCaseList(String testPlanId){
        TestPlanCaseQuery testPlanCaseQuery = new TestPlanCaseQuery();
        testPlanCaseQuery.setTestPlanId(testPlanId);
        String[] caseTypeList = {
                MagicValue.CASE_TYPE_API_UNIT,
                MagicValue.CASE_TYPE_API_SCENE,
                MagicValue.CASE_TYPE_API_PERFORM,
                MagicValue.CASE_TYPE_WEB,
//                MagicValue.CASE_TYPE_APP
        };
        testPlanCaseQuery.setCaseTypeList(caseTypeList);
        List<PlanCase> planCaseList = testPlanCaseService.findPlanCaseList(testPlanCaseQuery);
        return planCaseList;
    }

    /***
     * 执行用例
     * @param testPlanTestData
     * @param testPlanInstanceId
     * @param planCaseList
     */
    private void executeTestPlanCases(TestPlanTestData testPlanTestData,String testPlanInstanceId,List<PlanCase> planCaseList){
        ArrayList<TestPlanCaseInstanceBind> testPlanCaseInstanceList = new ArrayList<>();
        //循环 执行用例
        for(PlanCase testPlanCase : planCaseList){
            String caseType = testPlanCase.getCaseType();

            switch (caseType) {
                case MagicValue.CASE_TYPE_API_UNIT -> {
                    TestPlanCaseInstanceBind testPlanCaseInstanceBind = testPlanExecuteApiDispatch.exeApiUnit(testPlanCase, testPlanTestData, testPlanInstanceId);
                    testPlanCaseInstanceList.add(testPlanCaseInstanceBind);
                    planInstanceIdOrPlanCaseInstanceList.put(testPlanInstanceId, testPlanCaseInstanceList);
                    break;
                }
                case MagicValue.CASE_TYPE_API_SCENE -> {
                    TestPlanCaseInstanceBind apiSceneInstance = testPlanExecuteApiDispatch.exeApiScene(testPlanCase, testPlanTestData, testPlanInstanceId);
                    testPlanCaseInstanceList.add(apiSceneInstance);
                    planInstanceIdOrPlanCaseInstanceList.put(testPlanInstanceId, testPlanCaseInstanceList);
                    break;
                }
                case MagicValue.CASE_TYPE_API_PERFORM -> {
                    testPlanExecuteApiDispatch.exeApiPerform(testPlanCase, testPlanTestData);
                    break;
                }
                case MagicValue.CASE_TYPE_WEB -> {
                    testPlanExecuteWebDispatch.exeWebScene(testPlanCase, testPlanTestData);
                    break;
                }
                    case "app-scene" -> {
                        TestPlanCaseInstanceBind appSceneInstance = testPlanExecuteAppDispatch.exeAppScene(testPlanCase, testPlanTestData, testPlanInstanceId);
                        testPlanCaseInstanceList.add(appSceneInstance);
                        break;
                    }
                default -> {
                }
            }
        }
    }

    /**
     * 创建测试计划实例
     * @param repositoryId
     * @param size
     */
    private String createPlanInstance(String repositoryId, String testPlanId, int size){
        TestPlanInstance testPlanInstance = new TestPlanInstance();
        TestPlan testPlan1 = new TestPlan();
        testPlan1.setId(testPlanId);
        testPlanInstance.setTestPlan(testPlan1);
        testPlanInstance.setRepositoryId(repositoryId);
        testPlanInstance.setCreateTime(new Timestamp(System.currentTimeMillis()));
        testPlanInstance.setCreateUser(LoginContext.getLoginId());
        int planCaseNum = testPlanCaseService.findPlanCaseNum(testPlanId);
        testPlanInstance.setTotal(planCaseNum);
        testPlanInstance.setExecutableCaseNum(size);
        testPlanInstance.setPassNum(0);
        testPlanInstance.setPassRate("0.00%");
        testPlanInstance.setFailNum(0);
        testPlanInstance.setErrorRate("0.00%");
        testPlanInstance.setResult(2);
        testPlanInstance.setStatus(1);
        String planInstanceId = testPlanInstanceService.createTestPlanInstance(testPlanInstance);


        // 创建公共实例
        Instance instance = new Instance();
        instance.setId(planInstanceId);

        instance.setBelongId(testPlanId);
        instance.setType(MagicValue.TEST_PLAN);

        TestPlan testPlan = testPlanService.findTestPlan(testPlanId);
        instance.setName(testPlan.getName());
        instance.setRepositoryId(testPlan.getRepository().getId());

        //获取当前执行次数
        int executeNum = instanceService.getRecentExecuteNum(testPlanId);
        instance.setExecuteNumber(executeNum);

        JSONObject instanceMap = new JSONObject();
        instanceMap.put("result",testPlanInstance.getResult().toString());
        instanceMap.put("total",planCaseNum);
        instanceMap.put("executableCaseNum",testPlanInstance.getTotal().toString());
        instanceMap.put("passNum",testPlanInstance.getPassNum().toString());
        instanceMap.put("passRate",testPlanInstance.getPassRate());
        instanceMap.put("failNum",testPlanInstance.getFailNum().toString());
        instanceMap.put("errorRate",testPlanInstance.getErrorRate());
        instance.setContent(instanceMap.toString());

        instanceService.createInstance(instance);

        return planInstanceId;
    }


    @Override
    public TestPlanTestResponse exeResult(String testPlanId) {

        List<PlanCase> executableCaseList = testPlanIdOrPlanCaseList.get(testPlanId);
        String testPlanInstanceId = testPlanIdOrPlanInstanceId.get(testPlanId);
        ArrayList<TestPlanCaseInstanceBind> testPlanCaseInstanceList = planInstanceIdOrPlanCaseInstanceList.get(testPlanInstanceId);

        // 如果testPlanCaseInstanceList为null，则初始化
        if (testPlanCaseInstanceList == null) {
            testPlanCaseInstanceList = new ArrayList<>();
        }

        ArrayList<Integer> resultList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(executableCaseList)) {
            for (PlanCase testPlanCase : executableCaseList) {
                processCase(testPlanCase, testPlanInstanceId, testPlanCaseInstanceList, resultList);
            }
        }

        TestPlanTestResponse testPlanTestResponse = processResultData(executableCaseList, testPlanCaseInstanceList, resultList, testPlanId);

        return testPlanTestResponse;
    }

    /**
     * 根据caseType处理测试用例
     */
    private void processCase(PlanCase testPlanCase, String testPlanInstanceId,
                             ArrayList<TestPlanCaseInstanceBind> testPlanCaseInstanceList,
                             ArrayList<Integer> resultList) {
        String caseType = testPlanCase.getCaseType();
        TestPlanCaseInstanceBind caseInstance;
        Integer status;

        switch (caseType) {
            case MagicValue.CASE_TYPE_API_PERFORM:
                caseInstance = testPlanExecuteApiDispatch.apiPerfResult(testPlanCase, testPlanInstanceId);
                break;
            case MagicValue.CASE_TYPE_WEB:
                caseInstance = testPlanExecuteWebDispatch.webSceneResult(testPlanCase, testPlanInstanceId);
                break;
            default:
                return; // 如果caseType不匹配，则直接返回
        }

        status = caseInstance.getStatus();
        // 通用逻辑，根据状态处理testPlanCaseInstanceList和resultList
        if (status != null &&status == 1) {
            testPlanCaseInstanceList.removeIf(instance -> Objects.equals(instance.getCaseId(), caseInstance.getCaseId()));
        }
        testPlanCaseInstanceList.add(caseInstance);
        resultList.add(status);
    }



    /**
     * 处理测试结果
     * @param executableCaseList
     * @param testPlanCaseInstanceList
     * @param resultList
     * @param testPlanId
     * @return
     */
    private TestPlanTestResponse processResultData(List<PlanCase> executableCaseList,
                                                   ArrayList<TestPlanCaseInstanceBind> testPlanCaseInstanceList,
                                                   ArrayList<Integer> resultList,
                                                   String testPlanId){

        TestPlanTestResponse testPlanTestResponse = new TestPlanTestResponse();

        //处理数据
        int executableCaseCount = executableCaseList.size();
        int executableCaseInstanceCount = testPlanCaseInstanceList.size();

        TestPlanInstance testPlanInstance = processInstance(testPlanCaseInstanceList,executableCaseCount);
        testPlanTestResponse.setTestPlanInstance(testPlanInstance);
        testPlanTestResponse.setTestPlanCaseInstanceList(testPlanCaseInstanceList);

        // 确定测试计划的状态
        int status = getPlanStatus(executableCaseInstanceCount, executableCaseCount, resultList, testPlanId);
        testPlanTestResponse.setStatus(status);

        //更新历史
        updatePlanInstance(testPlanInstance,testPlanId);

        return testPlanTestResponse;
    }


    /**
     * 处理结果
     * @param testPlanCaseInstanceList
     * @param executableCaseCount 可执行的用例总数
     * @return
     */
    private TestPlanInstance processInstance(ArrayList<TestPlanCaseInstanceBind> testPlanCaseInstanceList, int executableCaseCount){

        TestPlanInstance testPlanInstance = new TestPlanInstance();

        int total = executableCaseCount;
        testPlanInstance.setTotal(total);

        int passNum = (int) testPlanCaseInstanceList.stream()
                .filter(testPlanCaseInstance -> Objects.equals(testPlanCaseInstance.getResult(), 1))
                .count();

        String passRate= testUtil.processRate(passNum, total);
        testPlanInstance.setPassNum(passNum);
        testPlanInstance.setPassRate(passRate);
        testPlanInstance.setFailNum(total-passNum);
        String errorRate = testUtil.processRate(total - passNum, total);
        testPlanInstance.setErrorRate(errorRate);


        if(Objects.equals(total,passNum)){
            testPlanInstance.setResult(1);
        }else {
            testPlanInstance.setResult(0);
        }

        return testPlanInstance;
    }

    /**
     * 获取测试计划状态
     * @param executableCaseInstanceCount
     * @param executableCaseCount
     * @param resultList
     * @param testPlanId
     * @return
     */
    private int getPlanStatus(int executableCaseInstanceCount, int executableCaseCount, List<Integer> resultList, String testPlanId) {
        if (executableCaseCount == executableCaseInstanceCount) {
            boolean isContinuing = resultList.contains(1);
            if (isContinuing) {
                // 继续执行
                return 1;
            } else {
                // 执行结束
                updateStatus(0, testPlanId);

                return 0;
            }
        } else {
            // 继续执行
            return 1;
        }
    }


    /**
     * 更新历史到数据库
     * @param testPlanInstance
     * @param testPlanId
     */
    private void updatePlanInstance(TestPlanInstance testPlanInstance, String testPlanId) {
        String testPlanInstanceId = testPlanIdOrPlanInstanceId.get(testPlanId);

        TestPlan testPlan = new TestPlan();
        testPlan.setId(testPlanId);
        testPlanInstance.setTestPlan(testPlan);
        testPlanInstance.setId(testPlanInstanceId);
        testPlanInstanceService.updateTestPlanInstance(testPlanInstance);

        Instance instance = instanceService.findInstance(testPlanInstanceId);
        int planCaseNum = testPlanCaseService.findPlanCaseNum(testPlanId);

        JSONObject instanceMap = new JSONObject();
        instanceMap.put("result",testPlanInstance.getResult().toString());
        instanceMap.put("total",planCaseNum);
        instanceMap.put("executableCaseNum",testPlanInstance.getTotal().toString());
        instanceMap.put("passNum",testPlanInstance.getPassNum().toString());
        instanceMap.put("passRate",testPlanInstance.getPassRate());
        instanceMap.put("failNum",testPlanInstance.getFailNum().toString());
        instanceMap.put("errorRate",testPlanInstance.getErrorRate());
        instance.setContent(instanceMap.toString());

        instanceService.updateInstance(instance);
    }


    /**
     * 更新测试计划历史中的执行状态 0 未执行， 1 执行中
     * @param status
     */
    private void updateStatus(int status,String testPlanId){
        String testPlanInstanceId = testPlanIdOrPlanInstanceId.get(testPlanId);
        TestPlanInstance testPlanInstance = testPlanInstanceService.findTestPlanInstance(testPlanInstanceId);
        testPlanInstance.setStatus(status);
        testPlanInstanceService.updateTestPlanInstance(testPlanInstance);;
    }


    /**
     * 清楚数据
     * @param testPlanId
     */
    @Override
    public void cleanUpExecutionData(String testPlanId) {
        testPlanIdOrPlanCaseList.remove(testPlanId);
        testPlanIdOrPlanInstanceId.remove(testPlanId);
        planInstanceIdOrPlanCaseInstanceList.remove(testPlanId);
    }


}




