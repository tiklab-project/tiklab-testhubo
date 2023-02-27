package net.tiklab.teston.app.scene.instance.dao;

import net.tiklab.core.page.Pagination;
import net.tiklab.dal.jpa.JpaTemplate;
import net.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import net.tiklab.dal.jpa.criterial.condition.QueryCondition;
import net.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import net.tiklab.teston.app.scene.instance.entity.AppSceneInstanceStepEntity;
import net.tiklab.teston.app.scene.instance.model.AppSceneInstanceStepQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * AppSceneInstanceStepDao
 */
@Repository
public class AppSceneInstanceStepDao{

    private static Logger logger = LoggerFactory.getLogger(AppSceneInstanceStepDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param appSceneInstanceStepEntity
     * @return
     */
    public String createAppSceneInstanceStep(AppSceneInstanceStepEntity appSceneInstanceStepEntity) {
        return jpaTemplate.save(appSceneInstanceStepEntity,String.class);
    }

    /**
     * 更新
     * @param appSceneInstanceStepEntity
     */
    public void updateAppSceneInstanceStep(AppSceneInstanceStepEntity appSceneInstanceStepEntity){
        jpaTemplate.update(appSceneInstanceStepEntity);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteAppSceneInstanceStep(String id){
        jpaTemplate.delete(AppSceneInstanceStepEntity.class,id);
    }

    public void deleteAppSceneInstanceStep(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public AppSceneInstanceStepEntity findAppSceneInstanceStep(String id){
        return jpaTemplate.findOne(AppSceneInstanceStepEntity.class,id);
    }

    /**
    * findAllAppSceneInstanceStep
    * @return
    */
    public List<AppSceneInstanceStepEntity> findAllAppSceneInstanceStep() {
        return jpaTemplate.findAll(AppSceneInstanceStepEntity.class);
    }

    public List<AppSceneInstanceStepEntity> findAppSceneInstanceStepList(List<String> idList) {
        return jpaTemplate.findList(AppSceneInstanceStepEntity.class,idList);
    }

    public List<AppSceneInstanceStepEntity> findAppSceneInstanceStepList(AppSceneInstanceStepQuery appSceneInstanceStepQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(AppSceneInstanceStepEntity.class)
                .eq("appSceneInstanceId", appSceneInstanceStepQuery.getAppSceneInstanceId())
                .orders(appSceneInstanceStepQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition,AppSceneInstanceStepEntity.class);
    }

    public Pagination<AppSceneInstanceStepEntity> findAppSceneInstanceStepPage(AppSceneInstanceStepQuery appSceneInstanceStepQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(AppSceneInstanceStepEntity.class)
                .eq("appSceneInstanceId", appSceneInstanceStepQuery.getAppSceneInstanceId())
                .orders(appSceneInstanceStepQuery.getOrderParams())
                .pagination(appSceneInstanceStepQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition,AppSceneInstanceStepEntity.class);
    }
}