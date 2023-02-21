package net.tiklab.teston.apitest.http.unittest.controller;

import net.tiklab.postin.annotation.Api;
import net.tiklab.teston.apitest.http.unittest.model.ApiUnitCase;
import net.tiklab.teston.apitest.http.unittest.model.ApiUnitCaseQuery;
import net.tiklab.teston.apitest.http.unittest.service.ApiUnitCaseService;
import net.tiklab.core.page.Pagination;
import net.tiklab.core.Result;
import net.tiklab.postin.annotation.ApiMethod;
import net.tiklab.postin.annotation.ApiParam;
import net.tiklab.teston.testcase.model.TestCaseQuery;
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
 * ApiUnitCaseController
 */
@RestController
@RequestMapping("/apiUnitCase")
@Api(name = "ApiUnitCaseController",desc = "api单元测试用例管理")
public class ApiUnitCaseController {

    private static Logger logger = LoggerFactory.getLogger(ApiUnitCaseController.class);

    @Autowired
    private ApiUnitCaseService apiUnitCaseService;

    @RequestMapping(path="/createApiUnitCase",method = RequestMethod.POST)
    @ApiMethod(name = "createApiUnitCase",desc = "createApiUnitCase")
    @ApiParam(name = "path",desc = "path",required = true)
    public Result<String> createApiUnitCase(@RequestBody @NotNull @Valid ApiUnitCase apiUnitCase){
        String id = apiUnitCaseService.createApiUnitCase(apiUnitCase);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateApiUnitCase",method = RequestMethod.POST)
    @ApiMethod(name = "updateApiUnitCase",desc = "updateApiUnitCase")
    @ApiParam(name = "apiUnitCase",desc = "apiUnitCase",required = true)
    public Result<Void> updateApiUnitCase(@RequestBody @NotNull @Valid ApiUnitCase apiUnitCase){
        apiUnitCaseService.updateApiUnitCase(apiUnitCase);

        return Result.ok();
    }

    @RequestMapping(path="/deleteApiUnitCase",method = RequestMethod.POST)
    @ApiMethod(name = "deleteApiUnitCase",desc = "deleteApiUnitCase")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteApiUnitCase(@NotNull String id){
        apiUnitCaseService.deleteApiUnitCase(id);

        return Result.ok();
    }

    @RequestMapping(path="/findApiUnitCase",method = RequestMethod.POST)
    @ApiMethod(name = "findApiUnitCase",desc = "findApiUnitCase")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<ApiUnitCase> findApiUnitCase(@NotNull String id){
        ApiUnitCase apiUnitCase = apiUnitCaseService.findApiUnitCase(id);

        return Result.ok(apiUnitCase);
    }

    @RequestMapping(path="/findAllApiUnitCase",method = RequestMethod.POST)
    @ApiMethod(name = "findAllApiUnitCase",desc = "findAllApiUnitCase")
    public Result<List<ApiUnitCase>> findAllApiUnitCase(){
        List<ApiUnitCase> apiUnitCaseList = apiUnitCaseService.findAllApiUnitCase();

        return Result.ok(apiUnitCaseList);
    }

    @RequestMapping(path = "/findApiUnitCaseList",method = RequestMethod.POST)
    @ApiMethod(name = "findApiUnitCaseList",desc = "findApiUnitCaseList")
    @ApiParam(name = "apiUnitCaseQuery",desc = "apiUnitCaseQuery",required = true)
    public Result<List<ApiUnitCase>> findApiUnitCaseList(@RequestBody @Valid @NotNull ApiUnitCaseQuery apiUnitCaseQuery ){
        List<ApiUnitCase> apiUnitCaseList = apiUnitCaseService.findApiUnitCaseList(apiUnitCaseQuery);

        return Result.ok(apiUnitCaseList);
    }


    @RequestMapping(path = "/findApiUnitCasePage",method = RequestMethod.POST)
    @ApiMethod(name = "findApiUnitCasePage",desc = "findApiUnitCasePage")
    @ApiParam(name = "apiUnitCaseQuery",desc = "apiUnitCaseQuery",required = true)
    public Result<Pagination<ApiUnitCase>> findApiUnitCasePage(@RequestBody @Valid @NotNull ApiUnitCaseQuery apiUnitCaseQuery){
        Pagination<ApiUnitCase> pagination = apiUnitCaseService.findApiUnitCasePage(apiUnitCaseQuery);

        return Result.ok(pagination);
    }

    @RequestMapping(path = "/findApiUnitCaseListByTestCase",method = RequestMethod.POST)
    @ApiMethod(name = "findApiUnitCaseListByTestCase",desc = "findApiUnitCaseListByTestCase")
    @ApiParam(name = "testCaseQuery",desc = "testCaseQuery",required = true)
    public Result<List<ApiUnitCase>> findApiUnitCaseListByTestCase(@RequestBody @Valid @NotNull TestCaseQuery testCaseQuery ){
        List<ApiUnitCase> apiUnitCaseList = apiUnitCaseService.findApiUnitCaseListByTestCase(testCaseQuery);

        return Result.ok(apiUnitCaseList);
    }

}
