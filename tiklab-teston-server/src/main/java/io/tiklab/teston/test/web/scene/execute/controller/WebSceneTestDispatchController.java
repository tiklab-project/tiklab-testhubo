package io.tiklab.teston.test.web.scene.execute.controller;

import io.tiklab.core.Result;
import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.annotation.ApiMethod;
import io.tiklab.postin.annotation.ApiParam;
import io.tiklab.teston.test.web.scene.execute.model.WebSceneTestRequest;
import io.tiklab.teston.test.web.scene.execute.model.WebSceneTestResponse;
import io.tiklab.teston.test.web.scene.execute.service.WebSceneTestDispatchService;
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
    @ApiParam(name = "testWebCaseExec",desc = "testWebCaseExec",required = true)
    public Result<WebSceneTestResponse> execute(@RequestBody @NotNull @Valid WebSceneTestRequest webSceneTestRequest) {
        WebSceneTestResponse result = webSceneTestDispatchService.execute(webSceneTestRequest);

        return Result.ok(result);
    }

}
