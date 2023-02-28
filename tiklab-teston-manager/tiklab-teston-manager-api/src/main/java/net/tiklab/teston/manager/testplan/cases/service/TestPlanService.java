package net.tiklab.teston.manager.testplan.cases.service;

import net.tiklab.core.page.Pagination;

import net.tiklab.join.annotation.FindAll;
import net.tiklab.join.annotation.FindList;
import net.tiklab.join.annotation.FindOne;
import net.tiklab.join.annotation.JoinProvider;
import net.tiklab.teston.manager.testplan.cases.model.TestPlan;
import net.tiklab.teston.manager.testplan.cases.model.TestPlanQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* TestPlanService
*/
@JoinProvider(model = TestPlan.class)
public interface TestPlanService {

    /**
    * 创建
    * @param testPlan
    * @return
    */
    String createTestPlan(@NotNull @Valid TestPlan testPlan);

    /**
    * 更新
    * @param testPlan
    */
    void updateTestPlan(@NotNull @Valid TestPlan testPlan);

    /**
    * 删除
    * @param id
    */
    void deleteTestPlan(@NotNull String id);

    @FindOne
    TestPlan findOne(@NotNull String id);

    @FindList
    List<TestPlan> findList(List<String> idList);

    /**
    * 查找
    * @param id
    * @return
    */

    TestPlan findTestPlan(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    @FindAll
    List<TestPlan> findAllTestPlan();

    /**
    * 查询列表
    * @param testPlanQuery
    * @return
    */
    List<TestPlan> findTestPlanList(TestPlanQuery testPlanQuery);

    /**
    * 按分页查询
    * @param testPlanQuery
    * @return
    */
    Pagination<TestPlan> findTestPlanPage(TestPlanQuery testPlanQuery);

}