package net.tiklab.teston.support.environment.controller;

import net.tiklab.postin.annotation.Api;
import net.tiklab.core.Result;
import net.tiklab.core.page.Pagination;
import net.tiklab.teston.support.environment.model.AppEnv;
import net.tiklab.teston.support.environment.model.AppEnvQuery;
import net.tiklab.teston.support.environment.service.AppEnvService;
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
 * app环境 控制器
 */
@RestController
@RequestMapping("/appEnv")
@Api(name = "AppEnvController",desc = "app环境管理")
public class AppEnvController {

    private static Logger logger = LoggerFactory.getLogger(AppEnvController.class);

    @Autowired
    private AppEnvService appEnvService;

    @RequestMapping(path="/createAppEnv",method = RequestMethod.POST)
    @ApiMethod(name = "createAppEnv",desc = "创建app环境")
    @ApiParam(name = "appEnv",desc = "appEnv",required = true)
    public Result<String> createAppEnv(@RequestBody @NotNull @Valid AppEnv appEnv){
        String id = appEnvService.createAppEnv(appEnv);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateAppEnv",method = RequestMethod.POST)
    @ApiMethod(name = "updateAppEnv",desc = "更新app环境")
    @ApiParam(name = "appEnv",desc = "appEnv",required = true)
    public Result<Void> updateAppEnv(@RequestBody @NotNull @Valid AppEnv appEnv){
        appEnvService.updateAppEnv(appEnv);

        return Result.ok();
    }

    @RequestMapping(path="/deleteAppEnv",method = RequestMethod.POST)
    @ApiMethod(name = "deleteAppEnv",desc = "删除app环境")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteAppEnv(@NotNull String id){
        appEnvService.deleteAppEnv(id);

        return Result.ok();
    }

    @RequestMapping(path="/findAppEnv",method = RequestMethod.POST)
    @ApiMethod(name = "findAppEnv",desc = "根据id查找app环境")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<AppEnv> findAppEnv(@NotNull String id){
        AppEnv appEnv = appEnvService.findAppEnv(id);

        return Result.ok(appEnv);
    }

    @RequestMapping(path="/findAllAppEnv",method = RequestMethod.POST)
    @ApiMethod(name = "findAllAppEnv",desc = "查找所有app环境")
    public Result<List<AppEnv>> findAllAppEnv(){
        List<AppEnv> appEnvList = appEnvService.findAllAppEnv();

        return Result.ok(appEnvList);
    }

    @RequestMapping(path = "/findAppEnvList",method = RequestMethod.POST)
    @ApiMethod(name = "findAppEnvList",desc = "根据查询参数查询app环境列表")
    @ApiParam(name = "appEnvQuery",desc = "appEnvQuery",required = true)
    public Result<List<AppEnv>> findAppEnvList(@RequestBody @Valid @NotNull AppEnvQuery appEnvQuery){
        List<AppEnv> appEnvList = appEnvService.findAppEnvList(appEnvQuery);

        return Result.ok(appEnvList);
    }

    @RequestMapping(path = "/findAppEnvPage",method = RequestMethod.POST)
    @ApiMethod(name = "findAppEnvPage",desc = "根据查询参数按分页查询app环境")
    @ApiParam(name = "appEnvQuery",desc = "appEnvQuery",required = true)
    public Result<Pagination<AppEnv>> findAppEnvPage(@RequestBody @Valid @NotNull AppEnvQuery appEnvQuery){
        Pagination<AppEnv> pagination = appEnvService.findAppEnvPage(appEnvQuery);

        return Result.ok(pagination);
    }

}
