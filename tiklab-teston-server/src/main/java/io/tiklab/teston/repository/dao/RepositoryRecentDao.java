package io.tiklab.teston.repository.dao;

import io.tiklab.teston.repository.entity.RepositoryRecentEntity;
import io.tiklab.teston.repository.model.RepositoryRecentQuery;
import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 最近访问仓库  数据访问
 */
@Repository
public class RepositoryRecentDao{

    private static Logger logger = LoggerFactory.getLogger(RepositoryRecentDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建最近访问仓库
     * @param repositoryRecentEntity
     * @return
     */
    public String createRepositoryRecent(RepositoryRecentEntity repositoryRecentEntity) {
        return jpaTemplate.save(repositoryRecentEntity,String.class);
    }

    /**
     * 更新最近访问仓库
     * @param repositoryRecentEntity
     */
    public void updateRepositoryRecent(RepositoryRecentEntity repositoryRecentEntity){
        jpaTemplate.update(repositoryRecentEntity);
    }

    /**
     * 删除最近访问仓库
     * @param id
     */
    public void deleteRepositoryRecent(String id){
        jpaTemplate.delete(RepositoryRecentEntity.class,id);
    }

    public void deleteRepositoryRecent(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找最近访问仓库
     * @param id
     * @return
     */
    public RepositoryRecentEntity findRepositoryRecent(String id){
        return jpaTemplate.findOne(RepositoryRecentEntity.class,id);
    }

    /**
    * 查找所有最近访问仓库
    * @return
    */
    public List<RepositoryRecentEntity> findAllRepositoryRecent() {
        return jpaTemplate.findAll(RepositoryRecentEntity.class);
    }

    public List<RepositoryRecentEntity> findRepositoryRecentList(List<String> idList) {
        return jpaTemplate.findList(RepositoryRecentEntity.class,idList);
    }

    /**
     * 查询最近访问仓库列表
     * @param repositoryRecentQuery
     * @return
     */
    public List<RepositoryRecentEntity> findRepositoryRecentList(RepositoryRecentQuery repositoryRecentQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(RepositoryRecentEntity.class)
                .eq("repositoryId", repositoryRecentQuery.getRepositoryId())
                .eq("userId", repositoryRecentQuery.getUserId())
                .orders(repositoryRecentQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition,RepositoryRecentEntity.class);
    }

    /**
     * 按分页查询最近访问仓库
     * @param repositoryRecentQuery
     * @return
     */
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