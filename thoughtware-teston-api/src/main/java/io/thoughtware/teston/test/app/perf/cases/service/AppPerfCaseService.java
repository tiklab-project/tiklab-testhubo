package io.thoughtware.teston.test.app.perf.cases.service;


import io.thoughtware.teston.test.test.model.TestCaseQuery;
import io.thoughtware.teston.test.app.perf.cases.model.AppPerfCase;
import io.thoughtware.teston.test.app.perf.cases.model.AppPerfCaseQuery;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.toolkit.join.annotation.FindAll;
import io.thoughtware.toolkit.join.annotation.FindList;
import io.thoughtware.toolkit.join.annotation.FindOne;
import io.thoughtware.toolkit.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* app性能测试用例 服务接口
*/
@JoinProvider(model = AppPerfCase.class)
public interface AppPerfCaseService {

    /**
    * 创建app性能测试用例
    * @param appPerfCase
    * @return
    */
    String createAppPerfCase(@NotNull @Valid AppPerfCase appPerfCase);

    /**
    * 更新app性能测试用例
    * @param appPerfCase
    */
    void updateAppPerfCase(@NotNull @Valid AppPerfCase appPerfCase);

    /**
    * 删除app性能测试用例
    * @param id
    */
    void deleteAppPerfCase(@NotNull String id);

    @FindOne
    AppPerfCase findOne(@NotNull String id);

    @FindList
    List<AppPerfCase> findList(List<String> idList);

    /**
    * 根据id查找app性能测试用例
    * @param id
    * @return
    */
    AppPerfCase findAppPerfCase(@NotNull String id);

    /**
    * 查找所有app性能测试用例
    * @return
    */
    @FindAll
    List<AppPerfCase> findAllAppPerfCase();

    /**
    * 根据查询参数查询查询app性能测试用例列表
    * @param appPerfCaseQuery
    * @return
    */
    List<AppPerfCase> findAppPerfCaseList(AppPerfCaseQuery appPerfCaseQuery);

    /**
    * 根据查询参数查询按分页查询app性能测试用例
    * @param appPerfCaseQuery
    * @return
    */
    Pagination<AppPerfCase> findAppPerfCasePage(AppPerfCaseQuery appPerfCaseQuery);

    /**
     * 通过testcase查询app性能测试用例
     * @param testCaseQuery
     * @return
     */
    List<AppPerfCase> findAppPerfCaseListByTestCase(TestCaseQuery testCaseQuery);


}