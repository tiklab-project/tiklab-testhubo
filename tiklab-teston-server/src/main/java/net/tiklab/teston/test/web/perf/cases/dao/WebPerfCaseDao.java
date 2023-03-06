package net.tiklab.teston.test.web.perf.cases.dao;

import net.tiklab.core.page.Pagination;
import net.tiklab.dal.jpa.JpaTemplate;
import net.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import net.tiklab.dal.jpa.criterial.condition.QueryCondition;
import net.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import net.tiklab.teston.test.web.perf.cases.entity.WebPerfCaseEntity;
import net.tiklab.teston.test.web.perf.cases.model.WebPerfCaseQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * web性能用例 数据访问
 */
@Repository
public class WebPerfCaseDao {

    private static Logger logger = LoggerFactory.getLogger(WebPerfCaseDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建web性能用例
     * @param appPerfCaseEntity
     * @return
     */
    public String createWebPerfCase(WebPerfCaseEntity appPerfCaseEntity) {
        return jpaTemplate.save(appPerfCaseEntity,String.class);
    }

    /**
     * 更新web性能用例
     * @param webPerfCaseEntity
     */
    public void updateWebPerfCase(WebPerfCaseEntity webPerfCaseEntity){
        jpaTemplate.update(webPerfCaseEntity);
    }

    /**
     * 删除web性能用例
     * @param id
     */
    public void deleteWebPerfCase(String id){
        jpaTemplate.delete(WebPerfCaseEntity.class,id);
    }

    public void deleteWebPerfCase(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找web性能用例
     * @param id
     * @return
     */
    public WebPerfCaseEntity findWebPerfCase(String id){
        return jpaTemplate.findOne(WebPerfCaseEntity.class,id);
    }

    /**
    * 查找所有web性能用例
    * @return
    */
    public List<WebPerfCaseEntity> findAllWebPerfCase() {
        return jpaTemplate.findAll(WebPerfCaseEntity.class);
    }

    public List<WebPerfCaseEntity> findWebPerfCaseList(List<String> idList) {
        return jpaTemplate.findList(WebPerfCaseEntity.class,idList);
    }

    /**
     * 根据查询参数查询web性能用例列表
     * @param webPerfCaseQuery
     * @return
     */
    public List<WebPerfCaseEntity> findWebPerfCaseList(WebPerfCaseQuery webPerfCaseQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(WebPerfCaseEntity.class)
                .eq("testCaseId", webPerfCaseQuery.getTestCaseId())
                .orders(webPerfCaseQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, WebPerfCaseEntity.class);
    }

    /**
     * 根据查询参数按分页查询web性能用例
     * @param webPerfCaseQuery
     * @return
     */
    public Pagination<WebPerfCaseEntity> findWebPerfCasePage(WebPerfCaseQuery webPerfCaseQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(WebPerfCaseEntity.class)
                .eq("testCaseId", webPerfCaseQuery.getTestCaseId())
                .orders(webPerfCaseQuery.getOrderParams())
                .pagination(webPerfCaseQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, WebPerfCaseEntity.class);
    }
}