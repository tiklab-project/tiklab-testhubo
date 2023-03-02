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
 * Web环境控制器
 */
@RestController
@RequestMapping("/webEnv")
@Api(name = "WebEnvController",desc = "Web环境管理")
public class WebEnvController {

    private static Logger logger = LoggerFactory.getLogger(WebEnvController.class);

    @Autowired
    private WebEnvService webEnvService;

    @RequestMapping(path="/createWebEnv",method = RequestMethod.POST)
    @ApiMethod(name = "createWebEnv",desc = "创建Web环境")
    @ApiParam(name = "webEnv",desc = "webEnv",required = true)
    public Result<String> createWebEnv(@RequestBody @NotNull @Valid WebEnv webEnv){
        String id = webEnvService.createWebEnv(webEnv);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateWebEnv",method = RequestMethod.POST)
    @ApiMethod(name = "updateWebEnv",desc = "更新Web环境")
    @ApiParam(name = "webEnv",desc = "webEnv",required = true)
    public Result<Void> updateWebEnv(@RequestBody @NotNull @Valid WebEnv webEnv){
        webEnvService.updateWebEnv(webEnv);

        return Result.ok();
    }

    @RequestMapping(path="/deleteWebEnv",method = RequestMethod.POST)
    @ApiMethod(name = "deleteWebEnv",desc = "删除Web环境")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteWebEnv(@NotNull String id){
        webEnvService.deleteWebEnv(id);

        return Result.ok();
    }

    @RequestMapping(path="/findWebEnv",method = RequestMethod.POST)
    @ApiMethod(name = "findWebEnv",desc = "根据id查找Web环境")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<WebEnv> findWebEnv(@NotNull String id){
        WebEnv webEnv = webEnvService.findWebEnv(id);

        return Result.ok(webEnv);
    }

    @RequestMapping(path="/findAllWebEnv",method = RequestMethod.POST)
    @ApiMethod(name = "findAllWebEnv",desc = "查找所有Web环境")
    public Result<List<WebEnv>> findAllWebEnv(){
        List<WebEnv> webEnvList = webEnvService.findAllWebEnv();

        return Result.ok(webEnvList);
    }

    @RequestMapping(path = "/findWebEnvList",method = RequestMethod.POST)
    @ApiMethod(name = "findWebEnvList",desc = "根据查询参数查询Web环境列表")
    @ApiParam(name = "webEnvQuery",desc = "webEnvQuery",required = true)
    public Result<List<WebEnv>> findWebEnvList(@RequestBody @Valid @NotNull WebEnvQuery webEnvQuery){
        List<WebEnv> webEnvList = webEnvService.findWebEnvList(webEnvQuery);

        return Result.ok(webEnvList);
    }

    @RequestMapping(path = "/findWebEnvPage",method = RequestMethod.POST)
    @ApiMethod(name = "findWebEnvPage",desc = "根据查询参数按分页查询Web环境")
    @ApiParam(name = "webEnvQuery",desc = "webEnvQuery",required = true)
    public Result<Pagination<WebEnv>> findWebEnvPage(@RequestBody @Valid @NotNull WebEnvQuery webEnvQuery){
        Pagination<WebEnv> pagination = webEnvService.findWebEnvPage(webEnvQuery);

        return Result.ok(pagination);
    }

}
