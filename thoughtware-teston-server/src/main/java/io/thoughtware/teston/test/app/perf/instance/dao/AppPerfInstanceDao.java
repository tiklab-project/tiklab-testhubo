package io.thoughtware.teston.test.app.perf.instance.dao;

import io.thoughtware.teston.test.app.perf.instance.entity.AppPerfInstanceEntity;
import io.thoughtware.teston.test.app.perf.instance.mode.AppPerfInstanceQuery;
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
 * app性能实例 数据访问
 */
@Repository
public class AppPerfInstanceDao {

    private static Logger logger = LoggerFactory.getLogger(AppPerfInstanceDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建app性能实例
     * @param appPerfInstanceEntity
     * @return
     */
    public String createAppPerfInstance(AppPerfInstanceEntity appPerfInstanceEntity) {
        return jpaTemplate.save(appPerfInstanceEntity,String.class);
    }

    /**
     * 更新app性能实例
     * @param appPerfInstanceEntity
     */
    public void updateAppPerfInstance(AppPerfInstanceEntity appPerfInstanceEntity){
        jpaTemplate.update(appPerfInstanceEntity);
    }

    /**
     * 删除app性能实例
     * @param id
     */
    public void deleteAppPerfInstance(String id){
        jpaTemplate.delete(AppPerfInstanceEntity.class,id);
    }

    public void deleteAppPerfInstance(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找app性能实例
     * @param id
     * @return
     */
    public AppPerfInstanceEntity findAppPerfInstance(String id){
        return jpaTemplate.findOne(AppPerfInstanceEntity.class,id);
    }

    /**
    * 查找所有app性能实例
    * @return
    */
    public List<AppPerfInstanceEntity> findAllAppPerfInstance() {
        return jpaTemplate.findAll(AppPerfInstanceEntity.class);
    }

    public List<AppPerfInstanceEntity> findAppPerfInstanceList(List<String> idList) {
        return jpaTemplate.findList(AppPerfInstanceEntity.class,idList);
    }

    /**
     * 根据查询参数查询app性能实例列表
     * @param appPerfInstanceQuery
     * @return
     */
    public List<AppPerfInstanceEntity> findAppPerfInstanceList(AppPerfInstanceQuery appPerfInstanceQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(AppPerfInstanceEntity.class)
                .eq("appPerfId", appPerfInstanceQuery.getAppPerfId())
                .orders(appPerfInstanceQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, AppPerfInstanceEntity.class);
    }

    /**
     * 根据查询参数按分页查询app性能实例
     * @param appPerfInstanceQuery
     * @return
     */
    public Pagination<AppPerfInstanceEntity> findAppPerfInstancePage(AppPerfInstanceQuery appPerfInstanceQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(AppPerfInstanceEntity.class)
                .eq("appPerfId", appPerfInstanceQuery.getAppPerfId())
                .orders(appPerfInstanceQuery.getOrderParams())
                .pagination(appPerfInstanceQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, AppPerfInstanceEntity.class);
    }
}