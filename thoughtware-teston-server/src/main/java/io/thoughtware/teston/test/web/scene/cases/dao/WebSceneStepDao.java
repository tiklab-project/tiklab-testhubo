package io.thoughtware.teston.test.web.scene.cases.dao;

import io.thoughtware.teston.test.web.scene.cases.entity.WebSceneStepEntity;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.dal.jpa.JpaTemplate;
import io.thoughtware.dal.jpa.criterial.condition.DeleteCondition;
import io.thoughtware.dal.jpa.criterial.condition.QueryCondition;
import io.thoughtware.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.thoughtware.teston.test.web.scene.cases.model.WebSceneStepQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * web场景下步骤 数据访问
 */
@Repository
public class WebSceneStepDao{

    private static Logger logger = LoggerFactory.getLogger(WebSceneStepDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建web场景下步骤
     * @param webSceneStepEntity
     * @return
     */
    public String createWebSceneStep(WebSceneStepEntity webSceneStepEntity) {
        return jpaTemplate.save(webSceneStepEntity,String.class);
    }

    /**
     * 更新web场景下步骤
     * @param webSceneStepEntity
     */
    public void updateWebSceneStep(WebSceneStepEntity webSceneStepEntity){
        jpaTemplate.update(webSceneStepEntity);
    }

    /**
     * 删除web场景下步骤
     * @param id
     */
    public void deleteWebSceneStep(String id){
        jpaTemplate.delete(WebSceneStepEntity.class,id);
    }

    public void deleteWebSceneStep(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找web场景下步骤
     * @param id
     * @return
     */
    public WebSceneStepEntity findWebSceneStep(String id){
        return jpaTemplate.findOne(WebSceneStepEntity.class,id);
    }


    /**
    * 查找所有web场景下步骤
    * @return
    */
    public List<WebSceneStepEntity> findAllWebSceneStep() {
        return jpaTemplate.findAll(WebSceneStepEntity.class);
    }


    public List<WebSceneStepEntity> findWebSceneStepList(List<String> idList) {
        return jpaTemplate.findList(WebSceneStepEntity.class,idList);
    }

    /**
     * 根据查询参数查询web场景下步骤列表
     * @param webSceneStepQuery
     * @return
     */
    public List<WebSceneStepEntity> findWebSceneStepList(WebSceneStepQuery webSceneStepQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(WebSceneStepEntity.class)
                .eq("webSceneId",webSceneStepQuery.getWebSceneId())
//                .orders(webSceneStepQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition,WebSceneStepEntity.class);
    }

    /**
     * 根据查询参数按分页查询web场景下步骤
     * @param webSceneStepQuery
     * @return
     */
    public Pagination<WebSceneStepEntity> findWebSceneStepPage(WebSceneStepQuery webSceneStepQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(WebSceneStepEntity.class)
                .eq("webSceneId",webSceneStepQuery.getWebSceneId())
                .orders(webSceneStepQuery.getOrderParams())
                .pagination(webSceneStepQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition,WebSceneStepEntity.class);
    }
}