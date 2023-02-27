package net.tiklab.teston.web.scene.cases.service;

import net.tiklab.core.page.Pagination;
import net.tiklab.join.annotation.FindAll;
import net.tiklab.join.annotation.FindList;
import net.tiklab.join.annotation.FindOne;
import net.tiklab.join.annotation.JoinProvider;
import net.tiklab.teston.web.scene.cases.model.WebSceneStep;
import net.tiklab.teston.web.scene.cases.model.WebSceneStepQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* WebSceneStepService
*/
@JoinProvider(model = WebSceneStep.class)
public interface WebSceneStepService {

    /**
    * 创建
    * @param webSceneStep
    * @return
    */
    String createWebSceneStep(@NotNull @Valid WebSceneStep webSceneStep);

    /**
    * 更新
    * @param webSceneStep
    */
    void updateWebSceneStep(@NotNull @Valid WebSceneStep webSceneStep);

    /**
    * 删除
    * @param id
    */
    void deleteWebSceneStep(@NotNull String id);

    @FindOne
    WebSceneStep findOne(@NotNull String id);

    @FindList
    List<WebSceneStep> findList(List<String> idList);

    /**
    * 查找
    * @param id
    * @return
    */
    WebSceneStep findWebSceneStep(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    @FindAll
    List<WebSceneStep> findAllWebSceneStep();

    /**
    * 查询列表
    * @param webSceneStepQuery
    * @return
    */
    List<WebSceneStep> findWebSceneStepList(WebSceneStepQuery webSceneStepQuery);

    /**
    * 按分页查询
    * @param webSceneStepQuery
    * @return
    */
    Pagination<WebSceneStep> findWebSceneStepPage(WebSceneStepQuery webSceneStepQuery);

    /**
     * 绑定webUnitCase
     * @param webSceneStepList
     */
    void bindWebUnit(List<WebSceneStep> webSceneStepList);

}