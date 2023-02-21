package net.tiklab.teston.apitest.http.unittest.dao;

import net.tiklab.core.page.Pagination;
import net.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import net.tiklab.dal.jpa.criterial.condition.QueryCondition;
import net.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import net.tiklab.teston.apitest.http.unittest.entity.FormParamEntity;
import net.tiklab.teston.apitest.http.unittest.model.FormParamQuery;
import net.tiklab.dal.jpa.JpaTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * FormParamDao
 */
@Repository
public class FormParamDao{

    private static Logger logger = LoggerFactory.getLogger(FormParamDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param formParamEntity
     * @return
     */
    public String createFormParam(FormParamEntity formParamEntity) {
        return jpaTemplate.save(formParamEntity,String.class);
    }

    /**
     * 更新
     * @param formParamEntity
     */
    public void updateFormParam(FormParamEntity formParamEntity){
        jpaTemplate.update(formParamEntity);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteFormParam(String id){
        jpaTemplate.delete(FormParamEntity.class,id);
    }

    public void deleteFormParam(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public FormParamEntity findFormParam(String id){
        return jpaTemplate.findOne(FormParamEntity.class,id);
    }

    /**
    * findAllFormParam
    * @return
    */
    public List<FormParamEntity> findAllFormParam() {
        return jpaTemplate.findAll(FormParamEntity.class);
    }

    public List<FormParamEntity> findFormParamList(List<String> idList) {
        return jpaTemplate.findList(FormParamEntity.class,idList);
    }

    public List<FormParamEntity> findFormParamList(FormParamQuery formParamQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(FormParamEntity.class)
                .eq("apiUnitId",formParamQuery.getApiUnitId())
                .orders(formParamQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, FormParamEntity.class);
    }

    public Pagination<FormParamEntity> findFormParamPage(FormParamQuery formParamQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(FormParamEntity.class)
                .eq("apiUnitId",formParamQuery.getApiUnitId())
                .orders(formParamQuery.getOrderParams())
                .pagination(formParamQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, FormParamEntity.class);
    }
}