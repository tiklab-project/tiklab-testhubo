package net.tiklab.teston.testplan.controller;

import net.tiklab.postin.annotation.Api;
import net.tiklab.teston.test.testcase.model.TestCase;
import net.tiklab.teston.testplan.model.TestPlanDetail;
import net.tiklab.teston.testplan.model.TestPlanDetailQuery;
import net.tiklab.teston.testplan.service.TestPlanDetailService;
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
 * TestPlanDetailController
 */
@RestController
@RequestMapping("/testPlanDetail")
@Api(name = "TestPlanDetailController",desc = "测试计划详情表(测试计划关联用例表)")
public class TestPlanDetailController {

    private static Logger logger = LoggerFactory.getLogger(TestPlanDetailController.class);

    @Autowired
    private TestPlanDetailService testPlanDetailService;

    @RequestMapping(path="/createTestPlanDetail",method = RequestMethod.POST)
    @ApiMethod(name = "createTestPlanDetail",desc = "创建测试计划详情表")
    @ApiParam(name = "testPlanDetail",desc = "testPlanDetail",required = true)
    public Result<String> createTestPlanDetail(@RequestBody @NotNull @Valid TestPlanDetail testPlanDetail){
        String id = testPlanDetailService.createTestPlanDetail(testPlanDetail);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateTestPlanDetail",method = RequestMethod.POST)
    @ApiMethod(name = "updateTestPlanDetail",desc = "修改测试计划详情")
    @ApiParam(name = "testPlanDetail",desc = "testPlanDetail",required = true)
    public Result<Void> updateTestPlanDetail(@RequestBody @NotNull @Valid TestPlanDetail testPlanDetail){
        testPlanDetailService.updateTestPlanDetail(testPlanDetail);

        return Result.ok();
    }

    @RequestMapping(path="/deleteTestPlanDetail",method = RequestMethod.POST)
    @ApiMethod(name = "deleteTestPlanDetail",desc = "删除测试计划详情")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteTestPlanDetail(@NotNull String id){
        testPlanDetailService.deleteTestPlanDetail(id);

        return Result.ok();
    }

    @RequestMapping(path="/findTestPlanDetail",method = RequestMethod.POST)
    @ApiMethod(name = "findTestPlanDetail",desc = "通过id查询")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<TestPlanDetail> findTestPlanDetail(@NotNull String id){
        TestPlanDetail testPlanDetail = testPlanDetailService.findTestPlanDetail(id);

        return Result.ok(testPlanDetail);
    }

    @RequestMapping(path="/findAllTestPlanDetail",method = RequestMethod.POST)
    @ApiMethod(name = "findAllTestPlanDetail",desc = "查询所有")
    public Result<List<TestPlanDetail>> findAllTestPlanDetail(){
        List<TestPlanDetail> testPlanDetailList = testPlanDetailService.findAllTestPlanDetail();

        return Result.ok(testPlanDetailList);
    }

    @RequestMapping(path = "/findTestPlanDetailList",method = RequestMethod.POST)
    @ApiMethod(name = "findTestPlanDetailList",desc = "通过查询对象查询")
    @ApiParam(name = "testPlanDetailQuery",desc = "testPlanDetailQuery",required = true)
    public Result<List<TestPlanDetail>> findTestPlanDetailList(@RequestBody @Valid @NotNull TestPlanDetailQuery testPlanDetailQuery){
        List<TestPlanDetail> testPlanDetailList = testPlanDetailService.findTestPlanDetailList(testPlanDetailQuery);

        return Result.ok(testPlanDetailList);
    }

    @RequestMapping(path = "/findTestPlanDetailPage",method = RequestMethod.POST)
    @ApiMethod(name = "findTestPlanDetailPage",desc = "通过查询对象分页查询")
    @ApiParam(name = "testPlanDetailQuery",desc = "testPlanDetailQuery",required = true)
    public Result<Pagination<TestPlanDetail>> findTestPlanDetailPage(@RequestBody @Valid @NotNull TestPlanDetailQuery testPlanDetailQuery){
        Pagination<TestPlanDetail> pagination = testPlanDetailService.findTestPlanDetailPage(testPlanDetailQuery);

        return Result.ok(pagination);
    }


    @RequestMapping(path = "/findTesCaseList",method = RequestMethod.POST)
    @ApiMethod(name = "findTesCase",desc = "测试计划详情添加用例弹窗列表")
    @ApiParam(name = "testPlanDetailQuery",desc = "testPlanDetailQuery",required = true)
    public Result<Pagination<TestCase>> findTesCaseList(@RequestBody @Valid @NotNull TestPlanDetail testPlanDetail){
        Pagination<TestCase> pagination = testPlanDetailService.findTesCaseList(testPlanDetail);

        return Result.ok(pagination);
    }

    @RequestMapping(path = "/findBindTestCaseList",method = RequestMethod.POST)
    @ApiMethod(name = "findReleTesCase",desc = "查询测试计划关联的用例列表")
    @ApiParam(name = "testPlanDetailQuery",desc = "testPlanDetailQuery",required = true)
    public Result<Pagination<TestPlanDetail>> findBindTestCaseList(@RequestBody @Valid @NotNull TestPlanDetailQuery testPlanDetailQuery){
        Pagination<TestPlanDetail> pagination = testPlanDetailService.findBindTestCaseList(testPlanDetailQuery);

        return Result.ok(pagination);
    }

    @RequestMapping(path="/createTestPlanDetailList",method = RequestMethod.POST)
    @ApiMethod(name = "createTestPlanDetailList",desc = "给测试计划关联测试用例")
    @ApiParam(name = "testPlanDetailList",desc = "testPlanDetailList",required = true)
    public Result<String> createTestPlanDetailList( @RequestBody @NotNull @Valid List<TestPlanDetail> testPlanDetailList){
        String id=null;
        for (TestPlanDetail testPlanDetail:testPlanDetailList){
            id= testPlanDetailService.createTestPlanDetail(testPlanDetail);

        }
        return Result.ok(id);

    }
}
