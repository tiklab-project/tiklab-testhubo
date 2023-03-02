package net.tiklab.teston.web.perf.instance.dao;

import net.tiklab.core.page.Pagination;
import net.tiklab.dal.jpa.JpaTemplate;
import net.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import net.tiklab.dal.jpa.criterial.condition.QueryCondition;
import net.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import net.tiklab.teston.web.perf.instance.entity.WebPerfInstanceEntity;
import net.tiklab.teston.web.perf.instance.model.WebPerfInstanceQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * web性能测试实例 数据访问
 */
@Repository
public class WebPerfInstanceDao {

    private static Logger logger = LoggerFactory.getLogger(WebPerfInstanceDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建web性能测试实例
     * @param appPerfInstanceEntity
     * @return
     */
    public String createWebPerfInstance(WebPerfInstanceEntity appPerfInstanceEntity) {
        return jpaTemplate.save(appPerfInstanceEntity,String.class);
    }

    /**
     * 更新web性能测试实例
     * @param webPerfInstanceEntity
     */
    public void updateWebPerfInstance(WebPerfInstanceEntity webPerfInstanceEntity){
        jpaTemplate.update(webPerfInstanceEntity);
    }

    /**
     * 删除web性能测试实例
     * @param id
     */
    public void deleteWebPerfInstance(String id){
        jpaTemplate.delete(WebPerfInstanceEntity.class,id);
    }

    public void deleteWebPerfInstance(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找web性能测试实例
     * @param id
     * @return
     */
    public WebPerfInstanceEntity findWebPerfInstance(String id){
        return jpaTemplate.findOne(WebPerfInstanceEntity.class,id);
    }

    /**
    * 查找所有web性能测试实例
    * @return
    */
    public List<WebPerfInstanceEntity> findAllWebPerfInstance() {
        return jpaTemplate.findAll(WebPerfInstanceEntity.class);
    }

    public List<WebPerfInstanceEntity> findWebPerfInstanceList(List<String> idList) {
        return jpaTemplate.findList(WebPerfInstanceEntity.class,idList);
    }

    /**
     * 根据查询参数查询web性能测试实例列表
     * @param webPerfInstanceQuery
     * @return
     */
    public List<WebPerfInstanceEntity> findWebPerfInstanceList(WebPerfInstanceQuery webPerfInstanceQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(WebPerfInstanceEntity.class)
                .eq("webPerfId", webPerfInstanceQuery.getWebPerfId())
                .orders(webPerfInstanceQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, WebPerfInstanceEntity.class);
    }

    /**
     * 根据查询参数按分页查询web性能测试实例
     * @param webPerfInstanceQuery
     * @return
     */
    public Pagination<WebPerfInstanceEntity> findWebPerfInstancePage(WebPerfInstanceQuery webPerfInstanceQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(WebPerfInstanceEntity.class)
                .eq("webPerfId", webPerfInstanceQuery.getWebPerfId())
                .orders(webPerfInstanceQuery.getOrderParams())
                .pagination(webPerfInstanceQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, WebPerfInstanceEntity.class);
    }
}