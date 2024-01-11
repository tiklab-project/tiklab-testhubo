package io.thoughtware.teston.testplan.execute.service;

import com.alibaba.fastjson.JSONObject;
import io.thoughtware.rpc.annotation.Exporter;
import io.thoughtware.teston.common.MagicValue;
import io.thoughtware.teston.common.TestUtil;
import io.thoughtware.teston.instance.model.Instance;
import io.thoughtware.teston.instance.model.InstanceQuery;
import io.thoughtware.teston.instance.service.InstanceService;
import io.thoughtware.teston.testplan.cases.model.PlanCase;
import io.thoughtware.teston.testplan.cases.model.TestPlan;
import io.thoughtware.teston.testplan.cases.service.TestPlanService;
import io.thoughtware.teston.testplan.execute.model.TestPlanTestData;
import io.thoughtware.teston.testplan.execute.model.TestPlanTestResponse;
import io.thoughtware.teston.testplan.instance.model.TestPlanCaseInstanceBind;
import io.thoughtware.teston.testplan.instance.model.TestPlanInstance;
import io.thoughtware.teston.testplan.instance.service.TestPlanInstanceService;
import io.thoughtware.teston.testplan.cases.model.TestPlanCase;
import io.thoughtware.teston.testplan.cases.model.TestPlanCaseQuery;
import io.thoughtware.teston.testplan.cases.service.TestPlanCaseService;
import io.thoughtware.eam.common.context.LoginContext;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

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

    /**
     * 可执行的用例
     */
    private List<PlanCase> planCaseList;

    /**
     * 测试计划执行实例
     */
    private ArrayList<TestPlanCaseInstanceBind> testPlanCaseInstanceList = new ArrayList<>();

    private String testPlanId;

    /**
     * 需要执行的用例总数
     */
    private Integer exeCount=0;

    /**
     * 执行时设置一个测试计划历史Id
     */
    public String testPlanInstanceId;

    /**
     * 执行的状态：0：未执行，1：正在进行
     */
    private Integer status=0;


    @Override
    public void execute(TestPlanTestData testPlanTestData) {
        //如果之前执行过，先清空之前保留数据
        exeCount=0;
        testPlanCaseInstanceList.clear();

        //执行把状态变为 1 正在进行
        status=1;

        testPlanId = testPlanTestData.getTestPlanId();
        String repositoryId = testPlanTestData.getRepositoryId();


        //查询出当前计划的关联的可执行用例
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
        planCaseList = testPlanCaseService.findPlanCaseList(testPlanCaseQuery);

        //执行的时候先创建一个历史，里面没有数据，用于获取Id
        testPlanInstanceId = createPlanInstance(repositoryId);

        if(CollectionUtils.isNotEmpty(planCaseList)){
            //首先获取能执行的用例总数
            exeCount=planCaseList.size();

            //循环 执行用例
            for(PlanCase testPlanCase : planCaseList){
                String caseType = testPlanCase.getCaseType();

                switch (caseType) {
                    case MagicValue.CASE_TYPE_API_UNIT -> {
                        TestPlanCaseInstanceBind testPlanCaseInstanceBind = testPlanExecuteApiDispatch.exeApiUnit(testPlanCase, testPlanTestData, testPlanInstanceId);
                        testPlanCaseInstanceList.add(testPlanCaseInstanceBind);
                        break;
                    }
                    case MagicValue.CASE_TYPE_API_SCENE -> {
                        TestPlanCaseInstanceBind apiSceneInstance = testPlanExecuteApiDispatch.exeApiScene(testPlanCase, testPlanTestData, testPlanInstanceId);
                        testPlanCaseInstanceList.add(apiSceneInstance);
                        break;
                    }
                    case MagicValue.CASE_TYPE_API_PERFORM -> {
                        testPlanExecuteApiDispatch.exeApiPerform(testPlanCase, testPlanTestData, testPlanInstanceId);
                        break;
                    }
                    case MagicValue.CASE_TYPE_WEB -> {
                        testPlanExecuteWebDispatch.exeWebScene(testPlanCase, testPlanTestData, testPlanInstanceId);
                        break;
                    }
//                    case "app-scene" -> {
//                        TestPlanCaseInstanceBind appSceneInstance = testPlanExecuteAppDispatch.exeAppScene(testPlanCase, testPlanTestData, testPlanInstanceId);
//                        testPlanCaseInstanceList.add(appSceneInstance);
//                        break;
//                    }
                    default -> {
                    }
                }


            }
        }
    }


    @Override
    public TestPlanTestResponse exeResult() {

        ArrayList<Integer> resultList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(planCaseList)){
            //循环
            for(PlanCase testPlanCase : planCaseList){
                String caseType = testPlanCase.getCaseType();

                switch (caseType) {
                    case MagicValue.CASE_TYPE_API_PERFORM:
                        if(testPlanExecuteApiDispatch.apiPerfStatus==1){
                            TestPlanCaseInstanceBind apiPerfInstance = testPlanExecuteApiDispatch.apiPerfResult();
                            testPlanCaseInstanceList.removeIf(instance -> Objects.equals(instance.getCaseId(), apiPerfInstance.getCaseId()));
                            testPlanCaseInstanceList.add(apiPerfInstance);
                        }
                        resultList.add(testPlanExecuteApiDispatch.apiPerfStatus);
                        break;
                    case MagicValue.CASE_TYPE_WEB:
                        if(testPlanExecuteWebDispatch.webSceneStatus==1){
                            TestPlanCaseInstanceBind webSceneResult = testPlanExecuteWebDispatch.webSceneResult();
                            // 前一次执行的有，就删了，添加新的返回结果
                            testPlanCaseInstanceList.removeIf(instance -> Objects.equals(instance.getCaseId(), webSceneResult.getCaseId()));
                            testPlanCaseInstanceList.add(webSceneResult);
                        }
                        resultList.add(testPlanExecuteWebDispatch.webSceneStatus);
                        break;

                    default:
                        break;
                }
            }
        }


        TestPlanTestResponse testPlanTestResponse = new TestPlanTestResponse();
        //处理数据
        TestPlanInstance testPlanInstance = processInstance(testPlanCaseInstanceList);
        testPlanTestResponse.setTestPlanInstance(testPlanInstance);
        testPlanTestResponse.setTestPlanCaseInstanceList(testPlanCaseInstanceList);

        //如果用例个数 和 执行的总个数一样，证明执行了所有
        if(Objects.equals(testPlanCaseInstanceList.size(),exeCount)){
            if(resultList.contains(1)){
                status=1;
            }else {
                //执行结束
                status=0;

            }

            testPlanTestResponse.setStatus(status);
        }else {
            status=1;
            testPlanTestResponse.setStatus(status);
        }

        //更新历史
        saveToSql(testPlanInstance);


        return testPlanTestResponse;
    }


    /**
     * 处理结果
     * @param testPlanCaseInstanceList
     * @return
     */
    private TestPlanInstance processInstance(ArrayList<TestPlanCaseInstanceBind> testPlanCaseInstanceList){

        TestPlanInstance testPlanInstance = new TestPlanInstance();

        int total = exeCount;
        testPlanInstance.setTotal(total);

        int passNum = 0;
        for(TestPlanCaseInstanceBind testPlanCaseInstance:testPlanCaseInstanceList){
            if(Objects.equals(testPlanCaseInstance.getResult(),1)){
                passNum++;
            }
        }

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
     * 更新历史到数据库
     * @param testPlanInstance
     */
    private void saveToSql(TestPlanInstance testPlanInstance) {

        TestPlan testPlan = new TestPlan();
        testPlan.setId(testPlanId);
        testPlanInstance.setTestPlan(testPlan);
        testPlanInstance.setId(testPlanInstanceId);
        testPlanInstanceService.updateTestPlanInstance(testPlanInstance);


        Instance instance = instanceService.findInstance(testPlanInstanceId);

        JSONObject instanceMap = new JSONObject();
        instanceMap.put("result",testPlanInstance.getResult().toString());
        instanceMap.put("total",testPlanInstance.getTotal().toString());
        instanceMap.put("passNum",testPlanInstance.getPassNum().toString());
        instanceMap.put("passRate",testPlanInstance.getPassRate());
        instanceMap.put("failNum",testPlanInstance.getFailNum().toString());
        instanceMap.put("errorRate",testPlanInstance.getErrorRate());
        instance.setContent(instanceMap.toString());

        instanceService.updateInstance(instance);
    }

    /**
     * 创建测试计划实例
     * @param repositoryId
     */
    private String createPlanInstance(String repositoryId){
        TestPlanInstance testPlanInstance = new TestPlanInstance();
        TestPlan testPlan1 = new TestPlan();
        testPlan1.setId(testPlanId);
        testPlanInstance.setTestPlan(testPlan1);
        testPlanInstance.setRepositoryId(repositoryId);
        testPlanInstance.setCreateTime(new Timestamp(System.currentTimeMillis()));
        testPlanInstance.setCreateUser(LoginContext.getLoginId());
        testPlanInstance.setTotal(exeCount);
        testPlanInstance.setPassNum(0);
        testPlanInstance.setPassRate("0.00%");
        testPlanInstance.setFailNum(0);
        testPlanInstance.setErrorRate("0.00%");
        testPlanInstance.setResult(2);
        String planInstanceId = testPlanInstanceService.createTestPlanInstance(testPlanInstance);


        // 创建公共实例
        Instance instance = new Instance();
        instance.setId(planInstanceId);

        instance.setBelongId(testPlanId);
        instance.setType(MagicValue.TEST_PLAN);

        TestPlan testPlan = testPlanService.findTestPlan(testPlanId);
        instance.setName(testPlan.getName());
        instance.setRepositoryId(testPlan.getRepository().getId());

        InstanceQuery instanceQuery = new InstanceQuery();
        instanceQuery.setBelongId(testPlanId);
        List<Instance> instanceList = instanceService.findInstanceList(instanceQuery);
        if(instanceList!=null&& !instanceList.isEmpty()){
            Integer executeNumber = instanceList.get(0).getExecuteNumber();
            instance.setExecuteNumber(++executeNumber);
        }else {
            instance.setExecuteNumber(1);
        }

        JSONObject instanceMap = new JSONObject();
        instanceMap.put("result",testPlanInstance.getResult().toString());
        instanceMap.put("total",testPlanInstance.getTotal().toString());
        instanceMap.put("passNum",testPlanInstance.getPassNum().toString());
        instanceMap.put("passRate",testPlanInstance.getPassRate());
        instanceMap.put("failNum",testPlanInstance.getFailNum().toString());
        instanceMap.put("errorRate",testPlanInstance.getErrorRate());
        instance.setContent(instanceMap.toString());

        instanceService.createInstance(instance);

        return planInstanceId;
    }


}



