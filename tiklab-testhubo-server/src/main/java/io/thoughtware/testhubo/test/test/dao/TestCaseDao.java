package io.thoughtware.testhubo.test.test.dao;

import io.thoughtware.testhubo.test.test.entity.TestCasesEntity;
import io.thoughtware.testhubo.test.test.model.TestCaseQuery;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.dal.jpa.JpaTemplate;
import io.thoughtware.dal.jpa.criterial.condition.DeleteCondition;
import io.thoughtware.dal.jpa.criterial.condition.QueryCondition;
import io.thoughtware.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
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
     * 查询总数
     * @param repositoryId
     * @return
     */
    public int findTestCaseNum(String repositoryId) {
        String caseSql = "Select count(1) as total from teston_testcase where repository_id = '" + repositoryId+ "'";
        Integer caseTotal = jpaTemplate.getJdbcTemplate().queryForObject(caseSql, new Object[]{}, Integer.class);

        return caseTotal;
    }

    /**
     * 查询不同用例类型的数量，功能，接口，ui，性能
     * @param repositoryId
     * @return
     */
    public HashMap<String, Integer> findDiffTypeCaseNum(String repositoryId) {
        HashMap<String, Integer> diffTypeCaseNum = new HashMap<>();

        int testCaseNum = findTestCaseNum(repositoryId);
        diffTypeCaseNum.put("all",testCaseNum);

        String functionSql = "Select count(1) as total from teston_testcase where repository_id = '" + repositoryId+ "'" + " AND test_type = 'function'";
        Integer functionTotal = jpaTemplate.getJdbcTemplate().queryForObject(functionSql, new Object[]{}, Integer.class);
        diffTypeCaseNum.put("function", functionTotal);

        String apiSql = "Select count(1) as total from teston_testcase where repository_id = '" + repositoryId+ "'" + " AND test_type = 'api'";
        Integer apiTotal = jpaTemplate.getJdbcTemplate().queryForObject(apiSql, new Object[]{}, Integer.class);
        diffTypeCaseNum.put("api", apiTotal);

        String uiSql = "Select count(1) as total from teston_testcase where repository_id = '" + repositoryId+ "'" + " AND test_type = 'ui'";
        Integer uiTotal = jpaTemplate.getJdbcTemplate().queryForObject(uiSql, new Object[]{}, Integer.class);
        diffTypeCaseNum.put("ui", uiTotal);

        String performSql = "Select count(1) as total from teston_testcase where repository_id = '" + repositoryId+ "'" + " AND test_type = 'perform'";
        Integer performTotal = jpaTemplate.getJdbcTemplate().queryForObject(performSql, new Object[]{}, Integer.class);
        diffTypeCaseNum.put("perform", performTotal);

        return diffTypeCaseNum;
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
                .eq("status",testCaseQuery.getStatus())
                .in("repositoryId",testCaseQuery.getInList())
                .in("caseType",testCaseQuery.getCaseTypeList())
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
                .eq("status",testCaseQuery.getStatus())
                .eq("createUser",testCaseQuery.getCreateUser())
                .notIn("id",testCaseQuery.getNotInList())
                .in("repositoryId",testCaseQuery.getInList())
                .in("caseType",testCaseQuery.getCaseTypeList())
                .like("name", testCaseQuery.getName())
                .pagination(testCaseQuery.getPageParam())
                .orders(testCaseQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, TestCasesEntity.class);
    }


}