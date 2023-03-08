package io.tiklab.teston.test.app.perf.cases.controller;

import io.tiklab.core.Result;
import io.tiklab.core.page.Pagination;
import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.annotation.ApiMethod;
import io.tiklab.postin.annotation.ApiParam;

import io.tiklab.teston.test.test.model.TestCaseQuery;
import io.tiklab.teston.test.app.perf.cases.model.AppPerfCase;
import io.tiklab.teston.test.app.perf.cases.model.AppPerfCaseQuery;
import io.tiklab.teston.test.app.perf.cases.service.AppPerfCaseService;
import io.tiklab.teston.test.web.perf.cases.model.WebPerfCase;
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
 * app性能测试用例 控制器
 */
@RestController
@RequestMapping("/appPerfCase")
@Api(name = "AppPerfCaseController",desc = "app性能测试用例管理")
public class AppPerfCaseController {

    private static Logger logger = LoggerFactory.getLogger(AppPerfCaseController.class);

    @Autowired
    private AppPerfCaseService appPerfCaseService;

    @RequestMapping(path="/createAppPerfCase",method = RequestMethod.POST)
    @ApiMethod(name = "createAppPerfCase",desc = "创建app性能测试用例")
    @ApiParam(name = "appPerfCase",desc = "appPerfCase",required = true)
    public Result<String> createAppPerfCase(@RequestBody @NotNull @Valid AppPerfCase appPerfCase){
        String id = appPerfCaseService.createAppPerfCase(appPerfCase);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateAppPerfCase",method = RequestMethod.POST)
    @ApiMethod(name = "updateAppPerfCase",desc = "更新app性能测试用例")
    @ApiParam(name = "appPerfCase",desc = "appPerfCase",required = true)
    public Result<Void> updateAppPerfCase(@RequestBody @NotNull @Valid AppPerfCase appPerfCase){
        appPerfCaseService.updateAppPerfCase(appPerfCase);

        return Result.ok();
    }

    @RequestMapping(path="/deleteAppPerfCase",method = RequestMethod.POST)
    @ApiMethod(name = "deleteAppPerfCase",desc = "删除app性能测试用例")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteAppPerfCase(@NotNull String id){
        appPerfCaseService.deleteAppPerfCase(id);

        return Result.ok();
    }

    @RequestMapping(path="/findAppPerfCase",method = RequestMethod.POST)
    @ApiMethod(name = "findAppPerfCase",desc = "根据id查找app性能测试用例")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<AppPerfCase> findAppPerfCase(@NotNull String id){
        AppPerfCase appPerfCase = appPerfCaseService.findAppPerfCase(id);

        return Result.ok(appPerfCase);
    }

    @RequestMapping(path="/findAllAppPerfCase",method = RequestMethod.POST)
    @ApiMethod(name = "findAllAppPerfCase",desc = "查找所有app性能测试用例")
    public Result<List<AppPerfCase>> findAllAppPerfCase(){
        List<AppPerfCase> appPerfCaseList = appPerfCaseService.findAllAppPerfCase();

        return Result.ok(appPerfCaseList);
    }

    @RequestMapping(path = "/findAppPerfCaseList",method = RequestMethod.POST)
    @ApiMethod(name = "findAppPerfCaseList",desc = "根据查询参数查询查询app性能测试用例列表")
    @ApiParam(name = "appPerfCaseQuery",desc = "appPerfCaseQuery",required = true)
    public Result<List<AppPerfCase>> findAppPerfCaseList(@RequestBody @Valid @NotNull AppPerfCaseQuery appPerfCaseQuery){
        List<AppPerfCase> appPerfCaseList = appPerfCaseService.findAppPerfCaseList(appPerfCaseQuery);

        return Result.ok(appPerfCaseList);
    }

    @RequestMapping(path = "/findAppPerfCasePage",method = RequestMethod.POST)
    @ApiMethod(name = "findAppPerfCasePage",desc = "根据查询参数查询按分页查询app性能测试用例")
    @ApiParam(name = "appPerfCaseQuery",desc = "appPerfCaseQuery",required = true)
    public Result<Pagination<AppPerfCase>> findAppPerfCasePage(@RequestBody @Valid @NotNull AppPerfCaseQuery appPerfCaseQuery){
        Pagination<AppPerfCase> pagination = appPerfCaseService.findAppPerfCasePage(appPerfCaseQuery);

        return Result.ok(pagination);
    }

    @RequestMapping(path = "/findAppPerfCaseListByTestCase",method = RequestMethod.POST)
    @ApiMethod(name = "findAppPerfCaseListByTestCase",desc = "通过中间层testcase查询下面的场景用例")
    @ApiParam(name = "testCaseQuery",desc = "testCaseQuery",required = true)
    public Result<List<WebPerfCase>> findWebPerfCaseListByTestCase(@RequestBody @Valid @NotNull TestCaseQuery testCaseQuery){
        List<AppPerfCase> appPerfCaseList = appPerfCaseService.findAppPerfCaseListByTestCase(testCaseQuery);

        return  Result.ok(appPerfCaseList);
    }


}
