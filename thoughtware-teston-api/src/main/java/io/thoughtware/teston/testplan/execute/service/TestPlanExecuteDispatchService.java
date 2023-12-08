package io.thoughtware.teston.testplan.execute.service;

import io.thoughtware.teston.testplan.execute.model.TestPlanTestData;
import io.thoughtware.teston.testplan.execute.model.TestPlanTestResponse;

/**
 * 测试计划 测试
 */
public interface TestPlanExecuteDispatchService {

    /**
     * 执行
     * @param testPlanTestData
     * @return
     */
    void execute(TestPlanTestData testPlanTestData);


    /**
     * 获取测试的测试数据
     * @return
     */
    TestPlanTestResponse exeResult();

}
