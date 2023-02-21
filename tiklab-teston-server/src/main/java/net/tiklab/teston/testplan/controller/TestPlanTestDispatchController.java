package net.tiklab.teston.testplan.controller;

import net.tiklab.core.Result;
import net.tiklab.postin.annotation.Api;
import net.tiklab.postin.annotation.ApiMethod;
import net.tiklab.postin.annotation.ApiParam;
import net.tiklab.teston.testplan.model.TestPlanInstance;
import net.tiklab.teston.testplan.model.TestPlanTestData;
import net.tiklab.teston.testplan.model.TestPlanTestResponse;
import net.tiklab.teston.testplan.service.TestPlanTestDispatchService;
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
 * TestPlanTestDispatchController
 */
@RestController
@RequestMapping("/testPlanTestDispatch")
@Api(name = "TestPlanTestDispatchController",desc = "api性能测试执行调度")
public class TestPlanTestDispatchController {

    private static Logger logger = LoggerFactory.getLogger(TestPlanTestDispatchController.class);

    @Autowired
    TestPlanTestDispatchService testPlanTestDispatchService;

    @RequestMapping(path = "/execute", method = RequestMethod.POST)
    @ApiMethod(name = "execute", desc = "执行性能测试")
    @ApiParam(name = "performCaseExec", desc = "执行需要传的参数", required = true)
    public Result<Void> execute(@RequestBody @Valid @NotNull TestPlanTestData testPlanTestData) {
         testPlanTestDispatchService.execute(testPlanTestData);

        return Result.ok();
    }

    @RequestMapping(path = "/exeResult", method = RequestMethod.POST)
    @ApiMethod(name = "exeResult", desc = "获取性能测试结果")
    @ApiParam(name = "testPlanTestRequest", desc = "执行需要传的参数", required = true)
    public Result<TestPlanTestResponse> exeResult() {
        TestPlanTestResponse testPlanTestResponse = testPlanTestDispatchService.exeResult();
        return Result.ok(testPlanTestResponse);
    }


}
