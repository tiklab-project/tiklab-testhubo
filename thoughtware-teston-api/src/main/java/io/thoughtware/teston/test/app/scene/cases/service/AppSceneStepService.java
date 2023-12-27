package io.thoughtware.teston.test.app.scene.cases.service;

import io.thoughtware.teston.test.app.scene.cases.model.AppSceneStep;
import io.thoughtware.teston.test.app.scene.cases.model.AppSceneStepQuery;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.toolkit.join.annotation.FindAll;
import io.thoughtware.toolkit.join.annotation.FindList;
import io.thoughtware.toolkit.join.annotation.FindOne;
import io.thoughtware.toolkit.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* app场景下步骤 服务接口
*/
@JoinProvider(model = AppSceneStep.class)
public interface AppSceneStepService {

    /**
    * 创建场景步骤
    * @param appSceneStep
    * @return
    */
    String createAppSceneStep(@NotNull @Valid AppSceneStep appSceneStep);

    /**
    * 更新场景步骤
    * @param appSceneStep
    */
    void updateAppSceneStep(@NotNull @Valid AppSceneStep appSceneStep);

    /**
    * 删除场景步骤
    * @param id
    */
    void deleteAppSceneStep(@NotNull String id);

    @FindOne
    AppSceneStep findOne(@NotNull String id);

    @FindList
    List<AppSceneStep> findList(List<String> idList);

    /**
    * 根据id查找场景步骤
    * @param id
    * @return
    */
    AppSceneStep findAppSceneStep(@NotNull String id);

    /**
    * 查找所有场景步骤
    * @return
    */
    @FindAll
    List<AppSceneStep> findAllAppSceneStep();

    /**
    * 根据查询参数查询场景步骤列表
    * @param appSceneStepQuery
    * @return
    */
    List<AppSceneStep> findAppSceneStepList(AppSceneStepQuery appSceneStepQuery);

    /**
    * 根据查询参数按分页查询场景步骤
    * @param appSceneStepQuery
    * @return
    */
    Pagination<AppSceneStep> findAppSceneStepPage(AppSceneStepQuery appSceneStepQuery);


}