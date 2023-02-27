package net.tiklab.teston.app.perf.cases.controller;

import net.tiklab.core.Result;
import net.tiklab.core.page.Pagination;
import net.tiklab.postin.annotation.Api;
import net.tiklab.postin.annotation.ApiMethod;
import net.tiklab.postin.annotation.ApiParam;

import net.tiklab.teston.manager.testcase.model.TestCaseQuery;
import net.tiklab.teston.app.perf.cases.model.AppPerfCase;
import net.tiklab.teston.app.perf.cases.model.AppPerfCaseQuery;
import net.tiklab.teston.app.perf.cases.service.AppPerfCaseService;
import net.tiklab.teston.web.perf.cases.model.WebPerfCase;
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
 * AppPerfCaseController
 */
@RestController
@RequestMapping("/appPerfCase")
@Api(name = "AppPerfCaseController",desc = "AppPerfCaseController")
public class AppPerfCaseController {

    private static Logger logger = LoggerFactory.getLogger(AppPerfCaseController.class);

    @Autowired
    private AppPerfCaseService appPerfCaseService;

    @RequestMapping(path="/createAppPerfCase",method = RequestMethod.POST)
    @ApiMethod(name = "createAppPerfCase",desc = "createAppPerfCase")
    @ApiParam(name = "appPerfCase",desc = "appPerfCase",required = true)
    public Result<String> createAppPerfCase(@RequestBody @NotNull @Valid AppPerfCase appPerfCase){
        String id = appPerfCaseService.createAppPerfCase(appPerfCase);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateAppPerfCase",method = RequestMethod.POST)
    @ApiMethod(name = "updateAppPerfCase",desc = "updateAppPerfCase")
    @ApiParam(name = "appPerfCase",desc = "appPerfCase",required = true)
    public Result<Void> updateAppPerfCase(@RequestBody @NotNull @Valid AppPerfCase appPerfCase){
        appPerfCaseService.updateAppPerfCase(appPerfCase);

        return Result.ok();
    }

    @RequestMapping(path="/deleteAppPerfCase",method = RequestMethod.POST)
    @ApiMethod(name = "deleteAppPerfCase",desc = "deleteAppPerfCase")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteAppPerfCase(@NotNull String id){
        appPerfCaseService.deleteAppPerfCase(id);

        return Result.ok();
    }

    @RequestMapping(path="/findAppPerfCase",method = RequestMethod.POST)
    @ApiMethod(name = "findAppPerfCase",desc = "findAppPerfCase")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<AppPerfCase> findAppPerfCase(@NotNull String id){
        AppPerfCase appPerfCase = appPerfCaseService.findAppPerfCase(id);

        return Result.ok(appPerfCase);
    }

    @RequestMapping(path="/findAllAppPerfCase",method = RequestMethod.POST)
    @ApiMethod(name = "findAllAppPerfCase",desc = "findAllAppPerfCase")
    public Result<List<AppPerfCase>> findAllAppPerfCase(){
        List<AppPerfCase> appPerfCaseList = appPerfCaseService.findAllAppPerfCase();

        return Result.ok(appPerfCaseList);
    }

    @RequestMapping(path = "/findAppPerfCaseList",method = RequestMethod.POST)
    @ApiMethod(name = "findAppPerfCaseList",desc = "findAppPerfCaseList")
    @ApiParam(name = "appPerfCaseQuery",desc = "appPerfCaseQuery",required = true)
    public Result<List<AppPerfCase>> findAppPerfCaseList(@RequestBody @Valid @NotNull AppPerfCaseQuery appPerfCaseQuery){
        List<AppPerfCase> appPerfCaseList = appPerfCaseService.findAppPerfCaseList(appPerfCaseQuery);

        return Result.ok(appPerfCaseList);
    }

    @RequestMapping(path = "/findAppPerfCasePage",method = RequestMethod.POST)
    @ApiMethod(name = "findAppPerfCasePage",desc = "findAppPerfCasePage")
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
