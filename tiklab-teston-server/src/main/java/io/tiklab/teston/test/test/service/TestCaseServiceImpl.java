package io.tiklab.teston.test.test.service;

import io.tiklab.rpc.annotation.Exporter;
import io.tiklab.teston.test.app.perf.instance.mode.AppPerfInstance;
import io.tiklab.teston.test.app.perf.instance.mode.AppPerfInstanceQuery;
import io.tiklab.teston.test.app.perf.instance.service.AppPerfInstanceService;
import io.tiklab.teston.test.app.scene.instance.model.AppSceneInstance;
import io.tiklab.teston.test.app.scene.instance.model.AppSceneInstanceQuery;
import io.tiklab.teston.test.app.scene.instance.service.AppSceneInstanceService;
import io.tiklab.teston.test.test.entity.TestCasesEntity;
import io.tiklab.teston.test.test.model.TestCaseRecent;
import io.tiklab.teston.test.test.model.TestCaseRecentQuery;
import io.tiklab.teston.test.test.model.TestCases;
import io.tiklab.teston.test.web.perf.instance.model.WebPerfInstance;
import io.tiklab.teston.test.web.perf.instance.model.WebPerfInstanceQuery;
import io.tiklab.teston.test.web.perf.instance.service.WebPerfInstanceService;
import io.tiklab.beans.BeanMapper;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.DeleteBuilders;
import io.tiklab.join.JoinTemplate;
import io.tiklab.teston.test.apix.http.perf.instance.model.ApiPerfInstance;
import io.tiklab.teston.test.test.dao.TestCaseDao;
import io.tiklab.teston.test.apix.http.perf.instance.model.ApiPerfInstanceQuery;
import io.tiklab.teston.test.apix.http.perf.instance.service.ApiPerfInstanceService;
import io.tiklab.teston.test.apix.http.scene.instance.model.ApiSceneInstance;
import io.tiklab.teston.test.apix.http.scene.instance.model.ApiSceneInstanceQuery;
import io.tiklab.teston.test.apix.http.scene.instance.service.ApiSceneInstanceService;
import io.tiklab.teston.test.apix.http.unit.instance.model.ApiUnitInstanceBind;
import io.tiklab.teston.test.apix.http.unit.instance.model.ApiUnitInstanceBindQuery;
import io.tiklab.teston.test.apix.http.unit.instance.service.ApiUnitInstanceBindService;

import io.tiklab.teston.test.test.model.TestCaseQuery;
import io.tiklab.teston.test.web.scene.instance.model.WebSceneInstance;
import io.tiklab.teston.test.web.scene.instance.model.WebSceneInstanceQuery;
import io.tiklab.teston.test.web.scene.instance.service.WebSceneInstanceService;
import io.tiklab.eam.common.context.LoginContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.*;

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

    @Override
    public String createTestCase(TestCases testCases) {
        TestCasesEntity testCasesEntity = BeanMapper.map(testCases, TestCasesEntity.class);

        //初始化项目成员
        String userId = LoginContext.getLoginId();
        testCasesEntity.setCreateUser(userId);
        testCasesEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));
        testCasesEntity.setUpdateTime(new Timestamp(System.currentTimeMillis()));

        String id = testCaseDao.createTestCase(testCasesEntity);

        return id;
    }

    @Override
    public void updateTestCase(TestCases testCases) {
        TestCasesEntity testCasesEntity = BeanMapper.map(testCases, TestCasesEntity.class);

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
    public TestCases findOne(String id) {
        TestCasesEntity testCasesEntity = testCaseDao.findTestCase(id);

        TestCases testCases = BeanMapper.map(testCasesEntity, TestCases.class);

        return testCases;
    }

    @Override
    public List<TestCases> findList(List<String> idList) {
        List<TestCasesEntity> testCaseList = testCaseDao.findTestCaseList(idList);

        List<TestCases> mapList = BeanMapper.mapList(testCaseList, TestCases.class);

        return mapList;
    }

    @Override
    public TestCases findTestCase(@NotNull String id) {
        TestCases testCases = findOne(id);

        joinTemplate.joinQuery(testCases);

        return testCases;
    }

    @Override
    public List<TestCases> findAllTestCase() {
        List<TestCasesEntity> testCasesEntityList = testCaseDao.findAllTestCase();

        List<TestCases> testCasesList = BeanMapper.mapList(testCasesEntityList, TestCases.class);

        joinTemplate.joinQuery(testCasesList);

        return testCasesList;
    }

    @Override
    public List<TestCases> findTestCaseList(TestCaseQuery testCaseQuery) {
        List<TestCasesEntity> testCasesEntityList = testCaseDao.findTestCaseList(testCaseQuery);

        List<TestCases> testCasesList = BeanMapper.mapList(testCasesEntityList, TestCases.class);

        joinTemplate.joinQuery(testCasesList);

        return testCasesList;
    }

    @Override
    public Pagination<TestCases> findTestCasePage(TestCaseQuery testCaseQuery) {
        Pagination<TestCasesEntity> pagination = testCaseDao.findTestCasePage(testCaseQuery);

        List<TestCases> testCasesList = BeanMapper.mapList(pagination.getDataList(), TestCases.class);

        joinTemplate.joinQuery(testCasesList);

        List<TestCases> newTestCasesList = recentInstance(testCasesList);

        return PaginationBuilder.build(pagination, newTestCasesList);
    }

    /**
     * 给用例设置最近一次执行结果
     * @param testCasesList
     * @return
     */
    private List<TestCases> recentInstance(List<TestCases> testCasesList){

        ArrayList<TestCases> newTestCasesList = new ArrayList<>();


        if(testCasesList !=null&& testCasesList.size()>0){
            for(TestCases testCases : testCasesList){
                HashMap<Object, Object> recentInstance = new HashMap<>();

                //根据不同的测试类型进行测试
                if(Objects.equals(testCases.getTestType(),"auto")||Objects.equals(testCases.getTestType(),"perform")){

                    switch (testCases.getCaseType()) {
                        case "api-unit":
                            ApiUnitInstanceBindQuery apiUnitInstanceBindQuery = new ApiUnitInstanceBindQuery();
                            apiUnitInstanceBindQuery.setApiUnitId(testCases.getId());

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
                            apiSceneInstanceQuery.setApiSceneId(testCases.getId());
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
                            apiPerfInstanceQuery.setApiPerfId(testCases.getId());
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
                            webSceneInstanceQuery.setWebSceneId(testCases.getId());
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
                            webPerfInstanceQuery.setWebPerfId(testCases.getId());
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
                            appSceneInstanceQuery.setAppSceneId(testCases.getId());
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
                            appPerfInstanceQuery.setAppPerfId(testCases.getId());
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

                testCases.setRecentInstance(recentInstance);
                newTestCasesList.add(testCases);
            }
        }

        return newTestCasesList;
    }



}