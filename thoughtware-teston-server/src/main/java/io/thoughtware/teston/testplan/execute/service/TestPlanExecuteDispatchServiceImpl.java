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
import io.thoughtware.teston.testplan.instance.service.TestPlanCaseInstanceBindService;
import io.thoughtware.teston.testplan.instance.service.TestPlanInstanceService;
import io.thoughtware.teston.testplan.cases.model.TestPlanCaseQuery;
import io.thoughtware.teston.testplan.cases.service.TestPlanCaseService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
    TestUtil testUtil;

    @Autowired
    TestPlanInstanceService testPlanInstanceService;

    @Autowired
    TestPlanService testPlanService;

    @Autowired
    InstanceService instanceService;

    @Autowired
    TestPlanCaseInstanceBindService testPlanCaseInstanceBindService;


    //任务线程池
    public final ExecutorService executorService = Executors.newCachedThreadPool();

    //测试计划id: 可执行用例
    public static final Map<String, List<PlanCase>> testPlanIdOrPlanCaseList = new ConcurrentHashMap<>();
    //测试计划id: 测试计划实例Id
    public static final Map<String, String> testPlanIdOrPlanInstanceId = new ConcurrentHashMap<>();
    //测试计划实例id: 测试计划实例列表
    public static final Map<String, ArrayList<TestPlanCaseInstanceBind>> planInstanceIdOrPlanCaseInstanceList = new ConcurrentHashMap<>();


    @Override
    public String execute(TestPlanTestData testPlanTestData) {
        String testPlanId = testPlanTestData.getTestPlanId();
        String repositoryId = testPlanTestData.getRepositoryId();

        if(!isExistExecute(testPlanId)){
            //查询出当前计划的关联的，可执行用例
            List<PlanCase> executableCaseList = findExecutableCaseList(testPlanId);
            testPlanIdOrPlanCaseList.put(testPlanId,executableCaseList);

            //执行的时候先创建一个初始历史，用于获取Id
            String testPlanInstanceId = createPlanInstance(repositoryId,testPlanId,executableCaseList);
            testPlanIdOrPlanInstanceId.put(testPlanId,testPlanInstanceId);

            ArrayList<TestPlanCaseInstanceBind> planCaseInstanceList = createPlanCaseInstanceList(executableCaseList, testPlanInstanceId);
            planInstanceIdOrPlanCaseInstanceList.put(testPlanInstanceId,planCaseInstanceList);

            if(CollectionUtils.isNotEmpty(executableCaseList)){
                //并发执行
                executorService.submit(() -> {
                    try{
                        executeTestPlanCases(testPlanTestData,planCaseInstanceList);
                    }catch (Exception e) {
                        updatePlanStatus(0,testPlanId);
                        updateStatus(testPlanInstanceId,MagicValue.TEST_STATUS_FAIL);
                        throw new ApplicationException(e);
                    }finally {
                        try {
                            Thread.sleep(3000);
                        }catch (Exception e){
                            throw new ApplicationException(e);
                        }finally {
//                            cleanUpExecutionData(testPlanId);
                        }
                    }
                });
            }

            return testPlanInstanceId;
        }

        return null;
    }


    /**
     * 是否存在执行
     */
    private boolean isExistExecute(String testPlanId){
        return testPlanIdOrPlanCaseList.containsKey(testPlanId);
    }

    /**
     *  查询出当前计划的关联的，可执行的自动化用例
     */
    private List<PlanCase> findExecutableCaseList(String testPlanId){
        TestPlanCaseQuery testPlanCaseQuery = new TestPlanCaseQuery();
        testPlanCaseQuery.setTestPlanId(testPlanId);
        List<PlanCase> planCaseList = testPlanCaseService.findPlanCaseList(testPlanCaseQuery);
        return planCaseList;
    }

    /***
     * 执行用例
     * @param testPlanTestData
     * @param planCaseInstanceList
     */
    @Override
    public void executeTestPlanCases(TestPlanTestData testPlanTestData, ArrayList<TestPlanCaseInstanceBind> planCaseInstanceList){
        //循环 执行用例
        for(TestPlanCaseInstanceBind testPlanCaseInstanceBind : planCaseInstanceList){
            String caseType = testPlanCaseInstanceBind.getCaseType();

            switch (caseType) {
                case MagicValue.CASE_TYPE_API_UNIT -> {
                     testPlanExecuteApiDispatch.exeApiUnit(testPlanCaseInstanceBind, testPlanTestData);
                     break;
                }
                case MagicValue.CASE_TYPE_API_SCENE -> {
                    testPlanExecuteApiDispatch.exeApiScene(testPlanCaseInstanceBind, testPlanTestData);
                    break;
                }
                default -> {
                }
            }
        }
    }



    /**
     * 创建测试计划实例
     * @param executableCaseList
     * @param repositoryId
     * @param executableCaseList
     */
    private String createPlanInstance(String repositoryId, String testPlanId, List<PlanCase> executableCaseList){
        TestPlanInstance testPlanInstance = new TestPlanInstance();
        TestPlan testPlan1 = new TestPlan();
        testPlan1.setId(testPlanId);
        testPlanInstance.setTestPlan(testPlan1);
        testPlanInstance.setRepositoryId(repositoryId);
        int planCaseNum = testPlanCaseService.findPlanCaseNum(testPlanId);
        testPlanInstance.setTotal(planCaseNum);
        int executableCaseNum = (executableCaseList!=null)?executableCaseList.size():0;
        testPlanInstance.setExecutableCaseNum(executableCaseNum);
        testPlanInstance.setPassNum(0);
        testPlanInstance.setPassRate("0.00%");
        testPlanInstance.setFailNum(0);
        testPlanInstance.setErrorRate("0.00%");
        String planInstanceId = testPlanInstanceService.createTestPlanInstance(testPlanInstance);

        // 创建公共实例
        Instance instance = new Instance();
        instance.setId(planInstanceId);
        instance.setBelongId(testPlanId);
        instance.setRepositoryId(repositoryId);
        instance.setType(MagicValue.TEST_PLAN);
        TestPlan testPlan = testPlanService.findTestPlan(testPlanId);
        instance.setName(testPlan.getName());

        //获取当前执行次数
        int executeNum = instanceService.getRecentExecuteNum(testPlanId);
        instance.setExecuteNumber(executeNum);

        //设置状态,开始执行
        instance.setStatus(MagicValue.TEST_STATUS_START);

        JSONObject instanceMap = new JSONObject();
        instanceMap.put("total",planCaseNum);
        instanceMap.put("executableCaseNum",executableCaseNum);
        instanceMap.put("passNum",testPlanInstance.getPassNum().toString());
        instanceMap.put("passRate",testPlanInstance.getPassRate());
        instanceMap.put("failNum",testPlanInstance.getFailNum().toString());
        instanceMap.put("errorRate",testPlanInstance.getErrorRate());
        instance.setContent(instanceMap.toString());
        instanceService.createInstance(instance);

        return planInstanceId;
    }

    private ArrayList<TestPlanCaseInstanceBind> createPlanCaseInstanceList( List<PlanCase> executableCaseList,String testPlanInstanceId){
        ArrayList<TestPlanCaseInstanceBind> arrayList = new ArrayList<>();
        for(PlanCase testPlanCase : executableCaseList){
            TestPlanCaseInstanceBind testPlanCaseInstanceBind = new TestPlanCaseInstanceBind();
            testPlanCaseInstanceBind.setTestPlanInstanceId(testPlanInstanceId);
            testPlanCaseInstanceBind.setCaseId(testPlanCase.getId());
            testPlanCaseInstanceBind.setName(testPlanCase.getName());
            testPlanCaseInstanceBind.setCaseType(testPlanCase.getCaseType());
            testPlanCaseInstanceBind.setTestType(testPlanCase.getTestType());
            testPlanCaseInstanceBind.setStatus(2);
            String testPlanCaseInstanceBindId = testPlanCaseInstanceBindService.createTestPlanCaseInstanceBind(testPlanCaseInstanceBind);
            testPlanCaseInstanceBind.setId(testPlanCaseInstanceBindId);
            arrayList.add(testPlanCaseInstanceBind);
        }

        return arrayList;
    }

    @Override
    public TestPlanTestResponse exeResult(String testPlanId) {
        TestPlanTestResponse testPlanTestResponse = new TestPlanTestResponse();

        List<PlanCase> executableCaseList = testPlanIdOrPlanCaseList.get(testPlanId);
        String testPlanInstanceId = testPlanIdOrPlanInstanceId.get(testPlanId);

        ArrayList<TestPlanCaseInstanceBind> testPlanCaseInstanceList = new ArrayList<>();
        if(testPlanInstanceId != null){
             testPlanCaseInstanceList = planInstanceIdOrPlanCaseInstanceList.get(testPlanInstanceId);
        }

        if (CollectionUtils.isNotEmpty(testPlanCaseInstanceList)) {
            getPlanCaseInstance(testPlanCaseInstanceList);
            testPlanTestResponse = processResultData(executableCaseList, testPlanCaseInstanceList,  testPlanId);
        }

        return testPlanTestResponse;
    }

    @Override
    public void getPlanCaseInstance(ArrayList<TestPlanCaseInstanceBind> testPlanCaseInstanceList){
        for (TestPlanCaseInstanceBind testPlanCaseInstanceBind : testPlanCaseInstanceList) {
            String caseType = testPlanCaseInstanceBind.getCaseType();
            switch (caseType) {
                default:
                    break;
            }
        }
    }

    /**
     * 处理测试结果
     * @param executableCaseList
     * @param testPlanCaseInstanceList
     * @param testPlanId
     * @return
     */
    private TestPlanTestResponse processResultData(List<PlanCase> executableCaseList,
                                                   ArrayList<TestPlanCaseInstanceBind> testPlanCaseInstanceList,
                                                   String testPlanId){

        TestPlanTestResponse testPlanTestResponse = new TestPlanTestResponse();

        try {
            //处理数据
            int executableCaseCount = executableCaseList.size();

            TestPlanInstance testPlanInstance = processInstance(testPlanCaseInstanceList,executableCaseCount);
            testPlanTestResponse.setTestPlanInstance(testPlanInstance);
            testPlanTestResponse.setTestPlanCaseInstanceList(testPlanCaseInstanceList);

            // 确定测试计划的状态
            int status = getPlanStatus(testPlanCaseInstanceList, testPlanId);
            testPlanTestResponse.setStatus(status);

            //更新历史
            updatePlanInstance(testPlanInstance,testPlanId);
        }catch (Exception e){
            logger.info("process ---------- {}",e);
            throw new ApplicationException(e.getMessage());
        }

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

        // 计算正在执行的任务数量
        long executingCount = testPlanCaseInstanceList.stream()
                .filter(testPlanCaseInstanceBind -> Objects.equals(testPlanCaseInstanceBind.getStatus(), 1))
                .count();

        // 计算总时间
        int totalElapsedTime = testPlanCaseInstanceList.stream()
                .mapToInt(TestPlanCaseInstanceBind::getElapsedTime)
                .sum();
        testPlanInstance.setElapsedTime(totalElapsedTime);

        if (executingCount == 0) {
            // 如果没有任务正在执行
            if (Objects.equals(total, passNum)) {
                testPlanInstance.setResult(1); // 全部通过
            } else {
                testPlanInstance.setResult(0); // 有失败
                testPlanInstance.setStatus(0);// 执行完毕
            }
        } else {
            // 有任务正在执行
            testPlanInstance.setResult(2); // 未执行
            testPlanInstance.setStatus(1);//进行中
        }
        return testPlanInstance;
    }

    /**
     * 获取测试计划状态
     * @param testPlanId
     * @return
     */
    private int getPlanStatus(ArrayList<TestPlanCaseInstanceBind> testPlanCaseInstanceList,
                              String testPlanId) {
        ArrayList<Integer> resultList = new ArrayList<>();
        for(TestPlanCaseInstanceBind testPlanCaseInstanceBind:testPlanCaseInstanceList){
            resultList.add(testPlanCaseInstanceBind.getStatus());
        }

        String testPlanInstanceId = testPlanIdOrPlanInstanceId.get(testPlanId);
        if (resultList.contains(1)||resultList.contains(2)) {
            // 继续执行
            return 1;
        } else {
            // 执行结束
            updatePlanStatus(0,testPlanId);
            updateStatus(testPlanInstanceId,MagicValue.TEST_STATUS_SUCCESS);
            return 0;
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
        instanceMap.put("elapsedTime",testPlanInstance.getElapsedTime());
        instance.setContent(instanceMap.toString());

        instanceService.updateInstance(instance);
    }


    /**
     * 更新测试计划历史中的执行状态 0 未执行， 1 执行中
     * @param status
     */
    private void updatePlanStatus(int status,String testPlanId){
        String testPlanInstanceId = testPlanIdOrPlanInstanceId.get(testPlanId);
        TestPlanInstance testPlanInstance = testPlanInstanceService.findTestPlanInstance(testPlanInstanceId);
        testPlanInstance.setStatus(status);
        testPlanInstanceService.updateTestPlanInstance(testPlanInstance);;
    }

    /**
     * 状态更改
     */
    private void updateStatus(String instanceId,String status){
        Instance instance = instanceService.findInstance(instanceId);
        instance.setStatus(status);
        instanceService.updateInstance(instance);
    }

    /**
     * 清楚数据
     * @param testPlanId
     */
    @Override
    public void cleanUpExecutionData(String testPlanId) {
        String testPlanInstanceId = testPlanIdOrPlanInstanceId.get(testPlanId);

        planInstanceIdOrPlanCaseInstanceList.remove(testPlanInstanceId);
        testPlanIdOrPlanCaseList.remove(testPlanId);
        testPlanIdOrPlanInstanceId.remove(testPlanId);
    }


}




