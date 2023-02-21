package net.tiklab.teston.apitest.http.unittest.controller;

import net.tiklab.postin.annotation.Api;
import net.tiklab.postin.annotation.ApiMethod;
import net.tiklab.postin.annotation.ApiParam;
import net.tiklab.core.Result;
import net.tiklab.teston.apitest.http.unittest.model.ApiUnitInstance;
import net.tiklab.teston.apitest.http.unittest.model.ApiUnitTestRequest;
import net.tiklab.teston.apitest.http.unittest.service.ApiUnitTestDispatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/apiUnitTestDispatch")
@Api(name = "ApiUnitTestDispatchController",desc = "api单元测试调度")
public class ApiUnitTestDispatchController {

    @Autowired
    ApiUnitTestDispatchService apiUnitTestDispatchService;

    @RequestMapping(path="/execute",method = RequestMethod.POST)
    @ApiMethod(name = "execute",desc = "执行api接口的测试用例")
    @ApiParam(name = "ApiUnitTestDispatch",desc = "ApiUnitTestDispatch",required = true)
    public Result<ApiUnitInstance> execute(@RequestBody @NotNull @Valid ApiUnitTestRequest apiUnitTestRequest){
        ApiUnitInstance execute = apiUnitTestDispatchService.execute(apiUnitTestRequest);

        return Result.ok(execute);
    }

}
