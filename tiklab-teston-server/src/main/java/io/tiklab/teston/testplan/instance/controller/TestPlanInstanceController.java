package io.tiklab.teston.testplan.instance.controller;

import io.tiklab.teston.testplan.instance.model.TestPlanInstance;
import io.tiklab.teston.testplan.instance.model.TestPlanInstanceQuery;
import io.tiklab.teston.testplan.instance.service.TestPlanInstanceService;
import io.tiklab.core.Result;
import io.tiklab.core.page.Pagination;
import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.annotation.ApiMethod;
import io.tiklab.postin.annotation.ApiParam;
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
 * 测试计划实例 控制器
 */
@RestController
@RequestMapping("/testPlanInstance")
@Api(name = "TestPlanInstanceController",desc = "测试计划实例管理")
public class TestPlanInstanceController {

    private static Logger logger = LoggerFactory.getLogger(TestPlanInstanceController.class);

    @Autowired
    private TestPlanInstanceService testPlanInstanceService;

    @RequestMapping(path="/createTestPlanInstance",method = RequestMethod.POST)
    @ApiMethod(name = "createTestPlanInstance",desc = "创建测试计划实例")
    @ApiParam(name = "testPlanInstance",desc = "testPlanInstance",required = true)
    public Result<String> createTestPlanInstance(@RequestBody @NotNull @Valid TestPlanInstance testPlanInstance){
        String id = testPlanInstanceService.createTestPlanInstance(testPlanInstance);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateTestPlanInstance",method = RequestMethod.POST)
    @ApiMethod(name = "updateTestPlanInstance",desc = "更新测试计划实例")
    @ApiParam(name = "testPlanInstance",desc = "testPlanInstance",required = true)
    public Result<Void> updateTestPlanInstance(@RequestBody @NotNull @Valid TestPlanInstance testPlanInstance){
        testPlanInstanceService.updateTestPlanInstance(testPlanInstance);

        return Result.ok();
    }

    @RequestMapping(path="/deleteTestPlanInstance",method = RequestMethod.POST)
    @ApiMethod(name = "deleteTestPlanInstance",desc = "删除测试计划实例")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteTestPlanInstance(@NotNull String id){
        testPlanInstanceService.deleteTestPlanInstance(id);

        return Result.ok();
    }

    @RequestMapping(path="/findTestPlanInstance",method = RequestMethod.POST)
    @ApiMethod(name = "findTestPlanInstance",desc = "根据id查找测试计划实例")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<TestPlanInstance> findTestPlanInstance(@NotNull String id){
        TestPlanInstance testPlanInstance = testPlanInstanceService.findTestPlanInstance(id);

        return Result.ok(testPlanInstance);
    }

    @RequestMapping(path="/findAllTestPlanInstance",method = RequestMethod.POST)
    @ApiMethod(name = "findAllTestPlanInstance",desc = "查找所有测试计划实例")
    public Result<List<TestPlanInstance>> findAllTestPlanInstance(){
        List<TestPlanInstance> testPlanInstanceList = testPlanInstanceService.findAllTestPlanInstance();

        return Result.ok(testPlanInstanceList);
    }

    @RequestMapping(path = "/findTestPlanInstanceList",method = RequestMethod.POST)
    @ApiMethod(name = "findTestPlanInstanceList",desc = "根据查询参数查询测试计划实例列表")
    @ApiParam(name = "testPlanInstanceQuery",desc = "testPlanInstanceQuery",required = true)
    public Result<List<TestPlanInstance>> findTestPlanInstanceList(@RequestBody @Valid @NotNull TestPlanInstanceQuery testPlanInstanceQuery){
        List<TestPlanInstance> testPlanInstanceList = testPlanInstanceService.findTestPlanInstanceList(testPlanInstanceQuery);

        return Result.ok(testPlanInstanceList);
    }

    @RequestMapping(path = "/findTestPlanInstancePage",method = RequestMethod.POST)
    @ApiMethod(name = "findTestPlanInstancePage",desc = "根据查询参数按分页查询测试计划实例")
    @ApiParam(name = "testPlanInstanceQuery",desc = "testPlanInstanceQuery",required = true)
    public Result<Pagination<TestPlanInstance>> findTestPlanInstancePage(@RequestBody @Valid @NotNull TestPlanInstanceQuery testPlanInstanceQuery){
        Pagination<TestPlanInstance> pagination = testPlanInstanceService.findTestPlanInstancePage(testPlanInstanceQuery);

        return Result.ok(pagination);
    }

}
