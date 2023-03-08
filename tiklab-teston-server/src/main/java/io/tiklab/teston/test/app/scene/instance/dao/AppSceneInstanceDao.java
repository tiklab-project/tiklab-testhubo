package io.tiklab.teston.test.app.scene.instance.dao;

import io.tiklab.teston.test.app.scene.instance.entity.AppSceneInstanceEntity;
import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.teston.test.app.scene.instance.model.AppSceneInstanceQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * app场景测试历史实例 数据访问
 */
@Repository
public class AppSceneInstanceDao {

    private static Logger logger = LoggerFactory.getLogger(AppSceneInstanceDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建app场景测试历史实例
     * @param appSceneInstanceEntity
     * @return
     */
    public String createAppSceneInstance(AppSceneInstanceEntity appSceneInstanceEntity) {
        return jpaTemplate.save(appSceneInstanceEntity,String.class);
    }

    /**
     * 更新app场景测试历史实例
     * @param appSceneInstanceEntity
     */
    public void updateAppSceneInstance(AppSceneInstanceEntity appSceneInstanceEntity){
        jpaTemplate.update(appSceneInstanceEntity);
    }

    /**
     * 删除app场景测试历史实例
     * @param id
     */
    public void deleteAppSceneInstance(String id){
        jpaTemplate.delete(AppSceneInstanceEntity.class,id);
    }

    public void deleteAppSceneInstance(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找app场景测试历史实例
     * @param id
     * @return
     */
    public AppSceneInstanceEntity findAppSceneInstance(String id){
        return jpaTemplate.findOne(AppSceneInstanceEntity.class,id);
    }

    /**
    * 查找所有app场景测试历史实例
    * @return
    */
    public List<AppSceneInstanceEntity> findAllAppSceneInstance() {
        return jpaTemplate.findAll(AppSceneInstanceEntity.class);
    }

    public List<AppSceneInstanceEntity> findAppSceneInstanceList(List<String> idList) {
        return jpaTemplate.findList(AppSceneInstanceEntity.class,idList);
    }

    /**
     * 根据查询参数查询app场景测试历史实例列表
     * @param appSceneInstanceQuery
     * @return
     */
    public List<AppSceneInstanceEntity> findAppSceneInstanceList(AppSceneInstanceQuery appSceneInstanceQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(AppSceneInstanceEntity.class)
                .eq("appSceneId", appSceneInstanceQuery.getAppSceneId())
                .orders(appSceneInstanceQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, AppSceneInstanceEntity.class);
    }

    /**
     * 根据查询参数按分页查询app场景测试历史实例
     * @param appSceneInstanceQuery
     * @return
     */
    public Pagination<AppSceneInstanceEntity> findAppSceneInstancePage(AppSceneInstanceQuery appSceneInstanceQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(AppSceneInstanceEntity.class)
                .eq("appSceneId", appSceneInstanceQuery.getAppSceneId())
                .orders(appSceneInstanceQuery.getOrderParams())
                .pagination(appSceneInstanceQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, AppSceneInstanceEntity.class);
    }
}