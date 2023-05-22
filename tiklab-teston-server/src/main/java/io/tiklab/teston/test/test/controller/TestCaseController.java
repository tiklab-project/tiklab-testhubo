package io.tiklab.teston.test.test.controller;

import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.annotation.ApiMethod;
import io.tiklab.postin.annotation.ApiParam;
import io.tiklab.core.Result;
import io.tiklab.core.page.Pagination;
import io.tiklab.teston.test.test.model.TestCases;
import io.tiklab.teston.test.test.model.TestCaseQuery;
import io.tiklab.teston.test.test.service.TestCaseService;
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
 * 测试用例 控制器
 */
@RestController
@RequestMapping("/testCase")
@Api(name = "TestCaseController",desc = "测试用例管理")
public class TestCaseController {

    private static Logger logger = LoggerFactory.getLogger(TestCaseController.class);

    @Autowired
    private TestCaseService testCaseService;

    @RequestMapping(path="/createTestCase",method = RequestMethod.POST)
    @ApiMethod(name = "createTestCase",desc = "创建测试用例")
    @ApiParam(name = "testCase",desc = "testCase",required = true)
    public Result<String> createTestCase(@RequestBody @NotNull @Valid TestCases testCases){
        String id = testCaseService.createTestCase(testCases);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateTestCase",method = RequestMethod.POST)
    @ApiMethod(name = "updateTestCase",desc = "更新测试用例")
    @ApiParam(name = "testCase",desc = "testCase",required = true)
    public Result<Void> updateTestCase(@RequestBody @NotNull @Valid TestCases testCases){
        testCaseService.updateTestCase(testCases);

        return Result.ok();
    }

    @RequestMapping(path="/deleteTestCase",method = RequestMethod.POST)
    @ApiMethod(name = "deleteTestCase",desc = "删除测试用例")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteTestCase(@NotNull String id){
        testCaseService.deleteTestCase(id);

        return Result.ok();
    }

    @RequestMapping(path="/findTestCase",method = RequestMethod.POST)
    @ApiMethod(name = "findTestCase",desc = "根据id查找测试用例")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<TestCases> findTestCase(@NotNull String id){
        TestCases testCases = testCaseService.findTestCase(id);

        return Result.ok(testCases);
    }

    @RequestMapping(path="/findAllTestCase",method = RequestMethod.POST)
    @ApiMethod(name = "findAllTestCase",desc = "查找所有测试用例")
    public Result<List<TestCases>> findAllTestCase(){
        List<TestCases> testCasesList = testCaseService.findAllTestCase();

        return Result.ok(testCasesList);
    }

    @RequestMapping(path = "/findTestCaseList",method = RequestMethod.POST)
    @ApiMethod(name = "findTestCaseList",desc = "根据查询参数查询测试用例列表")
    @ApiParam(name = "testCaseQuery",desc = "testCaseQuery",required = true)
    public Result<List<TestCases>> findTestCaseList(@RequestBody @Valid @NotNull TestCaseQuery testCaseQuery){
        List<TestCases> testCasesList = testCaseService.findTestCaseList(testCaseQuery);

        return Result.ok(testCasesList);
    }


    @RequestMapping(path = "/findTestCasePage",method = RequestMethod.POST)
    @ApiMethod(name = "findTestCasePage",desc = "根据查询参数按分页查询测试用例")
    @ApiParam(name = "testCaseQuery",desc = "testCaseQuery",required = true)
    public Result<Pagination<TestCases>> findTestCasePage(@RequestBody @Valid @NotNull TestCaseQuery testCaseQuery){
        Pagination<TestCases> pagination = testCaseService.findTestCasePage(testCaseQuery);

        return Result.ok(pagination);
    }

}
