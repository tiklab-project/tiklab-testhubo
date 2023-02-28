package net.tiklab.teston.manager.testplan.cases.service;

import net.tiklab.core.page.Pagination;

import net.tiklab.teston.manager.testplan.cases.model.TestPlanCase;
import net.tiklab.teston.manager.testcase.model.TestCase;
import net.tiklab.teston.manager.testplan.cases.model.TestPlanCaseQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* TestPlanCaseService
*/
public interface TestPlanCaseService {

    /**
    * 创建
    * @param testPlanCase
    * @return
    */
    String createTestPlanCase(@NotNull @Valid TestPlanCase testPlanCase);

    /**
    * 更新
    * @param testPlanCase
    */
    void updateTestPlanCase(@NotNull @Valid TestPlanCase testPlanCase);

    /**
    * 删除
    * @param id
    */
    void deleteTestPlanCase(@NotNull String id);

    TestPlanCase findOne(@NotNull String id);

    List<TestPlanCase> findList(List<String> idList);

    /**
    * 查找
    * @param id
    * @return
    */
    TestPlanCase findTestPlanCase(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<TestPlanCase> findAllTestPlanCase();

    /**
    * 查询列表
    * @param testPlanCaseQuery
    * @return
    */
    List<TestPlanCase> findTestPlanCaseList(TestPlanCaseQuery testPlanCaseQuery);

    /**
    * 按分页查询
    * @param testPlanCaseQuery
    * @return
    */
    Pagination<TestPlanCase> findTestPlanCasePage(TestPlanCaseQuery testPlanCaseQuery);
    /**
     * 添加用例弹窗列表
     * @param testPlanCase
     * @return
     */
    Pagination<TestCase> findTesCaseList(TestPlanCase testPlanCase);

    /**
     * 查询已经关联的用例
     * @param testPlanCaseQuery
     * @return
     */
    Pagination<TestPlanCase> findBindTestCaseList(TestPlanCaseQuery testPlanCaseQuery);
}