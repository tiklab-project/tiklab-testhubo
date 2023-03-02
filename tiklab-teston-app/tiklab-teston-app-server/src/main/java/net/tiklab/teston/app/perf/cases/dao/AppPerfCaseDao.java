package net.tiklab.teston.app.perf.cases.dao;

import net.tiklab.core.page.Pagination;
import net.tiklab.dal.jpa.JpaTemplate;
import net.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import net.tiklab.dal.jpa.criterial.condition.QueryCondition;
import net.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import net.tiklab.teston.app.perf.cases.entity.AppPerfCaseEntity;
import net.tiklab.teston.app.perf.cases.model.AppPerfCaseQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * app性能测试用例 数据访问
 */
@Repository
public class AppPerfCaseDao{

    private static Logger logger = LoggerFactory.getLogger(AppPerfCaseDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建app性能测试用例
     * @param appPerfCaseEntity
     * @return
     */
    public String createAppPerfCase(AppPerfCaseEntity appPerfCaseEntity) {
        return jpaTemplate.save(appPerfCaseEntity,String.class);
    }

    /**
     * 更新app性能测试用例
     * @param appPerfCaseEntity
     */
    public void updateAppPerfCase(AppPerfCaseEntity appPerfCaseEntity){
        jpaTemplate.update(appPerfCaseEntity);
    }

    /**
     * 删除app性能测试用例
     * @param id
     */
    public void deleteAppPerfCase(String id){
        jpaTemplate.delete(AppPerfCaseEntity.class,id);
    }

    public void deleteAppPerfCase(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找app性能测试用例
     * @param id
     * @return
     */
    public AppPerfCaseEntity findAppPerfCase(String id){
        return jpaTemplate.findOne(AppPerfCaseEntity.class,id);
    }

    /**
    * 查找所有app性能测试用例
    * @return
    */
    public List<AppPerfCaseEntity> findAllAppPerfCase() {
        return jpaTemplate.findAll(AppPerfCaseEntity.class);
    }

    public List<AppPerfCaseEntity> findAppPerfCaseList(List<String> idList) {
        return jpaTemplate.findList(AppPerfCaseEntity.class,idList);
    }

    /**
     * 根据查询参数查询查询app性能测试用例列表
     * @param appPerfCaseQuery
     * @return
     */
    public List<AppPerfCaseEntity> findAppPerfCaseList(AppPerfCaseQuery appPerfCaseQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(AppPerfCaseEntity.class)
                .eq("testCaseId", appPerfCaseQuery.getTestCaseId())
                .orders(appPerfCaseQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition,AppPerfCaseEntity.class);
    }

    /**
     * 根据查询参数查询按分页查询app性能测试用例
     * @param appPerfCaseQuery
     * @return
     */
    public Pagination<AppPerfCaseEntity> findAppPerfCasePage(AppPerfCaseQuery appPerfCaseQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(AppPerfCaseEntity.class)
                .eq("testCaseId", appPerfCaseQuery.getTestCaseId())
                .pagination(appPerfCaseQuery.getPageParam())
                .orders(appPerfCaseQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition,AppPerfCaseEntity.class);
    }
}