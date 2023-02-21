package net.tiklab.teston.apptest.perftest.dao;

import net.tiklab.core.page.Pagination;
import net.tiklab.dal.jpa.criterial.condition.QueryCondition;
import net.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import net.tiklab.teston.apptest.perftest.entity.AppPerfCaseEntity;
import net.tiklab.teston.apptest.perftest.model.AppPerfCaseQuery;
import net.tiklab.dal.jpa.JpaTemplate;
import net.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * AppPerfCaseDao
 */
@Repository
public class AppPerfCaseDao{

    private static Logger logger = LoggerFactory.getLogger(AppPerfCaseDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param appPerfCaseEntity
     * @return
     */
    public String createAppPerfCase(AppPerfCaseEntity appPerfCaseEntity) {
        return jpaTemplate.save(appPerfCaseEntity,String.class);
    }

    /**
     * 更新
     * @param appPerfCaseEntity
     */
    public void updateAppPerfCase(AppPerfCaseEntity appPerfCaseEntity){
        jpaTemplate.update(appPerfCaseEntity);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteAppPerfCase(String id){
        jpaTemplate.delete(AppPerfCaseEntity.class,id);
    }

    public void deleteAppPerfCase(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public AppPerfCaseEntity findAppPerfCase(String id){
        return jpaTemplate.findOne(AppPerfCaseEntity.class,id);
    }

    /**
    * findAllAppPerfCase
    * @return
    */
    public List<AppPerfCaseEntity> findAllAppPerfCase() {
        return jpaTemplate.findAll(AppPerfCaseEntity.class);
    }

    public List<AppPerfCaseEntity> findAppPerfCaseList(List<String> idList) {
        return jpaTemplate.findList(AppPerfCaseEntity.class,idList);
    }

    public List<AppPerfCaseEntity> findAppPerfCaseList(AppPerfCaseQuery appPerfCaseQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(AppPerfCaseEntity.class)
                .eq("testCaseId", appPerfCaseQuery.getTestCaseId())
                .orders(appPerfCaseQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition,AppPerfCaseEntity.class);
    }

    public Pagination<AppPerfCaseEntity> findAppPerfCasePage(AppPerfCaseQuery appPerfCaseQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(AppPerfCaseEntity.class)
                .eq("testCaseId", appPerfCaseQuery.getTestCaseId())
                .pagination(appPerfCaseQuery.getPageParam())
                .orders(appPerfCaseQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition,AppPerfCaseEntity.class);
    }
}