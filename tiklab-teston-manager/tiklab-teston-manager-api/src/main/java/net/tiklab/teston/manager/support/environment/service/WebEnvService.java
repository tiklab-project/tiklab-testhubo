package net.tiklab.teston.manager.support.environment.service;

import net.tiklab.core.page.Pagination;
import net.tiklab.join.annotation.FindAll;
import net.tiklab.join.annotation.FindList;
import net.tiklab.join.annotation.FindOne;
import net.tiklab.join.annotation.JoinProvider;
import net.tiklab.teston.manager.support.environment.model.WebEnv;
import net.tiklab.teston.manager.support.environment.model.WebEnvQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* WebEnvService
*/
@JoinProvider(model = WebEnv.class)
public interface WebEnvService {

    /**
    * 创建
    * @param webEnv
    * @return
    */
    String createWebEnv(@NotNull @Valid WebEnv webEnv);

    /**
    * 更新
    * @param webEnv
    */
    void updateWebEnv(@NotNull @Valid WebEnv webEnv);

    /**
    * 删除
    * @param id
    */
    void deleteWebEnv(@NotNull String id);

    @FindOne
    WebEnv findOne(@NotNull String id);

    @FindList
    List<WebEnv> findList(List<String> idList);

    /**
    * 查找
    * @param id
    * @return
    */
    WebEnv findWebEnv(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    @FindAll
    List<WebEnv> findAllWebEnv();

    /**
    * 查询列表
    * @param webEnvQuery
    * @return
    */
    List<WebEnv> findWebEnvList(WebEnvQuery webEnvQuery);

    /**
    * 按分页查询
    * @param webEnvQuery
    * @return
    */
    Pagination<WebEnv> findWebEnvPage(WebEnvQuery webEnvQuery);

}