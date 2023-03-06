package net.tiklab.teston.testplan.cases.controller;

import net.tiklab.postin.annotation.Api;
import net.tiklab.teston.test.test.model.TestCase;
import net.tiklab.teston.testplan.cases.model.TestPlanCase;
import net.tiklab.teston.testplan.cases.model.TestPlanCaseQuery;
import net.tiklab.teston.testplan.cases.service.TestPlanCaseService;
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
 * 测试计划绑定的用例 控制器
 */
@RestController
@RequestMapping("/testPlanCase")
@Api(name = "TestPlanCaseController",desc = "测试计划绑定的用例(测试计划关联用例表)")
public class TestPlanCaseController {

    private static Logger logger = LoggerFactory.getLogger(TestPlanCaseController.class);

    @Autowired
    private TestPlanCaseService testPlanCaseService;

    @RequestMapping(path="/createTestPlanCase",method = RequestMethod.POST)
    @ApiMethod(name = "createTestPlanCase",desc = "创建测试计划绑定的用例")
    @ApiParam(name = "testPlanDetail",desc = "testPlanDetail",required = true)
    public Result<String> createTestPlanCase(@RequestBody @NotNull @Valid TestPlanCase testPlanCase){
        String id = testPlanCaseService.createTestPlanCase(testPlanCase);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateTestPlanCase",method = RequestMethod.POST)
    @ApiMethod(name = "updateTestPlanCase",desc = "修改测试计划绑定的用例")
    @ApiParam(name = "testPlanDetail",desc = "testPlanDetail",required = true)
    public Result<Void> updateTestPlanCase(@RequestBody @NotNull @Valid TestPlanCase testPlanCase){
        testPlanCaseService.updateTestPlanCase(testPlanCase);

        return Result.ok();
    }

    @RequestMapping(path="/deleteTestPlanCase",method = RequestMethod.POST)
    @ApiMethod(name = "deleteTestPlanCase",desc = "删除测试计划绑定的用例")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteTestPlanCase(@NotNull String id){
        testPlanCaseService.deleteTestPlanCase(id);

        return Result.ok();
    }

    @RequestMapping(path="/findTestPlanCase",method = RequestMethod.POST)
    @ApiMethod(name = "findTestPlanCase",desc = "通过id查询")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<TestPlanCase> findTestPlanCase(@NotNull String id){
        TestPlanCase testPlanCase = testPlanCaseService.findTestPlanCase(id);

        return Result.ok(testPlanCase);
    }

    @RequestMapping(path="/findAllTestPlanCase",method = RequestMethod.POST)
    @ApiMethod(name = "findAllTestPlanCase",desc = "查询所有")
    public Result<List<TestPlanCase>> findAllTestPlanCase(){
        List<TestPlanCase> testPlanCaseList = testPlanCaseService.findAllTestPlanCase();

        return Result.ok(testPlanCaseList);
    }

    @RequestMapping(path = "/findTestPlanCaseList",method = RequestMethod.POST)
    @ApiMethod(name = "findTestPlanCaseList",desc = "通过查询对象查询")
    @ApiParam(name = "testPlanDetailQuery",desc = "testPlanDetailQuery",required = true)
    public Result<List<TestPlanCase>> findTestPlanCaseList(@RequestBody @Valid @NotNull TestPlanCaseQuery testPlanCaseQuery){
        List<TestPlanCase> testPlanCaseList = testPlanCaseService.findTestPlanCaseList(testPlanCaseQuery);

        return Result.ok(testPlanCaseList);
    }

    @RequestMapping(path = "/findTestPlanCasePage",method = RequestMethod.POST)
    @ApiMethod(name = "findTestPlanCasePage",desc = "通过查询对象分页查询")
    @ApiParam(name = "testPlanDetailQuery",desc = "testPlanDetailQuery",required = true)
    public Result<Pagination<TestPlanCase>> findTestPlanCasePage(@RequestBody @Valid @NotNull TestPlanCaseQuery testPlanCaseQuery){
        Pagination<TestPlanCase> pagination = testPlanCaseService.findTestPlanCasePage(testPlanCaseQuery);

        return Result.ok(pagination);
    }


    @RequestMapping(path = "/findTesCaseList",method = RequestMethod.POST)
    @ApiMethod(name = "findTesCase",desc = "测试计划绑定的用例添加用例弹窗列表")
    @ApiParam(name = "testPlanDetailQuery",desc = "testPlanDetailQuery",required = true)
    public Result<Pagination<TestCase>> findTesCaseList(@RequestBody @Valid @NotNull TestPlanCase testPlanCase){
        Pagination<TestCase> pagination = testPlanCaseService.findTesCaseList(testPlanCase);

        return Result.ok(pagination);
    }

    @RequestMapping(path = "/findBindTestCaseList",method = RequestMethod.POST)
    @ApiMethod(name = "findReleTesCase",desc = "查询测试计划关联的用例列表")
    @ApiParam(name = "testPlanDetailQuery",desc = "testPlanDetailQuery",required = true)
    public Result<Pagination<TestPlanCase>> findBindTestCaseList(@RequestBody @Valid @NotNull TestPlanCaseQuery testPlanCaseQuery){
        Pagination<TestPlanCase> pagination = testPlanCaseService.findBindTestCaseList(testPlanCaseQuery);

        return Result.ok(pagination);
    }

    @RequestMapping(path="/createTestPlanCaseList",method = RequestMethod.POST)
    @ApiMethod(name = "createTestPlanCaseList",desc = "给测试计划关联测试用例")
    @ApiParam(name = "testPlanDetailList",desc = "testPlanDetailList",required = true)
    public Result<String> createTestPlanCaseList( @RequestBody @NotNull @Valid List<TestPlanCase> testPlanCaseList){
        String id=null;
        for (TestPlanCase testPlanCase : testPlanCaseList){
            id= testPlanCaseService.createTestPlanCase(testPlanCase);

        }
        return Result.ok(id);

    }
}
