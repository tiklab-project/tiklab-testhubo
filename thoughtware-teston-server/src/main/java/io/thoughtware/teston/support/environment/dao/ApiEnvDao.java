package io.thoughtware.teston.support.environment.dao;

import io.thoughtware.teston.support.environment.entity.ApiEnvEntity;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.dal.jpa.criterial.condition.DeleteCondition;
import io.thoughtware.dal.jpa.criterial.condition.QueryCondition;
import io.thoughtware.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.thoughtware.teston.support.environment.model.ApiEnvQuery;
import io.thoughtware.dal.jpa.JpaTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 接口环境 数据访问
 */
@Repository
public class ApiEnvDao {

    private static Logger logger = LoggerFactory.getLogger(ApiEnvDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建接口环境
     * @param apiEnvEntity
     * @return
     */
    public String createApiEnv(ApiEnvEntity apiEnvEntity) {
        return jpaTemplate.save(apiEnvEntity,String.class);
    }

    /**
     * 更新接口环境
     * @param apiEnvEntity
     */
    public void updateApiEnv(ApiEnvEntity apiEnvEntity){
        jpaTemplate.update(apiEnvEntity);
    }

    /**
     * 删除接口环境
     * @param id
     */
    public void deleteApiEnv(String id){
        jpaTemplate.delete(ApiEnvEntity.class,id);
    }

    public void deleteApiEnv(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找接口环境
     * @param id
     * @return
     */
    public ApiEnvEntity findApiEnv(String id){
        return jpaTemplate.findOne(ApiEnvEntity.class,id);
    }

    /**
    * 查找所有接口环境
    * @return
    */
    public List<ApiEnvEntity> findAllApiEnv() {
        return jpaTemplate.findAll(ApiEnvEntity.class);
    }

    public List<ApiEnvEntity> findApiEnvList(List<String> idList) {
        return jpaTemplate.findList(ApiEnvEntity.class,idList);
    }

    /**
     * 根据查询参数查询接口环境列表
     * @param apiEnvQuery
     * @return
     */
    public List<ApiEnvEntity> findApiEnvList(ApiEnvQuery apiEnvQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ApiEnvEntity.class)
                .eq("repositoryId", apiEnvQuery.getRepositoryId())
                .orders(apiEnvQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, ApiEnvEntity.class);
    }

    /**
     * 根据查询参数按分页查询接口环境
     * @param apiEnvQuery
     * @return
     */
    public Pagination<ApiEnvEntity> findApiEnvPage(ApiEnvQuery apiEnvQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ApiEnvEntity.class)
                .eq("repositoryId", apiEnvQuery.getRepositoryId())
                .orders(apiEnvQuery.getOrderParams())
                .pagination(apiEnvQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, ApiEnvEntity.class);
    }
}