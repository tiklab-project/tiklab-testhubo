package net.tiklab.teston.web.perf.instance.controller;

import net.tiklab.core.Result;
import net.tiklab.core.page.Pagination;
import net.tiklab.postin.annotation.Api;
import net.tiklab.postin.annotation.ApiMethod;
import net.tiklab.postin.annotation.ApiParam;
import net.tiklab.teston.web.perf.instance.model.WebPerfInstance;
import net.tiklab.teston.web.perf.instance.model.WebPerfInstanceQuery;
import net.tiklab.teston.web.perf.instance.service.WebPerfInstanceService;
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
 * web性能测试实例 控制器
 */
@RestController
@RequestMapping("/webPerfInstance")
@Api(name = "WebPerfInstanceController",desc = "web性能测试实例")
public class WebPerfInstanceController {

    private static Logger logger = LoggerFactory.getLogger(WebPerfInstanceController.class);

    @Autowired
    private WebPerfInstanceService webPerfInstanceService;

    @RequestMapping(path="/createWebPerfInstance",method = RequestMethod.POST)
    @ApiMethod(name = "createWebPerfInstance",desc = "创建性能测试汇总报告")
    @ApiParam(name = "performanceInstance",desc = "performanceInstance",required = true)
    public Result<String> createWebPerfInstance(@RequestBody @NotNull @Valid WebPerfInstance webPerfInstance){
        String id = webPerfInstanceService.createWebPerfInstance(webPerfInstance);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateWebPerfInstance",method = RequestMethod.POST)
    @ApiMethod(name = "updateWebPerfInstance",desc = "修改性能测试汇总报告")
    @ApiParam(name = "performanceInstance",desc = "performanceInstance",required = true)
    public Result<Void> updateWebPerfInstance(@RequestBody @NotNull @Valid WebPerfInstance webPerfInstance){
        webPerfInstanceService.updateWebPerfInstance(webPerfInstance);

        return Result.ok();
    }

    @RequestMapping(path="/deleteWebPerfInstance",method = RequestMethod.POST)
    @ApiMethod(name = "deleteWebPerfInstance",desc = "删除性能测试汇总报告")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteWebPerfInstance(@NotNull String id){
        webPerfInstanceService.deleteWebPerfInstance(id);

        return Result.ok();
    }

    @RequestMapping(path="/findWebPerfInstance",method = RequestMethod.POST)
    @ApiMethod(name = "findWebPerfInstance",desc = "通过id查询性能测试汇总报告")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<WebPerfInstance> findWebPerfInstance(@NotNull String id){
        WebPerfInstance performanceInstance = webPerfInstanceService.findWebPerfInstance(id);

        return Result.ok(performanceInstance);
    }

    @RequestMapping(path="/findAllWebPerfInstance",method = RequestMethod.POST)
    @ApiMethod(name = "findAllWebPerfInstance",desc = "查询所有性能测试汇总报告")
    public Result<List<WebPerfInstance>> findAllWebPerfInstance(){
        List<WebPerfInstance> performanceInstanceList = webPerfInstanceService.findAllWebPerfInstance();

        return Result.ok(performanceInstanceList);
    }

    @RequestMapping(path = "/findWebPerfInstanceList",method = RequestMethod.POST)
    @ApiMethod(name = "findWebPerfInstanceList",desc = "通过条件查询性能测试汇总报告")
    @ApiParam(name = "performanceInstanceQuery",desc = "performanceInstanceQuery",required = true)
    public Result<List<WebPerfInstance>> findWebPerfInstanceList(@RequestBody @Valid @NotNull WebPerfInstanceQuery webPerfInstanceQuery){
        List<WebPerfInstance> performanceInstanceList = webPerfInstanceService.findWebPerfInstanceList(webPerfInstanceQuery);

        return Result.ok(performanceInstanceList);
    }

    @RequestMapping(path = "/findWebPerfInstancePage",method = RequestMethod.POST)
    @ApiMethod(name = "findWebPerfInstancePage",desc = "通过条件分页查询性能测试汇总报告")
    @ApiParam(name = "performanceInstanceQuery",desc = "performanceInstanceQuery",required = true)
    public Result<Pagination<WebPerfInstance>> findWebPerfInstancePage(@RequestBody @Valid @NotNull WebPerfInstanceQuery webPerfInstanceQuery){
        Pagination<WebPerfInstance> pagination = webPerfInstanceService.findWebPerfInstancePage(webPerfInstanceQuery);

        return Result.ok(pagination);
    }

}
