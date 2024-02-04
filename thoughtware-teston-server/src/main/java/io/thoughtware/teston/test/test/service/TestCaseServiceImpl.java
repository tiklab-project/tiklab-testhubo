package io.thoughtware.teston.test.test.service;

import com.alibaba.fastjson.JSONObject;
import io.thoughtware.teston.common.MagicValue;
import io.thoughtware.teston.instance.model.Instance;
import io.thoughtware.teston.instance.model.InstanceQuery;
import io.thoughtware.teston.instance.service.InstanceService;
import io.thoughtware.teston.test.apix.http.scene.instance.model.ApiSceneInstance;
import io.thoughtware.teston.test.apix.http.scene.instance.model.ApiSceneInstanceQuery;
import io.thoughtware.teston.test.apix.http.scene.instance.service.ApiSceneInstanceService;
import io.thoughtware.teston.test.apix.http.unit.instance.model.ApiUnitInstanceBind;
import io.thoughtware.teston.test.apix.http.unit.instance.model.ApiUnitInstanceBindQuery;
import io.thoughtware.teston.test.apix.http.unit.instance.service.ApiUnitInstanceBindService;
import io.thoughtware.teston.test.app.perf.instance.mode.AppPerfInstance;
import io.thoughtware.teston.test.app.perf.instance.mode.AppPerfInstanceQuery;
import io.thoughtware.teston.test.app.perf.instance.service.AppPerfInstanceService;
import io.thoughtware.teston.test.app.scene.instance.model.AppSceneInstance;
import io.thoughtware.teston.test.app.scene.instance.model.AppSceneInstanceQuery;
import io.thoughtware.teston.test.app.scene.instance.service.AppSceneInstanceService;
import io.thoughtware.teston.test.test.model.TestCase;
import io.thoughtware.teston.test.test.model.TestCaseQuery;
import io.thoughtware.teston.test.test.model.TestCaseRecent;
import io.thoughtware.teston.test.test.model.TestCaseRecentQuery;
import io.thoughtware.rpc.annotation.Exporter;
import io.thoughtware.teston.test.test.entity.TestCasesEntity;
import io.thoughtware.teston.test.web.perf.instance.model.WebPerfInstance;
import io.thoughtware.teston.test.web.perf.instance.model.WebPerfInstanceQuery;
import io.thoughtware.teston.test.web.perf.instance.service.WebPerfInstanceService;
import io.thoughtware.toolkit.beans.BeanMapper;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.core.page.PaginationBuilder;
import io.thoughtware.dal.jpa.criterial.condition.DeleteCondition;
import io.thoughtware.dal.jpa.criterial.conditionbuilder.DeleteBuilders;
import io.thoughtware.toolkit.join.JoinTemplate;
import io.thoughtware.teston.test.apix.http.perf.instance.model.ApiPerfInstance;
import io.thoughtware.teston.test.test.dao.TestCaseDao;
import io.thoughtware.teston.test.apix.http.perf.instance.model.ApiPerfInstanceQuery;
import io.thoughtware.teston.test.apix.http.perf.instance.service.ApiPerfInstanceService;

import io.thoughtware.teston.test.web.scene.instance.model.WebSceneInstance;
import io.thoughtware.teston.test.web.scene.instance.model.WebSceneInstanceQuery;
import io.thoughtware.teston.test.web.scene.instance.service.WebSceneInstanceService;
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
    ApiUnitInstanceBindService apiUnitInstanceBindService;

    @Autowired
    ApiSceneInstanceService apiSceneInstanceService;

    @Autowired
    ApiPerfInstanceService apiPerfInstanceService;

    @Autowired
    WebSceneInstanceService webSceneInstanceService;

    @Autowired
    WebPerfInstanceService webPerfInstanceService;

    @Autowired
    AppSceneInstanceService appSceneInstanceService;

    @Autowired
    AppPerfInstanceService appPerfInstanceService;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    InstanceService instanceService;

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
    public void deleteTestCase(@NotNull String id) {
        //删除最近访问的TestCase
        //根据用例id 获取到最近访问id，删除
        TestCaseRecentQuery testCaseRecent = new TestCaseRecentQuery();
        testCaseRecent.setTestCaseId(id);
        List<TestCaseRecent> testCaseRecentList = testCaseRecentService.findTestCaseRecentList(testCaseRecent);
        if(testCaseRecentList!=null&&testCaseRecentList.size()>0){
            testCaseRecentService.deleteTestCaseRecent(testCaseRecentList.get(0).getId());
        }


        testCaseDao.deleteTestCase(id);
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
        JSONObject jsonObject = JSONObject.parseObject(instance.getContent());
        String resultStr = jsonObject.getString("result");

        int result = 2; // 默认情况下，使用2作为结果
        if (resultStr != null) {
            if (resultStr.matches("\\d+")) {
                result = Integer.parseInt(resultStr); // 如果结果是数字，使用该数字
            } else {
                result = 2;
            }
        }else {
            // 如果结果字符串不是数字，使用3表示异常情况
            result = 3;
        }

        HashMap<Object, Object> details = new HashMap<>();
        details.put("result", result);
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