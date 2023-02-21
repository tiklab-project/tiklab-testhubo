package net.tiklab.teston.apitest.http.unittest.dao;

import net.tiklab.core.page.Pagination;
import net.tiklab.dal.jpa.JpaTemplate;
import net.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import net.tiklab.dal.jpa.criterial.condition.QueryCondition;
import net.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import net.tiklab.teston.apitest.http.unittest.entity.ApiUnitInstanceBindEntity;
import net.tiklab.teston.apitest.http.unittest.model.ApiUnitInstanceBindQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ApiUnitInstanceBindDao
 */
@Repository
public class ApiUnitInstanceBindDao{

    private static Logger logger = LoggerFactory.getLogger(ApiUnitInstanceBindDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param apiUnitInstanceBindEntity
     * @return
     */
    public String createApiUnitInstanceBind(ApiUnitInstanceBindEntity apiUnitInstanceBindEntity) {
        return jpaTemplate.save(apiUnitInstanceBindEntity,String.class);
    }

    /**
     * 更新
     * @param apiUnitInstanceBindEntity
     */
    public void updateApiUnitInstanceBind(ApiUnitInstanceBindEntity apiUnitInstanceBindEntity){
        jpaTemplate.update(apiUnitInstanceBindEntity);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteApiUnitInstanceBind(String id){
        jpaTemplate.delete(ApiUnitInstanceBindEntity.class,id);
    }

    public void deleteApiUnitInstanceBind(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public ApiUnitInstanceBindEntity findApiUnitInstanceBind(String id){
        return jpaTemplate.findOne(ApiUnitInstanceBindEntity.class,id);
    }

    /**
    * findAllApiUnitInstanceBind
    * @return
    */
    public List<ApiUnitInstanceBindEntity> findAllApiUnitInstanceBind() {
        return jpaTemplate.findAll(ApiUnitInstanceBindEntity.class);
    }

    public List<ApiUnitInstanceBindEntity> findApiUnitInstanceBindList(List<String> idList) {
        return jpaTemplate.findList(ApiUnitInstanceBindEntity.class,idList);
    }

    public List<ApiUnitInstanceBindEntity> findApiUnitInstanceBindList(ApiUnitInstanceBindQuery apiUnitInstanceBindQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ApiUnitInstanceBindEntity.class)
                .eq("apiUnitId", apiUnitInstanceBindQuery.getApiUnitId())
                .orders(apiUnitInstanceBindQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition,ApiUnitInstanceBindEntity.class);
    }

    public Pagination<ApiUnitInstanceBindEntity> findApiUnitInstanceBindPage(ApiUnitInstanceBindQuery apiUnitInstanceBindQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ApiUnitInstanceBindEntity.class)
                .eq("apiUnitId", apiUnitInstanceBindQuery.getApiUnitId())
                .orders(apiUnitInstanceBindQuery.getOrderParams())
                .pagination(apiUnitInstanceBindQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition,ApiUnitInstanceBindEntity.class);
    }
}