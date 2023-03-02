package net.tiklab.teston.apix.http.unit.http.cases.controller;

import net.tiklab.postin.annotation.Api;
import net.tiklab.core.page.Pagination;
import net.tiklab.core.Result;
import net.tiklab.postin.annotation.ApiMethod;
import net.tiklab.postin.annotation.ApiParam;
import net.tiklab.teston.apix.http.unit.cases.model.ResponseResult;
import net.tiklab.teston.apix.http.unit.cases.model.ResponseResultQuery;
import net.tiklab.teston.apix.http.unit.cases.service.ResponseResultService;
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
    public Result<String> createResponseResult(@RequestBody @NotNull @Valid ResponseResult responseResult){
        String id = responseResultService.createResponseResult(responseResult);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateResponseResult",method = RequestMethod.POST)
    @ApiMethod(name = "updateResponseResult",desc = "更新响应结果")
    @ApiParam(name = "responseResult",desc = "responseResult",required = true)
    public Result<Void> updateResponseResult(@RequestBody @NotNull @Valid ResponseResult responseResult){
        responseResultService.updateResponseResult(responseResult);

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
    public Result<ResponseResult> findResponseResult(@NotNull String id){
        ResponseResult responseResult = responseResultService.findResponseResult(id);

        return Result.ok(responseResult);
    }

    @RequestMapping(path="/findAllResponseResult",method = RequestMethod.POST)
    @ApiMethod(name = "findAllResponseResult",desc = "查找所有响应结果")
    public Result<List<ResponseResult>> findAllResponseResult(){
        List<ResponseResult> responseResultList = responseResultService.findAllResponseResult();

        return Result.ok(responseResultList);
    }

    @RequestMapping(path = "/findResponseResultList",method = RequestMethod.POST)
    @ApiMethod(name = "findResponseResultList",desc = "根据查询参数查询响应结果列表")
    @ApiParam(name = "responseResultQuery",desc = "responseResultQuery",required = true)
    public Result<List<ResponseResult>> findResponseResultList(@RequestBody @Valid @NotNull ResponseResultQuery responseResultQuery){
        List<ResponseResult> responseResultList = responseResultService.findResponseResultList(responseResultQuery);

        return Result.ok(responseResultList);
    }

    @RequestMapping(path = "/findResponseResultPage",method = RequestMethod.POST)
    @ApiMethod(name = "findResponseResultPage",desc = "根据查询参数按分页查询响应结果")
    @ApiParam(name = "responseResultQuery",desc = "responseResultQuery",required = true)
    public Result<Pagination<ResponseResult>> findResponseResultPage(@RequestBody @Valid @NotNull ResponseResultQuery responseResultQuery){
        Pagination<ResponseResult> pagination = responseResultService.findResponseResultPage(responseResultQuery);

        return Result.ok(pagination);
    }

}
