package io.thoughtware.teston.test.apix.http.unit.cases.dao;

import io.thoughtware.teston.test.apix.http.unit.cases.entity.PreScriptEntity;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.dal.jpa.criterial.condition.DeleteCondition;
import io.thoughtware.dal.jpa.criterial.condition.QueryCondition;
import io.thoughtware.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.thoughtware.dal.jpa.JpaTemplate;
import io.thoughtware.teston.test.apix.http.unit.cases.model.PreScriptUnitQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 前置脚本 数据访问
 */
@Repository
public class PreScriptDao{

    private static Logger logger = LoggerFactory.getLogger(PreScriptDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建前置脚本
     * @param preScriptEntity
     * @return
     */
    public String createPreScript(PreScriptEntity preScriptEntity) {
        return jpaTemplate.save(preScriptEntity,String.class);
    }

    /**
     * 更新前置脚本
     * @param preScriptEntity
     */
    public void updatePreScript(PreScriptEntity preScriptEntity){
        jpaTemplate.update(preScriptEntity);
    }

    /**
     * 删除前置脚本
     * @param id
     */
    public void deletePreScript(String id){
        jpaTemplate.delete(PreScriptEntity.class,id);
    }

    public void deletePreScript(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找前置脚本
     * @param id
     * @return
     */
    public PreScriptEntity findPreScript(String id){
        return jpaTemplate.findOne(PreScriptEntity.class,id);
    }

    /**
    * 查找所有前置脚本
    * @return
    */
    public List<PreScriptEntity> findAllPreScript() {
        return jpaTemplate.findAll(PreScriptEntity.class);
    }

    public List<PreScriptEntity> findPreScriptList(List<String> idList) {
        return jpaTemplate.findList(PreScriptEntity.class,idList);
    }

    /**
     * 根据查询参数查询前置脚本列表
     * @param preScriptUnitQuery
     * @return
     */
    public List<PreScriptEntity> findPreScriptList(PreScriptUnitQuery preScriptUnitQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(PreScriptEntity.class)
                .eq("apiUnitId", preScriptUnitQuery.getApiUnitId())
                .orders(preScriptUnitQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, PreScriptEntity.class);
    }

    /**
     * 根据查询参数按分页查询前置脚本
     * @param preScriptUnitQuery
     * @return
     */
    public Pagination<PreScriptEntity> findPreScriptPage(PreScriptUnitQuery preScriptUnitQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(PreScriptEntity.class)
                .eq("apiUnitId", preScriptUnitQuery.getApiUnitId())
                .orders(preScriptUnitQuery.getOrderParams())
                .pagination(preScriptUnitQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, PreScriptEntity.class);
    }
}