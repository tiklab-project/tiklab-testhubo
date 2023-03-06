package net.tiklab.teston.test.app.scene.instance.controller;

import net.tiklab.core.Result;
import net.tiklab.core.page.Pagination;
import net.tiklab.postin.annotation.Api;
import net.tiklab.postin.annotation.ApiMethod;
import net.tiklab.postin.annotation.ApiParam;
import net.tiklab.teston.test.app.scene.instance.model.AppSceneInstanceStep;
import net.tiklab.teston.test.app.scene.instance.model.AppSceneInstanceStepQuery;
import net.tiklab.teston.test.app.scene.instance.service.AppSceneInstanceStepService;
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
 * app场景步骤实例 控制器
 */
@RestController
@RequestMapping("/appSceneInstanceStep")
@Api(name = "AppSceneInstanceStepController",desc = "app场景步骤实例管理")
public class AppSceneInstanceStepController {

    private static Logger logger = LoggerFactory.getLogger(AppSceneInstanceStepController.class);

    @Autowired
    private AppSceneInstanceStepService appSceneInstanceStepService;

    @RequestMapping(path="/createAppSceneInstanceStep",method = RequestMethod.POST)
    @ApiMethod(name = "createAppSceneInstanceStep",desc = "创建app场景步骤实例")
    @ApiParam(name = "appSceneInstanceStep",desc = "appSceneInstanceStep",required = true)
    public Result<String> createAppSceneInstanceStep(@RequestBody @NotNull @Valid AppSceneInstanceStep appSceneInstanceStep){
        String id = appSceneInstanceStepService.createAppSceneInstanceStep(appSceneInstanceStep);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateAppSceneInstanceStep",method = RequestMethod.POST)
    @ApiMethod(name = "updateAppSceneInstanceStep",desc = "更新app场景步骤实例")
    @ApiParam(name = "appSceneInstanceStep",desc = "appSceneInstanceStep",required = true)
    public Result<Void> updateAppSceneInstanceStep(@RequestBody @NotNull @Valid AppSceneInstanceStep appSceneInstanceStep){
        appSceneInstanceStepService.updateAppSceneInstanceStep(appSceneInstanceStep);

        return Result.ok();
    }

    @RequestMapping(path="/deleteAppSceneInstanceStep",method = RequestMethod.POST)
    @ApiMethod(name = "deleteAppSceneInstanceStep",desc = "删除app场景步骤实例")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteAppSceneInstanceStep(@NotNull String id){
        appSceneInstanceStepService.deleteAppSceneInstanceStep(id);

        return Result.ok();
    }

    @RequestMapping(path="/findAppSceneInstanceStep",method = RequestMethod.POST)
    @ApiMethod(name = "findAppSceneInstanceStep",desc = "根据id查找app场景步骤实例")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<AppSceneInstanceStep> findAppSceneInstanceStep(@NotNull String id){
        AppSceneInstanceStep appSceneInstanceStep = appSceneInstanceStepService.findAppSceneInstanceStep(id);

        return Result.ok(appSceneInstanceStep);
    }

    @RequestMapping(path="/findAllAppSceneInstanceStep",method = RequestMethod.POST)
    @ApiMethod(name = "findAllAppSceneInstanceStep",desc = "根据查询参数查找所有app场景步骤实例")
    public Result<List<AppSceneInstanceStep>> findAllAppSceneInstanceStep(){
        List<AppSceneInstanceStep> appSceneInstanceStepList = appSceneInstanceStepService.findAllAppSceneInstanceStep();

        return Result.ok(appSceneInstanceStepList);
    }

    @RequestMapping(path = "/findAppSceneInstanceStepList",method = RequestMethod.POST)
    @ApiMethod(name = "findAppSceneInstanceStepList",desc = "根据查询参数查询app场景步骤实例列表")
    @ApiParam(name = "appSceneInstanceStepQuery",desc = "appSceneInstanceStepQuery",required = true)
    public Result<List<AppSceneInstanceStep>> findAppSceneInstanceStepList(@RequestBody @Valid @NotNull AppSceneInstanceStepQuery appSceneInstanceStepQuery){
        List<AppSceneInstanceStep> appSceneInstanceStepList = appSceneInstanceStepService.findAppSceneInstanceStepList(appSceneInstanceStepQuery);

        return Result.ok(appSceneInstanceStepList);
    }

    @RequestMapping(path = "/findAppSceneInstanceStepPage",method = RequestMethod.POST)
    @ApiMethod(name = "findAppSceneInstanceStepPage",desc = "按分页查询app场景步骤实例")
    @ApiParam(name = "appSceneInstanceStepQuery",desc = "appSceneInstanceStepQuery",required = true)
    public Result<Pagination<AppSceneInstanceStep>> findAppSceneInstanceStepPage(@RequestBody @Valid @NotNull AppSceneInstanceStepQuery appSceneInstanceStepQuery){
        Pagination<AppSceneInstanceStep> pagination = appSceneInstanceStepService.findAppSceneInstanceStepPage(appSceneInstanceStepQuery);

        return Result.ok(pagination);
    }

}
