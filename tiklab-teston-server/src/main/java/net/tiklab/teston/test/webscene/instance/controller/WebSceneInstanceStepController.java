package net.tiklab.teston.test.webscene.instance.controller;

import net.tiklab.postin.annotation.Api;
import net.tiklab.core.Result;
import net.tiklab.core.page.Pagination;
import net.tiklab.teston.test.webscene.instance.model.WebSceneInstanceStep;
import net.tiklab.teston.test.webscene.instance.model.WebSceneInstanceStepQuery;
import net.tiklab.teston.test.webscene.instance.service.WebSceneInstanceStepService;
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
 * WebSceneInstanceStepController
 */
@RestController
@RequestMapping("/webSceneInstanceStep")
@Api(name = "WebSceneInstanceStepController",desc = "WebSceneInstanceStepController")
public class WebSceneInstanceStepController {

    private static Logger logger = LoggerFactory.getLogger(WebSceneInstanceStepController.class);

    @Autowired
    private WebSceneInstanceStepService webSceneInstanceStepService;

    @RequestMapping(path="/createWebSceneInstanceStep",method = RequestMethod.POST)
    @ApiMethod(name = "createWebSceneInstanceStep",desc = "createWebSceneInstanceStep")
    @ApiParam(name = "webSceneInstanceStep",desc = "webSceneInstanceStep",required = true)
    public Result<String> createWebSceneInstanceStep(@RequestBody @NotNull @Valid WebSceneInstanceStep webSceneInstanceStep){
        String id = webSceneInstanceStepService.createWebSceneInstanceStep(webSceneInstanceStep);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateWebSceneInstanceStep",method = RequestMethod.POST)
    @ApiMethod(name = "updateWebSceneInstanceStep",desc = "updateWebSceneInstanceStep")
    @ApiParam(name = "webSceneInstanceStep",desc = "webSceneInstanceStep",required = true)
    public Result<Void> updateWebSceneInstanceStep(@RequestBody @NotNull @Valid WebSceneInstanceStep webSceneInstanceStep){
        webSceneInstanceStepService.updateWebSceneInstanceStep(webSceneInstanceStep);

        return Result.ok();
    }

    @RequestMapping(path="/deleteWebSceneInstanceStep",method = RequestMethod.POST)
    @ApiMethod(name = "deleteWebSceneInstanceStep",desc = "deleteWebSceneInstanceStep")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteWebSceneInstanceStep(@NotNull String id){
        webSceneInstanceStepService.deleteWebSceneInstanceStep(id);

        return Result.ok();
    }

    @RequestMapping(path="/findWebSceneInstanceStep",method = RequestMethod.POST)
    @ApiMethod(name = "findWebSceneInstanceStep",desc = "findWebSceneInstanceStep")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<WebSceneInstanceStep> findWebSceneInstanceStep(@NotNull String id){
        WebSceneInstanceStep webSceneInstanceStep = webSceneInstanceStepService.findWebSceneInstanceStep(id);

        return Result.ok(webSceneInstanceStep);
    }

    @RequestMapping(path="/findAllWebSceneInstanceStep",method = RequestMethod.POST)
    @ApiMethod(name = "findAllWebSceneInstanceStep",desc = "findAllWebSceneInstanceStep")
    public Result<List<WebSceneInstanceStep>> findAllWebSceneInstanceStep(){
        List<WebSceneInstanceStep> webSceneInstanceStepList = webSceneInstanceStepService.findAllWebSceneInstanceStep();

        return Result.ok(webSceneInstanceStepList);
    }

    @RequestMapping(path = "/findWebSceneInstanceStepList",method = RequestMethod.POST)
    @ApiMethod(name = "findWebSceneInstanceStepList",desc = "findWebSceneInstanceStepList")
    @ApiParam(name = "webSceneInstanceStepQuery",desc = "webSceneInstanceStepQuery",required = true)
    public Result<List<WebSceneInstanceStep>> findWebSceneInstanceStepList(@RequestBody @Valid @NotNull WebSceneInstanceStepQuery webSceneInstanceStepQuery){
        List<WebSceneInstanceStep> webSceneInstanceStepList = webSceneInstanceStepService.findWebSceneInstanceStepList(webSceneInstanceStepQuery);

        return Result.ok(webSceneInstanceStepList);
    }

    @RequestMapping(path = "/findWebSceneInstanceStepPage",method = RequestMethod.POST)
    @ApiMethod(name = "findWebSceneInstanceStepPage",desc = "findWebSceneInstanceStepPage")
    @ApiParam(name = "webSceneInstanceStepQuery",desc = "webSceneInstanceStepQuery",required = true)
    public Result<Pagination<WebSceneInstanceStep>> findWebSceneInstanceStepPage(@RequestBody @Valid @NotNull WebSceneInstanceStepQuery webSceneInstanceStepQuery){
        Pagination<WebSceneInstanceStep> pagination = webSceneInstanceStepService.findWebSceneInstanceStepPage(webSceneInstanceStepQuery);

        return Result.ok(pagination);
    }

}
