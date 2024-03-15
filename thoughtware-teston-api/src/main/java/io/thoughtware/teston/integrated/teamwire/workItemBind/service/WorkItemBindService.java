package io.thoughtware.teston.integrated.teamwire.workItemBind.service;

import io.thoughtware.core.page.Pagination;
import io.thoughtware.toolkit.join.annotation.FindAll;
import io.thoughtware.toolkit.join.annotation.FindList;
import io.thoughtware.toolkit.join.annotation.FindOne;
import io.thoughtware.teston.integrated.teamwire.workItemBind.model.WorkItemBind;
import io.thoughtware.teston.integrated.teamwire.workItemBind.model.WorkItemBindQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 绑定的缺陷 服务接口
*/
public interface WorkItemBindService {

    /**
     * 创建绑定的缺陷
     * @param workspaceBind
     * @return
     */
    String createWorkItemBind(@NotNull @Valid WorkItemBind workspaceBind);

    /**
     * 更新绑定的缺陷
     * @param workspaceBind
     */
    void updateWorkItemBind(@NotNull @Valid WorkItemBind workspaceBind);

    /**
     * 删除绑定的缺陷
     * @param id
     */
    void deleteWorkItemBind(@NotNull String id);

    void deleteAllWorkItemBind(String caseId);

    int findTotalNum(String caseId);

    @FindOne
    WorkItemBind findOne(@NotNull String id);

    @FindList
    List<WorkItemBind> findList(List<String> idList);

    /**
     * 根据id查找绑定的缺陷
     * @param id
     * @return
     */
    WorkItemBind findWorkItemBind(@NotNull String id);

    /**
     * 查找所有绑定的缺陷
     * @return
     */
    @FindAll
    List<WorkItemBind> findAllWorkItemBind();

    /**
     * 根据查询参数查询绑定的缺陷列表
     * @param workspaceBindQuery
     * @return
     */
    List<WorkItemBind> findWorkItemBindList(WorkItemBindQuery workspaceBindQuery);

    /**
     * 根据查询参数按分页查询绑定的缺陷
     * @param workspaceBindQuery
     * @return
     */
    Pagination<WorkItemBind> findWorkItemBindPage(WorkItemBindQuery workspaceBindQuery);



}