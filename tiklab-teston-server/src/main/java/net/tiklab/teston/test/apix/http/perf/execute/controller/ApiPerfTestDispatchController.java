package net.tiklab.teston.test.apix.http.perf.execute.controller;

import net.tiklab.postin.annotation.Api;
import net.tiklab.postin.annotation.ApiMethod;
import net.tiklab.postin.annotation.ApiParam;
import net.tiklab.core.Result;
import net.tiklab.teston.test.apix.http.perf.execute.model.ApiPerfTestRequest;
import net.tiklab.teston.test.apix.http.perf.execute.model.ApiPerfTestResponse;
import net.tiklab.teston.test.apix.http.perf.execute.service.ApiPerfExecuteDispatchService;
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
    @ApiParam(name = "performCaseExec", desc = "执行需要传的参数", required = true)
    public Result<Void> execute(@RequestBody @Valid @NotNull ApiPerfTestRequest apiPerfTestRequest) {
         apiPerfExecuteDispatchService.execute(apiPerfTestRequest);

        return Result.ok();
    }

    @RequestMapping(path = "/exeResult", method = RequestMethod.POST)
    @ApiMethod(name = "exeResult", desc = "获取性能测试结果")
    @ApiParam(name = "apiPerfTestRequest", desc = "执行需要传的参数", required = true)
    public Result<ApiPerfTestResponse> exeResult(@RequestBody @Valid ApiPerfTestRequest apiPerfTestRequest) {
        ApiPerfTestResponse apiPerfTestResponse = apiPerfExecuteDispatchService.exeResult(apiPerfTestRequest);
        return Result.ok(apiPerfTestResponse);
    }


    @RequestMapping(path = "/endOrPause", method = RequestMethod.POST)
    @ApiMethod(name = "endOrPause", desc = "停止或暂停性能测试")
    @ApiParam(name = "performCaseExec", desc = "执行需要传的参数 testCaseId  executeType ", required = true)
    public Result<Void> endOrPause(@RequestBody @Valid @NotNull ApiPerfTestRequest apiPerfTestRequest) {
        apiPerfExecuteDispatchService.stop(apiPerfTestRequest);

        return Result.ok();
    }

}
