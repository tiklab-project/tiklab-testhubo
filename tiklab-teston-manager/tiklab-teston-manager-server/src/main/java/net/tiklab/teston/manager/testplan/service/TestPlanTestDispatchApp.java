package net.tiklab.teston.manager.testplan.service;

import net.tiklab.teston.app.perf.cases.model.AppPerfCase;
import net.tiklab.teston.app.perf.execute.mode.AppPerfTestRequest;
import net.tiklab.teston.app.perf.instance.service.AppPerfInstanceService;
import net.tiklab.teston.app.perf.execute.service.AppPerfTestDispatchService;
import net.tiklab.teston.app.scene.instance.model.AppSceneInstance;
import net.tiklab.teston.app.scene.execute.model.AppSceneTestRequest;
import net.tiklab.teston.app.scene.execute.model.AppSceneTestResponse;
import net.tiklab.teston.app.scene.execute.model.AppTestConfig;
import net.tiklab.teston.app.scene.instance.service.AppSceneInstanceService;
import net.tiklab.teston.app.scene.execute.service.AppSceneTestDispatchService;
import net.tiklab.teston.manager.support.environment.model.AppEnv;
import net.tiklab.teston.manager.support.environment.service.AppEnvService;
import net.tiklab.teston.manager.testplan.model.TestPlanCaseInstanceBind;
import net.tiklab.teston.manager.testplan.model.TestPlanDetail;
import net.tiklab.teston.manager.testplan.model.TestPlanTestData;
import net.tiklab.teston.app.perf.instance.mode.AppPerfInstance;
import net.tiklab.teston.app.perf.execute.mode.AppPerfTestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestPlanTestDispatchApp {

    @Autowired
    TestPlanCaseInstanceBindService testPlanCaseInstanceBindService;

    @Autowired
    AppSceneTestDispatchService appSceneTestDispatchService;

    @Autowired
    AppSceneInstanceService appSceneInstanceService;

    @Autowired
    AppEnvService appEnvService;
    
    @Autowired
    AppPerfTestDispatchService appPerfTestDispatchService;
    
    @Autowired
    AppPerfInstanceService appPerfInstanceService;

    
    public Integer appPerfStatus = 0;

    private String appPerfInstanceId;

    private String planInstanceId;

    private boolean isFirst=true;

    private TestPlanDetail testPlanDetailData;

    private AppPerfTestRequest appPerfTestRequest = new AppPerfTestRequest();



    /**
     * 执行AppScene
     * @param testPlanDetail
     * @param testPlanTestData
     * @param testPlanInstanceId
     * @return
     */
    public TestPlanCaseInstanceBind exeAppScene(TestPlanDetail testPlanDetail, TestPlanTestData testPlanTestData, String testPlanInstanceId){
        String caseType = testPlanDetail.getTestCase().getCaseType();
        String testType = testPlanDetail.getTestCase().getTestType();
        String name = testPlanDetail.getTestCase().getName();


        String appEnvId = testPlanTestData.getAppEnv();
        AppEnv appEnv = appEnvService.findAppEnv(appEnvId);

        AppTestConfig appTestConfig = new AppTestConfig();
        appTestConfig.setPlatformName(appEnv.getPlatformName());
        appTestConfig.setAppiumSever(appEnv.getAppiumSever());
        appTestConfig.setAppPackage(appEnv.getAppPackage());
        appTestConfig.setAppActivity(appEnv.getAppActivity());
        appTestConfig.setDeviceName(appEnv.getDeviceName());

        AppSceneTestRequest appSceneTestRequest = new AppSceneTestRequest();
        appSceneTestRequest.setAppTestConfig(appTestConfig);
        appSceneTestRequest.setExeType("testPlanTest");

        //执行
        AppSceneTestResponse appSceneTestResponse = appSceneTestDispatchService.execute(appSceneTestRequest);

        //保存
        AppSceneInstance appSceneInstance = appSceneTestResponse.getAppSceneInstance();
        String appSceneInstanceId = appSceneInstanceService.saveAppSceneInstanceToSql(appSceneInstance, appSceneTestResponse);

        //测试计划历史 与 绑定用例的历史 公共历史表
        TestPlanCaseInstanceBind testPlanCaseInstanceBind = new TestPlanCaseInstanceBind();
        testPlanCaseInstanceBind.setCaseInstanceId(appSceneInstanceId);
        testPlanCaseInstanceBind.setTestPlanInstanceId(testPlanInstanceId);
        testPlanCaseInstanceBind.setName(name);
        testPlanCaseInstanceBind.setCaseType(caseType);
        testPlanCaseInstanceBind.setTestType(testType);
        testPlanCaseInstanceBind.setResult(appSceneTestResponse.getAppSceneInstance().getResult());
        testPlanCaseInstanceBindService.createTestPlanCaseInstanceBind(testPlanCaseInstanceBind);

        return testPlanCaseInstanceBind;

    }

    
    public void exeAppPerform(TestPlanDetail testPlanDetail, TestPlanTestData testPlanTestData, String testPlanInstanceId) {
        //如果之前执行过，初始化。
        appPerfInstanceId=null;
        isFirst=true;
        testPlanDetailData=testPlanDetail;
        planInstanceId=testPlanInstanceId;
        appPerfStatus=1;

        AppPerfCase appPerfCase = new AppPerfCase();
        appPerfCase.setId(testPlanDetail.getTestCase().getId());
        
        appPerfTestRequest.setAppPerfCase(appPerfCase);

        String appEnvId = testPlanTestData.getAppEnv();
        AppEnv appEnv = appEnvService.findAppEnv(appEnvId);

        AppTestConfig appTestConfig = new AppTestConfig();
        appTestConfig.setPlatformName(appEnv.getPlatformName());
        appTestConfig.setAppiumSever(appEnv.getAppiumSever());
        appTestConfig.setAppPackage(appEnv.getAppPackage());
        appTestConfig.setAppActivity(appEnv.getAppActivity());
        appTestConfig.setDeviceName(appEnv.getDeviceName());

        appPerfTestRequest.setAppTestConfig(appTestConfig);
        appPerfTestRequest.setExeType("testPlanTest");


        appPerfTestDispatchService.execute(appPerfTestRequest);
    }



    public TestPlanCaseInstanceBind appPerfResult(){
        AppPerfTestResponse appPerfTestResponse = appPerfTestDispatchService.exeResult(appPerfTestRequest);

        //测试计划历史 与 绑定用例的历史 公共历史表
        TestPlanCaseInstanceBind testPlanCaseInstanceBind = new TestPlanCaseInstanceBind();

        //是否第一次创建
        if (isFirst) {
            String caseType = testPlanDetailData.getTestCase().getCaseType();
            String testType = testPlanDetailData.getTestCase().getTestType();
            String name = testPlanDetailData.getTestCase().getName();

            appPerfInstanceId = appPerfInstanceService.createAppPerfInstance(new AppPerfInstance());

            testPlanCaseInstanceBind.setCaseInstanceId(appPerfInstanceId);
            testPlanCaseInstanceBind.setTestPlanInstanceId(planInstanceId);
            testPlanCaseInstanceBind.setName(name);
            testPlanCaseInstanceBind.setCaseType(caseType);
            testPlanCaseInstanceBind.setTestType(testType);
            testPlanCaseInstanceBindService.createTestPlanCaseInstanceBind(testPlanCaseInstanceBind);

            isFirst=false;
        }else {
            AppPerfInstance appPerfInstance = appPerfTestResponse.getAppPerfInstance();
            appPerfInstance.setId(appPerfInstanceId);
            appPerfInstanceService.updateAppPerfInstance(appPerfInstance);
        }

        appPerfStatus=appPerfTestResponse.getStatus();

        return testPlanCaseInstanceBind;
    }





}
