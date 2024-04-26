package io.thoughtware.teston.testplan.execute.service;

import io.thoughtware.teston.common.MagicValue;
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

import java.util.Objects;

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
    

    /**
     * 执行AppScene
     * @param testPlanCaseInstanceBind
     * @param testPlanTestData
     * @return
     */
    public void exeAppScene(TestPlanCaseInstanceBind testPlanCaseInstanceBind, TestPlanTestData testPlanTestData){
        String caseId = testPlanCaseInstanceBind.getCaseId();

        AppSceneTestRequest appSceneTestRequest = new AppSceneTestRequest();
        appSceneTestRequest.setAppEnvId(testPlanTestData.getAppEnv());
        appSceneTestRequest.setAppSceneId(caseId);

        appSceneTestDispatchService.executeStart(appSceneTestRequest);
    }


    public TestPlanCaseInstanceBind appSceneResult(TestPlanCaseInstanceBind testPlanCaseInstanceBind){
        String caseId = testPlanCaseInstanceBind.getCaseId();

        AppSceneTestRequest appSceneTestRequest = new AppSceneTestRequest();
        appSceneTestRequest.setAppSceneId(caseId);

        try {
            AppSceneTestResponse appSceneTestResponse = appSceneTestDispatchService.getResult(appSceneTestRequest);

            if(appSceneTestResponse.getAppSceneInstance()!=null){
                String status = appSceneTestResponse.getAppSceneInstance().getStatus();

                if(Objects.equals(status, MagicValue.TEST_STATUS_START)){
                    testPlanCaseInstanceBind.setStatus(1);
                }else {
                    testPlanCaseInstanceBind.setStatus(0);
                }

                if(Objects.equals(status, MagicValue.TEST_STATUS_SUCCESS)){
                    testPlanCaseInstanceBind.setResult(1);
                }
                if(Objects.equals(status, MagicValue.TEST_STATUS_FAIL)){
                    testPlanCaseInstanceBind.setResult(0);
                }

                if(!Objects.equals(status, MagicValue.TEST_STATUS_START)){
                    String appSceneInstanceId = appSceneInstanceService.createAppSceneInstance(appSceneTestResponse.getAppSceneInstance());
                    testPlanCaseInstanceBind.setCaseInstanceId(appSceneInstanceId);
                    testPlanCaseInstanceBindService.updateTestPlanCaseInstanceBind(testPlanCaseInstanceBind);
                    appSceneInstanceService.createAppSceneStepInstance(appSceneTestResponse.getStepCommonInstanceList(),appSceneInstanceId);
                }
            }else {
                testPlanCaseInstanceBind.setResult(0);
            }

        }catch (Exception e){
            testPlanCaseInstanceBind.setStatus(0);
        }

        return testPlanCaseInstanceBind;
    }

    /**
     * 清楚数据
     * @param appSceneId
     */
    public void cleanUpData(String appSceneId){
        appSceneTestDispatchService.cleanUpData(appSceneId);
    }


}
