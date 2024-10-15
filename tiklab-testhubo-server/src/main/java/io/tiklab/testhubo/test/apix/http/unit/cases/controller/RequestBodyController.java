package io.tiklab.testhubo.test.apix.http.unit.cases.controller;

import io.tiklab.testhubo.test.apix.http.unit.cases.model.RequestBodyUnit;
import io.tiklab.testhubo.test.apix.http.unit.cases.model.RequestBodyUnitQuery;
import io.tiklab.testhubo.test.apix.http.unit.cases.service.RequestBodyService;
import io.tiklab.postin.annotation.Api;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.Result;
import io.tiklab.postin.annotation.ApiMethod;
import io.tiklab.postin.annotation.ApiParam;
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
    public Result<String> createRequestBody(@org.springframework.web.bind.annotation.RequestBody @NotNull @Valid RequestBodyUnit requestBodyUnit){
        String id = requestBodyService.createRequestBody(requestBodyUnit);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateRequestBody",method = RequestMethod.POST)
    @ApiMethod(name = "updateRequestBody",desc = "更新请求体")
    @ApiParam(name = "requestBody",desc = "requestBody",required = true)
    public Result<Void> updateRequestBody(@org.springframework.web.bind.annotation.RequestBody @NotNull @Valid RequestBodyUnit requestBodyUnit){
        requestBodyService.updateRequestBody(requestBodyUnit);

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
    public Result<RequestBodyUnit> findRequestBody(@NotNull String id){
        RequestBodyUnit requestBodyUnit = requestBodyService.findRequestBody(id);

        return Result.ok(requestBodyUnit);
    }

    @RequestMapping(path="/findAllRequestBody",method = RequestMethod.POST)
    @ApiMethod(name = "findAllRequestBody",desc = "查找所有请求体")
    public Result<List<RequestBodyUnit>> findAllRequestBody(){
        List<RequestBodyUnit> requestBodyUnitList = requestBodyService.findAllRequestBody();

        return Result.ok(requestBodyUnitList);
    }

    @RequestMapping(path = "/findRequestBodyList",method = RequestMethod.POST)
    @ApiMethod(name = "findRequestBodyList",desc = "根据查询参数查询请求体列表")
    @ApiParam(name = "requestBodyQuery",desc = "requestBodyQuery",required = true)
    public Result<List<RequestBodyUnit>> findRequestBodyList(@org.springframework.web.bind.annotation.RequestBody @Valid @NotNull RequestBodyUnitQuery requestBodyUnitQuery){
        List<RequestBodyUnit> requestBodyUnitList = requestBodyService.findRequestBodyList(requestBodyUnitQuery);

        return Result.ok(requestBodyUnitList);
    }

    @RequestMapping(path = "/findRequestBodyPage",method = RequestMethod.POST)
    @ApiMethod(name = "findRequestBodyPage",desc = "根据查询参数按分页查询请求体")
    @ApiParam(name = "requestBodyQuery",desc = "requestBodyQuery",required = true)
    public Result<Pagination<RequestBodyUnit>> findRequestBodyPage(@org.springframework.web.bind.annotation.RequestBody @Valid @NotNull RequestBodyUnitQuery requestBodyUnitQuery){
        Pagination<RequestBodyUnit> pagination = requestBodyService.findRequestBodyPage(requestBodyUnitQuery);

        return Result.ok(pagination);
    }

}
