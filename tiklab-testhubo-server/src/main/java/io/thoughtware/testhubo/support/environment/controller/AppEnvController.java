package io.thoughtware.testhubo.support.environment.controller;

import io.thoughtware.postin.annotation.Api;
import io.thoughtware.core.Result;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.testhubo.support.environment.model.AppEnv;
import io.thoughtware.testhubo.support.environment.model.AppEnvQuery;
import io.thoughtware.testhubo.support.environment.service.AppEnvService;
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
 * 
 * @pi.protocol: http
 * @pi.groupName: app环境 控制器
 */
@RestController
@RequestMapping("/appEnv")
@Api(name = "AppEnvController",desc = "app环境管理")
public class AppEnvController {

    private static Logger logger = LoggerFactory.getLogger(AppEnvController.class);

    @Autowired
    private AppEnvService appEnvService;

    /**
     * @pi.name:createAppEnv
     * @pi.path:/appEnv/createAppEnv
     * @pi.methodType:post
     * @pi.request-type:json
     * @pi.param: model=AppEnv
     */
    @RequestMapping(path="/createAppEnv",method = RequestMethod.POST)
    @ApiMethod(name = "createAppEnv",desc = "创建app环境")
    @ApiParam(name = "appEnv",desc = "appEnv",required = true)
    public Result<String> createAppEnv(@RequestBody @NotNull @Valid AppEnv appEnv){
        String id = appEnvService.createAppEnv(appEnv);

        return Result.ok(id);
    }

    /**
     * @pi.name:updateAppEnv
     * @pi.path:/appEnv/updateAppEnv
     * @pi.methodType:post
     * @pi.request-type:json
     * @pi.param: model=AppEnv
     */
    @RequestMapping(path="/updateAppEnv",method = RequestMethod.POST)
    @ApiMethod(name = "updateAppEnv",desc = "更新app环境")
    @ApiParam(name = "appEnv",desc = "appEnv",required = true)
    public Result<Void> updateAppEnv(@RequestBody @NotNull @Valid AppEnv appEnv){
        appEnvService.updateAppEnv(appEnv);

        return Result.ok();
    }

    /**
     * @pi.name:deleteAppEnv
     * @pi.path:/appEnv/deleteAppEnv
     * @pi.methodType:post
     * @pi.request-type:json
     * @pi.param: name=id;dataType=string;value=id;desc=当前删除的id
     */
    @RequestMapping(path="/deleteAppEnv",method = RequestMethod.POST)
    @ApiMethod(name = "deleteAppEnv",desc = "删除app环境")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteAppEnv(@NotNull String id){
        appEnvService.deleteAppEnv(id);

        return Result.ok();
    }

    /**
     * @pi.name:findAppEnv
     * @pi.path:/appEnv/findAppEnv
     * @pi.methodType:post
     * @pi.request-type:formdata
     * @pi.param: name=id;dataType=string;value=id;desc=当前查找的id
     */
    @RequestMapping(path="/findAppEnv",method = RequestMethod.POST)
    @ApiMethod(name = "findAppEnv",desc = "根据id查找app环境")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<AppEnv> findAppEnv(@NotNull String id){
        AppEnv appEnv = appEnvService.findAppEnv(id);

        return Result.ok(appEnv);
    }

    /**
     * @pi.name:findAllAppEnv
     * @pi.path:/appEnv/findAllAppEnv
     * @pi.methodType:post
     * @pi.request-type:none
     */
    @RequestMapping(path="/findAllAppEnv",method = RequestMethod.POST)
    @ApiMethod(name = "findAllAppEnv",desc = "查找所有app环境")
    public Result<List<AppEnv>> findAllAppEnv(){
        List<AppEnv> appEnvList = appEnvService.findAllAppEnv();

        return Result.ok(appEnvList);
    }

    /**
     * @pi.name:findAppEnvList
     * @pi.path:/appEnv/findAppEnvList
     * @pi.methodType:post
     * @pi.request-type:json
     * @pi.param: model=AppEnv
     */
    @RequestMapping(path = "/findAppEnvList",method = RequestMethod.POST)
    @ApiMethod(name = "findAppEnvList",desc = "根据查询参数查询app环境列表")
    @ApiParam(name = "appEnvQuery",desc = "appEnvQuery",required = true)
    public Result<List<AppEnv>> findAppEnvList(@RequestBody @Valid @NotNull AppEnvQuery appEnvQuery){
        List<AppEnv> appEnvList = appEnvService.findAppEnvList(appEnvQuery);

        return Result.ok(appEnvList);
    }

    /**
     * @pi.name:findAppEnvPage
     * @pi.path:/appEnv/findAppEnvPage
     * @pi.methodType:post
     * @pi.request-type:json
     * @pi.param: model=AppEnvQuery
     */
    @RequestMapping(path = "/findAppEnvPage",method = RequestMethod.POST)
    @ApiMethod(name = "findAppEnvPage",desc = "根据查询参数按分页查询app环境")
    @ApiParam(name = "appEnvQuery",desc = "appEnvQuery",required = true)
    public Result<Pagination<AppEnv>> findAppEnvPage(@RequestBody @Valid @NotNull AppEnvQuery appEnvQuery){
        Pagination<AppEnv> pagination = appEnvService.findAppEnvPage(appEnvQuery);

        return Result.ok(pagination);
    }

}
