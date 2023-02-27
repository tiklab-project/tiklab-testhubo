package net.tiklab.teston.manager.support.environment.service;
import net.tiklab.core.page.Pagination;
import net.tiklab.join.annotation.FindAll;
import net.tiklab.join.annotation.FindList;
import net.tiklab.join.annotation.FindOne;
import net.tiklab.join.annotation.JoinProvider;
import net.tiklab.teston.manager.support.environment.model.AppEnv;
import net.tiklab.teston.manager.support.environment.model.AppEnvQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* AppEnvService
*/
@JoinProvider(model = AppEnv.class)
public interface AppEnvService {

    /**
    * 创建
    * @param appEnv
    * @return
    */
    String createAppEnv(@NotNull @Valid AppEnv appEnv);

    /**
    * 更新
    * @param appEnv
    */
    void updateAppEnv(@NotNull @Valid AppEnv appEnv);

    /**
    * 删除
    * @param id
    */
    void deleteAppEnv(@NotNull String id);

    @FindOne
    AppEnv findOne(@NotNull String id);

    @FindList
    List<AppEnv> findList(List<String> idList);

    /**
    * 查找
    * @param id
    * @return
    */
    AppEnv findAppEnv(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    @FindAll
    List<AppEnv> findAllAppEnv();

    /**
    * 查询列表
    * @param appEnvQuery
    * @return
    */
    List<AppEnv> findAppEnvList(AppEnvQuery appEnvQuery);

    /**
    * 按分页查询
    * @param appEnvQuery
    * @return
    */
    Pagination<AppEnv> findAppEnvPage(AppEnvQuery appEnvQuery);

}