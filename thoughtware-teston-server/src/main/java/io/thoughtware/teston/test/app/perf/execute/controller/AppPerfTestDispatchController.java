package io.thoughtware.teston.test.app.perf.execute.controller;

import io.thoughtware.teston.test.app.perf.execute.mode.AppPerfTestRequest;
import io.thoughtware.teston.test.app.perf.execute.mode.AppPerfTestResponse;
import io.thoughtware.teston.test.app.perf.execute.service.AppPerfTestDispatchService;
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
 * app性能测试执行 控制器
 */
@RestController
@RequestMapping("/appPerfTestDispatch")
@Api(name = "AppPerfTestDispatchController",desc = "app性能测试执行")
public class AppPerfTestDispatchController {

    private static Logger logger = LoggerFactory.getLogger(AppPerfTestDispatchController.class);

    @Autowired
    AppPerfTestDispatchService appPerfTestDispatchService;

    @RequestMapping(path = "/execute", method = RequestMethod.POST)
    @ApiMethod(name = "execute", desc = "执行性能测试")
    @ApiParam(name = "appPerfTestRequest", desc = "执行需要传的参数", required = true)
    public Result<Void> execute(@RequestBody @Valid @NotNull AppPerfTestRequest appPerfTestRequest) {
        appPerfTestDispatchService.execute(appPerfTestRequest);

        return Result.ok();
    }

    @RequestMapping(path = "/exeResult", method = RequestMethod.POST)
    @ApiMethod(name = "exeResult", desc = "获取性能测试结果")
    @ApiParam(name = "appPerfTestRequest", desc = "执行需要传的参数", required = true)
    public Result<AppPerfTestResponse> exeResult(@RequestBody @Valid AppPerfTestRequest appPerfTestRequest) {
        AppPerfTestResponse appPerfTestResponse = appPerfTestDispatchService.exeResult(appPerfTestRequest);

        return Result.ok(appPerfTestResponse);
    }


}