package io.tiklab.teston.support.environment.controller;

import io.tiklab.postin.annotation.Api;
import io.tiklab.teston.support.environment.model.ApiEnv;
import io.tiklab.teston.support.environment.model.ApiEnvQuery;
import io.tiklab.teston.support.environment.service.ApiEnvService;
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
 * @pi.protocol: http
 * @pi.groupName: apiEnv 控制器
 */
@RestController
@RequestMapping("/apxEnv")
@Api(name = "ApiEnvController",desc = "测试环境管理")
public class ApiEnvController {

    private static Logger logger = LoggerFactory.getLogger(ApiEnvController.class);

    @Autowired
    private ApiEnvService apiEnvService;

    /**
     * @pi.name:createApiEnv
     * @pi.path:/apxEnv/createApiEnv
     * @pi.methodType:post
     * @pi.request-type:json
     * @pi.param: model=ApiEnv
     */
    @RequestMapping(path="/createApiEnv",method = RequestMethod.POST)
    @ApiMethod(name = "createApiEnv",desc = "添加测试环境")
    @ApiParam(name = "apiEnv",desc = "apiEnv",required = true)
    public Result<String> createApiEnv(@RequestBody @NotNull @Valid ApiEnv apiEnv){
        String id = apiEnvService.createApiEnv(apiEnv);

        return Result.ok(id);
    }

    /**
     * @pi.name:updateApiEnv
     * @pi.path:/apxEnv/updateApiEnv
     * @pi.methodType:post
     * @pi.request-type:json
     * @pi.param: model=ApiEnv
     */
    @RequestMapping(path="/updateApiEnv",method = RequestMethod.POST)
    @ApiMethod(name = "updateApiEnv",desc = "更新测试环境")
    @ApiParam(name = "apiEnv",desc = "apiEnv",required = true)
    public Result<Void> updateApiEnv(@RequestBody @NotNull @Valid ApiEnv apiEnv){
        apiEnvService.updateApiEnv(apiEnv);

        return Result.ok();
    }

    /**
     * @pi.name:deleteApiEnv
     * @pi.path:/apxEnv/deleteApiEnv
     * @pi.methodType:post
     * @pi.request-type:json
     * @pi.param: name=id;dataType=string;value=id;desc=当前删除的id
     */
    @RequestMapping(path="/deleteApiEnv",method = RequestMethod.POST)
    @ApiMethod(name = "deleteApiEnv",desc = "删除测试环境")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteApiEnv(@NotNull String id){
        apiEnvService.deleteApiEnv(id);

        return Result.ok();
    }

    /**
     * @pi.name:findApiEnv
     * @pi.path:/apxEnv/findApiEnv
     * @pi.methodType:post
     * @pi.request-type:formdata
     * @pi.param: name=id;dataType=string;value=id;desc=当前查找的id
     */
    @RequestMapping(path="/findApiEnv",method = RequestMethod.POST)
    @ApiMethod(name = "findApiEnv",desc = "通过id查询测试环境")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<ApiEnv> findApiEnv(@NotNull String id){
        ApiEnv apiEnv = apiEnvService.findApiEnv(id);

        return Result.ok(apiEnv);
    }

    /**
     * @pi.name:findAllApiEnv
     * @pi.path:/apxEnv/findAllApiEnv
     * @pi.methodType:post
     * @pi.request-type:none
     */
    @RequestMapping(path="/findAllApiEnv",method = RequestMethod.POST)
    @ApiMethod(name = "findAllApiEnv",desc = "查询所有测试环境")
    public Result<List<ApiEnv>> findAllApiEnv(){
        List<ApiEnv> apiEnvList = apiEnvService.findAllApiEnv();

        return Result.ok(apiEnvList);
    }


    /**
     * @pi.name:findApiEnvList
     * @pi.path:/apxEnv/findApiEnvList
     * @pi.methodType:post
     * @pi.request-type:json
     * @pi.param: model=ApiEnv
     */
    @RequestMapping(path = "/findApiEnvList",method = RequestMethod.POST)
    @ApiMethod(name = "findApiEnvList",desc = "通过查询对象查询测试环境")
    @ApiParam(name = "apiEnvQuery",desc = "apiEnvQuery",required = true)
    public Result<List<ApiEnv>> findApiEnvList(@RequestBody @Valid @NotNull ApiEnvQuery apiEnvQuery){
        List<ApiEnv> apiEnvList = apiEnvService.findApiEnvList(apiEnvQuery);

        return Result.ok(apiEnvList);
    }

    /**
     * @pi.name:findApiEnvPage
     * @pi.path:/apxEnv/findApiEnvPage
     * @pi.methodType:post
     * @pi.request-type:json
     * @pi.param: model=ApiEnvQuery
     */
    @RequestMapping(path = "/findApiEnvPage",method = RequestMethod.POST)
    @ApiMethod(name = "findApiEnvPage",desc = "通过查询对象分页查询测试环境")
    @ApiParam(name = "apiEnvQuery",desc = "apiEnvQuery",required = true)
    public Result<Pagination<ApiEnv>> findApiEnvPage(@RequestBody @Valid @NotNull ApiEnvQuery apiEnvQuery){
        Pagination<ApiEnv> pagination = apiEnvService.findApiEnvPage(apiEnvQuery);

        return Result.ok(pagination);
    }

}
