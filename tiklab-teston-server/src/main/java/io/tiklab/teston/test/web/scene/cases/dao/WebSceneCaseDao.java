package io.tiklab.teston.test.web.scene.cases.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.teston.test.web.scene.cases.entity.WebSceneCaseEntity;
import io.tiklab.teston.test.web.scene.cases.model.WebSceneCaseQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * web场景用例 数据访问
 */
@Repository
public class WebSceneCaseDao {

    private static Logger logger = LoggerFactory.getLogger(WebSceneCaseDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建web场景用例
     * @param webSceneCaseEntity
     * @return
     */
    public String createWebSceneCase(WebSceneCaseEntity webSceneCaseEntity) {
        return jpaTemplate.save(webSceneCaseEntity,String.class);
    }

    /**
     * 更新web场景用例
     * @param webSceneCaseEntity
     */
    public void updateWebSceneCase(WebSceneCaseEntity webSceneCaseEntity){
        jpaTemplate.update(webSceneCaseEntity);
    }

    /**
     * 删除web场景用例
     * @param id
     */
    public void deleteWebSceneCase(String id){
        jpaTemplate.delete(WebSceneCaseEntity.class,id);
    }

    public void deleteWebSceneCase(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找web场景用例
     * @param id
     * @return
     */
    public WebSceneCaseEntity findWebSceneCase(String id){
        return jpaTemplate.findOne(WebSceneCaseEntity.class,id);
    }

    /**
    * 查找所有web场景用例
    * @return
    */
    public List<WebSceneCaseEntity> findAllWebSceneCase() {
        return jpaTemplate.findAll(WebSceneCaseEntity.class);
    }

    public List<WebSceneCaseEntity> findWebSceneCaseList(List<String> idList) {
        return jpaTemplate.findList(WebSceneCaseEntity.class,idList);
    }

    /**
     * 根据查询参数查询web场景用例列表
     * @param webSceneCaseQuery
     * @return
     */
    public List<WebSceneCaseEntity> findWebSceneCaseList(WebSceneCaseQuery webSceneCaseQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(WebSceneCaseEntity.class)
                .eq("testCaseId",webSceneCaseQuery.getTestCaseId())
                .orders(webSceneCaseQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, WebSceneCaseEntity.class);
    }

    /**
     * 根据查询参数按分页查询web场景用例
     * @param webSceneCaseQuery
     * @return
     */
    public Pagination<WebSceneCaseEntity> findWebSceneCasePage(WebSceneCaseQuery webSceneCaseQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(WebSceneCaseEntity.class)
                .eq("testCaseId",webSceneCaseQuery.getTestCaseId())
                .pagination(webSceneCaseQuery.getPageParam())
                .orders(webSceneCaseQuery.getOrderParams())
                .get();

        return jpaTemplate.findPage(queryCondition, WebSceneCaseEntity.class);
    }


}