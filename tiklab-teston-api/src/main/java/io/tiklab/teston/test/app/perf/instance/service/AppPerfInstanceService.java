package io.tiklab.teston.test.app.perf.instance.service;

import io.tiklab.teston.test.app.perf.instance.mode.AppPerfInstance;
import io.tiklab.core.page.Pagination;
import io.tiklab.teston.test.app.perf.instance.mode.AppPerfInstanceQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* app性能实例 服务接口
*/
public interface AppPerfInstanceService {

    /**
    * 创建app性能实例
    * @param appPerfInstance
    * @return
    */
    String createAppPerfInstance(@NotNull @Valid AppPerfInstance appPerfInstance);

    /**
    * 更新app性能实例
    * @param appPerfInstance
    */
    void updateAppPerfInstance(@NotNull @Valid AppPerfInstance appPerfInstance);

    /**
    * 删除app性能实例
    * @param id
    */
    void deleteAppPerfInstance(@NotNull String id);

    AppPerfInstance findOne(@NotNull String id);

    List<AppPerfInstance> findList(List<String> idList);

    /**
    * 根据id查找app性能实例
    * @param id
    * @return
    */
    AppPerfInstance findAppPerfInstance(@NotNull String id);

    /**
    * 查找所有app性能实例
    * @return
    */
    List<AppPerfInstance> findAllAppPerfInstance();

    /**
    * 根据查询参数查询app性能实例列表
    * @param appPerfInstanceQuery
    * @return
    */
    List<AppPerfInstance> findAppPerfInstanceList(AppPerfInstanceQuery appPerfInstanceQuery);

    /**
    * 根据查询参数按分页查询app性能实例
    * @param appPerfInstanceQuery
    * @return
    */
    Pagination<AppPerfInstance> findAppPerfInstancePage(AppPerfInstanceQuery appPerfInstanceQuery);

}