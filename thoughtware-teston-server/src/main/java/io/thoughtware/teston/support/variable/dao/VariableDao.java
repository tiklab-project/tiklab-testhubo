package io.thoughtware.teston.support.variable.dao;

import io.thoughtware.core.page.Pagination;
import io.thoughtware.dal.jpa.JpaTemplate;
import io.thoughtware.dal.jpa.criterial.condition.DeleteCondition;
import io.thoughtware.dal.jpa.criterial.condition.QueryCondition;
import io.thoughtware.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.thoughtware.teston.support.variable.entity.VariableEntity;
import io.thoughtware.teston.support.variable.model.VariableQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 场景变量 数据访问
 */
@Repository
public class VariableDao {

    private static Logger logger = LoggerFactory.getLogger(VariableDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建场景变量
     * @param variableEntity
     * @return
     */
    public String createVariable(VariableEntity variableEntity) {
        return jpaTemplate.save(variableEntity,String.class);
    }

    /**
     * 更新场景变量
     * @param variableEntity
     */
    public void updateVariable(VariableEntity variableEntity){
        jpaTemplate.update(variableEntity);
    }

    /**
     * 删除场景变量
     * @param id
     */
    public void deleteVariable(String id){
        jpaTemplate.delete(VariableEntity.class,id);
    }

    public void deleteVariable(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找场景变量
     * @param id
     * @return
     */
    public VariableEntity findVariable(String id){
        return jpaTemplate.findOne(VariableEntity.class,id);
    }

    /**
    * 查找所有场景变量
    * @return
    */
    public List<VariableEntity> findAllVariable() {
        return jpaTemplate.findAll(VariableEntity.class);
    }

    public List<VariableEntity> findVariableList(List<String> idList) {
        return jpaTemplate.findList(VariableEntity.class,idList);
    }

    /**
     * 根据查询参数查询场景变量列表
     * @param variableQuery
     * @return
     */
    public List<VariableEntity> findVariableList(VariableQuery variableQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(VariableEntity.class)
                .eq("belongId", variableQuery.getBelongId())
                .eq("name",variableQuery.getName())
                .orders(variableQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition,VariableEntity.class);
    }

    /**
     * 根据查询参数按分页查询场景变量
     * @param variableQuery
     * @return
     */
    public Pagination<VariableEntity> findVariablePage(VariableQuery variableQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(VariableEntity.class)
                .eq("belongId", variableQuery.getBelongId())
                .eq("name",variableQuery.getName())
                .orders(variableQuery.getOrderParams())
                .pagination(variableQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition,VariableEntity.class);
    }
}