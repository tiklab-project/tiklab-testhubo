package io.tiklab.teston.test.apix.http.unit.cases.controller;

import io.tiklab.postin.annotation.Api;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.Result;
import io.tiklab.postin.annotation.ApiMethod;
import io.tiklab.postin.annotation.ApiParam;
import io.tiklab.teston.test.apix.http.unit.cases.model.RequestBody;
import io.tiklab.teston.test.apix.http.unit.cases.model.RequestBodyQuery;
import io.tiklab.teston.test.apix.http.unit.cases.service.RequestBodyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 请求体 控制器
 */
@RestController
@RequestMapping("/requestBody")
@Api(name = "RequestBodyController",desc = "接口用例步骤RequestBodyController")
public class RequestBodyController {

    private static Logger logger = LoggerFactory.getLogger(RequestBodyController.class);

    @Autowired
    private RequestBodyService requestBodyService;

    @RequestMapping(path="/createRequestBody",method = RequestMethod.POST)
    @ApiMethod(name = "createRequestBody",desc = "创建请求体")
    @ApiParam(name = "requestBody",desc = "requestBody",required = true)
    public Result<String> createRequestBody(@org.springframework.web.bind.annotation.RequestBody @NotNull @Valid RequestBody requestBody){
        String id = requestBodyService.createRequestBody(requestBody);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateRequestBody",method = RequestMethod.POST)
    @ApiMethod(name = "updateRequestBody",desc = "更新请求体")
    @ApiParam(name = "requestBody",desc = "requestBody",required = true)
    public Result<Void> updateRequestBody(@org.springframework.web.bind.annotation.RequestBody @NotNull @Valid RequestBody requestBody){
        requestBodyService.updateRequestBody(requestBody);

        return Result.ok();
    }

    @RequestMapping(path="/deleteRequestBody",method = RequestMethod.POST)
    @ApiMethod(name = "deleteRequestBody",desc = "删除请求体")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteRequestBody(@NotNull String id){
        requestBodyService.deleteRequestBody(id);

        return Result.ok();
    }

    @RequestMapping(path="/findRequestBody",method = RequestMethod.POST)
    @ApiMethod(name = "findRequestBody",desc = "查找请求体")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<RequestBody> findRequestBody(@NotNull String id){
        RequestBody requestBody = requestBodyService.findRequestBody(id);

        return Result.ok(requestBody);
    }

    @RequestMapping(path="/findAllRequestBody",method = RequestMethod.POST)
    @ApiMethod(name = "findAllRequestBody",desc = "查找所有请求体")
    public Result<List<RequestBody>> findAllRequestBody(){
        List<RequestBody> requestBodyList = requestBodyService.findAllRequestBody();

        return Result.ok(requestBodyList);
    }

    @RequestMapping(path = "/findRequestBodyList",method = RequestMethod.POST)
    @ApiMethod(name = "findRequestBodyList",desc = "根据查询参数查询请求体列表")
    @ApiParam(name = "requestBodyQuery",desc = "requestBodyQuery",required = true)
    public Result<List<RequestBody>> findRequestBodyList(@org.springframework.web.bind.annotation.RequestBody @Valid @NotNull RequestBodyQuery requestBodyQuery){
        List<RequestBody> requestBodyList = requestBodyService.findRequestBodyList(requestBodyQuery);

        return Result.ok(requestBodyList);
    }

    @RequestMapping(path = "/findRequestBodyPage",method = RequestMethod.POST)
    @ApiMethod(name = "findRequestBodyPage",desc = "根据查询参数按分页查询请求体")
    @ApiParam(name = "requestBodyQuery",desc = "requestBodyQuery",required = true)
    public Result<Pagination<RequestBody>> findRequestBodyPage(@org.springframework.web.bind.annotation.RequestBody @Valid @NotNull RequestBodyQuery requestBodyQuery){
        Pagination<RequestBody> pagination = requestBodyService.findRequestBodyPage(requestBodyQuery);

        return Result.ok(pagination);
    }

}
