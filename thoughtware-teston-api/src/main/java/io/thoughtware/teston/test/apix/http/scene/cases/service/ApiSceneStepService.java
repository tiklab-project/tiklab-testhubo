package io.thoughtware.teston.test.apix.http.scene.cases.service;

import io.thoughtware.core.page.Pagination;
import io.thoughtware.teston.test.apix.http.scene.cases.model.ApiSceneStepWillBindCase;
import io.thoughtware.toolkit.join.annotation.FindAll;
import io.thoughtware.toolkit.join.annotation.FindList;
import io.thoughtware.toolkit.join.annotation.FindOne;
import io.thoughtware.toolkit.join.annotation.JoinProvider;
import io.thoughtware.teston.test.apix.http.scene.cases.model.ApiSceneStep;
import io.thoughtware.teston.test.apix.http.scene.cases.model.ApiSceneStepQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 接口场景下步骤 服务接口
*/
@JoinProvider(model = ApiSceneStep.class)
public interface ApiSceneStepService {

    /**
    * 创建步骤
    * @param apiSceneStep
    * @return
    */
    String createApiSceneStep(@NotNull @Valid ApiSceneStep apiSceneStep);

    /**
    * 更新步骤
    * @param apiSceneStep
    */
    void updateApiSceneStep(@NotNull @Valid ApiSceneStep apiSceneStep);

    /**
    * 删除步骤
    * @param id
    */
    void deleteApiSceneStep(@NotNull String id);

    @FindOne
    ApiSceneStep findOne(@NotNull String id);

    @FindList
    List<ApiSceneStep> findList(List<String> idList);

    /**
    * 根据id查找步骤
    * @param id
    * @return
    */
    ApiSceneStep findApiSceneStep(@NotNull String id);

    /**
    * 查找所有步骤
    * @return
    */
    @FindAll
    List<ApiSceneStep> findAllApiSceneStep();

    /**
    * 根据查询参数查找查询步骤列表
    * @param apiSceneStepQuery
    * @return
    */
    List<ApiSceneStep> findApiSceneStepList(ApiSceneStepQuery apiSceneStepQuery);

    /**
    * 根据查询参数查找按分页查询步骤
    * @param apiSceneStepQuery
    * @return
    */
    Pagination<ApiSceneStep> findApiSceneStepPage(ApiSceneStepQuery apiSceneStepQuery);


    /**
     * 绑定apiUnit
     * @param apiSceneStepList
     * @return
     */
    void bindApiUnit(List<ApiSceneStep> apiSceneStepList);

    /**
     * 查询接口场景步骤，将要关联的接口单元用例
     * @param apiSceneStepQuery
     * @return
     */
    Pagination<ApiSceneStepWillBindCase> findApiSceneStepWillBindCasePage(ApiSceneStepQuery apiSceneStepQuery);

}