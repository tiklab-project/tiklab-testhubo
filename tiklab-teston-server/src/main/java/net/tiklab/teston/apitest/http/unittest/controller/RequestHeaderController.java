package net.tiklab.teston.apitest.http.unittest.controller;

import net.tiklab.postin.annotation.Api;
import net.tiklab.teston.apitest.http.unittest.model.RequestHeader;
import net.tiklab.teston.apitest.http.unittest.model.RequestHeaderQuery;
import net.tiklab.teston.apitest.http.unittest.service.RequestHeaderService;
import net.tiklab.core.page.Pagination;
import net.tiklab.core.Result;
import net.tiklab.postin.annotation.ApiMethod;
import net.tiklab.postin.annotation.ApiParam;
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
 * RequestHeaderController
 */
@RestController
@RequestMapping("/requestHeader")
@Api(name = "RequestHeaderController",desc = "接口用例步骤RequestHeaderController")
public class RequestHeaderController {

    private static Logger logger = LoggerFactory.getLogger(RequestHeaderController.class);

    @Autowired
    private RequestHeaderService requestHeaderService;

    @RequestMapping(path="/createRequestHeader",method = RequestMethod.POST)
    @ApiMethod(name = "createRequestHeader",desc = "createRequestHeader")
    @ApiParam(name = "requestHeader",desc = "requestHeader",required = true)
    public Result<String> createRequestHeader(@RequestBody @NotNull @Valid RequestHeader requestHeader){
        String id = requestHeaderService.createRequestHeader(requestHeader);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateRequestHeader",method = RequestMethod.POST)
    @ApiMethod(name = "updateRequestHeader",desc = "updateRequestHeader")
    @ApiParam(name = "requestHeader",desc = "requestHeader",required = true)
    public Result<Void> updateRequestHeader(@RequestBody @NotNull @Valid RequestHeader requestHeader){
        requestHeaderService.updateRequestHeader(requestHeader);

        return Result.ok();
    }

    @RequestMapping(path="/deleteRequestHeader",method = RequestMethod.POST)
    @ApiMethod(name = "deleteRequestHeader",desc = "deleteRequestHeader")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteRequestHeader(@NotNull String id){
        requestHeaderService.deleteRequestHeader(id);

        return Result.ok();
    }

    @RequestMapping(path="/findRequestHeader",method = RequestMethod.POST)
    @ApiMethod(name = "findRequestHeader",desc = "findRequestHeader")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<RequestHeader> findRequestHeader(@NotNull String id){
        RequestHeader requestHeader = requestHeaderService.findRequestHeader(id);

        return Result.ok(requestHeader);
    }

    @RequestMapping(path="/findAllRequestHeader",method = RequestMethod.POST)
    @ApiMethod(name = "findAllRequestHeader",desc = "findAllRequestHeader")
    public Result<List<RequestHeader>> findAllRequestHeader(){
        List<RequestHeader> requestHeaderList = requestHeaderService.findAllRequestHeader();

        return Result.ok(requestHeaderList);
    }

    @RequestMapping(path = "/findRequestHeaderList",method = RequestMethod.POST)
    @ApiMethod(name = "findRequestHeaderList",desc = "findRequestHeaderList")
    @ApiParam(name = "requestHeaderQuery",desc = "requestHeaderQuery",required = true)
    public Result<List<RequestHeader>> findRequestHeaderList(@RequestBody @Valid @NotNull RequestHeaderQuery requestHeaderQuery){
        List<RequestHeader> requestHeaderList = requestHeaderService.findRequestHeaderList(requestHeaderQuery);

        return Result.ok(requestHeaderList);
    }

    @RequestMapping(path = "/findRequestHeaderPage",method = RequestMethod.POST)
    @ApiMethod(name = "findRequestHeaderPage",desc = "findRequestHeaderPage")
    @ApiParam(name = "requestHeaderQuery",desc = "requestHeaderQuery",required = true)
    public Result<Pagination<RequestHeader>> findRequestHeaderPage(@RequestBody @Valid @NotNull RequestHeaderQuery requestHeaderQuery){
        Pagination<RequestHeader> pagination = requestHeaderService.findRequestHeaderPage(requestHeaderQuery);

        return Result.ok(pagination);
    }

}
