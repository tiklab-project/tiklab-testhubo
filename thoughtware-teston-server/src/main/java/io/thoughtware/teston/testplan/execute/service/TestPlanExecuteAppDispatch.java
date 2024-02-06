package io.thoughtware.teston.testplan.execute.service;

import io.thoughtware.teston.test.app.perf.cases.model.AppPerfCase;
import io.thoughtware.teston.test.app.perf.execute.mode.AppPerfTestRequest;
import io.thoughtware.teston.test.app.perf.execute.mode.AppPerfTestResponse;
import io.thoughtware.teston.test.app.perf.execute.service.AppPerfTestDispatchService;
import io.thoughtware.teston.test.app.perf.instance.mode.AppPerfInstance;
import io.thoughtware.teston.test.app.perf.instance.service.AppPerfInstanceService;
import io.thoughtware.teston.test.app.scene.execute.model.AppSceneTestRequest;
import io.thoughtware.teston.test.app.scene.execute.model.AppSceneTestResponse;
import io.thoughtware.teston.test.app.scene.execute.model.AppTestConfig;
import io.thoughtware.teston.test.app.scene.execute.service.AppSceneTestDispatchService;
import io.thoughtware.teston.test.app.scene.instance.service.AppSceneInstanceService;
import io.thoughtware.teston.testplan.cases.model.PlanCase;
import io.thoughtware.teston.testplan.execute.model.TestPlanTestData;
import io.thoughtware.teston.testplan.instance.model.TestPlanCaseInstanceBind;
import io.thoughtware.teston.testplan.instance.service.TestPlanCaseInstanceBindService;
import io.thoughtware.teston.support.environment.model.AppEnv;
import io.thoughtware.teston.support.environment.service.AppEnvService;
import io.thoughtware.teston.testplan.cases.model.TestPlanCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 测试计划中app的执行测试
 */
@Component
public class TestPlanExecuteAppDispatch {

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

    private TestPlanCase testPlanCaseData;

    private AppPerfTestRequest appPerfTestRequest = new AppPerfTestRequest();



    /**
     * 执行AppScene
     * @param testPlanCaseInstanceBind
     * @param testPlanTestData
     * @return
     */
    public void exeAppScene(TestPlanCaseInstanceBind testPlanCaseInstanceBind, TestPlanTestData testPlanTestData){
        String caseId = testPlanCaseInstanceBind.getCaseId();

        AppEnv appEnv = appEnvService.findAppEnv(testPlanTestData.getAppEnv());
        AppTestConfig appTestConfig = new AppTestConfig();
        appTestConfig.setPlatformName(appEnv.getPlatformName());
        appTestConfig.setAppiumSever(appEnv.getAppiumSever());
        appTestConfig.setAppPackage(appEnv.getAppPackage());
        appTestConfig.setAppActivity(appEnv.getAppActivity());
        appTestConfig.setDeviceName(appEnv.getDeviceName());

        AppSceneTestRequest appSceneTestRequest = new AppSceneTestRequest();
        appSceneTestRequest.setAppTestConfig(appTestConfig);
        appSceneTestRequest.setAppSceneId(caseId);

        appSceneTestDispatchService.execute(appSceneTestRequest);
    }


    public TestPlanCaseInstanceBind appSceneResult(TestPlanCaseInstanceBind testPlanCaseInstanceBind){
        String caseId = testPlanCaseInstanceBind.getCaseId();

        AppSceneTestRequest appSceneTestRequest = new AppSceneTestRequest();
        appSceneTestRequest.setAppSceneId(caseId);
        appSceneTestRequest.setExeType("plan");
        AppSceneTestResponse appSceneTestResponse = appSceneTestDispatchService.result(appSceneTestRequest);

        if(appSceneTestResponse.getAppSceneInstance()!=null){
            testPlanCaseInstanceBind.setResult(appSceneTestResponse.getAppSceneInstance().getResult());
        }else {
            testPlanCaseInstanceBind.setResult(0);
        }
        testPlanCaseInstanceBind.setCaseId(caseId);
        testPlanCaseInstanceBind.setStatus(appSceneTestResponse.getStatus());

        if(appSceneTestResponse.getAppSceneInstance()!=null&&appSceneTestResponse.getStatus()==0){
            String appSceneInstanceId = appSceneInstanceService.createAppSceneInstance(appSceneTestResponse.getAppSceneInstance());
            testPlanCaseInstanceBind.setCaseInstanceId(appSceneInstanceId);
            testPlanCaseInstanceBind.setResult(appSceneTestResponse.getAppSceneInstance().getResult());
            testPlanCaseInstanceBind.setStatus(0);
            testPlanCaseInstanceBindService.updateTestPlanCaseInstanceBind(testPlanCaseInstanceBind);
            appSceneInstanceService.createAppSceneStepInstance(appSceneTestResponse.getStepCommonInstanceList(),appSceneInstanceId);
        }

        return testPlanCaseInstanceBind;
    }








    
    public void exeAppPerform(TestPlanCase testPlanCase, TestPlanTestData testPlanTestData, String testPlanInstanceId) {
        //如果之前执行过，初始化。
        appPerfInstanceId=null;
        isFirst=true;
        testPlanCaseData = testPlanCase;
        planInstanceId=testPlanInstanceId;
        appPerfStatus=1;

        AppPerfCase appPerfCase = new AppPerfCase();
        appPerfCase.setId(testPlanCase.getTestCase().getId());
        
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
            String caseType = testPlanCaseData.getTestCase().getCaseType();
            String testType = testPlanCaseData.getTestCase().getTestType();
            String name = testPlanCaseData.getTestCase().getName();

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
