package net.tiklab.teston.manager.testcase.dao;

import net.tiklab.core.page.Pagination;
import net.tiklab.dal.jpa.JpaTemplate;
import net.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import net.tiklab.dal.jpa.criterial.condition.QueryCondition;
import net.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import net.tiklab.teston.manager.testcase.entity.TestCaseEntity;
import net.tiklab.teston.manager.testcase.model.TestCaseQuery;
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
     * @param testCaseEntity
     * @return
     */
    public String createTestCase(TestCaseEntity testCaseEntity) {
        return jpaTemplate.save(testCaseEntity,String.class);
    }

    /**
     * 更新测试用例
     * @param testCaseEntity
     */
    public void updateTestCase(TestCaseEntity testCaseEntity){
        jpaTemplate.update(testCaseEntity);
    }

    /**
     * 删除测试用例
     * @param id
     */
    public void deleteTestCase(String id){
        jpaTemplate.delete(TestCaseEntity.class,id);
    }

    public void deleteTestCase(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 通过目录id删除
     * @param id
     * @return
     */
    public TestCaseEntity findTestCase(String id){
        return jpaTemplate.findOne(TestCaseEntity.class,id);
    }

    /**
    * 查找所有测试用例
    * @return
    */
    public List<TestCaseEntity> findAllTestCase() {
        return jpaTemplate.findAll(TestCaseEntity.class);
    }

    public List<TestCaseEntity> findTestCaseList(List<String> idList) {
        return jpaTemplate.findList(TestCaseEntity.class,idList);
    }

    /**
     * 根据查询参数查询测试用例列表
     * @param testCaseQuery
     * @return
     */
    public List<TestCaseEntity> findTestCaseList(TestCaseQuery testCaseQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(TestCaseEntity.class)
                .eq("repositoryId",testCaseQuery.getRepositoryId())
                .eq("categoryId", testCaseQuery.getCategoryId())
                .eq("testType",testCaseQuery.getTestType())
                .eq("caseType",testCaseQuery.getCaseType())
                .like("name", testCaseQuery.getName())
                .orders(testCaseQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, TestCaseEntity.class);
    }

    /**
     * 根据查询参数按分页查询测试用例
     * @param testCaseQuery
     * @return
     */
    public Pagination<TestCaseEntity> findTestCasePage(TestCaseQuery testCaseQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(TestCaseEntity.class)
                .eq("repositoryId",testCaseQuery.getRepositoryId())
                .eq("categoryId", testCaseQuery.getCategoryId())
                .eq("testType",testCaseQuery.getTestType())
                .eq("caseType",testCaseQuery.getCaseType())
                .like("name", testCaseQuery.getName())
                .pagination(testCaseQuery.getPageParam())
                .orders(testCaseQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, TestCaseEntity.class);
    }

}