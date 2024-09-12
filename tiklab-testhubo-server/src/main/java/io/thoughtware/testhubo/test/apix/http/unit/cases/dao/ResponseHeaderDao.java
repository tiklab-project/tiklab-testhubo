package io.thoughtware.testhubo.test.apix.http.unit.cases.dao;

import io.thoughtware.core.page.Pagination;
import io.thoughtware.dal.jpa.criterial.condition.DeleteCondition;
import io.thoughtware.dal.jpa.criterial.condition.QueryCondition;
import io.thoughtware.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.thoughtware.dal.jpa.JpaTemplate;
import io.thoughtware.testhubo.test.apix.http.unit.cases.model.ResponseHeaderUnitQuery;
import io.thoughtware.testhubo.test.apix.http.unit.cases.entity.ResponseHeaderEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 响应头 数据访问
 */
@Repository
public class ResponseHeaderDao{

    private static Logger logger = LoggerFactory.getLogger(ResponseHeaderDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建响应头
     * @param responseHeaderEntity
     * @return
     */
    public String createResponseHeader(ResponseHeaderEntity responseHeaderEntity) {
        return jpaTemplate.save(responseHeaderEntity,String.class);
    }

    /**
     * 更新响应头
     * @param responseHeaderEntity
     */
    public void updateResponseHeader(ResponseHeaderEntity responseHeaderEntity){
        jpaTemplate.update(responseHeaderEntity);
    }

    /**
     * 删除响应头
     * @param id
     */
    public void deleteResponseHeader(String id){
        jpaTemplate.delete(ResponseHeaderEntity.class,id);
    }

    public void deleteResponseHeader(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找响应头
     * @param id
     * @return
     */
    public ResponseHeaderEntity findResponseHeader(String id){
        return jpaTemplate.findOne(ResponseHeaderEntity.class,id);
    }

    /**
    * 查找所有响应头
    * @return
    */
    public List<ResponseHeaderEntity> findAllResponseHeader() {
        return jpaTemplate.findAll(ResponseHeaderEntity.class);
    }

    public List<ResponseHeaderEntity> findResponseHeaderList(List<String> idList) {
        return jpaTemplate.findList(ResponseHeaderEntity.class,idList);
    }

    /**
     * 根据查询参数查询响应头列表
     * @param responseHeaderUnitQuery
     * @return
     */
    public List<ResponseHeaderEntity> findResponseHeaderList(ResponseHeaderUnitQuery responseHeaderUnitQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ResponseHeaderEntity.class)
                .eq("apiUnitId", responseHeaderUnitQuery.getApiUnitId())
                .orders(responseHeaderUnitQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, ResponseHeaderEntity.class);
    }

    /**
     * 根据查询参数按分页查询响应头
     * @param responseHeaderUnitQuery
     * @return
     */
    public Pagination<ResponseHeaderEntity> findResponseHeaderPage(ResponseHeaderUnitQuery responseHeaderUnitQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ResponseHeaderEntity.class)
                .eq("apiUnitId", responseHeaderUnitQuery.getApiUnitId())
                .orders(responseHeaderUnitQuery.getOrderParams())
                .pagination(responseHeaderUnitQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, ResponseHeaderEntity.class);
    }
}