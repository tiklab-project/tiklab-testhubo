package net.tiklab.teston.repository.service;

import net.tiklab.core.page.Pagination;
import net.tiklab.join.annotation.FindAll;
import net.tiklab.join.annotation.FindList;
import net.tiklab.join.annotation.FindOne;
import net.tiklab.join.annotation.JoinProvider;
import net.tiklab.teston.repository.model.Repository;
import net.tiklab.teston.repository.model.RepositoryFollow;
import net.tiklab.teston.repository.model.RepositoryFollowQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* RepositoryFollowService
*/
@JoinProvider(model = RepositoryFollow.class)
public interface RepositoryFollowService {

    /**
    * 创建
    * @param repositoryFollow
    * @return
    */
    String createRepositoryFollow(@NotNull @Valid RepositoryFollow repositoryFollow);

    /**
    * 更新
    * @param repositoryFollow
    */
    void updateRepositoryFollow(@NotNull @Valid RepositoryFollow repositoryFollow);

    /**
    * 删除
    * @param id
    */
    void deleteRepositoryFollow(@NotNull String id);

    @FindOne
    RepositoryFollow findOne(@NotNull String id);

    @FindList
    List<RepositoryFollow> findList(List<String> idList);

    /**
    * 查找
    * @param id
    * @return
    */
    RepositoryFollow findRepositoryFollow(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    @FindAll
    List<RepositoryFollow> findAllRepositoryFollow();

    /**
    * 查询列表
    * @param repositoryFollowQuery
    * @return
    */
    List<Repository> findRepositoryFollowList(RepositoryFollowQuery repositoryFollowQuery);

    /**
    * 按分页查询
    * @param repositoryFollowQuery
    * @return
    */
    Pagination<RepositoryFollow> findRepositoryFollowPage(RepositoryFollowQuery repositoryFollowQuery);

}