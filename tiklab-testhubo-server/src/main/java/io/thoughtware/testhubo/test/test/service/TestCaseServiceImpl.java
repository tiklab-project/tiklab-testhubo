package io.thoughtware.testhubo.test.test.service;

import io.thoughtware.testhubo.common.MagicValue;
import io.thoughtware.testhubo.instance.model.Instance;
import io.thoughtware.testhubo.instance.model.InstanceQuery;
import io.thoughtware.testhubo.instance.service.InstanceService;
import io.thoughtware.testhubo.test.apix.http.perf.cases.service.ApiPerfCaseService;
import io.thoughtware.testhubo.test.apix.http.scene.cases.service.ApiSceneCaseService;
import io.thoughtware.testhubo.test.apix.http.unit.cases.service.ApiUnitCaseService;
import io.thoughtware.testhubo.test.func.service.FuncUnitCaseService;
import io.thoughtware.testhubo.test.test.model.TestCase;
import io.thoughtware.testhubo.test.test.model.TestCaseQuery;
import io.thoughtware.testhubo.test.test.model.TestCaseRecent;
import io.thoughtware.testhubo.test.test.model.TestCaseRecentQuery;
import io.thoughtware.rpc.annotation.Exporter;
import io.thoughtware.testhubo.test.test.entity.TestCasesEntity;
import io.thoughtware.toolkit.beans.BeanMapper;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.core.page.PaginationBuilder;
import io.thoughtware.dal.jpa.criterial.condition.DeleteCondition;
import io.thoughtware.dal.jpa.criterial.conditionbuilder.DeleteBuilders;
import io.thoughtware.toolkit.join.JoinTemplate;
import io.thoughtware.testhubo.test.test.dao.TestCaseDao;
import io.thoughtware.eam.common.context.LoginContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

/**
* 测试用例 服务
*/
@Service
@Exporter
public class TestCaseServiceImpl implements TestCaseService {

    @Autowired
    TestCaseDao testCaseDao;

    @Autowired
    TestCaseRecentService testCaseRecentService;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    InstanceService instanceService;

    @Autowired
    ApiUnitCaseService apiUnitCaseService;

    @Autowired
    ApiSceneCaseService apiSceneCaseService;

    @Autowired
    ApiPerfCaseService apiPerfCaseService;

    @Autowired
    FuncUnitCaseService funcUnitCaseService;

    @Override
    public String createTestCase(TestCase testCase) {
        TestCasesEntity testCasesEntity = BeanMapper.map(testCase, TestCasesEntity.class);

        //初始化项目成员
        String userId = LoginContext.getLoginId();
        testCasesEntity.setCreateUser(userId);
        testCasesEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));
        testCasesEntity.setUpdateTime(new Timestamp(System.currentTimeMillis()));

        String id = testCaseDao.createTestCase(testCasesEntity);

        return id;
    }

    @Override
    public void updateTestCase(TestCase testCase) {
        TestCasesEntity testCasesEntity = BeanMapper.map(testCase, TestCasesEntity.class);

        testCasesEntity.setUpdateTime(new Timestamp(System.currentTimeMillis()));

        String userId = LoginContext.getLoginId();
        testCasesEntity.setUpdateUser(userId);

        testCaseDao.updateTestCase(testCasesEntity);
    }

    @Override
    public void deleteTestCase(@NotNull String id,String caseType) {
        //删除最近访问的TestCase
        //根据用例id 获取到最近访问id，删除
        TestCaseRecentQuery testCaseRecent = new TestCaseRecentQuery();
        testCaseRecent.setTestCaseId(id);
        List<TestCaseRecent> testCaseRecentList = testCaseRecentService.findTestCaseRecentList(testCaseRecent);
        if(testCaseRecentList!=null&&testCaseRecentList.size()>0){
            testCaseRecentService.deleteTestCaseRecent(testCaseRecentList.get(0).getId());
        }

        //删除用例
        testCaseDao.deleteTestCase(id);

        //删除对应的用例
        switch (caseType) {
            case MagicValue.CASE_TYPE_API_UNIT -> {
                apiUnitCaseService.deleteApiUnitCase(id);
                break;
            }
            case MagicValue.CASE_TYPE_API_SCENE -> {
                apiSceneCaseService.deleteApiSceneCase(id);
                break;
            }
            case MagicValue.CASE_TYPE_API_PERFORM -> {
                apiPerfCaseService.deleteApiPerfCase(id);
                break;
            }
            case MagicValue.CASE_TYPE_FUNCTION -> {
                funcUnitCaseService.deleteFuncUnitCase(id);
                break;
            }
            default -> {
            }
        }
    }

    @Override
    public void deleteAllTestCase( String repositoryId) {
        TestCaseQuery testCaseQuery = new TestCaseQuery();
        testCaseQuery.setRepositoryId(repositoryId);
        List<TestCase> testCaseList = findTestCaseList(testCaseQuery);
        for(TestCase testCase:testCaseList){
            deleteTestCase(testCase.getId(),testCase.getCaseType());
        }
    }




    @Override
    public void deleteTestCaseByCategoryId(String categoryId) {
        //删除相关联的子表
        DeleteCondition deleteCondition = DeleteBuilders.createDelete(TestCasesEntity.class)
                .eq("categoryId", categoryId)
                .get();
        testCaseDao.deleteTestCase(deleteCondition);
    }

    @Override
    public TestCase findOne(String id) {
        TestCasesEntity testCasesEntity = testCaseDao.findTestCase(id);

        TestCase testCase = BeanMapper.map(testCasesEntity, TestCase.class);

        return testCase;
    }

    @Override
    public List<TestCase> findList(List<String> idList) {
        List<TestCasesEntity> testCaseList = testCaseDao.findTestCaseList(idList);

        List<TestCase> mapList = BeanMapper.mapList(testCaseList, TestCase.class);

        return mapList;
    }

    @Override
    public int findTestCaseNum(String repositoryId) {
        int testCaseNum = testCaseDao.findTestCaseNum(repositoryId);
        return testCaseNum;
    }

    @Override
    public HashMap<String, Integer> findDiffTypeCaseNum(String repositoryId) {
        HashMap<String, Integer> diffTypeCaseNum = testCaseDao.findDiffTypeCaseNum(repositoryId);
        return diffTypeCaseNum;
    }

    @Override
    public TestCase findTestCase(@NotNull String id) {
        TestCase testCase = findOne(id);

        joinTemplate.joinQuery(testCase);

        return testCase;
    }

    @Override
    public List<TestCase> findAllTestCase() {
        List<TestCasesEntity> testCasesEntityList = testCaseDao.findAllTestCase();

        List<TestCase> testCaseList = BeanMapper.mapList(testCasesEntityList, TestCase.class);

        joinTemplate.joinQuery(testCaseList);

        return testCaseList;
    }

    @Override
    public List<TestCase> findTestCaseList(TestCaseQuery testCaseQuery) {
        List<TestCasesEntity> testCasesEntityList = testCaseDao.findTestCaseList(testCaseQuery);

        List<TestCase> testCaseList = BeanMapper.mapList(testCasesEntityList, TestCase.class);

        joinTemplate.joinQuery(testCaseList);

        return testCaseList;
    }

    @Override
    public Pagination<TestCase> findTestCasePage(TestCaseQuery testCaseQuery) {
        Pagination<TestCasesEntity> pagination = testCaseDao.findTestCasePage(testCaseQuery);

        List<TestCase> testCaseList = BeanMapper.mapList(pagination.getDataList(), TestCase.class);

        joinTemplate.joinQuery(testCaseList);

        List<TestCase> newTestCaseList = recentInstance(testCaseList);

        return PaginationBuilder.build(pagination, newTestCaseList);
    }


    /**
     * 给用例设置最近一次执行结果
     * @param testCaseList
     * @return
     */
    private List<TestCase> recentInstance(List<TestCase> testCaseList) {
        // 提前处理空或空列表的情况
        if (testCaseList == null || testCaseList.isEmpty()) {
            return Collections.emptyList();
        }

        Set<String> validTestTypes = new HashSet<>(Arrays.asList(
                MagicValue.TEST_TYPE_API,
                MagicValue.TEST_TYPE_UI,
                MagicValue.TEST_TYPE_PERFORM
        ));

        return testCaseList.stream().map(testCase -> {
            if (validTestTypes.contains(testCase.getTestType())) {
                Map<Object, Object> recentInstance = getRecentInstanceByCaseType(testCase);
                testCase.setRecentInstance(recentInstance);
            } else {
                testCase.setRecentInstance(createDefaultRecentInstance(2));
            }
            return testCase;
        }).collect(Collectors.toList());
    }

    private Map<Object, Object> getRecentInstanceByCaseType(TestCase testCase) {
        Set<String> caseTypes = new HashSet<>(Arrays.asList(
                MagicValue.CASE_TYPE_API_UNIT,
                MagicValue.CASE_TYPE_API_SCENE,
                MagicValue.CASE_TYPE_API_PERFORM,
                MagicValue.CASE_TYPE_WEB,
                MagicValue.CASE_TYPE_APP
        ));

        if (caseTypes.contains(testCase.getCaseType())) {
            return getInstanceInfo(testCase.getCaseType(), testCase.getId());
        } else {
            return createDefaultRecentInstance(2);
        }
    }

    // 函数用于获取实例信息
    private HashMap<Object, Object> getInstanceInfo(String instanceType, String testCaseId) {
        InstanceQuery instanceQuery = new InstanceQuery();
        instanceQuery.setBelongId(testCaseId);
        instanceQuery.setType(instanceType);

        Pagination<Instance> instancePage = instanceService.findInstancePage(instanceQuery);

        // 如果未找到实例，直接返回包含默认值的Map
        if (instancePage == null || instancePage.getDataList().isEmpty()) {
            return createDefaultRecentInstance(2); // 使用2作为默认结果，表示没有找到实例
        }

        Instance instance = instancePage.getDataList().get(0);
        return extractInstanceDetails(instance);
    }

    private HashMap<Object, Object> extractInstanceDetails(Instance instance) {
        String status = instance.getStatus();

        HashMap<Object, Object> details = new HashMap<>();
        details.put("result", status);
        details.put("executeNumber", instance.getExecuteNumber());
        details.put("instanceId", instance.getId());

        return details;
    }

    private HashMap<Object, Object> createDefaultRecentInstance(int defaultResult) {
        HashMap<Object, Object> recentInstance = new HashMap<>();
        recentInstance.put("result", defaultResult);
        recentInstance.put("instanceId", null);
        recentInstance.put("executeNumber", null);
        return recentInstance;
    }
}