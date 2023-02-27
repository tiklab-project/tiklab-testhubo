package net.tiklab.teston.manager.testplan.dao;

import net.tiklab.core.page.Pagination;
import net.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import net.tiklab.dal.jpa.criterial.condition.QueryCondition;
import net.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import net.tiklab.teston.manager.testplan.entity.TestPlanDetailEntity;
import net.tiklab.teston.manager.testplan.model.TestPlanDetailQuery;
import net.tiklab.dal.jpa.JpaTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * TestPlanDetailDao
 */
@Repository
public class TestPlanDetailDao{

    private static Logger logger = LoggerFactory.getLogger(TestPlanDetailDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param testPlanDetailEntity
     * @return
     */
    public String createTestPlanDetail(TestPlanDetailEntity testPlanDetailEntity) {
        return jpaTemplate.save(testPlanDetailEntity,String.class);
    }

    /**
     * 更新
     * @param testPlanDetailEntity
     */
    public void updateTestPlanDetail(TestPlanDetailEntity testPlanDetailEntity){
        jpaTemplate.update(testPlanDetailEntity);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteTestPlanDetail(String id){
        jpaTemplate.delete(TestPlanDetailEntity.class,id);
    }

    public void deleteTestPlanDetail(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public TestPlanDetailEntity findTestPlanDetail(String id){
        return jpaTemplate.findOne(TestPlanDetailEntity.class,id);
    }

    /**
    * findAllTestPlanDetail
    * @return
    */
    public List<TestPlanDetailEntity> findAllTestPlanDetail() {
        return jpaTemplate.findAll(TestPlanDetailEntity.class);
    }

    public List<TestPlanDetailEntity> findTestPlanDetailList(List<String> idList) {
        return jpaTemplate.findList(TestPlanDetailEntity.class,idList);
    }

    public List<TestPlanDetailEntity> findTestPlanDetailList(TestPlanDetailQuery testPlanDetailQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(TestPlanDetailEntity.class)
                .eq("testPlanId", testPlanDetailQuery.getTestPlanId())
                .orders(testPlanDetailQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, TestPlanDetailEntity.class);
    }

    public Pagination<TestPlanDetailEntity> findTestPlanDetailPage(TestPlanDetailQuery testPlanDetailQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(TestPlanDetailEntity.class)
                .eq("testPlanId", testPlanDetailQuery.getTestPlanId())
                .orders(testPlanDetailQuery.getOrderParams())
                .pagination(testPlanDetailQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, TestPlanDetailEntity.class);
    }
}