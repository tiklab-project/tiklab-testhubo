package net.tiklab.teston.manager.testplan.execute.service;

import net.tiklab.teston.manager.common.TestUtil;
import net.tiklab.teston.manager.testplan.cases.model.*;
import net.tiklab.teston.manager.testplan.cases.service.TestPlanCaseService;
import net.tiklab.teston.manager.testplan.execute.model.TestPlanTestData;
import net.tiklab.teston.manager.testplan.execute.model.TestPlanTestResponse;
import net.tiklab.teston.manager.testplan.instance.model.TestPlanCaseInstanceBind;
import net.tiklab.teston.manager.testplan.instance.model.TestPlanInstance;
import net.tiklab.teston.manager.testplan.instance.model.TestPlanInstanceQuery;
import net.tiklab.teston.manager.testplan.instance.service.TestPlanInstanceService;
import net.tiklab.utils.context.LoginContext;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

/**
 * 测试计划测试调度
 */
@Service
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


    private ArrayList<TestPlanCaseInstanceBind> testPlanCaseInstanceList = new ArrayList<>();

    private String testPlanId;

    /**
     * 需要执行的用例总数
     */
    private Integer exeCount=0;

    private Integer apiPerfCount=0;
    private Integer webPerfCount=0;
    private Integer appPerfCount=0;

    /**
     * 执行时设置一个测试计划历史Id
     */
    public String testPlanInstanceId;

    /**
     * 执行的状态：0：未执行，1：正在进行，2：结束
     */
    private Integer status=0;


    @Override
    public void execute(TestPlanTestData testPlanTestData) {
        //如果之前执行过，先清空之前保留数据
        exeCount=0;
        apiPerfCount=0;
        webPerfCount=0;
        appPerfCount=0;
        testPlanCaseInstanceList.clear();

        //执行把状态变为 1 正在进行
        status=1;

        testPlanId = testPlanTestData.getTestPlanId();
        String repositoryId = testPlanTestData.getRepositoryId();

        //执行的时候先创建一个历史，里面没有数据，用于获取Id
        TestPlanInstance testPlanInstance = new TestPlanInstance();

        TestPlanInstanceQuery testPlanInstanceQuery = new TestPlanInstanceQuery();
        testPlanInstanceQuery.setRepositoryId(testPlanTestData.getRepositoryId());
        List<TestPlanInstance> testPlanInstanceList = testPlanInstanceService.findTestPlanInstanceList(testPlanInstanceQuery);

        if(testPlanInstanceList.size()>0){
            //获取上一条执行次数
            Integer executeNumber = testPlanInstanceList.get(0).getExecuteNumber();
            Integer addNumber=executeNumber+1;
            testPlanInstance.setExecuteNumber(addNumber);
        }else {
            testPlanInstance.setExecuteNumber(1);
        }

        testPlanInstance.setTestPlanId(testPlanId);
        testPlanInstance.setRepositoryId(repositoryId);
        testPlanInstance.setCreateTime(new Timestamp(System.currentTimeMillis()));
        testPlanInstance.setCreateUser(LoginContext.getLoginId());
        testPlanInstanceId = testPlanInstanceService.createTestPlanInstance(testPlanInstance);


        //查询出当前测试计划的所有关联的测试用例
        TestPlanCaseQuery testPlanCaseQuery = new TestPlanCaseQuery();
        testPlanCaseQuery.setTestPlanId(testPlanId);
        List<TestPlanCase> testPlanCaseList = testPlanCaseService.findTestPlanCaseList(testPlanCaseQuery);

        if(CollectionUtils.isNotEmpty(testPlanCaseList)){
            //首先获取能执行的用例总数
            for(TestPlanCase testPlanCase : testPlanCaseList){
                String testType = testPlanCase.getTestCase().getTestType();
                //只有自动化测试和性能测试执行
                if(Objects.equals(testType,"auto")||Objects.equals(testType,"perform")){
                    exeCount++;
                }
            }

            //循环 测试用例
            for(TestPlanCase testPlanCase : testPlanCaseList){
                String testType = testPlanCase.getTestCase().getTestType();
                String caseType = testPlanCase.getTestCase().getCaseType();

                //根据不同的测试类型进行测试
                if(Objects.equals(testType,"auto")||Objects.equals(testType,"perform")){

                    switch (caseType) {
                        case "api-unit" -> {
                            TestPlanCaseInstanceBind testPlanCaseInstanceBind = testPlanExecuteApiDispatch.exeApiUnit(testPlanCase, testPlanTestData, testPlanInstanceId);
                            testPlanCaseInstanceList.add(testPlanCaseInstanceBind);
                        }
                        case "api-scene" -> {
                            TestPlanCaseInstanceBind apiSceneInstance = testPlanExecuteApiDispatch.exeApiScene(testPlanCase, testPlanTestData, testPlanInstanceId);
                            testPlanCaseInstanceList.add(apiSceneInstance);
                        }
                        case "api-perform" -> {
                            apiPerfCount++;
                            testPlanExecuteApiDispatch.exeApiPerform(testPlanCase, testPlanTestData, testPlanInstanceId);
                        }
                        case "web-scene" -> {
                            TestPlanCaseInstanceBind webSceneInstance = testPlanExecuteWebDispatch.exeWebScene(testPlanCase, testPlanTestData, testPlanInstanceId);
                            testPlanCaseInstanceList.add(webSceneInstance);
                        }
                        case "web-perform" -> {
                            webPerfCount++;
                            testPlanExecuteWebDispatch.exeWebPerform(testPlanCase, testPlanTestData, testPlanInstanceId);
                        }
                        case "app-scene" -> {
                            TestPlanCaseInstanceBind appSceneInstance = testPlanExecuteAppDispatch.exeAppScene(testPlanCase, testPlanTestData, testPlanInstanceId);
                            testPlanCaseInstanceList.add(appSceneInstance);
                        }
                        case "app-perform" -> {
                            appPerfCount++;
                            testPlanExecuteAppDispatch.exeAppPerform(testPlanCase, testPlanTestData, testPlanInstanceId);
                        }
                        default -> {
                        }
                    }

                }
            }
        }
    }




    @Override
    public TestPlanTestResponse exeResult() {

        TestPlanTestResponse testPlanTestResponse = new TestPlanTestResponse();
        //处理数据
        TestPlanInstance testPlanInstance = processInstance(testPlanCaseInstanceList);

        testPlanTestResponse.setTestPlanInstance(testPlanInstance);
        testPlanTestResponse.setTestPlanCaseInstanceList(testPlanCaseInstanceList);


        if(apiPerfCount!=0){
            if(testPlanExecuteApiDispatch.apiPerfStatus==1){
                TestPlanCaseInstanceBind apiPerfInstance = testPlanExecuteApiDispatch.apiPerfResult();
                testPlanCaseInstanceList.add(apiPerfInstance);
            }
        }

//        if(webPerfCount!=0){
//            if(testPlanTestDispatchWeb.webPerfStatus==1){
//                TestPlanCaseInstanceBind webPerfResult = testPlanTestDispatchWeb.webPerfResult();
//                testPlanCaseInstanceList.add(webPerfResult);
//            }
//        }
//
//        if(appPerfCount!=0){
//            if(testPlanTestDispatchApp.appPerfStatus==1){
//                TestPlanCaseInstanceBind appPerfResult = testPlanTestDispatchApp.appPerfResult();
//                testPlanCaseInstanceList.add(appPerfResult);
//            }
//        }

        //如果用例个数 和 执行的总个数一样，证明执行了所有
        if(Objects.equals(testPlanCaseInstanceList.size(),exeCount)){
            //执行结束
            status=2;
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

        testPlanInstance.setTestPlanId(testPlanId);
        testPlanInstance.setId(testPlanInstanceId);
        testPlanInstanceService.updateTestPlanInstance(testPlanInstance);

    }

}



