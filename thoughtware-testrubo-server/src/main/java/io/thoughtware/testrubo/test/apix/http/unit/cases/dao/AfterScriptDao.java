package io.thoughtware.testrubo.test.apix.http.unit.cases.dao;

import io.thoughtware.core.page.Pagination;
import io.thoughtware.dal.jpa.criterial.condition.DeleteCondition;
import io.thoughtware.dal.jpa.criterial.condition.QueryCondition;
import io.thoughtware.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.thoughtware.dal.jpa.JpaTemplate;
import io.thoughtware.testrubo.test.apix.http.unit.cases.model.AfterScriptUnitQuery;
import io.thoughtware.testrubo.test.apix.http.unit.cases.entity.AfterScriptEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 后置脚本 数据访问
 */
@Repository
public class AfterScriptDao{

    private static Logger logger = LoggerFactory.getLogger(AfterScriptDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param afterScriptEntity
     * @return
     */
    public String createAfterScript(AfterScriptEntity afterScriptEntity) {
        return jpaTemplate.save(afterScriptEntity,String.class);
    }

    /**
     * 更新
     * @param afterScriptEntity
     */
    public void updateAfterScript(AfterScriptEntity afterScriptEntity){
        jpaTemplate.update(afterScriptEntity);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteAfterScript(String id){
        jpaTemplate.delete(AfterScriptEntity.class,id);
    }

    public void deleteAfterScript(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public AfterScriptEntity findAfterScript(String id){
        return jpaTemplate.findOne(AfterScriptEntity.class,id);
    }

    /**
    * findAllAfterScript
    * @return
    */
    public List<AfterScriptEntity> findAllAfterScript() {
        return jpaTemplate.findAll(AfterScriptEntity.class);
    }

    public List<AfterScriptEntity> findAfterScriptList(List<String> idList) {
        return jpaTemplate.findList(AfterScriptEntity.class,idList);
    }

    public List<AfterScriptEntity> findAfterScriptList(AfterScriptUnitQuery afterScriptUnitQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(AfterScriptEntity.class)
                .eq("apiUnitId", afterScriptUnitQuery.getApiUnitId())
                .orders(afterScriptUnitQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, AfterScriptEntity.class);
    }

    public Pagination<AfterScriptEntity> findAfterScriptPage(AfterScriptUnitQuery afterScriptUnitQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(AfterScriptEntity.class)
                .eq("apiUnitId", afterScriptUnitQuery.getApiUnitId())
                .orders(afterScriptUnitQuery.getOrderParams())
                .pagination(afterScriptUnitQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, AfterScriptEntity.class);
    }
}