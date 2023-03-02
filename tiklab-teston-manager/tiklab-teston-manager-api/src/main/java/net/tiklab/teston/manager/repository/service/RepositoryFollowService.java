package net.tiklab.teston.manager.repository.service;

import net.tiklab.core.page.Pagination;
import net.tiklab.join.annotation.FindAll;
import net.tiklab.join.annotation.FindList;
import net.tiklab.join.annotation.FindOne;
import net.tiklab.join.annotation.JoinProvider;
import net.tiklab.teston.manager.repository.model.RepositoryFollow;
import net.tiklab.teston.manager.repository.model.RepositoryFollowQuery;
import net.tiklab.teston.manager.repository.model.Repository;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 仓库关注 服务接口
*/
@JoinProvider(model = RepositoryFollow.class)
public interface RepositoryFollowService {

    /**
    * 创建仓库关注
    * @param repositoryFollow
    * @return
    */
    String createRepositoryFollow(@NotNull @Valid RepositoryFollow repositoryFollow);

    /**
    * 更新仓库关注
    * @param repositoryFollow
    */
    void updateRepositoryFollow(@NotNull @Valid RepositoryFollow repositoryFollow);

    /**
    * 删除仓库关注
    * @param id
    */
    void deleteRepositoryFollow(@NotNull String id);

    @FindOne
    RepositoryFollow findOne(@NotNull String id);

    @FindList
    List<RepositoryFollow> findList(List<String> idList);

    /**
    * 根据id查找仓库关注
    * @param id
    * @return
    */
    RepositoryFollow findRepositoryFollow(@NotNull String id);

    /**
    * 查找所有仓库关注
    * @return
    */
    @FindAll
    List<RepositoryFollow> findAllRepositoryFollow();

    /**
    * 根据查询参数查询仓库关注列表
    * @param repositoryFollowQuery
    * @return
    */
    List<Repository> findRepositoryFollowList(RepositoryFollowQuery repositoryFollowQuery);

    /**
    * 根据查询参数按分页查询仓库关注
    * @param repositoryFollowQuery
    * @return
    */
    Pagination<RepositoryFollow> findRepositoryFollowPage(RepositoryFollowQuery repositoryFollowQuery);

}