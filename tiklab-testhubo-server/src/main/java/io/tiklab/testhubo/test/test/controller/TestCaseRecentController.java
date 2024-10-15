package io.tiklab.testhubo.test.test.controller;

import io.tiklab.testhubo.test.test.model.TestCaseRecent;
import io.tiklab.testhubo.test.test.model.TestCaseRecentQuery;
import io.tiklab.testhubo.test.test.service.TestCaseRecentService;
import io.tiklab.core.Result;
import io.tiklab.core.page.Pagination;
import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.annotation.ApiMethod;
import io.tiklab.postin.annotation.ApiParam;
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
 * 最近访问仓库 控制器
 */
@RestController
@RequestMapping("/testCaseRecent")
@Api(name = "TestCaseRecentController",desc = "TestCaseRecentController")
public class TestCaseRecentController {

    private static Logger logger = LoggerFactory.getLogger(TestCaseRecentController.class);

    @Autowired
    private TestCaseRecentService testCaseRecentService;



    @RequestMapping(path = "/findTestCaseRecentList",method = RequestMethod.POST)
    @ApiMethod(name = "findTestCaseRecentList",desc = "查询最近访问仓库列表")
    @ApiParam(name = "testCaseRecentQuery",desc = "testCaseRecentQuery",required = true)
    public Result<List<TestCaseRecent>> findTestCaseRecentList(@RequestBody @Valid @NotNull TestCaseRecentQuery testCaseRecentQuery){
        List<TestCaseRecent> testCaseRecentList = testCaseRecentService.findTestCaseRecentList(testCaseRecentQuery);

        return Result.ok(testCaseRecentList);
    }


    @RequestMapping(path = "/findTestCaseRecentPage",method = RequestMethod.POST)
    @ApiMethod(name = "findTestCaseRecentPage",desc = "根据查询参数按分页查询最近访问仓库")
    @ApiParam(name = "testCaseRecentQuery",desc = "testCaseRecentQuery",required = true)
    public Result<Pagination<TestCaseRecent>> findTestCaseRecentPage(@RequestBody @Valid @NotNull  TestCaseRecentQuery testCaseRecentQuery){
        Pagination<TestCaseRecent> pagination = testCaseRecentService.findTestCaseRecentPage(testCaseRecentQuery);

        return Result.ok(pagination);
    }



    @RequestMapping(path="/testCaseRecent",method = RequestMethod.POST)
    @ApiMethod(name = "testCaseRecent",desc = "设置最近访问仓库")
    @ApiParam(name = "testCaseRecent",desc = "testCaseRecent",required = true)
    public Result<Void> workspaceRecent(@RequestBody @NotNull @Valid TestCaseRecent testCaseRecent){
        testCaseRecentService.testCaseRecent(testCaseRecent);

        return Result.ok();
    }


}
