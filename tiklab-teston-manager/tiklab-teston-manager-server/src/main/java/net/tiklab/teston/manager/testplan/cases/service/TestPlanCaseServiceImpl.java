package net.tiklab.teston.manager.testplan.cases.service;

import net.tiklab.beans.BeanMapper;
import net.tiklab.join.JoinTemplate;
import net.tiklab.teston.manager.category.service.CategoryService;
import net.tiklab.teston.manager.testcase.model.TestCase;
import net.tiklab.teston.manager.testcase.model.TestCaseQuery;
import net.tiklab.teston.manager.testcase.service.TestCaseService;
import net.tiklab.teston.manager.testplan.cases.dao.TestPlanCaseDao;
import net.tiklab.teston.manager.testplan.cases.entity.TestPlanCaseEntity;
import net.tiklab.teston.manager.testplan.cases.model.TestPlanCase;
import net.tiklab.teston.manager.testplan.cases.model.TestPlanCaseQuery;

import net.tiklab.core.page.Pagination;
import net.tiklab.core.page.PaginationBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.*;

/**
* TestPlanCaseServiceImpl
*/
@Service
public class TestPlanCaseServiceImpl implements TestPlanCaseService {

    @Autowired
    TestPlanCaseDao testPlanDetailDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    TestCaseService testCaseService;

    @Autowired
    CategoryService categoryService;


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
    public Pagination<TestPlanCase> findTestPlanCasePage(TestPlanCaseQuery testPlanCaseQuery) {
        Pagination<TestPlanCaseEntity>  pagination = testPlanDetailDao.findTestPlanCasePage(testPlanCaseQuery);

        List<TestPlanCase> testPlanCaseList = BeanMapper.mapList(pagination.getDataList(), TestPlanCase.class);

        joinTemplate.joinQuery(testPlanCaseList);

        return PaginationBuilder.build(pagination, testPlanCaseList);
    }

    @Override
    public Pagination<TestCase> findTesCaseList(TestPlanCase testPlanCase) {

        //查询所有用例
        TestCaseQuery testCaseQuery = new TestCaseQuery();
        testCaseQuery.setRepositoryId(testPlanCase.getRepositoryId());
        Pagination<TestCase> testCasePage = testCaseService.findTestCasePage(testCaseQuery);

        return testCasePage;
    }

    @Override
    public Pagination<TestPlanCase> findBindTestCaseList(TestPlanCaseQuery testPlanCaseQuery) {
        Pagination<TestPlanCase> testPlanDetailPage = findTestPlanCasePage(testPlanCaseQuery);

        return testPlanDetailPage;
    }
}