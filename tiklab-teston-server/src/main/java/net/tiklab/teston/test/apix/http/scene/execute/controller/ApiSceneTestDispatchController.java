package net.tiklab.teston.test.apix.http.scene.execute.controller;

import net.tiklab.postin.annotation.Api;
import net.tiklab.postin.annotation.ApiMethod;
import net.tiklab.postin.annotation.ApiParam;
import net.tiklab.core.Result;
import net.tiklab.teston.test.apix.http.scene.execute.model.ApiSceneTestRequest;
import net.tiklab.teston.test.apix.http.scene.execute.model.ApiSceneTestResponse;
import net.tiklab.teston.test.apix.http.scene.execute.service.ApiSceneExecuteDispatchService;
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
    @ApiParam(name = "ApiSceneCase",desc = "ApiSceneCase",required = true)
    public Result<ApiSceneTestResponse> execute(@RequestBody @NotNull @Valid ApiSceneTestRequest apiSceneTestRequest){
        ApiSceneTestResponse apiSceneTestResponse = apiSceneExecuteDispatchService.execute(apiSceneTestRequest);

        return Result.ok(apiSceneTestResponse);
    }

}
