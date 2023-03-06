package net.tiklab.teston.test.app.perf.cases.service;


import net.tiklab.core.page.Pagination;
import net.tiklab.join.annotation.FindAll;
import net.tiklab.join.annotation.FindList;
import net.tiklab.join.annotation.FindOne;
import net.tiklab.join.annotation.JoinProvider;
import net.tiklab.teston.test.app.perf.cases.model.AppPerfStep;
import net.tiklab.teston.test.app.perf.cases.model.AppPerfStepQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* app性能测试用例下绑定的步骤 服务接口
*/
@JoinProvider(model = AppPerfStep.class)
public interface AppPerfStepService {

    /**
    * 创建绑定的步骤
    * @param appPerfStep
    * @return
    */
    String createAppPerfStep(@NotNull @Valid AppPerfStep appPerfStep);

    /**
    * 更新绑定的步骤
    * @param appPerfStep
    */
    void updateAppPerfStep(@NotNull @Valid AppPerfStep appPerfStep);

    /**
    * 删除绑定的步骤
    * @param id
    */
    void deleteAppPerfStep(@NotNull String id);

    @FindOne
    AppPerfStep findOne(@NotNull String id);

    @FindList
    List<AppPerfStep> findList(List<String> idList);

    /**
    * 根据id查找绑定的步骤
    * @param id
    * @return
    */
    AppPerfStep findAppPerfStep(@NotNull String id);

    /**
    * 查找所有绑定的步骤
    * @return
    */
    @FindAll
    List<AppPerfStep> findAllAppPerfStep();

    /**
    * 根据查询参数查询绑定的步骤 列表
    * @param appPerfStepQuery
    * @return
    */
    List<AppPerfStep> findAppPerfStepList(AppPerfStepQuery appPerfStepQuery);

    /**
    * 根据查询参数按分页查询绑定的步骤
    * @param appPerfStepQuery
    * @return
    */
    Pagination<AppPerfStep> findAppPerfStepPage(AppPerfStepQuery appPerfStepQuery);

    /**
     * 绑定app场景
     * @param appPerfStepList
     */
    void bindAppScene(List<AppPerfStep> appPerfStepList);


}