package io.tiklab.teston.testplan.execute.service;

import io.tiklab.teston.test.web.perf.cases.model.WebPerfCase;
import io.tiklab.teston.test.web.perf.execute.model.WebPerfTestRequest;
import io.tiklab.teston.test.web.perf.execute.model.WebPerfTestResponse;
import io.tiklab.teston.test.web.perf.execute.service.WebPerfTestDispatchService;
import io.tiklab.teston.test.web.perf.instance.model.WebPerfInstance;
import io.tiklab.teston.test.web.perf.instance.service.WebPerfInstanceService;
import io.tiklab.teston.testplan.execute.model.TestPlanTestData;
import io.tiklab.teston.testplan.instance.model.TestPlanCaseInstanceBind;
import io.tiklab.teston.testplan.instance.service.TestPlanCaseInstanceBindService;
import io.tiklab.teston.testplan.cases.model.TestPlanCase;
import io.tiklab.teston.test.web.scene.instance.model.WebSceneInstance;
import io.tiklab.teston.test.web.scene.execute.model.WebSceneTestRequest;
import io.tiklab.teston.test.web.scene.execute.model.WebSceneTestResponse;
import io.tiklab.teston.test.web.scene.instance.service.WebSceneInstanceService;
import io.tiklab.teston.test.web.scene.execute.service.WebSceneTestDispatchService;
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


    public Integer webPerfStatus = 0;

    private String webPerfInstanceId;

    private String planInstanceId;

    private boolean isFirst=true;

    private TestPlanCase testPlanCaseData;

    private WebPerfTestRequest webPerfTestRequest = new WebPerfTestRequest();



    /**
     * 执行web场景
     * @param testPlanCase
     * @param testPlanTestData
     * @param testPlanInstanceId
     * @return
     */
    public TestPlanCaseInstanceBind exeWebScene(TestPlanCase testPlanCase, TestPlanTestData testPlanTestData, String testPlanInstanceId){
        String caseType = testPlanCase.getTestCase().getCaseType();
        String testType = testPlanCase.getTestCase().getTestType();
        String name = testPlanCase.getTestCase().getName();

        WebSceneTestRequest webSceneTestRequest = new WebSceneTestRequest();
        webSceneTestRequest.setWebSceneId(testPlanCase.getTestCase().getId());
        webSceneTestRequest.setExeType("testType");

        //执行
        webSceneTestDispatchService.execute(webSceneTestRequest);


        //todo webScene结构更改后续调整
        //保存
//        WebSceneInstance webSceneInstance = webSceneTestResponse.getWebSceneInstance();
//        String webSceneInstanceId = webSceneInstanceService.saveWebSceneInstanceToSql(webSceneInstance, webSceneTestResponse);
//
//
//        //测试计划历史 与 绑定用例的历史 公共历史表
//        TestPlanCaseInstanceBind testPlanCaseInstanceBind = new TestPlanCaseInstanceBind();
//        testPlanCaseInstanceBind.setCaseInstanceId(webSceneInstanceId);
//        testPlanCaseInstanceBind.setTestPlanInstanceId(testPlanInstanceId);
//        testPlanCaseInstanceBind.setName(name);
//        testPlanCaseInstanceBind.setCaseType(caseType);
//        testPlanCaseInstanceBind.setTestType(testType);
//        testPlanCaseInstanceBind.setResult(webSceneTestResponse.getWebSceneInstance().getResult());
//        testPlanCaseInstanceBindService.createTestPlanCaseInstanceBind(testPlanCaseInstanceBind);

//        return testPlanCaseInstanceBind;
        return null;
    }

    /**
     * 执行web性能
     * @param testPlanCase
     * @param testPlanTestData
     * @param testPlanInstanceId
     */
    public void exeWebPerform(TestPlanCase testPlanCase, TestPlanTestData testPlanTestData, String testPlanInstanceId){
        //如果之前执行过，初始化。
        webPerfInstanceId=null;
        isFirst=true;
        testPlanCaseData = testPlanCase;
        planInstanceId=testPlanInstanceId;
        webPerfStatus=1;


        WebPerfCase webPerfCase = new WebPerfCase();
        webPerfCase.setId(testPlanCase.getTestCase().getId());
        webPerfTestRequest.setWebPerfCase(webPerfCase);
        webPerfTestRequest.setExeType("testPlanTest");

        webPerfTestDispatchService.execute(webPerfTestRequest);

    }

    public TestPlanCaseInstanceBind webPerfResult(){
        WebPerfTestResponse webPerfTestResponse = webPerfTestDispatchService.exeResult(webPerfTestRequest);

        //测试计划历史 与 绑定用例的历史 公共历史表
        TestPlanCaseInstanceBind testPlanCaseInstanceBind = new TestPlanCaseInstanceBind();

        //是否第一次创建
        if (isFirst) {
            String caseType = testPlanCaseData.getTestCase().getCaseType();
            String testType = testPlanCaseData.getTestCase().getTestType();
            String name = testPlanCaseData.getTestCase().getName();

            webPerfInstanceId = webPerfInstanceService.createWebPerfInstance(new WebPerfInstance());

            testPlanCaseInstanceBind.setCaseInstanceId(webPerfInstanceId);
            testPlanCaseInstanceBind.setTestPlanInstanceId(planInstanceId);
            testPlanCaseInstanceBind.setName(name);
            testPlanCaseInstanceBind.setCaseType(caseType);
            testPlanCaseInstanceBind.setTestType(testType);
            testPlanCaseInstanceBindService.createTestPlanCaseInstanceBind(testPlanCaseInstanceBind);

            isFirst=false;
        }else {
            WebPerfInstance webPerfInstance = webPerfTestResponse.getWebPerfInstance();
            webPerfInstance.setId(webPerfInstanceId);
            webPerfInstanceService.updateWebPerfInstance(webPerfInstance);
        }

        webPerfStatus=webPerfTestResponse.getStatus();

        return testPlanCaseInstanceBind;
    }




}
