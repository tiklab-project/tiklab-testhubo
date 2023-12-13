package io.thoughtware.teston.test.apix.http.unit.cases.dao;

import io.thoughtware.core.page.Pagination;
import io.thoughtware.dal.jpa.criterial.condition.DeleteCondition;
import io.thoughtware.dal.jpa.criterial.condition.QueryCondition;
import io.thoughtware.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.thoughtware.dal.jpa.JpaTemplate;
import io.thoughtware.teston.test.apix.http.unit.cases.model.JsonResponseUnitQuery;
import io.thoughtware.teston.test.apix.http.unit.cases.entity.JsonResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 响应中json 数据访问
 */
@Repository
public class JsonResponseDao{

    private static Logger logger = LoggerFactory.getLogger(JsonResponseDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建响应中json
     * @param jsonResponseEntity
     * @return
     */
    public String createJsonResponse(JsonResponseEntity jsonResponseEntity) {
        return jpaTemplate.save(jsonResponseEntity,String.class);
    }

    /**
     * 更新响应中json
     * @param jsonResponseEntity
     */
    public void updateJsonResponse(JsonResponseEntity jsonResponseEntity){
        jpaTemplate.update(jsonResponseEntity);
    }

    /**
     * 删除响应中json
     * @param id
     */
    public void deleteJsonResponse(String id){
        jpaTemplate.delete(JsonResponseEntity.class,id);
    }

    public void deleteJsonResponse(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找响应中json
     * @param id
     * @return
     */
    public JsonResponseEntity findJsonResponse(String id){
        return jpaTemplate.findOne(JsonResponseEntity.class,id);
    }

    /**
    * 查找所有响应中json
    * @return
    */
    public List<JsonResponseEntity> findAllJsonResponse() {
        return jpaTemplate.findAll(JsonResponseEntity.class);
    }

    public List<JsonResponseEntity> findJsonResponseList(List<String> idList) {
        return jpaTemplate.findList(JsonResponseEntity.class,idList);
    }

    /**
     * 根据查询参数查找查询响应中json列表
     * @param jsonResponseUnitQuery
     * @return
     */
    public List<JsonResponseEntity> findJsonResponseList(JsonResponseUnitQuery jsonResponseUnitQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(JsonResponseEntity.class)
                .eq("apiUnitId", jsonResponseUnitQuery.getApiUnitId())
                .orders(jsonResponseUnitQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, JsonResponseEntity.class);
    }

    /**
     * 根据查询参数查找按分页查询响应中json
     * @param jsonResponseUnitQuery
     * @return
     */
    public Pagination<JsonResponseEntity> findJsonResponsePage(JsonResponseUnitQuery jsonResponseUnitQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(JsonResponseEntity.class)
                .eq("apiUnitId", jsonResponseUnitQuery.getApiUnitId())
                .orders(jsonResponseUnitQuery.getOrderParams())
                .pagination(jsonResponseUnitQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, JsonResponseEntity.class);
    }
}