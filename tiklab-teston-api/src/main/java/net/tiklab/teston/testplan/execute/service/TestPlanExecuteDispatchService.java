package net.tiklab.teston.testplan.execute.service;

import net.tiklab.teston.testplan.execute.model.TestPlanTestResponse;
import net.tiklab.teston.testplan.execute.model.TestPlanTestData;

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
