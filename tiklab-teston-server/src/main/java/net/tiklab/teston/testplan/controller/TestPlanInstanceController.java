package net.tiklab.teston.testplan.controller;

import net.tiklab.core.Result;
import net.tiklab.core.page.Pagination;
import net.tiklab.postin.annotation.Api;
import net.tiklab.postin.annotation.ApiMethod;
import net.tiklab.postin.annotation.ApiParam;
import net.tiklab.teston.testplan.model.TestPlanInstance;
import net.tiklab.teston.testplan.model.TestPlanInstanceQuery;
import net.tiklab.teston.testplan.service.TestPlanInstanceService;
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
 * TestPlanInstanceController
 */
@RestController
@RequestMapping("/testPlanInstance")
@Api(name = "TestPlanInstanceController",desc = "TestPlanInstanceController")
public class TestPlanInstanceController {

    private static Logger logger = LoggerFactory.getLogger(TestPlanInstanceController.class);

    @Autowired
    private TestPlanInstanceService testPlanInstanceService;

    @RequestMapping(path="/createTestPlanInstance",method = RequestMethod.POST)
    @ApiMethod(name = "createTestPlanInstance",desc = "createTestPlanInstance")
    @ApiParam(name = "testPlanInstance",desc = "testPlanInstance",required = true)
    public Result<String> createTestPlanInstance(@RequestBody @NotNull @Valid TestPlanInstance testPlanInstance){
        String id = testPlanInstanceService.createTestPlanInstance(testPlanInstance);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateTestPlanInstance",method = RequestMethod.POST)
    @ApiMethod(name = "updateTestPlanInstance",desc = "updateTestPlanInstance")
    @ApiParam(name = "testPlanInstance",desc = "testPlanInstance",required = true)
    public Result<Void> updateTestPlanInstance(@RequestBody @NotNull @Valid TestPlanInstance testPlanInstance){
        testPlanInstanceService.updateTestPlanInstance(testPlanInstance);

        return Result.ok();
    }

    @RequestMapping(path="/deleteTestPlanInstance",method = RequestMethod.POST)
    @ApiMethod(name = "deleteTestPlanInstance",desc = "deleteTestPlanInstance")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteTestPlanInstance(@NotNull String id){
        testPlanInstanceService.deleteTestPlanInstance(id);

        return Result.ok();
    }

    @RequestMapping(path="/findTestPlanInstance",method = RequestMethod.POST)
    @ApiMethod(name = "findTestPlanInstance",desc = "findTestPlanInstance")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<TestPlanInstance> findTestPlanInstance(@NotNull String id){
        TestPlanInstance testPlanInstance = testPlanInstanceService.findTestPlanInstance(id);

        return Result.ok(testPlanInstance);
    }

    @RequestMapping(path="/findAllTestPlanInstance",method = RequestMethod.POST)
    @ApiMethod(name = "findAllTestPlanInstance",desc = "findAllTestPlanInstance")
    public Result<List<TestPlanInstance>> findAllTestPlanInstance(){
        List<TestPlanInstance> testPlanInstanceList = testPlanInstanceService.findAllTestPlanInstance();

        return Result.ok(testPlanInstanceList);
    }

    @RequestMapping(path = "/findTestPlanInstanceList",method = RequestMethod.POST)
    @ApiMethod(name = "findTestPlanInstanceList",desc = "findTestPlanInstanceList")
    @ApiParam(name = "testPlanInstanceQuery",desc = "testPlanInstanceQuery",required = true)
    public Result<List<TestPlanInstance>> findTestPlanInstanceList(@RequestBody @Valid @NotNull TestPlanInstanceQuery testPlanInstanceQuery){
        List<TestPlanInstance> testPlanInstanceList = testPlanInstanceService.findTestPlanInstanceList(testPlanInstanceQuery);

        return Result.ok(testPlanInstanceList);
    }

    @RequestMapping(path = "/findTestPlanInstancePage",method = RequestMethod.POST)
    @ApiMethod(name = "findTestPlanInstancePage",desc = "findTestPlanInstancePage")
    @ApiParam(name = "testPlanInstanceQuery",desc = "testPlanInstanceQuery",required = true)
    public Result<Pagination<TestPlanInstance>> findTestPlanInstancePage(@RequestBody @Valid @NotNull TestPlanInstanceQuery testPlanInstanceQuery){
        Pagination<TestPlanInstance> pagination = testPlanInstanceService.findTestPlanInstancePage(testPlanInstanceQuery);

        return Result.ok(pagination);
    }

}
