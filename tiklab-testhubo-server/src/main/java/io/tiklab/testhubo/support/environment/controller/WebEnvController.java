package io.tiklab.testhubo.support.environment.controller;

import io.tiklab.postin.annotation.Api;
import io.tiklab.core.Result;
import io.tiklab.core.page.Pagination;
import io.tiklab.testhubo.support.environment.model.WebEnv;
import io.tiklab.testhubo.support.environment.model.WebEnvQuery;
import io.tiklab.testhubo.support.environment.service.WebEnvService;
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
 * 
 * @pi.protocol: http
 * @pi.groupName: Web环境控制器
 */
@RestController
@RequestMapping("/webEnv")
@Api(name = "WebEnvController",desc = "Web环境管理")
public class WebEnvController {

    private static Logger logger = LoggerFactory.getLogger(WebEnvController.class);

    @Autowired
    private WebEnvService webEnvService;
    
    /**
     * @pi.name:createWebEnv
     * @pi.path:/webEnv/createWebEnv
     * @pi.methodType:post
     * @pi.request-type:json
     * @pi.param: model=WebEnv
     */
    @RequestMapping(path="/createWebEnv",method = RequestMethod.POST)
    @ApiMethod(name = "createWebEnv",desc = "创建Web环境")
    @ApiParam(name = "webEnv",desc = "webEnv",required = true)
    public Result<String> createWebEnv(@RequestBody @NotNull @Valid WebEnv webEnv){
        String id = webEnvService.createWebEnv(webEnv);

        return Result.ok(id);
    }

    /**
     * @pi.name:updateWebEnv
     * @pi.path:/webEnv/updateWebEnv
     * @pi.methodType:post
     * @pi.request-type:json
     * @pi.param: model=WebEnv
     */
    @RequestMapping(path="/updateWebEnv",method = RequestMethod.POST)
    @ApiMethod(name = "updateWebEnv",desc = "更新Web环境")
    @ApiParam(name = "webEnv",desc = "webEnv",required = true)
    public Result<Void> updateWebEnv(@RequestBody @NotNull @Valid WebEnv webEnv){
        webEnvService.updateWebEnv(webEnv);

        return Result.ok();
    }

    /**
     * @pi.name:deleteWebEnv
     * @pi.path:/webEnv/deleteWebEnv
     * @pi.methodType:post
     * @pi.request-type:json
     * @pi.param: name=id;dataType=string;value=id;desc=当前删除的id
     */
    @RequestMapping(path="/deleteWebEnv",method = RequestMethod.POST)
    @ApiMethod(name = "deleteWebEnv",desc = "删除Web环境")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteWebEnv(@NotNull String id){
        webEnvService.deleteWebEnv(id);

        return Result.ok();
    }

    /**
     * @pi.name:findWebEnv
     * @pi.path:/webEnv/findWebEnv
     * @pi.methodType:post
     * @pi.request-type:formdata
     * @pi.param: name=id;dataType=string;value=id;desc=当前查找的id
     */
    @RequestMapping(path="/findWebEnv",method = RequestMethod.POST)
    @ApiMethod(name = "findWebEnv",desc = "根据id查找Web环境")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<WebEnv> findWebEnv(@NotNull String id){
        WebEnv webEnv = webEnvService.findWebEnv(id);

        return Result.ok(webEnv);
    }

    /**
     * @pi.name:findAllWebEnv
     * @pi.path:/webEnv/findAllWebEnv
     * @pi.methodType:post
     * @pi.request-type:none
     */
    @RequestMapping(path="/findAllWebEnv",method = RequestMethod.POST)
    @ApiMethod(name = "findAllWebEnv",desc = "查找所有Web环境")
    public Result<List<WebEnv>> findAllWebEnv(){
        List<WebEnv> webEnvList = webEnvService.findAllWebEnv();

        return Result.ok(webEnvList);
    }

    /**
     * @pi.name:findWebEnvList
     * @pi.path:/webEnv/findWebEnvList
     * @pi.methodType:post
     * @pi.request-type:json
     * @pi.param: model=WebEnv
     */
    @RequestMapping(path = "/findWebEnvList",method = RequestMethod.POST)
    @ApiMethod(name = "findWebEnvList",desc = "根据查询参数查询Web环境列表")
    @ApiParam(name = "webEnvQuery",desc = "webEnvQuery",required = true)
    public Result<List<WebEnv>> findWebEnvList(@RequestBody @Valid @NotNull WebEnvQuery webEnvQuery){
        List<WebEnv> webEnvList = webEnvService.findWebEnvList(webEnvQuery);

        return Result.ok(webEnvList);
    }

    /**
     * @pi.name:findWebEnvPage
     * @pi.path:/webEnv/findWebEnvPage
     * @pi.methodType:post
     * @pi.request-type:json
     * @pi.param: model=WebEnvQuery
     */
    @RequestMapping(path = "/findWebEnvPage",method = RequestMethod.POST)
    @ApiMethod(name = "findWebEnvPage",desc = "根据查询参数按分页查询Web环境")
    @ApiParam(name = "webEnvQuery",desc = "webEnvQuery",required = true)
    public Result<Pagination<WebEnv>> findWebEnvPage(@RequestBody @Valid @NotNull WebEnvQuery webEnvQuery){
        Pagination<WebEnv> pagination = webEnvService.findWebEnvPage(webEnvQuery);

        return Result.ok(pagination);
    }

}
