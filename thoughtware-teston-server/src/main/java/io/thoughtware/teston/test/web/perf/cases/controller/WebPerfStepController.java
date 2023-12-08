package io.thoughtware.teston.test.web.perf.cases.controller;

import io.thoughtware.teston.test.web.perf.cases.model.WebPerfStep;
import io.thoughtware.teston.test.web.perf.cases.model.WebPerfStepQuery;
import io.thoughtware.teston.test.web.perf.cases.service.WebPerfStepService;
import io.thoughtware.core.Result;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.postin.annotation.Api;
import io.thoughtware.postin.annotation.ApiMethod;
import io.thoughtware.postin.annotation.ApiParam;
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
 * web性能用例下绑定的场景 控制器
 */
@RestController
@RequestMapping("/webPerfStep")
@Api(name = "WebPerfStepController",desc = "web性能用例下绑定的场景管理")
public class WebPerfStepController {

    private static Logger logger = LoggerFactory.getLogger(WebPerfStepController.class);

    @Autowired
    private WebPerfStepService webPerfStepService;


    @RequestMapping(path="/bindWebScene",method = RequestMethod.POST)
    @ApiMethod(name = "bindWebScene",desc = "绑定web场景")
    @ApiParam(name = "webPerfStepList",desc = "webPerfStepList",required = true)
    public Result<String> bindWebScene(@RequestBody @NotNull @Valid List<WebPerfStep> webPerfStepList){
        webPerfStepService.bindWebScene(webPerfStepList);

        return Result.ok();
    }

    @RequestMapping(path="/createWebPerfStep",method = RequestMethod.POST)
    @ApiMethod(name = "createWebPerfStep",desc = "创建web性能用例下绑定的场景")
    @ApiParam(name = "webPerfStep",desc = "webPerfStep",required = true)
    public Result<String> createWebPerfStep(@RequestBody @NotNull @Valid WebPerfStep webPerfStep){
        String id = webPerfStepService.createWebPerfStep(webPerfStep);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateWebPerfStep",method = RequestMethod.POST)
    @ApiMethod(name = "updateWebPerfStep",desc = "更新web性能用例下绑定的场景")
    @ApiParam(name = "webPerfStep",desc = "webPerfStep",required = true)
    public Result<Void> updateWebPerfStep(@RequestBody @NotNull @Valid WebPerfStep webPerfStep){
        webPerfStepService.updateWebPerfStep(webPerfStep);

        return Result.ok();
    }

    @RequestMapping(path="/deleteWebPerfStep",method = RequestMethod.POST)
    @ApiMethod(name = "deleteWebPerfStep",desc = "删除web性能用例下绑定的场景")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteWebPerfStep(@NotNull String id){
        webPerfStepService.deleteWebPerfStep(id);

        return Result.ok();
    }

    @RequestMapping(path="/findWebPerfStep",method = RequestMethod.POST)
    @ApiMethod(name = "findWebPerfStep",desc = "根据id查找web性能用例下绑定的场景")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<WebPerfStep> findWebPerfStep(@NotNull String id){
        WebPerfStep webPerfStep = webPerfStepService.findWebPerfStep(id);

        return Result.ok(webPerfStep);
    }

    @RequestMapping(path="/findAllWebPerfStep",method = RequestMethod.POST)
    @ApiMethod(name = "findAllWebPerfStep",desc = "查找所有web性能用例下绑定的场景")
    public Result<List<WebPerfStep>> findAllWebPerfStep(){
        List<WebPerfStep> webPerfStepList = webPerfStepService.findAllWebPerfStep();

        return Result.ok(webPerfStepList);
    }

    @RequestMapping(path = "/findWebPerfStepList",method = RequestMethod.POST)
    @ApiMethod(name = "findWebPerfStepList",desc = "根据查询参数查询web性能用例下绑定的场景列表")
    @ApiParam(name = "webPerfStepQuery",desc = "webPerfStepQuery",required = true)
    public Result<List<WebPerfStep>> findWebPerfStepList(@RequestBody @Valid @NotNull WebPerfStepQuery webPerfStepQuery){
        List<WebPerfStep> webPerfStepList = webPerfStepService.findWebPerfStepList(webPerfStepQuery);

        return Result.ok(webPerfStepList);
    }

    @RequestMapping(path = "/findWebPerfStepPage",method = RequestMethod.POST)
    @ApiMethod(name = "findWebPerfStepPage",desc = "根据查询参数按分页查询web性能用例下绑定的场景")
    @ApiParam(name = "webPerfStepQuery",desc = "webPerfStepQuery",required = true)
    public Result<Pagination<WebPerfStep>> findWebPerfStepPage(@RequestBody @Valid @NotNull WebPerfStepQuery webPerfStepQuery){
        Pagination<WebPerfStep> pagination = webPerfStepService.findWebPerfStepPage(webPerfStepQuery);

        return Result.ok(pagination);
    }

}
