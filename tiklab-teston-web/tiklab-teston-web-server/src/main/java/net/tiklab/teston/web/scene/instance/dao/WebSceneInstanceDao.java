package net.tiklab.teston.web.scene.instance.dao;

import net.tiklab.core.page.Pagination;
import net.tiklab.dal.jpa.JpaTemplate;
import net.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import net.tiklab.dal.jpa.criterial.condition.QueryCondition;
import net.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import net.tiklab.teston.web.scene.instance.entity.WebSceneInstanceEntity;
import net.tiklab.teston.web.scene.instance.model.WebSceneInstanceQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * web场景实例 服务
 */
@Repository
public class WebSceneInstanceDao {

    private static Logger logger = LoggerFactory.getLogger(WebSceneInstanceDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建web场景实例
     * @param webSceneInstanceEntity
     * @return
     */
    public String createWebSceneInstance(WebSceneInstanceEntity webSceneInstanceEntity) {
        return jpaTemplate.save(webSceneInstanceEntity,String.class);
    }

    /**
     * 更新web场景实例
     * @param webSceneInstanceEntity
     */
    public void updateWebSceneInstance(WebSceneInstanceEntity webSceneInstanceEntity){
        jpaTemplate.update(webSceneInstanceEntity);
    }

    /**
     * 删除web场景实例
     * @param id
     */
    public void deleteWebSceneInstance(String id){
        jpaTemplate.delete(WebSceneInstanceEntity.class,id);
    }

    public void deleteWebSceneInstance(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找web场景实例
     * @param id
     * @return
     */
    public WebSceneInstanceEntity findWebSceneInstance(String id){
        return jpaTemplate.findOne(WebSceneInstanceEntity.class,id);
    }

    /**
    * 查找所有web场景实例
    * @return
    */
    public List<WebSceneInstanceEntity> findAllWebSceneInstance() {
        return jpaTemplate.findAll(WebSceneInstanceEntity.class);
    }

    public List<WebSceneInstanceEntity> findWebSceneInstanceList(List<String> idList) {
        return jpaTemplate.findList(WebSceneInstanceEntity.class,idList);
    }

    /**
     * 根据查询参数查询web场景实例列表
     * @param webSceneInstanceQuery
     * @return
     */
    public List<WebSceneInstanceEntity> findWebSceneInstanceList(WebSceneInstanceQuery webSceneInstanceQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(WebSceneInstanceEntity.class)
                .eq("webSceneId", webSceneInstanceQuery.getWebSceneId())
                .orders(webSceneInstanceQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, WebSceneInstanceEntity.class);
    }

    /**
     * 根据查询参数按分页查询web场景实例
     * @param webSceneInstanceQuery
     * @return
     */
    public Pagination<WebSceneInstanceEntity> findWebSceneInstancePage(WebSceneInstanceQuery webSceneInstanceQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(WebSceneInstanceEntity.class)
                .eq("webSceneId", webSceneInstanceQuery.getWebSceneId())
                .pagination(webSceneInstanceQuery.getPageParam())
                .orders(webSceneInstanceQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, WebSceneInstanceEntity.class);
    }
}