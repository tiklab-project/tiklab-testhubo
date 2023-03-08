package io.tiklab.teston.test.app.scene.cases.dao;

import io.tiklab.teston.test.app.scene.cases.entity.AppSceneStepEntity;
import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.teston.test.app.scene.cases.model.AppSceneStepQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * app场景下步骤 数据访问
 */
@Repository
public class AppSceneStepDao{

    private static Logger logger = LoggerFactory.getLogger(AppSceneStepDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建场景步骤
     * @param appSceneStepEntity
     * @return
     */
    public String createAppSceneStep(AppSceneStepEntity appSceneStepEntity) {
        return jpaTemplate.save(appSceneStepEntity,String.class);
    }

    /**
     * 更新场景步骤
     * @param appSceneStepEntity
     */
    public void updateAppSceneStep(AppSceneStepEntity appSceneStepEntity){
        jpaTemplate.update(appSceneStepEntity);
    }

    /**
     * 删除场景步骤
     * @param id
     */
    public void deleteAppSceneStep(String id){
        jpaTemplate.delete(AppSceneStepEntity.class,id);
    }

    public void deleteAppSceneStep(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找场景步骤
     * @param id
     * @return
     */
    public AppSceneStepEntity findAppSceneStep(String id){
        return jpaTemplate.findOne(AppSceneStepEntity.class,id);
    }

    /**
    * 查找所有场景步骤
    * @return
    */
    public List<AppSceneStepEntity> findAllAppSceneStep() {
        return jpaTemplate.findAll(AppSceneStepEntity.class);
    }

    public List<AppSceneStepEntity> findAppSceneStepList(List<String> idList) {
        return jpaTemplate.findList(AppSceneStepEntity.class,idList);
    }

    /**
     * 根据查询参数查询场景步骤列表
     * @param appSceneStepQuery
     * @return
     */
    public List<AppSceneStepEntity> findAppSceneStepList(AppSceneStepQuery appSceneStepQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(AppSceneStepEntity.class)
                .eq("appSceneId", appSceneStepQuery.getAppSceneId())
                .orders(appSceneStepQuery.getOrderParams())
                .get();

        return jpaTemplate.findList(queryCondition,AppSceneStepEntity.class);
    }

    /**
     * 根据查询参数按分页查询场景步骤
     * @param appSceneStepQuery
     * @return
     */
    public Pagination<AppSceneStepEntity> findAppSceneStepPage(AppSceneStepQuery appSceneStepQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(AppSceneStepEntity.class)
                .eq("appSceneId", appSceneStepQuery.getAppSceneId())
                .pagination(appSceneStepQuery.getPageParam())
                .orders(appSceneStepQuery.getOrderParams())
                .get();

        return jpaTemplate.findPage(queryCondition,AppSceneStepEntity.class);
    }
}