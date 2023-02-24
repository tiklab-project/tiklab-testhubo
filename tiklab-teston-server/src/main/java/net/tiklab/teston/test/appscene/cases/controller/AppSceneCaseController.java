package net.tiklab.teston.test.appscene.cases.controller;

import net.tiklab.postin.annotation.Api;
import net.tiklab.core.Result;
import net.tiklab.core.page.Pagination;
import net.tiklab.teston.test.appscene.cases.model.AppSceneCase;
import net.tiklab.teston.test.appscene.cases.model.AppSceneCaseQuery;
import net.tiklab.teston.test.appscene.cases.service.AppSceneCaseService;
import net.tiklab.postin.annotation.ApiMethod;
import net.tiklab.postin.annotation.ApiParam;
import net.tiklab.teston.test.testcase.model.TestCaseQuery;
import net.tiklab.teston.test.webscene.cases.model.WebSceneCase;
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
 * AppSceneCaseController
 */
@RestController
@RequestMapping("/appSceneCase")
@Api(name = "AppSceneCaseController",desc = "AppSceneCaseController")
public class AppSceneCaseController {

    private static Logger logger = LoggerFactory.getLogger(AppSceneCaseController.class);

    @Autowired
    private AppSceneCaseService appSceneCaseService;

    @RequestMapping(path="/createAppSceneCase",method = RequestMethod.POST)
    @ApiMethod(name = "createAppSceneCase",desc = "createAppSceneCase")
    @ApiParam(name = "appSceneCase",desc = "appSceneCase",required = true)
    public Result<String> createAppSceneCase(@RequestBody @NotNull @Valid AppSceneCase appSceneCase){
        String id = appSceneCaseService.createAppSceneCase(appSceneCase);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateAppSceneCase",method = RequestMethod.POST)
    @ApiMethod(name = "updateAppSceneCase",desc = "updateAppSceneCase")
    @ApiParam(name = "appSceneCase",desc = "appSceneCase",required = true)
    public Result<Void> updateAppSceneCase(@RequestBody @NotNull @Valid AppSceneCase appSceneCase){
        appSceneCaseService.updateAppSceneCase(appSceneCase);

        return Result.ok();
    }

    @RequestMapping(path="/deleteAppSceneCase",method = RequestMethod.POST)
    @ApiMethod(name = "deleteAppSceneCase",desc = "deleteAppSceneCase")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteAppSceneCase(@NotNull String id){
        appSceneCaseService.deleteAppSceneCase(id);

        return Result.ok();
    }

    @RequestMapping(path="/findAppSceneCase",method = RequestMethod.POST)
    @ApiMethod(name = "findAppSceneCase",desc = "findAppSceneCase")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<AppSceneCase> findAppSceneCase(@NotNull String id){
        AppSceneCase appSceneCase = appSceneCaseService.findAppSceneCase(id);

        return Result.ok(appSceneCase);
    }

    @RequestMapping(path="/findAllAppSceneCase",method = RequestMethod.POST)
    @ApiMethod(name = "findAllAppSceneCase",desc = "findAllAppSceneCase")
    public Result<List<AppSceneCase>> findAllAppSceneCase(){
        List<AppSceneCase> appSceneCaseList = appSceneCaseService.findAllAppSceneCase();

        return Result.ok(appSceneCaseList);
    }

    @RequestMapping(path = "/findAppSceneCaseList",method = RequestMethod.POST)
    @ApiMethod(name = "findAppSceneCaseList",desc = "findAppSceneCaseList")
    @ApiParam(name = "appSceneCaseQuery",desc = "appSceneCaseQuery",required = true)
    public Result<List<AppSceneCase>> findAppSceneCaseList(@RequestBody @Valid @NotNull AppSceneCaseQuery appSceneCaseQuery){
        List<AppSceneCase> appSceneCaseList = appSceneCaseService.findAppSceneCaseList(appSceneCaseQuery);

        return Result.ok(appSceneCaseList);
    }

    @RequestMapping(path = "/findAppSceneCasePage",method = RequestMethod.POST)
    @ApiMethod(name = "findAppSceneCasePage",desc = "findAppSceneCasePage")
    @ApiParam(name = "appSceneCaseQuery",desc = "appSceneCaseQuery",required = true)
    public Result<Pagination<AppSceneCase>> findAppSceneCasePage(@RequestBody @Valid @NotNull AppSceneCaseQuery appSceneCaseQuery){
        Pagination<AppSceneCase> pagination = appSceneCaseService.findAppSceneCasePage(appSceneCaseQuery);

        return Result.ok(pagination);
    }


    @RequestMapping(path = "/findAppSceneCaseListByTestCase",method = RequestMethod.POST)
    @ApiMethod(name = "findAppSceneCaseListByTestCase",desc = "通过中间层testcase查询下面的场景用例")
    @ApiParam(name = "testCaseQuery",desc = "testCaseQuery",required = true)
    public Result<List<WebSceneCase>> findAppSceneCaseListByTestCase(@RequestBody @Valid @NotNull TestCaseQuery testCaseQuery){
        List<AppSceneCase> appSceneCaseList = appSceneCaseService.findAppSceneCaseListByTestCase(testCaseQuery);

        return Result.ok(appSceneCaseList);
    }


}
