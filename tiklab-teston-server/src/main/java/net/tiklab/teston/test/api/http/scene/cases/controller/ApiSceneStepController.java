package net.tiklab.teston.test.api.http.scene.cases.controller;

import net.tiklab.postin.annotation.Api;
import net.tiklab.teston.test.api.http.scene.cases.model.ApiSceneStep;
import net.tiklab.teston.test.api.http.scene.cases.model.ApiSceneStepQuery;
import net.tiklab.teston.test.api.http.scene.cases.service.ApiSceneStepService;
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
 * ApiSceneStepController
 */
@RestController
@RequestMapping("/apiSceneStep")
@Api(name = "ApiSceneStepController",desc = "ApiSceneStepController")
public class ApiSceneStepController {

    private static Logger logger = LoggerFactory.getLogger(ApiSceneStepController.class);

    @Autowired
    private ApiSceneStepService apiSceneStepService;

    @RequestMapping(path="/createApiSceneStep",method = RequestMethod.POST)
    @ApiMethod(name = "createApiSceneStep",desc = "createApiSceneStep")
    @ApiParam(name = "apiSceneStep",desc = "apiSceneStep",required = true)
    public Result<String> createApiSceneStep(@RequestBody @NotNull @Valid ApiSceneStep apiSceneStep){
        String id = apiSceneStepService.createApiSceneStep(apiSceneStep);

        return Result.ok(id);
    }


    @RequestMapping(path="/bindApiUnit",method = RequestMethod.POST)
    @ApiMethod(name = "bindApiUnit",desc = "bindApiUnit")
    @ApiParam(name = "apiSceneStep",desc = "apiSceneStep",required = true)
    public Result<String> bindApiUnit(@RequestBody @NotNull @Valid List<ApiSceneStep> apiSceneStep){
        apiSceneStepService.bindApiUnit(apiSceneStep);

        return Result.ok();
    }


    @RequestMapping(path="/updateApiSceneStep",method = RequestMethod.POST)
    @ApiMethod(name = "updateApiSceneStep",desc = "updateApiSceneStep")
    @ApiParam(name = "apiSceneStep",desc = "apiSceneStep",required = true)
    public Result<Void> updateApiSceneStep(@RequestBody @NotNull @Valid ApiSceneStep apiSceneStep){
        apiSceneStepService.updateApiSceneStep(apiSceneStep);

        return Result.ok();
    }

    @RequestMapping(path="/deleteApiSceneStep",method = RequestMethod.POST)
    @ApiMethod(name = "deleteApiSceneStep",desc = "deleteApiSceneStep")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteApiSceneStep(@NotNull String id){
        apiSceneStepService.deleteApiSceneStep(id);

        return Result.ok();
    }

    @RequestMapping(path="/findApiSceneStep",method = RequestMethod.POST)
    @ApiMethod(name = "findApiSceneStep",desc = "findApiSceneStep")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<ApiSceneStep> findApiSceneStep(@NotNull String id){
        ApiSceneStep apiSceneStep = apiSceneStepService.findApiSceneStep(id);

        return Result.ok(apiSceneStep);
    }

    @RequestMapping(path="/findAllApiSceneStep",method = RequestMethod.POST)
    @ApiMethod(name = "findAllApiSceneStep",desc = "findAllApiSceneStep")
    public Result<List<ApiSceneStep>> findAllApiSceneStep(){
        List<ApiSceneStep> apiSceneStepList = apiSceneStepService.findAllApiSceneStep();

        return Result.ok(apiSceneStepList);
    }

    @RequestMapping(path = "/findApiSceneStepList",method = RequestMethod.POST)
    @ApiMethod(name = "findApiSceneStepList",desc = "findApiSceneStepList")
    @ApiParam(name = "apiSceneStepQuery",desc = "apiSceneStepQuery",required = true)
    public Result<List<ApiSceneStep>> findApiSceneStepList(@RequestBody @Valid @NotNull ApiSceneStepQuery apiSceneStepQuery){
        List<ApiSceneStep> apiSceneStepList = apiSceneStepService.findApiSceneStepList(apiSceneStepQuery);

        return Result.ok(apiSceneStepList);
    }

    @RequestMapping(path = "/findApiSceneStepPage",method = RequestMethod.POST)
    @ApiMethod(name = "findApiSceneStepPage",desc = "findApiSceneStepPage")
    @ApiParam(name = "apiSceneStepQuery",desc = "apiSceneStepQuery",required = true)
    public Result<Pagination<ApiSceneStep>> findApiSceneStepPage(@RequestBody @Valid @NotNull ApiSceneStepQuery apiSceneStepQuery){
        Pagination<ApiSceneStep> pagination = apiSceneStepService.findApiSceneStepPage(apiSceneStepQuery);

        return Result.ok(pagination);
    }

}
