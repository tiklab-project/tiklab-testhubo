package net.tiklab.teston.apitest.http.scenetest.service;

import net.tiklab.core.page.Pagination;

import net.tiklab.join.annotation.FindAll;
import net.tiklab.join.annotation.FindList;
import net.tiklab.join.annotation.FindOne;
import net.tiklab.join.annotation.JoinProvider;
import net.tiklab.teston.apitest.http.scenetest.model.ApiSceneStep;
import net.tiklab.teston.apitest.http.scenetest.model.ApiSceneStepQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* ApiSceneStepService
*/
@JoinProvider(model = ApiSceneStep.class)
public interface ApiSceneStepService {

    /**
    * 创建
    * @param apiSceneStep
    * @return
    */
    String createApiSceneStep(@NotNull @Valid ApiSceneStep apiSceneStep);

    /**
    * 更新
    * @param apiSceneStep
    */
    void updateApiSceneStep(@NotNull @Valid ApiSceneStep apiSceneStep);

    /**
    * 删除
    * @param id
    */
    void deleteApiSceneStep(@NotNull String id);

    @FindOne
    ApiSceneStep findOne(@NotNull String id);

    @FindList
    List<ApiSceneStep> findList(List<String> idList);

    /**
    * 查找
    * @param id
    * @return
    */
    ApiSceneStep findApiSceneStep(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    @FindAll
    List<ApiSceneStep> findAllApiSceneStep();

    /**
    * 查询列表
    * @param apiSceneStepQuery
    * @return
    */
    List<ApiSceneStep> findApiSceneStepList(ApiSceneStepQuery apiSceneStepQuery);

    /**
    * 按分页查询
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

}