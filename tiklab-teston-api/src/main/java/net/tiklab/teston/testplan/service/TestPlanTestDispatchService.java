package net.tiklab.teston.testplan.service;

import net.tiklab.teston.testplan.model.TestPlanInstance;
import net.tiklab.teston.testplan.model.TestPlanTestData;
import net.tiklab.teston.testplan.model.TestPlanTestResponse;

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
