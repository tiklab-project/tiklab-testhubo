package net.tiklab.teston.support.environment.dao;

import net.tiklab.core.page.Pagination;
import net.tiklab.dal.jpa.criterial.condition.QueryCondition;
import net.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import net.tiklab.teston.support.environment.entity.AppEnvEntity;
import net.tiklab.teston.support.environment.model.AppEnvQuery;
import net.tiklab.dal.jpa.JpaTemplate;
import net.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * AppEnvDao
 */
@Repository
public class AppEnvDao{

    private static Logger logger = LoggerFactory.getLogger(AppEnvDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param appEnvEntity
     * @return
     */
    public String createAppEnv(AppEnvEntity appEnvEntity) {
        return jpaTemplate.save(appEnvEntity,String.class);
    }

    /**
     * 更新
     * @param appEnvEntity
     */
    public void updateAppEnv(AppEnvEntity appEnvEntity){
        jpaTemplate.update(appEnvEntity);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteAppEnv(String id){
        jpaTemplate.delete(AppEnvEntity.class,id);
    }

    public void deleteAppEnv(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public AppEnvEntity findAppEnv(String id){
        return jpaTemplate.findOne(AppEnvEntity.class,id);
    }

    /**
    * findAllAppEnv
    * @return
    */
    public List<AppEnvEntity> findAllAppEnv() {
        return jpaTemplate.findAll(AppEnvEntity.class);
    }

    public List<AppEnvEntity> findAppEnvList(List<String> idList) {
        return jpaTemplate.findList(AppEnvEntity.class,idList);
    }

    public List<AppEnvEntity> findAppEnvList(AppEnvQuery appEnvQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(AppEnvEntity.class)
                .eq("repositoryId", appEnvQuery.getRepositoryId())
                .orders(appEnvQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition,AppEnvEntity.class);
    }

    public Pagination<AppEnvEntity> findAppEnvPage(AppEnvQuery appEnvQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(AppEnvEntity.class)
                .eq("repositoryId", appEnvQuery.getRepositoryId())
                .orders(appEnvQuery.getOrderParams())
                .pagination(appEnvQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition,AppEnvEntity.class);
    }
}