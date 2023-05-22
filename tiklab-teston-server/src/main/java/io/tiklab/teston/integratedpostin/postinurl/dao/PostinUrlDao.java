package io.tiklab.teston.integratedpostin.postinurl.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.teston.integratedpostin.postinurl.entity.PostinUrlEntity;
import io.tiklab.teston.integratedpostin.postinurl.model.PostinUrlQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * postinUrl配置 数据访问
 */
@Repository
public class PostinUrlDao {

    private static Logger logger = LoggerFactory.getLogger(PostinUrlDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建postinUrl配置
     * @param postinUrlEntity
     * @return
     */
    public String createPostinUrl(PostinUrlEntity postinUrlEntity) {
        return jpaTemplate.save(postinUrlEntity,String.class);
    }

    /**
     * 更新postinUrl配置
     * @param postinUrlEntity
     */
    public void updatePostinUrl(PostinUrlEntity postinUrlEntity){
        jpaTemplate.update(postinUrlEntity);
    }

    /**
     * 删除postinUrl配置
     * @param id
     */
    public void deletePostinUrl(String id){
        jpaTemplate.delete(PostinUrlEntity.class,id);
    }

    public void deletePostinUrl(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找postinUrl配置
     * @param id
     * @return
     */
    public PostinUrlEntity findPostinUrl(String id){
        return jpaTemplate.findOne(PostinUrlEntity.class,id);
    }

    /**
    * 查找所有postinUrl配置
    * @return
    */
    public List<PostinUrlEntity> findAllPostinUrl() {
        return jpaTemplate.findAll(PostinUrlEntity.class);
    }

    public List<PostinUrlEntity> findPostinUrlList(List<String> idList) {
        return jpaTemplate.findList(PostinUrlEntity.class,idList);
    }

    /**
     * 根据查询参数查询postinUrl配置列表
     * @param postinUrlQuery
     * @return
     */
    public List<PostinUrlEntity> findPostinUrlList(PostinUrlQuery postinUrlQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(PostinUrlEntity.class)
                .eq("userId", postinUrlQuery.getUserId())
                .orders(postinUrlQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition,PostinUrlEntity.class);
    }

    /**
     * 根据查询参数按分页查询postinUrl配置
     * @param postinUrlQuery
     * @return
     */
    public Pagination<PostinUrlEntity> findPostinUrlPage(PostinUrlQuery postinUrlQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(PostinUrlEntity.class)
                .eq("userId", postinUrlQuery.getUserId())
                .orders(postinUrlQuery.getOrderParams())
                .pagination(postinUrlQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition,PostinUrlEntity.class);
    }
}