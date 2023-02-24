package net.tiklab.teston.test.apiscene.execute.controller;

import net.tiklab.postin.annotation.Api;
import net.tiklab.postin.annotation.ApiMethod;
import net.tiklab.postin.annotation.ApiParam;
import net.tiklab.core.Result;
import net.tiklab.teston.test.apiscene.execute.model.ApiSceneTestRequest;
import net.tiklab.teston.test.apiscene.execute.model.ApiSceneTestResponse;
import net.tiklab.teston.test.apiscene.execute.service.ApiSceneTestDispatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/apiSceneTestDispatch")
@Api(name = "ApiSceneTestDispatchController",desc = "api场景测试调度")
public class ApiSceneTestDispatchController {

    @Autowired
    private ApiSceneTestDispatchService apiSceneTestDispatchService;

    @RequestMapping(path="/execute",method = RequestMethod.POST)
    @ApiMethod(name = "execute",desc = "执行api接口的测试用例")
    @ApiParam(name = "ApiSceneCase",desc = "ApiSceneCase",required = true)
    public Result<ApiSceneTestResponse> execute(@RequestBody @NotNull @Valid ApiSceneTestRequest apiSceneTestRequest){
        ApiSceneTestResponse apiSceneTestResponse = apiSceneTestDispatchService.execute(apiSceneTestRequest);

        return Result.ok(apiSceneTestResponse);
    }

}
