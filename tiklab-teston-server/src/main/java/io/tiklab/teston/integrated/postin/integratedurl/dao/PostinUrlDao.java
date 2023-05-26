package io.tiklab.teston.integrated.postin.integratedurl.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.teston.integrated.postin.integratedurl.entity.IntegratedUrlEntity;
import io.tiklab.teston.integrated.postin.integratedurl.model.IntegratedUrlQuery;
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
     * @param integratedUrlEntity
     * @return
     */
    public String createPostinUrl(IntegratedUrlEntity integratedUrlEntity) {
        return jpaTemplate.save(integratedUrlEntity,String.class);
    }

    /**
     * 更新postinUrl配置
     * @param integratedUrlEntity
     */
    public void updatePostinUrl(IntegratedUrlEntity integratedUrlEntity){
        jpaTemplate.update(integratedUrlEntity);
    }

    /**
     * 删除postinUrl配置
     * @param id
     */
    public void deletePostinUrl(String id){
        jpaTemplate.delete(IntegratedUrlEntity.class,id);
    }

    public void deletePostinUrl(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找postinUrl配置
     * @param id
     * @return
     */
    public IntegratedUrlEntity findPostinUrl(String id){
        return jpaTemplate.findOne(IntegratedUrlEntity.class,id);
    }

    /**
    * 查找所有postinUrl配置
    * @return
    */
    public List<IntegratedUrlEntity> findAllPostinUrl() {
        return jpaTemplate.findAll(IntegratedUrlEntity.class);
    }

    public List<IntegratedUrlEntity> findPostinUrlList(List<String> idList) {
        return jpaTemplate.findList(IntegratedUrlEntity.class,idList);
    }

    /**
     * 根据查询参数查询postinUrl配置列表
     * @param integratedUrlQuery
     * @return
     */
    public List<IntegratedUrlEntity> findPostinUrlList(IntegratedUrlQuery integratedUrlQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(IntegratedUrlEntity.class)
                .eq("userId", integratedUrlQuery.getUserId())
                .eq("projectName",integratedUrlQuery.getProjectName())
                .orders(integratedUrlQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, IntegratedUrlEntity.class);
    }

    /**
     * 根据查询参数按分页查询postinUrl配置
     * @param integratedUrlQuery
     * @return
     */
    public Pagination<IntegratedUrlEntity> findPostinUrlPage(IntegratedUrlQuery integratedUrlQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(IntegratedUrlEntity.class)
                .eq("userId", integratedUrlQuery.getUserId())
                .eq("projectName",integratedUrlQuery.getProjectName())
                .orders(integratedUrlQuery.getOrderParams())
                .pagination(integratedUrlQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, IntegratedUrlEntity.class);
    }
}