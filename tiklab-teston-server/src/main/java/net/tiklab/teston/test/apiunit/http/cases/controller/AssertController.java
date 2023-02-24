package net.tiklab.teston.test.apiunit.http.cases.controller;

import net.tiklab.postin.annotation.Api;
import net.tiklab.teston.test.apiunit.http.cases.model.AssertCase;
import net.tiklab.teston.test.apiunit.http.cases.model.AssertCaseQuery;
import net.tiklab.teston.test.apiunit.http.cases.service.AssertService;
import net.tiklab.core.page.Pagination;
import net.tiklab.core.Result;
import net.tiklab.postin.annotation.ApiMethod;
import net.tiklab.postin.annotation.ApiParam;
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
 * AssertCaseControllera
 */
@RestController
@RequestMapping("/assertParam")
@Api(name = "AssertCaseController",desc = "接口用例步骤断言管理")
public class AssertController {

    private static Logger logger = LoggerFactory.getLogger(AssertController.class);

    @Autowired
    private AssertService assertCaseService;

    @RequestMapping(path="/createAssertParam",method = RequestMethod.POST)
    @ApiMethod(name = "createAssertCase",desc = "createAssertCase")
    @ApiParam(name = "assertCase",desc = "assertCase",required = true)
    public Result<String> createAssertCase(@RequestBody @NotNull @Valid AssertCase assertCase){
        String id = assertCaseService.createAssertCase(assertCase);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateAssertParam",method = RequestMethod.POST)
    @ApiMethod(name = "updateAssertCase",desc = "updateAssertCase")
    @ApiParam(name = "assertCase",desc = "assertCase",required = true)
    public Result<Void> updateAssertCase(@RequestBody @NotNull @Valid AssertCase assertCase){
        assertCaseService.updateAssertCase(assertCase);

        return Result.ok();
    }

    @RequestMapping(path="/deleteAssertParam",method = RequestMethod.POST)
    @ApiMethod(name = "deleteAssertCase",desc = "deleteAssertCase")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteAssertCase(@NotNull String id){
        assertCaseService.deleteAssertCase(id);

        return Result.ok();
    }

    @RequestMapping(path="/findAssertParam",method = RequestMethod.POST)
    @ApiMethod(name = "findAssertCase",desc = "findAssertCase")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<AssertCase> findAssertCase(@NotNull String id){
        AssertCase assertCase = assertCaseService.findAssertCase(id);

        return Result.ok(assertCase);
    }

    @RequestMapping(path="/findAllAssertParam",method = RequestMethod.POST)
    @ApiMethod(name = "findAllAssertCase",desc = "findAllAssertCase")
    public Result<List<AssertCase>> findAllAssertCase(){
        List<AssertCase> assertCaseList = assertCaseService.findAllAssertCase();

        return Result.ok(assertCaseList);
    }

    @RequestMapping(path = "/findAssertParamList",method = RequestMethod.POST)
    @ApiMethod(name = "findAssertCaseList",desc = "findAssertCaseList")
    @ApiParam(name = "assertCaseQuery",desc = "assertCaseQuery",required = true)
    public Result<List<AssertCase>> findAssertCaseList(@RequestBody @Valid @NotNull AssertCaseQuery assertCaseQuery){
        List<AssertCase> assertCaseList = assertCaseService.findAssertCaseList(assertCaseQuery);

        return Result.ok(assertCaseList);
    }

    @RequestMapping(path = "/findAssertParamPage",method = RequestMethod.POST)
    @ApiMethod(name = "findAssertCasePage",desc = "findAssertCasePage")
    @ApiParam(name = "assertCaseQuery",desc = "assertCaseQuery",required = true)
    public Result<Pagination<AssertCase>> findAssertCasePage(@RequestBody @Valid @NotNull AssertCaseQuery assertCaseQuery){
        Pagination<AssertCase> pagination = assertCaseService.findAssertCasePage(assertCaseQuery);

        return Result.ok(pagination);
    }

}
