package net.tiklab.teston.manager.testplan.cases.dao;

import net.tiklab.core.page.Pagination;
import net.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import net.tiklab.dal.jpa.criterial.condition.QueryCondition;
import net.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import net.tiklab.teston.manager.testplan.cases.entity.TestPlanEntity;
import net.tiklab.teston.manager.testplan.cases.model.TestPlanQuery;
import net.tiklab.dal.jpa.JpaTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 测试计划 数据访问
 */
@Repository
public class TestPlanDao{

    private static Logger logger = LoggerFactory.getLogger(TestPlanDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建测试计划
     * @param testPlanEntity
     * @return
     */
    public String createTestPlan(TestPlanEntity testPlanEntity) {
        return jpaTemplate.save(testPlanEntity,String.class);
    }

    /**
     * 更新测试计划
     * @param testPlanEntity
     */
    public void updateTestPlan(TestPlanEntity testPlanEntity){
        jpaTemplate.update(testPlanEntity);
    }

    /**
     * 删除测试计划
     * @param id
     */
    public void deleteTestPlan(String id){
        jpaTemplate.delete(TestPlanEntity.class,id);
    }

    public void deleteTestPlan(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找测试计划
     * @param id
     * @return
     */
    public TestPlanEntity findTestPlan(String id){
        return jpaTemplate.findOne(TestPlanEntity.class,id);
    }

    /**
    * 查找所有测试计划
    * @return
    */
    public List<TestPlanEntity> findAllTestPlan() {
        return jpaTemplate.findAll(TestPlanEntity.class);
    }

    public List<TestPlanEntity> findTestPlanList(List<String> idList) {
        return jpaTemplate.findList(TestPlanEntity.class,idList);
    }

    /**
     * 根据查询参数查询测试计划列表
     * @param testPlanQuery
     * @return
     */
    public List<TestPlanEntity> findTestPlanList(TestPlanQuery testPlanQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(TestPlanEntity.class)
                .eq("repositoryId", testPlanQuery.getRepositoryId())
                .eq("state",testPlanQuery.getState())
                .orders(testPlanQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, TestPlanEntity.class);
    }

    /**
     * 根据查询参数按分页查询测试计划
     * @param testPlanQuery
     * @return
     */
    public Pagination<TestPlanEntity> findTestPlanPage(TestPlanQuery testPlanQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(TestPlanEntity.class)
                .eq("repositoryId", testPlanQuery.getRepositoryId())
                .eq("state",testPlanQuery.getState())
                .orders(testPlanQuery.getOrderParams())
                .pagination(testPlanQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, TestPlanEntity.class);
    }
}