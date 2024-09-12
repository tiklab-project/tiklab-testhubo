package io.thoughtware.testhubo.integrated.postin.workspaceBind.dao;

import io.thoughtware.testhubo.integrated.postin.workspaceBind.entity.WorkspaceBindEntity;
import io.thoughtware.testhubo.integrated.postin.workspaceBind.model.WorkspaceBindQuery;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.dal.jpa.JpaTemplate;
import io.thoughtware.dal.jpa.criterial.condition.DeleteCondition;
import io.thoughtware.dal.jpa.criterial.condition.QueryCondition;
import io.thoughtware.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 绑定的空间 数据访问
 */
@Repository
public class WorkspaceBindDao {

    private static Logger logger = LoggerFactory.getLogger(WorkspaceBindDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建空间
     * @param workspaceBindEntity
     * @return
     */
    public String createWorkspaceBind(WorkspaceBindEntity workspaceBindEntity) {
        return jpaTemplate.save(workspaceBindEntity,String.class);
    }

    /**
     * 更新空间
     * @param workspaceBindEntity
     */
    public void updateWorkspaceBind(WorkspaceBindEntity workspaceBindEntity){
        jpaTemplate.update(workspaceBindEntity);
    }

    /**
     * 删除空间
     * @param id
     */
    public void deleteWorkspaceBind(String id){
        jpaTemplate.delete(WorkspaceBindEntity.class,id);
    }

    public void deleteWorkspaceBind(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找空间
     * @param id
     * @return
     */
    public WorkspaceBindEntity findWorkspaceBind(String id){
        return jpaTemplate.findOne(WorkspaceBindEntity.class,id);
    }

    /**
    * 查找所有空间
    * @return
    */
    public List<WorkspaceBindEntity> findAllWorkspaceBind() {
        return jpaTemplate.findAll(WorkspaceBindEntity.class);
    }

    public List<WorkspaceBindEntity> findWorkspaceBindList(List<String> idList) {
        return jpaTemplate.findList(WorkspaceBindEntity.class,idList);
    }

    /**
     * 根据查询参数查询空间列表
     * @param workspaceBindQuery
     * @return
     */
    public List<WorkspaceBindEntity> findWorkspaceBindList(WorkspaceBindQuery workspaceBindQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(WorkspaceBindEntity.class)
                .eq("repositoryId",workspaceBindQuery.getRepositoryId())
                .orders(workspaceBindQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, WorkspaceBindEntity.class);
    }

    /**
     * 根据查询参数按分页查询空间
     * @param workspaceBindQuery
     * @return
     */
    public Pagination<WorkspaceBindEntity> findWorkspaceBindPage(WorkspaceBindQuery workspaceBindQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(WorkspaceBindEntity.class)
                .eq("repositoryId",workspaceBindQuery.getRepositoryId())
                .orders(workspaceBindQuery.getOrderParams())
                .pagination(workspaceBindQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, WorkspaceBindEntity.class);
    }
}