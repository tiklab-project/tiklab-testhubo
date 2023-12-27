package io.thoughtware.teston.testplan.instance.service;

import io.thoughtware.teston.testplan.instance.dao.TestPlanCaseInstanceBindDao;
import io.thoughtware.teston.testplan.instance.model.TestPlanCaseInstanceBind;
import io.thoughtware.teston.testplan.instance.model.TestPlanCaseInstanceBindQuery;
import io.thoughtware.rpc.annotation.Exporter;
import io.thoughtware.teston.testplan.instance.entity.TestPlanCaseInstanceBindEntity;
import io.thoughtware.toolkit.beans.BeanMapper;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.core.page.PaginationBuilder;
import io.thoughtware.toolkit.join.JoinTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;


/**
* 测试计划下用例的实例中间层 服务
*/
@Service
@Exporter
public class TestPlanCaseInstanceBindServiceImpl implements TestPlanCaseInstanceBindService {

    @Autowired
    TestPlanCaseInstanceBindDao testPlanCaseInstanceBindDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createTestPlanCaseInstanceBind(@NotNull @Valid TestPlanCaseInstanceBind testPlanCaseInstanceBind) {
        TestPlanCaseInstanceBindEntity testPlanCaseInstanceBindEntity = BeanMapper.map(testPlanCaseInstanceBind, TestPlanCaseInstanceBindEntity.class);

        return testPlanCaseInstanceBindDao.createTestPlanCaseInstanceBind(testPlanCaseInstanceBindEntity);
    }

    @Override
    public void updateTestPlanCaseInstanceBind(@NotNull @Valid TestPlanCaseInstanceBind testPlanCaseInstanceBind) {
        TestPlanCaseInstanceBindEntity testPlanCaseInstanceBindEntity = BeanMapper.map(testPlanCaseInstanceBind, TestPlanCaseInstanceBindEntity.class);

        testPlanCaseInstanceBindDao.updateTestPlanCaseInstanceBind(testPlanCaseInstanceBindEntity);
    }

    @Override
    public void deleteTestPlanCaseInstanceBind(@NotNull String id) {
        testPlanCaseInstanceBindDao.deleteTestPlanCaseInstanceBind(id);


    }

    @Override
    public TestPlanCaseInstanceBind findOne(String id) {
        TestPlanCaseInstanceBindEntity testPlanCaseInstanceBindEntity = testPlanCaseInstanceBindDao.findTestPlanCaseInstanceBind(id);

        TestPlanCaseInstanceBind testPlanCaseInstanceBind = BeanMapper.map(testPlanCaseInstanceBindEntity, TestPlanCaseInstanceBind.class);
        return testPlanCaseInstanceBind;
    }

    @Override
    public List<TestPlanCaseInstanceBind> findList(List<String> idList) {
        List<TestPlanCaseInstanceBindEntity> testPlanCaseInstanceBindEntityList =  testPlanCaseInstanceBindDao.findTestPlanCaseInstanceBindList(idList);

        List<TestPlanCaseInstanceBind> testPlanCaseInstanceBindList =  BeanMapper.mapList(testPlanCaseInstanceBindEntityList,TestPlanCaseInstanceBind.class);
        return testPlanCaseInstanceBindList;
    }

    @Override
    public TestPlanCaseInstanceBind findTestPlanCaseInstanceBind(@NotNull String id) {
        TestPlanCaseInstanceBind testPlanCaseInstanceBind = findOne(id);

        joinTemplate.joinQuery(testPlanCaseInstanceBind);

        return testPlanCaseInstanceBind;
    }

    @Override
    public List<TestPlanCaseInstanceBind> findAllTestPlanCaseInstanceBind() {
        List<TestPlanCaseInstanceBindEntity> testPlanCaseInstanceBindEntityList =  testPlanCaseInstanceBindDao.findAllTestPlanCaseInstanceBind();

        List<TestPlanCaseInstanceBind> testPlanCaseInstanceBindList =  BeanMapper.mapList(testPlanCaseInstanceBindEntityList,TestPlanCaseInstanceBind.class);

        joinTemplate.joinQuery(testPlanCaseInstanceBindList);

        return testPlanCaseInstanceBindList;
    }

    @Override
    public List<TestPlanCaseInstanceBind> findTestPlanCaseInstanceBindList(TestPlanCaseInstanceBindQuery testPlanCaseInstanceBindQuery) {
        List<TestPlanCaseInstanceBindEntity> testPlanCaseInstanceBindEntityList = testPlanCaseInstanceBindDao.findTestPlanCaseInstanceBindList(testPlanCaseInstanceBindQuery);

        List<TestPlanCaseInstanceBind> testPlanCaseInstanceBindList = BeanMapper.mapList(testPlanCaseInstanceBindEntityList,TestPlanCaseInstanceBind.class);

        joinTemplate.joinQuery(testPlanCaseInstanceBindList);

        return testPlanCaseInstanceBindList;
    }

    @Override
    public Pagination<TestPlanCaseInstanceBind> findTestPlanCaseInstanceBindPage(TestPlanCaseInstanceBindQuery testPlanCaseInstanceBindQuery) {
        Pagination<TestPlanCaseInstanceBindEntity>  pagination = testPlanCaseInstanceBindDao.findTestPlanCaseInstanceBindPage(testPlanCaseInstanceBindQuery);

        List<TestPlanCaseInstanceBind> testPlanCaseInstanceBindList = BeanMapper.mapList(pagination.getDataList(),TestPlanCaseInstanceBind.class);

        joinTemplate.joinQuery(testPlanCaseInstanceBindList);

        return PaginationBuilder.build(pagination,testPlanCaseInstanceBindList);
    }
}