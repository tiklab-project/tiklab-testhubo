package net.tiklab.teston.apix.http.unit.cases.dao;

import net.tiklab.core.page.Pagination;
import net.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import net.tiklab.dal.jpa.criterial.condition.QueryCondition;
import net.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import net.tiklab.dal.jpa.JpaTemplate;
import net.tiklab.teston.apix.http.unit.cases.model.RequestBodyQuery;
import net.tiklab.teston.apix.http.unit.cases.entity.RequestBodyEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 请求体 数据访问
 */
@Repository
public class RequestBodyDao{

    private static Logger logger = LoggerFactory.getLogger(RequestBodyDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建请求体
     * @param requestBodyEntity
     * @return
     */
    public String createRequestBody(RequestBodyEntity requestBodyEntity) {
        return jpaTemplate.save(requestBodyEntity,String.class);
    }

    /**
     * 更新请求体
     * @param requestBodyEntity
     */
    public void updateRequestBody(RequestBodyEntity requestBodyEntity){
        jpaTemplate.update(requestBodyEntity);
    }

    /**
     * 删除请求体
     * @param id
     */
    public void deleteRequestBody(String id){
        jpaTemplate.delete(RequestBodyEntity.class,id);
    }

    public void deleteRequestBody(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找请求体
     * @param id
     * @return
     */
    public RequestBodyEntity findRequestBody(String id){
        return jpaTemplate.findOne(RequestBodyEntity.class,id);
    }

    /**
    * 查找所有请求体
    * @return
    */
    public List<RequestBodyEntity> findAllRequestBody() {
        return jpaTemplate.findAll(RequestBodyEntity.class);
    }

    public List<RequestBodyEntity> findRequestBodyList(List<String> idList) {
        return jpaTemplate.findList(RequestBodyEntity.class,idList);
    }

    /**
     * 根据查询参数查询请求体列表
     * @param requestBodyQuery
     * @return
     */
    public List<RequestBodyEntity> findRequestBodyList(RequestBodyQuery requestBodyQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(RequestBodyEntity.class)
                .eq("apiUnitId", requestBodyQuery.getApiUnitId())
                .orders(requestBodyQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, RequestBodyEntity.class);
    }

    /**
     * 根据查询参数按分页查询请求体
     * @param requestBodyQuery
     * @return
     */
    public Pagination<RequestBodyEntity> findRequestBodyPage(RequestBodyQuery requestBodyQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(RequestBodyEntity.class)
                .eq("apiUnitId", requestBodyQuery.getApiUnitId())
                .orders(requestBodyQuery.getOrderParams())
                .pagination(requestBodyQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, RequestBodyEntity.class);
    }
}