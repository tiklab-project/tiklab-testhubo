package io.thoughtware.teston.testplan.execute.service;

import io.thoughtware.teston.common.MagicValue;
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
    TestPlanCaseInstanceBindService testPlanCaseInstanceBindService;


    /**
     * 执行web场景
     * @param testPlanCaseInstanceBind
     * @param testPlanTestData
     * @return
     */
    public void exeWebScene(TestPlanCaseInstanceBind testPlanCaseInstanceBind, TestPlanTestData testPlanTestData){
        WebSceneTestRequest webSceneTestRequest= new WebSceneTestRequest();
        webSceneTestRequest.setRepositoryId(testPlanTestData.getRepositoryId());
        webSceneTestRequest.setWebSceneId(testPlanCaseInstanceBind.getCaseId());

        //执行
        webSceneTestDispatchService.executeStart(webSceneTestRequest);
    }


    public TestPlanCaseInstanceBind webSceneResult(TestPlanCaseInstanceBind testPlanCaseInstanceBind){
        String caseId = testPlanCaseInstanceBind.getCaseId();

        WebSceneTestRequest webSceneTestRequest=new WebSceneTestRequest();
        webSceneTestRequest.setWebSceneId(caseId);
        WebSceneTestResponse webSceneTestResponse = webSceneTestDispatchService.getResult(webSceneTestRequest);

        if(webSceneTestResponse.getWebSceneInstance()!=null){
            testPlanCaseInstanceBind.setResult(webSceneTestResponse.getWebSceneInstance().getResult());
        }else {
            testPlanCaseInstanceBind.setResult(0);
        }

        testPlanCaseInstanceBind.setStatus(webSceneTestResponse.getStatus());

        if(webSceneTestResponse.getWebSceneInstance()!=null&&webSceneTestResponse.getStatus()==0){
            String webSceneInstanceId = webSceneInstanceService.createWebSceneInstance(webSceneTestResponse.getWebSceneInstance());
            testPlanCaseInstanceBind.setCaseInstanceId(webSceneInstanceId);
            testPlanCaseInstanceBind.setResult(webSceneTestResponse.getWebSceneInstance().getResult());
            testPlanCaseInstanceBindService.updateTestPlanCaseInstanceBind(testPlanCaseInstanceBind);
            testPlanCaseInstanceBind.setStatus(0);
            webSceneInstanceService.createStepInstance(webSceneTestResponse.getStepCommonInstanceList(),webSceneInstanceId);
        }

        return testPlanCaseInstanceBind;
    }






}
