package net.tiklab.teston.app.perf.execute.controller;

import net.tiklab.core.Result;
import net.tiklab.postin.annotation.Api;
import net.tiklab.postin.annotation.ApiMethod;
import net.tiklab.postin.annotation.ApiParam;
import net.tiklab.teston.app.perf.execute.mode.AppPerfTestRequest;
import net.tiklab.teston.app.perf.execute.mode.AppPerfTestResponse;
import net.tiklab.teston.app.perf.execute.service.AppPerfTestDispatchService;

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
