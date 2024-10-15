package io.tiklab.testhubo.test.apix.http.unit.cases.controller;

import io.tiklab.testhubo.test.apix.http.unit.cases.model.RequestHeaderUnit;
import io.tiklab.testhubo.test.apix.http.unit.cases.model.RequestHeaderUnitQuery;
import io.tiklab.testhubo.test.apix.http.unit.cases.service.RequestHeaderService;
import io.tiklab.postin.annotation.Api;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.Result;
import io.tiklab.postin.annotation.ApiMethod;
import io.tiklab.postin.annotation.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 请求头 控制器
 */
@RestController
@RequestMapping("/requestHeader")
@Api(name = "RequestHeaderController",desc = "接口用例步骤RequestHeaderController")
public class RequestHeaderController {

    private static Logger logger = LoggerFactory.getLogger(RequestHeaderController.class);

    @Autowired
    private RequestHeaderService requestHeaderService;

    @RequestMapping(path="/createRequestHeader",method = RequestMethod.POST)
    @ApiMethod(name = "createRequestHeader",desc = "创建请求头")
    @ApiParam(name = "requestHeader",desc = "requestHeader",required = true)
    public Result<String> createRequestHeader(@RequestBody @NotNull @Valid RequestHeaderUnit requestHeaderUnit){
        String id = requestHeaderService.createRequestHeader(requestHeaderUnit);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateRequestHeader",method = RequestMethod.POST)
    @ApiMethod(name = "updateRequestHeader",desc = "更新请求头")
    @ApiParam(name = "requestHeader",desc = "requestHeader",required = true)
    public Result<Void> updateRequestHeader(@RequestBody @NotNull @Valid RequestHeaderUnit requestHeaderUnit){
        requestHeaderService.updateRequestHeader(requestHeaderUnit);

        return Result.ok();
    }

    @RequestMapping(path="/deleteRequestHeader",method = RequestMethod.POST)
    @ApiMethod(name = "deleteRequestHeader",desc = "删除请求头")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteRequestHeader(@NotNull String id){
        requestHeaderService.deleteRequestHeader(id);

        return Result.ok();
    }

    @RequestMapping(path="/findRequestHeader",method = RequestMethod.POST)
    @ApiMethod(name = "findRequestHeader",desc = "查找请求头")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<RequestHeaderUnit> findRequestHeader(@NotNull String id){
        RequestHeaderUnit requestHeaderUnit = requestHeaderService.findRequestHeader(id);

        return Result.ok(requestHeaderUnit);
    }

    @RequestMapping(path="/findAllRequestHeader",method = RequestMethod.POST)
    @ApiMethod(name = "findAllRequestHeader",desc = "查找所有请求头")
    public Result<List<RequestHeaderUnit>> findAllRequestHeader(){
        List<RequestHeaderUnit> requestHeaderUnitList = requestHeaderService.findAllRequestHeader();

        return Result.ok(requestHeaderUnitList);
    }

    @RequestMapping(path = "/findRequestHeaderList",method = RequestMethod.POST)
    @ApiMethod(name = "findRequestHeaderList",desc = "根据参数查询请求头列表")
    @ApiParam(name = "requestHeaderQuery",desc = "requestHeaderQuery",required = true)
    public Result<List<RequestHeaderUnit>> findRequestHeaderList(@RequestBody @Valid @NotNull RequestHeaderUnitQuery requestHeaderUnitQuery){
        List<RequestHeaderUnit> requestHeaderUnitList = requestHeaderService.findRequestHeaderList(requestHeaderUnitQuery);

        return Result.ok(requestHeaderUnitList);
    }

    @RequestMapping(path = "/findRequestHeaderPage",method = RequestMethod.POST)
    @ApiMethod(name = "findRequestHeaderPage",desc = "根据参数按分页查询请求头")
    @ApiParam(name = "requestHeaderQuery",desc = "requestHeaderQuery",required = true)
    public Result<Pagination<RequestHeaderUnit>> findRequestHeaderPage(@RequestBody @Valid @NotNull RequestHeaderUnitQuery requestHeaderUnitQuery){
        Pagination<RequestHeaderUnit> pagination = requestHeaderService.findRequestHeaderPage(requestHeaderUnitQuery);

        return Result.ok(pagination);
    }

}
