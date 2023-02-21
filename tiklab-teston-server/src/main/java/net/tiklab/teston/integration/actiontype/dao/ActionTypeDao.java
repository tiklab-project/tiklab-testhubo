package net.tiklab.teston.integration.actiontype.dao;

import net.tiklab.core.page.Pagination;
import net.tiklab.dal.jpa.JpaTemplate;
import net.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import net.tiklab.dal.jpa.criterial.condition.QueryCondition;
import net.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import net.tiklab.teston.integration.actiontype.entity.ActionTypeEntity;
import net.tiklab.teston.integration.actiontype.model.ActionTypeQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ActionTypeDao
 */
@Repository
public class ActionTypeDao{

    private static Logger logger = LoggerFactory.getLogger(ActionTypeDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param actionTypeEntity
     * @return
     */
    public String createActionType(ActionTypeEntity actionTypeEntity) {
        return jpaTemplate.save(actionTypeEntity,String.class);
    }

    /**
     * 更新
     * @param actionTypeEntity
     */
    public void updateActionType(ActionTypeEntity actionTypeEntity){
        jpaTemplate.update(actionTypeEntity);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteActionType(String id){
        jpaTemplate.delete(ActionTypeEntity.class,id);
    }

    public void deleteActionType(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public ActionTypeEntity findActionType(String id){
        return jpaTemplate.findOne(ActionTypeEntity.class,id);
    }

    /**
    * findAllActionType
    * @return
    */
    public List<ActionTypeEntity> findAllActionType() {
        return jpaTemplate.findAll(ActionTypeEntity.class);
    }

    public List<ActionTypeEntity> findActionTypeList(List<String> idList) {
        return jpaTemplate.findList(ActionTypeEntity.class,idList);
    }

    public List<ActionTypeEntity> findActionTypeList(ActionTypeQuery actionTypeQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ActionTypeEntity.class)
                .orders(actionTypeQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, ActionTypeEntity.class);
    }

    public Pagination<ActionTypeEntity> findActionTypePage(ActionTypeQuery actionTypeQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ActionTypeEntity.class)
                .orders(actionTypeQuery.getOrderParams())
                .pagination(actionTypeQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(actionTypeQuery, ActionTypeEntity.class);
    }
}