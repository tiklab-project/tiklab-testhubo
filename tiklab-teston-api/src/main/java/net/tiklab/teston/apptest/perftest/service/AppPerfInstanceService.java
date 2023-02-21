package net.tiklab.teston.apptest.perftest.service;

import net.tiklab.core.page.Pagination;
import net.tiklab.teston.apptest.perftest.model.AppPerfInstance;
import net.tiklab.teston.apptest.perftest.model.AppPerfInstanceQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* AppPerfInstanceService
*/
public interface AppPerfInstanceService {

    /**
    * 创建
    * @param appPerfInstance
    * @return
    */
    String createAppPerfInstance(@NotNull @Valid AppPerfInstance appPerfInstance);

    /**
    * 更新
    * @param appPerfInstance
    */
    void updateAppPerfInstance(@NotNull @Valid AppPerfInstance appPerfInstance);

    /**
    * 删除
    * @param id
    */
    void deleteAppPerfInstance(@NotNull String id);

    AppPerfInstance findOne(@NotNull String id);

    List<AppPerfInstance> findList(List<String> idList);

    /**
    * 查找
    * @param id
    * @return
    */
    AppPerfInstance findAppPerfInstance(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<AppPerfInstance> findAllAppPerfInstance();

    /**
    * 查询列表
    * @param appPerfInstanceQuery
    * @return
    */
    List<AppPerfInstance> findAppPerfInstanceList(AppPerfInstanceQuery appPerfInstanceQuery);

    /**
    * 按分页查询
    * @param appPerfInstanceQuery
    * @return
    */
    Pagination<AppPerfInstance> findAppPerfInstancePage(AppPerfInstanceQuery appPerfInstanceQuery);

}