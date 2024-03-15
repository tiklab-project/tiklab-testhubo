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
     * @param testPlanCaseInstanceBind
     * @param testPlanTestData
     * @return
     */
    public TestPlanCaseInstanceBind exeApiUnit(TestPlanCaseInstanceBind testPlanCaseInstanceBind, TestPlanTestData testPlanTestData){
        String caseId = testPlanCaseInstanceBind.getCaseId();

        //构造执行api 单元用例 所需的参数
        ApiUnitTestRequest apiUnitTestRequest = new ApiUnitTestRequest();

        ApiUnitCase apiUnitCase = new ApiUnitCase();
        apiUnitCase.setId(caseId);
        apiUnitTestRequest.setApiUnitCase(apiUnitCase);
        apiUnitTestRequest.setApiEnv(testPlanTestData.getApiEnv());

        //执行
        ApiUnitInstance apiUnitInstance = apiUnitExecuteDispatchService.executeStart(apiUnitTestRequest);

        //保存历史
        String apiUnitInstanceId = apiUnitInstanceService.saveApiUnitInstanceToSql(apiUnitInstance);

        //测试计划历史 与 绑定用例的历史 公共历史表
        testPlanCaseInstanceBind.setCaseInstanceId(apiUnitInstanceId);
        testPlanCaseInstanceBind.setResult(apiUnitInstance.getResult());
        testPlanCaseInstanceBind.setStatus(0);
        testPlanCaseInstanceBindService.updateTestPlanCaseInstanceBind(testPlanCaseInstanceBind);

        return testPlanCaseInstanceBind;
    }


    /**
     * 执行接口场景用例
     * @param testPlanTestData
     * @return
     */
    public TestPlanCaseInstanceBind exeApiScene(TestPlanCaseInstanceBind testPlanCaseInstanceBind, TestPlanTestData testPlanTestData){
        ApiSceneTestRequest apiSceneTestRequest = new ApiSceneTestRequest();

        ApiSceneCase apiSceneCase = new ApiSceneCase();
        String caseId = testPlanCaseInstanceBind.getCaseId();
        apiSceneCase.setId(caseId);

        apiSceneTestRequest.setApiSceneCase(apiSceneCase);
        apiSceneTestRequest.setApiEnv(testPlanTestData.getApiEnv());

        //执行
        ApiSceneTestResponse apiSceneTestResponse = apiSceneExecuteDispatchService.executeStart(apiSceneTestRequest);
        ApiSceneInstance apiSceneInstance = apiSceneTestResponse.getApiSceneInstance();

        //保存历史
        String apiSceneInstanceId = apiSceneInstanceService.createApiSceneInstance(apiSceneInstance);
        //步骤历史
        apiSceneInstanceService.createWebStepInstance(apiSceneTestResponse.getStepCommonInstanceList(),apiSceneInstanceId);

        //测试计划历史 与 绑定用例的历史 公共历史
        testPlanCaseInstanceBind.setCaseInstanceId(apiSceneInstanceId);
        testPlanCaseInstanceBind.setResult(apiSceneTestResponse.getApiSceneInstance().getResult());
        testPlanCaseInstanceBind.setStatus(0);
        testPlanCaseInstanceBindService.updateTestPlanCaseInstanceBind(testPlanCaseInstanceBind);

        return testPlanCaseInstanceBind;
    }


    /**
     * 执行接口性能用例
     * @param testPlanCaseInstanceBind
     * @param testPlanTestData
     * @return
     */
    public void exeApiPerform(TestPlanCaseInstanceBind testPlanCaseInstanceBind, TestPlanTestData testPlanTestData){
        ApiPerfTestRequest apiPerfTestRequest = new ApiPerfTestRequest();
        ApiPerfCase apiPerfCase = new ApiPerfCase();
        apiPerfCase.setId(testPlanCaseInstanceBind.getCaseId());
        apiPerfTestRequest.setApiPerfCase(apiPerfCase);
        apiPerfTestRequest.setApiEnv(testPlanTestData.getApiEnv());

        apiPerfExecuteDispatchService.executeStart(apiPerfTestRequest);

    }


    public TestPlanCaseInstanceBind apiPerfResult(TestPlanCaseInstanceBind testPlanCaseInstanceBind){
        String apiPerfId = testPlanCaseInstanceBind.getCaseId();

        ApiPerfTestRequest apiPerfTestRequest = new ApiPerfTestRequest();
        ApiPerfCase apiPerfCase = new ApiPerfCase();
        apiPerfCase.setId(apiPerfId);
        apiPerfTestRequest.setApiPerfCase(apiPerfCase);
        ApiPerfTestResponse apiPerfTestResponse = apiPerfExecuteDispatchService.getResult(apiPerfTestRequest);

        if(apiPerfTestResponse!=null&&apiPerfTestResponse.getApiPerfInstance()!=null){
            if(apiPerfTestResponse.getStatus()==0) {
                String apiPerfInstanceId = apiPerfInstanceService.createApiPerfInstance(apiPerfTestResponse.getApiPerfInstance());
                testPlanCaseInstanceBind.setCaseInstanceId(apiPerfInstanceId);
                testPlanCaseInstanceBind.setResult(1);
                testPlanCaseInstanceBind.setStatus(0);
                testPlanCaseInstanceBindService.updateTestPlanCaseInstanceBind(testPlanCaseInstanceBind);
            }

            testPlanCaseInstanceBind.setStatus(apiPerfTestResponse.getStatus());
        }else {
            testPlanCaseInstanceBind.setStatus(0);
        }

        return testPlanCaseInstanceBind;
    }
}
