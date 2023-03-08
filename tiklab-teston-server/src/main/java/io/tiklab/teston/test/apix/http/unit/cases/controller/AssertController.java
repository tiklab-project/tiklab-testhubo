package io.tiklab.teston.test.apix.http.unit.cases.controller;

import io.tiklab.postin.annotation.Api;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.Result;
import io.tiklab.postin.annotation.ApiMethod;
import io.tiklab.postin.annotation.ApiParam;
import io.tiklab.teston.test.apix.http.unit.cases.model.AssertCase;
import io.tiklab.teston.test.apix.http.unit.cases.model.AssertCaseQuery;
import io.tiklab.teston.test.apix.http.unit.cases.service.AssertService;
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
 * 断言 控住器
 */
@RestController
@RequestMapping("/assertParam")
@Api(name = "AssertCaseController",desc = "接口用例步骤断言管理")
public class AssertController {

    private static Logger logger = LoggerFactory.getLogger(AssertController.class);

    @Autowired
    private AssertService assertCaseService;

    @RequestMapping(path="/createAssertParam",method = RequestMethod.POST)
    @ApiMethod(name = "createAssertCase",desc = "创建断言")
    @ApiParam(name = "assertCase",desc = "assertCase",required = true)
    public Result<String> createAssertCase(@RequestBody @NotNull @Valid AssertCase assertCase){
        String id = assertCaseService.createAssertCase(assertCase);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateAssertParam",method = RequestMethod.POST)
    @ApiMethod(name = "updateAssertCase",desc = "更新断言")
    @ApiParam(name = "assertCase",desc = "assertCase",required = true)
    public Result<Void> updateAssertCase(@RequestBody @NotNull @Valid AssertCase assertCase){
        assertCaseService.updateAssertCase(assertCase);

        return Result.ok();
    }

    @RequestMapping(path="/deleteAssertParam",method = RequestMethod.POST)
    @ApiMethod(name = "deleteAssertCase",desc = "删除断言")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteAssertCase(@NotNull String id){
        assertCaseService.deleteAssertCase(id);

        return Result.ok();
    }

    @RequestMapping(path="/findAssertParam",method = RequestMethod.POST)
    @ApiMethod(name = "findAssertCase",desc = "通过id查找断言")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<AssertCase> findAssertCase(@NotNull String id){
        AssertCase assertCase = assertCaseService.findAssertCase(id);

        return Result.ok(assertCase);
    }

    @RequestMapping(path="/findAllAssertParam",method = RequestMethod.POST)
    @ApiMethod(name = "findAllAssertCase",desc = "查找所有断言")
    public Result<List<AssertCase>> findAllAssertCase(){
        List<AssertCase> assertCaseList = assertCaseService.findAllAssertCase();

        return Result.ok(assertCaseList);
    }

    @RequestMapping(path = "/findAssertParamList",method = RequestMethod.POST)
    @ApiMethod(name = "findAssertCaseList",desc = "查询断言列表")
    @ApiParam(name = "assertCaseQuery",desc = "assertCaseQuery",required = true)
    public Result<List<AssertCase>> findAssertCaseList(@RequestBody @Valid @NotNull AssertCaseQuery assertCaseQuery){
        List<AssertCase> assertCaseList = assertCaseService.findAssertCaseList(assertCaseQuery);

        return Result.ok(assertCaseList);
    }

    @RequestMapping(path = "/findAssertParamPage",method = RequestMethod.POST)
    @ApiMethod(name = "findAssertCasePage",desc = "按分页查询断言")
    @ApiParam(name = "assertCaseQuery",desc = "assertCaseQuery",required = true)
    public Result<Pagination<AssertCase>> findAssertCasePage(@RequestBody @Valid @NotNull AssertCaseQuery assertCaseQuery){
        Pagination<AssertCase> pagination = assertCaseService.findAssertCasePage(assertCaseQuery);

        return Result.ok(pagination);
    }

}
