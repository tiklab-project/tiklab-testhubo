package io.thoughtware.teston.test.web.scene.cases.service;

import io.thoughtware.teston.test.web.scene.cases.model.WebSceneStep;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.toolkit.join.annotation.FindAll;
import io.thoughtware.toolkit.join.annotation.FindList;
import io.thoughtware.toolkit.join.annotation.FindOne;
import io.thoughtware.toolkit.join.annotation.JoinProvider;
import io.thoughtware.teston.test.web.scene.cases.model.WebSceneStepQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* web场景下步骤 服务接口
*/
@JoinProvider(model = WebSceneStep.class)
public interface WebSceneStepService {

    /**
    * 创建web场景下步骤
    * @param webSceneStep
    * @return
    */
    String createWebSceneStep(@NotNull @Valid WebSceneStep webSceneStep);

    /**
    * 更新web场景下步骤
    * @param webSceneStep
    */
    void updateWebSceneStep(@NotNull @Valid WebSceneStep webSceneStep);

    /**
    * 删除web场景下步骤
    * @param id
    */
    void deleteWebSceneStep(@NotNull String id);

    @FindOne
    WebSceneStep findOne(@NotNull String id);

    @FindList
    List<WebSceneStep> findList(List<String> idList);

    /**
    * 根据id查找web场景下步骤
    * @param id
    * @return
    */
    WebSceneStep findWebSceneStep(@NotNull String id);

    /**
    * 查找所有web场景下步骤
    * @return
    */
    @FindAll
    List<WebSceneStep> findAllWebSceneStep();

    /**
    * 根据查询参数查询web场景下步骤列表
    * @param webSceneStepQuery
    * @return
    */
    List<WebSceneStep> findWebSceneStepList(WebSceneStepQuery webSceneStepQuery);

    /**
    * 根据查询参数按分页查询web场景下步骤
    * @param webSceneStepQuery
    * @return
    */
    Pagination<WebSceneStep> findWebSceneStepPage(WebSceneStepQuery webSceneStepQuery);


}