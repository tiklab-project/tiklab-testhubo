package io.tiklab.teston.test.apix.http.unit.cases.dao;

import io.tiklab.teston.test.apix.http.unit.cases.entity.RequestHeaderEntity;
import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.teston.test.apix.http.unit.cases.model.RequestHeaderQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 请求头 数据访问
 */
@Repository
public class RequestHeaderDao{

    private static Logger logger = LoggerFactory.getLogger(RequestHeaderDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建请求头
     * @param requestHeaderEntity
     * @return
     */
    public String createRequestHeader(RequestHeaderEntity requestHeaderEntity) {
        return jpaTemplate.save(requestHeaderEntity,String.class);
    }

    /**
     * 更新请求头
     * @param requestHeaderEntity
     */
    public void updateRequestHeader(RequestHeaderEntity requestHeaderEntity){
        jpaTemplate.update(requestHeaderEntity);
    }

    /**
     * 删除请求头
     * @param id
     */
    public void deleteRequestHeader(String id){
        jpaTemplate.delete(RequestHeaderEntity.class,id);
    }

    public void deleteRequestHeader(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找请求头
     * @param id
     * @return
     */
    public RequestHeaderEntity findRequestHeader(String id){
        return jpaTemplate.findOne(RequestHeaderEntity.class,id);
    }

    /**
    * 查找所有请求头
    * @return
    */
    public List<RequestHeaderEntity> findAllRequestHeader() {
        return jpaTemplate.findAll(RequestHeaderEntity.class);
    }

    public List<RequestHeaderEntity> findRequestHeaderList(List<String> idList) {
        return jpaTemplate.findList(RequestHeaderEntity.class,idList);
    }

    /**
     * 根据参数查询请求头列表
     * @param requestHeaderQuery
     * @return
     */
    public List<RequestHeaderEntity> findRequestHeaderList(RequestHeaderQuery requestHeaderQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(RequestHeaderEntity.class)
                .eq("apiUnitId", requestHeaderQuery.getApiUnitId())
                .orders(requestHeaderQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, RequestHeaderEntity.class);
    }

    /**
     * 根据参数按分页查询请求头
     * @param requestHeaderQuery
     * @return
     */
    public Pagination<RequestHeaderEntity> findRequestHeaderPage(RequestHeaderQuery requestHeaderQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(RequestHeaderEntity.class)
                .eq("apiUnitId", requestHeaderQuery.getApiUnitId())
                .orders(requestHeaderQuery.getOrderParams())
                .pagination(requestHeaderQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(requestHeaderQuery, RequestHeaderEntity.class);
    }
}