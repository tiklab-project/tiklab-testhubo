package net.tiklab.teston.test.appscene.cases.controller;

import net.tiklab.postin.annotation.Api;
import net.tiklab.core.Result;
import net.tiklab.core.page.Pagination;
import net.tiklab.teston.test.appscene.cases.model.AppSceneStep;
import net.tiklab.teston.test.appscene.cases.model.AppSceneStepQuery;
import net.tiklab.teston.test.appscene.cases.service.AppSceneStepService;
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
 * AppSceneStepController
 */
@RestController
@RequestMapping("/appSceneStep")
@Api(name = "AppSceneStepController",desc = "AppSceneStepController")
public class AppSceneStepController {

    private static Logger logger = LoggerFactory.getLogger(AppSceneStepController.class);

    @Autowired
    private AppSceneStepService appSceneStepService;

    @RequestMapping(path="/bindAppUnit",method = RequestMethod.POST)
    @ApiMethod(name = "bindAppUnit",desc = "bindAppUnit")
    @ApiParam(name = "appSceneStep",desc = "appSceneStep",required = true)
    public Result<String> bindAppUnit(@RequestBody @NotNull @Valid List<AppSceneStep> appSceneStepList){
        appSceneStepService.bindAppUnit(appSceneStepList);

        return Result.ok();
    }


    @RequestMapping(path="/createAppSceneStep",method = RequestMethod.POST)
    @ApiMethod(name = "createAppSceneStep",desc = "createAppSceneStep")
    @ApiParam(name = "appSceneStep",desc = "appSceneStep",required = true)
    public Result<String> createAppSceneStep(@RequestBody @NotNull @Valid AppSceneStep appSceneStep){
        String id = appSceneStepService.createAppSceneStep(appSceneStep);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateAppSceneStep",method = RequestMethod.POST)
    @ApiMethod(name = "updateAppSceneStep",desc = "updateAppSceneStep")
    @ApiParam(name = "appSceneStep",desc = "appSceneStep",required = true)
    public Result<Void> updateAppSceneStep(@RequestBody @NotNull @Valid AppSceneStep appSceneStep){
        appSceneStepService.updateAppSceneStep(appSceneStep);

        return Result.ok();
    }

    @RequestMapping(path="/deleteAppSceneStep",method = RequestMethod.POST)
    @ApiMethod(name = "deleteAppSceneStep",desc = "deleteAppSceneStep")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteAppSceneStep(@NotNull String id){
        appSceneStepService.deleteAppSceneStep(id);

        return Result.ok();
    }

    @RequestMapping(path="/findAppSceneStep",method = RequestMethod.POST)
    @ApiMethod(name = "findAppSceneStep",desc = "findAppSceneStep")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<AppSceneStep> findAppSceneStep(@NotNull String id){
        AppSceneStep appSceneStep = appSceneStepService.findAppSceneStep(id);

        return Result.ok(appSceneStep);
    }

    @RequestMapping(path="/findAllAppSceneStep",method = RequestMethod.POST)
    @ApiMethod(name = "findAllAppSceneStep",desc = "findAllAppSceneStep")
    public Result<List<AppSceneStep>> findAllAppSceneStep(){
        List<AppSceneStep> appSceneStepList = appSceneStepService.findAllAppSceneStep();

        return Result.ok(appSceneStepList);
    }

    @RequestMapping(path = "/findAppSceneStepList",method = RequestMethod.POST)
    @ApiMethod(name = "findAppSceneStepList",desc = "findAppSceneStepList")
    @ApiParam(name = "appSceneStepQuery",desc = "appSceneStepQuery",required = true)
    public Result<List<AppSceneStep>> findAppSceneStepList(@RequestBody @Valid @NotNull AppSceneStepQuery appSceneStepQuery){
        List<AppSceneStep> appSceneStepList = appSceneStepService.findAppSceneStepList(appSceneStepQuery);

        return Result.ok(appSceneStepList);
    }

    @RequestMapping(path = "/findAppSceneStepPage",method = RequestMethod.POST)
    @ApiMethod(name = "findAppSceneStepPage",desc = "findAppSceneStepPage")
    @ApiParam(name = "appSceneStepQuery",desc = "appSceneStepQuery",required = true)
    public Result<Pagination<AppSceneStep>> findAppSceneStepPage(@RequestBody @Valid @NotNull AppSceneStepQuery appSceneStepQuery){
        Pagination<AppSceneStep> pagination = appSceneStepService.findAppSceneStepPage(appSceneStepQuery);

        return Result.ok(pagination);
    }

}
