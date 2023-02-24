package net.tiklab.teston.test.apiunit.http.cases.dao;

import net.tiklab.core.page.Pagination;
import net.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import net.tiklab.dal.jpa.criterial.condition.QueryCondition;
import net.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import net.tiklab.teston.test.apiunit.http.cases.entity.PreScriptEntity;
import net.tiklab.teston.test.apiunit.http.cases.model.PreScriptQuery;
import net.tiklab.dal.jpa.JpaTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * PreScriptDao
 */
@Repository
public class PreScriptDao{

    private static Logger logger = LoggerFactory.getLogger(PreScriptDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param preScriptEntity
     * @return
     */
    public String createPreScript(PreScriptEntity preScriptEntity) {
        return jpaTemplate.save(preScriptEntity,String.class);
    }

    /**
     * 更新
     * @param preScriptEntity
     */
    public void updatePreScript(PreScriptEntity preScriptEntity){
        jpaTemplate.update(preScriptEntity);
    }

    /**
     * 删除
     * @param id
     */
    public void deletePreScript(String id){
        jpaTemplate.delete(PreScriptEntity.class,id);
    }

    public void deletePreScript(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public PreScriptEntity findPreScript(String id){
        return jpaTemplate.findOne(PreScriptEntity.class,id);
    }

    /**
    * findAllPreScript
    * @return
    */
    public List<PreScriptEntity> findAllPreScript() {
        return jpaTemplate.findAll(PreScriptEntity.class);
    }

    public List<PreScriptEntity> findPreScriptList(List<String> idList) {
        return jpaTemplate.findList(PreScriptEntity.class,idList);
    }

    public List<PreScriptEntity> findPreScriptList(PreScriptQuery preScriptQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(PreScriptEntity.class)
                .eq("apiUnitId", preScriptQuery.getApiUnitId())
                .orders(preScriptQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, PreScriptEntity.class);
    }

    public Pagination<PreScriptEntity> findPreScriptPage(PreScriptQuery preScriptQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(PreScriptEntity.class)
                .eq("apiUnitId", preScriptQuery.getApiUnitId())
                .orders(preScriptQuery.getOrderParams())
                .pagination(preScriptQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, PreScriptEntity.class);
    }
}