package net.tiklab.teston.app.scene.cases.service;


import net.tiklab.core.page.Pagination;
import net.tiklab.join.annotation.FindAll;
import net.tiklab.join.annotation.FindList;
import net.tiklab.join.annotation.FindOne;
import net.tiklab.join.annotation.JoinProvider;
import net.tiklab.teston.app.scene.cases.model.AppSceneCaseQuery;
import net.tiklab.teston.manager.testcase.model.TestCaseQuery;
import net.tiklab.teston.app.scene.cases.model.AppSceneCase;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* AppSceneCaseService
*/
@JoinProvider(model = AppSceneCase.class)
public interface AppSceneCaseService {

    /**
    * 创建
    * @param appSceneCase
    * @return
    */
    String createAppSceneCase(@NotNull @Valid AppSceneCase appSceneCase);

    /**
    * 更新
    * @param appSceneCase
    */
    void updateAppSceneCase(@NotNull @Valid AppSceneCase appSceneCase);

    /**
    * 删除
    * @param id
    */
    void deleteAppSceneCase(@NotNull String id);

    @FindOne
    AppSceneCase findOne(@NotNull String id);

    @FindList
    List<AppSceneCase> findList(List<String> idList);

    /**
    * 查找
    * @param id
    * @return
    */
    AppSceneCase findAppSceneCase(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    @FindAll
    List<AppSceneCase> findAllAppSceneCase();

    /**
    * 查询列表
    * @param appSceneCaseQuery
    * @return
    */
    List<AppSceneCase> findAppSceneCaseList(AppSceneCaseQuery appSceneCaseQuery);

    /**
    * 按分页查询
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