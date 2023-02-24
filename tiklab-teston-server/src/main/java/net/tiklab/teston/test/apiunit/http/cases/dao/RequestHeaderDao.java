package net.tiklab.teston.test.apiunit.http.cases.dao;

import net.tiklab.core.page.Pagination;
import net.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import net.tiklab.dal.jpa.criterial.condition.QueryCondition;
import net.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import net.tiklab.teston.test.apiunit.http.cases.entity.RequestHeaderEntity;
import net.tiklab.teston.test.apiunit.http.cases.model.RequestHeaderQuery;
import net.tiklab.dal.jpa.JpaTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * RequestHeaderDao
 */
@Repository
public class RequestHeaderDao{

    private static Logger logger = LoggerFactory.getLogger(RequestHeaderDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param requestHeaderEntity
     * @return
     */
    public String createRequestHeader(RequestHeaderEntity requestHeaderEntity) {
        return jpaTemplate.save(requestHeaderEntity,String.class);
    }

    /**
     * 更新
     * @param requestHeaderEntity
     */
    public void updateRequestHeader(RequestHeaderEntity requestHeaderEntity){
        jpaTemplate.update(requestHeaderEntity);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteRequestHeader(String id){
        jpaTemplate.delete(RequestHeaderEntity.class,id);
    }

    public void deleteRequestHeader(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public RequestHeaderEntity findRequestHeader(String id){
        return jpaTemplate.findOne(RequestHeaderEntity.class,id);
    }

    /**
    * findAllRequestHeader
    * @return
    */
    public List<RequestHeaderEntity> findAllRequestHeader() {
        return jpaTemplate.findAll(RequestHeaderEntity.class);
    }

    public List<RequestHeaderEntity> findRequestHeaderList(List<String> idList) {
        return jpaTemplate.findList(RequestHeaderEntity.class,idList);
    }

    public List<RequestHeaderEntity> findRequestHeaderList(RequestHeaderQuery requestHeaderQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(RequestHeaderEntity.class)
                .eq("apiUnitId", requestHeaderQuery.getApiUnitId())
                .orders(requestHeaderQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, RequestHeaderEntity.class);
    }

    public Pagination<RequestHeaderEntity> findRequestHeaderPage(RequestHeaderQuery requestHeaderQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(RequestHeaderEntity.class)
                .eq("apiUnitId", requestHeaderQuery.getApiUnitId())
                .orders(requestHeaderQuery.getOrderParams())
                .pagination(requestHeaderQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(requestHeaderQuery, RequestHeaderEntity.class);
    }
}