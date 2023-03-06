package net.tiklab.teston.test.web.scene.cases.controller;

import net.tiklab.core.Result;
import net.tiklab.core.page.Pagination;
import net.tiklab.postin.annotation.Api;
import net.tiklab.postin.annotation.ApiMethod;
import net.tiklab.postin.annotation.ApiParam;
import net.tiklab.teston.test.web.scene.cases.model.WebSceneStep;
import net.tiklab.teston.test.web.scene.cases.model.WebSceneStepQuery;
import net.tiklab.teston.test.web.scene.cases.service.WebSceneStepService;
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
 * web场景下步骤 服务
 */
@RestController
@RequestMapping("/webSceneStep")
@Api(name = "WebSceneStepController",desc = "web场景下步骤管理")
public class WebSceneStepController {

    private static Logger logger = LoggerFactory.getLogger(WebSceneStepController.class);

    @Autowired
    private WebSceneStepService webSceneStepService;

    @RequestMapping(path="/createWebSceneStep",method = RequestMethod.POST)
    @ApiMethod(name = "createWebSceneStep",desc = "创建web场景下步骤")
    @ApiParam(name = "webSceneStep",desc = "webSceneStep",required = true)
    public Result<String> createWebSceneStep(@RequestBody @NotNull @Valid WebSceneStep webSceneStep){
        String id = webSceneStepService.createWebSceneStep(webSceneStep);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateWebSceneStep",method = RequestMethod.POST)
    @ApiMethod(name = "updateWebSceneStep",desc = "更新web场景下步骤")
    @ApiParam(name = "webSceneStep",desc = "webSceneStep",required = true)
    public Result<Void> updateWebSceneStep(@RequestBody @NotNull @Valid WebSceneStep webSceneStep){
        webSceneStepService.updateWebSceneStep(webSceneStep);

        return Result.ok();
    }

    @RequestMapping(path="/deleteWebSceneStep",method = RequestMethod.POST)
    @ApiMethod(name = "deleteWebSceneStep",desc = "删除web场景下步骤")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteWebSceneStep(@NotNull String id){
        webSceneStepService.deleteWebSceneStep(id);

        return Result.ok();
    }

    @RequestMapping(path="/findWebSceneStep",method = RequestMethod.POST)
    @ApiMethod(name = "findWebSceneStep",desc = "根据id查找web场景下步骤")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<WebSceneStep> findWebSceneStep(@NotNull String id){
        WebSceneStep webSceneStep = webSceneStepService.findWebSceneStep(id);

        return Result.ok(webSceneStep);
    }

    @RequestMapping(path="/findAllWebSceneStep",method = RequestMethod.POST)
    @ApiMethod(name = "findAllWebSceneStep",desc = "查找所有web场景下步骤")
    public Result<List<WebSceneStep>> findAllWebSceneStep(){
        List<WebSceneStep> webSceneStepList = webSceneStepService.findAllWebSceneStep();

        return Result.ok(webSceneStepList);
    }

    @RequestMapping(path = "/findWebSceneStepList",method = RequestMethod.POST)
    @ApiMethod(name = "findWebSceneStepList",desc = "根据查询参数查询web场景下步骤列表")
    @ApiParam(name = "webSceneStepQuery",desc = "webSceneStepQuery",required = true)
    public Result<List<WebSceneStep>> findWebSceneStepList(@RequestBody @Valid @NotNull WebSceneStepQuery webSceneStepQuery){
        List<WebSceneStep> webSceneStepList = webSceneStepService.findWebSceneStepList(webSceneStepQuery);

        return Result.ok(webSceneStepList);
    }

    @RequestMapping(path = "/findWebSceneStepPage",method = RequestMethod.POST)
    @ApiMethod(name = "findWebSceneStepPage",desc = "根据查询参数按分页查询web场景下步骤")
    @ApiParam(name = "webSceneStepQuery",desc = "webSceneStepQuery",required = true)
    public Result<Pagination<WebSceneStep>> findWebSceneStepPage(@RequestBody @Valid @NotNull WebSceneStepQuery webSceneStepQuery){
        Pagination<WebSceneStep> pagination = webSceneStepService.findWebSceneStepPage(webSceneStepQuery);

        return Result.ok(pagination);
    }

}
