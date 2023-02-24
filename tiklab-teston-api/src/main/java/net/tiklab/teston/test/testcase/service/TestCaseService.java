package net.tiklab.teston.test.testcase.service;

import net.tiklab.core.page.Pagination;
import net.tiklab.join.annotation.FindAll;
import net.tiklab.join.annotation.FindList;
import net.tiklab.join.annotation.FindOne;
import net.tiklab.join.annotation.JoinProvider;
import net.tiklab.teston.test.testcase.model.TestCase;
import net.tiklab.teston.test.testcase.model.TestCaseQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* StepService
*/
@JoinProvider(model = TestCase.class)
public interface TestCaseService {

    /**
    * 创建
    * @param testCase
    * @return
    */
    String createTestCase(@NotNull @Valid TestCase testCase);

    /**
    * 更新
    * @param testCase
    */
    void updateTestCase(@NotNull @Valid TestCase testCase);

    /**
    * 删除
    * @param id
    */
    void deleteTestCase(@NotNull String id);

    /**
     * 通过目录id删除
     * @param categoryId
     */
    void deleteTestCaseByCategoryId(@NotNull String categoryId);

    @FindOne
    TestCase findOne(@NotNull String id);

    @FindList
    List<TestCase> findList(List<String> idList);

    /**
    * 查找
    * @param id
    * @return
    */
    TestCase findTestCase(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    @FindAll
    List<TestCase> findAllTestCase();

    /**
    * 查询列表
    * @param testCaseQuery
    * @return
    */
    List<TestCase> findTestCaseList(TestCaseQuery testCaseQuery);

    /**
    * 按分页查询
    * @param testCaseQuery
    * @return
    */
    Pagination<TestCase> findTestCasePage(TestCaseQuery testCaseQuery);

}