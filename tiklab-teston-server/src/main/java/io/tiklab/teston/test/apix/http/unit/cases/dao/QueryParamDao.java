package io.tiklab.teston.test.apix.http.unit.cases.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.teston.test.apix.http.unit.cases.model.QueryParamUnitQuery;
import io.tiklab.teston.test.apix.http.unit.cases.entity.QueryParamsEntity;
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
     * @param queryParamsEntity
     * @return
     */
    public String createQueryParam(QueryParamsEntity queryParamsEntity) {
        return jpaTemplate.save(queryParamsEntity,String.class);
    }

    /**
     * 更新query
     * @param queryParamsEntity
     */
    public void updateQueryParam(QueryParamsEntity queryParamsEntity){
        jpaTemplate.update(queryParamsEntity);
    }

    /**
     * 删除query
     * @param id
     */
    public void deleteQueryParam(String id){
        jpaTemplate.delete(QueryParamsEntity.class,id);
    }

    public void deleteQueryParam(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找query
     * @param id
     * @return
     */
    public QueryParamsEntity findQueryParam(String id){
        return jpaTemplate.findOne(QueryParamsEntity.class,id);
    }

    /**
    * 查找所有query
    * @return
    */
    public List<QueryParamsEntity> findAllQueryParam() {
        return jpaTemplate.findAll(QueryParamsEntity.class);
    }

    public List<QueryParamsEntity> findQueryParamList(List<String> idList) {
        return jpaTemplate.findList(QueryParamsEntity.class,idList);
    }

    /**
     * 根据查询参数查询query列表
     * @param queryParamUnitQuery
     * @return
     */
    public List<QueryParamsEntity> findQueryParamList(QueryParamUnitQuery queryParamUnitQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(QueryParamsEntity.class)
                .eq("apiUnitId", queryParamUnitQuery.getApiUnitId())
                .orders(queryParamUnitQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, QueryParamsEntity.class);
    }

    /**
     * 根据查询参数按分页查询query
     * @param queryParamUnitQuery
     * @return
     */
    public Pagination<QueryParamsEntity> findQueryParamPage(QueryParamUnitQuery queryParamUnitQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(QueryParamsEntity.class)
                .eq("apiUnitId", queryParamUnitQuery.getApiUnitId())
                .pagination(queryParamUnitQuery.getPageParam())
                .orders(queryParamUnitQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, QueryParamsEntity.class);
    }
}