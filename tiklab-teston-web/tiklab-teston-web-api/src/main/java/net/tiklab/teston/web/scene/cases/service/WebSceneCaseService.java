package net.tiklab.teston.web.scene.cases.service;

import net.tiklab.core.page.Pagination;
import net.tiklab.join.annotation.FindAll;
import net.tiklab.join.annotation.FindList;
import net.tiklab.join.annotation.FindOne;
import net.tiklab.join.annotation.JoinProvider;
import net.tiklab.teston.manager.testcase.model.TestCaseQuery;
import net.tiklab.teston.web.scene.cases.model.WebSceneCase;
import net.tiklab.teston.web.scene.cases.model.WebSceneCaseQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* TestCaseService
*/
@JoinProvider(model = WebSceneCase.class)
public interface WebSceneCaseService {

    /**
    * 创建
    * @param sceneCase
    * @return
    */
    String createWebSceneCase(@NotNull @Valid WebSceneCase sceneCase);

    /**
    * 更新
    * @param sceneCase
    */
    void updateWebSceneCase(@NotNull @Valid WebSceneCase sceneCase);

    /**
    * 删除
    * @param id
    */
    void deleteWebSceneCase(@NotNull String id);

    @FindOne
    WebSceneCase findOne(@NotNull String id);

    @FindList
    List<WebSceneCase> findList(List<String> idList);

    /**
    * 查找
    * @param id
    * @return
    */
    WebSceneCase findWebSceneCase(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    @FindAll
    List<WebSceneCase> findAllWebSceneCase();

    /**
    * 查询列表
    * @param sceneCaseQuery
    * @return
    */
    List<WebSceneCase> findWebSceneCaseList(WebSceneCaseQuery sceneCaseQuery);

    /**
    * 按分页查询
    * @param sceneCaseQuery
    * @return
    */
    Pagination<WebSceneCase> findWebSceneCasePage(WebSceneCaseQuery sceneCaseQuery);


    /**
     * 通过中间层Testcase 查询场景
     * @param testCaseQuery
     * @return
     */
    List<WebSceneCase> findWebSceneCaseListByTestCase(TestCaseQuery testCaseQuery);

}