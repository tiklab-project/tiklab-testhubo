package net.tiklab.teston.testplan.cases.service;

import net.tiklab.core.page.Pagination;

import net.tiklab.teston.testplan.cases.model.TestPlanCase;
import net.tiklab.teston.test.test.model.TestCase;
import net.tiklab.teston.testplan.cases.model.TestPlanCaseQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 测试计划绑定的用例 服务接口
*/
public interface TestPlanCaseService {

    /**
    * 创建绑定的用例
    * @param testPlanCase
    * @return
    */
    String createTestPlanCase(@NotNull @Valid TestPlanCase testPlanCase);

    /**
    * 更新绑定的用例
    * @param testPlanCase
    */
    void updateTestPlanCase(@NotNull @Valid TestPlanCase testPlanCase);

    /**
    * 删除绑定的用例
    * @param id
    */
    void deleteTestPlanCase(@NotNull String id);

    TestPlanCase findOne(@NotNull String id);

    List<TestPlanCase> findList(List<String> idList);

    /**
    * 根据id查找绑定的用例
    * @param id
    * @return
    */
    TestPlanCase findTestPlanCase(@NotNull String id);

    /**
    * 查找所有绑定的用例
    * @return
    */
    List<TestPlanCase> findAllTestPlanCase();

    /**
    * 查询绑定的用例 列表
    * @param testPlanCaseQuery
    * @return
    */
    List<TestPlanCase> findTestPlanCaseList(TestPlanCaseQuery testPlanCaseQuery);

    /**
    * 按分页查询绑定的用例
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