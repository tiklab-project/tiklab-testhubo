package net.tiklab.teston.manager.testplan.controller;

import net.tiklab.core.Result;
import net.tiklab.core.page.Pagination;
import net.tiklab.postin.annotation.Api;
import net.tiklab.postin.annotation.ApiMethod;
import net.tiklab.postin.annotation.ApiParam;
import net.tiklab.teston.manager.testplan.model.TestPlanCaseInstanceBind;
import net.tiklab.teston.manager.testplan.model.TestPlanCaseInstanceBindQuery;
import net.tiklab.teston.manager.testplan.service.TestPlanCaseInstanceBindService;

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
 * TestPlanCaseInstanceBindController
 */
@RestController
@RequestMapping("/testPlanCaseInstanceBind")
@Api(name = "TestPlanCaseInstanceBindController",desc = "TestPlanCaseInstanceBindController")
public class TestPlanCaseInstanceBindController {

    private static Logger logger = LoggerFactory.getLogger(TestPlanCaseInstanceBindController.class);

    @Autowired
    private TestPlanCaseInstanceBindService testPlanCaseInstanceBindService;

    @RequestMapping(path="/createTestPlanCaseInstanceBind",method = RequestMethod.POST)
    @ApiMethod(name = "createTestPlanCaseInstanceBind",desc = "createTestPlanCaseInstanceBind")
    @ApiParam(name = "testPlanCaseInstanceBind",desc = "testPlanCaseInstanceBind",required = true)
    public Result<String> createTestPlanCaseInstanceBind(@RequestBody @NotNull @Valid TestPlanCaseInstanceBind testPlanCaseInstanceBind){
        String id = testPlanCaseInstanceBindService.createTestPlanCaseInstanceBind(testPlanCaseInstanceBind);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateTestPlanCaseInstanceBind",method = RequestMethod.POST)
    @ApiMethod(name = "updateTestPlanCaseInstanceBind",desc = "updateTestPlanCaseInstanceBind")
    @ApiParam(name = "testPlanCaseInstanceBind",desc = "testPlanCaseInstanceBind",required = true)
    public Result<Void> updateTestPlanCaseInstanceBind(@RequestBody @NotNull @Valid TestPlanCaseInstanceBind testPlanCaseInstanceBind){
        testPlanCaseInstanceBindService.updateTestPlanCaseInstanceBind(testPlanCaseInstanceBind);

        return Result.ok();
    }

    @RequestMapping(path="/deleteTestPlanCaseInstanceBind",method = RequestMethod.POST)
    @ApiMethod(name = "deleteTestPlanCaseInstanceBind",desc = "deleteTestPlanCaseInstanceBind")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteTestPlanCaseInstanceBind(@NotNull String id){
        testPlanCaseInstanceBindService.deleteTestPlanCaseInstanceBind(id);

        return Result.ok();
    }

    @RequestMapping(path="/findTestPlanCaseInstanceBind",method = RequestMethod.POST)
    @ApiMethod(name = "findTestPlanCaseInstanceBind",desc = "findTestPlanCaseInstanceBind")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<TestPlanCaseInstanceBind> findTestPlanCaseInstanceBind(@NotNull String id){
        TestPlanCaseInstanceBind testPlanCaseInstanceBind = testPlanCaseInstanceBindService.findTestPlanCaseInstanceBind(id);

        return Result.ok(testPlanCaseInstanceBind);
    }

    @RequestMapping(path="/findAllTestPlanCaseInstanceBind",method = RequestMethod.POST)
    @ApiMethod(name = "findAllTestPlanCaseInstanceBind",desc = "findAllTestPlanCaseInstanceBind")
    public Result<List<TestPlanCaseInstanceBind>> findAllTestPlanCaseInstanceBind(){
        List<TestPlanCaseInstanceBind> testPlanCaseInstanceBindList = testPlanCaseInstanceBindService.findAllTestPlanCaseInstanceBind();

        return Result.ok(testPlanCaseInstanceBindList);
    }

    @RequestMapping(path = "/findTestPlanCaseInstanceBindList",method = RequestMethod.POST)
    @ApiMethod(name = "findTestPlanCaseInstanceBindList",desc = "findTestPlanCaseInstanceBindList")
    @ApiParam(name = "testPlanCaseInstanceBindQuery",desc = "testPlanCaseInstanceBindQuery",required = true)
    public Result<List<TestPlanCaseInstanceBind>> findTestPlanCaseInstanceBindList(@RequestBody @Valid @NotNull TestPlanCaseInstanceBindQuery testPlanCaseInstanceBindQuery){
        List<TestPlanCaseInstanceBind> testPlanCaseInstanceBindList = testPlanCaseInstanceBindService.findTestPlanCaseInstanceBindList(testPlanCaseInstanceBindQuery);

        return Result.ok(testPlanCaseInstanceBindList);
    }

    @RequestMapping(path = "/findTestPlanCaseInstanceBindPage",method = RequestMethod.POST)
    @ApiMethod(name = "findTestPlanCaseInstanceBindPage",desc = "findTestPlanCaseInstanceBindPage")
    @ApiParam(name = "testPlanCaseInstanceBindQuery",desc = "testPlanCaseInstanceBindQuery",required = true)
    public Result<Pagination<TestPlanCaseInstanceBind>> findTestPlanCaseInstanceBindPage(@RequestBody @Valid @NotNull TestPlanCaseInstanceBindQuery testPlanCaseInstanceBindQuery){
        Pagination<TestPlanCaseInstanceBind> pagination = testPlanCaseInstanceBindService.findTestPlanCaseInstanceBindPage(testPlanCaseInstanceBindQuery);

        return Result.ok(pagination);
    }

}
