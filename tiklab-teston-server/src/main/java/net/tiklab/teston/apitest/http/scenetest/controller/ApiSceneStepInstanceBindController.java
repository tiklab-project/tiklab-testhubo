package net.tiklab.teston.apitest.http.scenetest.controller;

import net.tiklab.core.Result;
import net.tiklab.core.page.Pagination;
import net.tiklab.postin.annotation.Api;
import net.tiklab.postin.annotation.ApiMethod;
import net.tiklab.postin.annotation.ApiParam;
import net.tiklab.teston.apitest.http.scenetest.model.ApiSceneStepInstanceBind;
import net.tiklab.teston.apitest.http.scenetest.model.ApiSceneStepInstanceBindQuery;
import net.tiklab.teston.apitest.http.scenetest.service.ApiSceneStepInstanceBindService;
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
 * ApiSceneStepInstanceBindController
 */
@RestController
@RequestMapping("/apiSceneStepInstanceBind")
@Api(name = "ApiSceneStepInstanceBindController",desc = "ApiSceneStepInstanceBindController")
public class ApiSceneStepInstanceBindController {

    private static Logger logger = LoggerFactory.getLogger(ApiSceneStepInstanceBindController.class);

    @Autowired
    private ApiSceneStepInstanceBindService apiSceneStepInstanceBindService;

    @RequestMapping(path="/createApiSceneStepInstanceBind",method = RequestMethod.POST)
    @ApiMethod(name = "createApiSceneStepInstanceBind",desc = "createApiSceneStepInstanceBind")
    @ApiParam(name = "apiSceneStepInstanceBind",desc = "apiSceneStepInstanceBind",required = true)
    public Result<String> createApiSceneStepInstanceBind(@RequestBody @NotNull @Valid ApiSceneStepInstanceBind apiSceneStepInstanceBind){
        String id = apiSceneStepInstanceBindService.createApiSceneStepInstanceBind(apiSceneStepInstanceBind);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateApiSceneStepInstanceBind",method = RequestMethod.POST)
    @ApiMethod(name = "updateApiSceneStepInstanceBind",desc = "updateApiSceneStepInstanceBind")
    @ApiParam(name = "apiSceneStepInstanceBind",desc = "apiSceneStepInstanceBind",required = true)
    public Result<Void> updateApiSceneStepInstanceBind(@RequestBody @NotNull @Valid ApiSceneStepInstanceBind apiSceneStepInstanceBind){
        apiSceneStepInstanceBindService.updateApiSceneStepInstanceBind(apiSceneStepInstanceBind);

        return Result.ok();
    }

    @RequestMapping(path="/deleteApiSceneStepInstanceBind",method = RequestMethod.POST)
    @ApiMethod(name = "deleteApiSceneStepInstanceBind",desc = "deleteApiSceneStepInstanceBind")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteApiSceneStepInstanceBind(@NotNull String id){
        apiSceneStepInstanceBindService.deleteApiSceneStepInstanceBind(id);

        return Result.ok();
    }

    @RequestMapping(path="/findApiSceneStepInstanceBind",method = RequestMethod.POST)
    @ApiMethod(name = "findApiSceneStepInstanceBind",desc = "findApiSceneStepInstanceBind")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<ApiSceneStepInstanceBind> findApiSceneStepInstanceBind(@NotNull String id){
        ApiSceneStepInstanceBind apiSceneStepInstanceBind = apiSceneStepInstanceBindService.findApiSceneStepInstanceBind(id);

        return Result.ok(apiSceneStepInstanceBind);
    }

    @RequestMapping(path="/findAllApiSceneStepInstanceBind",method = RequestMethod.POST)
    @ApiMethod(name = "findAllApiSceneStepInstanceBind",desc = "findAllApiSceneStepInstanceBind")
    public Result<List<ApiSceneStepInstanceBind>> findAllApiSceneStepInstanceBind(){
        List<ApiSceneStepInstanceBind> apiSceneStepInstanceBindList = apiSceneStepInstanceBindService.findAllApiSceneStepInstanceBind();

        return Result.ok(apiSceneStepInstanceBindList);
    }

    @RequestMapping(path = "/findApiSceneStepInstanceBindList",method = RequestMethod.POST)
    @ApiMethod(name = "findApiSceneStepInstanceBindList",desc = "findApiSceneStepInstanceBindList")
    @ApiParam(name = "apiSceneStepInstanceBindQuery",desc = "apiSceneStepInstanceBindQuery",required = true)
    public Result<List<ApiSceneStepInstanceBind>> findApiSceneStepInstanceBindList(@RequestBody @Valid @NotNull ApiSceneStepInstanceBindQuery apiSceneStepInstanceBindQuery){
        List<ApiSceneStepInstanceBind> apiSceneStepInstanceBindList = apiSceneStepInstanceBindService.findApiSceneStepInstanceBindList(apiSceneStepInstanceBindQuery);

        return Result.ok(apiSceneStepInstanceBindList);
    }

    @RequestMapping(path = "/findApiSceneStepInstanceBindPage",method = RequestMethod.POST)
    @ApiMethod(name = "findApiSceneStepInstanceBindPage",desc = "findApiSceneStepInstanceBindPage")
    @ApiParam(name = "apiSceneStepInstanceBindQuery",desc = "apiSceneStepInstanceBindQuery",required = true)
    public Result<Pagination<ApiSceneStepInstanceBind>> findApiSceneStepInstanceBindPage(@RequestBody @Valid @NotNull ApiSceneStepInstanceBindQuery apiSceneStepInstanceBindQuery){
        Pagination<ApiSceneStepInstanceBind> pagination = apiSceneStepInstanceBindService.findApiSceneStepInstanceBindPage(apiSceneStepInstanceBindQuery);

        return Result.ok(pagination);
    }

}
