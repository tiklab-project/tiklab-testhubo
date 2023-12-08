package io.thoughtware.teston.integrated.postin.workspaceBind.controller;

import io.thoughtware.teston.integrated.postin.workspaceBind.model.Workspace;
import io.thoughtware.teston.integrated.postin.workspaceBind.model.WorkspaceBind;
import io.thoughtware.teston.integrated.postin.workspaceBind.model.WorkspaceBindQuery;
import io.thoughtware.teston.integrated.postin.workspaceBind.model.WorkspaceQuery;
import io.thoughtware.teston.integrated.postin.workspaceBind.service.WorkspaceBindService;
import io.thoughtware.core.Result;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.postin.annotation.Api;
import io.thoughtware.postin.annotation.ApiMethod;
import io.thoughtware.postin.annotation.ApiParam;
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
 * 绑定的空间 控制器
 */
@RestController
@RequestMapping("/workspaceBind")
@Api(name = "WorkspaceBindController",desc = "绑定的空间管理")
public class WorkspaceBindController {

    private static Logger logger = LoggerFactory.getLogger(WorkspaceBindController.class);

    @Autowired
    private WorkspaceBindService workspaceBindService;

    @RequestMapping(path="/createWorkspaceBind",method = RequestMethod.POST)
    @ApiMethod(name = "createWorkspaceBind",desc = "创建绑定的空间")
    @ApiParam(name = "workspaceBind",desc = "workspaceBind",required = true)
    public Result<String> createWorkspaceBind(@RequestBody @NotNull @Valid WorkspaceBind workspaceBind){
        String id = workspaceBindService.createWorkspaceBind(workspaceBind);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateWorkspaceBind",method = RequestMethod.POST)
    @ApiMethod(name = "updateWorkspaceBind",desc = "更新绑定的空间")
    @ApiParam(name = "workspaceBind",desc = "workspaceBind",required = true)
    public Result<Void> updateWorkspaceBind(@RequestBody @NotNull @Valid WorkspaceBind workspaceBind){
        workspaceBindService.updateWorkspaceBind(workspaceBind);

        return Result.ok();
    }

    @RequestMapping(path="/deleteWorkspaceBind",method = RequestMethod.POST)
    @ApiMethod(name = "deleteWorkspaceBind",desc = "删除绑定的空间")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteWorkspaceBind(@NotNull String id){
        workspaceBindService.deleteWorkspaceBind(id);

        return Result.ok();
    }

    @RequestMapping(path="/findWorkspaceBind",method = RequestMethod.POST)
    @ApiMethod(name = "findWorkspaceBind",desc = "根据id查找绑定的空间")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<WorkspaceBind> findWorkspaceBind(@NotNull String id){
        WorkspaceBind workspaceBind = workspaceBindService.findWorkspaceBind(id);

        return Result.ok(workspaceBind);
    }

    @RequestMapping(path="/findAllWorkspaceBind",method = RequestMethod.POST)
    @ApiMethod(name = "findAllWorkspaceBind",desc = "查找所有绑定的空间")
    public Result<List<WorkspaceBind>> findAllWorkspaceBind(){
        List<WorkspaceBind> workspaceBindList = workspaceBindService.findAllWorkspaceBind();

        return Result.ok(workspaceBindList);
    }

    @RequestMapping(path = "/findWorkspaceBindList",method = RequestMethod.POST)
    @ApiMethod(name = "findWorkspaceBindList",desc = "根据查询参数查询绑定的空间列表")
    @ApiParam(name = "workspaceBindQuery",desc = "workspaceBindQuery",required = true)
    public Result<List<WorkspaceBind>> findWorkspaceBindList(@RequestBody @Valid @NotNull WorkspaceBindQuery workspaceBindQuery){
        List<WorkspaceBind> workspaceBindList = workspaceBindService.findWorkspaceBindList(workspaceBindQuery);

        return Result.ok(workspaceBindList);
    }

    @RequestMapping(path = "/findWorkspaceBindPage",method = RequestMethod.POST)
    @ApiMethod(name = "findWorkspaceBindPage",desc = "根据查询参数按分页查询绑定的空间")
    @ApiParam(name = "workspaceBindQuery",desc = "workspaceBindQuery",required = true)
    public Result<Pagination<WorkspaceBind>> findWorkspaceBindPage(@RequestBody @Valid @NotNull WorkspaceBindQuery workspaceBindQuery){
        Pagination<WorkspaceBind> pagination = workspaceBindService.findWorkspaceBindPage(workspaceBindQuery);

        return Result.ok(pagination);
    }

    @RequestMapping(path = "/findWorkspaceList",method = RequestMethod.POST)
    @ApiMethod(name = "findWorkspaceList",desc = "查询空间列表")
    @ApiParam(name = "workspaceQuery",desc = "workspaceQuery",required = true)
    public Result<Workspace> findWorkspaceList(@RequestBody @Valid @NotNull WorkspaceQuery workspaceQuery){
        List<Workspace> workspaceList = workspaceBindService.findWorkspaceList(workspaceQuery);

        return Result.ok(workspaceList);
    }



    @RequestMapping(path="/bindWorkspace",method = RequestMethod.POST)
    @ApiMethod(name = "bindWorkspace",desc = "绑定空间")
    @ApiParam(name = "workspaceBind",desc = "workspaceBind",required = true)
    public Result<String> bindWorkspace(@RequestBody @NotNull @Valid WorkspaceBind workspaceBind){
        String id = workspaceBindService.bindWorkspace(workspaceBind);

        return Result.ok(id);
    }

}
