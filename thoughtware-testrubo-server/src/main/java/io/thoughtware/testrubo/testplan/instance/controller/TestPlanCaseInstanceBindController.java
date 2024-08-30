package io.thoughtware.testrubo.testplan.instance.controller;

import io.thoughtware.testrubo.testplan.instance.model.TestPlanCaseInstanceBind;
import io.thoughtware.testrubo.testplan.instance.model.TestPlanCaseInstanceBindQuery;
import io.thoughtware.testrubo.testplan.instance.service.TestPlanCaseInstanceBindService;
import io.thoughtware.core.Result;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.postin.annotation.Api;
import io.thoughtware.postin.annotation.ApiMethod;
import io.thoughtware.postin.annotation.ApiParam;

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
 * 
 * @pi.protocol: http
 * @pi.groupName: 测试计划下用例的实例中间层 控制器
 */
@RestController
@RequestMapping("/testPlanCaseInstanceBind")
@Api(name = "TestPlanCaseInstanceBindController",desc = "测试计划下用例的实例中间层管理")
public class TestPlanCaseInstanceBindController {

    private static Logger logger = LoggerFactory.getLogger(TestPlanCaseInstanceBindController.class);

    @Autowired
    private TestPlanCaseInstanceBindService testPlanCaseInstanceBindService;
    
    /**
     * @pi.name:createTestPlanCaseInstanceBind
     * @pi.path:/testPlanCaseInstanceBind/createTestPlanCaseInstanceBind
     * @pi.methodType:post
     * @pi.request-type:json
     * @pi.param: model=TestPlanCaseInstanceBind
     */
    @RequestMapping(path="/createTestPlanCaseInstanceBind",method = RequestMethod.POST)
    @ApiMethod(name = "createTestPlanCaseInstanceBind",desc = "创建用例的实例中间层")
    @ApiParam(name = "testPlanCaseInstanceBind",desc = "testPlanCaseInstanceBind",required = true)
    public Result<String> createTestPlanCaseInstanceBind(@RequestBody @NotNull @Valid TestPlanCaseInstanceBind testPlanCaseInstanceBind){
        String id = testPlanCaseInstanceBindService.createTestPlanCaseInstanceBind(testPlanCaseInstanceBind);

        return Result.ok(id);
    }

    /**
     * @pi.name:updateTestPlanCaseInstanceBind
     * @pi.path:/testPlanCaseInstanceBind/updateTestPlanCaseInstanceBind
     * @pi.methodType:post
     * @pi.request-type:json
     * @pi.param: model=TestPlanCaseInstanceBind
     */
    @RequestMapping(path="/updateTestPlanCaseInstanceBind",method = RequestMethod.POST)
    @ApiMethod(name = "updateTestPlanCaseInstanceBind",desc = "更新用例的实例中间层")
    @ApiParam(name = "testPlanCaseInstanceBind",desc = "testPlanCaseInstanceBind",required = true)
    public Result<Void> updateTestPlanCaseInstanceBind(@RequestBody @NotNull @Valid TestPlanCaseInstanceBind testPlanCaseInstanceBind){
        testPlanCaseInstanceBindService.updateTestPlanCaseInstanceBind(testPlanCaseInstanceBind);

        return Result.ok();
    }
    
    /**
     * @pi.name:deleteTestPlanCaseInstanceBind
     * @pi.path:/testPlanCaseInstanceBind/deleteTestPlanCaseInstanceBind
     * @pi.methodType:post
     * @pi.request-type:json
     * @pi.param: name=id;dataType=string;value=id;desc=当前删除的id
     */
    @RequestMapping(path="/deleteTestPlanCaseInstanceBind",method = RequestMethod.POST)
    @ApiMethod(name = "deleteTestPlanCaseInstanceBind",desc = "删除用例的实例中间层")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteTestPlanCaseInstanceBind(@NotNull String id){
        testPlanCaseInstanceBindService.deleteTestPlanCaseInstanceBind(id);

        return Result.ok();
    }

    /**
     * @pi.name:findTestPlanCaseInstanceBind
     * @pi.path:/testPlanCaseInstanceBind/findTestPlanCaseInstanceBind
     * @pi.methodType:post
     * @pi.request-type:formdata
     * @pi.param: name=id;dataType=string;value=id;desc=当前查找的id
     */
    @RequestMapping(path="/findTestPlanCaseInstanceBind",method = RequestMethod.POST)
    @ApiMethod(name = "findTestPlanCaseInstanceBind",desc = "查找用例的实例中间层")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<TestPlanCaseInstanceBind> findTestPlanCaseInstanceBind(@NotNull String id){
        TestPlanCaseInstanceBind testPlanCaseInstanceBind = testPlanCaseInstanceBindService.findTestPlanCaseInstanceBind(id);

        return Result.ok(testPlanCaseInstanceBind);
    }

    /**
     * @pi.name:findAllTestPlanCaseInstanceBind
     * @pi.path:/testPlanCaseInstanceBind/findAllTestPlanCaseInstanceBind
     * @pi.methodType:post
     * @pi.request-type:none
     */
    @RequestMapping(path="/findAllTestPlanCaseInstanceBind",method = RequestMethod.POST)
    @ApiMethod(name = "findAllTestPlanCaseInstanceBind",desc = "查找所有用例的实例中间层")
    public Result<List<TestPlanCaseInstanceBind>> findAllTestPlanCaseInstanceBind(){
        List<TestPlanCaseInstanceBind> testPlanCaseInstanceBindList = testPlanCaseInstanceBindService.findAllTestPlanCaseInstanceBind();

        return Result.ok(testPlanCaseInstanceBindList);
    }

    /**
     * @pi.name:findTestPlanCaseInstanceBindList
     * @pi.path:/testPlanCaseInstanceBind/findTestPlanCaseInstanceBindList
     * @pi.methodType:post
     * @pi.request-type:json
     * @pi.param: model=TestPlanCaseInstanceBind
     */
    @RequestMapping(path = "/findTestPlanCaseInstanceBindList",method = RequestMethod.POST)
    @ApiMethod(name = "findTestPlanCaseInstanceBindList",desc = "根据查询参数查询用例的实例中间层列表")
    @ApiParam(name = "testPlanCaseInstanceBindQuery",desc = "testPlanCaseInstanceBindQuery",required = true)
    public Result<List<TestPlanCaseInstanceBind>> findTestPlanCaseInstanceBindList(@RequestBody @Valid @NotNull TestPlanCaseInstanceBindQuery testPlanCaseInstanceBindQuery){
        List<TestPlanCaseInstanceBind> testPlanCaseInstanceBindList = testPlanCaseInstanceBindService.findTestPlanCaseInstanceBindList(testPlanCaseInstanceBindQuery);

        return Result.ok(testPlanCaseInstanceBindList);
    }

    /**
     * @pi.name:findTestPlanCaseInstanceBindPage
     * @pi.path:/testPlanCaseInstanceBind/findTestPlanCaseInstanceBindPage
     * @pi.methodType:post
     * @pi.request-type:json
     * @pi.param: model=TestPlanCaseInstanceBindQuery
     */
    @RequestMapping(path = "/findTestPlanCaseInstanceBindPage",method = RequestMethod.POST)
    @ApiMethod(name = "findTestPlanCaseInstanceBindPage",desc = "根据查询参数按分页查询用例的实例中间层")
    @ApiParam(name = "testPlanCaseInstanceBindQuery",desc = "testPlanCaseInstanceBindQuery",required = true)
    public Result<Pagination<TestPlanCaseInstanceBind>> findTestPlanCaseInstanceBindPage(@RequestBody @Valid @NotNull TestPlanCaseInstanceBindQuery testPlanCaseInstanceBindQuery){
        Pagination<TestPlanCaseInstanceBind> pagination = testPlanCaseInstanceBindService.findTestPlanCaseInstanceBindPage(testPlanCaseInstanceBindQuery);

        return Result.ok(pagination);
    }

}
