package io.tiklab.testhubo.integrated.teamwire.workItem.controller;

import io.tiklab.core.page.Pagination;
import io.tiklab.testhubo.integrated.teamwire.workItem.model.*;
import io.tiklab.testhubo.integrated.teamwire.workItem.service.WorkItemTestOnService;
import io.tiklab.core.Result;
import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.annotation.ApiMethod;
import io.tiklab.postin.annotation.ApiParam;
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
 * teamwire 需求 缺陷 控制器
 */
@RestController
@RequestMapping("/teamWire")
@Api(name = "WorkItemController",desc = "postin接口转用例")
public class WorkItemController {

    private static Logger logger = LoggerFactory.getLogger(WorkItemController.class);

    @Autowired
    private WorkItemTestOnService workItemTestOnService;

    @RequestMapping(path="/createWorkItem",method = RequestMethod.POST)
    @ApiMethod(name = "createWorkItem",desc = "创建缺陷")
    @ApiParam(name = "workItemTestOn",desc = "workItemTestOn",required = true)
    public Result<String> createWorkItemBind(@RequestBody @NotNull @Valid WorkItemTestOn workItemTestOn){
        String id = workItemTestOnService.createWorkItem(workItemTestOn);

        return Result.ok(id);
    }

    @RequestMapping(path = "/findProjectList",method = RequestMethod.POST)
    @ApiMethod(name = "findProjectList",desc = "根据查询teamwire 项目列表")
    @ApiParam(name = "projectTestOnQuery",desc = "projectTestOnQuery")
    public Result<List<ProjectTestOn>> findProjectList(@RequestBody @Valid @NotNull ProjectTestOnQuery projectTestOnQuery){
        List<ProjectTestOn> projectTestOnList = workItemTestOnService.findProjectList(projectTestOnQuery);

        return Result.ok(projectTestOnList);
    }

    @RequestMapping(path = "/findWorkItemList",method = RequestMethod.POST)
    @ApiMethod(name = "findWorkItemList",desc = "查询teamwire 需求缺陷列表")
    @ApiParam(name = "workItemTestOnQuery",desc = "workItemTestOnQuery")
    public Result<Pagination<WorkItem>> findWorkItemList(@RequestBody @Valid @NotNull WorkItemTestOnQuery workItemTestOnQuery){
        Pagination<WorkItem> workItemTestOnList = workItemTestOnService.findWorkItemList(workItemTestOnQuery);

        return Result.ok(workItemTestOnList);
    }

    @RequestMapping(path="/findWorkItem",method = RequestMethod.POST)
    @ApiMethod(name = "findWorkItem",desc = "根据id查找需求")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<WorkItemTestOn> findCategory(@NotNull String id,String repositoryId){
        WorkItemTestOn workItem = workItemTestOnService.findWorkItem(id,repositoryId);

        return Result.ok(workItem);
    }



}
