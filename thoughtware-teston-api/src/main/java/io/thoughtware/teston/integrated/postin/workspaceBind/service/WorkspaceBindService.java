package io.thoughtware.teston.integrated.postin.workspaceBind.service;

import io.thoughtware.teston.integrated.postin.workspaceBind.model.Workspace;
import io.thoughtware.teston.integrated.postin.workspaceBind.model.WorkspaceQuery;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.toolkit.join.annotation.FindAll;
import io.thoughtware.toolkit.join.annotation.FindList;
import io.thoughtware.toolkit.join.annotation.FindOne;
import io.thoughtware.teston.integrated.postin.workspaceBind.model.WorkspaceBind;
import io.thoughtware.teston.integrated.postin.workspaceBind.model.WorkspaceBindQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 接口单元 服务接口
*/
public interface WorkspaceBindService {


    /**
     * 创建仓库
     * @param workspaceBind
     * @return
     */
    String createWorkspaceBind(@NotNull @Valid WorkspaceBind workspaceBind);

    /**
     * 更新仓库
     * @param workspaceBind
     */
    void updateWorkspaceBind(@NotNull @Valid WorkspaceBind workspaceBind);

    /**
     * 删除仓库
     * @param id
     */
    void deleteWorkspaceBind(@NotNull String id);

    @FindOne
    WorkspaceBind findOne(@NotNull String id);

    @FindList
    List<WorkspaceBind> findList(List<String> idList);

    /**
     * 根据id查找仓库
     * @param id
     * @return
     */
    WorkspaceBind findWorkspaceBind(@NotNull String id);

    /**
     * 查找所有仓库
     * @return
     */
    @FindAll
    List<WorkspaceBind> findAllWorkspaceBind();

    /**
     * 根据查询参数查询仓库列表
     * @param workspaceBindQuery
     * @return
     */
    List<WorkspaceBind> findWorkspaceBindList(WorkspaceBindQuery workspaceBindQuery);

    /**
     * 根据查询参数按分页查询仓库
     * @param workspaceBindQuery
     * @return
     */
    Pagination<WorkspaceBind> findWorkspaceBindPage(WorkspaceBindQuery workspaceBindQuery);

    /**
     * 查询列表空间
     * @param workspaceQuery
     * @return
     */
    List<Workspace> findWorkspaceList(WorkspaceQuery workspaceQuery);


    /***
     * 绑定空间
     * @param workspaceBind
     * @return
     */
    String bindWorkspace(WorkspaceBind workspaceBind);

}