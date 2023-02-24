package net.tiklab.teston.test.appperf.cases.service;


import net.tiklab.core.page.Pagination;
import net.tiklab.join.annotation.FindAll;
import net.tiklab.join.annotation.FindList;
import net.tiklab.join.annotation.FindOne;
import net.tiklab.join.annotation.JoinProvider;
import net.tiklab.teston.test.appperf.cases.model.AppPerfCaseQuery;
import net.tiklab.teston.test.appperf.cases.model.AppPerfCase;
import net.tiklab.teston.test.testcase.model.TestCaseQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* AppPerfCaseService
*/
@JoinProvider(model = AppPerfCase.class)
public interface AppPerfCaseService {

    /**
    * 创建
    * @param appPerfCase
    * @return
    */
    String createAppPerfCase(@NotNull @Valid AppPerfCase appPerfCase);

    /**
    * 更新
    * @param appPerfCase
    */
    void updateAppPerfCase(@NotNull @Valid AppPerfCase appPerfCase);

    /**
    * 删除
    * @param id
    */
    void deleteAppPerfCase(@NotNull String id);

    @FindOne
    AppPerfCase findOne(@NotNull String id);

    @FindList
    List<AppPerfCase> findList(List<String> idList);

    /**
    * 查找
    * @param id
    * @return
    */
    AppPerfCase findAppPerfCase(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    @FindAll
    List<AppPerfCase> findAllAppPerfCase();

    /**
    * 查询列表
    * @param appPerfCaseQuery
    * @return
    */
    List<AppPerfCase> findAppPerfCaseList(AppPerfCaseQuery appPerfCaseQuery);

    /**
    * 按分页查询
    * @param appPerfCaseQuery
    * @return
    */
    Pagination<AppPerfCase> findAppPerfCasePage(AppPerfCaseQuery appPerfCaseQuery);

    /**
     * 通过testcase查询
     * @param testCaseQuery
     * @return
     */
    List<AppPerfCase> findAppPerfCaseListByTestCase(TestCaseQuery testCaseQuery);


}