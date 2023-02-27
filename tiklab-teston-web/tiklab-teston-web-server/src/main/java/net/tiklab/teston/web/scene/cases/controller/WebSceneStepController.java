package net.tiklab.teston.web.scene.cases.controller;

import net.tiklab.core.Result;
import net.tiklab.core.page.Pagination;
import net.tiklab.postin.annotation.Api;
import net.tiklab.postin.annotation.ApiMethod;
import net.tiklab.postin.annotation.ApiParam;
import net.tiklab.teston.web.scene.cases.model.WebSceneStep;
import net.tiklab.teston.web.scene.cases.model.WebSceneStepQuery;
import net.tiklab.teston.web.scene.cases.service.WebSceneStepService;
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
 * WebSceneStepController
 */
@RestController
@RequestMapping("/webSceneStep")
@Api(name = "WebSceneStepController",desc = "WebSceneStepController")
public class WebSceneStepController {

    private static Logger logger = LoggerFactory.getLogger(WebSceneStepController.class);

    @Autowired
    private WebSceneStepService webSceneStepService;

    @RequestMapping(path="/bindWebUnit",method = RequestMethod.POST)
    @ApiMethod(name = "bindWebUnit",desc = "bindWebUnit")
    @ApiParam(name = "webSceneStep",desc = "webSceneStep",required = true)
    public Result<String> bindWebUnit(@RequestBody @NotNull @Valid List<WebSceneStep> webSceneStepList){
        webSceneStepService.bindWebUnit(webSceneStepList);

        return Result.ok();
    }


    @RequestMapping(path="/createWebSceneStep",method = RequestMethod.POST)
    @ApiMethod(name = "createWebSceneStep",desc = "createWebSceneStep")
    @ApiParam(name = "webSceneStep",desc = "webSceneStep",required = true)
    public Result<String> createWebSceneStep(@RequestBody @NotNull @Valid WebSceneStep webSceneStep){
        String id = webSceneStepService.createWebSceneStep(webSceneStep);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateWebSceneStep",method = RequestMethod.POST)
    @ApiMethod(name = "updateWebSceneStep",desc = "updateWebSceneStep")
    @ApiParam(name = "webSceneStep",desc = "webSceneStep",required = true)
    public Result<Void> updateWebSceneStep(@RequestBody @NotNull @Valid WebSceneStep webSceneStep){
        webSceneStepService.updateWebSceneStep(webSceneStep);

        return Result.ok();
    }

    @RequestMapping(path="/deleteWebSceneStep",method = RequestMethod.POST)
    @ApiMethod(name = "deleteWebSceneStep",desc = "deleteWebSceneStep")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteWebSceneStep(@NotNull String id){
        webSceneStepService.deleteWebSceneStep(id);

        return Result.ok();
    }

    @RequestMapping(path="/findWebSceneStep",method = RequestMethod.POST)
    @ApiMethod(name = "findWebSceneStep",desc = "findWebSceneStep")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<WebSceneStep> findWebSceneStep(@NotNull String id){
        WebSceneStep webSceneStep = webSceneStepService.findWebSceneStep(id);

        return Result.ok(webSceneStep);
    }

    @RequestMapping(path="/findAllWebSceneStep",method = RequestMethod.POST)
    @ApiMethod(name = "findAllWebSceneStep",desc = "findAllWebSceneStep")
    public Result<List<WebSceneStep>> findAllWebSceneStep(){
        List<WebSceneStep> webSceneStepList = webSceneStepService.findAllWebSceneStep();

        return Result.ok(webSceneStepList);
    }

    @RequestMapping(path = "/findWebSceneStepList",method = RequestMethod.POST)
    @ApiMethod(name = "findWebSceneStepList",desc = "findWebSceneStepList")
    @ApiParam(name = "webSceneStepQuery",desc = "webSceneStepQuery",required = true)
    public Result<List<WebSceneStep>> findWebSceneStepList(@RequestBody @Valid @NotNull WebSceneStepQuery webSceneStepQuery){
        List<WebSceneStep> webSceneStepList = webSceneStepService.findWebSceneStepList(webSceneStepQuery);

        return Result.ok(webSceneStepList);
    }

    @RequestMapping(path = "/findWebSceneStepPage",method = RequestMethod.POST)
    @ApiMethod(name = "findWebSceneStepPage",desc = "findWebSceneStepPage")
    @ApiParam(name = "webSceneStepQuery",desc = "webSceneStepQuery",required = true)
    public Result<Pagination<WebSceneStep>> findWebSceneStepPage(@RequestBody @Valid @NotNull WebSceneStepQuery webSceneStepQuery){
        Pagination<WebSceneStep> pagination = webSceneStepService.findWebSceneStepPage(webSceneStepQuery);

        return Result.ok(pagination);
    }

}
