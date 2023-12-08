package io.thoughtware.teston.test.apix.http.unit.cases.dao;

import io.thoughtware.core.page.Pagination;
import io.thoughtware.dal.jpa.criterial.condition.DeleteCondition;
import io.thoughtware.dal.jpa.criterial.condition.QueryCondition;
import io.thoughtware.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.thoughtware.dal.jpa.JpaTemplate;
import io.thoughtware.teston.test.apix.http.unit.cases.entity.FormParamsEntity;
import io.thoughtware.teston.test.apix.http.unit.cases.model.FormParamUnitQuery;
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
     * @param formParamsEntity
     * @return
     */
    public String createFormParam(FormParamsEntity formParamsEntity) {
        return jpaTemplate.save(formParamsEntity,String.class);
    }

    /**
     * 更新form-data
     * @param formParamsEntity
     */
    public void updateFormParam(FormParamsEntity formParamsEntity){
        jpaTemplate.update(formParamsEntity);
    }

    /**
     * 删除form-data
     * @param id
     */
    public void deleteFormParam(String id){
        jpaTemplate.delete(FormParamsEntity.class,id);
    }

    public void deleteFormParam(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找form-data
     * @param id
     * @return
     */
    public FormParamsEntity findFormParam(String id){
        return jpaTemplate.findOne(FormParamsEntity.class,id);
    }

    /**
    * 查找所有form-data
    * @return
    */
    public List<FormParamsEntity> findAllFormParam() {
        return jpaTemplate.findAll(FormParamsEntity.class);
    }

    public List<FormParamsEntity> findFormParamList(List<String> idList) {
        return jpaTemplate.findList(FormParamsEntity.class,idList);
    }

    /**
     * 根据查询参数查询form-data列表
     * @param formParamUnitQuery
     * @return
     */
    public List<FormParamsEntity> findFormParamList(FormParamUnitQuery formParamUnitQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(FormParamsEntity.class)
                .eq("apiUnitId", formParamUnitQuery.getApiUnitId())
                .orders(formParamUnitQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, FormParamsEntity.class);
    }

    /**
     * 根据查询参数按分页查询form-data
     * @param formParamUnitQuery
     * @return
     */
    public Pagination<FormParamsEntity> findFormParamPage(FormParamUnitQuery formParamUnitQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(FormParamsEntity.class)
                .eq("apiUnitId", formParamUnitQuery.getApiUnitId())
                .orders(formParamUnitQuery.getOrderParams())
                .pagination(formParamUnitQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, FormParamsEntity.class);
    }
}