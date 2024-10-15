package io.tiklab.testhubo.testplan.cases.service;

import io.tiklab.testhubo.category.model.Category;
import io.tiklab.testhubo.common.MagicValue;
import io.tiklab.testhubo.testplan.cases.dao.TestPlanCaseDao;
import io.tiklab.testhubo.testplan.cases.entity.PlanCaseEntity;
import io.tiklab.testhubo.testplan.cases.entity.TestPlanCaseEntity;
import io.tiklab.testhubo.testplan.cases.model.TestPlan;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.toolkit.join.JoinTemplate;
import io.tiklab.testhubo.category.service.CategoryService;
import io.tiklab.testhubo.testplan.cases.model.PlanCase;
import io.tiklab.testhubo.testplan.cases.model.TestPlanCase;
import io.tiklab.testhubo.testplan.cases.model.TestPlanCaseQuery;

import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.user.user.model.User;
import io.tiklab.user.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.*;

/**
* 测试计划绑定的用例 服务
*/
@Service
public class TestPlanCaseServiceImpl implements TestPlanCaseService {

    @Autowired
    TestPlanCaseDao testPlanDetailDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    CategoryService categoryService;

    @Autowired
    UserService userService;

    @Autowired
    TestPlanService testPlanService;


    @Override
    public String createTestPlanCase(@NotNull @Valid TestPlanCase testPlanCase) {
        TestPlanCaseEntity testPlanCaseEntity = BeanMapper.map(testPlanCase, TestPlanCaseEntity.class);
        //初始化 2为未执行
        testPlanCaseEntity.setStatus(2);
        return testPlanDetailDao.createTestPlanCase(testPlanCaseEntity);
    }

    @Override
    public void updateTestPlanCase(@NotNull @Valid TestPlanCase testPlanCase) {
        TestPlanCaseEntity testPlanCaseEntity = BeanMapper.map(testPlanCase, TestPlanCaseEntity.class);

        testPlanDetailDao.updateTestPlanCase(testPlanCaseEntity);
    }

    @Override
    public void deleteTestPlanCase(@NotNull String id) {
        testPlanDetailDao.deleteTestPlanCase(id);
    }

    @Override
    public void deleteAllTestPlanCase(String testPlanId) {
        TestPlanCaseQuery testPlanCaseQuery = new TestPlanCaseQuery();
        testPlanCaseQuery.setTestPlanId(testPlanId);
        List<TestPlanCase> testPlanCaseList = findTestPlanCaseList(testPlanCaseQuery);
        for(TestPlanCase testPlanCase:testPlanCaseList){
            deleteTestPlanCase(testPlanCase.getId());
        }
    }


    @Override
    public TestPlanCase findOne(String id) {
        TestPlanCaseEntity testPlanCaseEntity = testPlanDetailDao.findTestPlanCase(id);

        TestPlanCase testPlanCase = BeanMapper.map(testPlanCaseEntity, TestPlanCase.class);
        return testPlanCase;
    }

    @Override
    public List<TestPlanCase> findList(List<String> idList) {
        List<TestPlanCaseEntity> testPlanCaseEntityList =  testPlanDetailDao.findTestPlanCaseList(idList);

        List<TestPlanCase> testPlanCaseList =  BeanMapper.mapList(testPlanCaseEntityList, TestPlanCase.class);
        return testPlanCaseList;
    }

    @Override
    public TestPlanCase findTestPlanCase(@NotNull String id) {
        TestPlanCase testPlanCase = findOne(id);

        joinTemplate.joinQuery(testPlanCase);
        return testPlanCase;
    }

    @Override
    public List<TestPlanCase> findAllTestPlanCase() {
        List<TestPlanCaseEntity> testPlanCaseEntityList =  testPlanDetailDao.findAllTestPlanCase();

        List<TestPlanCase> testPlanCaseList =  BeanMapper.mapList(testPlanCaseEntityList, TestPlanCase.class);

        joinTemplate.joinQuery(testPlanCaseList);
        return testPlanCaseList;
    }

    @Override
    public List<TestPlanCase> findTestPlanCaseList(TestPlanCaseQuery testPlanCaseQuery) {
        List<TestPlanCaseEntity> testPlanCaseEntityList = testPlanDetailDao.findTestPlanCaseList(testPlanCaseQuery);

        List<TestPlanCase> testPlanCaseList = BeanMapper.mapList(testPlanCaseEntityList, TestPlanCase.class);

        joinTemplate.joinQuery(testPlanCaseList);

        return testPlanCaseList;
    }

    @Override
    public int findPlanCaseNum(String testPlanId) {
        int planCaseNum = testPlanDetailDao.findPlanCaseNum(testPlanId);

        return planCaseNum;
    }

    @Override
    public Pagination<TestPlanCase> findTestPlanCasePage(TestPlanCaseQuery testPlanCaseQuery) {
        Pagination<TestPlanCaseEntity>  pagination = testPlanDetailDao.findTestPlanCasePage(testPlanCaseQuery);
        List<TestPlanCase> testPlanCaseList = BeanMapper.mapList(pagination.getDataList(), TestPlanCase.class);

        joinTemplate.joinQuery(testPlanCaseList);

        if(testPlanCaseList!=null){
            for(TestPlanCase testPlanCase:testPlanCaseList){
                Category category = null;
                if(testPlanCase.getTestCase().getCategory()!=null){
                    category = categoryService.findCategory(testPlanCase.getTestCase().getCategory().getId());
                }

                User user = null;
                if(testPlanCase.getTestCase().getCreateUser()!=null){
                    user = userService.findUser(testPlanCase.getTestCase().getCreateUser().getId());
                }

                testPlanCase.getTestCase().setCategory(category);
                testPlanCase.getTestCase().setCreateUser(user);
            }
        }
        return PaginationBuilder.build(pagination, testPlanCaseList);
    }



    @Override
    public List<PlanCase> findPlanCaseList(TestPlanCaseQuery testPlanCaseQuery) {
        List<PlanCaseEntity> planCaseEntityList= testPlanDetailDao.findPlanCaseList(testPlanCaseQuery);
        List<PlanCase> planCaseList = BeanMapper.mapList(planCaseEntityList, PlanCase.class);
        joinTemplate.joinQuery(planCaseList);
        return planCaseList;
    }


    @Override
    public Pagination<PlanCase> findPlanCasePage(TestPlanCaseQuery testPlanCaseQuery) {
        Pagination<PlanCaseEntity> planCasePage = testPlanDetailDao.findPlanCasePage(testPlanCaseQuery);
        List<PlanCase> testCaseList = BeanMapper.mapList(planCasePage.getDataList(), PlanCase.class);
        joinTemplate.joinQuery(testCaseList);
        return PaginationBuilder.build(planCasePage, testCaseList);
    }


    @Override
    public Pagination<PlanCase> findTestCasePage(TestPlanCaseQuery testPlanCaseQuery) {

        String testPlanId = testPlanCaseQuery.getTestPlanId();
        TestPlan testPlan = testPlanService.findTestPlan(testPlanId);
        if(Objects.equals(testPlan.getType(), MagicValue.TEST_PLAN_TYPE_FUNCTION)){
            testPlanCaseQuery.setTestType(MagicValue.TEST_PLAN_TYPE_FUNCTION);
        }else {
            String[] caseTypeList = {
                    MagicValue.CASE_TYPE_API_UNIT,
                    MagicValue.CASE_TYPE_API_SCENE,
                    MagicValue.CASE_TYPE_WEB,
                    MagicValue.CASE_TYPE_APP
            };
            testPlanCaseQuery.setCaseTypeList(caseTypeList);
        }

        Pagination<PlanCaseEntity> planCasePage = testPlanDetailDao.findTestCasePage(testPlanCaseQuery);
        List<PlanCase> testCaseList = BeanMapper.mapList(planCasePage.getDataList(), PlanCase.class);
        joinTemplate.joinQuery(testCaseList);
        return PaginationBuilder.build(planCasePage, testCaseList);
    }


    @Override
    public void planBindCase(List<TestPlanCase> testPlanCaseList) {
        for(TestPlanCase testPlanCase: testPlanCaseList){
            createTestPlanCase(testPlanCase);
        }
    }

    @Override
    public Boolean isCaseExist(String caseId){
        Integer caseExist = testPlanDetailDao.isCaseExist(caseId);

        if(caseExist!=null&&caseExist>0){
            return true;
        }else {
            return false;
        }
    }
    @Override
    public Map<String,Integer> getCaseTypeNum(String testPlanId){
        List<Map<String, Object>> resultList = testPlanDetailDao.getCaseTypeNum(testPlanId);
        Map<String, Integer> resultMap = new HashMap<>();
        for (Map<String, Object> row : resultList) {
            String caseType = (String) row.get("case_type");
            int total = ((Number) row.get("total")).intValue();
            resultMap.put(caseType, total);
        }
        return resultMap;
    }

    @Override
    public Map<String,Integer> getTestTypeNum(String testPlanId){
        List<Map<String, Object>> resultList = testPlanDetailDao.getTestTypeNum(testPlanId);
        Map<String, Integer> resultMap = new HashMap<>();
        int allTotal = 0;
        for (Map<String, Object> row : resultList) {
            String caseType = (String) row.get("test_type");
            int total = ((Number) row.get("total")).intValue();
            allTotal+=total;
            resultMap.put(caseType, total);
        }

        resultMap.put("all", allTotal);
        return resultMap;
    }



}