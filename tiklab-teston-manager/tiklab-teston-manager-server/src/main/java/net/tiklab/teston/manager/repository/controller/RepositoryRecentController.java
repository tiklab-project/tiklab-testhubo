package net.tiklab.teston.manager.repository.controller;

import net.tiklab.core.Result;
import net.tiklab.core.page.Pagination;
import net.tiklab.postin.annotation.Api;
import net.tiklab.postin.annotation.ApiMethod;
import net.tiklab.postin.annotation.ApiParam;
import net.tiklab.teston.manager.repository.model.Repository;
import net.tiklab.teston.manager.repository.model.RepositoryRecent;
import net.tiklab.teston.manager.repository.model.RepositoryRecentQuery;
import net.tiklab.teston.manager.repository.service.RepositoryRecentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * RepositoryRecentController
 */
@RestController
@RequestMapping("/repositoryRecent")
@Api(name = "RepositoryRecentController",desc = "RepositoryRecentController")
public class RepositoryRecentController {

    private static Logger logger = LoggerFactory.getLogger(RepositoryRecentController.class);

    @Autowired
    private RepositoryRecentService repositoryRecentService;

    @RequestMapping(path="/createRepositoryRecent",method = RequestMethod.POST)
    @ApiMethod(name = "createRepositoryRecent",desc = "createRepositoryRecent")
    @ApiParam(name = "repositoryRecent",desc = "repositoryRecent",required = true)
    public Result<String> createRepositoryRecent(@RequestBody @NotNull @Valid RepositoryRecent repositoryRecent){
        String id = repositoryRecentService.createRepositoryRecent(repositoryRecent);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateRepositoryRecent",method = RequestMethod.POST)
    @ApiMethod(name = "updateRepositoryRecent",desc = "updateRepositoryRecent")
    @ApiParam(name = "repositoryRecent",desc = "repositoryRecent",required = true)
    public Result<Void> updateRepositoryRecent(@RequestBody @NotNull @Valid RepositoryRecent repositoryRecent){
        repositoryRecentService.updateRepositoryRecent(repositoryRecent);

        return Result.ok();
    }

    @RequestMapping(path="/deleteRepositoryRecent",method = RequestMethod.POST)
    @ApiMethod(name = "deleteRepositoryRecent",desc = "deleteRepositoryRecent")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteRepositoryRecent(@NotNull String id){
        repositoryRecentService.deleteRepositoryRecent(id);

        return Result.ok();
    }

    @RequestMapping(path="/findRepositoryRecent",method = RequestMethod.POST)
    @ApiMethod(name = "findRepositoryRecent",desc = "findRepositoryRecent")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<RepositoryRecent> findRepositoryRecent(@NotNull String id){
        RepositoryRecent repositoryRecent = repositoryRecentService.findRepositoryRecent(id);

        return Result.ok(repositoryRecent);
    }

    @RequestMapping(path="/findAllRepositoryRecent",method = RequestMethod.POST)
    @ApiMethod(name = "findAllRepositoryRecent",desc = "findAllRepositoryRecent")
    public Result<List<RepositoryRecent>> findAllRepositoryRecent(){
        List<RepositoryRecent> repositoryRecentList = repositoryRecentService.findAllRepositoryRecent();

        return Result.ok(repositoryRecentList);
    }

    @RequestMapping(path = "/findRepositoryRecentList",method = RequestMethod.POST)
    @ApiMethod(name = "findRepositoryRecentList",desc = "findRepositoryRecentList")
    @ApiParam(name = "repositoryRecentQuery",desc = "repositoryRecentQuery",required = true)
    public Result<List<Repository>> findRepositoryRecentList(@RequestBody @Valid @NotNull RepositoryRecentQuery repositoryRecentQuery){
        List<Repository> repositoryRecentList = repositoryRecentService.findRepositoryRecentList(repositoryRecentQuery);

        return Result.ok(repositoryRecentList);
    }

    @RequestMapping(path = "/findRepositoryRecentPage",method = RequestMethod.POST)
    @ApiMethod(name = "findRepositoryRecentPage",desc = "findRepositoryRecentPage")
    @ApiParam(name = "repositoryRecentQuery",desc = "repositoryRecentQuery",required = true)
    public Result<Pagination<RepositoryRecent>> findRepositoryRecentPage(@RequestBody @Valid @NotNull RepositoryRecentQuery repositoryRecentQuery){
        Pagination<RepositoryRecent> pagination = repositoryRecentService.findRepositoryRecentPage(repositoryRecentQuery);

        return Result.ok(pagination);
    }

    @RequestMapping(path="/repositoryRecent",method = RequestMethod.POST)
    @ApiMethod(name = "repositoryRecent",desc = "repositoryRecent")
    @ApiParam(name = "repositoryRecent",desc = "repositoryRecent",required = true)
    public Result<Void> workspaceRecent(@RequestBody @NotNull @Valid RepositoryRecent repositoryRecent){
        repositoryRecentService.repositoryRecent(repositoryRecent);

        return Result.ok();
    }


}
