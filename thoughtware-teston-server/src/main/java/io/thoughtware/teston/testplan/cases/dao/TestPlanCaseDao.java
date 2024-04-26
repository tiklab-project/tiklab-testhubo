package io.thoughtware.teston.testplan.cases.dao;

import io.thoughtware.teston.testplan.cases.entity.TestPlanCaseEntity;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.dal.jpa.criterial.condition.DeleteCondition;
import io.thoughtware.dal.jpa.criterial.condition.QueryCondition;
import io.thoughtware.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.thoughtware.teston.testplan.cases.model.TestPlanCaseQuery;
import io.thoughtware.dal.jpa.JpaTemplate;
import io.thoughtware.teston.testplan.cases.entity.PlanCaseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * 查询测试计划绑定的用例总数
     * @param testPlanId
     * @return
     */
    public int findPlanCaseNum(String testPlanId) {
        String sql = "Select count(1) as total from teston_test_plan_detail where test_plan_id = '" + testPlanId+ "'";
        Integer total = jpaTemplate.getJdbcTemplate().queryForObject(sql, new Object[]{}, Integer.class);

        return total;
    }


    /**
     * 查询绑定的用例 列表
     * @param testPlanCaseQuery
     * @return
     */
    public List<PlanCaseEntity> findPlanCaseList(TestPlanCaseQuery testPlanCaseQuery) {
        StringBuilder modelSqlBuilder = new StringBuilder();
        modelSqlBuilder.append("SELECT teston_test_plan_detail.id AS plan_case_id,teston_testcase.id, teston_testcase.create_user, teston_testcase.create_time, teston_testcase.case_type,teston_testcase.test_type, teston_testcase.category_id, teston_testcase.name ")
                .append("FROM teston_test_plan_detail ")
                .append("JOIN teston_testcase ON teston_testcase.id = teston_test_plan_detail.test_case_id ")
                .append("JOIN teston_test_plan ON teston_test_plan.id = teston_test_plan_detail.test_plan_id ")
                .append("WHERE teston_test_plan.id = '").append(testPlanCaseQuery.getTestPlanId()).append("'");

        if(testPlanCaseQuery.getCaseTypeList()!=null&&testPlanCaseQuery.getCaseTypeList().length>0){
            StringBuilder caseTypeList = new StringBuilder();
            for (int i = 0; i < testPlanCaseQuery.getCaseTypeList().length; i++) {
                String s = testPlanCaseQuery.getCaseTypeList()[i];
                caseTypeList.append("'").append(s).append("'");

                if (i < testPlanCaseQuery.getCaseTypeList().length - 1) {
                    caseTypeList.append(",");
                }
            }

            modelSqlBuilder.append(" AND teston_testcase.case_type in (").append(caseTypeList).append(")");
        }

        String modelSql = modelSqlBuilder.toString();
        List<PlanCaseEntity> planCaseEntityList = jpaTemplate.getJdbcTemplate()
                .query(modelSql,new BeanPropertyRowMapper<>(PlanCaseEntity.class));

        return planCaseEntityList;
    }


    /**
     * 按分页查询绑定的用例
     * @param testPlanCaseQuery
     * @return
     */
    public Pagination<PlanCaseEntity> findPlanCasePage(TestPlanCaseQuery testPlanCaseQuery){
        StringBuilder modelSqlBuilder = new StringBuilder();
        modelSqlBuilder.append("SELECT teston_test_plan_detail.id AS plan_case_id,teston_testcase.id, teston_testcase.create_user, teston_testcase.create_time, teston_testcase.case_type,teston_testcase.test_type, teston_testcase.category_id, teston_testcase.name ")
                .append("FROM teston_test_plan_detail ")
                .append("JOIN teston_testcase ON teston_testcase.id = teston_test_plan_detail.test_case_id ")
                .append("JOIN teston_test_plan ON teston_test_plan.id = teston_test_plan_detail.test_plan_id ")
                .append("WHERE teston_test_plan.id = '").append(testPlanCaseQuery.getTestPlanId()).append("'");

        if (testPlanCaseQuery.getName() != null) {
            modelSqlBuilder.append(" AND teston_testcase.name LIKE '%").append(testPlanCaseQuery.getName()).append("%'");
        }

        if (testPlanCaseQuery.getCaseType() != null) {
            modelSqlBuilder.append(" AND teston_testcase.case_type = '").append(testPlanCaseQuery.getCaseType()).append("'");
        }

        if (testPlanCaseQuery.getTestType() != null) {
            modelSqlBuilder.append(" And teston_testcase.test_type = '").append(testPlanCaseQuery.getTestType()).append("'");
        }

        if(testPlanCaseQuery.getCaseTypeList()!=null&&testPlanCaseQuery.getCaseTypeList().length>0){
            StringBuilder caseTypeList = new StringBuilder();
            for (int i = 0; i < testPlanCaseQuery.getCaseTypeList().length; i++) {
                String s = testPlanCaseQuery.getCaseTypeList()[i];
                caseTypeList.append("'").append(s).append("'");

                if (i < testPlanCaseQuery.getCaseTypeList().length - 1) {
                    caseTypeList.append(",");
                }
            }

            modelSqlBuilder.append(" AND teston_testcase.case_type in (").append(caseTypeList).append(")");
        }

        String modelSql = modelSqlBuilder.toString();

        Pagination<PlanCaseEntity> page = jpaTemplate.getJdbcTemplate().findPage(modelSql, new Object[]{}, testPlanCaseQuery.getPageParam(), new BeanPropertyRowMapper<>(PlanCaseEntity.class));
        return page;
    }


    /**
     * 查询未关联的用例
     * @param testPlanCaseQuery
     * @return
     */
    public Pagination<PlanCaseEntity> findTestCasePage(TestPlanCaseQuery testPlanCaseQuery) {
        StringBuilder modelSqlBuilder = new StringBuilder();
        modelSqlBuilder.append("SELECT  teston_testcase.id AS plan_case_id, teston_testcase.id, teston_testcase.director,  teston_testcase.create_user, teston_testcase.create_time, teston_testcase.case_type,teston_testcase.test_type, teston_testcase.category_id, teston_testcase.name, teston_testcase.status,teston_testcase.priority_level ")
                .append(" FROM teston_testcase ")
                .append(" WHERE teston_testcase.repository_id = '").append(testPlanCaseQuery.getRepositoryId()).append("'")
                .append(" AND NOT EXISTS (  ")
                .append(" SELECT 1   ")
                .append(" FROM teston_test_plan_detail  ")
                .append(" JOIN teston_test_plan ON teston_test_plan.id = teston_test_plan_detail.test_plan_id ")
                .append(" WHERE teston_test_plan_detail.test_case_id = teston_testcase.id ")
                .append(" AND teston_test_plan.id = '").append(testPlanCaseQuery.getTestPlanId()).append("')");

        if (testPlanCaseQuery.getTestType() != null) {
            modelSqlBuilder.append(" And teston_testcase.test_type = '").append(testPlanCaseQuery.getTestType()).append("'");
        }

        if (testPlanCaseQuery.getName() != null) {
            modelSqlBuilder.append(" AND teston_testcase.name LIKE '%").append(testPlanCaseQuery.getName()).append("%'");
        }

        String modelSql = modelSqlBuilder.toString();

        Pagination<PlanCaseEntity> page = jpaTemplate.getJdbcTemplate().findPage(modelSql, new Object[]{}, testPlanCaseQuery.getPageParam(), new BeanPropertyRowMapper<>(PlanCaseEntity.class));
        return page;
    }

    /**
     * 通过用例id，判断是否用例被绑定
     * @param caseId
     * @return
     */
    public Integer isCaseExist(String caseId) {
        String sql = "Select count(1) as total from teston_test_plan_detail where test_case_id = '" + caseId + "'";
        Integer modelTotal = jpaTemplate.getJdbcTemplate().queryForObject(sql, new Object[]{}, Integer.class);

        return modelTotal;
    }

    /**
     * 获取不同用例类型的数量
     * @param testPlanId
     * @return
     */
    public List<Map<String, Object>> getCaseTypeNum(String testPlanId){
        String sql = "SELECT tc.case_type, COUNT(*) AS total " +
                "FROM teston_test_plan_detail AS tpd " +
                "INNER JOIN teston_testcase AS tc ON tpd.test_case_id = tc.id " +
                "INNER JOIN teston_test_plan AS tp ON tpd.test_plan_id = tp.id " +
                "WHERE tp.id = ? " +
                "GROUP BY tc.case_type";

        List<Map<String, Object>> maps = jpaTemplate.getJdbcTemplate().queryForList(sql, testPlanId);

        return maps;
    }


    /**
     * 获取不同测试类型的数量
     * @param testPlanId
     * @return
     */
    public List<Map<String, Object>> getTestTypeNum(String testPlanId){
        String sql = "SELECT tc.test_type, COUNT(*) AS total " +
                "FROM teston_test_plan_detail AS tpd " +
                "INNER JOIN teston_testcase AS tc ON tpd.test_case_id = tc.id " +
                "INNER JOIN teston_test_plan AS tp ON tpd.test_plan_id = tp.id " +
                "WHERE tp.id = ? " +
                "GROUP BY tc.test_type";

        List<Map<String, Object>> maps = jpaTemplate.getJdbcTemplate().queryForList(sql, testPlanId);

        return maps;
    }



}