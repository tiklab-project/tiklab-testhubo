package io.thoughtware.teston.testplan.execute.service;

import io.thoughtware.teston.instance.service.InstanceService;
import io.thoughtware.teston.test.apix.http.perf.cases.model.ApiPerfCase;
import io.thoughtware.teston.testplan.cases.model.PlanCase;
import io.thoughtware.teston.testplan.execute.model.TestPlanTestData;
import io.thoughtware.teston.testplan.instance.model.TestPlanCaseInstanceBind;
import io.thoughtware.teston.testplan.instance.service.TestPlanCaseInstanceBindService;
import io.thoughtware.teston.test.apix.http.perf.instance.model.ApiPerfInstance;
import io.thoughtware.teston.test.apix.http.perf.execute.model.ApiPerfTestRequest;
import io.thoughtware.teston.test.apix.http.perf.execute.model.ApiPerfTestResponse;
import io.thoughtware.teston.test.apix.http.perf.instance.service.ApiPerfInstanceService;
import io.thoughtware.teston.test.apix.http.perf.execute.service.ApiPerfExecuteDispatchService;
import io.thoughtware.teston.test.apix.http.scene.cases.model.ApiSceneCase;
import io.thoughtware.teston.test.apix.http.scene.instance.model.ApiSceneInstance;
import io.thoughtware.teston.test.apix.http.scene.execute.model.ApiSceneTestRequest;
import io.thoughtware.teston.test.apix.http.scene.execute.model.ApiSceneTestResponse;
import io.thoughtware.teston.test.apix.http.scene.instance.service.ApiSceneInstanceService;
import io.thoughtware.teston.test.apix.http.scene.execute.service.ApiSceneExecuteDispatchService;
import io.thoughtware.teston.test.apix.http.unit.cases.model.ApiUnitCase;
import io.thoughtware.teston.test.apix.http.unit.instance.model.ApiUnitInstance;
import io.thoughtware.teston.test.apix.http.unit.execute.model.ApiUnitTestRequest;
import io.thoughtware.teston.test.apix.http.unit.instance.service.ApiUnitInstanceService;
import io.thoughtware.teston.test.apix.http.unit.execute.service.ApiUnitExecuteDispatchService;
import io.thoughtware.teston.testplan.cases.model.TestPlanCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 测试计划中接口的执行测试
 */
@Component
public class TestPlanExecuteApiDispatch {

    @Autowired
    ApiUnitExecuteDispatchService apiUnitExecuteDispatchService;

    @Autowired
    ApiUnitInstanceService apiUnitInstanceService;

    @Autowired
    ApiSceneInstanceService apiSceneInstanceService;

    @Autowired
    ApiSceneExecuteDispatchService apiSceneExecuteDispatchService;

    @Autowired
    ApiPerfExecuteDispatchService apiPerfExecuteDispatchService;

    @Autowired
    ApiPerfInstanceService apiPerfInstanceService;

    @Autowired
    TestPlanCaseInstanceBindService testPlanCaseInstanceBindService;


    /**
     * 执行接口单元用例
     * @param testPlanCase
     * @param testPlanTestData
     * @param testPlanInstanceId
     * @return
     */
    public TestPlanCaseInstanceBind exeApiUnit(PlanCase testPlanCase, TestPlanTestData testPlanTestData, String testPlanInstanceId){
        String caseType = testPlanCase.getCaseType();
        String testType = testPlanCase.getTestType();
        String name = testPlanCase.getName();

        //构造执行api 单元用例 所需的参数
        ApiUnitTestRequest apiUnitTestRequest = new ApiUnitTestRequest();

        ApiUnitCase apiUnitCase = new ApiUnitCase();
        apiUnitCase.setId(testPlanCase.getId());
        apiUnitTestRequest.setApiUnitCase(apiUnitCase);
        apiUnitTestRequest.setApiEnv(testPlanTestData.getApiEnv());
        //设置一个执行的类型，如果是testPlanTest 则 执行后，apiUnitTestDispatchService里不保存历史，返回数据，这边保存历史。
        apiUnitTestRequest.setExeType("testPlanTest");

        //执行
        ApiUnitInstance apiUnitInstance = apiUnitExecuteDispatchService.execute(apiUnitTestRequest);

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
    public TestPlanCaseInstanceBind exeApiScene(PlanCase testPlanCase, TestPlanTestData testPlanTestData, String testPlanInstanceId){
        String caseType = testPlanCase.getCaseType();
        String testType = testPlanCase.getTestType();
        String name = testPlanCase.getName();

        ApiSceneTestRequest apiSceneTestRequest = new ApiSceneTestRequest();

        ApiSceneCase apiSceneCase = new ApiSceneCase();
        apiSceneCase.setId(testPlanCase.getId());

        apiSceneTestRequest.setApiSceneCase(apiSceneCase);
        apiSceneTestRequest.setApiEnv(testPlanTestData.getApiEnv());
        apiSceneTestRequest.setExeType("testPlanTest");


        //执行
        ApiSceneTestResponse apiSceneTestResponse = apiSceneExecuteDispatchService.execute(apiSceneTestRequest);
        ApiSceneInstance apiSceneInstance = apiSceneTestResponse.getApiSceneInstance();

        //保存历史
        String apiSceneInstanceId = apiSceneInstanceService.createApiSceneInstance(apiSceneInstance);
        //步骤历史
        apiSceneInstanceService.createWebStepInstance(apiSceneTestResponse.getStepCommonInstanceList(),apiSceneInstanceId);

        //测试计划历史 与 绑定用例的历史 公共历史
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
     * @return
     */
    public void exeApiPerform(PlanCase testPlanCase, TestPlanTestData testPlanTestData){
        //如果之前执行过，初始化。

        ApiPerfTestRequest apiPerfTestRequest = new ApiPerfTestRequest();
        ApiPerfCase apiPerfCase = new ApiPerfCase();
        apiPerfCase.setId(testPlanCase.getId());
        apiPerfTestRequest.setApiPerfCase(apiPerfCase);
        apiPerfTestRequest.setExeType("testPlanTest");
        apiPerfTestRequest.setApiEnv(testPlanTestData.getApiEnv());

        apiPerfExecuteDispatchService.execute(apiPerfTestRequest);

    }


    public TestPlanCaseInstanceBind apiPerfResult(PlanCase testPlanCase, String testPlanInstanceId){
        String apiPerfId = testPlanCase.getId();

        ApiPerfTestRequest apiPerfTestRequest = new ApiPerfTestRequest();
        ApiPerfCase apiPerfCase = new ApiPerfCase();
        apiPerfCase.setId(apiPerfId);
        apiPerfTestRequest.setApiPerfCase(apiPerfCase);
        ApiPerfTestResponse apiPerfTestResponse = apiPerfExecuteDispatchService.result(apiPerfTestRequest);

        //测试计划历史 与 绑定用例的历史 公共历史表
        TestPlanCaseInstanceBind testPlanCaseInstanceBind = new TestPlanCaseInstanceBind();
        String caseType = testPlanCase.getCaseType();
        String testType = testPlanCase.getTestType();
        String name = testPlanCase.getName();

        testPlanCaseInstanceBind.setTestPlanInstanceId(testPlanInstanceId);
        testPlanCaseInstanceBind.setName(name);
        testPlanCaseInstanceBind.setCaseType(caseType);
        testPlanCaseInstanceBind.setTestType(testType);
        testPlanCaseInstanceBind.setCaseId(apiPerfId);
        if(apiPerfTestResponse!=null){
            if(apiPerfTestResponse.getStatus()==0) {
                String apiPerfInstanceId = apiPerfInstanceService.createApiPerfInstance(apiPerfTestResponse.getApiPerfInstance());
                testPlanCaseInstanceBind.setCaseInstanceId(apiPerfInstanceId);
                testPlanCaseInstanceBind.setResult(1);
                testPlanCaseInstanceBindService.createTestPlanCaseInstanceBind(testPlanCaseInstanceBind);
            }

            testPlanCaseInstanceBind.setStatus(apiPerfTestResponse.getStatus());
        }else {
            testPlanCaseInstanceBind.setStatus(0);
        }

        return testPlanCaseInstanceBind;
    }
}
