package io.thoughtware.teston.testplan.execute.service;

import io.thoughtware.teston.common.MagicValue;
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

import java.util.Objects;

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

        try {
            WebSceneTestResponse webSceneTestResponse = webSceneTestDispatchService.getResult(webSceneTestRequest);

            if(webSceneTestResponse.getWebSceneInstance()!=null){
                String status = webSceneTestResponse.getWebSceneInstance().getStatus();

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
                    String webSceneInstanceId = webSceneInstanceService.createWebSceneInstance(webSceneTestResponse.getWebSceneInstance());
                    testPlanCaseInstanceBind.setCaseInstanceId(webSceneInstanceId);
                    testPlanCaseInstanceBindService.updateTestPlanCaseInstanceBind(testPlanCaseInstanceBind);
                    webSceneInstanceService.createStepInstance(webSceneTestResponse.getStepCommonInstanceList(),webSceneInstanceId);
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
     * @param webSceneId
     */
    public void cleanUpData(String webSceneId){
        webSceneTestDispatchService.cleanUpData(webSceneId);
    }






}
