package io.tiklab.teston.test.apix.http.unit.execute.controller;

import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.annotation.ApiMethod;
import io.tiklab.postin.annotation.ApiParam;
import io.tiklab.core.Result;
import io.tiklab.teston.test.apix.http.unit.execute.model.ApiUnitTestRequest;
import io.tiklab.teston.test.apix.http.unit.execute.service.ApiUnitExecuteDispatchService;
import io.tiklab.teston.test.apix.http.unit.instance.model.ApiUnitInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 接口单元测试调度 控制器
 */
@RestController
@RequestMapping("/apiUnitTestDispatch")
@Api(name = "ApiUnitTestDispatchController",desc = "api单元测试调度")
public class ApiUnitTestDispatchController {

    @Autowired
    ApiUnitExecuteDispatchService apiUnitExecuteDispatchService;

    @RequestMapping(path="/execute",method = RequestMethod.POST)
    @ApiMethod(name = "execute",desc = "执行api接口的测试用例")
    @ApiParam(name = "ApiUnitTestDispatch",desc = "ApiUnitTestDispatch",required = true)
    public Result<ApiUnitInstance> execute(@RequestBody @NotNull @Valid ApiUnitTestRequest apiUnitTestRequest){
        ApiUnitInstance execute = apiUnitExecuteDispatchService.execute(apiUnitTestRequest);

        return Result.ok(execute);
    }

}
