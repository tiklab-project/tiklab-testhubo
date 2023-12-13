package io.thoughtware.teston.test.app.perf.instance.controller;

import io.thoughtware.teston.test.app.perf.instance.mode.AppPerfInstance;
import io.thoughtware.teston.test.app.perf.instance.mode.AppPerfInstanceQuery;
import io.thoughtware.teston.test.app.perf.instance.service.AppPerfInstanceService;
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
 * app性能实例 控制器
 */
@RestController
@RequestMapping("/appPerfInstance")
@Api(name = "AppPerfInstanceController",desc = "app性能实例")
public class AppPerfInstanceController {

    private static Logger logger = LoggerFactory.getLogger(AppPerfInstanceController.class);

    @Autowired
    private AppPerfInstanceService appPerfInstanceService;

    @RequestMapping(path="/createAppPerfInstance",method = RequestMethod.POST)
    @ApiMethod(name = "createAppPerfInstance",desc = "创建性能测试汇总报告")
    @ApiParam(name = "appPerfInstance",desc = "appPerfInstance",required = true)
    public Result<String> createAppPerfInstance(@RequestBody @NotNull @Valid AppPerfInstance appPerfInstance){
        String id = appPerfInstanceService.createAppPerfInstance(appPerfInstance);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateAppPerfInstance",method = RequestMethod.POST)
    @ApiMethod(name = "updateAppPerfInstance",desc = "修改性能测试汇总报告")
    @ApiParam(name = "appPerfInstance",desc = "appPerfInstance",required = true)
    public Result<Void> updateAppPerfInstance(@RequestBody @NotNull @Valid AppPerfInstance appPerfInstance){
        appPerfInstanceService.updateAppPerfInstance(appPerfInstance);

        return Result.ok();
    }

    @RequestMapping(path="/deleteAppPerfInstance",method = RequestMethod.POST)
    @ApiMethod(name = "deleteAppPerfInstance",desc = "删除性能测试汇总报告")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteAppPerfInstance(@NotNull String id){
        appPerfInstanceService.deleteAppPerfInstance(id);

        return Result.ok();
    }

    @RequestMapping(path="/findAppPerfInstance",method = RequestMethod.POST)
    @ApiMethod(name = "findAppPerfInstance",desc = "通过id查询性能测试汇总报告")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<AppPerfInstance> findAppPerfInstance(@NotNull String id){
        AppPerfInstance appPerfInstance = appPerfInstanceService.findAppPerfInstance(id);

        return Result.ok(appPerfInstance);
    }

    @RequestMapping(path="/findAllAppPerfInstance",method = RequestMethod.POST)
    @ApiMethod(name = "findAllAppPerfInstance",desc = "查询所有性能测试汇总报告")
    public Result<List<AppPerfInstance>> findAllAppPerfInstance(){
        List<AppPerfInstance> appPerfInstanceList = appPerfInstanceService.findAllAppPerfInstance();

        return Result.ok(appPerfInstanceList);
    }

    @RequestMapping(path = "/findAppPerfInstanceList",method = RequestMethod.POST)
    @ApiMethod(name = "findAppPerfInstanceList",desc = "通过条件查询性能测试汇总报告")
    @ApiParam(name = "appPerfInstanceQuery",desc = "appPerfInstanceQuery",required = true)
    public Result<List<AppPerfInstance>> findAppPerfInstanceList(@RequestBody @Valid @NotNull AppPerfInstanceQuery appPerfInstanceQuery){
        List<AppPerfInstance> appPerfInstanceList = appPerfInstanceService.findAppPerfInstanceList(appPerfInstanceQuery);

        return Result.ok(appPerfInstanceList);
    }

    @RequestMapping(path = "/findAppPerfInstancePage",method = RequestMethod.POST)
    @ApiMethod(name = "findAppPerfInstancePage",desc = "通过条件分页查询性能测试汇总报告")
    @ApiParam(name = "appPerfInstanceQuery",desc = "appPerfInstanceQuery",required = true)
    public Result<Pagination<AppPerfInstance>> findAppPerfInstancePage(@RequestBody @Valid @NotNull AppPerfInstanceQuery appPerfInstanceQuery){
        Pagination<AppPerfInstance> pagination = appPerfInstanceService.findAppPerfInstancePage(appPerfInstanceQuery);

        return Result.ok(pagination);
    }

}