package net.tiklab.teston.apix.http.unit.http.cases.controller;

import net.tiklab.postin.annotation.Api;
import net.tiklab.core.page.Pagination;
import net.tiklab.core.Result;
import net.tiklab.postin.annotation.ApiMethod;
import net.tiklab.postin.annotation.ApiParam;
import net.tiklab.teston.apix.http.unit.cases.model.RequestHeader;
import net.tiklab.teston.apix.http.unit.cases.model.RequestHeaderQuery;
import net.tiklab.teston.apix.http.unit.cases.service.RequestHeaderService;
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
    public Result<String> createRequestHeader(@RequestBody @NotNull @Valid RequestHeader requestHeader){
        String id = requestHeaderService.createRequestHeader(requestHeader);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateRequestHeader",method = RequestMethod.POST)
    @ApiMethod(name = "updateRequestHeader",desc = "更新请求头")
    @ApiParam(name = "requestHeader",desc = "requestHeader",required = true)
    public Result<Void> updateRequestHeader(@RequestBody @NotNull @Valid RequestHeader requestHeader){
        requestHeaderService.updateRequestHeader(requestHeader);

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
    public Result<RequestHeader> findRequestHeader(@NotNull String id){
        RequestHeader requestHeader = requestHeaderService.findRequestHeader(id);

        return Result.ok(requestHeader);
    }

    @RequestMapping(path="/findAllRequestHeader",method = RequestMethod.POST)
    @ApiMethod(name = "findAllRequestHeader",desc = "查找所有请求头")
    public Result<List<RequestHeader>> findAllRequestHeader(){
        List<RequestHeader> requestHeaderList = requestHeaderService.findAllRequestHeader();

        return Result.ok(requestHeaderList);
    }

    @RequestMapping(path = "/findRequestHeaderList",method = RequestMethod.POST)
    @ApiMethod(name = "findRequestHeaderList",desc = "根据参数查询请求头列表")
    @ApiParam(name = "requestHeaderQuery",desc = "requestHeaderQuery",required = true)
    public Result<List<RequestHeader>> findRequestHeaderList(@RequestBody @Valid @NotNull RequestHeaderQuery requestHeaderQuery){
        List<RequestHeader> requestHeaderList = requestHeaderService.findRequestHeaderList(requestHeaderQuery);

        return Result.ok(requestHeaderList);
    }

    @RequestMapping(path = "/findRequestHeaderPage",method = RequestMethod.POST)
    @ApiMethod(name = "findRequestHeaderPage",desc = "根据参数按分页查询请求头")
    @ApiParam(name = "requestHeaderQuery",desc = "requestHeaderQuery",required = true)
    public Result<Pagination<RequestHeader>> findRequestHeaderPage(@RequestBody @Valid @NotNull RequestHeaderQuery requestHeaderQuery){
        Pagination<RequestHeader> pagination = requestHeaderService.findRequestHeaderPage(requestHeaderQuery);

        return Result.ok(pagination);
    }

}
