package io.thoughtware.teston.test.app.perf.cases.dao;

import io.thoughtware.teston.test.app.perf.cases.entity.AppPerfStepEntity;
import io.thoughtware.teston.test.app.perf.cases.model.AppPerfStepQuery;
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
 * AppPerfStepDao
 */
@Repository
public class AppPerfStepDao{

    private static Logger logger = LoggerFactory.getLogger(AppPerfStepDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建绑定的步骤
     * @param appPerfStepEntity
     * @return
     */
    public String createAppPerfStep(AppPerfStepEntity appPerfStepEntity) {
        return jpaTemplate.save(appPerfStepEntity,String.class);
    }

    /**
     * 更新绑定的步骤
     * @param appPerfStepEntity
     */
    public void updateAppPerfStep(AppPerfStepEntity appPerfStepEntity){
        jpaTemplate.update(appPerfStepEntity);
    }

    /**
     * 删除绑定的步骤
     * @param id
     */
    public void deleteAppPerfStep(String id){
        jpaTemplate.delete(AppPerfStepEntity.class,id);
    }

    public void deleteAppPerfStep(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找绑定的步骤
     * @param id
     * @return
     */
    public AppPerfStepEntity findAppPerfStep(String id){
        return jpaTemplate.findOne(AppPerfStepEntity.class,id);
    }

    /**
    * 查找所有绑定的步骤
    * @return
    */
    public List<AppPerfStepEntity> findAllAppPerfStep() {
        return jpaTemplate.findAll(AppPerfStepEntity.class);
    }

    public List<AppPerfStepEntity> findAppPerfStepList(List<String> idList) {
        return jpaTemplate.findList(AppPerfStepEntity.class,idList);
    }

    /**
     * 根据查询参数查询绑定的步骤 列表
     * @param appPerfStepQuery
     * @return
     */
    public List<AppPerfStepEntity> findAppPerfStepList(AppPerfStepQuery appPerfStepQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(AppPerfStepEntity.class)
                .eq("appPerfId", appPerfStepQuery.getAppPerfId())
                .orders(appPerfStepQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition,AppPerfStepEntity.class);
    }

    /**
     * 根据查询参数按分页查询绑定的步骤
     * @param appPerfStepQuery
     * @return
     */
    public Pagination<AppPerfStepEntity> findAppPerfStepPage(AppPerfStepQuery appPerfStepQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(AppPerfStepEntity.class)
                .eq("appPerfId", appPerfStepQuery.getAppPerfId())
                .orders(appPerfStepQuery.getOrderParams())
                .pagination(appPerfStepQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition,AppPerfStepEntity.class);
    }
}