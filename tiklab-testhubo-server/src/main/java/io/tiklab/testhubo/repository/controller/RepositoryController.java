package io.tiklab.testhubo.repository.controller;

import io.tiklab.testhubo.repository.model.Repository;
import io.tiklab.testhubo.repository.model.RepositoryQuery;
import io.tiklab.testhubo.repository.service.RepositoryService;
import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.annotation.ApiMethod;
import io.tiklab.postin.annotation.ApiParam;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @pi.protocol: http
 * @pi.groupName: 仓库 控制器
 */

@RestController
@RequestMapping("/repository")
@Api(name = "RepositoryController",desc = "仓库管理")
public class RepositoryController {

    private static Logger logger = LoggerFactory.getLogger(RepositoryController.class);

    @Autowired
    private RepositoryService repositoryService;

    /**
     * @pi.name:createRepository
     * @pi.path:/repository/createRepository
     * @pi.methodType:post
     * @pi.request-type:json
     * @pi.param: model=Repository
     */
    @RequestMapping(path="/createRepository",method = RequestMethod.POST)
    @ApiMethod(name = "createRepository",desc = "创建仓库")
    @ApiParam(name = "repository",desc = "repository",required = true)
    public Result<String> createRepository(@RequestBody @NotNull @Valid Repository repository){
        String id = repositoryService.createRepository(repository);

        return Result.ok(id);
    }

    /**
     * @pi.name:updateRepository
     * @pi.path:/repository/updateRepository
     * @pi.methodType:post
     * @pi.request-type:json
     * @pi.param: model=Repository
     */
    @RequestMapping(path="/updateRepository",method = RequestMethod.POST)
    @ApiMethod(name = "updateRepository",desc = "更新仓库")
    @ApiParam(name = "repository",desc = "repository",required = true)
    public Result<Void> updateRepository(@RequestBody @NotNull @Valid Repository repository){
        repositoryService.updateRepository(repository);

        return Result.ok();
    }

    /**
     * @pi.name:deleteRepository
     * @pi.path:/repository/deleteRepository
     * @pi.methodType:post
     * @pi.request-type:formdata
     * @pi.param: name=id;dataType=string;value=id;desc=当前删除的id
     */
    @RequestMapping(path="/deleteRepository",method = RequestMethod.POST)
    @ApiMethod(name = "deleteRepository",desc = "删除仓库")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteRepository(@NotNull String id){
        repositoryService.deleteRepository(id);

        return Result.ok();
    }

    /**
     * @pi.name:findRepository
     * @pi.path:/repository/findRepository
     * @pi.methodType:post
     * @pi.request-type:formdata
     * @pi.param: name=id;dataType=string;value=id;desc=当前查找的id
     */
    @RequestMapping(path="/findRepository",method = RequestMethod.POST)
    @ApiMethod(name = "findRepository",desc = "根据id查找仓库")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Repository> findRepository(@NotNull String id){
        Repository repository = repositoryService.findRepository(id);

        return Result.ok(repository);
    }

    /**
     * @pi.name:findAllRepository
     * @pi.path:/repository/findAllRepository
     * @pi.methodType:post
     * @pi.request-type:none
     */
    @RequestMapping(path="/findAllRepository",method = RequestMethod.POST)
    @ApiMethod(name = "findAllRepository",desc = "查找所有仓库")
    public Result<List<Repository>> findAllRepository(){
        List<Repository> repositoryList = repositoryService.findAllRepository();

        return Result.ok(repositoryList);
    }

    /**
     * @pi.name:findList
     * @pi.path:/repository/findList
     * @pi.methodType:post
     * @pi.request-type:json
     * @pi.param: model=Repository
     */
    @RequestMapping(path = "/findList",method = RequestMethod.POST)
    @ApiMethod(name = "findList",desc = "根据查询参数查询仓库列表")
    @ApiParam(name = "idList",desc = "idList",required = true)
    public Result<List<Repository>> findRepositoryList(@RequestBody @Valid @NotNull List<String> idList){
        List<Repository> repositoryList = repositoryService.findList(idList);

        return Result.ok(repositoryList);
    }

    @RequestMapping(path = "/findRepositoryList",method = RequestMethod.POST)
    @ApiMethod(name = "findRepositoryList",desc = "根据查询参数查询仓库列表")
    @ApiParam(name = "repositoryQuery",desc = "repositoryQuery",required = true)
    public Result<List<Repository>> findRepositoryList(@RequestBody @Valid @NotNull RepositoryQuery repositoryQuery){
        List<Repository> repositoryList = repositoryService.findRepositoryList(repositoryQuery);

        return Result.ok(repositoryList);
    }


    /**
     * @pi.name:findRepositoryPage
     * @pi.path:/repository/findRepositoryPage
     * @pi.methodType:post
     * @pi.request-type:json
     * @pi.param: model=RepositoryQuery
     */
    @RequestMapping(path = "/findRepositoryPage",method = RequestMethod.POST)
    @ApiMethod(name = "findRepositoryPage",desc = "根据查询参数按分页查询仓库")
    @ApiParam(name = "repositoryQuery",desc = "repositoryQuery",required = true)
    public Result<Pagination<Repository>> findRepositoryPage(@RequestBody @Valid @NotNull RepositoryQuery repositoryQuery){
        Pagination<Repository> pagination = repositoryService.findRepositoryPage(repositoryQuery);

        return Result.ok(pagination);
    }

    /**
     * @pi.name:findRepositoryJoinList
     * @pi.path:/repository/findRepositoryJoinList
     * @pi.methodType:post
     * @pi.request-type:json
     * @pi.param: model=RepositoryQuery
     */
    @RequestMapping(path = "/findRepositoryJoinList",method = RequestMethod.POST)
    @ApiMethod(name = "findRepositoryJoinList",desc = "查询我加入的仓库列表")
    @ApiParam(name = "repositoryQuery",desc = "repositoryQuery",required = true)
    public Result<List<Repository>> findRepositoryJoinList(@RequestBody @Valid @NotNull RepositoryQuery repositoryQuery){
        List<Repository> repositoryJoinList = repositoryService.findRepositoryJoinList(repositoryQuery);

        return Result.ok(repositoryJoinList);
    }


}
