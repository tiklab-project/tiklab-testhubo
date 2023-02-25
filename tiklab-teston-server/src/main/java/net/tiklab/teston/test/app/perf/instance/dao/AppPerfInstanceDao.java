package net.tiklab.teston.test.app.perf.instance.dao;

import net.tiklab.core.page.Pagination;
import net.tiklab.dal.jpa.JpaTemplate;
import net.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import net.tiklab.dal.jpa.criterial.condition.QueryCondition;
import net.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import net.tiklab.teston.test.app.perf.instance.entity.AppPerfInstanceEntity;
import net.tiklab.teston.test.app.perf.instance.mode.AppPerfInstanceQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * AppPerfInstanceDao
 */
@Repository
public class AppPerfInstanceDao {

    private static Logger logger = LoggerFactory.getLogger(AppPerfInstanceDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param appPerfInstanceEntity
     * @return
     */
    public String createAppPerfInstance(AppPerfInstanceEntity appPerfInstanceEntity) {
        return jpaTemplate.save(appPerfInstanceEntity,String.class);
    }

    /**
     * 更新
     * @param appPerfInstanceEntity
     */
    public void updateAppPerfInstance(AppPerfInstanceEntity appPerfInstanceEntity){
        jpaTemplate.update(appPerfInstanceEntity);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteAppPerfInstance(String id){
        jpaTemplate.delete(AppPerfInstanceEntity.class,id);
    }

    public void deleteAppPerfInstance(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public AppPerfInstanceEntity findAppPerfInstance(String id){
        return jpaTemplate.findOne(AppPerfInstanceEntity.class,id);
    }

    /**
    * findAllAppPerfInstance
    * @return
    */
    public List<AppPerfInstanceEntity> findAllAppPerfInstance() {
        return jpaTemplate.findAll(AppPerfInstanceEntity.class);
    }

    public List<AppPerfInstanceEntity> findAppPerfInstanceList(List<String> idList) {
        return jpaTemplate.findList(AppPerfInstanceEntity.class,idList);
    }

    public List<AppPerfInstanceEntity> findAppPerfInstanceList(AppPerfInstanceQuery appPerfInstanceQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(AppPerfInstanceEntity.class)
                .eq("appPerfId", appPerfInstanceQuery.getAppPerfId())
                .orders(appPerfInstanceQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, AppPerfInstanceEntity.class);
    }

    public Pagination<AppPerfInstanceEntity> findAppPerfInstancePage(AppPerfInstanceQuery appPerfInstanceQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(AppPerfInstanceEntity.class)
                .eq("appPerfId", appPerfInstanceQuery.getAppPerfId())
                .orders(appPerfInstanceQuery.getOrderParams())
                .pagination(appPerfInstanceQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, AppPerfInstanceEntity.class);
    }
}