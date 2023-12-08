package io.thoughtware.teston.test.app.scene.execute.controller;

import io.thoughtware.teston.test.app.scene.execute.model.AppSceneTestRequest;
import io.thoughtware.teston.test.app.scene.execute.model.AppSceneTestResponse;
import io.thoughtware.teston.test.app.scene.execute.service.AppSceneTestDispatchService;
import io.thoughtware.core.Result;
import io.thoughtware.postin.annotation.Api;
import io.thoughtware.postin.annotation.ApiMethod;
import io.thoughtware.postin.annotation.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * app场景测试调度 控制器
 */
@RestController
@RequestMapping("/appSceneTestDispatch")
@Api(name = "AppSceneTestDispatchController",desc = "app场景测试")
public class AppSceneTestDispatchController {

    @Autowired
    private AppSceneTestDispatchService appSceneTestDispatchService;

    @RequestMapping(path="/execute",method = RequestMethod.POST)
    @ApiMethod(name = "execute",desc = "执行app测试用例")
    @ApiParam(name = "appSceneTestRequest",desc = "appSceneTestRequest",required = true)
    public Result<AppSceneTestResponse> execute(@RequestBody @NotNull @Valid AppSceneTestRequest appSceneTestRequest) {
        //通过controller执行的为手动执行
        Integer reslut = appSceneTestDispatchService.execute(appSceneTestRequest);

        return Result.ok(reslut);
    }


    @RequestMapping(path="/status",method = RequestMethod.POST)
    @ApiMethod(name = "status",desc = "当前执行的状态")
    public Result<Integer> execute() {
        Integer status = appSceneTestDispatchService.status();

        return Result.ok(status);
    }

    @RequestMapping(path="/result",method = RequestMethod.POST)
    @ApiMethod(name = "result",desc = "当前执行的结果")
    @ApiParam(name = "appSceneTestRequest",desc = "appSceneTestRequest",required = true)
    public Result<AppSceneTestResponse> result(@RequestBody @NotNull @Valid AppSceneTestRequest appSceneTestRequest) {
        AppSceneTestResponse result = appSceneTestDispatchService.result(appSceneTestRequest);

        return Result.ok(result);
    }


}
