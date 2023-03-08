package io.tiklab.teston.test.web.scene.cases.controller;

import io.tiklab.core.Result;
import io.tiklab.core.page.Pagination;
import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.annotation.ApiMethod;
import io.tiklab.postin.annotation.ApiParam;
import io.tiklab.teston.test.test.model.TestCaseQuery;
import io.tiklab.teston.test.web.scene.cases.model.WebSceneCase;
import io.tiklab.teston.test.web.scene.cases.model.WebSceneCaseQuery;
import io.tiklab.teston.test.web.scene.cases.service.WebSceneCaseService;
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
 * web场景用例 控制器
 */
@RestController
@RequestMapping("/webSceneCase")
@Api(name = "WebSceneCaseController",desc = "web场景测试用例")
public class WebSceneCaseController {

    private static Logger logger = LoggerFactory.getLogger(WebSceneCaseController.class);

    @Autowired
    private WebSceneCaseService webSceneCaseService;

    @RequestMapping(path="/createWebSceneCase",method = RequestMethod.POST)
    @ApiMethod(name = "createWebSceneCase",desc = "添加测试用例")
    @ApiParam(name = "webSceneCase",desc = "webSceneCase",required = true)
    public Result<String> createWebSceneCase(@RequestBody @NotNull @Valid WebSceneCase webSceneCase){
        String id = webSceneCaseService.createWebSceneCase(webSceneCase);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateWebSceneCase",method = RequestMethod.POST)
    @ApiMethod(name = "updateWebSceneCase",desc = "更新测试用例")
    @ApiParam(name = "webSceneCase",desc = "webSceneCase",required = true)
    public Result<Void> updateWebSceneCase(@RequestBody @NotNull @Valid WebSceneCase webSceneCase){
        webSceneCaseService.updateWebSceneCase(webSceneCase);

        return Result.ok();
    }

    @RequestMapping(path="/deleteWebSceneCase",method = RequestMethod.POST)
    @ApiMethod(name = "deleteWebSceneCase",desc = "删除测试用例")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteWebSceneCase(@NotNull String id){
        webSceneCaseService.deleteWebSceneCase(id);

        return Result.ok();
    }

    @RequestMapping(path="/findWebSceneCase",method = RequestMethod.POST)
    @ApiMethod(name = "findWebSceneCase",desc = "通过id查询测试用例")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<WebSceneCase> findWebSceneCase(@NotNull String id){
        WebSceneCase webSceneCase = webSceneCaseService.findWebSceneCase(id);

        return Result.ok(webSceneCase);
    }

    @RequestMapping(path="/findAllWebSceneCase",method = RequestMethod.POST)
    @ApiMethod(name = "findAllWebSceneCase",desc = "查询所有测试用例")
    public Result<List<WebSceneCase>> findAllWebSceneCase(){
        List<WebSceneCase> webSceneCaseList = webSceneCaseService.findAllWebSceneCase();

        return Result.ok(webSceneCaseList);
    }

    @RequestMapping(path = "/findWebSceneCaseList",method = RequestMethod.POST)
    @ApiMethod(name = "findWebSceneCaseList",desc = "通过查询对象查询测试用例")
    @ApiParam(name = "webSceneCaseQuery",desc = "webSceneCaseQuery",required = true)
    public Result<List<WebSceneCase>> findWebSceneCaseList(@RequestBody @Valid @NotNull WebSceneCaseQuery webSceneCaseQuery){
        List<WebSceneCase> webSceneCaseList = webSceneCaseService.findWebSceneCaseList(webSceneCaseQuery);

        return Result.ok(webSceneCaseList);
    }


    @RequestMapping(path = "/findWebSceneCasePage",method = RequestMethod.POST)
    @ApiMethod(name = "findWebSceneCasePage",desc = "通过查询对象分页查询测试用例")
    @ApiParam(name = "webSceneCaseQuery",desc = "webSceneCaseQuery",required = true)
    public Result<Pagination<WebSceneCase>> findWebSceneCasePage(@RequestBody @Valid @NotNull WebSceneCaseQuery webSceneCaseQuery){
        Pagination<WebSceneCase> pagination = webSceneCaseService.findWebSceneCasePage(webSceneCaseQuery);

        return Result.ok(pagination);
    }

    @RequestMapping(path = "/findWebSceneCaseListByTestCase",method = RequestMethod.POST)
    @ApiMethod(name = "findWebSceneCaseListByTestCase",desc = "通过中间层testcase查询下面的场景用例")
    @ApiParam(name = "testCaseQuery",desc = "testCaseQuery",required = true)
    public Result<List<WebSceneCase>> findWebSceneCaseListByTestCase(@RequestBody @Valid @NotNull TestCaseQuery testCaseQuery){
        List<WebSceneCase> webSceneCaseList = webSceneCaseService.findWebSceneCaseListByTestCase(testCaseQuery);

        return Result.ok(webSceneCaseList);
    }


}
