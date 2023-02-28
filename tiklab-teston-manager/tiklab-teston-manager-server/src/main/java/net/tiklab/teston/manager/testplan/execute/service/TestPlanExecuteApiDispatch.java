package net.tiklab.teston.manager.testplan.execute.service;

import net.tiklab.teston.apix.http.perf.cases.model.ApiPerfCase;
import net.tiklab.teston.apix.http.perf.instance.model.ApiPerfInstance;
import net.tiklab.teston.apix.http.perf.execute.model.ApiPerfTestRequest;
import net.tiklab.teston.apix.http.perf.execute.model.ApiPerfTestResponse;
import net.tiklab.teston.apix.http.perf.instance.service.ApiPerfInstanceService;
import net.tiklab.teston.apix.http.perf.execute.service.ApiPerfTestDispatchService;
import net.tiklab.teston.apix.http.scene.cases.model.ApiSceneCase;
import net.tiklab.teston.apix.http.scene.instance.model.ApiSceneInstance;
import net.tiklab.teston.apix.http.scene.execute.model.ApiSceneTestRequest;
import net.tiklab.teston.apix.http.scene.execute.model.ApiSceneTestResponse;
import net.tiklab.teston.apix.http.scene.instance.service.ApiSceneInstanceService;
import net.tiklab.teston.apix.http.scene.execute.service.ApiSceneTestDispatchService;
import net.tiklab.teston.apix.http.unit.cases.model.ApiUnitCase;
import net.tiklab.teston.apix.http.unit.instance.model.ApiUnitInstance;
import net.tiklab.teston.apix.http.unit.execute.model.ApiUnitTestRequest;
import net.tiklab.teston.apix.http.unit.instance.service.ApiUnitInstanceService;
import net.tiklab.teston.apix.http.unit.execute.service.ApiUnitTestDispatchService;
import net.tiklab.teston.manager.testplan.cases.model.TestPlanCase;
import net.tiklab.teston.manager.testplan.instance.model.TestPlanCaseInstanceBind;
import net.tiklab.teston.manager.testplan.execute.model.TestPlanTestData;
import net.tiklab.teston.manager.testplan.instance.service.TestPlanCaseInstanceBindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestPlanExecuteApiDispatch {

    @Autowired
    ApiUnitTestDispatchService apiUnitTestDispatchService;

    @Autowired
    ApiUnitInstanceService apiUnitInstanceService;

    @Autowired
    ApiSceneInstanceService apiSceneInstanceService;

    @Autowired
    ApiSceneTestDispatchService apiSceneTestDispatchService;

    @Autowired
    ApiPerfTestDispatchService apiPerfTestDispatchService;

    @Autowired
    ApiPerfInstanceService apiPerfInstanceService;

    @Autowired
    TestPlanCaseInstanceBindService testPlanCaseInstanceBindService;


    public Integer apiPerfStatus = 0;

    private String apiPerfInstanceId;

    private String planInstanceId;

    private boolean isFirst=true;

    private TestPlanCase testPlanCaseData;

    private ApiPerfTestRequest apiPerfTestRequest = new ApiPerfTestRequest();


    /**
     * 执行接口单元用例
     * @param testPlanCase
     * @param testPlanTestData
     * @param testPlanInstanceId
     * @return
     */
    public TestPlanCaseInstanceBind exeApiUnit(TestPlanCase testPlanCase, TestPlanTestData testPlanTestData, String testPlanInstanceId){
        String caseType = testPlanCase.getTestCase().getCaseType();
        String testType = testPlanCase.getTestCase().getTestType();
        String name = testPlanCase.getTestCase().getName();

        //构造执行api 单元用例 所需的参数
        ApiUnitTestRequest apiUnitTestRequest = new ApiUnitTestRequest();

        ApiUnitCase apiUnitCase = new ApiUnitCase();
        apiUnitCase.setId(testPlanCase.getTestCase().getId());
        apiUnitTestRequest.setApiUnitCase(apiUnitCase);
        apiUnitTestRequest.setApiEnv(testPlanTestData.getApiEnv());
        //设置一个执行的类型，如果是testPlanTest 则 执行后，apiUnitTestDispatchService里不保存历史，返回数据，这边保存历史。
        apiUnitTestRequest.setExeType("testPlanTest");

        //执行
        ApiUnitInstance apiUnitInstance = apiUnitTestDispatchService.execute(apiUnitTestRequest);

        //保存历史
        String apiUnitInstanceId = apiUnitInstanceService.saveApiUnitInstanceToSql(apiUnitInstance);

        //测试计划历史 与 绑定用例的历史 公共历史表
        TestPlanCaseInstanceBind testPlanCaseInstanceBind = new TestPlanCaseInstanceBind();
        testPlanCaseInstanceBind.setCaseInstanceId(apiUnitInstanceId);
        testPlanCaseInstanceBind.setTestPlanInstanceId(testPlanInstanceId);
        testPlanCaseInstanceBind.setName(name);
        testPlanCaseInstanceBind.setCaseType(caseType);
        testPlanCaseInstanceBind.setTestType(testType);
        testPlanCaseInstanceBind.setResult(apiUnitInstance.getResult());
        testPlanCaseInstanceBindService.createTestPlanCaseInstanceBind(testPlanCaseInstanceBind);


        return testPlanCaseInstanceBind;
    }


    /**
     * 执行接口场景用例
     * @param testPlanCase
     * @param testPlanTestData
     * @param testPlanInstanceId
     * @return
     */
    public TestPlanCaseInstanceBind exeApiScene(TestPlanCase testPlanCase, TestPlanTestData testPlanTestData, String testPlanInstanceId){
        String caseType = testPlanCase.getTestCase().getCaseType();
        String testType = testPlanCase.getTestCase().getTestType();
        String name = testPlanCase.getTestCase().getName();

        ApiSceneTestRequest apiSceneTestRequest = new ApiSceneTestRequest();

        ApiSceneCase apiSceneCase = new ApiSceneCase();
        apiSceneCase.setId(testPlanCase.getTestCase().getId());

        apiSceneTestRequest.setApiSceneCase(apiSceneCase);
        apiSceneTestRequest.setApiEnv(testPlanTestData.getApiEnv());
        apiSceneTestRequest.setExeType("testPlanTest");


        //执行
        ApiSceneTestResponse apiSceneTestResponse = apiSceneTestDispatchService.execute(apiSceneTestRequest);

        ApiSceneInstance apiSceneInstance = apiSceneTestResponse.getApiSceneInstance();
        String apiSceneInstanceId = apiSceneInstanceService.saveApiSceneInstanceToSql(apiSceneInstance,apiSceneTestResponse);

        //测试计划历史 与 绑定用例的历史 公共历史表
        TestPlanCaseInstanceBind testPlanCaseInstanceBind = new TestPlanCaseInstanceBind();
        testPlanCaseInstanceBind.setCaseInstanceId(apiSceneInstanceId);
        testPlanCaseInstanceBind.setTestPlanInstanceId(testPlanInstanceId);
        testPlanCaseInstanceBind.setName(name);
        testPlanCaseInstanceBind.setCaseType(caseType);
        testPlanCaseInstanceBind.setTestType(testType);
        testPlanCaseInstanceBind.setResult(apiSceneTestResponse.getApiSceneInstance().getResult());
        testPlanCaseInstanceBindService.createTestPlanCaseInstanceBind(testPlanCaseInstanceBind);

        return testPlanCaseInstanceBind;
    }


    /**
     * 执行接口性能用例
     * @param testPlanCase
     * @param testPlanTestData
     * @param testPlanInstanceId
     * @return
     */
    public void exeApiPerform(TestPlanCase testPlanCase, TestPlanTestData testPlanTestData, String testPlanInstanceId){
        //如果之前执行过，初始化。
        apiPerfInstanceId=null;
        isFirst=true;
        testPlanCaseData = testPlanCase;
        planInstanceId=testPlanInstanceId;
        apiPerfStatus=1;

        ApiPerfCase apiPerfCase = new ApiPerfCase();
        apiPerfCase.setId(testPlanCase.getTestCase().getId());
        apiPerfTestRequest.setApiPerfCase(apiPerfCase);
        apiPerfTestRequest.setExeType("testPlanTest");
        apiPerfTestRequest.setApiEnv(testPlanTestData.getApiEnv());

        apiPerfTestDispatchService.execute(apiPerfTestRequest);

    }


    public TestPlanCaseInstanceBind apiPerfResult(){
        ApiPerfTestResponse apiPerfTestResponse = apiPerfTestDispatchService.exeResult(apiPerfTestRequest);

        //测试计划历史 与 绑定用例的历史 公共历史表
        TestPlanCaseInstanceBind testPlanCaseInstanceBind = new TestPlanCaseInstanceBind();

        //是否第一次创建
        if (isFirst) {
            String caseType = testPlanCaseData.getTestCase().getCaseType();
            String testType = testPlanCaseData.getTestCase().getTestType();
            String name = testPlanCaseData.getTestCase().getName();

            apiPerfInstanceId = apiPerfInstanceService.createApiPerfInstance(new ApiPerfInstance());

            testPlanCaseInstanceBind.setCaseInstanceId(apiPerfInstanceId);
            testPlanCaseInstanceBind.setTestPlanInstanceId(planInstanceId);
            testPlanCaseInstanceBind.setName(name);
            testPlanCaseInstanceBind.setCaseType(caseType);
            testPlanCaseInstanceBind.setTestType(testType);
            testPlanCaseInstanceBindService.createTestPlanCaseInstanceBind(testPlanCaseInstanceBind);

            isFirst=false;
        }else {
            ApiPerfInstance apiPerfInstance = apiPerfTestResponse.getApiPerfInstance();
            apiPerfInstance.setId(apiPerfInstanceId);
            apiPerfInstanceService.updateApiPerfInstance(apiPerfInstance);
        }

        apiPerfStatus=apiPerfTestResponse.getStatus();

        return testPlanCaseInstanceBind;
    }


}