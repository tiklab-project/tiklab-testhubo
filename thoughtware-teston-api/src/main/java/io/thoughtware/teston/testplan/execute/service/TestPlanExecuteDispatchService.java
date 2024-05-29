package io.thoughtware.teston.testplan.execute.service;

import io.thoughtware.teston.testplan.execute.model.TestPlanTestData;
import io.thoughtware.teston.testplan.execute.model.TestPlanTestResponse;
import io.thoughtware.teston.testplan.instance.model.TestPlanCaseInstanceBind;

import java.util.ArrayList;

/**
 * 测试计划 测试
 */
public interface TestPlanExecuteDispatchService {

    /**
     * 执行
     * @param testPlanTestData
     * @return
     */
    String execute(TestPlanTestData testPlanTestData);


    /**
     * 获取测试的测试数据
     * @return
     */
    TestPlanTestResponse exeResult(String testPlanId);

    void cleanUpExecutionData(String testPlanId);

    /**
     * 重写
     * @param testPlanTestData
     * @param planCaseInstanceList
     */
    void executeTestPlanCases(TestPlanTestData testPlanTestData, ArrayList<TestPlanCaseInstanceBind> planCaseInstanceList);
    void getPlanCaseInstance(ArrayList<TestPlanCaseInstanceBind> testPlanCaseInstanceList);



}
