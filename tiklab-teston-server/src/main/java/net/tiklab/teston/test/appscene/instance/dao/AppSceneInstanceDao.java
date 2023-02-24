package net.tiklab.teston.test.appscene.instance.dao;

import net.tiklab.core.page.Pagination;
import net.tiklab.dal.jpa.JpaTemplate;
import net.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import net.tiklab.dal.jpa.criterial.condition.QueryCondition;
import net.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import net.tiklab.teston.test.appscene.instance.entity.AppSceneInstanceEntity;
import net.tiklab.teston.test.appscene.instance.model.AppSceneInstanceQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * AppSceneInstanceDao
 */
@Repository
public class AppSceneInstanceDao {

    private static Logger logger = LoggerFactory.getLogger(AppSceneInstanceDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param appSceneInstanceEntity
     * @return
     */
    public String createAppSceneInstance(AppSceneInstanceEntity appSceneInstanceEntity) {
        return jpaTemplate.save(appSceneInstanceEntity,String.class);
    }

    /**
     * 更新
     * @param appSceneInstanceEntity
     */
    public void updateAppSceneInstance(AppSceneInstanceEntity appSceneInstanceEntity){
        jpaTemplate.update(appSceneInstanceEntity);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteAppSceneInstance(String id){
        jpaTemplate.delete(AppSceneInstanceEntity.class,id);
    }

    public void deleteAppSceneInstance(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public AppSceneInstanceEntity findAppSceneInstance(String id){
        return jpaTemplate.findOne(AppSceneInstanceEntity.class,id);
    }

    /**
    * findAllAppSceneInstance
    * @return
    */
    public List<AppSceneInstanceEntity> findAllAppSceneInstance() {
        return jpaTemplate.findAll(AppSceneInstanceEntity.class);
    }

    public List<AppSceneInstanceEntity> findAppSceneInstanceList(List<String> idList) {
        return jpaTemplate.findList(AppSceneInstanceEntity.class,idList);
    }

    public List<AppSceneInstanceEntity> findAppSceneInstanceList(AppSceneInstanceQuery appSceneInstanceQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(AppSceneInstanceEntity.class)
                .eq("appSceneId", appSceneInstanceQuery.getAppSceneId())
                .orders(appSceneInstanceQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, AppSceneInstanceEntity.class);
    }

    public Pagination<AppSceneInstanceEntity> findAppSceneInstancePage(AppSceneInstanceQuery appSceneInstanceQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(AppSceneInstanceEntity.class)
                .eq("appSceneId", appSceneInstanceQuery.getAppSceneId())
                .orders(appSceneInstanceQuery.getOrderParams())
                .pagination(appSceneInstanceQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, AppSceneInstanceEntity.class);
    }
}