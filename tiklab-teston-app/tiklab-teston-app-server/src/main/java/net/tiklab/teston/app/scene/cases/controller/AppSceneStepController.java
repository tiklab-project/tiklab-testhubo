package net.tiklab.teston.app.scene.cases.controller;

import net.tiklab.core.Result;
import net.tiklab.core.page.Pagination;
import net.tiklab.postin.annotation.Api;
import net.tiklab.postin.annotation.ApiMethod;
import net.tiklab.postin.annotation.ApiParam;
import net.tiklab.teston.app.scene.cases.model.AppSceneStep;
import net.tiklab.teston.app.scene.cases.model.AppSceneStepQuery;
import net.tiklab.teston.app.scene.cases.service.AppSceneStepService;
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
 * app场景下步骤 控制器
 */
@RestController
@RequestMapping("/appSceneStep")
@Api(name = "AppSceneStepController",desc = "app场景下步骤管理")
public class AppSceneStepController {

    private static Logger logger = LoggerFactory.getLogger(AppSceneStepController.class);

    @Autowired
    private AppSceneStepService appSceneStepService;


    @RequestMapping(path="/createAppSceneStep",method = RequestMethod.POST)
    @ApiMethod(name = "createAppSceneStep",desc = "创建场景步骤")
    @ApiParam(name = "appSceneStep",desc = "appSceneStep",required = true)
    public Result<String> createAppSceneStep(@RequestBody @NotNull @Valid AppSceneStep appSceneStep){
        String id = appSceneStepService.createAppSceneStep(appSceneStep);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateAppSceneStep",method = RequestMethod.POST)
    @ApiMethod(name = "updateAppSceneStep",desc = "更新场景步骤")
    @ApiParam(name = "appSceneStep",desc = "appSceneStep",required = true)
    public Result<Void> updateAppSceneStep(@RequestBody @NotNull @Valid AppSceneStep appSceneStep){
        appSceneStepService.updateAppSceneStep(appSceneStep);

        return Result.ok();
    }

    @RequestMapping(path="/deleteAppSceneStep",method = RequestMethod.POST)
    @ApiMethod(name = "deleteAppSceneStep",desc = "删除场景步骤")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteAppSceneStep(@NotNull String id){
        appSceneStepService.deleteAppSceneStep(id);

        return Result.ok();
    }

    @RequestMapping(path="/findAppSceneStep",method = RequestMethod.POST)
    @ApiMethod(name = "findAppSceneStep",desc = "根据id查找场景步骤")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<AppSceneStep> findAppSceneStep(@NotNull String id){
        AppSceneStep appSceneStep = appSceneStepService.findAppSceneStep(id);

        return Result.ok(appSceneStep);
    }

    @RequestMapping(path="/findAllAppSceneStep",method = RequestMethod.POST)
    @ApiMethod(name = "findAllAppSceneStep",desc = "查找所有场景步骤")
    public Result<List<AppSceneStep>> findAllAppSceneStep(){
        List<AppSceneStep> appSceneStepList = appSceneStepService.findAllAppSceneStep();

        return Result.ok(appSceneStepList);
    }

    @RequestMapping(path = "/findAppSceneStepList",method = RequestMethod.POST)
    @ApiMethod(name = "findAppSceneStepList",desc = "根据查询参数查询场景步骤列表")
    @ApiParam(name = "appSceneStepQuery",desc = "appSceneStepQuery",required = true)
    public Result<List<AppSceneStep>> findAppSceneStepList(@RequestBody @Valid @NotNull AppSceneStepQuery appSceneStepQuery){
        List<AppSceneStep> appSceneStepList = appSceneStepService.findAppSceneStepList(appSceneStepQuery);

        return Result.ok(appSceneStepList);
    }

    @RequestMapping(path = "/findAppSceneStepPage",method = RequestMethod.POST)
    @ApiMethod(name = "findAppSceneStepPage",desc = "根据查询参数按分页查询场景步骤")
    @ApiParam(name = "appSceneStepQuery",desc = "appSceneStepQuery",required = true)
    public Result<Pagination<AppSceneStep>> findAppSceneStepPage(@RequestBody @Valid @NotNull AppSceneStepQuery appSceneStepQuery){
        Pagination<AppSceneStep> pagination = appSceneStepService.findAppSceneStepPage(appSceneStepQuery);

        return Result.ok(pagination);
    }

}
