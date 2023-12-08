package io.thoughtware.teston.test.web.scene.instance.controller;

import io.thoughtware.core.Result;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.postin.annotation.Api;
import io.thoughtware.postin.annotation.ApiMethod;
import io.thoughtware.postin.annotation.ApiParam;
import io.thoughtware.teston.test.web.scene.instance.model.WebSceneInstanceQuery;
import io.thoughtware.teston.test.web.scene.instance.model.WebSceneInstance;
import io.thoughtware.teston.test.web.scene.instance.service.WebSceneInstanceService;
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
 * web场景实例 控制器
 */
@RestController
@RequestMapping("/webSceneInstance")
@Api(name = "WebSceneInstanceController",desc = "web场景测试实例")
public class WebSceneInstanceController {

    private static Logger logger = LoggerFactory.getLogger(WebSceneInstanceController.class);

    @Autowired
    private WebSceneInstanceService webSceneInstanceService;

    @RequestMapping(path="/createWebSceneInstance",method = RequestMethod.POST)
    @ApiMethod(name = "createWebSceneInstance",desc = "创建web场景实例")
    @ApiParam(name = "webSceneInstance",desc = "webSceneInstance",required = true)
    public Result<String> createWebSceneInstance(@RequestBody @NotNull @Valid WebSceneInstance webSceneInstance){
        String id = webSceneInstanceService.createWebSceneInstance(webSceneInstance);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateWebSceneInstance",method = RequestMethod.POST)
    @ApiMethod(name = "updateWebSceneInstance",desc = "更新web场景实例")
    @ApiParam(name = "webSceneInstance",desc = "webSceneInstance",required = true)
    public Result<Void> updateWebSceneInstance(@RequestBody @NotNull @Valid WebSceneInstance webSceneInstance){
        webSceneInstanceService.updateWebSceneInstance(webSceneInstance);

        return Result.ok();
    }

    @RequestMapping(path="/deleteWebSceneInstance",method = RequestMethod.POST)
    @ApiMethod(name = "deleteWebSceneInstance",desc = "删除web场景实例")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteWebSceneInstance(@NotNull String id){
        webSceneInstanceService.deleteWebSceneInstance(id);

        return Result.ok();
    }

    @RequestMapping(path="/findWebSceneInstance",method = RequestMethod.POST)
    @ApiMethod(name = "findWebSceneInstance",desc = "根据id查找web场景实例")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<WebSceneInstance> findWebSceneInstance(@NotNull String id){
        WebSceneInstance webSceneInstance = webSceneInstanceService.findWebSceneInstance(id);

        return Result.ok(webSceneInstance);
    }

    @RequestMapping(path="/findAllWebSceneInstance",method = RequestMethod.POST)
    @ApiMethod(name = "findAllWebSceneInstance",desc = "查找所有web场景实例")
    public Result<List<WebSceneInstance>> findAllWebSceneInstance(){
        List<WebSceneInstance> allWebSceneInstance = webSceneInstanceService.findAllWebSceneInstance();

        return Result.ok(allWebSceneInstance);
    }

    @RequestMapping(path = "/findWebSceneInstanceList",method = RequestMethod.POST)
    @ApiMethod(name = "findWebSceneInstanceList",desc = "根据查询参数查询web场景实例列表")
    @ApiParam(name = "webSceneInstanceQuery",desc = "webSceneInstanceQuery",required = true)
    public Result<List<WebSceneInstance>> findWebSceneInstanceList(@RequestBody @Valid @NotNull WebSceneInstanceQuery webSceneInstanceQuery){
        List<WebSceneInstance> webSceneInstanceList = webSceneInstanceService.findWebSceneInstanceList(webSceneInstanceQuery);

        return Result.ok(webSceneInstanceList);
    }

    @RequestMapping(path = "/findWebSceneInstancePage",method = RequestMethod.POST)
    @ApiMethod(name = "findWebSceneInstancePage",desc = "根据查询参数按分页查询web场景实例")
    @ApiParam(name = "webSceneInstanceQuery",desc = "webSceneInstanceQuery",required = true)
    public Result<Pagination<WebSceneInstance>> findWebSceneInstancePage(@RequestBody @Valid @NotNull WebSceneInstanceQuery webSceneInstanceQuery){
        Pagination<WebSceneInstance> pagination = webSceneInstanceService.findWebSceneInstancePage(webSceneInstanceQuery);

        return Result.ok(pagination);
    }

}
