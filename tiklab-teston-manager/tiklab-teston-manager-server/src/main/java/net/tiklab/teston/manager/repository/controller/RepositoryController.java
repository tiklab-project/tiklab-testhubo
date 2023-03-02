package net.tiklab.teston.manager.repository.controller;

import net.tiklab.postin.annotation.Api;
import net.tiklab.postin.annotation.ApiMethod;
import net.tiklab.postin.annotation.ApiParam;
import net.tiklab.core.page.Pagination;
import net.tiklab.core.Result;
import net.tiklab.teston.manager.repository.model.Repository;
import net.tiklab.teston.manager.repository.model.RepositoryQuery;
import net.tiklab.teston.manager.repository.service.RepositoryService;
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
 * 仓库 控制器
 */
@RestController
@RequestMapping("/repository")
@Api(name = "RepositoryController",desc = "仓库管理")
public class RepositoryController {

    private static Logger logger = LoggerFactory.getLogger(RepositoryController.class);

    @Autowired
    private RepositoryService repositoryService;

    @RequestMapping(path="/createRepository",method = RequestMethod.POST)
    @ApiMethod(name = "createRepository",desc = "创建仓库")
    @ApiParam(name = "repository",desc = "repository",required = true)
    public Result<String> createRepository(@RequestBody @NotNull @Valid Repository repository){
        String id = repositoryService.createRepository(repository);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateRepository",method = RequestMethod.POST)
    @ApiMethod(name = "updateRepository",desc = "更新仓库")
    @ApiParam(name = "repository",desc = "repository",required = true)
    public Result<Void> updateRepository(@RequestBody @NotNull @Valid Repository repository){
        repositoryService.updateRepository(repository);

        return Result.ok();
    }

    @RequestMapping(path="/deleteRepository",method = RequestMethod.POST)
    @ApiMethod(name = "deleteRepository",desc = "删除仓库")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteRepository(@NotNull String id){
        repositoryService.deleteRepository(id);

        return Result.ok();
    }

    @RequestMapping(path="/findRepository",method = RequestMethod.POST)
    @ApiMethod(name = "findRepository",desc = "根据id查找仓库")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Repository> findRepository(@NotNull String id){
        Repository repository = repositoryService.findRepository(id);

        return Result.ok(repository);
    }

    @RequestMapping(path="/findAllRepository",method = RequestMethod.POST)
    @ApiMethod(name = "findAllRepository",desc = "查找所有仓库")
    public Result<List<Repository>> findAllRepository(){
        List<Repository> repositoryList = repositoryService.findAllRepository();

        return Result.ok(repositoryList);
    }

    @RequestMapping(path = "/findRepositoryList",method = RequestMethod.POST)
    @ApiMethod(name = "findRepositoryList",desc = "根据查询参数查询仓库列表")
    @ApiParam(name = "repositoryQuery",desc = "repositoryQuery",required = true)
    public Result<List<Repository>> findRepositoryList(@RequestBody @Valid @NotNull RepositoryQuery repositoryQuery){
        List<Repository> repositoryList = repositoryService.findRepositoryList(repositoryQuery);

        return Result.ok(repositoryList);
    }

    @RequestMapping(path = "/findRepositoryPage",method = RequestMethod.POST)
    @ApiMethod(name = "findRepositoryPage",desc = "根据查询参数按分页查询仓库")
    @ApiParam(name = "repositoryQuery",desc = "repositoryQuery",required = true)
    public Result<Pagination<Repository>> findRepositoryPage(@RequestBody @Valid @NotNull RepositoryQuery repositoryQuery){
        Pagination<Repository> pagination = repositoryService.findRepositoryPage(repositoryQuery);

        return Result.ok(pagination);
    }

    @RequestMapping(path = "/findRepositoryJoinList",method = RequestMethod.POST)
    @ApiMethod(name = "findRepositoryJoinList",desc = "查询我加入的仓库列表")
    @ApiParam(name = "repositoryQuery",desc = "repositoryQuery",required = true)
    public Result<List<Repository>> findRepositoryJoinList(@RequestBody @Valid @NotNull RepositoryQuery repositoryQuery){
        List<Repository> repositoryJoinList = repositoryService.findRepositoryJoinList(repositoryQuery);

        return Result.ok(repositoryJoinList);
    }


}
