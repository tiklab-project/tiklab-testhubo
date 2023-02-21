package net.tiklab.teston.repository.dao;

import net.tiklab.core.page.Pagination;
import net.tiklab.dal.jpa.JpaTemplate;
import net.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import net.tiklab.dal.jpa.criterial.condition.QueryCondition;
import net.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import net.tiklab.teston.repository.entity.RepositoryFollowEntity;
import net.tiklab.teston.repository.model.RepositoryFollowQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * RepositoryFollowDao
 */
@Repository
public class RepositoryFollowDao{

    private static Logger logger = LoggerFactory.getLogger(RepositoryFollowDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param repositoryFollowEntity
     * @return
     */
    public String createRepositoryFollow(RepositoryFollowEntity repositoryFollowEntity) {
        return jpaTemplate.save(repositoryFollowEntity,String.class);
    }

    /**
     * 更新
     * @param repositoryFollowEntity
     */
    public void updateRepositoryFollow(RepositoryFollowEntity repositoryFollowEntity){
        jpaTemplate.update(repositoryFollowEntity);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteRepositoryFollow(String id){
        jpaTemplate.delete(RepositoryFollowEntity.class,id);
    }

    public void deleteRepositoryFollow(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public RepositoryFollowEntity findRepositoryFollow(String id){
        return jpaTemplate.findOne(RepositoryFollowEntity.class,id);
    }

    /**
    * findAllRepositoryFollow
    * @return
    */
    public List<RepositoryFollowEntity> findAllRepositoryFollow() {
        return jpaTemplate.findAll(RepositoryFollowEntity.class);
    }

    public List<RepositoryFollowEntity> findRepositoryFollowList(List<String> idList) {
        return jpaTemplate.findList(RepositoryFollowEntity.class,idList);
    }

    public List<RepositoryFollowEntity> findRepositoryFollowList(RepositoryFollowQuery repositoryFollowQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(RepositoryFollowEntity.class)
                .eq("userId", repositoryFollowQuery.getUserId())
                .orders(repositoryFollowQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition,RepositoryFollowEntity.class);
    }

    public Pagination<RepositoryFollowEntity> findRepositoryFollowPage(RepositoryFollowQuery repositoryFollowQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(RepositoryFollowEntity.class)
                .eq("userId", repositoryFollowQuery.getUserId())
                .orders(repositoryFollowQuery.getOrderParams())
                .pagination(repositoryFollowQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition,RepositoryFollowEntity.class);
    }
}