package net.tiklab.testonapp.perf.cases.service;


import net.tiklab.core.page.Pagination;
import net.tiklab.join.annotation.FindAll;
import net.tiklab.join.annotation.FindList;
import net.tiklab.join.annotation.FindOne;
import net.tiklab.join.annotation.JoinProvider;
import net.tiklab.testonapp.perf.cases.model.AppPerfStep;
import net.tiklab.testonapp.perf.cases.model.AppPerfStepQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* AppPerfStepService
*/
@JoinProvider(model = AppPerfStep.class)
public interface AppPerfStepService {

    /**
    * 创建
    * @param appPerfStep
    * @return
    */
    String createAppPerfStep(@NotNull @Valid AppPerfStep appPerfStep);

    /**
    * 更新
    * @param appPerfStep
    */
    void updateAppPerfStep(@NotNull @Valid AppPerfStep appPerfStep);

    /**
    * 删除
    * @param id
    */
    void deleteAppPerfStep(@NotNull String id);

    @FindOne
    AppPerfStep findOne(@NotNull String id);

    @FindList
    List<AppPerfStep> findList(List<String> idList);

    /**
    * 查找
    * @param id
    * @return
    */
    AppPerfStep findAppPerfStep(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    @FindAll
    List<AppPerfStep> findAllAppPerfStep();

    /**
    * 查询列表
    * @param appPerfStepQuery
    * @return
    */
    List<AppPerfStep> findAppPerfStepList(AppPerfStepQuery appPerfStepQuery);

    /**
    * 按分页查询
    * @param appPerfStepQuery
    * @return
    */
    Pagination<AppPerfStep> findAppPerfStepPage(AppPerfStepQuery appPerfStepQuery);

    /**
     * 绑定web场景
     * @param appPerfStepList
     */
    void bindAppScene(List<AppPerfStep> appPerfStepList);


}