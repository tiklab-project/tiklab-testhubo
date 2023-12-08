package io.thoughtware.teston.support.environment.dao;

import io.thoughtware.teston.support.environment.entity.WebEnvEntity;
import io.thoughtware.teston.support.environment.model.WebEnvQuery;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.dal.jpa.criterial.condition.QueryCondition;
import io.thoughtware.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.thoughtware.dal.jpa.JpaTemplate;
import io.thoughtware.dal.jpa.criterial.condition.DeleteCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Web环境 数据访问
 */
@Repository
public class WebEnvDao{

    private static Logger logger = LoggerFactory.getLogger(WebEnvDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建Web环境
     * @param webEnvEntity
     * @return
     */
    public String createWebEnv(WebEnvEntity webEnvEntity) {
        return jpaTemplate.save(webEnvEntity,String.class);
    }

    /**
     * 更新Web环境
     * @param webEnvEntity
     */
    public void updateWebEnv(WebEnvEntity webEnvEntity){
        jpaTemplate.update(webEnvEntity);
    }

    /**
     * 删除Web环境
     * @param id
     */
    public void deleteWebEnv(String id){
        jpaTemplate.delete(WebEnvEntity.class,id);
    }

    public void deleteWebEnv(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找Web环境
     * @param id
     * @return
     */
    public WebEnvEntity findWebEnv(String id){
        return jpaTemplate.findOne(WebEnvEntity.class,id);
    }

    /**
    * 查找所有Web环境
    * @return
    */
    public List<WebEnvEntity> findAllWebEnv() {
        return jpaTemplate.findAll(WebEnvEntity.class);
    }

    public List<WebEnvEntity> findWebEnvList(List<String> idList) {
        return jpaTemplate.findList(WebEnvEntity.class,idList);
    }

    /**
     * 根据查询参数查询Web环境列表
     * @param webEnvQuery
     * @return
     */
    public List<WebEnvEntity> findWebEnvList(WebEnvQuery webEnvQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(WebEnvEntity.class)
                .eq("repositoryId", webEnvQuery.getRepositoryId())
                .orders(webEnvQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition,WebEnvEntity.class);
    }

    /**
     * 根据查询参数按分页查询Web环境
     * @param webEnvQuery
     * @return
     */
    public Pagination<WebEnvEntity> findWebEnvPage(WebEnvQuery webEnvQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(WebEnvEntity.class)
                .eq("repositoryId", webEnvQuery.getRepositoryId())
                .orders(webEnvQuery.getOrderParams())
                .pagination(webEnvQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition,WebEnvEntity.class);
    }
}