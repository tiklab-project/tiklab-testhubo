package net.tiklab.teston.test.appscene.execute.controller;

import net.tiklab.postin.annotation.Api;
import net.tiklab.postin.annotation.ApiMethod;
import net.tiklab.postin.annotation.ApiParam;
import net.tiklab.core.Result;
import net.tiklab.teston.test.appscene.execute.model.AppSceneTestRequest;
import net.tiklab.teston.test.appscene.execute.model.AppSceneTestResponse;
import net.tiklab.teston.test.appscene.execute.service.AppSceneTestDispatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

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
        AppSceneTestResponse reslut = appSceneTestDispatchService.execute(appSceneTestRequest);

        return Result.ok(reslut);
    }

}
