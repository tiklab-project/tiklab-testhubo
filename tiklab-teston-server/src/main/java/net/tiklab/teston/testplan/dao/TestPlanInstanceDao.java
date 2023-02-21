package net.tiklab.teston.testplan.dao;

import net.tiklab.core.page.Pagination;
import net.tiklab.dal.jpa.JpaTemplate;
import net.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import net.tiklab.dal.jpa.criterial.condition.QueryCondition;
import net.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import net.tiklab.teston.testplan.entity.TestPlanInstanceEntity;
import net.tiklab.teston.testplan.model.TestPlanInstanceQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * TestPlanInstanceDao
 */
@Repository
public class TestPlanInstanceDao{

    private static Logger logger = LoggerFactory.getLogger(TestPlanInstanceDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param testPlanInstanceEntity
     * @return
     */
    public String createTestPlanInstance(TestPlanInstanceEntity testPlanInstanceEntity) {
        return jpaTemplate.save(testPlanInstanceEntity,String.class);
    }

    /**
     * 更新
     * @param testPlanInstanceEntity
     */
    public void updateTestPlanInstance(TestPlanInstanceEntity testPlanInstanceEntity){
        jpaTemplate.update(testPlanInstanceEntity);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteTestPlanInstance(String id){
        jpaTemplate.delete(TestPlanInstanceEntity.class,id);
    }

    public void deleteTestPlanInstance(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public TestPlanInstanceEntity findTestPlanInstance(String id){
        return jpaTemplate.findOne(TestPlanInstanceEntity.class,id);
    }

    /**
    * findAllTestPlanInstance
    * @return
    */
    public List<TestPlanInstanceEntity> findAllTestPlanInstance() {
        return jpaTemplate.findAll(TestPlanInstanceEntity.class);
    }

    public List<TestPlanInstanceEntity> findTestPlanInstanceList(List<String> idList) {
        return jpaTemplate.findList(TestPlanInstanceEntity.class,idList);
    }

    public List<TestPlanInstanceEntity> findTestPlanInstanceList(TestPlanInstanceQuery testPlanInstanceQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(TestPlanInstanceEntity.class)
                .eq("testPlanId", testPlanInstanceQuery.getTestPlanId())
                .eq("repositoryId",testPlanInstanceQuery.getRepositoryId())
                .orders(testPlanInstanceQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition,TestPlanInstanceEntity.class);
    }

    public Pagination<TestPlanInstanceEntity> findTestPlanInstancePage(TestPlanInstanceQuery testPlanInstanceQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(TestPlanInstanceEntity.class)
                .eq("testPlanId", testPlanInstanceQuery.getTestPlanId())
                .eq("repositoryId",testPlanInstanceQuery.getRepositoryId())
                .orders(testPlanInstanceQuery.getOrderParams())
                .pagination(testPlanInstanceQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition,TestPlanInstanceEntity.class);
    }
}