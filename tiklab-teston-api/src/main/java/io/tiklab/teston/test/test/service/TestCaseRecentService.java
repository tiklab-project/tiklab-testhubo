package io.tiklab.teston.test.test.service;


import io.tiklab.core.page.Pagination;
import io.tiklab.join.annotation.FindAll;
import io.tiklab.join.annotation.FindList;
import io.tiklab.join.annotation.FindOne;
import io.tiklab.join.annotation.JoinProvider;
import io.tiklab.teston.test.test.model.TestCaseRecent;
import io.tiklab.teston.test.test.model.TestCaseRecentQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 最近访问仓库 服务接口
*/
@JoinProvider(model = TestCaseRecent.class)
public interface TestCaseRecentService {

    /**
    * 创建最近访问仓库
    * @param testCaseRecent
    * @return
    */
    String createTestCaseRecent(@NotNull @Valid TestCaseRecent testCaseRecent);

    /**
    * 更新最近访问仓库
    * @param testCaseRecent
    */
    void updateTestCaseRecent(@NotNull @Valid TestCaseRecent testCaseRecent);

    /**
    * 删除最近访问仓库
    * @param id
    */
    void deleteTestCaseRecent(@NotNull String id);

    @FindOne
    TestCaseRecent findOne(@NotNull String id);

    @FindList
    List<TestCaseRecent> findList(List<String> idList);

    /**
    * 查找最近访问仓库
    * @param id
    * @return
    */
    TestCaseRecent findTestCaseRecent(@NotNull String id);

    /**
    * 查找所有最近访问仓库
    * @return
    */
    @FindAll
    List<TestCaseRecent> findAllTestCaseRecent();

    /**
     * 查询最近访问仓库列表
     * @param testCaseRecentQuery
     * @return
     */
    List<TestCaseRecent> findTestCaseRecentList(TestCaseRecentQuery testCaseRecentQuery);


    /**
    * 按分页查询最近访问仓库
    * @param testCaseRecentQuery
    * @return
    */
    Pagination<TestCaseRecent> findTestCaseRecentPage(TestCaseRecentQuery testCaseRecentQuery);

    /**
     * 设置最近访问仓库
     * @param testCaseRecent
     */
    void testCaseRecent(@NotNull @Valid TestCaseRecent testCaseRecent);

}