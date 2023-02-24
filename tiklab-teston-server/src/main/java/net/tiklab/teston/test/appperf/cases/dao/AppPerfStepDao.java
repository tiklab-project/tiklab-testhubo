package net.tiklab.teston.test.appperf.cases.dao;

import net.tiklab.core.page.Pagination;
import net.tiklab.dal.jpa.criterial.condition.QueryCondition;
import net.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import net.tiklab.teston.test.appperf.cases.entity.AppPerfStepEntity;
import net.tiklab.teston.test.appperf.cases.model.AppPerfStepQuery;
import net.tiklab.dal.jpa.JpaTemplate;
import net.tiklab.dal.jpa.criterial.condition.DeleteCondition;
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
     * 创建
     * @param appPerfStepEntity
     * @return
     */
    public String createAppPerfStep(AppPerfStepEntity appPerfStepEntity) {
        return jpaTemplate.save(appPerfStepEntity,String.class);
    }

    /**
     * 更新
     * @param appPerfStepEntity
     */
    public void updateAppPerfStep(AppPerfStepEntity appPerfStepEntity){
        jpaTemplate.update(appPerfStepEntity);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteAppPerfStep(String id){
        jpaTemplate.delete(AppPerfStepEntity.class,id);
    }

    public void deleteAppPerfStep(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public AppPerfStepEntity findAppPerfStep(String id){
        return jpaTemplate.findOne(AppPerfStepEntity.class,id);
    }

    /**
    * findAllAppPerfStep
    * @return
    */
    public List<AppPerfStepEntity> findAllAppPerfStep() {
        return jpaTemplate.findAll(AppPerfStepEntity.class);
    }

    public List<AppPerfStepEntity> findAppPerfStepList(List<String> idList) {
        return jpaTemplate.findList(AppPerfStepEntity.class,idList);
    }

    public List<AppPerfStepEntity> findAppPerfStepList(AppPerfStepQuery appPerfStepQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(AppPerfStepEntity.class)
                .eq("appPerfId", appPerfStepQuery.getAppPerfId())
                .orders(appPerfStepQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition,AppPerfStepEntity.class);
    }

    public Pagination<AppPerfStepEntity> findAppPerfStepPage(AppPerfStepQuery appPerfStepQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(AppPerfStepEntity.class)
                .eq("appPerfId", appPerfStepQuery.getAppPerfId())
                .orders(appPerfStepQuery.getOrderParams())
                .pagination(appPerfStepQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition,AppPerfStepEntity.class);
    }
}