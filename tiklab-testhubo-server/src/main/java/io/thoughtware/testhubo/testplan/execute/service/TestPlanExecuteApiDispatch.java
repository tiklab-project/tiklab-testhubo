package io.thoughtware.testhubo.testplan.execute.service;

import io.thoughtware.testhubo.testplan.execute.model.TestPlanTestData;
import io.thoughtware.testhubo.testplan.instance.model.TestPlanCaseInstanceBind;
import io.thoughtware.testhubo.testplan.instance.service.TestPlanCaseInstanceBindService;
import io.thoughtware.testhubo.test.apix.http.scene.cases.model.ApiSceneCase;
import io.thoughtware.testhubo.test.apix.http.scene.instance.model.ApiSceneInstance;
import io.thoughtware.testhubo.test.apix.http.scene.execute.model.ApiSceneTestRequest;
import io.thoughtware.testhubo.test.apix.http.scene.execute.model.ApiSceneTestResponse;
import io.thoughtware.testhubo.test.apix.http.scene.instance.service.ApiSceneInstanceService;
import io.thoughtware.testhubo.test.apix.http.scene.execute.service.ApiSceneExecuteDispatchService;
import io.thoughtware.testhubo.test.apix.http.unit.cases.model.ApiUnitCase;
import io.thoughtware.testhubo.test.apix.http.unit.instance.model.ApiUnitInstance;
import io.thoughtware.testhubo.test.apix.http.unit.execute.model.ApiUnitTestRequest;
import io.thoughtware.testhubo.test.apix.http.unit.instance.service.ApiUnitInstanceService;
import io.thoughtware.testhubo.test.apix.http.unit.execute.service.ApiUnitExecuteDispatchService;
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

        try {
            //执行
            ApiUnitInstance apiUnitInstance = apiUnitExecuteDispatchService.executeStart(apiUnitTestRequest);

            //保存历史
            String apiUnitInstanceId = apiUnitInstanceService.saveApiUnitInstanceToSql(apiUnitInstance);

            //测试计划历史 与 绑定用例的历史 公共历史表
            testPlanCaseInstanceBind.setCaseInstanceId(apiUnitInstanceId);
            testPlanCaseInstanceBind.setResult(apiUnitInstance.getResult());
            testPlanCaseInstanceBind.setStatus(0);
            testPlanCaseInstanceBind.setElapsedTime(apiUnitInstance.getElapsedTime().intValue());
            testPlanCaseInstanceBindService.updateTestPlanCaseInstanceBind(testPlanCaseInstanceBind);
        }catch (Exception e){
            testPlanCaseInstanceBind.setStatus(0);
        }

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

        try {
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
            testPlanCaseInstanceBind.setElapsedTime(apiSceneTestResponse.getApiSceneInstance().getElapsedTime());
            testPlanCaseInstanceBindService.updateTestPlanCaseInstanceBind(testPlanCaseInstanceBind);
        }catch (Exception e){
            testPlanCaseInstanceBind.setStatus(0);
        }

        return testPlanCaseInstanceBind;
    }


}
