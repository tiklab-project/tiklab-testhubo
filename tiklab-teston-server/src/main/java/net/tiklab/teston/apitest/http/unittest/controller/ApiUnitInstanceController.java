package net.tiklab.teston.apitest.http.unittest.controller;

import net.tiklab.postin.annotation.Api;
import net.tiklab.postin.annotation.ApiMethod;
import net.tiklab.postin.annotation.ApiParam;
import net.tiklab.core.Result;
import net.tiklab.core.page.Pagination;
import net.tiklab.teston.apitest.http.unittest.model.ApiUnitInstance;
import net.tiklab.teston.apitest.http.unittest.model.ApiUnitInstanceQuery;
import net.tiklab.teston.apitest.http.unittest.service.ApiUnitInstanceService;
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
 * ApiUnitInstanceController
 */
@RestController
@RequestMapping("/apiUnitInstance")
@Api(name = "ApiUnitInstanceController",desc = "api单元测试实例管理")
public class ApiUnitInstanceController {

    private static Logger logger = LoggerFactory.getLogger(ApiUnitInstanceController.class);

    @Autowired
    private ApiUnitInstanceService apiUnitInstanceService;

    @RequestMapping(path="/createApiUnitInstance",method = RequestMethod.POST)
    @ApiMethod(name = "createApiUnitInstance",desc = "创建步骤实例")
    @ApiParam(name = "testStepInstance",desc = "对象",required = true)
    public Result<String> createApiUnitInstance(@RequestBody @NotNull @Valid ApiUnitInstance testStepInstance){
        String id = apiUnitInstanceService.createApiUnitInstance(testStepInstance);

        return Result.ok(id);
    }

    @RequestMapping(path="/createApiUnitInstanceList",method = RequestMethod.POST)
    @ApiMethod(name = "createApiUnitInstanceList",desc = "创建步骤用例实例")
    @ApiParam(name = "testStepInstances",desc = "传list",required = true)
    public Result<String> createApiUnitInstanceList(@NotNull @Valid List<ApiUnitInstance> testStepInstances){
        String id = apiUnitInstanceService.createApiUnitInstanceWithNest(testStepInstances);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateApiUnitInstance",method = RequestMethod.POST)
    @ApiMethod(name = "updateApiUnitInstance",desc = "更新测试用例实例")
    @ApiParam(name = "testStepInstance",desc = "testStepInstance",required = true)
    public Result<Void> updateApiUnitInstance(@RequestBody @NotNull @Valid ApiUnitInstance testStepInstance){
        apiUnitInstanceService.updateApiUnitInstance(testStepInstance);

        return Result.ok();
    }

    @RequestMapping(path="/deleteApiUnitInstance",method = RequestMethod.POST)
    @ApiMethod(name = "deleteApiUnitInstance",desc = "删除测试用例实例")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteApiUnitInstance(@NotNull String id){
        apiUnitInstanceService.deleteApiUnitInstance(id);

        return Result.ok();
    }

    @RequestMapping(path="/findApiUnitInstance",method = RequestMethod.POST)
    @ApiMethod(name = "findApiUnitInstance",desc = "通过id查询测试用例实例")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<ApiUnitInstance> findApiUnitInstance(@NotNull String id){
        ApiUnitInstance testStepInstance = apiUnitInstanceService.findApiUnitInstanceWithNest(id);

        return Result.ok(testStepInstance);
    }

    @RequestMapping(path="/findAllApiUnitInstance",method = RequestMethod.POST)
    @ApiMethod(name = "findAllApiUnitInstance",desc = "查询所有测试用例实例")
    public Result<List<ApiUnitInstance>> findAllApiUnitInstance(){
        List<ApiUnitInstance> allApiUnitInstance = apiUnitInstanceService.findAllApiUnitInstance();

        return Result.ok(allApiUnitInstance);
    }

    @RequestMapping(path = "/findApiUnitInstanceList",method = RequestMethod.POST)
    @ApiMethod(name = "findApiUnitInstanceList",desc = "通过查询对象查询测试用例实例")
    @ApiParam(name = "testStepInstanceQuery",desc = "testStepInstanceQuery",required = true)
    public Result<List<ApiUnitInstance>> findApiUnitInstanceList(@RequestBody @Valid @NotNull ApiUnitInstanceQuery testStepInstanceQuery){
        List<ApiUnitInstance> testStepInstanceList = apiUnitInstanceService.findApiUnitInstanceList(testStepInstanceQuery);

        return Result.ok(testStepInstanceList);
    }

    @RequestMapping(path = "/findApiUnitInstancePage",method = RequestMethod.POST)
    @ApiMethod(name = "findApiUnitInstancePage",desc = "通过查询对象分页查询测试用例实例")
    @ApiParam(name = "apiUnitInstanceQuery",desc = "apiUnitInstanceQuery",required = true)
    public Result<Pagination<ApiUnitInstance>> findApiUnitInstancePage(@RequestBody @Valid @NotNull ApiUnitInstanceQuery testStepInstanceQuery){
        Pagination<ApiUnitInstance> pagination = apiUnitInstanceService.findApiUnitInstancePage(testStepInstanceQuery);

        return Result.ok(pagination);
    }



}
