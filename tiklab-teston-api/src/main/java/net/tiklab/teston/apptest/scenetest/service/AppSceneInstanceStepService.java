package net.tiklab.teston.apptest.scenetest.service;

import net.tiklab.core.page.Pagination;
import net.tiklab.join.annotation.FindAll;
import net.tiklab.join.annotation.FindList;
import net.tiklab.join.annotation.FindOne;
import net.tiklab.join.annotation.JoinProvider;
import net.tiklab.teston.apptest.scenetest.model.AppSceneInstanceStep;
import net.tiklab.teston.apptest.scenetest.model.AppSceneInstanceStepQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* AppSceneInstanceStepService
*/
@JoinProvider(model = AppSceneInstanceStep.class)
public interface AppSceneInstanceStepService {

    /**
    * 创建
    * @param appSceneInstanceStep
    * @return
    */
    String createAppSceneInstanceStep(@NotNull @Valid AppSceneInstanceStep appSceneInstanceStep);

    /**
    * 更新
    * @param appSceneInstanceStep
    */
    void updateAppSceneInstanceStep(@NotNull @Valid AppSceneInstanceStep appSceneInstanceStep);

    /**
    * 删除
    * @param id
    */
    void deleteAppSceneInstanceStep(@NotNull String id);

    @FindOne
    AppSceneInstanceStep findOne(@NotNull String id);

    @FindList
    List<AppSceneInstanceStep> findList(List<String> idList);

    /**
    * 查找
    * @param id
    * @return
    */
    AppSceneInstanceStep findAppSceneInstanceStep(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    @FindAll
    List<AppSceneInstanceStep> findAllAppSceneInstanceStep();

    /**
    * 查询列表
    * @param appSceneInstanceStepQuery
    * @return
    */
    List<AppSceneInstanceStep> findAppSceneInstanceStepList(AppSceneInstanceStepQuery appSceneInstanceStepQuery);

    /**
    * 按分页查询
    * @param appSceneInstanceStepQuery
    * @return
    */
    Pagination<AppSceneInstanceStep> findAppSceneInstanceStepPage(AppSceneInstanceStepQuery appSceneInstanceStepQuery);

}