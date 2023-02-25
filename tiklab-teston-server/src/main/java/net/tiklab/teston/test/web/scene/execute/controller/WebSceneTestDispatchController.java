package net.tiklab.teston.test.web.scene.execute.controller;

import net.tiklab.postin.annotation.Api;
import net.tiklab.postin.annotation.ApiMethod;
import net.tiklab.postin.annotation.ApiParam;
import net.tiklab.core.Result;
import net.tiklab.teston.test.web.scene.execute.model.WebSceneTestRequest;
import net.tiklab.teston.test.web.scene.execute.model.WebSceneTestResponse;
import net.tiklab.teston.test.web.scene.execute.service.WebSceneTestDispatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

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
