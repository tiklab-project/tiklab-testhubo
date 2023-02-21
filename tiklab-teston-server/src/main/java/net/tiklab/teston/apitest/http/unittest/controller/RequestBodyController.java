package net.tiklab.teston.apitest.http.unittest.controller;

import net.tiklab.postin.annotation.Api;
import net.tiklab.teston.apitest.http.unittest.model.RequestBody;
import net.tiklab.teston.apitest.http.unittest.model.RequestBodyQuery;
import net.tiklab.teston.apitest.http.unittest.service.RequestBodyService;
import net.tiklab.core.page.Pagination;
import net.tiklab.core.Result;
import net.tiklab.postin.annotation.ApiMethod;
import net.tiklab.postin.annotation.ApiParam;
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
 * RequestBodyController
 */
@RestController
@RequestMapping("/requestBody")
@Api(name = "RequestBodyController",desc = "接口用例步骤RequestBodyController")
public class RequestBodyController {

    private static Logger logger = LoggerFactory.getLogger(RequestBodyController.class);

    @Autowired
    private RequestBodyService requestBodyService;

    @RequestMapping(path="/createRequestBody",method = RequestMethod.POST)
    @ApiMethod(name = "createRequestBody",desc = "createRequestBody")
    @ApiParam(name = "requestBody",desc = "requestBody",required = true)
    public Result<String> createRequestBody(@org.springframework.web.bind.annotation.RequestBody @NotNull @Valid RequestBody requestBody){
        String id = requestBodyService.createRequestBody(requestBody);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateRequestBody",method = RequestMethod.POST)
    @ApiMethod(name = "updateRequestBody",desc = "updateRequestBody")
    @ApiParam(name = "requestBody",desc = "requestBody",required = true)
    public Result<Void> updateRequestBody(@org.springframework.web.bind.annotation.RequestBody @NotNull @Valid RequestBody requestBody){
        requestBodyService.updateRequestBody(requestBody);

        return Result.ok();
    }

    @RequestMapping(path="/deleteRequestBody",method = RequestMethod.POST)
    @ApiMethod(name = "deleteRequestBody",desc = "deleteRequestBody")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteRequestBody(@NotNull String id){
        requestBodyService.deleteRequestBody(id);

        return Result.ok();
    }

    @RequestMapping(path="/findRequestBody",method = RequestMethod.POST)
    @ApiMethod(name = "findRequestBody",desc = "findRequestBody")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<RequestBody> findRequestBody(@NotNull String id){
        RequestBody requestBody = requestBodyService.findRequestBody(id);

        return Result.ok(requestBody);
    }

    @RequestMapping(path="/findAllRequestBody",method = RequestMethod.POST)
    @ApiMethod(name = "findAllRequestBody",desc = "findAllRequestBody")
    public Result<List<RequestBody>> findAllRequestBody(){
        List<RequestBody> requestBodyList = requestBodyService.findAllRequestBody();

        return Result.ok(requestBodyList);
    }

    @RequestMapping(path = "/findRequestBodyList",method = RequestMethod.POST)
    @ApiMethod(name = "findRequestBodyList",desc = "findRequestBodyList")
    @ApiParam(name = "requestBodyQuery",desc = "requestBodyQuery",required = true)
    public Result<List<RequestBody>> findRequestBodyList(@org.springframework.web.bind.annotation.RequestBody @Valid @NotNull RequestBodyQuery requestBodyQuery){
        List<RequestBody> requestBodyList = requestBodyService.findRequestBodyList(requestBodyQuery);

        return Result.ok(requestBodyList);
    }

    @RequestMapping(path = "/findRequestBodyPage",method = RequestMethod.POST)
    @ApiMethod(name = "findRequestBodyPage",desc = "findRequestBodyPage")
    @ApiParam(name = "requestBodyQuery",desc = "requestBodyQuery",required = true)
    public Result<Pagination<RequestBody>> findRequestBodyPage(@org.springframework.web.bind.annotation.RequestBody @Valid @NotNull RequestBodyQuery requestBodyQuery){
        Pagination<RequestBody> pagination = requestBodyService.findRequestBodyPage(requestBodyQuery);

        return Result.ok(pagination);
    }

}
