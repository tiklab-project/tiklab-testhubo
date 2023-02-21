package net.tiklab.teston.testplan.service;

import net.tiklab.teston.apitest.http.perftest.model.ApiPerfCase;
import net.tiklab.teston.apitest.http.perftest.model.ApiPerfInstance;
import net.tiklab.teston.apitest.http.perftest.model.ApiPerfTestRequest;
import net.tiklab.teston.apitest.http.perftest.model.ApiPerfTestResponse;
import net.tiklab.teston.apitest.http.perftest.service.ApiPerfInstanceService;
import net.tiklab.teston.apitest.http.perftest.service.ApiPerfTestDispatchService;
import net.tiklab.teston.apitest.http.scenetest.model.ApiSceneCase;
import net.tiklab.teston.apitest.http.scenetest.model.ApiSceneInstance;
import net.tiklab.teston.apitest.http.scenetest.model.ApiSceneTestRequest;
import net.tiklab.teston.apitest.http.scenetest.model.ApiSceneTestResponse;
import net.tiklab.teston.apitest.http.scenetest.service.ApiSceneInstanceService;
import net.tiklab.teston.apitest.http.scenetest.service.ApiSceneTestDispatchService;
import net.tiklab.teston.apitest.http.unittest.model.ApiUnitCase;
import net.tiklab.teston.apitest.http.unittest.model.ApiUnitInstance;
import net.tiklab.teston.apitest.http.unittest.model.ApiUnitTestRequest;
import net.tiklab.teston.apitest.http.unittest.service.ApiUnitInstanceService;
import net.tiklab.teston.apitest.http.unittest.service.ApiUnitTestDispatchService;
import net.tiklab.teston.testplan.model.TestPlanCaseInstanceBind;
import net.tiklab.teston.testplan.model.TestPlanDetail;
import net.tiklab.teston.testplan.model.TestPlanTestData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestPlanTestDispatchApi {

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

    private TestPlanDetail testPlanDetailData;

    private ApiPerfTestRequest apiPerfTestRequest = new ApiPerfTestRequest();


    /**
     * 执行接口单元用例
     * @param testPlanDetail
     * @param testPlanTestData
     * @param testPlanInstanceId
     * @return
     */
    public TestPlanCaseInstanceBind exeApiUnit(TestPlanDetail testPlanDetail, TestPlanTestData testPlanTestData, String testPlanInstanceId){
        String caseType = testPlanDetail.getTestCase().getCaseType();
        String testType = testPlanDetail.getTestCase().getTestType();
        String name = testPlanDetail.getTestCase().getName();

        //构造执行api 单元用例 所需的参数
        ApiUnitTestRequest apiUnitTestRequest = new ApiUnitTestRequest();

        ApiUnitCase apiUnitCase = new ApiUnitCase();
        apiUnitCase.setId(testPlanDetail.getTestCase().getId());
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
     * @param testPlanDetail
     * @param testPlanTestData
     * @param testPlanInstanceId
     * @return
     */
    public TestPlanCaseInstanceBind exeApiScene(TestPlanDetail testPlanDetail, TestPlanTestData testPlanTestData, String testPlanInstanceId){
        String caseType = testPlanDetail.getTestCase().getCaseType();
        String testType = testPlanDetail.getTestCase().getTestType();
        String name = testPlanDetail.getTestCase().getName();

        ApiSceneTestRequest apiSceneTestRequest = new ApiSceneTestRequest();

        ApiSceneCase apiSceneCase = new ApiSceneCase();
        apiSceneCase.setId(testPlanDetail.getTestCase().getId());

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
     * @param testPlanDetail
     * @param testPlanTestData
     * @param testPlanInstanceId
     * @return
     */
    public void exeApiPerform(TestPlanDetail testPlanDetail, TestPlanTestData testPlanTestData, String testPlanInstanceId){
        //如果之前执行过，初始化。
        apiPerfInstanceId=null;
        isFirst=true;
        testPlanDetailData=testPlanDetail;
        planInstanceId=testPlanInstanceId;
        apiPerfStatus=1;

        ApiPerfCase apiPerfCase = new ApiPerfCase();
        apiPerfCase.setId(testPlanDetail.getTestCase().getId());
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
            String caseType = testPlanDetailData.getTestCase().getCaseType();
            String testType = testPlanDetailData.getTestCase().getTestType();
            String name = testPlanDetailData.getTestCase().getName();

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
