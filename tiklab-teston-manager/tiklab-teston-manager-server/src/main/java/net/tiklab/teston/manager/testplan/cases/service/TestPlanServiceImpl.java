package net.tiklab.teston.manager.testplan.cases.service;

import net.tiklab.teston.manager.testplan.cases.dao.TestPlanDao;
import net.tiklab.teston.manager.testplan.cases.dao.TestPlanCaseDao;
import net.tiklab.teston.manager.testplan.cases.entity.TestPlanCaseEntity;
import net.tiklab.teston.manager.testplan.cases.entity.TestPlanEntity;
import net.tiklab.teston.manager.testplan.cases.model.TestPlan;
import net.tiklab.teston.manager.testplan.cases.model.TestPlanCaseQuery;
import net.tiklab.teston.manager.testplan.cases.model.TestPlanQuery;

import net.tiklab.core.page.Pagination;
import net.tiklab.core.page.PaginationBuilder;
import net.tiklab.beans.BeanMapper;
import net.tiklab.join.JoinTemplate;
import org.apache.commons.collections.CollectionUtils;
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
public class TestPlanServiceImpl implements TestPlanService {

    @Autowired
    TestPlanDao testPlanDao;

    @Autowired
    TestPlanCaseDao testPlanCaseDao;

    @Autowired
    JoinTemplate joinTemplate;


    @Override
    public String createTestPlan(@NotNull @Valid TestPlan testPlan) {
        TestPlanEntity testPlanEntity = BeanMapper.map(testPlan, TestPlanEntity.class);
        testPlanEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));

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
    }

    @Override
    public TestPlan findOne(String id) {
        TestPlanEntity testPlanEntity = testPlanDao.findTestPlan(id);

        TestPlan testPlan = BeanMapper.map(testPlanEntity, TestPlan.class);
        return testPlan;
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
            List<TestPlanCaseEntity> testPlanDetailList = testPlanCaseDao.findTestPlanCaseList(new TestPlanCaseQuery().setTestPlanId(testPlan.getId()));
            if (CollectionUtils.isNotEmpty(testPlanDetailList)){
                testPlan.setTestCaseNum(testPlanDetailList.size());
            }else {
                testPlan.setTestCaseNum(0);
            }
        }
        joinTemplate.joinQuery(testPlanList);

        return PaginationBuilder.build(pagination,testPlanList);
    }


}