package io.thoughtware.teston.testplan.cases.service;

import io.thoughtware.rpc.annotation.Exporter;
import io.thoughtware.teston.instance.service.InstanceService;
import io.thoughtware.teston.testplan.cases.dao.TestPlanDao;
import io.thoughtware.teston.testplan.cases.entity.TestPlanEntity;
import io.thoughtware.teston.testplan.cases.model.TestPlan;
import io.thoughtware.teston.testplan.cases.model.TestPlanQuery;

import io.thoughtware.core.page.Pagination;
import io.thoughtware.core.page.PaginationBuilder;
import io.thoughtware.teston.testplan.quartz.service.QuartzPlanService;
import io.thoughtware.toolkit.beans.BeanMapper;
import io.thoughtware.toolkit.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

/**
* 测试计划 服务
*/
@Service
@Exporter
public class TestPlanServiceImpl implements TestPlanService {

    @Autowired
    TestPlanDao testPlanDao;

    @Autowired
    TestPlanCaseService testPlanCaseService;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    InstanceService instanceService;

    @Autowired
    QuartzPlanService quartzPlanService;

    @Override
    public String createTestPlan(@NotNull @Valid TestPlan testPlan) {
        TestPlanEntity testPlanEntity = BeanMapper.map(testPlan, TestPlanEntity.class);
        testPlanEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));
        //状态
        testPlanEntity.setState(0);

        return testPlanDao.createTestPlan(testPlanEntity);
    }

    @Override
    public void updateTestPlan(@NotNull @Valid TestPlan testPlan) {
        TestPlanEntity testPlanEntity = BeanMapper.map(testPlan, TestPlanEntity.class);
        testPlanEntity.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        testPlanDao.updateTestPlan(testPlanEntity);
    }

    @Override
    public void deleteTestPlan(@NotNull String id) {
        testPlanDao.deleteTestPlan(id);

        testPlanCaseService.deleteAllTestPlanCase(id);

        instanceService.deleteAllInstance(id);

        quartzPlanService.deleteAllQuartzPlan(id);
    }

    @Override
    public void deleteAllTestPlan(String repositoryId) {
        TestPlanQuery testPlanQuery = new TestPlanQuery();
        testPlanQuery.setRepositoryId(repositoryId);
        List<TestPlan> testPlanList = findTestPlanList(testPlanQuery);
        for(TestPlan testPlan: testPlanList){
            deleteTestPlan(testPlan.getId());
        }
    }


    @Override
    public TestPlan findOne(String id) {
        TestPlanEntity testPlanEntity = testPlanDao.findTestPlan(id);

        TestPlan testPlan = BeanMapper.map(testPlanEntity, TestPlan.class);
        return testPlan;
    }

    @Override
    public int findTestPlanNum(String repositoryId) {
        int testPlanNum = testPlanDao.findTestPlanNum(repositoryId);
        return testPlanNum;
    }

    @Override
    public List<TestPlan> findList(List<String> idList) {
        List<TestPlanEntity> testPlanEntityList =  testPlanDao.findTestPlanList(idList);

        List<TestPlan> testPlanList =  BeanMapper.mapList(testPlanEntityList,TestPlan.class);
        return testPlanList;
    }

    @Override
    public TestPlan findTestPlan(@NotNull String id) {
        TestPlan testPlan = findOne(id);

        joinTemplate.joinQuery(testPlan);
        return testPlan;
    }

    @Override
    public List<TestPlan> findAllTestPlan() {
        List<TestPlanEntity> testPlanEntityList =  testPlanDao.findAllTestPlan();

        List<TestPlan> testPlanList =  BeanMapper.mapList(testPlanEntityList,TestPlan.class);

        joinTemplate.joinQuery(testPlanList);
        return testPlanList;
    }

    @Override
    public List<TestPlan> findTestPlanList(TestPlanQuery testPlanQuery) {
        List<TestPlanEntity> testPlanEntityList = testPlanDao.findTestPlanList(testPlanQuery);

        List<TestPlan> testPlanList = BeanMapper.mapList(testPlanEntityList,TestPlan.class);

        joinTemplate.joinQuery(testPlanList);

        return testPlanList;
    }

    @Override
    public Pagination<TestPlan> findTestPlanPage(TestPlanQuery testPlanQuery) {
        Pagination<TestPlanEntity>  pagination = testPlanDao.findTestPlanPage(testPlanQuery);

        List<TestPlan> testPlanList = BeanMapper.mapList(pagination.getDataList(),TestPlan.class);
        //添加测试计划关联的用例数
        for (TestPlan testPlan:testPlanList){
            int planCaseNum = testPlanCaseService.findPlanCaseNum(testPlan.getId());
            testPlan.setTestCaseNum(planCaseNum);
        }
        joinTemplate.joinQuery(testPlanList);

        return PaginationBuilder.build(pagination,testPlanList);
    }


}