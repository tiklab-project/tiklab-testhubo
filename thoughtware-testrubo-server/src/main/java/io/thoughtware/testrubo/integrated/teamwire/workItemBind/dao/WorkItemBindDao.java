package io.thoughtware.testrubo.integrated.teamwire.workItemBind.dao;

import io.thoughtware.testrubo.integrated.teamwire.workItemBind.entity.WorkItemBindEntity;
import io.thoughtware.testrubo.integrated.teamwire.workItemBind.model.WorkItemBindQuery;
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
 * 绑定的缺陷 数据访问
 */
@Repository
public class WorkItemBindDao {

    private static Logger logger = LoggerFactory.getLogger(WorkItemBindDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建缺陷
     * @param workItemBindEntity
     * @return
     */
    public String createWorkItemBind(WorkItemBindEntity workItemBindEntity) {
        return jpaTemplate.save(workItemBindEntity,String.class);
    }

    /**
     * 更新缺陷
     * @param workItemBindEntity
     */
    public void updateWorkItemBind(WorkItemBindEntity workItemBindEntity){
        jpaTemplate.update(workItemBindEntity);
    }

    /**
     * 删除缺陷
     * @param id
     */
    public void deleteWorkItemBind(String id){
        jpaTemplate.delete(WorkItemBindEntity.class,id);
    }

    public void deleteWorkItemBind(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    public int findTotalNum(String caseId) {
        String stepSql = "Select count(1) as total from teston_workitem_bind where case_id = '" + caseId+ "'";
        Integer stepNum = jpaTemplate.getJdbcTemplate().queryForObject(stepSql, new Object[]{}, Integer.class);

        return stepNum;
    }


    /**
     * 根据id查找缺陷
     * @param id
     * @return
     */
    public WorkItemBindEntity findWorkItemBind(String id){
        return jpaTemplate.findOne(WorkItemBindEntity.class,id);
    }

    /**
    * 查找所有缺陷
    * @return
    */
    public List<WorkItemBindEntity> findAllWorkItemBind() {
        return jpaTemplate.findAll(WorkItemBindEntity.class);
    }

    public List<WorkItemBindEntity> findWorkItemBindList(List<String> idList) {
        return jpaTemplate.findList(WorkItemBindEntity.class,idList);
    }

    /**
     * 根据查询参数查询缺陷列表
     * @param workItemBindQuery
     * @return
     */
    public List<WorkItemBindEntity> findWorkItemBindList(WorkItemBindQuery workItemBindQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(WorkItemBindEntity.class)
                .eq("caseId",workItemBindQuery.getCaseId())
                .eq("repositoryId",workItemBindQuery.getRepositoryId())
                .orders(workItemBindQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, WorkItemBindEntity.class);
    }

    /**
     * 根据查询参数按分页查询缺陷
     * @param workItemBindQuery
     * @return
     */
    public Pagination<WorkItemBindEntity> findWorkItemBindPage(WorkItemBindQuery workItemBindQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(WorkItemBindEntity.class)
                .eq("caseId",workItemBindQuery.getCaseId())
                .eq("repositoryId",workItemBindQuery.getRepositoryId())
                .orders(workItemBindQuery.getOrderParams())
                .pagination(workItemBindQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, WorkItemBindEntity.class);
    }
}