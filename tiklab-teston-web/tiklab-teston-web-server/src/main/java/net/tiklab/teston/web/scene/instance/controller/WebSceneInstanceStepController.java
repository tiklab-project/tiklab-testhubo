package net.tiklab.teston.web.scene.instance.controller;

import net.tiklab.core.Result;
import net.tiklab.core.page.Pagination;
import net.tiklab.postin.annotation.Api;
import net.tiklab.postin.annotation.ApiMethod;
import net.tiklab.postin.annotation.ApiParam;
import net.tiklab.teston.web.scene.instance.model.WebSceneInstanceStep;
import net.tiklab.teston.web.scene.instance.model.WebSceneInstanceStepQuery;
import net.tiklab.teston.web.scene.instance.service.WebSceneInstanceStepService;
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
 * web场景下步骤实例 控制器
 */
@RestController
@RequestMapping("/webSceneInstanceStep")
@Api(name = "WebSceneInstanceStepController",desc = "web场景下步骤实例管理")
public class WebSceneInstanceStepController {

    private static Logger logger = LoggerFactory.getLogger(WebSceneInstanceStepController.class);

    @Autowired
    private WebSceneInstanceStepService webSceneInstanceStepService;

    @RequestMapping(path="/createWebSceneInstanceStep",method = RequestMethod.POST)
    @ApiMethod(name = "createWebSceneInstanceStep",desc = "创建web场景下步骤实例")
    @ApiParam(name = "webSceneInstanceStep",desc = "webSceneInstanceStep",required = true)
    public Result<String> createWebSceneInstanceStep(@RequestBody @NotNull @Valid WebSceneInstanceStep webSceneInstanceStep){
        String id = webSceneInstanceStepService.createWebSceneInstanceStep(webSceneInstanceStep);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateWebSceneInstanceStep",method = RequestMethod.POST)
    @ApiMethod(name = "updateWebSceneInstanceStep",desc = "更新web场景下步骤实例")
    @ApiParam(name = "webSceneInstanceStep",desc = "webSceneInstanceStep",required = true)
    public Result<Void> updateWebSceneInstanceStep(@RequestBody @NotNull @Valid WebSceneInstanceStep webSceneInstanceStep){
        webSceneInstanceStepService.updateWebSceneInstanceStep(webSceneInstanceStep);

        return Result.ok();
    }

    @RequestMapping(path="/deleteWebSceneInstanceStep",method = RequestMethod.POST)
    @ApiMethod(name = "deleteWebSceneInstanceStep",desc = "删除web场景下步骤实例")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteWebSceneInstanceStep(@NotNull String id){
        webSceneInstanceStepService.deleteWebSceneInstanceStep(id);

        return Result.ok();
    }

    @RequestMapping(path="/findWebSceneInstanceStep",method = RequestMethod.POST)
    @ApiMethod(name = "findWebSceneInstanceStep",desc = "根据id查找web场景下步骤实例")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<WebSceneInstanceStep> findWebSceneInstanceStep(@NotNull String id){
        WebSceneInstanceStep webSceneInstanceStep = webSceneInstanceStepService.findWebSceneInstanceStep(id);

        return Result.ok(webSceneInstanceStep);
    }

    @RequestMapping(path="/findAllWebSceneInstanceStep",method = RequestMethod.POST)
    @ApiMethod(name = "findAllWebSceneInstanceStep",desc = "查找所有web场景下步骤实例")
    public Result<List<WebSceneInstanceStep>> findAllWebSceneInstanceStep(){
        List<WebSceneInstanceStep> webSceneInstanceStepList = webSceneInstanceStepService.findAllWebSceneInstanceStep();

        return Result.ok(webSceneInstanceStepList);
    }

    @RequestMapping(path = "/findWebSceneInstanceStepList",method = RequestMethod.POST)
    @ApiMethod(name = "findWebSceneInstanceStepList",desc = "根据查询参数查询web场景下步骤实例列表")
    @ApiParam(name = "webSceneInstanceStepQuery",desc = "webSceneInstanceStepQuery",required = true)
    public Result<List<WebSceneInstanceStep>> findWebSceneInstanceStepList(@RequestBody @Valid @NotNull WebSceneInstanceStepQuery webSceneInstanceStepQuery){
        List<WebSceneInstanceStep> webSceneInstanceStepList = webSceneInstanceStepService.findWebSceneInstanceStepList(webSceneInstanceStepQuery);

        return Result.ok(webSceneInstanceStepList);
    }

    @RequestMapping(path = "/findWebSceneInstanceStepPage",method = RequestMethod.POST)
    @ApiMethod(name = "findWebSceneInstanceStepPage",desc = "根据查询参数按分页查询web场景下步骤实例")
    @ApiParam(name = "webSceneInstanceStepQuery",desc = "webSceneInstanceStepQuery",required = true)
    public Result<Pagination<WebSceneInstanceStep>> findWebSceneInstanceStepPage(@RequestBody @Valid @NotNull WebSceneInstanceStepQuery webSceneInstanceStepQuery){
        Pagination<WebSceneInstanceStep> pagination = webSceneInstanceStepService.findWebSceneInstanceStepPage(webSceneInstanceStepQuery);

        return Result.ok(pagination);
    }

}
