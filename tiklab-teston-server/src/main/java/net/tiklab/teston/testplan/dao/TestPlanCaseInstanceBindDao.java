package net.tiklab.teston.testplan.dao;

import net.tiklab.core.page.Pagination;
import net.tiklab.dal.jpa.JpaTemplate;
import net.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import net.tiklab.dal.jpa.criterial.condition.QueryCondition;
import net.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import net.tiklab.teston.testplan.entity.TestPlanCaseInstanceBindEntity;
import net.tiklab.teston.testplan.model.TestPlanCaseInstanceBindQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * TestPlanCaseInstanceBindDao
 */
@Repository
public class TestPlanCaseInstanceBindDao{

    private static Logger logger = LoggerFactory.getLogger(TestPlanCaseInstanceBindDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param testPlanCaseInstanceBindEntity
     * @return
     */
    public String createTestPlanCaseInstanceBind(TestPlanCaseInstanceBindEntity testPlanCaseInstanceBindEntity) {
        return jpaTemplate.save(testPlanCaseInstanceBindEntity,String.class);
    }

    /**
     * 更新
     * @param testPlanCaseInstanceBindEntity
     */
    public void updateTestPlanCaseInstanceBind(TestPlanCaseInstanceBindEntity testPlanCaseInstanceBindEntity){
        jpaTemplate.update(testPlanCaseInstanceBindEntity);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteTestPlanCaseInstanceBind(String id){
        jpaTemplate.delete(TestPlanCaseInstanceBindEntity.class,id);
    }

    public void deleteTestPlanCaseInstanceBind(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public TestPlanCaseInstanceBindEntity findTestPlanCaseInstanceBind(String id){
        return jpaTemplate.findOne(TestPlanCaseInstanceBindEntity.class,id);
    }

    /**
    * findAllTestPlanCaseInstanceBind
    * @return
    */
    public List<TestPlanCaseInstanceBindEntity> findAllTestPlanCaseInstanceBind() {
        return jpaTemplate.findAll(TestPlanCaseInstanceBindEntity.class);
    }

    public List<TestPlanCaseInstanceBindEntity> findTestPlanCaseInstanceBindList(List<String> idList) {
        return jpaTemplate.findList(TestPlanCaseInstanceBindEntity.class,idList);
    }

    public List<TestPlanCaseInstanceBindEntity> findTestPlanCaseInstanceBindList(TestPlanCaseInstanceBindQuery testPlanCaseInstanceBindQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(TestPlanCaseInstanceBindEntity.class)
                .eq("caseInstanceId", testPlanCaseInstanceBindQuery.getCaseInstanceId())
                .eq("testPlanInstanceId",testPlanCaseInstanceBindQuery.getTestPlanInstanceId())
                .orders(testPlanCaseInstanceBindQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition,TestPlanCaseInstanceBindEntity.class);
    }

    public Pagination<TestPlanCaseInstanceBindEntity> findTestPlanCaseInstanceBindPage(TestPlanCaseInstanceBindQuery testPlanCaseInstanceBindQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(TestPlanCaseInstanceBindEntity.class)
                .eq("caseInstanceId", testPlanCaseInstanceBindQuery.getCaseInstanceId())
                .eq("testPlanInstanceId",testPlanCaseInstanceBindQuery.getTestPlanInstanceId())
                .orders(testPlanCaseInstanceBindQuery.getOrderParams())
                .pagination(testPlanCaseInstanceBindQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition,TestPlanCaseInstanceBindEntity.class);
    }
}