package net.tiklab.teston.testplan.cases.service;

import net.tiklab.core.page.Pagination;

import net.tiklab.join.annotation.FindAll;
import net.tiklab.join.annotation.FindList;
import net.tiklab.join.annotation.FindOne;
import net.tiklab.join.annotation.JoinProvider;
import net.tiklab.teston.testplan.cases.model.TestPlan;
import net.tiklab.teston.testplan.cases.model.TestPlanQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 测试计划 服务接口
*/
@JoinProvider(model = TestPlan.class)
public interface TestPlanService {

    /**
    * 创建测试计划
    * @param testPlan
    * @return
    */
    String createTestPlan(@NotNull @Valid TestPlan testPlan);

    /**
    * 更新测试计划
    * @param testPlan
    */
    void updateTestPlan(@NotNull @Valid TestPlan testPlan);

    /**
    * 删除测试计划
    * @param id
    */
    void deleteTestPlan(@NotNull String id);

    @FindOne
    TestPlan findOne(@NotNull String id);

    @FindList
    List<TestPlan> findList(List<String> idList);

    /**
    * 根据id查找测试计划
    * @param id
    * @return
    */

    TestPlan findTestPlan(@NotNull String id);

    /**
    * 查找所有测试计划
    * @return
    */
    @FindAll
    List<TestPlan> findAllTestPlan();

    /**
    * 根据查询参数查询测试计划列表
    * @param testPlanQuery
    * @return
    */
    List<TestPlan> findTestPlanList(TestPlanQuery testPlanQuery);

    /**
    * 根据查询参数按分页查询测试计划
    * @param testPlanQuery
    * @return
    */
    Pagination<TestPlan> findTestPlanPage(TestPlanQuery testPlanQuery);

}