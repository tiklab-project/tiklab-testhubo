package net.tiklab.teston.test.app.scene.cases.dao;

import net.tiklab.core.page.Pagination;
import net.tiklab.dal.jpa.criterial.condition.QueryCondition;
import net.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import net.tiklab.teston.test.app.scene.cases.entity.AppSceneStepEntity;
import net.tiklab.teston.test.app.scene.cases.model.AppSceneStepQuery;
import net.tiklab.dal.jpa.JpaTemplate;
import net.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * AppSceneStepDao
 */
@Repository
public class AppSceneStepDao{

    private static Logger logger = LoggerFactory.getLogger(AppSceneStepDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param appSceneStepEntity
     * @return
     */
    public String createAppSceneStep(AppSceneStepEntity appSceneStepEntity) {
        return jpaTemplate.save(appSceneStepEntity,String.class);
    }

    /**
     * 更新
     * @param appSceneStepEntity
     */
    public void updateAppSceneStep(AppSceneStepEntity appSceneStepEntity){
        jpaTemplate.update(appSceneStepEntity);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteAppSceneStep(String id){
        jpaTemplate.delete(AppSceneStepEntity.class,id);
    }

    public void deleteAppSceneStep(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public AppSceneStepEntity findAppSceneStep(String id){
        return jpaTemplate.findOne(AppSceneStepEntity.class,id);
    }

    /**
    * findAllAppSceneStep
    * @return
    */
    public List<AppSceneStepEntity> findAllAppSceneStep() {
        return jpaTemplate.findAll(AppSceneStepEntity.class);
    }

    public List<AppSceneStepEntity> findAppSceneStepList(List<String> idList) {
        return jpaTemplate.findList(AppSceneStepEntity.class,idList);
    }

    public List<AppSceneStepEntity> findAppSceneStepList(AppSceneStepQuery appSceneStepQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(AppSceneStepEntity.class)
                .eq("appSceneId", appSceneStepQuery.getAppSceneId())
                .orders(appSceneStepQuery.getOrderParams())
                .get();

        return jpaTemplate.findList(queryCondition,AppSceneStepEntity.class);
    }

    public Pagination<AppSceneStepEntity> findAppSceneStepPage(AppSceneStepQuery appSceneStepQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(AppSceneStepEntity.class)
                .eq("appSceneId", appSceneStepQuery.getAppSceneId())
                .pagination(appSceneStepQuery.getPageParam())
                .orders(appSceneStepQuery.getOrderParams())
                .get();

        return jpaTemplate.findPage(queryCondition,AppSceneStepEntity.class);
    }
}