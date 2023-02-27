package net.tiklab.teston.manager.repository.dao;

import net.tiklab.core.page.Pagination;
import net.tiklab.dal.jpa.criterial.condition.QueryCondition;
import net.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import net.tiklab.teston.manager.repository.entity.RepositoryRecentEntity;
import net.tiklab.teston.manager.repository.model.RepositoryRecentQuery;
import net.tiklab.dal.jpa.JpaTemplate;
import net.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * RepositoryRecentDao
 */
@Repository
public class RepositoryRecentDao{

    private static Logger logger = LoggerFactory.getLogger(RepositoryRecentDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param repositoryRecentEntity
     * @return
     */
    public String createRepositoryRecent(RepositoryRecentEntity repositoryRecentEntity) {
        return jpaTemplate.save(repositoryRecentEntity,String.class);
    }

    /**
     * 更新
     * @param repositoryRecentEntity
     */
    public void updateRepositoryRecent(RepositoryRecentEntity repositoryRecentEntity){
        jpaTemplate.update(repositoryRecentEntity);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteRepositoryRecent(String id){
        jpaTemplate.delete(RepositoryRecentEntity.class,id);
    }

    public void deleteRepositoryRecent(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public RepositoryRecentEntity findRepositoryRecent(String id){
        return jpaTemplate.findOne(RepositoryRecentEntity.class,id);
    }

    /**
    * findAllRepositoryRecent
    * @return
    */
    public List<RepositoryRecentEntity> findAllRepositoryRecent() {
        return jpaTemplate.findAll(RepositoryRecentEntity.class);
    }

    public List<RepositoryRecentEntity> findRepositoryRecentList(List<String> idList) {
        return jpaTemplate.findList(RepositoryRecentEntity.class,idList);
    }

    public List<RepositoryRecentEntity> findRepositoryRecentList(RepositoryRecentQuery repositoryRecentQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(RepositoryRecentEntity.class)
                .eq("repositoryId", repositoryRecentQuery.getRepositoryId())
                .eq("userId", repositoryRecentQuery.getUserId())
                .orders(repositoryRecentQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition,RepositoryRecentEntity.class);
    }

    public Pagination<RepositoryRecentEntity> findRepositoryRecentPage(RepositoryRecentQuery repositoryRecentQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(RepositoryRecentEntity.class)
                .eq("repositoryId", repositoryRecentQuery.getRepositoryId())
                .eq("userId", repositoryRecentQuery.getUserId())
                .orders(repositoryRecentQuery.getOrderParams())
                .pagination(repositoryRecentQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(repositoryRecentQuery,RepositoryRecentEntity.class);
    }
}