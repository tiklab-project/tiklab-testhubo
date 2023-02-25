package net.tiklab.teston.test.api.http.unit.http.cases.dao;

import net.tiklab.core.page.Pagination;
import net.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import net.tiklab.dal.jpa.criterial.condition.QueryCondition;
import net.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import net.tiklab.teston.test.api.http.unit.http.cases.entity.RequestBodyEntity;
import net.tiklab.teston.test.api.http.unit.cases.model.RequestBodyQuery;
import net.tiklab.dal.jpa.JpaTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * RequestBodyDao
 */
@Repository
public class RequestBodyDao{

    private static Logger logger = LoggerFactory.getLogger(RequestBodyDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param requestBodyEntity
     * @return
     */
    public String createRequestBody(RequestBodyEntity requestBodyEntity) {
        return jpaTemplate.save(requestBodyEntity,String.class);
    }

    /**
     * 更新
     * @param requestBodyEntity
     */
    public void updateRequestBody(RequestBodyEntity requestBodyEntity){
        jpaTemplate.update(requestBodyEntity);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteRequestBody(String id){
        jpaTemplate.delete(RequestBodyEntity.class,id);
    }

    public void deleteRequestBody(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public RequestBodyEntity findRequestBody(String id){
        return jpaTemplate.findOne(RequestBodyEntity.class,id);
    }

    /**
    * findAllRequestBody
    * @return
    */
    public List<RequestBodyEntity> findAllRequestBody() {
        return jpaTemplate.findAll(RequestBodyEntity.class);
    }

    public List<RequestBodyEntity> findRequestBodyList(List<String> idList) {
        return jpaTemplate.findList(RequestBodyEntity.class,idList);
    }

    public List<RequestBodyEntity> findRequestBodyList(RequestBodyQuery requestBodyQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(RequestBodyEntity.class)
                .eq("apiUnitId", requestBodyQuery.getApiUnitId())
                .orders(requestBodyQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, RequestBodyEntity.class);
    }

    public Pagination<RequestBodyEntity> findRequestBodyPage(RequestBodyQuery requestBodyQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(RequestBodyEntity.class)
                .eq("apiUnitId", requestBodyQuery.getApiUnitId())
                .orders(requestBodyQuery.getOrderParams())
                .pagination(requestBodyQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, RequestBodyEntity.class);
    }
}