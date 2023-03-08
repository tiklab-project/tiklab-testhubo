package io.tiklab.teston.test.apix.http.unit.cases.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.teston.test.apix.http.unit.cases.model.FormParamQuery;
import io.tiklab.teston.test.apix.http.unit.cases.entity.FormParamEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * form-data 数据访问
 */
@Repository
public class FormParamDao{

    private static Logger logger = LoggerFactory.getLogger(FormParamDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建form-data
     * @param formParamEntity
     * @return
     */
    public String createFormParam(FormParamEntity formParamEntity) {
        return jpaTemplate.save(formParamEntity,String.class);
    }

    /**
     * 更新form-data
     * @param formParamEntity
     */
    public void updateFormParam(FormParamEntity formParamEntity){
        jpaTemplate.update(formParamEntity);
    }

    /**
     * 删除form-data
     * @param id
     */
    public void deleteFormParam(String id){
        jpaTemplate.delete(FormParamEntity.class,id);
    }

    public void deleteFormParam(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找form-data
     * @param id
     * @return
     */
    public FormParamEntity findFormParam(String id){
        return jpaTemplate.findOne(FormParamEntity.class,id);
    }

    /**
    * 查找所有form-data
    * @return
    */
    public List<FormParamEntity> findAllFormParam() {
        return jpaTemplate.findAll(FormParamEntity.class);
    }

    public List<FormParamEntity> findFormParamList(List<String> idList) {
        return jpaTemplate.findList(FormParamEntity.class,idList);
    }

    /**
     * 根据查询参数查询form-data列表
     * @param formParamQuery
     * @return
     */
    public List<FormParamEntity> findFormParamList(FormParamQuery formParamQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(FormParamEntity.class)
                .eq("apiUnitId",formParamQuery.getApiUnitId())
                .orders(formParamQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, FormParamEntity.class);
    }

    /**
     * 根据查询参数按分页查询form-data
     * @param formParamQuery
     * @return
     */
    public Pagination<FormParamEntity> findFormParamPage(FormParamQuery formParamQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(FormParamEntity.class)
                .eq("apiUnitId",formParamQuery.getApiUnitId())
                .orders(formParamQuery.getOrderParams())
                .pagination(formParamQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, FormParamEntity.class);
    }
}