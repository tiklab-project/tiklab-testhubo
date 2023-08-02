package io.tiklab.teston.test.apix.http.scene.execute.controller;

import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.annotation.ApiMethod;
import io.tiklab.postin.annotation.ApiParam;
import io.tiklab.core.Result;
import io.tiklab.teston.test.apix.http.scene.execute.model.ApiSceneTestRequest;
import io.tiklab.teston.test.apix.http.scene.execute.model.ApiSceneTestResponse;
import io.tiklab.teston.test.apix.http.scene.execute.service.ApiSceneExecuteDispatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 接口场景测试调度 控制器
 */
@RestController
@RequestMapping("/apiSceneTestDispatch")
@Api(name = "ApiSceneTestDispatchController",desc = "api场景测试调度")
public class ApiSceneTestDispatchController {

    @Autowired
    private ApiSceneExecuteDispatchService apiSceneExecuteDispatchService;

    @RequestMapping(path="/execute",method = RequestMethod.POST)
    @ApiMethod(name = "execute",desc = "执行api接口的测试用例")
    @ApiParam(name = "apiSceneTestRequest",desc = "apiSceneTestRequest",required = true)
    public Result<ApiSceneTestResponse> execute(@RequestBody @NotNull @Valid ApiSceneTestRequest apiSceneTestRequest){
        ApiSceneTestResponse apiSceneTestResponse = apiSceneExecuteDispatchService.execute(apiSceneTestRequest);

        return Result.ok(apiSceneTestResponse);
    }

}
