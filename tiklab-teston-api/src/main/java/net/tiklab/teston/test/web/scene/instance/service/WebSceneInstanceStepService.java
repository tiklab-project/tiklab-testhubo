package net.tiklab.teston.test.web.scene.instance.service;


import net.tiklab.core.page.Pagination;
import net.tiklab.join.annotation.FindAll;
import net.tiklab.join.annotation.FindList;
import net.tiklab.join.annotation.FindOne;
import net.tiklab.join.annotation.JoinProvider;
import net.tiklab.teston.test.web.scene.instance.model.WebSceneInstanceStep;
import net.tiklab.teston.test.web.scene.instance.model.WebSceneInstanceStepQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* WebSceneInstanceStepService
*/
@JoinProvider(model = WebSceneInstanceStep.class)
public interface WebSceneInstanceStepService {

    /**
    * 创建
    * @param webSceneInstanceStep
    * @return
    */
    String createWebSceneInstanceStep(@NotNull @Valid WebSceneInstanceStep webSceneInstanceStep);

    /**
    * 更新
    * @param webSceneInstanceStep
    */
    void updateWebSceneInstanceStep(@NotNull @Valid WebSceneInstanceStep webSceneInstanceStep);

    /**
    * 删除
    * @param id
    */
    void deleteWebSceneInstanceStep(@NotNull String id);

    @FindOne
    WebSceneInstanceStep findOne(@NotNull String id);

    @FindList
    List<WebSceneInstanceStep> findList(List<String> idList);

    /**
    * 查找
    * @param id
    * @return
    */
    WebSceneInstanceStep findWebSceneInstanceStep(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    @FindAll
    List<WebSceneInstanceStep> findAllWebSceneInstanceStep();

    /**
    * 查询列表
    * @param webSceneInstanceStepQuery
    * @return
    */
    List<WebSceneInstanceStep> findWebSceneInstanceStepList(WebSceneInstanceStepQuery webSceneInstanceStepQuery);

    /**
    * 按分页查询
    * @param webSceneInstanceStepQuery
    * @return
    */
    Pagination<WebSceneInstanceStep> findWebSceneInstanceStepPage(WebSceneInstanceStepQuery webSceneInstanceStepQuery);

}