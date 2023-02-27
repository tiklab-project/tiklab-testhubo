package net.tiklab.teston.app.perf.cases.controller;

import net.tiklab.core.Result;
import net.tiklab.core.page.Pagination;
import net.tiklab.postin.annotation.Api;
import net.tiklab.postin.annotation.ApiMethod;
import net.tiklab.postin.annotation.ApiParam;
import net.tiklab.teston.app.perf.cases.model.AppPerfStep;
import net.tiklab.teston.app.perf.cases.model.AppPerfStepQuery;
import net.tiklab.teston.app.perf.cases.service.AppPerfStepService;
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
 * AppPerfStepController
 */
@RestController
@RequestMapping("/appPerfStep")
@Api(name = "AppPerfStepController",desc = "AppPerfStepController")
public class AppPerfStepController {

    private static Logger logger = LoggerFactory.getLogger(AppPerfStepController.class);

    @Autowired
    private AppPerfStepService appPerfStepService;


    @RequestMapping(path="/bindAppScene",method = RequestMethod.POST)
    @ApiMethod(name = "bindAppScene",desc = "bindAppScene")
    @ApiParam(name = "appPerfStep",desc = "appPerfStep",required = true)
    public Result<String> bindWebScene(@RequestBody @NotNull @Valid List<AppPerfStep> appPerfStepList){
        appPerfStepService.bindAppScene(appPerfStepList);

        return Result.ok();
    }

    @RequestMapping(path="/createAppPerfStep",method = RequestMethod.POST)
    @ApiMethod(name = "createAppPerfStep",desc = "createAppPerfStep")
    @ApiParam(name = "appPerfStep",desc = "appPerfStep",required = true)
    public Result<String> createAppPerfStep(@RequestBody @NotNull @Valid AppPerfStep appPerfStep){
        String id = appPerfStepService.createAppPerfStep(appPerfStep);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateAppPerfStep",method = RequestMethod.POST)
    @ApiMethod(name = "updateAppPerfStep",desc = "updateAppPerfStep")
    @ApiParam(name = "appPerfStep",desc = "appPerfStep",required = true)
    public Result<Void> updateAppPerfStep(@RequestBody @NotNull @Valid AppPerfStep appPerfStep){
        appPerfStepService.updateAppPerfStep(appPerfStep);

        return Result.ok();
    }

    @RequestMapping(path="/deleteAppPerfStep",method = RequestMethod.POST)
    @ApiMethod(name = "deleteAppPerfStep",desc = "deleteAppPerfStep")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteAppPerfStep(@NotNull String id){
        appPerfStepService.deleteAppPerfStep(id);

        return Result.ok();
    }

    @RequestMapping(path="/findAppPerfStep",method = RequestMethod.POST)
    @ApiMethod(name = "findAppPerfStep",desc = "findAppPerfStep")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<AppPerfStep> findAppPerfStep(@NotNull String id){
        AppPerfStep appPerfStep = appPerfStepService.findAppPerfStep(id);

        return Result.ok(appPerfStep);
    }

    @RequestMapping(path="/findAllAppPerfStep",method = RequestMethod.POST)
    @ApiMethod(name = "findAllAppPerfStep",desc = "findAllAppPerfStep")
    public Result<List<AppPerfStep>> findAllAppPerfStep(){
        List<AppPerfStep> appPerfStepList = appPerfStepService.findAllAppPerfStep();

        return Result.ok(appPerfStepList);
    }

    @RequestMapping(path = "/findAppPerfStepList",method = RequestMethod.POST)
    @ApiMethod(name = "findAppPerfStepList",desc = "findAppPerfStepList")
    @ApiParam(name = "appPerfStepQuery",desc = "appPerfStepQuery",required = true)
    public Result<List<AppPerfStep>> findAppPerfStepList(@RequestBody @Valid @NotNull AppPerfStepQuery appPerfStepQuery){
        List<AppPerfStep> appPerfStepList = appPerfStepService.findAppPerfStepList(appPerfStepQuery);

        return Result.ok(appPerfStepList);
    }

    @RequestMapping(path = "/findAppPerfStepPage",method = RequestMethod.POST)
    @ApiMethod(name = "findAppPerfStepPage",desc = "findAppPerfStepPage")
    @ApiParam(name = "appPerfStepQuery",desc = "appPerfStepQuery",required = true)
    public Result<Pagination<AppPerfStep>> findAppPerfStepPage(@RequestBody @Valid @NotNull AppPerfStepQuery appPerfStepQuery){
        Pagination<AppPerfStep> pagination = appPerfStepService.findAppPerfStepPage(appPerfStepQuery);

        return Result.ok(pagination);
    }

}
