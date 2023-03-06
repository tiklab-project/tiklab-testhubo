package net.tiklab.teston.repository.controller;

import net.tiklab.core.Result;
import net.tiklab.core.page.Pagination;
import net.tiklab.postin.annotation.Api;
import net.tiklab.postin.annotation.ApiMethod;
import net.tiklab.postin.annotation.ApiParam;
import net.tiklab.teston.repository.model.Repository;
import net.tiklab.teston.repository.model.RepositoryRecent;
import net.tiklab.teston.repository.model.RepositoryRecentQuery;
import net.tiklab.teston.repository.service.RepositoryRecentService;
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
 * 最近访问仓库 控制器
 */
@RestController
@RequestMapping("/repositoryRecent")
@Api(name = "RepositoryRecentController",desc = "RepositoryRecentController")
public class RepositoryRecentController {

    private static Logger logger = LoggerFactory.getLogger(RepositoryRecentController.class);

    @Autowired
    private RepositoryRecentService repositoryRecentService;



    @RequestMapping(path = "/findRepositoryRecentList",method = RequestMethod.POST)
    @ApiMethod(name = "findRepositoryRecentList",desc = "查询最近访问仓库列表")
    @ApiParam(name = "repositoryRecentQuery",desc = "repositoryRecentQuery",required = true)
    public Result<List<Repository>> findRepositoryRecentList(@RequestBody @Valid @NotNull RepositoryRecentQuery repositoryRecentQuery){
        List<Repository> repositoryRecentList = repositoryRecentService.findRepositoryRecentList(repositoryRecentQuery);

        return Result.ok(repositoryRecentList);
    }

    @RequestMapping(path = "/findRepositoryRecentPage",method = RequestMethod.POST)
    @ApiMethod(name = "findRepositoryRecentPage",desc = "按分页查询最近访问仓库")
    @ApiParam(name = "repositoryRecentQuery",desc = "repositoryRecentQuery",required = true)
    public Result<Pagination<RepositoryRecent>> findRepositoryRecentPage(@RequestBody @Valid @NotNull RepositoryRecentQuery repositoryRecentQuery){
        Pagination<RepositoryRecent> pagination = repositoryRecentService.findRepositoryRecentPage(repositoryRecentQuery);

        return Result.ok(pagination);
    }

    @RequestMapping(path="/repositoryRecent",method = RequestMethod.POST)
    @ApiMethod(name = "repositoryRecent",desc = "设置最近访问仓库")
    @ApiParam(name = "repositoryRecent",desc = "repositoryRecent",required = true)
    public Result<Void> workspaceRecent(@RequestBody @NotNull @Valid RepositoryRecent repositoryRecent){
        repositoryRecentService.repositoryRecent(repositoryRecent);

        return Result.ok();
    }


}
