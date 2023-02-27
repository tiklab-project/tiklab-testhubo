package net.tiklab.teston.manager.support.environment.dao;

import net.tiklab.core.page.Pagination;
import net.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import net.tiklab.dal.jpa.criterial.condition.QueryCondition;
import net.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import net.tiklab.teston.manager.support.environment.entity.ApiEnvEntity;
import net.tiklab.teston.manager.support.environment.model.ApiEnvQuery;
import net.tiklab.dal.jpa.JpaTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ApiEnvDao
 */
@Repository
public class ApiEnvDao {

    private static Logger logger = LoggerFactory.getLogger(ApiEnvDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param apiEnvEntity
     * @return
     */
    public String createApiEnv(ApiEnvEntity apiEnvEntity) {
        return jpaTemplate.save(apiEnvEntity,String.class);
    }

    /**
     * 更新
     * @param apiEnvEntity
     */
    public void updateApiEnv(ApiEnvEntity apiEnvEntity){
        jpaTemplate.update(apiEnvEntity);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteApiEnv(String id){
        jpaTemplate.delete(ApiEnvEntity.class,id);
    }

    public void deleteApiEnv(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public ApiEnvEntity findApiEnv(String id){
        return jpaTemplate.findOne(ApiEnvEntity.class,id);
    }

    /**
    * findAllApiEnv
    * @return
    */
    public List<ApiEnvEntity> findAllApiEnv() {
        return jpaTemplate.findAll(ApiEnvEntity.class);
    }

    public List<ApiEnvEntity> findApiEnvList(List<String> idList) {
        return jpaTemplate.findList(ApiEnvEntity.class,idList);
    }

    public List<ApiEnvEntity> findApiEnvList(ApiEnvQuery apiEnvQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ApiEnvEntity.class)
                .eq("repositoryId", apiEnvQuery.getRepositoryId())
                .orders(apiEnvQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, ApiEnvEntity.class);
    }

    public Pagination<ApiEnvEntity> findApiEnvPage(ApiEnvQuery apiEnvQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ApiEnvEntity.class)
                .eq("repositoryId", apiEnvQuery.getRepositoryId())
                .orders(apiEnvQuery.getOrderParams())
                .pagination(apiEnvQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, ApiEnvEntity.class);
    }
}