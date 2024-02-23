package io.thoughtware.teston.test.test.service;

import io.thoughtware.teston.test.test.model.TestCase;
import io.thoughtware.teston.test.test.model.TestCaseQuery;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.toolkit.join.annotation.FindAll;
import io.thoughtware.toolkit.join.annotation.FindList;
import io.thoughtware.toolkit.join.annotation.FindOne;
import io.thoughtware.toolkit.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;

/**
* 测试用例 服务接口
*/
@JoinProvider(model = TestCase.class)
public interface TestCaseService {

    /**
    * 创建测试用例
    * @param testCase
    * @return
    */
    String createTestCase(@NotNull @Valid TestCase testCase);

    /**
    * 更新测试用例
    * @param testCase
    */
    void updateTestCase(@NotNull @Valid TestCase testCase);

    /**
    * 删除测试用例
    * @param id
    */
    void deleteTestCase(@NotNull String id,String caseType);

    /**
     * 通过repositoryId删除用例
     * @param repositoryId
     */
    void deleteAllTestCase( String repositoryId);


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
     * 查询测试用例总数
     * @param repositoryId
     * @return
     */
    int findTestCaseNum(String repositoryId);

    /**
     * 查询不同测试类型的数量，功能，接口，ui，性能
     * @param repositoryId
     * @return
     */
    HashMap<String, Integer> findDiffTypeCaseNum(String repositoryId);

    /**
    * 根据id查找测试用例
    * @param id
    * @return
    */
    TestCase findTestCase(@NotNull String id);

    /**
    * 查找所有测试用例
    * @return
    */
    @FindAll
    List<TestCase> findAllTestCase();

    /**
    * 根据查询参数查询测试用例列表
    * @param testCaseQuery
    * @return
    */
    List<TestCase> findTestCaseList(TestCaseQuery testCaseQuery);

    /**
    * 根据查询参数按分页查询测试用例
    * @param testCaseQuery
    * @return
    */
    Pagination<TestCase> findTestCasePage(TestCaseQuery testCaseQuery);


}