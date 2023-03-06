package net.tiklab.teston.repository.service;


import net.tiklab.core.page.Pagination;
import net.tiklab.join.annotation.FindAll;
import net.tiklab.join.annotation.FindList;
import net.tiklab.join.annotation.FindOne;
import net.tiklab.join.annotation.JoinProvider;
import net.tiklab.teston.repository.model.Repository;
import net.tiklab.teston.repository.model.RepositoryRecent;
import net.tiklab.teston.repository.model.RepositoryRecentQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 最近访问仓库 服务接口
*/
@JoinProvider(model = RepositoryRecent.class)
public interface RepositoryRecentService {

    /**
    * 创建最近访问仓库
    * @param repositoryRecent
    * @return
    */
    String createRepositoryRecent(@NotNull @Valid RepositoryRecent repositoryRecent);

    /**
    * 更新最近访问仓库
    * @param repositoryRecent
    */
    void updateRepositoryRecent(@NotNull @Valid RepositoryRecent repositoryRecent);

    /**
    * 删除最近访问仓库
    * @param id
    */
    void deleteRepositoryRecent(@NotNull String id);

    @FindOne
    RepositoryRecent findOne(@NotNull String id);

    @FindList
    List<RepositoryRecent> findList(List<String> idList);

    /**
    * 查找最近访问仓库
    * @param id
    * @return
    */
    RepositoryRecent findRepositoryRecent(@NotNull String id);

    /**
    * 查找所有最近访问仓库
    * @return
    */
    @FindAll
    List<RepositoryRecent> findAllRepositoryRecent();

    /**
    * 查询最近访问仓库列表
    * @param repositoryRecentQuery
    * @return
    */
    List<Repository> findRepositoryRecentList(RepositoryRecentQuery repositoryRecentQuery);

    /**
     * 查询最近访问仓库列表
     * @param repositoryRecentQuery
     * @return
     */
    List<RepositoryRecent> findRecentList(RepositoryRecentQuery repositoryRecentQuery);


    /**
    * 按分页查询最近访问仓库
    * @param repositoryRecentQuery
    * @return
    */
    Pagination<RepositoryRecent> findRepositoryRecentPage(RepositoryRecentQuery repositoryRecentQuery);

    /**
     * 设置最近访问仓库
     * @param repositoryRecent
     */
    void repositoryRecent(@NotNull @Valid RepositoryRecent repositoryRecent);

}