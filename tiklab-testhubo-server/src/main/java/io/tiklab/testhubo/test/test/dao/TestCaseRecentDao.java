package io.tiklab.testhubo.test.test.dao;

import io.tiklab.testhubo.test.test.entity.TestCaseRecentEntity;
import io.tiklab.testhubo.test.test.model.TestCaseRecentQuery;
import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.List;

/**
 * 最近访问仓库  数据访问
 */
@Repository
public class TestCaseRecentDao {

    private static Logger logger = LoggerFactory.getLogger(TestCaseRecentDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建最近访问仓库
     * @param testCaseRecentEntity
     * @return
     */
    public String createTestCaseRecent(TestCaseRecentEntity testCaseRecentEntity) {
        return jpaTemplate.save(testCaseRecentEntity,String.class);
    }

    /**
     * 更新最近访问仓库
     * @param testCaseRecentEntity
     */
    public void updateTestCaseRecent(TestCaseRecentEntity testCaseRecentEntity){
        jpaTemplate.update(testCaseRecentEntity);
    }

    /**
     * 删除最近访问仓库
     * @param id
     */
    public void deleteTestCaseRecent(String id){
        jpaTemplate.delete(TestCaseRecentEntity.class,id);
    }

    public void deleteTestCaseRecent(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找最近访问仓库
     * @param id
     * @return
     */
    public TestCaseRecentEntity findTestCaseRecent(String id){
        return jpaTemplate.findOne(TestCaseRecentEntity.class,id);
    }

    /**
    * 查找所有最近访问仓库
    * @return
    */
    public List<TestCaseRecentEntity> findAllTestCaseRecent() {
        return jpaTemplate.findAll(TestCaseRecentEntity.class);
    }

    public List<TestCaseRecentEntity> findTestCaseRecentList(List<String> idList) {
        return jpaTemplate.findList(TestCaseRecentEntity.class,idList);
    }

    /**
     * 查询最近访问仓库列表
     * @param testCaseRecentQuery
     * @return
     */
    public List<TestCaseRecentEntity> findTestCaseRecentList(TestCaseRecentQuery testCaseRecentQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(TestCaseRecentEntity.class)
                .eq("userId", testCaseRecentQuery.getUserId())
                .eq("testCaseId",testCaseRecentQuery.getTestCaseId())
                .orders(testCaseRecentQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition,TestCaseRecentEntity.class);
    }

    /**
     * 按分页查询最近访问仓库
     * @param testCaseRecentQuery
     * @return
     */
    public Pagination<TestCaseRecentEntity> findTestCaseRecentPage(TestCaseRecentQuery testCaseRecentQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(TestCaseRecentEntity.class)
                .eq("userId", testCaseRecentQuery.getUserId())
                .eq("testCaseId",testCaseRecentQuery.getTestCaseId())
                .orders(testCaseRecentQuery.getOrderParams())
                .pagination(testCaseRecentQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition,TestCaseRecentEntity.class);
    }
}