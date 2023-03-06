package net.tiklab.teston.test.apix.http.unit.cases.controller;

import net.tiklab.postin.annotation.Api;
import net.tiklab.core.page.Pagination;
import net.tiklab.core.Result;
import net.tiklab.postin.annotation.ApiMethod;
import net.tiklab.postin.annotation.ApiParam;
import net.tiklab.teston.test.apix.http.unit.cases.model.ResponseHeader;
import net.tiklab.teston.test.apix.http.unit.cases.model.ResponseHeaderQuery;
import net.tiklab.teston.test.apix.http.unit.cases.service.ResponseHeaderService;
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
 * 响应头 控制器
 */
@RestController
@RequestMapping("/responseHeader")
@Api(name = "ResponseHeaderController",desc = "接口用例步骤ResponseHeaderController")
public class ResponseHeaderController {

    private static Logger logger = LoggerFactory.getLogger(ResponseHeaderController.class);

    @Autowired
    private ResponseHeaderService responseHeaderService;

    @RequestMapping(path="/createResponseHeader",method = RequestMethod.POST)
    @ApiMethod(name = "createResponseHeader",desc = "创建响应头")
    @ApiParam(name = "responseHeader",desc = "responseHeader",required = true)
    public Result<String> createResponseHeader(@RequestBody @NotNull @Valid ResponseHeader responseHeader){
        String id = responseHeaderService.createResponseHeader(responseHeader);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateResponseHeader",method = RequestMethod.POST)
    @ApiMethod(name = "updateResponseHeader",desc = "更新响应头")
    @ApiParam(name = "responseHeader",desc = "responseHeader",required = true)
    public Result<Void> updateResponseHeader(@RequestBody @NotNull @Valid ResponseHeader responseHeader){
        responseHeaderService.updateResponseHeader(responseHeader);

        return Result.ok();
    }

    @RequestMapping(path="/deleteResponseHeader",method = RequestMethod.POST)
    @ApiMethod(name = "deleteResponseHeader",desc = "删除响应头")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteResponseHeader(@NotNull String id){
        responseHeaderService.deleteResponseHeader(id);

        return Result.ok();
    }

    @RequestMapping(path="/findResponseHeader",method = RequestMethod.POST)
    @ApiMethod(name = "findResponseHeader",desc = "查找响应头")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<ResponseHeader> findResponseHeader(@NotNull String id){
        ResponseHeader responseHeader = responseHeaderService.findResponseHeader(id);

        return Result.ok(responseHeader);
    }

    @RequestMapping(path="/findAllResponseHeader",method = RequestMethod.POST)
    @ApiMethod(name = "findAllResponseHeader",desc = "查找所有响应头")
    public Result<List<ResponseHeader>> findAllResponseHeader(){
        List<ResponseHeader> responseHeaderList = responseHeaderService.findAllResponseHeader();

        return Result.ok(responseHeaderList);
    }

    @RequestMapping(path = "/findResponseHeaderList",method = RequestMethod.POST)
    @ApiMethod(name = "findResponseHeaderList",desc = "根据查询参数查询响应头列表")
    @ApiParam(name = "responseHeaderQuery",desc = "responseHeaderQuery",required = true)
    public Result<List<ResponseHeader>> findResponseHeaderList(@RequestBody @Valid @NotNull ResponseHeaderQuery responseHeaderQuery){
        List<ResponseHeader> responseHeaderList = responseHeaderService.findResponseHeaderList(responseHeaderQuery);

        return Result.ok(responseHeaderList);
    }

    @RequestMapping(path = "/findResponseHeaderPage",method = RequestMethod.POST)
    @ApiMethod(name = "findResponseHeaderPage",desc = "根据查询参数按分页查询响应头")
    @ApiParam(name = "responseHeaderQuery",desc = "responseHeaderQuery",required = true)
    public Result<Pagination<ResponseHeader>> findResponseHeaderPage(@RequestBody @Valid @NotNull ResponseHeaderQuery responseHeaderQuery){
        Pagination<ResponseHeader> pagination = responseHeaderService.findResponseHeaderPage(responseHeaderQuery);

        return Result.ok(pagination);
    }

}
