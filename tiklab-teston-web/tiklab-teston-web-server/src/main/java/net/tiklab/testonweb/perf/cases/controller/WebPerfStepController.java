package net.tiklab.testonweb.perf.cases.controller;

import net.tiklab.core.Result;
import net.tiklab.core.page.Pagination;
import net.tiklab.postin.annotation.Api;
import net.tiklab.postin.annotation.ApiMethod;
import net.tiklab.postin.annotation.ApiParam;
import net.tiklab.testonweb.perf.cases.model.WebPerfStep;
import net.tiklab.testonweb.perf.cases.model.WebPerfStepQuery;
import net.tiklab.testonweb.perf.cases.service.WebPerfStepService;
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
 * WebPerfStepController
 */
@RestController
@RequestMapping("/webPerfStep")
@Api(name = "WebPerfStepController",desc = "WebPerfStepController")
public class WebPerfStepController {

    private static Logger logger = LoggerFactory.getLogger(WebPerfStepController.class);

    @Autowired
    private WebPerfStepService webPerfStepService;


    @RequestMapping(path="/bindWebScene",method = RequestMethod.POST)
    @ApiMethod(name = "bindWebScene",desc = "bindWebScene")
    @ApiParam(name = "webPerfStep",desc = "webPerfStep",required = true)
    public Result<String> bindWebScene(@RequestBody @NotNull @Valid List<WebPerfStep> webPerfStepList){
        webPerfStepService.bindWebScene(webPerfStepList);

        return Result.ok();
    }

    @RequestMapping(path="/createWebPerfStep",method = RequestMethod.POST)
    @ApiMethod(name = "createWebPerfStep",desc = "createWebPerfStep")
    @ApiParam(name = "webPerfStep",desc = "webPerfStep",required = true)
    public Result<String> createWebPerfStep(@RequestBody @NotNull @Valid WebPerfStep webPerfStep){
        String id = webPerfStepService.createWebPerfStep(webPerfStep);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateWebPerfStep",method = RequestMethod.POST)
    @ApiMethod(name = "updateWebPerfStep",desc = "updateWebPerfStep")
    @ApiParam(name = "webPerfStep",desc = "webPerfStep",required = true)
    public Result<Void> updateWebPerfStep(@RequestBody @NotNull @Valid WebPerfStep webPerfStep){
        webPerfStepService.updateWebPerfStep(webPerfStep);

        return Result.ok();
    }

    @RequestMapping(path="/deleteWebPerfStep",method = RequestMethod.POST)
    @ApiMethod(name = "deleteWebPerfStep",desc = "deleteWebPerfStep")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteWebPerfStep(@NotNull String id){
        webPerfStepService.deleteWebPerfStep(id);

        return Result.ok();
    }

    @RequestMapping(path="/findWebPerfStep",method = RequestMethod.POST)
    @ApiMethod(name = "findWebPerfStep",desc = "findWebPerfStep")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<WebPerfStep> findWebPerfStep(@NotNull String id){
        WebPerfStep webPerfStep = webPerfStepService.findWebPerfStep(id);

        return Result.ok(webPerfStep);
    }

    @RequestMapping(path="/findAllWebPerfStep",method = RequestMethod.POST)
    @ApiMethod(name = "findAllWebPerfStep",desc = "findAllWebPerfStep")
    public Result<List<WebPerfStep>> findAllWebPerfStep(){
        List<WebPerfStep> webPerfStepList = webPerfStepService.findAllWebPerfStep();

        return Result.ok(webPerfStepList);
    }

    @RequestMapping(path = "/findWebPerfStepList",method = RequestMethod.POST)
    @ApiMethod(name = "findWebPerfStepList",desc = "findWebPerfStepList")
    @ApiParam(name = "webPerfStepQuery",desc = "webPerfStepQuery",required = true)
    public Result<List<WebPerfStep>> findWebPerfStepList(@RequestBody @Valid @NotNull WebPerfStepQuery webPerfStepQuery){
        List<WebPerfStep> webPerfStepList = webPerfStepService.findWebPerfStepList(webPerfStepQuery);

        return Result.ok(webPerfStepList);
    }

    @RequestMapping(path = "/findWebPerfStepPage",method = RequestMethod.POST)
    @ApiMethod(name = "findWebPerfStepPage",desc = "findWebPerfStepPage")
    @ApiParam(name = "webPerfStepQuery",desc = "webPerfStepQuery",required = true)
    public Result<Pagination<WebPerfStep>> findWebPerfStepPage(@RequestBody @Valid @NotNull WebPerfStepQuery webPerfStepQuery){
        Pagination<WebPerfStep> pagination = webPerfStepService.findWebPerfStepPage(webPerfStepQuery);

        return Result.ok(pagination);
    }

}
