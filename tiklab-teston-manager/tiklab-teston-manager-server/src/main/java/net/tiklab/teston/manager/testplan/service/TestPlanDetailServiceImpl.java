package net.tiklab.teston.manager.testplan.service;

import net.tiklab.beans.BeanMapper;
import net.tiklab.join.JoinTemplate;
import net.tiklab.teston.manager.category.service.CategoryService;
import net.tiklab.teston.manager.testcase.model.TestCase;
import net.tiklab.teston.manager.testcase.model.TestCaseQuery;
import net.tiklab.teston.manager.testcase.service.TestCaseService;
import net.tiklab.teston.manager.testplan.dao.TestPlanDetailDao;
import net.tiklab.teston.manager.testplan.entity.TestPlanDetailEntity;
import net.tiklab.teston.manager.testplan.model.TestPlanDetail;
import net.tiklab.teston.manager.testplan.model.TestPlanDetailQuery;

import net.tiklab.core.page.Pagination;
import net.tiklab.core.page.PaginationBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.*;

/**
* TestPlanDetailServiceImpl
*/
@Service
public class TestPlanDetailServiceImpl implements TestPlanDetailService {

    @Autowired
    TestPlanDetailDao testPlanDetailDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    TestCaseService testCaseService;

    @Autowired
    CategoryService categoryService;


    @Override
    public String createTestPlanDetail(@NotNull @Valid TestPlanDetail testPlanDetail) {
        TestPlanDetailEntity testPlanDetailEntity = BeanMapper.map(testPlanDetail, TestPlanDetailEntity.class);
        //初始化 2为未执行
        testPlanDetailEntity.setStatus(2);
        return testPlanDetailDao.createTestPlanDetail(testPlanDetailEntity);
    }

    @Override
    public void updateTestPlanDetail(@NotNull @Valid TestPlanDetail testPlanDetail) {
        TestPlanDetailEntity testPlanDetailEntity = BeanMapper.map(testPlanDetail, TestPlanDetailEntity.class);

        testPlanDetailDao.updateTestPlanDetail(testPlanDetailEntity);
    }

    @Override
    public void deleteTestPlanDetail(@NotNull String id) {
        testPlanDetailDao.deleteTestPlanDetail(id);
    }

    @Override
    public TestPlanDetail findOne(String id) {
        TestPlanDetailEntity testPlanDetailEntity = testPlanDetailDao.findTestPlanDetail(id);

        TestPlanDetail testPlanDetail = BeanMapper.map(testPlanDetailEntity, TestPlanDetail.class);
        return testPlanDetail;
    }

    @Override
    public List<TestPlanDetail> findList(List<String> idList) {
        List<TestPlanDetailEntity> testPlanDetailEntityList =  testPlanDetailDao.findTestPlanDetailList(idList);

        List<TestPlanDetail> testPlanDetailList =  BeanMapper.mapList(testPlanDetailEntityList,TestPlanDetail.class);
        return testPlanDetailList;
    }

    @Override
    public TestPlanDetail findTestPlanDetail(@NotNull String id) {
        TestPlanDetail testPlanDetail = findOne(id);

        joinTemplate.joinQuery(testPlanDetail);
        return testPlanDetail;
    }

    @Override
    public List<TestPlanDetail> findAllTestPlanDetail() {
        List<TestPlanDetailEntity> testPlanDetailEntityList =  testPlanDetailDao.findAllTestPlanDetail();

        List<TestPlanDetail> testPlanDetailList =  BeanMapper.mapList(testPlanDetailEntityList,TestPlanDetail.class);

        joinTemplate.joinQuery(testPlanDetailList);
        return testPlanDetailList;
    }

    @Override
    public List<TestPlanDetail> findTestPlanDetailList(TestPlanDetailQuery testPlanDetailQuery) {
        List<TestPlanDetailEntity> testPlanDetailEntityList = testPlanDetailDao.findTestPlanDetailList(testPlanDetailQuery);

        List<TestPlanDetail> testPlanDetailList = BeanMapper.mapList(testPlanDetailEntityList,TestPlanDetail.class);

        joinTemplate.joinQuery(testPlanDetailList);

        return testPlanDetailList;
    }

    @Override
    public Pagination<TestPlanDetail> findTestPlanDetailPage(TestPlanDetailQuery testPlanDetailQuery) {
        Pagination<TestPlanDetailEntity>  pagination = testPlanDetailDao.findTestPlanDetailPage(testPlanDetailQuery);

        List<TestPlanDetail> testPlanDetailList = BeanMapper.mapList(pagination.getDataList(),TestPlanDetail.class);

        joinTemplate.joinQuery(testPlanDetailList);

        return PaginationBuilder.build(pagination,testPlanDetailList);
    }

    @Override
    public Pagination<TestCase> findTesCaseList(TestPlanDetail testPlanDetail) {

        //查询所有用例
        TestCaseQuery testCaseQuery = new TestCaseQuery();
        testCaseQuery.setRepositoryId(testPlanDetail.getRepositoryId());
        Pagination<TestCase> testCasePage = testCaseService.findTestCasePage(testCaseQuery);

        return testCasePage;
    }

    @Override
    public Pagination<TestPlanDetail> findBindTestCaseList(TestPlanDetailQuery testPlanDetailQuery) {
        Pagination<TestPlanDetail> testPlanDetailPage = findTestPlanDetailPage(testPlanDetailQuery);

        return testPlanDetailPage;
    }
}