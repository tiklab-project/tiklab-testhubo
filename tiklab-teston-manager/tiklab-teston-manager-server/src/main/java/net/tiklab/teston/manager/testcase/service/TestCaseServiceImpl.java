package net.tiklab.teston.manager.testcase.service;

import net.tiklab.beans.BeanMapper;
import net.tiklab.core.page.Pagination;
import net.tiklab.core.page.PaginationBuilder;
import net.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import net.tiklab.dal.jpa.criterial.conditionbuilder.DeleteBuilders;
import net.tiklab.join.JoinTemplate;
import net.tiklab.teston.apix.http.perf.instance.model.ApiPerfInstance;
import net.tiklab.teston.app.perf.instance.mode.AppPerfInstance;
import net.tiklab.teston.app.perf.instance.mode.AppPerfInstanceQuery;
import net.tiklab.teston.app.perf.instance.service.AppPerfInstanceService;
import net.tiklab.teston.app.scene.instance.model.AppSceneInstance;
import net.tiklab.teston.app.scene.instance.model.AppSceneInstanceQuery;
import net.tiklab.teston.app.scene.instance.service.AppSceneInstanceService;
import net.tiklab.teston.manager.testcase.dao.TestCaseDao;
import net.tiklab.teston.manager.testcase.entity.TestCaseEntity;
import net.tiklab.teston.apix.http.perf.instance.model.ApiPerfInstanceQuery;
import net.tiklab.teston.apix.http.perf.instance.service.ApiPerfInstanceService;
import net.tiklab.teston.apix.http.scene.instance.model.ApiSceneInstance;
import net.tiklab.teston.apix.http.scene.instance.model.ApiSceneInstanceQuery;
import net.tiklab.teston.apix.http.scene.instance.service.ApiSceneInstanceService;
import net.tiklab.teston.apix.http.unit.instance.model.ApiUnitInstanceBind;
import net.tiklab.teston.apix.http.unit.instance.model.ApiUnitInstanceBindQuery;
import net.tiklab.teston.apix.http.unit.instance.service.ApiUnitInstanceBindService;

import net.tiklab.teston.manager.testcase.model.TestCase;
import net.tiklab.teston.manager.testcase.model.TestCaseQuery;
import net.tiklab.teston.web.perf.instance.model.WebPerfInstance;
import net.tiklab.teston.web.perf.instance.model.WebPerfInstanceQuery;
import net.tiklab.teston.web.perf.instance.service.WebPerfInstanceService;
import net.tiklab.teston.web.scene.instance.model.WebSceneInstance;
import net.tiklab.teston.web.scene.instance.model.WebSceneInstanceQuery;
import net.tiklab.teston.web.scene.instance.service.WebSceneInstanceService;
import net.tiklab.utils.context.LoginContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.*;

/**
* StepServiceImpl
*/
@Service
public class TestCaseServiceImpl implements TestCaseService {

    @Autowired
    TestCaseDao testCaseDao;

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

    @Override
    public String createTestCase(TestCase testCase) {
        TestCaseEntity testCaseEntity = BeanMapper.map(testCase, TestCaseEntity.class);

        //初始化项目成员
        String userId = LoginContext.getLoginId();
        testCaseEntity.setCreateUser(userId);
        testCaseEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));
        testCaseEntity.setUpdateTime(new Timestamp(System.currentTimeMillis()));

        String id = testCaseDao.createTestCase(testCaseEntity);

        return id;
    }

    @Override
    public void updateTestCase(TestCase testCase) {
        TestCaseEntity testCaseEntity = BeanMapper.map(testCase, TestCaseEntity.class);

        testCaseEntity.setUpdateTime(new Timestamp(System.currentTimeMillis()));

        String userId = LoginContext.getLoginId();
        testCaseEntity.setUpdateUser(userId);

        testCaseDao.updateTestCase(testCaseEntity);
    }

    @Override
    public void deleteTestCase(@NotNull String id) {
        testCaseDao.deleteTestCase(id);
    }

    @Override
    public void deleteTestCaseByCategoryId(String categoryId) {
        //删除相关联的子表
        DeleteCondition deleteCondition = DeleteBuilders.createDelete(TestCaseEntity.class)
                .eq("categoryId", categoryId)
                .get();
        testCaseDao.deleteTestCase(deleteCondition);
    }

    @Override
    public TestCase findOne(String id) {
        TestCaseEntity testCaseEntity = testCaseDao.findTestCase(id);

        TestCase testCase = BeanMapper.map(testCaseEntity, TestCase.class);

        return testCase;
    }

    @Override
    public List<TestCase> findList(List<String> idList) {
        List<TestCaseEntity> testCaseList = testCaseDao.findTestCaseList(idList);

        List<TestCase> mapList = BeanMapper.mapList(testCaseList, TestCase.class);

        return mapList;
    }

    @Override
    public TestCase findTestCase(@NotNull String id) {
        TestCase testCase = findOne(id);

        joinTemplate.joinQuery(testCase);

        return testCase;
    }

    @Override
    public List<TestCase> findAllTestCase() {
        List<TestCaseEntity> testCaseEntityList = testCaseDao.findAllTestCase();

        List<TestCase> testCaseList = BeanMapper.mapList(testCaseEntityList, TestCase.class);

        joinTemplate.joinQuery(testCaseList);

        return testCaseList;
    }

    @Override
    public List<TestCase> findTestCaseList(TestCaseQuery testCaseQuery) {
        List<TestCaseEntity> testCaseEntityList = testCaseDao.findTestCaseList(testCaseQuery);

        List<TestCase> testCaseList = BeanMapper.mapList(testCaseEntityList, TestCase.class);

        joinTemplate.joinQuery(testCaseList);

        return testCaseList;
    }

    @Override
    public Pagination<TestCase> findTestCasePage(TestCaseQuery testCaseQuery) {
        Pagination<TestCaseEntity> pagination = testCaseDao.findTestCasePage(testCaseQuery);

        List<TestCase> testCaseList = BeanMapper.mapList(pagination.getDataList(), TestCase.class);

        joinTemplate.joinQuery(testCaseList);

        List<TestCase> newTestCaseList = recentInstance(testCaseList);

        return PaginationBuilder.build(pagination,newTestCaseList);
    }

    /**
     * 给用例设置最近一次执行结果
     * @param testCaseList
     * @return
     */
    private List<TestCase> recentInstance(List<TestCase> testCaseList){

        ArrayList<TestCase> newTestCaseList = new ArrayList<>();


        if(testCaseList!=null&&testCaseList.size()>0){
            for(TestCase testCase:testCaseList){
                HashMap<Object, Object> recentInstance = new HashMap<>();

                //根据不同的测试类型进行测试
                if(Objects.equals(testCase.getTestType(),"auto")||Objects.equals(testCase.getTestType(),"perform")){

                    switch (testCase.getCaseType()) {
                        case "api-unit":
                            ApiUnitInstanceBindQuery apiUnitInstanceBindQuery = new ApiUnitInstanceBindQuery();
                            apiUnitInstanceBindQuery.setApiUnitId(testCase.getId());

                            List<ApiUnitInstanceBind> apiUnitInstanceBindList = apiUnitInstanceBindService.findApiUnitInstanceBindList(apiUnitInstanceBindQuery);

                            if(apiUnitInstanceBindList!=null&&apiUnitInstanceBindList.size()>0){
                                ApiUnitInstanceBind apiUnitInstanceBind = apiUnitInstanceBindList.get(0);
                                recentInstance.put("result",apiUnitInstanceBind.getApiUnitInstance().getResult());
                                recentInstance.put("executeNumber",apiUnitInstanceBind.getApiUnitInstance().getExecuteNumber());
                                recentInstance.put("instanceId",apiUnitInstanceBind.getId());
                            }else {
                                recentInstance.put("result",2);
                                recentInstance.put("instanceId",null);
                                recentInstance.put("executeNumber",null);
                            }
                            break;
                        case "api-scene":
                            ApiSceneInstanceQuery apiSceneInstanceQuery = new ApiSceneInstanceQuery();
                            apiSceneInstanceQuery.setApiSceneId(testCase.getId());
                            List<ApiSceneInstance> apiSceneInstanceList = apiSceneInstanceService.findApiSceneInstanceList(apiSceneInstanceQuery);
                            if(apiSceneInstanceList!=null&&apiSceneInstanceList.size()>0){
                                ApiSceneInstance apiSceneInstance = apiSceneInstanceList.get(0);
                                recentInstance.put("result",apiSceneInstance.getResult());
                                recentInstance.put("executeNumber",apiSceneInstance.getExecuteNumber());
                                recentInstance.put("instanceId",apiSceneInstance.getId());
                            }else {
                                recentInstance.put("result",2);
                                recentInstance.put("instanceId",null);
                                recentInstance.put("executeNumber",null);
                            }
                            break;
                        case "api-perform":
                            ApiPerfInstanceQuery apiPerfInstanceQuery = new ApiPerfInstanceQuery();
                            apiPerfInstanceQuery.setApiPerfId(testCase.getId());
                            List<ApiPerfInstance> apiPerfInstanceList = apiPerfInstanceService.findApiPerfInstanceList(apiPerfInstanceQuery);
                            if(apiPerfInstanceList!=null&&apiPerfInstanceList.size()>0){
                                ApiPerfInstance apiPerfInstance = apiPerfInstanceList.get(0);
                                recentInstance.put("result",apiPerfInstance.getResult());
                                recentInstance.put("instanceId",apiPerfInstance.getId());
                                recentInstance.put("executeNumber",apiPerfInstance.getExecuteNumber());
                            }else {
                                recentInstance.put("result",2);
                                recentInstance.put("instanceId",null);
                                recentInstance.put("executeNumber",null);
                            }
                            break;
                        case "web-scene":
                            WebSceneInstanceQuery webSceneInstanceQuery = new WebSceneInstanceQuery();
                            webSceneInstanceQuery.setWebSceneId(testCase.getId());
                            List<WebSceneInstance> webSceneInstanceList = webSceneInstanceService.findWebSceneInstanceList(webSceneInstanceQuery);
                            if(webSceneInstanceList!=null&&webSceneInstanceList.size()>0){
                                WebSceneInstance webSceneInstance = webSceneInstanceList.get(0);
                                recentInstance.put("result",webSceneInstance.getResult());
                                recentInstance.put("instanceId",webSceneInstance.getId());
                                recentInstance.put("executeNumber",webSceneInstance.getExecuteNumber());
                            }else {
                                recentInstance.put("result",2);
                                recentInstance.put("instanceId",null);
                                recentInstance.put("executeNumber",null);
                            }
                            break;
                        case "web-perform":
                            WebPerfInstanceQuery webPerfInstanceQuery = new WebPerfInstanceQuery();
                            webPerfInstanceQuery.setWebPerfId(testCase.getId());
                            List<WebPerfInstance> webPerfInstanceList = webPerfInstanceService.findWebPerfInstanceList(webPerfInstanceQuery);
                            if(webPerfInstanceList!=null&&webPerfInstanceList.size()>0){
                                WebPerfInstance webPerfInstance = webPerfInstanceList.get(0);
                                recentInstance.put("result",webPerfInstance.getResult());
                                recentInstance.put("instanceId",webPerfInstance.getId());
                                recentInstance.put("executeNumber",webPerfInstance.getExecuteNumber());
                            }else {
                                recentInstance.put("result",2);
                                recentInstance.put("instanceId",null);
                                recentInstance.put("executeNumber",null);
                            }
                            break;

                        case "app-scene":
                            AppSceneInstanceQuery appSceneInstanceQuery = new AppSceneInstanceQuery();
                            appSceneInstanceQuery.setAppSceneId(testCase.getId());
                            List<AppSceneInstance> appSceneInstanceList = appSceneInstanceService.findAppSceneInstanceList(appSceneInstanceQuery);
                            if(appSceneInstanceList!=null&&appSceneInstanceList.size()>0){
                                AppSceneInstance appSceneInstance = appSceneInstanceList.get(0);
                                recentInstance.put("result",appSceneInstance.getResult());
                                recentInstance.put("instanceId",appSceneInstance.getId());
                                recentInstance.put("executeNumber",appSceneInstance.getExecuteNumber());
                            }else {
                                recentInstance.put("result",2);
                                recentInstance.put("instanceId",null);
                                recentInstance.put("executeNumber",null);
                            }
                            break;

                        case "app-perform":
                            AppPerfInstanceQuery appPerfInstanceQuery = new AppPerfInstanceQuery();
                            appPerfInstanceQuery.setAppPerfId(testCase.getId());
                            List<AppPerfInstance> appPerfInstanceList = appPerfInstanceService.findAppPerfInstanceList(appPerfInstanceQuery);
                            if(appPerfInstanceList!=null&&appPerfInstanceList.size()>0){
                                AppPerfInstance appPerfInstance = appPerfInstanceList.get(0);
                                recentInstance.put("result",appPerfInstance.getResult());
                                recentInstance.put("instanceId",appPerfInstance.getId());
                                recentInstance.put("executeNumber",appPerfInstance.getExecuteNumber());
                            }else {
                                recentInstance.put("result",2);
                                recentInstance.put("instanceId",null);
                                recentInstance.put("executeNumber",null);
                            }
                            break;

                        default:
                            break;
                    }
                }else {
                    recentInstance.put("result",2);
                    recentInstance.put("instanceId",null);
                    recentInstance.put("executeNumber",null);
                }

                testCase.setRecentInstance(recentInstance);
                newTestCaseList.add(testCase);
            }
        }

        return newTestCaseList;
    }



}