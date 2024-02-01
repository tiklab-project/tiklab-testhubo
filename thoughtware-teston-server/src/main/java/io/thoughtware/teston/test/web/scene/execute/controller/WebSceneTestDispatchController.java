package io.thoughtware.teston.test.web.scene.execute.controller;

import io.thoughtware.core.Result;
import io.thoughtware.postin.annotation.Api;
import io.thoughtware.postin.annotation.ApiMethod;
import io.thoughtware.postin.annotation.ApiParam;
import io.thoughtware.teston.test.web.scene.execute.model.WebSceneTestRequest;
import io.thoughtware.teston.test.web.scene.execute.model.WebSceneTestResponse;
import io.thoughtware.teston.test.web.scene.execute.service.WebSceneTestDispatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * web场景测试调度 控制器
 */
@RestController
@RequestMapping("/webSceneTestDispatch")
@Api(name = "WebSceneTestDispatchController",desc = "web场景测试调度")
public class WebSceneTestDispatchController {

    @Autowired
    private WebSceneTestDispatchService webSceneTestDispatchService;

    @RequestMapping(path="/execute",method = RequestMethod.POST)
    @ApiMethod(name = "execute",desc = "执行web测试用例")
    @ApiParam(name = "webSceneTestRequest",desc = "webSceneTestRequest",required = true)
    public Result<Void> execute(@RequestBody @NotNull @Valid WebSceneTestRequest webSceneTestRequest) {
        webSceneTestDispatchService.execute(webSceneTestRequest);
        return Result.ok();
    }


    @RequestMapping(path="/status",method = RequestMethod.POST)
    @ApiMethod(name = "status",desc = "当前执行的状态")
    @ApiParam(name = "status",desc = "status",required = true)
    public Result<Integer> execute(@Valid String webSceneId) {
        Integer status = webSceneTestDispatchService.status(webSceneId);

        return Result.ok(status);
    }

    @RequestMapping(path="/result",method = RequestMethod.POST)
    @ApiMethod(name = "result",desc = "当前执行的结果")
    @ApiParam(name = "webSceneTestRequest",desc = "webSceneTestRequest",required = true)
    public Result<WebSceneTestResponse> result(@RequestBody @NotNull @Valid WebSceneTestRequest webSceneTestRequest) {
        WebSceneTestResponse result = webSceneTestDispatchService.result(webSceneTestRequest);

        return Result.ok(result);
    }


}
