package io.thoughtware.testrubo.integrated.teamwire.workItem.service;

import io.thoughtware.core.page.Pagination;
import io.thoughtware.testrubo.integrated.teamwire.workItem.model.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* teamwire需求 缺陷 服务接口
*/
public interface WorkItemTestOnService {

    /**
     * 查询teamwire 项目列表
     */
    List<ProjectTestOn> findProjectList(ProjectTestOnQuery query);


    /**
     *  查询teamwire 查询需求 缺陷列表
     *  需求： demand
     * 缺陷： defect
     * @param workItemTestOnQuery
     * @return
     */
    Pagination<WorkItem> findWorkItemList(WorkItemTestOnQuery workItemTestOnQuery);

    /**
     * 查询通过id需求 缺陷
     * @param id
     * @return
     */
    WorkItemTestOn findWorkItem(String id,String repositoryId);


    /**
     * 创建缺陷
     * @return
     */
    String createWorkItem(@NotNull @Valid WorkItemTestOn workItemTestOn);


}