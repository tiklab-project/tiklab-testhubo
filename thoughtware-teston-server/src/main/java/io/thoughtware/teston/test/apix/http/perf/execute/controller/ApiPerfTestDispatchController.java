package io.thoughtware.teston.test.apix.http.perf.execute.controller;

import io.thoughtware.postin.annotation.Api;
import io.thoughtware.postin.annotation.ApiMethod;
import io.thoughtware.postin.annotation.ApiParam;
import io.thoughtware.core.Result;
import io.thoughtware.teston.test.apix.http.perf.execute.model.ApiPerfTestRequest;
import io.thoughtware.teston.test.apix.http.perf.execute.model.ApiPerfTestResponse;
import io.thoughtware.teston.test.apix.http.perf.execute.service.ApiPerfExecuteDispatchService;
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
 * 接口性能测试调度 控制器
 */
@RestController
@RequestMapping("/apiPerfTestDispatch")
@Api(name = "ApiPerfTestDispatchController",desc = "api性能测试执行调度")
public class ApiPerfTestDispatchController {

    private static Logger logger = LoggerFactory.getLogger(ApiPerfTestDispatchController.class);

    @Autowired
    ApiPerfExecuteDispatchService apiPerfExecuteDispatchService;

    @RequestMapping(path = "/execute", method = RequestMethod.POST)
    @ApiMethod(name = "execute", desc = "执行性能测试")
    @ApiParam(name = "apiPerfTestRequest", desc = "执行需要传的参数", required = true)
    public Result<Void> execute(@RequestBody @Valid @NotNull ApiPerfTestRequest apiPerfTestRequest) {
         apiPerfExecuteDispatchService.execute(apiPerfTestRequest);

        return Result.ok();
    }

    @RequestMapping(path = "/result", method = RequestMethod.POST)
    @ApiMethod(name = "result", desc = "获取性能测试结果")
    @ApiParam(name = "apiPerfTestRequest", desc = "执行需要传的参数", required = true)
    public Result<ApiPerfTestResponse> exeResult(@RequestBody @Valid ApiPerfTestRequest apiPerfTestRequest) {
        ApiPerfTestResponse apiPerfTestResponse = apiPerfExecuteDispatchService.result(apiPerfTestRequest);
        return Result.ok(apiPerfTestResponse);
    }

    @RequestMapping(path = "/status", method = RequestMethod.POST)
    @ApiMethod(name = "status", desc = "获取性能测试状态")
    public Result<Integer> status() {
        Integer status = apiPerfExecuteDispatchService.status();
        return Result.ok(status);
    }



}
