package io.tiklab.teston.testplan.execute.controller;

import io.tiklab.teston.testplan.execute.model.TestPlanTestData;
import io.tiklab.teston.testplan.execute.model.TestPlanTestResponse;
import io.tiklab.teston.testplan.execute.service.TestPlanExecuteDispatchService;
import io.tiklab.core.Result;
import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.annotation.ApiMethod;
import io.tiklab.postin.annotation.ApiParam;
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
 * 测试计划测试 控制器
 */
@RestController
@RequestMapping("/testPlanTestDispatch")
@Api(name = "TestPlanTestDispatchController",desc = "测试计划测试调度")
public class TestPlanTestDispatchController {

    private static Logger logger = LoggerFactory.getLogger(TestPlanTestDispatchController.class);

    @Autowired
    TestPlanExecuteDispatchService testPlanExecuteDispatchService;

    @RequestMapping(path = "/execute", method = RequestMethod.POST)
    @ApiMethod(name = "execute", desc = "执行测试")
    @ApiParam(name = "testPlanTestData", desc = "执行需要传的参数", required = true)
    public Result<Void> execute(@RequestBody @Valid @NotNull TestPlanTestData testPlanTestData) {
         testPlanExecuteDispatchService.execute(testPlanTestData);

        return Result.ok();
    }

    @RequestMapping(path = "/exeResult", method = RequestMethod.POST)
    @ApiMethod(name = "exeResult", desc = "获取测试结果")
    public Result<TestPlanTestResponse> exeResult() {
        TestPlanTestResponse testPlanTestResponse = testPlanExecuteDispatchService.exeResult();
        return Result.ok(testPlanTestResponse);
    }


}
