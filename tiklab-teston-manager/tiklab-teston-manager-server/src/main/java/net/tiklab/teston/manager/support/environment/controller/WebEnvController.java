package net.tiklab.teston.manager.support.environment.controller;

import net.tiklab.postin.annotation.Api;
import net.tiklab.core.Result;
import net.tiklab.core.page.Pagination;
import net.tiklab.teston.manager.support.environment.model.WebEnv;
import net.tiklab.teston.manager.support.environment.model.WebEnvQuery;
import net.tiklab.teston.manager.support.environment.service.WebEnvService;
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
 * WebEnvController
 */
@RestController
@RequestMapping("/webEnv")
@Api(name = "WebEnvController",desc = "WebEnvController")
public class WebEnvController {

    private static Logger logger = LoggerFactory.getLogger(WebEnvController.class);

    @Autowired
    private WebEnvService webEnvService;

    @RequestMapping(path="/createWebEnv",method = RequestMethod.POST)
    @ApiMethod(name = "createWebEnv",desc = "createWebEnv")
    @ApiParam(name = "webEnv",desc = "webEnv",required = true)
    public Result<String> createWebEnv(@RequestBody @NotNull @Valid WebEnv webEnv){
        String id = webEnvService.createWebEnv(webEnv);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateWebEnv",method = RequestMethod.POST)
    @ApiMethod(name = "updateWebEnv",desc = "updateWebEnv")
    @ApiParam(name = "webEnv",desc = "webEnv",required = true)
    public Result<Void> updateWebEnv(@RequestBody @NotNull @Valid WebEnv webEnv){
        webEnvService.updateWebEnv(webEnv);

        return Result.ok();
    }

    @RequestMapping(path="/deleteWebEnv",method = RequestMethod.POST)
    @ApiMethod(name = "deleteWebEnv",desc = "deleteWebEnv")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteWebEnv(@NotNull String id){
        webEnvService.deleteWebEnv(id);

        return Result.ok();
    }

    @RequestMapping(path="/findWebEnv",method = RequestMethod.POST)
    @ApiMethod(name = "findWebEnv",desc = "findWebEnv")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<WebEnv> findWebEnv(@NotNull String id){
        WebEnv webEnv = webEnvService.findWebEnv(id);

        return Result.ok(webEnv);
    }

    @RequestMapping(path="/findAllWebEnv",method = RequestMethod.POST)
    @ApiMethod(name = "findAllWebEnv",desc = "findAllWebEnv")
    public Result<List<WebEnv>> findAllWebEnv(){
        List<WebEnv> webEnvList = webEnvService.findAllWebEnv();

        return Result.ok(webEnvList);
    }

    @RequestMapping(path = "/findWebEnvList",method = RequestMethod.POST)
    @ApiMethod(name = "findWebEnvList",desc = "findWebEnvList")
    @ApiParam(name = "webEnvQuery",desc = "webEnvQuery",required = true)
    public Result<List<WebEnv>> findWebEnvList(@RequestBody @Valid @NotNull WebEnvQuery webEnvQuery){
        List<WebEnv> webEnvList = webEnvService.findWebEnvList(webEnvQuery);

        return Result.ok(webEnvList);
    }

    @RequestMapping(path = "/findWebEnvPage",method = RequestMethod.POST)
    @ApiMethod(name = "findWebEnvPage",desc = "findWebEnvPage")
    @ApiParam(name = "webEnvQuery",desc = "webEnvQuery",required = true)
    public Result<Pagination<WebEnv>> findWebEnvPage(@RequestBody @Valid @NotNull WebEnvQuery webEnvQuery){
        Pagination<WebEnv> pagination = webEnvService.findWebEnvPage(webEnvQuery);

        return Result.ok(pagination);
    }

}
