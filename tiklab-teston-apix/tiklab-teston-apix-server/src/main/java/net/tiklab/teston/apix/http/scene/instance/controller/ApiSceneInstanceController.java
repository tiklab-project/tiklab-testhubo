package net.tiklab.teston.apix.http.scene.instance.controller;

import net.tiklab.postin.annotation.Api;
import net.tiklab.postin.annotation.ApiMethod;
import net.tiklab.postin.annotation.ApiParam;
import net.tiklab.core.Result;
import net.tiklab.core.page.Pagination;
import net.tiklab.teston.apix.http.scene.instance.model.ApiSceneInstance;
import net.tiklab.teston.apix.http.scene.instance.model.ApiSceneInstanceQuery;
import net.tiklab.teston.apix.http.scene.instance.service.ApiSceneInstanceService;
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
 * ApiSceneInstanceController
 */
@RestController
@RequestMapping("/apiSceneInstance")
@Api(name = "ApiSceneInstanceController",desc = "api场景测试实例管理")
public class ApiSceneInstanceController {

    private static Logger logger = LoggerFactory.getLogger(ApiSceneInstanceController.class);

    @Autowired
    private ApiSceneInstanceService apiSceneInstanceService;

    @RequestMapping(path="/createApiSceneInstance",method = RequestMethod.POST)
    @ApiMethod(name = "createApiSceneInstance",desc = "createApiSceneInstance")
    @ApiParam(name = "testInstance",desc = "testInstance",required = true)
    public Result<String> createApiSceneInstance(@RequestBody @NotNull @Valid ApiSceneInstance testInstance){
        String id = apiSceneInstanceService.createApiSceneInstance(testInstance);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateApiSceneInstance",method = RequestMethod.POST)
    @ApiMethod(name = "updateApiSceneInstance",desc = "updateApiSceneInstance")
    @ApiParam(name = "testInstance",desc = "testInstance",required = true)
    public Result<Void> updateApiSceneInstance(@RequestBody @NotNull @Valid ApiSceneInstance testInstance){
        apiSceneInstanceService.updateApiSceneInstance(testInstance);

        return Result.ok();
    }

    @RequestMapping(path="/deleteApiSceneInstance",method = RequestMethod.POST)
    @ApiMethod(name = "deleteApiSceneInstance",desc = "deleteApiSceneInstance")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteApiSceneInstance(@NotNull String id){
        apiSceneInstanceService.deleteApiSceneInstance(id);

        return Result.ok();
    }

    @RequestMapping(path="/findApiSceneInstance",method = RequestMethod.POST)
    @ApiMethod(name = "findApiSceneInstance",desc = "findApiSceneInstance")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<ApiSceneInstance> findApiSceneInstance(@NotNull String id){
        ApiSceneInstance testInstance = apiSceneInstanceService.findApiSceneInstance(id);

        return Result.ok(testInstance);
    }

    @RequestMapping(path="/findAllApiSceneInstance",method = RequestMethod.POST)
    @ApiMethod(name = "findAllApiSceneInstance",desc = "findAllApiSceneInstance")
    public Result<List<ApiSceneInstance>> findAllApiSceneInstance(){
        List<ApiSceneInstance> allApiSceneInstance = apiSceneInstanceService.findAllApiSceneInstance();

        return Result.ok(allApiSceneInstance);
    }

    @RequestMapping(path = "/findApiSceneInstanceList",method = RequestMethod.POST)
    @ApiMethod(name = "findApiSceneInstanceList",desc = "findApiSceneInstanceList")
    @ApiParam(name = "testInstanceQuery",desc = "testInstanceQuery",required = true)
    public Result<List<ApiSceneInstance>> findApiSceneInstanceList(@RequestBody @Valid @NotNull ApiSceneInstanceQuery apiSceneInstanceQuery){
        List<ApiSceneInstance> apiSceneInstanceList = apiSceneInstanceService.findApiSceneInstanceList(apiSceneInstanceQuery);

        return Result.ok(apiSceneInstanceList);
    }

    @RequestMapping(path = "/findApiSceneInstancePage",method = RequestMethod.POST)
    @ApiMethod(name = "findApiSceneInstancePage",desc = "findApiSceneInstancePage")
    @ApiParam(name = "testInstanceQuery",desc = "testInstanceQuery",required = true)
    public Result<Pagination<ApiSceneInstance>> findApiSceneInstancePage(@RequestBody @Valid @NotNull ApiSceneInstanceQuery testInstanceQuery){
        Pagination<ApiSceneInstance> pagination = apiSceneInstanceService.findApiSceneInstancePage(testInstanceQuery);

        return Result.ok(pagination);
    }




}
