package net.tiklab.teston.testplan.service;

import net.tiklab.teston.testplan.model.TestPlanCaseInstanceBind;
import net.tiklab.teston.testplan.model.TestPlanDetail;
import net.tiklab.teston.testplan.model.TestPlanTestData;
import net.tiklab.teston.test.webperf.cases.model.WebPerfCase;
import net.tiklab.teston.test.webperf.instance.model.WebPerfInstance;
import net.tiklab.teston.test.webperf.execute.model.WebPerfTestRequest;
import net.tiklab.teston.test.webperf.execute.model.WebPerfTestResponse;
import net.tiklab.teston.test.webperf.instance.service.WebPerfInstanceService;
import net.tiklab.teston.test.webperf.execute.service.WebPerfTestDispatchService;
import net.tiklab.teston.test.webscene.instance.model.WebSceneInstance;
import net.tiklab.teston.test.webscene.execute.model.WebSceneTestRequest;
import net.tiklab.teston.test.webscene.execute.model.WebSceneTestResponse;
import net.tiklab.teston.test.webscene.instance.service.WebSceneInstanceService;
import net.tiklab.teston.test.webscene.execute.service.WebSceneTestDispatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestPlanTestDispatchWeb {

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

    private TestPlanDetail testPlanDetailData;

    private WebPerfTestRequest webPerfTestRequest = new WebPerfTestRequest();



    /**
     * 执行web场景
     * @param testPlanDetail
     * @param testPlanTestData
     * @param testPlanInstanceId
     * @return
     */
    public TestPlanCaseInstanceBind exeWebScene(TestPlanDetail testPlanDetail, TestPlanTestData testPlanTestData, String testPlanInstanceId){
        String caseType = testPlanDetail.getTestCase().getCaseType();
        String testType = testPlanDetail.getTestCase().getTestType();
        String name = testPlanDetail.getTestCase().getName();

        WebSceneTestRequest webSceneTestRequest = new WebSceneTestRequest();
        webSceneTestRequest.setWebSceneId(testPlanDetail.getTestCase().getId());
        webSceneTestRequest.setExeType("testType");

        //执行
        WebSceneTestResponse webSceneTestResponse = webSceneTestDispatchService.execute(webSceneTestRequest);

        //保存
        WebSceneInstance webSceneInstance = webSceneTestResponse.getWebSceneInstance();
        String webSceneInstanceId = webSceneInstanceService.saveWebSceneInstanceToSql(webSceneInstance, webSceneTestResponse);


        //测试计划历史 与 绑定用例的历史 公共历史表
        TestPlanCaseInstanceBind testPlanCaseInstanceBind = new TestPlanCaseInstanceBind();
        testPlanCaseInstanceBind.setCaseInstanceId(webSceneInstanceId);
        testPlanCaseInstanceBind.setTestPlanInstanceId(testPlanInstanceId);
        testPlanCaseInstanceBind.setName(name);
        testPlanCaseInstanceBind.setCaseType(caseType);
        testPlanCaseInstanceBind.setTestType(testType);
        testPlanCaseInstanceBind.setResult(webSceneTestResponse.getWebSceneInstance().getResult());
        testPlanCaseInstanceBindService.createTestPlanCaseInstanceBind(testPlanCaseInstanceBind);

        return testPlanCaseInstanceBind;
    }

    /**
     * 执行web性能
     * @param testPlanDetail
     * @param testPlanTestData
     * @param testPlanInstanceId
     */
    public void exeWebPerform(TestPlanDetail testPlanDetail, TestPlanTestData testPlanTestData, String testPlanInstanceId){
        //如果之前执行过，初始化。
        webPerfInstanceId=null;
        isFirst=true;
        testPlanDetailData=testPlanDetail;
        planInstanceId=testPlanInstanceId;
        webPerfStatus=1;


        WebPerfCase webPerfCase = new WebPerfCase();
        webPerfCase.setId(testPlanDetail.getTestCase().getId());
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
            String caseType = testPlanDetailData.getTestCase().getCaseType();
            String testType = testPlanDetailData.getTestCase().getTestType();
            String name = testPlanDetailData.getTestCase().getName();

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
