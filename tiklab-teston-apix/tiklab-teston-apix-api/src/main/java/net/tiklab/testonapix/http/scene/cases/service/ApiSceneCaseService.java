package net.tiklab.testonapix.http.scene.cases.service;

import net.tiklab.core.page.Pagination;
import net.tiklab.join.annotation.FindAll;
import net.tiklab.join.annotation.FindList;
import net.tiklab.join.annotation.FindOne;
import net.tiklab.join.annotation.JoinProvider;
import net.tiklab.teston.test.testcase.model.TestCaseQuery;
import net.tiklab.testonapix.http.scene.cases.model.ApiSceneCase;
import net.tiklab.testonapix.http.scene.cases.model.ApiSceneCaseQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* TestCaseService
*/
@JoinProvider(model = ApiSceneCase.class)
public interface ApiSceneCaseService {

    /**
    * 创建
    * @param scenCase
    * @return
    */
    String createApiSceneCase(@NotNull @Valid ApiSceneCase scenCase);

    /**
    * 更新
    * @param scenCase
    */
    void updateApiSceneCase(@NotNull @Valid ApiSceneCase scenCase);

    /**
    * 删除
    * @param id
    */
    void deleteApiSceneCase(@NotNull String id);

    @FindOne
    ApiSceneCase findOne(@NotNull String id);

    @FindList
    List<ApiSceneCase> findList(List<String> idList);

    /**
    * 查找
    * @param id
    * @return
    */
    ApiSceneCase findApiSceneCase(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    @FindAll
    List<ApiSceneCase> findAllApiSceneCase();

    /**
    * 查询列表
    * @param scenCaseQuery
    * @return
    */
    List<ApiSceneCase> findApiSceneCaseList(ApiSceneCaseQuery scenCaseQuery);

    /**
    * 按分页查询
    * @param scenCaseQuery
    * @return
    */
    Pagination<ApiSceneCase> findApiSceneCasePage(ApiSceneCaseQuery scenCaseQuery);

    /**
     * 通过testCaseQuery查询
     * @param testCaseQuery
     * @return
     */
    List<ApiSceneCase> findApiSceneCaseListByTestCase(TestCaseQuery testCaseQuery);


}