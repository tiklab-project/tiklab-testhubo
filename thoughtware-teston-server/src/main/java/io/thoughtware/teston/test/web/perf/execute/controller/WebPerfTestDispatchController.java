package io.thoughtware.teston.test.web.perf.execute.controller;

import io.thoughtware.teston.test.web.perf.execute.model.WebPerfTestRequest;
import io.thoughtware.teston.test.web.perf.execute.model.WebPerfTestResponse;
import io.thoughtware.core.Result;
import io.thoughtware.postin.annotation.Api;
import io.thoughtware.postin.annotation.ApiMethod;
import io.thoughtware.postin.annotation.ApiParam;
import io.thoughtware.teston.test.web.perf.execute.service.WebPerfTestDispatchService;
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
 * web性能测试调度 控制器
 */
@RestController
@RequestMapping("/webPerfTestDispatch")
@Api(name = "WebPerfTestDispatchController",desc = "web性能测试调度")
public class WebPerfTestDispatchController {

    private static Logger logger = LoggerFactory.getLogger(WebPerfTestDispatchController.class);

    @Autowired
    WebPerfTestDispatchService webPerfTestDispatchService;

    @RequestMapping(path = "/execute", method = RequestMethod.POST)
    @ApiMethod(name = "execute", desc = "执行性能测试")
    @ApiParam(name = "webPerfTestRequest", desc = "执行需要传的参数", required = true)
    public Result<Void> execute(@RequestBody @Valid @NotNull WebPerfTestRequest webPerfTestRequest) {
        webPerfTestDispatchService.execute(webPerfTestRequest);

        return Result.ok();
    }

    @RequestMapping(path = "/exeResult", method = RequestMethod.POST)
    @ApiMethod(name = "exeResult", desc = "获取性能测试结果")
    @ApiParam(name = "webPerfTestRequest", desc = "执行需要传的参数", required = true)
    public Result<WebPerfTestResponse> exeResult(@RequestBody @Valid WebPerfTestRequest webPerfTestRequest) {
        WebPerfTestResponse webPerfTestResponse = webPerfTestDispatchService.exeResult(webPerfTestRequest);

        return Result.ok(webPerfTestResponse);
    }


}
