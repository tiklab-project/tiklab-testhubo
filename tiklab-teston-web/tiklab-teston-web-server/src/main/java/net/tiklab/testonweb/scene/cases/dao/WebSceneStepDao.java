package net.tiklab.testonweb.scene.cases.dao;

import net.tiklab.core.page.Pagination;
import net.tiklab.dal.jpa.JpaTemplate;
import net.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import net.tiklab.dal.jpa.criterial.condition.QueryCondition;
import net.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import net.tiklab.testonweb.scene.cases.entity.WebSceneStepEntity;
import net.tiklab.testonweb.scene.cases.model.WebSceneStepQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * WebSceneStepDao
 */
@Repository
public class WebSceneStepDao{

    private static Logger logger = LoggerFactory.getLogger(WebSceneStepDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param webSceneStepEntity
     * @return
     */
    public String createWebSceneStep(WebSceneStepEntity webSceneStepEntity) {
        return jpaTemplate.save(webSceneStepEntity,String.class);
    }

    /**
     * 更新
     * @param webSceneStepEntity
     */
    public void updateWebSceneStep(WebSceneStepEntity webSceneStepEntity){
        jpaTemplate.update(webSceneStepEntity);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteWebSceneStep(String id){
        jpaTemplate.delete(WebSceneStepEntity.class,id);
    }

    public void deleteWebSceneStep(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public WebSceneStepEntity findWebSceneStep(String id){
        return jpaTemplate.findOne(WebSceneStepEntity.class,id);
    }

    /**
    * findAllWebSceneStep
    * @return
    */
    public List<WebSceneStepEntity> findAllWebSceneStep() {
        return jpaTemplate.findAll(WebSceneStepEntity.class);
    }

    public List<WebSceneStepEntity> findWebSceneStepList(List<String> idList) {
        return jpaTemplate.findList(WebSceneStepEntity.class,idList);
    }

    public List<WebSceneStepEntity> findWebSceneStepList(WebSceneStepQuery webSceneStepQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(WebSceneStepEntity.class)
                .eq("webSceneId",webSceneStepQuery.getWebSceneId())
                .orders(webSceneStepQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition,WebSceneStepEntity.class);
    }

    public Pagination<WebSceneStepEntity> findWebSceneStepPage(WebSceneStepQuery webSceneStepQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(WebSceneStepEntity.class)
                .eq("webSceneId",webSceneStepQuery.getWebSceneId())
                .orders(webSceneStepQuery.getOrderParams())
                .pagination(webSceneStepQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition,WebSceneStepEntity.class);
    }
}