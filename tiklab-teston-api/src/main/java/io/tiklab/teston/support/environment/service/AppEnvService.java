package io.tiklab.teston.support.environment.service;
import io.tiklab.core.page.Pagination;
import io.tiklab.join.annotation.FindAll;
import io.tiklab.join.annotation.FindList;
import io.tiklab.join.annotation.FindOne;
import io.tiklab.join.annotation.JoinProvider;
import io.tiklab.teston.support.environment.model.AppEnv;
import io.tiklab.teston.support.environment.model.AppEnvQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* app环境 服务接口
*/
@JoinProvider(model = AppEnv.class)
public interface AppEnvService {

    /**
    * 创建app环境
    * @param appEnv
    * @return
    */
    String createAppEnv(@NotNull @Valid AppEnv appEnv);

    /**
    * 更新app环境
    * @param appEnv
    */
    void updateAppEnv(@NotNull @Valid AppEnv appEnv);

    /**
    * 删除app环境
    * @param id
    */
    void deleteAppEnv(@NotNull String id);

    @FindOne
    AppEnv findOne(@NotNull String id);

    @FindList
    List<AppEnv> findList(List<String> idList);

    /**
    * 根据id查找app环境
    * @param id
    * @return
    */
    AppEnv findAppEnv(@NotNull String id);

    /**
    * 查找所有app环境
    * @return
    */
    @FindAll
    List<AppEnv> findAllAppEnv();

    /**
    * 根据查询参数查询app环境列表
    * @param appEnvQuery
    * @return
    */
    List<AppEnv> findAppEnvList(AppEnvQuery appEnvQuery);

    /**
    * 根据查询参数按分页查询app环境
    * @param appEnvQuery
    * @return
    */
    Pagination<AppEnv> findAppEnvPage(AppEnvQuery appEnvQuery);

}