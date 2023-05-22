package io.tiklab.teston.test.test.service;

import io.tiklab.teston.test.test.model.TestCases;
import io.tiklab.teston.test.test.model.TestCaseQuery;
import io.tiklab.core.page.Pagination;
import io.tiklab.join.annotation.FindAll;
import io.tiklab.join.annotation.FindList;
import io.tiklab.join.annotation.FindOne;
import io.tiklab.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 测试用例 服务接口
*/
@JoinProvider(model = TestCases.class)
public interface TestCaseService {

    /**
    * 创建测试用例
    * @param testCases
    * @return
    */
    String createTestCase(@NotNull @Valid TestCases testCases);

    /**
    * 更新测试用例
    * @param testCases
    */
    void updateTestCase(@NotNull @Valid TestCases testCases);

    /**
    * 删除测试用例
    * @param id
    */
    void deleteTestCase(@NotNull String id);

    /**
     * 通过目录id删除
     * @param categoryId
     */
    void deleteTestCaseByCategoryId(@NotNull String categoryId);

    @FindOne
    TestCases findOne(@NotNull String id);

    @FindList
    List<TestCases> findList(List<String> idList);

    /**
    * 根据id查找测试用例
    * @param id
    * @return
    */
    TestCases findTestCase(@NotNull String id);

    /**
    * 查找所有测试用例
    * @return
    */
    @FindAll
    List<TestCases> findAllTestCase();

    /**
    * 根据查询参数查询测试用例列表
    * @param testCaseQuery
    * @return
    */
    List<TestCases> findTestCaseList(TestCaseQuery testCaseQuery);

    /**
    * 根据查询参数按分页查询测试用例
    * @param testCaseQuery
    * @return
    */
    Pagination<TestCases> findTestCasePage(TestCaseQuery testCaseQuery);

}