package io.thoughtware.teston.test.web.scene.cases.service;

import io.thoughtware.teston.test.test.model.TestCaseQuery;
import io.thoughtware.teston.test.web.scene.cases.model.WebSceneCase;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.join.annotation.FindAll;
import io.thoughtware.join.annotation.FindList;
import io.thoughtware.join.annotation.FindOne;
import io.thoughtware.join.annotation.JoinProvider;
import io.thoughtware.teston.test.web.scene.cases.model.WebSceneCaseQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* web场景用例 服务接口
*/
@JoinProvider(model = WebSceneCase.class)
public interface WebSceneCaseService {

    /**
    * 创建web场景用例
    * @param sceneCase
    * @return
    */
    String createWebSceneCase(@NotNull @Valid WebSceneCase sceneCase);

    /**
    * 更新web场景用例
    * @param sceneCase
    */
    void updateWebSceneCase(@NotNull @Valid WebSceneCase sceneCase);

    /**
    * 删除web场景用例
    * @param id
    */
    void deleteWebSceneCase(@NotNull String id);

    @FindOne
    WebSceneCase findOne(@NotNull String id);

    @FindList
    List<WebSceneCase> findList(List<String> idList);

    /**
    * 根据id查找web场景用例
    * @param id
    * @return
    */
    WebSceneCase findWebSceneCase(@NotNull String id);

    /**
    * 查找所有web场景用例
    * @return
    */
    @FindAll
    List<WebSceneCase> findAllWebSceneCase();

    /**
    * 查询web场景用例列表
    * @param sceneCaseQuery
    * @return
    */
    List<WebSceneCase> findWebSceneCaseList(WebSceneCaseQuery sceneCaseQuery);

    /**
    * 根据查询参数按分页查询web场景用例
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