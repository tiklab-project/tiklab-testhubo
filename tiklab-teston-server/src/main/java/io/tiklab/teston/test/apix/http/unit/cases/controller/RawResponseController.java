package io.tiklab.teston.test.apix.http.unit.cases.controller;

import io.tiklab.postin.annotation.Api;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.Result;
import io.tiklab.postin.annotation.ApiMethod;
import io.tiklab.postin.annotation.ApiParam;
import io.tiklab.teston.test.apix.http.unit.cases.model.RawResponse;
import io.tiklab.teston.test.apix.http.unit.cases.model.RawResponseQuery;
import io.tiklab.teston.test.apix.http.unit.cases.service.RawResponseService;
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
 * 响应中raw 控制器
 */
@RestController
@RequestMapping("/rawResponse")
@Api(name = "RawResponseController",desc = "接口用例步骤RawResponseController")
public class RawResponseController {

    private static Logger logger = LoggerFactory.getLogger(RawResponseController.class);

    @Autowired
    private RawResponseService rawResponseService;

    @RequestMapping(path="/createRawResponse",method = RequestMethod.POST)
    @ApiMethod(name = "createRawResponse",desc = "创建响应中raw")
    @ApiParam(name = "rawResponse",desc = "rawResponse",required = true)
    public Result<String> createRawResponse(@RequestBody @NotNull @Valid RawResponse rawResponse){
        String id = rawResponseService.createRawResponse(rawResponse);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateRawResponse",method = RequestMethod.POST)
    @ApiMethod(name = "updateRawResponse",desc = "更新响应中raw")
    @ApiParam(name = "rawResponse",desc = "rawResponse",required = true)
    public Result<Void> updateRawResponse(@RequestBody @NotNull @Valid RawResponse rawResponse){
        rawResponseService.updateRawResponse(rawResponse);

        return Result.ok();
    }

    @RequestMapping(path="/deleteRawResponse",method = RequestMethod.POST)
    @ApiMethod(name = "deleteRawResponse",desc = "删除响应中raw")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteRawResponse(@NotNull String id){
        rawResponseService.deleteRawResponse(id);

        return Result.ok();
    }

    @RequestMapping(path="/findRawResponse",method = RequestMethod.POST)
    @ApiMethod(name = "findRawResponse",desc = "查找响应中raw")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<RawResponse> findRawResponse(@NotNull String id){
        RawResponse rawResponse = rawResponseService.findRawResponse(id);

        return Result.ok(rawResponse);
    }

    @RequestMapping(path="/findAllRawResponse",method = RequestMethod.POST)
    @ApiMethod(name = "findAllRawResponse",desc = "查找所有响应中raw")
    public Result<List<RawResponse>> findAllRawResponse(){
        List<RawResponse> rawResponseList = rawResponseService.findAllRawResponse();

        return Result.ok(rawResponseList);
    }

    @RequestMapping(path = "/findRawResponseList",method = RequestMethod.POST)
    @ApiMethod(name = "findRawResponseList",desc = "根据查询参数查询响应中raw列表")
    @ApiParam(name = "rawResponseQuery",desc = "rawResponseQuery",required = true)
    public Result<List<RawResponse>> findRawResponseList(@RequestBody @Valid @NotNull RawResponseQuery rawResponseQuery){
        List<RawResponse> rawResponseList = rawResponseService.findRawResponseList(rawResponseQuery);

        return Result.ok(rawResponseList);
    }

    @RequestMapping(path = "/findRawResponsePage",method = RequestMethod.POST)
    @ApiMethod(name = "findRawResponsePage",desc = "根据查询参数按分页查询响应中raw")
    @ApiParam(name = "rawResponseQuery",desc = "rawResponseQuery",required = true)
    public Result<Pagination<RawResponse>> findRawResponsePage(@RequestBody @Valid @NotNull RawResponseQuery rawResponseQuery){
        Pagination<RawResponse> pagination = rawResponseService.findRawResponsePage(rawResponseQuery);

        return Result.ok(pagination);
    }

}