package io.thoughtware.teston.test.app.scene.instance.dao;

import io.thoughtware.teston.test.app.scene.instance.entity.AppSceneInstanceStepEntity;
import io.thoughtware.teston.test.app.scene.instance.model.AppSceneInstanceStepQuery;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.dal.jpa.JpaTemplate;
import io.thoughtware.dal.jpa.criterial.condition.DeleteCondition;
import io.thoughtware.dal.jpa.criterial.condition.QueryCondition;
import io.thoughtware.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * app场景步骤实例 数据访问
 */
@Repository
public class AppSceneInstanceStepDao{

    private static Logger logger = LoggerFactory.getLogger(AppSceneInstanceStepDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建app场景步骤实例
     * @param appSceneInstanceStepEntity
     * @return
     */
    public String createAppSceneInstanceStep(AppSceneInstanceStepEntity appSceneInstanceStepEntity) {
        return jpaTemplate.save(appSceneInstanceStepEntity,String.class);
    }

    /**
     * 更新app场景步骤实例
     * @param appSceneInstanceStepEntity
     */
    public void updateAppSceneInstanceStep(AppSceneInstanceStepEntity appSceneInstanceStepEntity){
        jpaTemplate.update(appSceneInstanceStepEntity);
    }

    /**
     * 删除app场景步骤实例
     * @param id
     */
    public void deleteAppSceneInstanceStep(String id){
        jpaTemplate.delete(AppSceneInstanceStepEntity.class,id);
    }

    public void deleteAppSceneInstanceStep(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找app场景步骤实例
     * @param id
     * @return
     */
    public AppSceneInstanceStepEntity findAppSceneInstanceStep(String id){
        return jpaTemplate.findOne(AppSceneInstanceStepEntity.class,id);
    }

    /**
    * 根据查询参数查找所有app场景步骤实例
    * @return
    */
    public List<AppSceneInstanceStepEntity> findAllAppSceneInstanceStep() {
        return jpaTemplate.findAll(AppSceneInstanceStepEntity.class);
    }

    public List<AppSceneInstanceStepEntity> findAppSceneInstanceStepList(List<String> idList) {
        return jpaTemplate.findList(AppSceneInstanceStepEntity.class,idList);
    }

    /**
     * 根据查询参数查询app场景步骤实例列表
     * @param appSceneInstanceStepQuery
     * @return
     */
    public List<AppSceneInstanceStepEntity> findAppSceneInstanceStepList(AppSceneInstanceStepQuery appSceneInstanceStepQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(AppSceneInstanceStepEntity.class)
                .eq("appSceneInstanceId", appSceneInstanceStepQuery.getAppSceneInstanceId())
                .orders(appSceneInstanceStepQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition,AppSceneInstanceStepEntity.class);
    }

    /**
     * 根据查询参数按分页查询app场景步骤实例
     * @param appSceneInstanceStepQuery
     * @return
     */
    public Pagination<AppSceneInstanceStepEntity> findAppSceneInstanceStepPage(AppSceneInstanceStepQuery appSceneInstanceStepQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(AppSceneInstanceStepEntity.class)
                .eq("appSceneInstanceId", appSceneInstanceStepQuery.getAppSceneInstanceId())
                .orders(appSceneInstanceStepQuery.getOrderParams())
                .pagination(appSceneInstanceStepQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition,AppSceneInstanceStepEntity.class);
    }
}