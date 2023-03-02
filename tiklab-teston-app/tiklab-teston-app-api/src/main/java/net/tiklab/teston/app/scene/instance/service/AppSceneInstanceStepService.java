package net.tiklab.teston.app.scene.instance.service;

import net.tiklab.core.page.Pagination;
import net.tiklab.join.annotation.FindAll;
import net.tiklab.join.annotation.FindList;
import net.tiklab.join.annotation.FindOne;
import net.tiklab.join.annotation.JoinProvider;
import net.tiklab.teston.app.scene.instance.model.AppSceneInstanceStep;
import net.tiklab.teston.app.scene.instance.model.AppSceneInstanceStepQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* app场景步骤实例 服务接口
*/
@JoinProvider(model = AppSceneInstanceStep.class)
public interface AppSceneInstanceStepService {

    /**
    * 创建app场景步骤实例
    * @param appSceneInstanceStep
    * @return
    */
    String createAppSceneInstanceStep(@NotNull @Valid AppSceneInstanceStep appSceneInstanceStep);

    /**
    * 更新app场景步骤实例
    * @param appSceneInstanceStep
    */
    void updateAppSceneInstanceStep(@NotNull @Valid AppSceneInstanceStep appSceneInstanceStep);

    /**
    * 删除app场景步骤实例
    * @param id
    */
    void deleteAppSceneInstanceStep(@NotNull String id);

    @FindOne
    AppSceneInstanceStep findOne(@NotNull String id);

    @FindList
    List<AppSceneInstanceStep> findList(List<String> idList);

    /**
    * 根据id查找app场景步骤实例
    * @param id
    * @return
    */
    AppSceneInstanceStep findAppSceneInstanceStep(@NotNull String id);

    /**
    * 根据查询参数查找所有app场景步骤实例
    * @return
    */
    @FindAll
    List<AppSceneInstanceStep> findAllAppSceneInstanceStep();

    /**
    * 根据查询参数查询app场景步骤实例列表
    * @param appSceneInstanceStepQuery
    * @return
    */
    List<AppSceneInstanceStep> findAppSceneInstanceStepList(AppSceneInstanceStepQuery appSceneInstanceStepQuery);

    /**
    * 根据查询参数按分页查询app场景步骤实例
    * @param appSceneInstanceStepQuery
    * @return
    */
    Pagination<AppSceneInstanceStep> findAppSceneInstanceStepPage(AppSceneInstanceStepQuery appSceneInstanceStepQuery);

}