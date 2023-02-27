package net.tiklab.teston.manager.testplan.service;

import net.tiklab.teston.manager.testplan.model.TestPlanTestResponse;
import net.tiklab.teston.manager.testplan.model.TestPlanTestData;

/**
 * 测试计划 测试
 */
public interface TestPlanTestDispatchService {

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
