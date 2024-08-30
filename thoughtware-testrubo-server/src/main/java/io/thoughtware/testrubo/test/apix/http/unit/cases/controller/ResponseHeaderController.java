package io.thoughtware.testrubo.test.apix.http.unit.cases.controller;

import io.thoughtware.testrubo.test.apix.http.unit.cases.model.ResponseHeaderUnit;
import io.thoughtware.testrubo.test.apix.http.unit.cases.model.ResponseHeaderUnitQuery;
import io.thoughtware.testrubo.test.apix.http.unit.cases.service.ResponseHeaderService;
import io.thoughtware.postin.annotation.Api;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.core.Result;
import io.thoughtware.postin.annotation.ApiMethod;
import io.thoughtware.postin.annotation.ApiParam;
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
    public Result<String> createResponseHeader(@RequestBody @NotNull @Valid ResponseHeaderUnit responseHeaderUnit){
        String id = responseHeaderService.createResponseHeader(responseHeaderUnit);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateResponseHeader",method = RequestMethod.POST)
    @ApiMethod(name = "updateResponseHeader",desc = "更新响应头")
    @ApiParam(name = "responseHeader",desc = "responseHeader",required = true)
    public Result<Void> updateResponseHeader(@RequestBody @NotNull @Valid ResponseHeaderUnit responseHeaderUnit){
        responseHeaderService.updateResponseHeader(responseHeaderUnit);

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
    public Result<ResponseHeaderUnit> findResponseHeader(@NotNull String id){
        ResponseHeaderUnit responseHeaderUnit = responseHeaderService.findResponseHeader(id);

        return Result.ok(responseHeaderUnit);
    }

    @RequestMapping(path="/findAllResponseHeader",method = RequestMethod.POST)
    @ApiMethod(name = "findAllResponseHeader",desc = "查找所有响应头")
    public Result<List<ResponseHeaderUnit>> findAllResponseHeader(){
        List<ResponseHeaderUnit> responseHeaderUnitList = responseHeaderService.findAllResponseHeader();

        return Result.ok(responseHeaderUnitList);
    }

    @RequestMapping(path = "/findResponseHeaderList",method = RequestMethod.POST)
    @ApiMethod(name = "findResponseHeaderList",desc = "根据查询参数查询响应头列表")
    @ApiParam(name = "responseHeaderQuery",desc = "responseHeaderQuery",required = true)
    public Result<List<ResponseHeaderUnit>> findResponseHeaderList(@RequestBody @Valid @NotNull ResponseHeaderUnitQuery responseHeaderUnitQuery){
        List<ResponseHeaderUnit> responseHeaderUnitList = responseHeaderService.findResponseHeaderList(responseHeaderUnitQuery);

        return Result.ok(responseHeaderUnitList);
    }

    @RequestMapping(path = "/findResponseHeaderPage",method = RequestMethod.POST)
    @ApiMethod(name = "findResponseHeaderPage",desc = "根据查询参数按分页查询响应头")
    @ApiParam(name = "responseHeaderQuery",desc = "responseHeaderQuery",required = true)
    public Result<Pagination<ResponseHeaderUnit>> findResponseHeaderPage(@RequestBody @Valid @NotNull ResponseHeaderUnitQuery responseHeaderUnitQuery){
        Pagination<ResponseHeaderUnit> pagination = responseHeaderService.findResponseHeaderPage(responseHeaderUnitQuery);

        return Result.ok(pagination);
    }

}
