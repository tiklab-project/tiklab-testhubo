package net.tiklab.teston.test.web.perf.instance.dao;

import net.tiklab.core.page.Pagination;
import net.tiklab.dal.jpa.JpaTemplate;
import net.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import net.tiklab.dal.jpa.criterial.condition.QueryCondition;
import net.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import net.tiklab.teston.test.web.perf.instance.entity.WebPerfInstanceEntity;
import net.tiklab.teston.test.web.perf.instance.model.WebPerfInstanceQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * WebPerfInstanceDao
 */
@Repository
public class WebPerfInstanceDao {

    private static Logger logger = LoggerFactory.getLogger(WebPerfInstanceDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param appPerfInstanceEntity
     * @return
     */
    public String createWebPerfInstance(WebPerfInstanceEntity appPerfInstanceEntity) {
        return jpaTemplate.save(appPerfInstanceEntity,String.class);
    }

    /**
     * 更新
     * @param webPerfInstanceEntity
     */
    public void updateWebPerfInstance(WebPerfInstanceEntity webPerfInstanceEntity){
        jpaTemplate.update(webPerfInstanceEntity);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteWebPerfInstance(String id){
        jpaTemplate.delete(WebPerfInstanceEntity.class,id);
    }

    public void deleteWebPerfInstance(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public WebPerfInstanceEntity findWebPerfInstance(String id){
        return jpaTemplate.findOne(WebPerfInstanceEntity.class,id);
    }

    /**
    * findAllWebPerfInstance
    * @return
    */
    public List<WebPerfInstanceEntity> findAllWebPerfInstance() {
        return jpaTemplate.findAll(WebPerfInstanceEntity.class);
    }

    public List<WebPerfInstanceEntity> findWebPerfInstanceList(List<String> idList) {
        return jpaTemplate.findList(WebPerfInstanceEntity.class,idList);
    }

    public List<WebPerfInstanceEntity> findWebPerfInstanceList(WebPerfInstanceQuery webPerfInstanceQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(WebPerfInstanceEntity.class)
                .eq("webPerfId", webPerfInstanceQuery.getWebPerfId())
                .orders(webPerfInstanceQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, WebPerfInstanceEntity.class);
    }

    public Pagination<WebPerfInstanceEntity> findWebPerfInstancePage(WebPerfInstanceQuery webPerfInstanceQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(WebPerfInstanceEntity.class)
                .eq("webPerfId", webPerfInstanceQuery.getWebPerfId())
                .orders(webPerfInstanceQuery.getOrderParams())
                .pagination(webPerfInstanceQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, WebPerfInstanceEntity.class);
    }
}