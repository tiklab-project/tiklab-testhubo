package io.thoughtware.teston.test.apix.http.unit.cases.controller;

import io.thoughtware.teston.test.apix.http.unit.cases.model.ResponseResultUnit;
import io.thoughtware.teston.test.apix.http.unit.cases.model.ResponseResultUnitQuery;
import io.thoughtware.teston.test.apix.http.unit.cases.service.ResponseResultService;
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
 * 响应结果 控制器
 */
@RestController
@RequestMapping("/responseResult")
@Api(name = "ResponseResultController",desc = "接口用例步骤ResponseResultController")
public class ResponseResultController {

    private static Logger logger = LoggerFactory.getLogger(ResponseResultController.class);

    @Autowired
    private ResponseResultService responseResultService;

    @RequestMapping(path="/createResponseResult",method = RequestMethod.POST)
    @ApiMethod(name = "createResponseResult",desc = "创建响应结果")
    @ApiParam(name = "responseResult",desc = "responseResult",required = true)
    public Result<String> createResponseResult(@RequestBody @NotNull @Valid ResponseResultUnit responseResultUnit){
        String id = responseResultService.createResponseResult(responseResultUnit);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateResponseResult",method = RequestMethod.POST)
    @ApiMethod(name = "updateResponseResult",desc = "更新响应结果")
    @ApiParam(name = "responseResult",desc = "responseResult",required = true)
    public Result<Void> updateResponseResult(@RequestBody @NotNull @Valid ResponseResultUnit responseResultUnit){
        responseResultService.updateResponseResult(responseResultUnit);

        return Result.ok();
    }

    @RequestMapping(path="/deleteResponseResult",method = RequestMethod.POST)
    @ApiMethod(name = "deleteResponseResult",desc = "删除响应结果")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteResponseResult(@NotNull String id){
        responseResultService.deleteResponseResult(id);

        return Result.ok();
    }

    @RequestMapping(path="/findResponseResult",method = RequestMethod.POST)
    @ApiMethod(name = "findResponseResult",desc = "查找响应结果")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<ResponseResultUnit> findResponseResult(@NotNull String id){
        ResponseResultUnit responseResultUnit = responseResultService.findResponseResult(id);

        return Result.ok(responseResultUnit);
    }

    @RequestMapping(path="/findAllResponseResult",method = RequestMethod.POST)
    @ApiMethod(name = "findAllResponseResult",desc = "查找所有响应结果")
    public Result<List<ResponseResultUnit>> findAllResponseResult(){
        List<ResponseResultUnit> responseResultUnitList = responseResultService.findAllResponseResult();

        return Result.ok(responseResultUnitList);
    }

    @RequestMapping(path = "/findResponseResultList",method = RequestMethod.POST)
    @ApiMethod(name = "findResponseResultList",desc = "根据查询参数查询响应结果列表")
    @ApiParam(name = "responseResultQuery",desc = "responseResultQuery",required = true)
    public Result<List<ResponseResultUnit>> findResponseResultList(@RequestBody @Valid @NotNull ResponseResultUnitQuery responseResultUnitQuery){
        List<ResponseResultUnit> responseResultUnitList = responseResultService.findResponseResultList(responseResultUnitQuery);

        return Result.ok(responseResultUnitList);
    }

    @RequestMapping(path = "/findResponseResultPage",method = RequestMethod.POST)
    @ApiMethod(name = "findResponseResultPage",desc = "根据查询参数按分页查询响应结果")
    @ApiParam(name = "responseResultQuery",desc = "responseResultQuery",required = true)
    public Result<Pagination<ResponseResultUnit>> findResponseResultPage(@RequestBody @Valid @NotNull ResponseResultUnitQuery responseResultUnitQuery){
        Pagination<ResponseResultUnit> pagination = responseResultService.findResponseResultPage(responseResultUnitQuery);

        return Result.ok(pagination);
    }

}
