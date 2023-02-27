package net.tiklab.teston.manager.repository.controller;

import net.tiklab.core.Result;
import net.tiklab.core.page.Pagination;
import net.tiklab.postin.annotation.Api;
import net.tiklab.postin.annotation.ApiMethod;
import net.tiklab.postin.annotation.ApiParam;
import net.tiklab.teston.manager.repository.model.Repository;
import net.tiklab.teston.manager.repository.model.RepositoryFollow;
import net.tiklab.teston.manager.repository.model.RepositoryFollowQuery;
import net.tiklab.teston.manager.repository.service.RepositoryFollowService;
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
 * RepositoryFollowController
 */
@RestController
@RequestMapping("/repositoryFollow")
@Api(name = "RepositoryFollowController",desc = "RepositoryFollowController")
public class RepositoryFollowController {

    private static Logger logger = LoggerFactory.getLogger(RepositoryFollowController.class);

    @Autowired
    private RepositoryFollowService repositoryFollowService;

    @RequestMapping(path="/createRepositoryFollow",method = RequestMethod.POST)
    @ApiMethod(name = "createRepositoryFollow",desc = "createRepositoryFollow")
    @ApiParam(name = "repositoryFollow",desc = "repositoryFollow",required = true)
    public Result<String> createRepositoryFollow(@RequestBody @NotNull @Valid RepositoryFollow repositoryFollow){
        String id = repositoryFollowService.createRepositoryFollow(repositoryFollow);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateRepositoryFollow",method = RequestMethod.POST)
    @ApiMethod(name = "updateRepositoryFollow",desc = "updateRepositoryFollow")
    @ApiParam(name = "repositoryFollow",desc = "repositoryFollow",required = true)
    public Result<Void> updateRepositoryFollow(@RequestBody @NotNull @Valid RepositoryFollow repositoryFollow){
        repositoryFollowService.updateRepositoryFollow(repositoryFollow);

        return Result.ok();
    }

    @RequestMapping(path="/deleteRepositoryFollow",method = RequestMethod.POST)
    @ApiMethod(name = "deleteRepositoryFollow",desc = "deleteRepositoryFollow")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteRepositoryFollow(@NotNull String id){
        repositoryFollowService.deleteRepositoryFollow(id);

        return Result.ok();
    }

    @RequestMapping(path="/findRepositoryFollow",method = RequestMethod.POST)
    @ApiMethod(name = "findRepositoryFollow",desc = "findRepositoryFollow")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<RepositoryFollow> findRepositoryFollow(@NotNull String id){
        RepositoryFollow repositoryFollow = repositoryFollowService.findRepositoryFollow(id);

        return Result.ok(repositoryFollow);
    }

    @RequestMapping(path="/findAllRepositoryFollow",method = RequestMethod.POST)
    @ApiMethod(name = "findAllRepositoryFollow",desc = "findAllRepositoryFollow")
    public Result<List<RepositoryFollow>> findAllRepositoryFollow(){
        List<RepositoryFollow> repositoryFollowList = repositoryFollowService.findAllRepositoryFollow();

        return Result.ok(repositoryFollowList);
    }

    @RequestMapping(path = "/findRepositoryFollowList",method = RequestMethod.POST)
    @ApiMethod(name = "findRepositoryFollowList",desc = "findRepositoryFollowList")
    @ApiParam(name = "repositoryFollowQuery",desc = "repositoryFollowQuery",required = true)
    public Result<List<Repository>> findRepositoryFollowList(@RequestBody @Valid @NotNull RepositoryFollowQuery repositoryFollowQuery){
        List<Repository> repositoryFollowList = repositoryFollowService.findRepositoryFollowList(repositoryFollowQuery);

        return Result.ok(repositoryFollowList);
    }

    @RequestMapping(path = "/findRepositoryFollowPage",method = RequestMethod.POST)
    @ApiMethod(name = "findRepositoryFollowPage",desc = "findRepositoryFollowPage")
    @ApiParam(name = "repositoryFollowQuery",desc = "repositoryFollowQuery",required = true)
    public Result<Pagination<RepositoryFollow>> findRepositoryFollowPage(@RequestBody @Valid @NotNull RepositoryFollowQuery repositoryFollowQuery){
        Pagination<RepositoryFollow> pagination = repositoryFollowService.findRepositoryFollowPage(repositoryFollowQuery);

        return Result.ok(pagination);
    }

}
