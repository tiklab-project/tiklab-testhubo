package io.tiklab.teston.repository.controller;

import io.tiklab.teston.repository.model.RepositoryTotal;
import io.tiklab.teston.repository.service.RepositoryOverviewService;
import io.tiklab.core.Result;
import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.annotation.ApiMethod;
import io.tiklab.postin.annotation.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * 仓库概况 控制器
 */
@RestController
@RequestMapping("/repository")
@Api(name = "RepositoryOverviewController",desc = "仓库概况管理")
public class RepositoryOverviewController {

    private static Logger logger = LoggerFactory.getLogger(RepositoryOverviewController.class);

    @Autowired
    private RepositoryOverviewService repositoryOverviewService;


    @RequestMapping(path="/findRepositoryTotal",method = RequestMethod.POST)
    @ApiMethod(name = "findRepositoryTotal",desc = "根据项目ID查找单个项目中概要")
    @ApiParam(name = "id",desc = "项目ID",required = true)
    public Result<RepositoryTotal> findWorkspaceTotal(@NotNull String id){
        RepositoryTotal workspace = repositoryOverviewService.findRepositoryOverview(id);

        return Result.ok(workspace);
    }


}
