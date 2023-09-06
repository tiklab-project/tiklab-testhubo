package io.tiklab.teston.test.test.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.teston.test.test.entity.TestCasesEntity;
import io.tiklab.teston.test.test.model.TestCaseQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 测试用例 数据访问
 */
@Repository
public class TestCaseDao {

    private static Logger logger = LoggerFactory.getLogger(TestCaseDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建测试用例
     * @param testCasesEntity
     * @return
     */
    public String createTestCase(TestCasesEntity testCasesEntity) {
        return jpaTemplate.save(testCasesEntity,String.class);
    }

    /**
     * 更新测试用例
     * @param testCasesEntity
     */
    public void updateTestCase(TestCasesEntity testCasesEntity){
        jpaTemplate.update(testCasesEntity);
    }

    /**
     * 删除测试用例
     * @param id
     */
    public void deleteTestCase(String id){
        jpaTemplate.delete(TestCasesEntity.class,id);
    }

    public void deleteTestCase(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 通过目录id删除
     * @param id
     * @return
     */
    public TestCasesEntity findTestCase(String id){
        return jpaTemplate.findOne(TestCasesEntity.class,id);
    }

    /**
    * 查找所有测试用例
    * @return
    */
    public List<TestCasesEntity> findAllTestCase() {
        return jpaTemplate.findAll(TestCasesEntity.class);
    }

    public List<TestCasesEntity> findTestCaseList(List<String> idList) {
        return jpaTemplate.findList(TestCasesEntity.class,idList);
    }

    /**
     * 根据查询参数查询测试用例列表
     * @param testCaseQuery
     * @return
     */
    public List<TestCasesEntity> findTestCaseList(TestCaseQuery testCaseQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(TestCasesEntity.class)
                .eq("repositoryId",testCaseQuery.getRepositoryId())
                .eq("categoryId", testCaseQuery.getCategoryId())
                .eq("testType",testCaseQuery.getTestType())
                .eq("caseType",testCaseQuery.getCaseType())
                .eq("createUser",testCaseQuery.getCreateUser())
                .in("repositoryId",testCaseQuery.getInList())
                .notIn("id",testCaseQuery.getNotInList())
                .like("name", testCaseQuery.getName())
                .orders(testCaseQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, TestCasesEntity.class);
    }

    /**
     * 根据查询参数按分页查询测试用例
     * @param testCaseQuery
     * @return
     */
    public Pagination<TestCasesEntity> findTestCasePage(TestCaseQuery testCaseQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(TestCasesEntity.class)
                .eq("repositoryId",testCaseQuery.getRepositoryId())
                .eq("categoryId", testCaseQuery.getCategoryId())
                .eq("testType",testCaseQuery.getTestType())
                .eq("caseType",testCaseQuery.getCaseType())
                .eq("createUser",testCaseQuery.getCreateUser())
                .notIn("id",testCaseQuery.getNotInList())
                .in("repositoryId",testCaseQuery.getInList())
                .like("name", testCaseQuery.getName())
                .pagination(testCaseQuery.getPageParam())
                .orders(testCaseQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, TestCasesEntity.class);
    }

}