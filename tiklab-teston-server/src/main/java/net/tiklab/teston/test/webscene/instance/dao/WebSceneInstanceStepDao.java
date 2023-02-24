package net.tiklab.teston.test.webscene.instance.dao;

import net.tiklab.core.page.Pagination;
import net.tiklab.dal.jpa.criterial.condition.QueryCondition;
import net.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import net.tiklab.teston.test.webscene.instance.entity.WebSceneInstanceStepEntity;
import net.tiklab.teston.test.webscene.instance.model.WebSceneInstanceStepQuery;
import net.tiklab.dal.jpa.JpaTemplate;
import net.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * WebSceneInstanceStepDao
 */
@Repository
public class WebSceneInstanceStepDao{

    private static Logger logger = LoggerFactory.getLogger(WebSceneInstanceStepDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param webSceneInstanceStepEntity
     * @return
     */
    public String createWebSceneInstanceStep(WebSceneInstanceStepEntity webSceneInstanceStepEntity) {
        return jpaTemplate.save(webSceneInstanceStepEntity,String.class);
    }

    /**
     * 更新
     * @param webSceneInstanceStepEntity
     */
    public void updateWebSceneInstanceStep(WebSceneInstanceStepEntity webSceneInstanceStepEntity){
        jpaTemplate.update(webSceneInstanceStepEntity);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteWebSceneInstanceStep(String id){
        jpaTemplate.delete(WebSceneInstanceStepEntity.class,id);
    }

    public void deleteWebSceneInstanceStep(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public WebSceneInstanceStepEntity findWebSceneInstanceStep(String id){
        return jpaTemplate.findOne(WebSceneInstanceStepEntity.class,id);
    }

    /**
    * findAllWebSceneInstanceStep
    * @return
    */
    public List<WebSceneInstanceStepEntity> findAllWebSceneInstanceStep() {
        return jpaTemplate.findAll(WebSceneInstanceStepEntity.class);
    }

    public List<WebSceneInstanceStepEntity> findWebSceneInstanceStepList(List<String> idList) {
        return jpaTemplate.findList(WebSceneInstanceStepEntity.class,idList);
    }

    public List<WebSceneInstanceStepEntity> findWebSceneInstanceStepList(WebSceneInstanceStepQuery webSceneInstanceStepQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(WebSceneInstanceStepEntity.class)
                .eq("webSceneInstanceId", webSceneInstanceStepQuery.getWebSceneInstanceId())
                .orders(webSceneInstanceStepQuery.getOrderParams())
                .get();

        return jpaTemplate.findList(queryCondition,WebSceneInstanceStepEntity.class);
    }

    public Pagination<WebSceneInstanceStepEntity> findWebSceneInstanceStepPage(WebSceneInstanceStepQuery webSceneInstanceStepQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(WebSceneInstanceStepEntity.class)
                .eq("webSceneInstanceId", webSceneInstanceStepQuery.getWebSceneInstanceId())
                .orders(webSceneInstanceStepQuery.getOrderParams())
                .pagination(webSceneInstanceStepQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition,WebSceneInstanceStepEntity.class);
    }
}