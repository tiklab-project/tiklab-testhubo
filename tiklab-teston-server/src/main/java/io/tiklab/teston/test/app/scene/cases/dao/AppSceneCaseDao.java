package io.tiklab.teston.test.app.scene.cases.dao;

import io.tiklab.teston.test.app.scene.cases.entity.AppSceneCaseEntity;
import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.teston.test.app.scene.cases.model.AppSceneCaseQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * app场景用例 数据访问
 */
@Repository
public class AppSceneCaseDao{

    private static Logger logger = LoggerFactory.getLogger(AppSceneCaseDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建app场景用例
     * @param appSceneCaseEntity
     * @return
     */
    public String createAppSceneCase(AppSceneCaseEntity appSceneCaseEntity) {
        return jpaTemplate.save(appSceneCaseEntity,String.class);
    }

    /**
     * 更新app场景用例
     * @param appSceneCaseEntity
     */
    public void updateAppSceneCase(AppSceneCaseEntity appSceneCaseEntity){
        jpaTemplate.update(appSceneCaseEntity);
    }

    /**
     * 删除app场景用例
     * @param id
     */
    public void deleteAppSceneCase(String id){
        jpaTemplate.delete(AppSceneCaseEntity.class,id);
    }

    public void deleteAppSceneCase(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找app场景用例
     * @param id
     * @return
     */
    public AppSceneCaseEntity findAppSceneCase(String id){
        return jpaTemplate.findOne(AppSceneCaseEntity.class,id);
    }

    /**
    * 查找所有app场景用例
    * @return
    */
    public List<AppSceneCaseEntity> findAllAppSceneCase() {
        return jpaTemplate.findAll(AppSceneCaseEntity.class);
    }

    public List<AppSceneCaseEntity> findAppSceneCaseList(List<String> idList) {
        return jpaTemplate.findList(AppSceneCaseEntity.class,idList);
    }

    /**
     * 根据查询参数查询app场景用例列表
     * @param appSceneCaseQuery
     * @return
     */
    public List<AppSceneCaseEntity> findAppSceneCaseList(AppSceneCaseQuery appSceneCaseQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(AppSceneCaseEntity.class)
                .eq("testCaseId", appSceneCaseQuery.getTestCaseId())
                .orders(appSceneCaseQuery.getOrderParams())
                .get();

        return jpaTemplate.findList(queryCondition,AppSceneCaseEntity.class);
    }

    /**
     * 根据查询参数按分页查询app场景用例
     * @param appSceneCaseQuery
     * @return
     */
    public Pagination<AppSceneCaseEntity> findAppSceneCasePage(AppSceneCaseQuery appSceneCaseQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(AppSceneCaseEntity.class)
                .eq("testCaseId", appSceneCaseQuery.getTestCaseId())
                .pagination(appSceneCaseQuery.getPageParam())
                .orders(appSceneCaseQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition,AppSceneCaseEntity.class);
    }
}