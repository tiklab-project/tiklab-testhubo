package io.thoughtware.teston.testplan.instance.service;

import io.thoughtware.eam.common.context.LoginContext;
import io.thoughtware.teston.testplan.instance.dao.TestPlanInstanceDao;
import io.thoughtware.teston.testplan.instance.model.TestPlanCaseInstanceBind;
import io.thoughtware.teston.testplan.instance.model.TestPlanCaseInstanceBindQuery;
import io.thoughtware.teston.testplan.instance.model.TestPlanInstance;
import io.thoughtware.teston.testplan.instance.model.TestPlanInstanceQuery;
import io.thoughtware.rpc.annotation.Exporter;
import io.thoughtware.teston.testplan.instance.entity.TestPlanInstanceEntity;
import io.thoughtware.toolkit.beans.BeanMapper;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.core.page.PaginationBuilder;
import io.thoughtware.toolkit.join.JoinTemplate;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

/**
* 测试计划实例 服务
*/
@Service
@Exporter
public class TestPlanInstanceServiceImpl implements TestPlanInstanceService {

    @Autowired
    TestPlanInstanceDao testPlanInstanceDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    TestPlanCaseInstanceBindService testPlanCaseInstanceBindService;

    @Override
    public String createTestPlanInstance(@NotNull @Valid TestPlanInstance testPlanInstance) {
        testPlanInstance.setCreateTime(new Timestamp(System.currentTimeMillis()));
        testPlanInstance.setCreateUser(LoginContext.getLoginId());

        TestPlanInstanceEntity testPlanInstanceEntity = BeanMapper.map(testPlanInstance, TestPlanInstanceEntity.class);

        return testPlanInstanceDao.createTestPlanInstance(testPlanInstanceEntity);
    }

    @Override
    public void updateTestPlanInstance(@NotNull @Valid TestPlanInstance testPlanInstance) {
        TestPlanInstanceEntity testPlanInstanceEntity = BeanMapper.map(testPlanInstance, TestPlanInstanceEntity.class);

        testPlanInstanceDao.updateTestPlanInstance(testPlanInstanceEntity);
    }

    @Override
    public void deleteTestPlanInstance(@NotNull String id) {
        testPlanInstanceDao.deleteTestPlanInstance(id);

        TestPlanCaseInstanceBindQuery testPlanCaseInstanceBindQuery = new TestPlanCaseInstanceBindQuery();
        testPlanCaseInstanceBindQuery.setTestPlanInstanceId(id);
        List<TestPlanCaseInstanceBind> testPlanCaseInstanceBindList = testPlanCaseInstanceBindService.findTestPlanCaseInstanceBindList(testPlanCaseInstanceBindQuery);
        if(CollectionUtils.isNotEmpty(testPlanCaseInstanceBindList)&&testPlanCaseInstanceBindList.size()>0){
            for(TestPlanCaseInstanceBind testPlanCaseInstanceBind:testPlanCaseInstanceBindList){
                testPlanCaseInstanceBindService.deleteTestPlanCaseInstanceBind(testPlanCaseInstanceBind.getId());
            }
        }

    }

    @Override
    public TestPlanInstance findOne(String id) {
        TestPlanInstanceEntity testPlanInstanceEntity = testPlanInstanceDao.findTestPlanInstance(id);

        TestPlanInstance testPlanInstance = BeanMapper.map(testPlanInstanceEntity, TestPlanInstance.class);
        return testPlanInstance;
    }

    @Override
    public List<TestPlanInstance> findList(List<String> idList) {
        List<TestPlanInstanceEntity> testPlanInstanceEntityList =  testPlanInstanceDao.findTestPlanInstanceList(idList);

        List<TestPlanInstance> testPlanInstanceList =  BeanMapper.mapList(testPlanInstanceEntityList,TestPlanInstance.class);
        return testPlanInstanceList;
    }

    @Override
    public TestPlanInstance findTestPlanInstance(@NotNull String id) {
        TestPlanInstance testPlanInstance = findOne(id);

        joinTemplate.joinQuery(testPlanInstance);

        return testPlanInstance;
    }

    @Override
    public List<TestPlanInstance> findAllTestPlanInstance() {
        List<TestPlanInstanceEntity> testPlanInstanceEntityList =  testPlanInstanceDao.findAllTestPlanInstance();

        List<TestPlanInstance> testPlanInstanceList =  BeanMapper.mapList(testPlanInstanceEntityList,TestPlanInstance.class);

        joinTemplate.joinQuery(testPlanInstanceList);

        return testPlanInstanceList;
    }

    @Override
    public List<TestPlanInstance> findTestPlanInstanceList(TestPlanInstanceQuery testPlanInstanceQuery) {
        List<TestPlanInstanceEntity> testPlanInstanceEntityList = testPlanInstanceDao.findTestPlanInstanceList(testPlanInstanceQuery);

        List<TestPlanInstance> testPlanInstanceList = BeanMapper.mapList(testPlanInstanceEntityList,TestPlanInstance.class);

        joinTemplate.joinQuery(testPlanInstanceList);

        return testPlanInstanceList;
    }

    @Override
    public Pagination<TestPlanInstance> findTestPlanInstancePage(TestPlanInstanceQuery testPlanInstanceQuery) {
        Pagination<TestPlanInstanceEntity>  pagination = testPlanInstanceDao.findPlanCasePage(testPlanInstanceQuery);

        List<TestPlanInstance> testPlanInstanceList = BeanMapper.mapList(pagination.getDataList(),TestPlanInstance.class);

        joinTemplate.joinQuery(testPlanInstanceList);

        return PaginationBuilder.build(pagination,testPlanInstanceList);
    }


}