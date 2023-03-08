package io.tiklab.teston.support.actiontype.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.teston.support.actiontype.entity.ActionTypeEntity;
import io.tiklab.teston.support.actiontype.model.ActionTypeQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * app、web中操作类型 数据访问
 */
@Repository
public class ActionTypeDao{

    private static Logger logger = LoggerFactory.getLogger(ActionTypeDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建操作类型
     * @param actionTypeEntity
     * @return
     */
    public String createActionType(ActionTypeEntity actionTypeEntity) {
        return jpaTemplate.save(actionTypeEntity,String.class);
    }

    /**
     * 更新操作类型
     * @param actionTypeEntity
     */
    public void updateActionType(ActionTypeEntity actionTypeEntity){
        jpaTemplate.update(actionTypeEntity);
    }

    /**
     * 删除操作类型
     * @param id
     */
    public void deleteActionType(String id){
        jpaTemplate.delete(ActionTypeEntity.class,id);
    }

    public void deleteActionType(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找操作类型
     * @param id
     * @return
     */
    public ActionTypeEntity findActionType(String id){
        return jpaTemplate.findOne(ActionTypeEntity.class,id);
    }

    /**
    * 查找所有操作类型
    * @return
    */
    public List<ActionTypeEntity> findAllActionType() {
        return jpaTemplate.findAll(ActionTypeEntity.class);
    }

    public List<ActionTypeEntity> findActionTypeList(List<String> idList) {
        return jpaTemplate.findList(ActionTypeEntity.class,idList);
    }

    /**
     * 根据查询参数查询操作类型列表
     * @param actionTypeQuery
     * @return
     */
    public List<ActionTypeEntity> findActionTypeList(ActionTypeQuery actionTypeQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ActionTypeEntity.class)
                .orders(actionTypeQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, ActionTypeEntity.class);
    }

    /**
     * 根据查询参数按分页查询操作类型
     * @param actionTypeQuery
     * @return
     */
    public Pagination<ActionTypeEntity> findActionTypePage(ActionTypeQuery actionTypeQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ActionTypeEntity.class)
                .orders(actionTypeQuery.getOrderParams())
                .pagination(actionTypeQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(actionTypeQuery, ActionTypeEntity.class);
    }
}