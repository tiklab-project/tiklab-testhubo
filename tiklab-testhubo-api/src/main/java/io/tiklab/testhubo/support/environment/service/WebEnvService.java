package io.tiklab.testhubo.support.environment.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.toolkit.join.annotation.FindAll;
import io.tiklab.toolkit.join.annotation.FindList;
import io.tiklab.toolkit.join.annotation.FindOne;
import io.tiklab.toolkit.join.annotation.JoinProvider;
import io.tiklab.testhubo.support.environment.model.WebEnv;
import io.tiklab.testhubo.support.environment.model.WebEnvQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* Web环境 服务接口
*/
@JoinProvider(model = WebEnv.class)
public interface WebEnvService {

    /**
    * 创建Web环境
    * @param webEnv
    * @return
    */
    String createWebEnv(@NotNull @Valid WebEnv webEnv);

    /**
    * 更新Web环境
    * @param webEnv
    */
    void updateWebEnv(@NotNull @Valid WebEnv webEnv);

    /**
    * 删除Web环境
    * @param id
    */
    void deleteWebEnv(@NotNull String id);

    void deleteAllWebEnv(String repositoryId);

    @FindOne
    WebEnv findOne(@NotNull String id);

    @FindList
    List<WebEnv> findList(List<String> idList);

    /**
    * 根据id查找Web环境
    * @param id
    * @return
    */
    WebEnv findWebEnv(@NotNull String id);

    /**
    * 查找所有Web环境
    * @return
    */
    @FindAll
    List<WebEnv> findAllWebEnv();

    /**
    * 根据查询参数查询Web环境列表
    * @param webEnvQuery
    * @return
    */
    List<WebEnv> findWebEnvList(WebEnvQuery webEnvQuery);

    /**
    * 根据查询参数按分页查询Web环境
    * @param webEnvQuery
    * @return
    */
    Pagination<WebEnv> findWebEnvPage(WebEnvQuery webEnvQuery);

}