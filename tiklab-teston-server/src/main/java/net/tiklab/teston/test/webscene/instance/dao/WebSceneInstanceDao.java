package net.tiklab.teston.test.webscene.instance.dao;

import net.tiklab.core.page.Pagination;
import net.tiklab.dal.jpa.JpaTemplate;
import net.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import net.tiklab.dal.jpa.criterial.condition.QueryCondition;
import net.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import net.tiklab.teston.test.webscene.instance.entity.WebSceneInstanceEntity;
import net.tiklab.teston.test.webscene.instance.model.WebSceneInstanceQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * WebSceneInstanceDao
 */
@Repository
public class WebSceneInstanceDao {

    private static Logger logger = LoggerFactory.getLogger(WebSceneInstanceDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param webSceneInstanceEntity
     * @return
     */
    public String createWebSceneInstance(WebSceneInstanceEntity webSceneInstanceEntity) {
        return jpaTemplate.save(webSceneInstanceEntity,String.class);
    }

    /**
     * 更新
     * @param webSceneInstanceEntity
     */
    public void updateWebSceneInstance(WebSceneInstanceEntity webSceneInstanceEntity){
        jpaTemplate.update(webSceneInstanceEntity);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteWebSceneInstance(String id){
        jpaTemplate.delete(WebSceneInstanceEntity.class,id);
    }

    public void deleteWebSceneInstance(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public WebSceneInstanceEntity findWebSceneInstance(String id){
        return jpaTemplate.findOne(WebSceneInstanceEntity.class,id);
    }

    /**
    * findAllWebSceneInstance
    * @return
    */
    public List<WebSceneInstanceEntity> findAllWebSceneInstance() {
        return jpaTemplate.findAll(WebSceneInstanceEntity.class);
    }

    public List<WebSceneInstanceEntity> findWebSceneInstanceList(List<String> idList) {
        return jpaTemplate.findList(WebSceneInstanceEntity.class,idList);
    }

    public List<WebSceneInstanceEntity> findWebSceneInstanceList(WebSceneInstanceQuery webSceneInstanceQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(WebSceneInstanceEntity.class)
                .eq("webSceneId", webSceneInstanceQuery.getWebSceneId())
                .orders(webSceneInstanceQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, WebSceneInstanceEntity.class);
    }

    public Pagination<WebSceneInstanceEntity> findWebSceneInstancePage(WebSceneInstanceQuery webSceneInstanceQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(WebSceneInstanceEntity.class)
                .eq("webSceneId", webSceneInstanceQuery.getWebSceneId())
                .pagination(webSceneInstanceQuery.getPageParam())
                .orders(webSceneInstanceQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, WebSceneInstanceEntity.class);
    }
}