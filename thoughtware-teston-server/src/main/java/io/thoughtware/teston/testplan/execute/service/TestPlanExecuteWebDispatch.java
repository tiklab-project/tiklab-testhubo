package io.thoughtware.teston.testplan.execute.service;

import io.thoughtware.teston.test.web.perf.execute.service.WebPerfTestDispatchService;
import io.thoughtware.teston.test.web.perf.instance.service.WebPerfInstanceService;
import io.thoughtware.teston.test.web.scene.execute.model.WebSceneTestResponse;
import io.thoughtware.teston.testplan.cases.model.PlanCase;
import io.thoughtware.teston.testplan.execute.model.TestPlanTestData;
import io.thoughtware.teston.testplan.instance.model.TestPlanCaseInstanceBind;
import io.thoughtware.teston.testplan.instance.service.TestPlanCaseInstanceBindService;
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


    /**
     * 执行web场景
     * @param testPlanCase
     * @param testPlanTestData
     * @return
     */
    public void exeWebScene(PlanCase testPlanCase, TestPlanTestData testPlanTestData){
        WebSceneTestRequest webSceneTestRequest= new WebSceneTestRequest();
        webSceneTestRequest.setRepositoryId(testPlanTestData.getRepositoryId());
        webSceneTestRequest.setWebSceneId(testPlanCase.getId());

        //执行
        webSceneTestDispatchService.execute(webSceneTestRequest);
    }


    public TestPlanCaseInstanceBind webSceneResult(PlanCase testPlanCase, String testPlanInstanceId){
        String caseId = testPlanCase.getId();
        String caseType = testPlanCase.getCaseType();
        String testType = testPlanCase.getTestType();
        String name = testPlanCase.getName();

        WebSceneTestRequest webSceneTestRequest=new WebSceneTestRequest();
        webSceneTestRequest.setWebSceneId(caseId);
        WebSceneTestResponse webSceneTestResponse = webSceneTestDispatchService.result(webSceneTestRequest);

        TestPlanCaseInstanceBind testPlanCaseInstanceBind = new TestPlanCaseInstanceBind();
        testPlanCaseInstanceBind.setTestPlanInstanceId(testPlanInstanceId);
        testPlanCaseInstanceBind.setName(name);
        testPlanCaseInstanceBind.setCaseType(caseType);
        testPlanCaseInstanceBind.setTestType(testType);
        if(webSceneTestResponse.getWebSceneInstance()!=null){
            testPlanCaseInstanceBind.setResult(webSceneTestResponse.getWebSceneInstance().getResult());
        }else {
            testPlanCaseInstanceBind.setResult(0);
        }

        testPlanCaseInstanceBind.setCaseId(caseId);
        testPlanCaseInstanceBind.setStatus(webSceneTestResponse.getStatus());

        if(webSceneTestResponse.getWebSceneInstance()!=null&&webSceneTestResponse.getStatus()==0){
            String webSceneInstanceId = webSceneInstanceService.createWebSceneInstance(webSceneTestResponse.getWebSceneInstance());
            testPlanCaseInstanceBind.setCaseInstanceId(webSceneInstanceId);
            testPlanCaseInstanceBind.setResult(webSceneTestResponse.getWebSceneInstance().getResult());
            testPlanCaseInstanceBindService.createTestPlanCaseInstanceBind(testPlanCaseInstanceBind);
            webSceneInstanceService.createStepInstance(webSceneTestResponse.getStepCommonInstanceList(),webSceneInstanceId);
        }

        return testPlanCaseInstanceBind;
    }






}
