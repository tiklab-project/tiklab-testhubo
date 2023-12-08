package io.thoughtware.teston.support.environment.dao;

import io.thoughtware.teston.support.environment.entity.AppEnvEntity;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.dal.jpa.criterial.condition.QueryCondition;
import io.thoughtware.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.thoughtware.teston.support.environment.model.AppEnvQuery;
import io.thoughtware.dal.jpa.JpaTemplate;
import io.thoughtware.dal.jpa.criterial.condition.DeleteCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * app环境 数据访问
 */
@Repository
public class AppEnvDao{

    private static Logger logger = LoggerFactory.getLogger(AppEnvDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建app环境
     * @param appEnvEntity
     * @return
     */
    public String createAppEnv(AppEnvEntity appEnvEntity) {
        return jpaTemplate.save(appEnvEntity,String.class);
    }

    /**
     * 更新app环境
     * @param appEnvEntity
     */
    public void updateAppEnv(AppEnvEntity appEnvEntity){
        jpaTemplate.update(appEnvEntity);
    }

    /**
     * 删除app环境
     * @param id
     */
    public void deleteAppEnv(String id){
        jpaTemplate.delete(AppEnvEntity.class,id);
    }

    public void deleteAppEnv(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找app环境
     * @param id
     * @return
     */
    public AppEnvEntity findAppEnv(String id){
        return jpaTemplate.findOne(AppEnvEntity.class,id);
    }

    /**
    * 查找所有app环境
    * @return
    */
    public List<AppEnvEntity> findAllAppEnv() {
        return jpaTemplate.findAll(AppEnvEntity.class);
    }

    public List<AppEnvEntity> findAppEnvList(List<String> idList) {
        return jpaTemplate.findList(AppEnvEntity.class,idList);
    }

    /**
     * 根据查询参数查询app环境列表
     * @param appEnvQuery
     * @return
     */
    public List<AppEnvEntity> findAppEnvList(AppEnvQuery appEnvQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(AppEnvEntity.class)
                .eq("repositoryId", appEnvQuery.getRepositoryId())
                .orders(appEnvQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition,AppEnvEntity.class);
    }

    /**
     * 根据查询参数按分页查询app环境
     * @param appEnvQuery
     * @return
     */
    public Pagination<AppEnvEntity> findAppEnvPage(AppEnvQuery appEnvQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(AppEnvEntity.class)
                .eq("repositoryId", appEnvQuery.getRepositoryId())
                .orders(appEnvQuery.getOrderParams())
                .pagination(appEnvQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition,AppEnvEntity.class);
    }
}