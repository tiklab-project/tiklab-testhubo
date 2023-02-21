package net.tiklab.teston.apptest.scenetest.dao;

import net.tiklab.core.page.Pagination;
import net.tiklab.dal.jpa.criterial.condition.QueryCondition;
import net.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import net.tiklab.teston.apptest.scenetest.entity.AppSceneCaseEntity;
import net.tiklab.teston.apptest.scenetest.model.AppSceneCaseQuery;
import net.tiklab.dal.jpa.JpaTemplate;
import net.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * AppSceneCaseDao
 */
@Repository
public class AppSceneCaseDao{

    private static Logger logger = LoggerFactory.getLogger(AppSceneCaseDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param appSceneCaseEntity
     * @return
     */
    public String createAppSceneCase(AppSceneCaseEntity appSceneCaseEntity) {
        return jpaTemplate.save(appSceneCaseEntity,String.class);
    }

    /**
     * 更新
     * @param appSceneCaseEntity
     */
    public void updateAppSceneCase(AppSceneCaseEntity appSceneCaseEntity){
        jpaTemplate.update(appSceneCaseEntity);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteAppSceneCase(String id){
        jpaTemplate.delete(AppSceneCaseEntity.class,id);
    }

    public void deleteAppSceneCase(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public AppSceneCaseEntity findAppSceneCase(String id){
        return jpaTemplate.findOne(AppSceneCaseEntity.class,id);
    }

    /**
    * findAllAppSceneCase
    * @return
    */
    public List<AppSceneCaseEntity> findAllAppSceneCase() {
        return jpaTemplate.findAll(AppSceneCaseEntity.class);
    }

    public List<AppSceneCaseEntity> findAppSceneCaseList(List<String> idList) {
        return jpaTemplate.findList(AppSceneCaseEntity.class,idList);
    }

    public List<AppSceneCaseEntity> findAppSceneCaseList(AppSceneCaseQuery appSceneCaseQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(AppSceneCaseEntity.class)
                .eq("testCaseId", appSceneCaseQuery.getTestCaseId())
                .orders(appSceneCaseQuery.getOrderParams())
                .get();

        return jpaTemplate.findList(queryCondition,AppSceneCaseEntity.class);
    }

    public Pagination<AppSceneCaseEntity> findAppSceneCasePage(AppSceneCaseQuery appSceneCaseQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(AppSceneCaseEntity.class)
                .eq("testCaseId", appSceneCaseQuery.getTestCaseId())
                .pagination(appSceneCaseQuery.getPageParam())
                .orders(appSceneCaseQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition,AppSceneCaseEntity.class);
    }
}