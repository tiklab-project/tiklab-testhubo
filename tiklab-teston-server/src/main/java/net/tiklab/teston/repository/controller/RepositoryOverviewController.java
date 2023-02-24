package net.tiklab.teston.repository.controller;

import net.tiklab.core.Result;
import net.tiklab.postin.annotation.Api;
import net.tiklab.postin.annotation.ApiMethod;
import net.tiklab.postin.annotation.ApiParam;
import net.tiklab.teston.repository.model.RepositoryTotal;
import net.tiklab.teston.repository.service.RepositoryOverviewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * RepositoryOverviewController
 */
@RestController
@RequestMapping("/repository")
@Api(name = "RepositoryOverviewController",desc = "RepositoryOverviewController")
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
