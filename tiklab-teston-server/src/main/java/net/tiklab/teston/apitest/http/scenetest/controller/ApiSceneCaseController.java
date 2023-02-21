package net.tiklab.teston.apitest.http.scenetest.controller;

import net.tiklab.postin.annotation.Api;
import net.tiklab.postin.annotation.ApiMethod;
import net.tiklab.postin.annotation.ApiParam;
import net.tiklab.core.Result;
import net.tiklab.core.page.Pagination;
import net.tiklab.teston.apitest.http.scenetest.model.ApiSceneCase;
import net.tiklab.teston.apitest.http.scenetest.model.ApiSceneCaseQuery;
import net.tiklab.teston.apitest.http.scenetest.service.ApiSceneCaseService;
import net.tiklab.teston.testcase.model.TestCaseQuery;
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
 * ApiSceneCaseController
 */
@RestController
@RequestMapping("/apiSceneCase")
@Api(name = "ApiSceneCaseController",desc = "api场景用例管理")
public class ApiSceneCaseController {

    private static Logger logger = LoggerFactory.getLogger(ApiSceneCaseController.class);

    @Autowired
    private ApiSceneCaseService apiSceneCaseService;

    @RequestMapping(path="/createApiSceneCase",method = RequestMethod.POST)
    @ApiMethod(name = "createApiSceneCase",desc = "添加测试用例")
    @ApiParam(name = "testCase",desc = "testCase",required = true)
    public Result<String> createApiSceneCase(@RequestBody @NotNull @Valid ApiSceneCase apiSceneCase){
        String id = apiSceneCaseService.createApiSceneCase(apiSceneCase);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateApiSceneCase",method = RequestMethod.POST)
    @ApiMethod(name = "updateApiSceneCase",desc = "更新测试用例")
    @ApiParam(name = "testCase",desc = "testCase",required = true)
    public Result<Void> updateApiSceneCase(@RequestBody @NotNull @Valid ApiSceneCase apiSceneCase){
        apiSceneCaseService.updateApiSceneCase(apiSceneCase);

        return Result.ok();
    }

    @RequestMapping(path="/deleteApiSceneCase",method = RequestMethod.POST)
    @ApiMethod(name = "deleteApiSceneCase",desc = "删除测试用例")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteApiSceneCase(@NotNull String id){
        apiSceneCaseService.deleteApiSceneCase(id);

        return Result.ok();
    }

    @RequestMapping(path="/findApiSceneCase",method = RequestMethod.POST)
    @ApiMethod(name = "findApiSceneCase",desc = "通过id查询测试用例")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<ApiSceneCase> findApiSceneCase(@NotNull String id){
        ApiSceneCase apiSceneCase = apiSceneCaseService.findApiSceneCase(id);

        return Result.ok(apiSceneCase);
    }

    @RequestMapping(path="/findAllApiSceneCase",method = RequestMethod.POST)
    @ApiMethod(name = "findAllApiSceneCase",desc = "查询所有测试用例")
    public Result<List<ApiSceneCase>> findAllApiSceneCase(){
        List<ApiSceneCase> apiSceneCaseList = apiSceneCaseService.findAllApiSceneCase();

        return Result.ok(apiSceneCaseList);
    }

    @RequestMapping(path = "/findApiSceneCaseList",method = RequestMethod.POST)
    @ApiMethod(name = "findApiSceneCaseList",desc = "通过查询对象查询测试用例")
    @ApiParam(name = "apiSceneCaseQuery",desc = "apiSceneCaseQuery",required = true)
    public Result<List<ApiSceneCase>> findApiSceneCaseList( @RequestBody @Valid @NotNull ApiSceneCaseQuery apiSceneCaseQuery){
        List<ApiSceneCase> apiSceneCaseList = apiSceneCaseService.findApiSceneCaseList(apiSceneCaseQuery);

        return Result.ok(apiSceneCaseList);
    }


    @RequestMapping(path = "/findApiSceneCasePage",method = RequestMethod.POST)
    @ApiMethod(name = "findApiSceneCasePage",desc = "通过查询对象分页查询测试用例")
    @ApiParam(name = "apiSceneCaseQuery",desc = "apiSceneCaseQuery",required = true)
    public Result<Pagination<ApiSceneCase>> findApiSceneCasePage(@RequestBody @Valid @NotNull ApiSceneCaseQuery apiSceneCaseQuery){
        Pagination<ApiSceneCase> pagination = apiSceneCaseService.findApiSceneCasePage(apiSceneCaseQuery);

        return Result.ok(pagination);
    }

    @RequestMapping(path = "/findApiSceneCaseListByTestCase",method = RequestMethod.POST)
    @ApiMethod(name = "findApiSceneCaseListByTestCase",desc = "通过中间层testcase查询下面的场景用例")
    @ApiParam(name = "testCaseQuery",desc = "testCaseQuery",required = true)
    public Result<List<ApiSceneCase>> findApiSceneCaseListByTestCase(@RequestBody @Valid @NotNull TestCaseQuery testCaseQuery){
        List<ApiSceneCase> apiSceneCaseList = apiSceneCaseService.findApiSceneCaseListByTestCase(testCaseQuery);

        return Result.ok(apiSceneCaseList);
    }

}
