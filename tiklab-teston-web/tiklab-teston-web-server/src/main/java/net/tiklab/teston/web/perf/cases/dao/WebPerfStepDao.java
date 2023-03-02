package net.tiklab.teston.web.perf.cases.dao;

import net.tiklab.core.page.Pagination;
import net.tiklab.dal.jpa.JpaTemplate;
import net.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import net.tiklab.dal.jpa.criterial.condition.QueryCondition;
import net.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import net.tiklab.teston.web.perf.cases.entity.WebPerfStepEntity;
import net.tiklab.teston.web.perf.cases.model.WebPerfStepQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * web性能用例下绑定的场景 数据访问
 */
@Repository
public class WebPerfStepDao{

    private static Logger logger = LoggerFactory.getLogger(WebPerfStepDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建web性能用例下绑定的场景
     * @param webPerfStepEntity
     * @return
     */
    public String createWebPerfStep(WebPerfStepEntity webPerfStepEntity) {
        return jpaTemplate.save(webPerfStepEntity,String.class);
    }

    /**
     * 更新web性能用例下绑定的场景
     * @param webPerfStepEntity
     */
    public void updateWebPerfStep(WebPerfStepEntity webPerfStepEntity){
        jpaTemplate.update(webPerfStepEntity);
    }

    /**
     * 删除web性能用例下绑定的场景
     * @param id
     */
    public void deleteWebPerfStep(String id){
        jpaTemplate.delete(WebPerfStepEntity.class,id);
    }

    public void deleteWebPerfStep(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找web性能用例下绑定的场景
     * @param id
     * @return
     */
    public WebPerfStepEntity findWebPerfStep(String id){
        return jpaTemplate.findOne(WebPerfStepEntity.class,id);
    }

    /**
    * 查找所有web性能用例下绑定的场景
    * @return
    */
    public List<WebPerfStepEntity> findAllWebPerfStep() {
        return jpaTemplate.findAll(WebPerfStepEntity.class);
    }

    public List<WebPerfStepEntity> findWebPerfStepList(List<String> idList) {
        return jpaTemplate.findList(WebPerfStepEntity.class,idList);
    }

    /**
     * 根据查询参数查询web性能用例下绑定的场景列表
     * @param webPerfStepQuery
     * @return
     */
    public List<WebPerfStepEntity> findWebPerfStepList(WebPerfStepQuery webPerfStepQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(WebPerfStepEntity.class)
                .eq("webPerfId",webPerfStepQuery.getWebPerfId())
                .orders(webPerfStepQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition,WebPerfStepEntity.class);
    }

    /**
     * 根据查询参数按分页查询web性能用例下绑定的场景
     * @param webPerfStepQuery
     * @return
     */
    public Pagination<WebPerfStepEntity> findWebPerfStepPage(WebPerfStepQuery webPerfStepQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(WebPerfStepEntity.class)
                .eq("webPerfId",webPerfStepQuery.getWebPerfId())
                .orders(webPerfStepQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition,WebPerfStepEntity.class);
    }
}