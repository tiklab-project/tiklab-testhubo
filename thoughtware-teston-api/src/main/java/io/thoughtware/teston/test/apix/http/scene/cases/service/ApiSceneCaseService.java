package io.thoughtware.teston.test.apix.http.scene.cases.service;

import io.thoughtware.teston.test.test.model.TestCaseQuery;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.join.annotation.FindAll;
import io.thoughtware.join.annotation.FindList;
import io.thoughtware.join.annotation.FindOne;
import io.thoughtware.join.annotation.JoinProvider;
import io.thoughtware.teston.test.apix.http.scene.cases.model.ApiSceneCaseQuery;
import io.thoughtware.teston.test.apix.http.scene.cases.model.ApiSceneCase;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 接口场景 服务接口
*/
@JoinProvider(model = ApiSceneCase.class)
public interface ApiSceneCaseService {

    /**
    * 创建接口场景
    * @param scenCase
    * @return
    */
    String createApiSceneCase(@NotNull @Valid ApiSceneCase scenCase);

    /**
    * 更新接口场景
    * @param scenCase
    */
    void updateApiSceneCase(@NotNull @Valid ApiSceneCase scenCase);

    /**
    * 删除接口场景
    * @param id
    */
    void deleteApiSceneCase(@NotNull String id);

    @FindOne
    ApiSceneCase findOne(@NotNull String id);

    @FindList
    List<ApiSceneCase> findList(List<String> idList);

    /**
    * 根据id查找接口场景
    * @param id
    * @return
    */
    ApiSceneCase findApiSceneCase(@NotNull String id);

    /**
    * 查找所有接口场景
    * @return
    */
    @FindAll
    List<ApiSceneCase> findAllApiSceneCase();

    /**
    * 根据查询参数查询接口场景列表
    * @param scenCaseQuery
    * @return
    */
    List<ApiSceneCase> findApiSceneCaseList(ApiSceneCaseQuery scenCaseQuery);

    /**
    * 根据查询参数按分页查询接口场景列表
    * @param scenCaseQuery
    * @return
    */
    Pagination<ApiSceneCase> findApiSceneCasePage(ApiSceneCaseQuery scenCaseQuery);

    /**
     * 根据测试用例查询参数查找接口场景列表
     * @param testCaseQuery
     * @return
     */
    List<ApiSceneCase> findApiSceneCaseListByTestCase(TestCaseQuery testCaseQuery);


}