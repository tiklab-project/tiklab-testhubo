package io.tiklab.teston.testplan.cases.service;

import io.tiklab.join.annotation.JoinProvider;
import io.tiklab.teston.test.test.model.TestCases;
import io.tiklab.teston.testplan.cases.model.TestPlanCase;
import io.tiklab.teston.testplan.cases.model.TestPlanCaseQuery;
import io.tiklab.core.page.Pagination;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 测试计划绑定的用例 服务接口
*/
@JoinProvider(model = TestPlanCase.class)
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
    Pagination<TestCases> findTesCaseList(TestPlanCase testPlanCase);

    /**
     * 查询已经关联的用例
     * @param testPlanCaseQuery
     * @return
     */
    Pagination<TestPlanCase> findBindTestCaseList(TestPlanCaseQuery testPlanCaseQuery);


    /**
     * 测试计划中关联用例
     * @param testPlanCaseList
     */
    void  planBindCase(List<TestPlanCase> testPlanCaseList);
}