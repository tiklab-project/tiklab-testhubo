package net.tiklab.testonapix.http.unit.http.cases.dao;

import net.tiklab.core.page.Pagination;
import net.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import net.tiklab.dal.jpa.criterial.condition.QueryCondition;
import net.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import net.tiklab.dal.jpa.JpaTemplate;
import net.tiklab.testonapix.http.unit.cases.model.QueryParamQuery;
import net.tiklab.testonapix.http.unit.http.cases.entity.QueryParamEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * QueryParamDao
 */
@Repository
public class QueryParamDao{

    private static Logger logger = LoggerFactory.getLogger(QueryParamDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param queryParamEntity
     * @return
     */
    public String createQueryParam(QueryParamEntity queryParamEntity) {
        return jpaTemplate.save(queryParamEntity,String.class);
    }

    /**
     * 更新
     * @param queryParamEntity
     */
    public void updateQueryParam(QueryParamEntity queryParamEntity){
        jpaTemplate.update(queryParamEntity);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteQueryParam(String id){
        jpaTemplate.delete(QueryParamEntity.class,id);
    }

    public void deleteQueryParam(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public QueryParamEntity findQueryParam(String id){
        return jpaTemplate.findOne(QueryParamEntity.class,id);
    }

    /**
    * findAllQueryParam
    * @return
    */
    public List<QueryParamEntity> findAllQueryParam() {
        return jpaTemplate.findAll(QueryParamEntity.class);
    }

    public List<QueryParamEntity> findQueryParamList(List<String> idList) {
        return jpaTemplate.findList(QueryParamEntity.class,idList);
    }

    public List<QueryParamEntity> findQueryParamList(QueryParamQuery queryParamQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(QueryParamEntity.class)
                .eq("apiUnitId", queryParamQuery.getApiUnitId())
                .orders(queryParamQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, QueryParamEntity.class);
    }

    public Pagination<QueryParamEntity> findQueryParamPage(QueryParamQuery queryParamQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(QueryParamEntity.class)
                .eq("apiUnitId", queryParamQuery.getApiUnitId())
                .pagination(queryParamQuery.getPageParam())
                .orders(queryParamQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, QueryParamEntity.class);
    }
}