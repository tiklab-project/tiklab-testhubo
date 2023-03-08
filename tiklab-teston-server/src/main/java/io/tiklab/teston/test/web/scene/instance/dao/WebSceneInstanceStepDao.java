package io.tiklab.teston.test.web.scene.instance.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.teston.test.web.scene.instance.entity.WebSceneInstanceStepEntity;
import io.tiklab.teston.test.web.scene.instance.model.WebSceneInstanceStepQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * web场景下步骤实例 数据访问
 */
@Repository
public class WebSceneInstanceStepDao{

    private static Logger logger = LoggerFactory.getLogger(WebSceneInstanceStepDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建web场景下步骤实例
     * @param webSceneInstanceStepEntity
     * @return
     */
    public String createWebSceneInstanceStep(WebSceneInstanceStepEntity webSceneInstanceStepEntity) {
        return jpaTemplate.save(webSceneInstanceStepEntity,String.class);
    }

    /**
     * 更新web场景下步骤实例
     * @param webSceneInstanceStepEntity
     */
    public void updateWebSceneInstanceStep(WebSceneInstanceStepEntity webSceneInstanceStepEntity){
        jpaTemplate.update(webSceneInstanceStepEntity);
    }

    /**
     * 删除web场景下步骤实例
     * @param id
     */
    public void deleteWebSceneInstanceStep(String id){
        jpaTemplate.delete(WebSceneInstanceStepEntity.class,id);
    }

    public void deleteWebSceneInstanceStep(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找web场景下步骤实例
     * @param id
     * @return
     */
    public WebSceneInstanceStepEntity findWebSceneInstanceStep(String id){
        return jpaTemplate.findOne(WebSceneInstanceStepEntity.class,id);
    }

    /**
    * 查找所有web场景下步骤实例
    * @return
    */
    public List<WebSceneInstanceStepEntity> findAllWebSceneInstanceStep() {
        return jpaTemplate.findAll(WebSceneInstanceStepEntity.class);
    }

    public List<WebSceneInstanceStepEntity> findWebSceneInstanceStepList(List<String> idList) {
        return jpaTemplate.findList(WebSceneInstanceStepEntity.class,idList);
    }

    /**
     * 根据查询参数查询web场景下步骤实例列表
     * @param webSceneInstanceStepQuery
     * @return
     */
    public List<WebSceneInstanceStepEntity> findWebSceneInstanceStepList(WebSceneInstanceStepQuery webSceneInstanceStepQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(WebSceneInstanceStepEntity.class)
                .eq("webSceneInstanceId", webSceneInstanceStepQuery.getWebSceneInstanceId())
                .orders(webSceneInstanceStepQuery.getOrderParams())
                .get();

        return jpaTemplate.findList(queryCondition,WebSceneInstanceStepEntity.class);
    }

    /**
     * 根据查询参数按分页查询web场景下步骤实例
     * @param webSceneInstanceStepQuery
     * @return
     */
    public Pagination<WebSceneInstanceStepEntity> findWebSceneInstanceStepPage(WebSceneInstanceStepQuery webSceneInstanceStepQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(WebSceneInstanceStepEntity.class)
                .eq("webSceneInstanceId", webSceneInstanceStepQuery.getWebSceneInstanceId())
                .orders(webSceneInstanceStepQuery.getOrderParams())
                .pagination(webSceneInstanceStepQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition,WebSceneInstanceStepEntity.class);
    }
}