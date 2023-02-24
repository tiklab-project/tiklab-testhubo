package net.tiklab.teston.test.appscene.cases.service;

import net.tiklab.core.page.Pagination;
import net.tiklab.join.annotation.FindAll;
import net.tiklab.join.annotation.FindList;
import net.tiklab.join.annotation.FindOne;
import net.tiklab.join.annotation.JoinProvider;
import net.tiklab.teston.test.appscene.cases.model.AppSceneStep;
import net.tiklab.teston.test.appscene.cases.model.AppSceneStepQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* AppSceneStepService
*/
@JoinProvider(model = AppSceneStep.class)
public interface AppSceneStepService {

    /**
    * 创建
    * @param appSceneStep
    * @return
    */
    String createAppSceneStep(@NotNull @Valid AppSceneStep appSceneStep);

    /**
    * 更新
    * @param appSceneStep
    */
    void updateAppSceneStep(@NotNull @Valid AppSceneStep appSceneStep);

    /**
    * 删除
    * @param id
    */
    void deleteAppSceneStep(@NotNull String id);

    @FindOne
    AppSceneStep findOne(@NotNull String id);

    @FindList
    List<AppSceneStep> findList(List<String> idList);

    /**
    * 查找
    * @param id
    * @return
    */
    AppSceneStep findAppSceneStep(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    @FindAll
    List<AppSceneStep> findAllAppSceneStep();

    /**
    * 查询列表
    * @param appSceneStepQuery
    * @return
    */
    List<AppSceneStep> findAppSceneStepList(AppSceneStepQuery appSceneStepQuery);

    /**
    * 按分页查询
    * @param appSceneStepQuery
    * @return
    */
    Pagination<AppSceneStep> findAppSceneStepPage(AppSceneStepQuery appSceneStepQuery);


    /**
     * 绑定webUnitCase
     * @param appSceneStepList
     */
    void bindAppUnit(List<AppSceneStep> appSceneStepList);

}