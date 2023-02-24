package net.tiklab.teston.support.environment.dao;

import net.tiklab.core.page.Pagination;
import net.tiklab.dal.jpa.criterial.condition.QueryCondition;
import net.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import net.tiklab.teston.support.environment.entity.WebEnvEntity;
import net.tiklab.teston.support.environment.model.WebEnvQuery;
import net.tiklab.dal.jpa.JpaTemplate;
import net.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * WebEnvDao
 */
@Repository
public class WebEnvDao{

    private static Logger logger = LoggerFactory.getLogger(WebEnvDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param webEnvEntity
     * @return
     */
    public String createWebEnv(WebEnvEntity webEnvEntity) {
        return jpaTemplate.save(webEnvEntity,String.class);
    }

    /**
     * 更新
     * @param webEnvEntity
     */
    public void updateWebEnv(WebEnvEntity webEnvEntity){
        jpaTemplate.update(webEnvEntity);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteWebEnv(String id){
        jpaTemplate.delete(WebEnvEntity.class,id);
    }

    public void deleteWebEnv(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public WebEnvEntity findWebEnv(String id){
        return jpaTemplate.findOne(WebEnvEntity.class,id);
    }

    /**
    * findAllWebEnv
    * @return
    */
    public List<WebEnvEntity> findAllWebEnv() {
        return jpaTemplate.findAll(WebEnvEntity.class);
    }

    public List<WebEnvEntity> findWebEnvList(List<String> idList) {
        return jpaTemplate.findList(WebEnvEntity.class,idList);
    }

    public List<WebEnvEntity> findWebEnvList(WebEnvQuery webEnvQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(WebEnvEntity.class)
                .eq("repositoryId", webEnvQuery.getRepositoryId())
                .orders(webEnvQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition,WebEnvEntity.class);
    }

    public Pagination<WebEnvEntity> findWebEnvPage(WebEnvQuery webEnvQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(WebEnvEntity.class)
                .eq("repositoryId", webEnvQuery.getRepositoryId())
                .orders(webEnvQuery.getOrderParams())
                .pagination(webEnvQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition,WebEnvEntity.class);
    }
}