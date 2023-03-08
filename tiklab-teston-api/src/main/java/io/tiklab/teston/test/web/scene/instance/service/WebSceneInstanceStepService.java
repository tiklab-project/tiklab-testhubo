package io.tiklab.teston.test.web.scene.instance.service;


import io.tiklab.core.page.Pagination;
import io.tiklab.join.annotation.FindAll;
import io.tiklab.join.annotation.FindList;
import io.tiklab.join.annotation.FindOne;
import io.tiklab.join.annotation.JoinProvider;
import io.tiklab.teston.test.web.scene.instance.model.WebSceneInstanceStep;
import io.tiklab.teston.test.web.scene.instance.model.WebSceneInstanceStepQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* web场景下步骤实例 服务接口
*/
@JoinProvider(model = WebSceneInstanceStep.class)
public interface WebSceneInstanceStepService {

    /**
    * 创建web场景下步骤实例
    * @param webSceneInstanceStep
    * @return
    */
    String createWebSceneInstanceStep(@NotNull @Valid WebSceneInstanceStep webSceneInstanceStep);

    /**
    * 更新web场景下步骤实例
    * @param webSceneInstanceStep
    */
    void updateWebSceneInstanceStep(@NotNull @Valid WebSceneInstanceStep webSceneInstanceStep);

    /**
    * 删除web场景下步骤实例
    * @param id
    */
    void deleteWebSceneInstanceStep(@NotNull String id);

    @FindOne
    WebSceneInstanceStep findOne(@NotNull String id);

    @FindList
    List<WebSceneInstanceStep> findList(List<String> idList);

    /**
    * 根据id查找web场景下步骤实例
    * @param id
    * @return
    */
    WebSceneInstanceStep findWebSceneInstanceStep(@NotNull String id);

    /**
    * 查找所有web场景下步骤实例
    * @return
    */
    @FindAll
    List<WebSceneInstanceStep> findAllWebSceneInstanceStep();

    /**
    * 根据查询参数查询web场景下步骤实例列表
    * @param webSceneInstanceStepQuery
    * @return
    */
    List<WebSceneInstanceStep> findWebSceneInstanceStepList(WebSceneInstanceStepQuery webSceneInstanceStepQuery);

    /**
    * 根据查询参数按分页查询web场景下步骤实例
    * @param webSceneInstanceStepQuery
    * @return
    */
    Pagination<WebSceneInstanceStep> findWebSceneInstanceStepPage(WebSceneInstanceStepQuery webSceneInstanceStepQuery);

}