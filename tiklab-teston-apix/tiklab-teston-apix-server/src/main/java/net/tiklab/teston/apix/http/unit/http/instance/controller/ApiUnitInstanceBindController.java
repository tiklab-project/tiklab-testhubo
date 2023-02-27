package net.tiklab.teston.apix.http.unit.http.instance.controller;

import net.tiklab.core.Result;
import net.tiklab.core.page.Pagination;
import net.tiklab.postin.annotation.Api;
import net.tiklab.postin.annotation.ApiMethod;
import net.tiklab.postin.annotation.ApiParam;

import net.tiklab.teston.apix.http.unit.instance.model.ApiUnitInstanceBind;
import net.tiklab.teston.apix.http.unit.instance.model.ApiUnitInstanceBindQuery;
import net.tiklab.teston.apix.http.unit.instance.service.ApiUnitInstanceBindService;
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
 * ApiUnitInstanceBindController
 */
@RestController
@RequestMapping("/apiUnitInstanceBind")
@Api(name = "ApiUnitInstanceBindController",desc = "ApiUnitInstanceBindController")
public class ApiUnitInstanceBindController {

    private static Logger logger = LoggerFactory.getLogger(ApiUnitInstanceBindController.class);

    @Autowired
    private ApiUnitInstanceBindService apiUnitInstanceBindService;

    @RequestMapping(path="/createApiUnitInstanceBind",method = RequestMethod.POST)
    @ApiMethod(name = "createApiUnitInstanceBind",desc = "createApiUnitInstanceBind")
    @ApiParam(name = "apiUnitInstanceBind",desc = "apiUnitInstanceBind",required = true)
    public Result<String> createApiUnitInstanceBind(@RequestBody @NotNull @Valid ApiUnitInstanceBind apiUnitInstanceBind){
        String id = apiUnitInstanceBindService.createApiUnitInstanceBind(apiUnitInstanceBind);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateApiUnitInstanceBind",method = RequestMethod.POST)
    @ApiMethod(name = "updateApiUnitInstanceBind",desc = "updateApiUnitInstanceBind")
    @ApiParam(name = "apiUnitInstanceBind",desc = "apiUnitInstanceBind",required = true)
    public Result<Void> updateApiUnitInstanceBind(@RequestBody @NotNull @Valid ApiUnitInstanceBind apiUnitInstanceBind){
        apiUnitInstanceBindService.updateApiUnitInstanceBind(apiUnitInstanceBind);

        return Result.ok();
    }

    @RequestMapping(path="/deleteApiUnitInstanceBind",method = RequestMethod.POST)
    @ApiMethod(name = "deleteApiUnitInstanceBind",desc = "deleteApiUnitInstanceBind")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteApiUnitInstanceBind(@NotNull String id){
        apiUnitInstanceBindService.deleteApiUnitInstanceBind(id);

        return Result.ok();
    }

    @RequestMapping(path="/findApiUnitInstanceBind",method = RequestMethod.POST)
    @ApiMethod(name = "findApiUnitInstanceBind",desc = "findApiUnitInstanceBind")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<ApiUnitInstanceBind> findApiUnitInstanceBind(@NotNull String id){
        ApiUnitInstanceBind apiUnitInstanceBind = apiUnitInstanceBindService.findApiUnitInstanceBind(id);

        return Result.ok(apiUnitInstanceBind);
    }

    @RequestMapping(path="/findAllApiUnitInstanceBind",method = RequestMethod.POST)
    @ApiMethod(name = "findAllApiUnitInstanceBind",desc = "findAllApiUnitInstanceBind")
    public Result<List<ApiUnitInstanceBind>> findAllApiUnitInstanceBind(){
        List<ApiUnitInstanceBind> apiUnitInstanceBindList = apiUnitInstanceBindService.findAllApiUnitInstanceBind();

        return Result.ok(apiUnitInstanceBindList);
    }

    @RequestMapping(path = "/findApiUnitInstanceBindList",method = RequestMethod.POST)
    @ApiMethod(name = "findApiUnitInstanceBindList",desc = "findApiUnitInstanceBindList")
    @ApiParam(name = "apiUnitInstanceBindQuery",desc = "apiUnitInstanceBindQuery",required = true)
    public Result<List<ApiUnitInstanceBind>> findApiUnitInstanceBindList(@RequestBody @Valid @NotNull ApiUnitInstanceBindQuery apiUnitInstanceBindQuery){
        List<ApiUnitInstanceBind> apiUnitInstanceBindList = apiUnitInstanceBindService.findApiUnitInstanceBindList(apiUnitInstanceBindQuery);

        return Result.ok(apiUnitInstanceBindList);
    }

    @RequestMapping(path = "/findApiUnitInstanceBindPage",method = RequestMethod.POST)
    @ApiMethod(name = "findApiUnitInstanceBindPage",desc = "findApiUnitInstanceBindPage")
    @ApiParam(name = "apiUnitInstanceBindQuery",desc = "apiUnitInstanceBindQuery",required = true)
    public Result<Pagination<ApiUnitInstanceBind>> findApiUnitInstanceBindPage(@RequestBody @Valid @NotNull ApiUnitInstanceBindQuery apiUnitInstanceBindQuery){
        Pagination<ApiUnitInstanceBind> pagination = apiUnitInstanceBindService.findApiUnitInstanceBindPage(apiUnitInstanceBindQuery);

        return Result.ok(pagination);
    }

}
