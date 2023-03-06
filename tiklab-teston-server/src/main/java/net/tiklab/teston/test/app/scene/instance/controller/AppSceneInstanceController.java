package net.tiklab.teston.test.app.scene.instance.controller;

import net.tiklab.core.Result;
import net.tiklab.core.page.Pagination;
import net.tiklab.postin.annotation.Api;
import net.tiklab.postin.annotation.ApiMethod;
import net.tiklab.postin.annotation.ApiParam;
import net.tiklab.teston.test.app.scene.instance.model.AppSceneInstance;
import net.tiklab.teston.test.app.scene.instance.model.AppSceneInstanceQuery;
import net.tiklab.teston.test.app.scene.instance.service.AppSceneInstanceService;
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
 * app场景实例 控制器
 */
@RestController
@RequestMapping("/appSceneInstance")
@Api(name = "AppSceneInstanceController",desc = "app场景实例")
public class AppSceneInstanceController {

    private static Logger logger = LoggerFactory.getLogger(AppSceneInstanceController.class);

    @Autowired
    private AppSceneInstanceService appSceneInstanceService;

    @RequestMapping(path="/createAppSceneInstance",method = RequestMethod.POST)
    @ApiMethod(name = "createAppSceneInstance",desc = "创建app场景测试历史实例")
    @ApiParam(name = "testInstance",desc = "testInstance",required = true)
    public Result<String> createAppSceneInstance(@RequestBody @NotNull @Valid AppSceneInstance testInstance){
        String id = appSceneInstanceService.createAppSceneInstance(testInstance);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateAppSceneInstance",method = RequestMethod.POST)
    @ApiMethod(name = "updateAppSceneInstance",desc = "更新app场景测试历史实例")
    @ApiParam(name = "testInstance",desc = "testInstance",required = true)
    public Result<Void> updateAppSceneInstance(@RequestBody @NotNull @Valid AppSceneInstance testInstance){
        appSceneInstanceService.updateAppSceneInstance(testInstance);

        return Result.ok();
    }

    @RequestMapping(path="/deleteAppSceneInstance",method = RequestMethod.POST)
    @ApiMethod(name = "deleteAppSceneInstance",desc = "删除app场景测试历史实例")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteAppSceneInstance(@NotNull String id){
        appSceneInstanceService.deleteAppSceneInstance(id);

        return Result.ok();
    }

    @RequestMapping(path="/findAppSceneInstance",method = RequestMethod.POST)
    @ApiMethod(name = "findAppSceneInstance",desc = "根据id查找app场景测试历史实例")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<AppSceneInstance> findAppSceneInstance(@NotNull String id){
        AppSceneInstance testInstance = appSceneInstanceService.findAppSceneInstance(id);

        return Result.ok(testInstance);
    }

    @RequestMapping(path="/findAllAppSceneInstance",method = RequestMethod.POST)
    @ApiMethod(name = "findAllAppSceneInstance",desc = "查找所有app场景测试历史实例")
    public Result<List<AppSceneInstance>> findAllAppSceneInstance(){
        List<AppSceneInstance> allAppSceneInstance = appSceneInstanceService.findAllAppSceneInstance();

        return Result.ok(allAppSceneInstance);
    }

    @RequestMapping(path = "/findAppSceneInstanceList",method = RequestMethod.POST)
    @ApiMethod(name = "findAppSceneInstanceList",desc = "根据查询参数查询app场景测试历史实例列表")
    @ApiParam(name = "testInstanceQuery",desc = "testInstanceQuery",required = true)
    public Result<List<AppSceneInstance>> findAppSceneInstanceList(@RequestBody @Valid @NotNull AppSceneInstanceQuery testInstanceQuery){
        List<AppSceneInstance> testInstanceList = appSceneInstanceService.findAppSceneInstanceList(testInstanceQuery);

        return Result.ok(testInstanceList);
    }

    @RequestMapping(path = "/findAppSceneInstancePage",method = RequestMethod.POST)
    @ApiMethod(name = "findAppSceneInstancePage",desc = "根据查询参数按分页查询app场景测试历史实例")
    @ApiParam(name = "testInstanceQuery",desc = "testInstanceQuery",required = true)
    public Result<Pagination<AppSceneInstance>> findAppSceneInstancePage(@RequestBody @Valid @NotNull AppSceneInstanceQuery testInstanceQuery){
        Pagination<AppSceneInstance> pagination = appSceneInstanceService.findAppSceneInstancePage(testInstanceQuery);

        return Result.ok(pagination);
    }


}
