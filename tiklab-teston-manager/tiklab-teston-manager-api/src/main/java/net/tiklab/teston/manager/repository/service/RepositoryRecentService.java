package net.tiklab.teston.manager.repository.service;


import net.tiklab.core.page.Pagination;
import net.tiklab.join.annotation.FindAll;
import net.tiklab.join.annotation.FindList;
import net.tiklab.join.annotation.FindOne;
import net.tiklab.join.annotation.JoinProvider;
import net.tiklab.teston.manager.repository.model.Repository;
import net.tiklab.teston.manager.repository.model.RepositoryRecent;
import net.tiklab.teston.manager.repository.model.RepositoryRecentQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* RepositoryRecentService
*/
@JoinProvider(model = RepositoryRecent.class)
public interface RepositoryRecentService {

    /**
    * 创建
    * @param repositoryRecent
    * @return
    */
    String createRepositoryRecent(@NotNull @Valid RepositoryRecent repositoryRecent);

    /**
    * 更新
    * @param repositoryRecent
    */
    void updateRepositoryRecent(@NotNull @Valid RepositoryRecent repositoryRecent);

    /**
    * 删除
    * @param id
    */
    void deleteRepositoryRecent(@NotNull String id);

    @FindOne
    RepositoryRecent findOne(@NotNull String id);

    @FindList
    List<RepositoryRecent> findList(List<String> idList);

    /**
    * 查找
    * @param id
    * @return
    */
    RepositoryRecent findRepositoryRecent(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    @FindAll
    List<RepositoryRecent> findAllRepositoryRecent();

    /**
    * 查询列表
    * @param repositoryRecentQuery
    * @return
    */
    List<Repository> findRepositoryRecentList(RepositoryRecentQuery repositoryRecentQuery);

    /**
     * 查询列表
     * @param repositoryRecentQuery
     * @return
     */
    List<RepositoryRecent> findRecentList(RepositoryRecentQuery repositoryRecentQuery);


    /**
    * 按分页查询
    * @param repositoryRecentQuery
    * @return
    */
    Pagination<RepositoryRecent> findRepositoryRecentPage(RepositoryRecentQuery repositoryRecentQuery);

    /**
     * 删除
     * @param repositoryRecent
     */
    void repositoryRecent(@NotNull @Valid RepositoryRecent repositoryRecent);

}