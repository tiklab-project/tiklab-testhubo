package net.tiklab.teston.manager.support.environment.service;

import net.tiklab.core.page.Pagination;

import net.tiklab.join.annotation.FindAll;
import net.tiklab.join.annotation.FindList;
import net.tiklab.join.annotation.FindOne;
import net.tiklab.join.annotation.JoinProvider;
import net.tiklab.teston.manager.support.environment.model.ApiEnv;
import net.tiklab.teston.manager.support.environment.model.ApiEnvQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 接口环境 服务接口
*/
@JoinProvider(model = ApiEnv.class)
public interface ApiEnvService {

    /**
    * 创建接口环境
    * @param apiEnv
    * @return
    */
    String createApiEnv(@NotNull @Valid ApiEnv apiEnv);

    /**
    * 更新接口环境
    * @param apiEnv
    */
    void updateApiEnv(@NotNull @Valid ApiEnv apiEnv);

    /**
    * 删除接口环境
    * @param id
    */
    void deleteApiEnv(@NotNull String id);
    @FindOne
    ApiEnv findOne(@NotNull String id);
    @FindList
    List<ApiEnv> findList(List<String> idList);

    /**
    * 根据id查找接口环境
    * @param id
    * @return
    */
    ApiEnv findApiEnv(@NotNull String id);

    /**
    * 查找所有接口环境
    * @return
    */
    @FindAll
    List<ApiEnv> findAllApiEnv();

    /**
    * 根据查询参数查询接口环境列表
    * @param apiEnvQuery
    * @return
    */
    List<ApiEnv> findApiEnvList(ApiEnvQuery apiEnvQuery);

    /**
    * 根据查询参数按分页查询接口环境
    * @param apiEnvQuery
    * @return
    */
    Pagination<ApiEnv> findApiEnvPage(ApiEnvQuery apiEnvQuery);

}