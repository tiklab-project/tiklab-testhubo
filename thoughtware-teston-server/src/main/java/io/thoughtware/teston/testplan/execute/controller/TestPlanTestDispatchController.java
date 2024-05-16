package io.thoughtware.teston.testplan.execute.controller;

import io.thoughtware.teston.testplan.execute.model.TestPlanTestData;
import io.thoughtware.teston.testplan.execute.model.TestPlanTestResponse;
import io.thoughtware.teston.testplan.execute.service.TestPlanExecuteDispatchService;
import io.thoughtware.core.Result;
import io.thoughtware.postin.annotation.Api;
import io.thoughtware.postin.annotation.ApiMethod;
import io.thoughtware.postin.annotation.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 *
 * @pi.protocol: http
 * @pi.groupName: 测试计划测试 控制器
 */
@RestController
@RequestMapping("/testPlanTestDispatch")
@Api(name = "TestPlanTestDispatchController",desc = "测试计划测试调度")
public class TestPlanTestDispatchController {

    private static Logger logger = LoggerFactory.getLogger(TestPlanTestDispatchController.class);

    @Autowired
    TestPlanExecuteDispatchService testPlanExecuteDispatchService;

    /**
     * @pi.name:execute
     * @pi.path:/testPlanTestDispatch/execute
     * @pi.methodType:post
     * @pi.request-type:json
     * @pi.param: model=TestPlanTestData
     */
    @RequestMapping(path = "/execute", method = RequestMethod.POST)
    @ApiMethod(name = "execute", desc = "执行测试")
    @ApiParam(name = "testPlanTestData", desc = "执行需要传的参数", required = true)
    public Result<String> execute(@RequestBody @Valid @NotNull TestPlanTestData testPlanTestData) {
        String instanceId = testPlanExecuteDispatchService.execute(testPlanTestData);

        return Result.ok(instanceId);
    }

    /**
     * @pi.name:exeResult
     * @pi.path:/testPlanTestDispatch/exeResult
     * @pi.methodType:post
     * @pi.request-type:none
     */
    @RequestMapping(path = "/exeResult", method = RequestMethod.POST)
    @ApiMethod(name = "exeResult", desc = "获取测试结果")
    public Result<TestPlanTestResponse> exeResult(@NotNull String testPlanId) {
        TestPlanTestResponse testPlanTestResponse = testPlanExecuteDispatchService.exeResult(testPlanId);
        return Result.ok(testPlanTestResponse);
    }


    @RequestMapping(path = "/cleanUpExecutionData", method = RequestMethod.POST)
    @ApiMethod(name = "cleanUpExecutionData", desc = "清楚测试数据")
    public Result<Void> cleanUpExecutionData(@NotNull String testPlanId) {
        testPlanExecuteDispatchService.cleanUpExecutionData(testPlanId);
        return Result.ok();
    }

}
