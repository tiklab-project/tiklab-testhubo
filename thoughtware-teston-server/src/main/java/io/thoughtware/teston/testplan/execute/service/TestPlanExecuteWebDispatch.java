package io.thoughtware.teston.testplan.execute.service;

import io.thoughtware.teston.test.web.perf.execute.model.WebPerfTestRequest;
import io.thoughtware.teston.test.web.perf.execute.model.WebPerfTestResponse;
import io.thoughtware.teston.test.web.perf.cases.model.WebPerfCase;
import io.thoughtware.teston.test.web.perf.execute.service.WebPerfTestDispatchService;
import io.thoughtware.teston.test.web.perf.instance.model.WebPerfInstance;
import io.thoughtware.teston.test.web.perf.instance.service.WebPerfInstanceService;
import io.thoughtware.teston.test.web.scene.execute.model.WebSceneTestResponse;
import io.thoughtware.teston.test.web.scene.instance.model.WebSceneInstance;
import io.thoughtware.teston.testplan.cases.model.PlanCase;
import io.thoughtware.teston.testplan.cases.model.TestPlan;
import io.thoughtware.teston.testplan.execute.model.TestPlanTestData;
import io.thoughtware.teston.testplan.instance.model.TestPlanCaseInstanceBind;
import io.thoughtware.teston.testplan.instance.service.TestPlanCaseInstanceBindService;
import io.thoughtware.teston.testplan.cases.model.TestPlanCase;
import io.thoughtware.teston.test.web.scene.execute.model.WebSceneTestRequest;
import io.thoughtware.teston.test.web.scene.instance.service.WebSceneInstanceService;
import io.thoughtware.teston.test.web.scene.execute.service.WebSceneTestDispatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 测试计划中web测试调度
 */
@Component
public class TestPlanExecuteWebDispatch {

    @Autowired
    WebSceneTestDispatchService webSceneTestDispatchService;

    @Autowired
    WebSceneInstanceService webSceneInstanceService;

    @Autowired
    WebPerfTestDispatchService webPerfTestDispatchService;

    @Autowired
    WebPerfInstanceService webPerfInstanceService;

    @Autowired
    TestPlanCaseInstanceBindService testPlanCaseInstanceBindService;


    public Integer webSceneStatus = 0;



    private String planInstanceId;

    private PlanCase testPlanCaseData;

    private String caseId;



    /**
     * 执行web场景
     * @param testPlanCase
     * @param testPlanTestData
     * @param testPlanInstanceId
     * @return
     */
    public void exeWebScene(PlanCase testPlanCase, TestPlanTestData testPlanTestData, String testPlanInstanceId){
        testPlanCaseData = testPlanCase;
        planInstanceId=testPlanInstanceId;
        webSceneStatus=1;
        caseId=testPlanCase.getId();

        WebSceneTestRequest webSceneTestRequest= new WebSceneTestRequest();
        webSceneTestRequest.setRepositoryId(testPlanTestData.getRepositoryId());
        webSceneTestRequest.setWebSceneId(caseId);


        //执行
        webSceneTestDispatchService.execute(webSceneTestRequest);
    }


    public TestPlanCaseInstanceBind webSceneResult(){
        WebSceneTestRequest webSceneTestRequest=new WebSceneTestRequest();
        webSceneTestRequest.setWebSceneId(caseId);
        WebSceneTestResponse webSceneTestResponse = webSceneTestDispatchService.result(webSceneTestRequest);

        webSceneStatus=webSceneTestDispatchService.status();

        TestPlanCaseInstanceBind testPlanCaseInstanceBind = new TestPlanCaseInstanceBind();
        String caseType = testPlanCaseData.getCaseType();
        String testType = testPlanCaseData.getTestType();
        String name = testPlanCaseData.getName();
        testPlanCaseInstanceBind.setTestPlanInstanceId(planInstanceId);
        testPlanCaseInstanceBind.setName(name);
        testPlanCaseInstanceBind.setCaseType(caseType);
        testPlanCaseInstanceBind.setTestType(testType);
        testPlanCaseInstanceBind.setResult(webSceneTestDispatchService.status());
        testPlanCaseInstanceBind.setCaseId(caseId);

        if(webSceneStatus==0){
            String webSceneInstanceId = webSceneInstanceService.createWebSceneInstance(webSceneTestResponse.getWebSceneInstance());
            testPlanCaseInstanceBind.setCaseInstanceId(webSceneInstanceId);
            testPlanCaseInstanceBind.setResult(webSceneTestResponse.getWebSceneInstance().getResult());
            testPlanCaseInstanceBindService.createTestPlanCaseInstanceBind(testPlanCaseInstanceBind);
            webSceneInstanceService.createStepInstance(webSceneTestResponse.getStepCommonInstanceList(),webSceneInstanceId);
        }

        return testPlanCaseInstanceBind;
    }






}
