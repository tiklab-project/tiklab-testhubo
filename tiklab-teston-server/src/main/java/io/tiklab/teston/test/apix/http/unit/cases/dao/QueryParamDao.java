package io.tiklab.teston.test.apix.http.unit.cases.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.teston.test.apix.http.unit.cases.model.QueryParamQuery;
import io.tiklab.teston.test.apix.http.unit.cases.entity.QueryParamEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * query 数据访问
 */
@Repository
public class QueryParamDao{

    private static Logger logger = LoggerFactory.getLogger(QueryParamDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建query
     * @param queryParamEntity
     * @return
     */
    public String createQueryParam(QueryParamEntity queryParamEntity) {
        return jpaTemplate.save(queryParamEntity,String.class);
    }

    /**
     * 更新query
     * @param queryParamEntity
     */
    public void updateQueryParam(QueryParamEntity queryParamEntity){
        jpaTemplate.update(queryParamEntity);
    }

    /**
     * 删除query
     * @param id
     */
    public void deleteQueryParam(String id){
        jpaTemplate.delete(QueryParamEntity.class,id);
    }

    public void deleteQueryParam(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找query
     * @param id
     * @return
     */
    public QueryParamEntity findQueryParam(String id){
        return jpaTemplate.findOne(QueryParamEntity.class,id);
    }

    /**
    * 查找所有query
    * @return
    */
    public List<QueryParamEntity> findAllQueryParam() {
        return jpaTemplate.findAll(QueryParamEntity.class);
    }

    public List<QueryParamEntity> findQueryParamList(List<String> idList) {
        return jpaTemplate.findList(QueryParamEntity.class,idList);
    }

    /**
     * 根据查询参数查询query列表
     * @param queryParamQuery
     * @return
     */
    public List<QueryParamEntity> findQueryParamList(QueryParamQuery queryParamQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(QueryParamEntity.class)
                .eq("apiUnitId", queryParamQuery.getApiUnitId())
                .orders(queryParamQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, QueryParamEntity.class);
    }

    /**
     * 根据查询参数按分页查询query
     * @param queryParamQuery
     * @return
     */
    public Pagination<QueryParamEntity> findQueryParamPage(QueryParamQuery queryParamQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(QueryParamEntity.class)
                .eq("apiUnitId", queryParamQuery.getApiUnitId())
                .pagination(queryParamQuery.getPageParam())
                .orders(queryParamQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, QueryParamEntity.class);
    }
}