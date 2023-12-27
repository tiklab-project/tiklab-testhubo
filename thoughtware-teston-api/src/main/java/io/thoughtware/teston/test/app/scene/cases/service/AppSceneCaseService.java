package io.thoughtware.teston.test.app.scene.cases.service;


import io.thoughtware.teston.test.test.model.TestCaseQuery;
import io.thoughtware.teston.test.app.scene.cases.model.AppSceneCase;
import io.thoughtware.teston.test.app.scene.cases.model.AppSceneCaseQuery;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.toolkit.join.annotation.FindAll;
import io.thoughtware.toolkit.join.annotation.FindList;
import io.thoughtware.toolkit.join.annotation.FindOne;
import io.thoughtware.toolkit.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* app场景用例 服务接口
*/
@JoinProvider(model = AppSceneCase.class)
public interface AppSceneCaseService {

    /**
    * 创建app场景用例
    * @param appSceneCase
    * @return
    */
    String createAppSceneCase(@NotNull @Valid AppSceneCase appSceneCase);

    /**
    * 更新app场景用例
    * @param appSceneCase
    */
    void updateAppSceneCase(@NotNull @Valid AppSceneCase appSceneCase);

    /**
    * 删除app场景用例
    * @param id
    */
    void deleteAppSceneCase(@NotNull String id);

    @FindOne
    AppSceneCase findOne(@NotNull String id);

    @FindList
    List<AppSceneCase> findList(List<String> idList);

    /**
    * 根据id查找app场景用例
    * @param id
    * @return
    */
    AppSceneCase findAppSceneCase(@NotNull String id);

    /**
    * 查找所有app场景用例
    * @return
    */
    @FindAll
    List<AppSceneCase> findAllAppSceneCase();

    /**
    * 根据查询参数查询app场景用例列表
    * @param appSceneCaseQuery
    * @return
    */
    List<AppSceneCase> findAppSceneCaseList(AppSceneCaseQuery appSceneCaseQuery);

    /**
    * 根据查询参数按分页查询app场景用例
    * @param appSceneCaseQuery
    * @return
    */
    Pagination<AppSceneCase> findAppSceneCasePage(AppSceneCaseQuery appSceneCaseQuery);


    /**
     * 通过中间层Testcase 查询场景
     * @param testCaseQuery
     * @return
     */
    List<AppSceneCase> findAppSceneCaseListByTestCase(TestCaseQuery testCaseQuery);


}