package io.tiklab.teston.repository.service;

import io.tiklab.teston.repository.model.Repository;
import io.tiklab.teston.repository.model.RepositoryQuery;
import io.tiklab.core.page.Pagination;

import io.tiklab.join.annotation.FindAll;
import io.tiklab.join.annotation.FindList;
import io.tiklab.join.annotation.FindOne;
import io.tiklab.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 仓库 服务接口
*/
@JoinProvider(model = Repository.class)
public interface RepositoryService {

    /**
    * 创建仓库
    * @param repository
    * @return
    */
    String createRepository(@NotNull @Valid Repository repository);

    /**
    * 更新仓库
    * @param repository
    */
    void updateRepository(@NotNull @Valid Repository repository);

    /**
    * 删除仓库
    * @param id
    */
    void deleteRepository(@NotNull String id);

    @FindOne
    Repository findOne(@NotNull String id);

    @FindList
    List<Repository> findList(List<String> idList);

    /**
    * 根据id查找仓库
    * @param id
    * @return
    */
    Repository findRepository(@NotNull String id);

    /**
    * 查找所有仓库
    * @return
    */
    @FindAll
    List<Repository> findAllRepository();

    /**
    * 根据查询参数查询仓库列表
    * @param repositoryQuery
    * @return
    */
    List<Repository> findRepositoryList(RepositoryQuery repositoryQuery);

    /**
    * 根据查询参数按分页查询仓库
    * @param repositoryQuery
    * @return
    */
    Pagination<Repository> findRepositoryPage(RepositoryQuery repositoryQuery);

    /**
     * 查询我加入的仓库列表
     * @param repositoryQuery
     * @return
     */
    List<Repository> findRepositoryJoinList(RepositoryQuery repositoryQuery);


}