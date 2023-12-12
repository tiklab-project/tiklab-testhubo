package io.thoughtware.teston.testplan.cases.dao;

import io.thoughtware.teston.testplan.cases.entity.TestPlanCaseEntity;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.dal.jpa.criterial.condition.DeleteCondition;
import io.thoughtware.dal.jpa.criterial.condition.QueryCondition;
import io.thoughtware.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.thoughtware.teston.testplan.cases.model.TestPlanCaseQuery;
import io.thoughtware.dal.jpa.JpaTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 测试计划绑定的用例 服务接口
 */
@Repository
public class TestPlanCaseDao {

    private static Logger logger = LoggerFactory.getLogger(TestPlanCaseDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建绑定的用例
     * @param testPlanCaseEntity
     * @return
     */
    public String createTestPlanCase(TestPlanCaseEntity testPlanCaseEntity) {
        return jpaTemplate.save(testPlanCaseEntity,String.class);
    }

    /**
     * 更新绑定的用例
     * @param testPlanCaseEntity
     */
    public void updateTestPlanCase(TestPlanCaseEntity testPlanCaseEntity){
        jpaTemplate.update(testPlanCaseEntity);
    }

    /**
     * 删除绑定的用例
     * @param id
     */
    public void deleteTestPlanCase(String id){
        jpaTemplate.delete(TestPlanCaseEntity.class,id);
    }

    public void deleteTestPlanCase(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找绑定的用例
     * @param id
     * @return
     */
    public TestPlanCaseEntity findTestPlanCase(String id){
        return jpaTemplate.findOne(TestPlanCaseEntity.class,id);
    }

    /**
    * 查找所有绑定的用例
    * @return
    */
    public List<TestPlanCaseEntity> findAllTestPlanCase() {
        return jpaTemplate.findAll(TestPlanCaseEntity.class);
    }

    public List<TestPlanCaseEntity> findTestPlanCaseList(List<String> idList) {
        return jpaTemplate.findList(TestPlanCaseEntity.class,idList);
    }

    /**
     * 查询绑定的用例 列表
     * @param testPlanCaseQuery
     * @return
     */
    public List<TestPlanCaseEntity> findTestPlanCaseList(TestPlanCaseQuery testPlanCaseQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(TestPlanCaseEntity.class)
                .eq("testPlanId", testPlanCaseQuery.getTestPlanId())
                .orders(testPlanCaseQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, TestPlanCaseEntity.class);
    }

    /**
     * 按分页查询绑定的用例
     * @param testPlanCaseQuery
     * @return
     */
    public Pagination<TestPlanCaseEntity> findTestPlanCasePage(TestPlanCaseQuery testPlanCaseQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(TestPlanCaseEntity.class)
                .eq("testPlanId", testPlanCaseQuery.getTestPlanId())
                .orders(testPlanCaseQuery.getOrderParams())
                .pagination(testPlanCaseQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, TestPlanCaseEntity.class);
    }


}