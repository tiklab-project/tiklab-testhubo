package io.thoughtware.teston.test.app.perf.cases.controller;

import io.thoughtware.teston.test.app.perf.cases.model.AppPerfStep;
import io.thoughtware.teston.test.app.perf.cases.model.AppPerfStepQuery;
import io.thoughtware.teston.test.app.perf.cases.service.AppPerfStepService;
import io.thoughtware.core.Result;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.postin.annotation.Api;
import io.thoughtware.postin.annotation.ApiMethod;
import io.thoughtware.postin.annotation.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * app性能测试用例下绑定的步骤 控制器
 */
@RestController
@RequestMapping("/appPerfStep")
@Api(name = "AppPerfStepController",desc = "app性能测试用例下绑定的步骤管理")
public class AppPerfStepController {

    private static Logger logger = LoggerFactory.getLogger(AppPerfStepController.class);

    @Autowired
    private AppPerfStepService appPerfStepService;


    @RequestMapping(path="/bindAppScene",method = RequestMethod.POST)
    @ApiMethod(name = "bindAppScene",desc = "绑定app场景")
    @ApiParam(name = "appPerfStepList",desc = "appPerfStepList",required = true)
    public Result<String> bindWebScene(@RequestBody @NotNull @Valid List<AppPerfStep> appPerfStepList){
        appPerfStepService.bindAppScene(appPerfStepList);

        return Result.ok();
    }

    @RequestMapping(path="/createAppPerfStep",method = RequestMethod.POST)
    @ApiMethod(name = "createAppPerfStep",desc = "创建绑定的步骤")
    @ApiParam(name = "appPerfStep",desc = "appPerfStep",required = true)
    public Result<String> createAppPerfStep(@RequestBody @NotNull @Valid AppPerfStep appPerfStep){
        String id = appPerfStepService.createAppPerfStep(appPerfStep);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateAppPerfStep",method = RequestMethod.POST)
    @ApiMethod(name = "updateAppPerfStep",desc = "更新绑定的步骤")
    @ApiParam(name = "appPerfStep",desc = "appPerfStep",required = true)
    public Result<Void> updateAppPerfStep(@RequestBody @NotNull @Valid AppPerfStep appPerfStep){
        appPerfStepService.updateAppPerfStep(appPerfStep);

        return Result.ok();
    }

    @RequestMapping(path="/deleteAppPerfStep",method = RequestMethod.POST)
    @ApiMethod(name = "deleteAppPerfStep",desc = "删除绑定的步骤")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteAppPerfStep(@NotNull String id){
        appPerfStepService.deleteAppPerfStep(id);

        return Result.ok();
    }

    @RequestMapping(path="/findAppPerfStep",method = RequestMethod.POST)
    @ApiMethod(name = "findAppPerfStep",desc = "根据id查找绑定的步骤")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<AppPerfStep> findAppPerfStep(@NotNull String id){
        AppPerfStep appPerfStep = appPerfStepService.findAppPerfStep(id);

        return Result.ok(appPerfStep);
    }

    @RequestMapping(path="/findAllAppPerfStep",method = RequestMethod.POST)
    @ApiMethod(name = "findAllAppPerfStep",desc = "查找所有绑定的步骤")
    public Result<List<AppPerfStep>> findAllAppPerfStep(){
        List<AppPerfStep> appPerfStepList = appPerfStepService.findAllAppPerfStep();

        return Result.ok(appPerfStepList);
    }

    @RequestMapping(path = "/findAppPerfStepList",method = RequestMethod.POST)
    @ApiMethod(name = "findAppPerfStepList",desc = "根据查询参数查询绑定的步骤 列表")
    @ApiParam(name = "appPerfStepQuery",desc = "appPerfStepQuery",required = true)
    public Result<List<AppPerfStep>> findAppPerfStepList(@RequestBody @Valid @NotNull AppPerfStepQuery appPerfStepQuery){
        List<AppPerfStep> appPerfStepList = appPerfStepService.findAppPerfStepList(appPerfStepQuery);

        return Result.ok(appPerfStepList);
    }

    @RequestMapping(path = "/findAppPerfStepPage",method = RequestMethod.POST)
    @ApiMethod(name = "findAppPerfStepPage",desc = "根据查询参数按分页查询绑定的步骤")
    @ApiParam(name = "appPerfStepQuery",desc = "appPerfStepQuery",required = true)
    public Result<Pagination<AppPerfStep>> findAppPerfStepPage(@RequestBody @Valid @NotNull AppPerfStepQuery appPerfStepQuery){
        Pagination<AppPerfStep> pagination = appPerfStepService.findAppPerfStepPage(appPerfStepQuery);

        return Result.ok(pagination);
    }

}
