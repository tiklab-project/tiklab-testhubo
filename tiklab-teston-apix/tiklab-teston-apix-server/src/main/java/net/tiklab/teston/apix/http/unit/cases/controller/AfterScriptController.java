package net.tiklab.teston.apix.http.unit.cases.controller;

import net.tiklab.postin.annotation.Api;
import net.tiklab.core.page.Pagination;
import net.tiklab.core.Result;
import net.tiklab.postin.annotation.ApiMethod;
import net.tiklab.postin.annotation.ApiParam;
import net.tiklab.teston.apix.http.unit.cases.model.AfterScript;
import net.tiklab.teston.apix.http.unit.cases.model.AfterScriptQuery;
import net.tiklab.teston.apix.http.unit.cases.service.AfterScriptService;
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
 * 后置脚本 控制器
 */
@RestController
@RequestMapping("/afterScript")
@Api(name = "AfterScriptController",desc = "接口用例步骤后置脚本管理")
public class AfterScriptController {

    private static Logger logger = LoggerFactory.getLogger(AfterScriptController.class);

    @Autowired
    private AfterScriptService afterScriptService;

    @RequestMapping(path="/createAfterScript",method = RequestMethod.POST)
    @ApiMethod(name = "createAfterScript",desc = "创建后置脚本")
    @ApiParam(name = "afterScript",desc = "afterScript",required = true)
    public Result<String> createAfterScript(@RequestBody @NotNull @Valid AfterScript afterScript){
        String id = afterScriptService.createAfterScript(afterScript);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateAfterScript",method = RequestMethod.POST)
    @ApiMethod(name = "updateAfterScript",desc = "更新后置脚本")
    @ApiParam(name = "afterScript",desc = "afterScript",required = true)
    public Result<Void> updateAfterScript(@RequestBody @NotNull @Valid AfterScript afterScript){
        afterScriptService.updateAfterScript(afterScript);

        return Result.ok();
    }

    @RequestMapping(path="/deleteAfterScript",method = RequestMethod.POST)
    @ApiMethod(name = "deleteAfterScript",desc = "删除后置脚本")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteAfterScript(@NotNull String id){
        afterScriptService.deleteAfterScript(id);

        return Result.ok();
    }

    @RequestMapping(path="/findAfterScript",method = RequestMethod.POST)
    @ApiMethod(name = "findAfterScript",desc = "根据id查找后置脚本")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<AfterScript> findAfterScript(@NotNull String id){
        AfterScript afterScript = afterScriptService.findAfterScript(id);

        return Result.ok(afterScript);
    }

    @RequestMapping(path="/findAllAfterScript",method = RequestMethod.POST)
    @ApiMethod(name = "findAllAfterScript",desc = "查找所有后置脚本")
    public Result<List<AfterScript>> findAllAfterScript(){
        List<AfterScript> afterScriptList = afterScriptService.findAllAfterScript();

        return Result.ok(afterScriptList);
    }

    @RequestMapping(path = "/findAfterScriptList",method = RequestMethod.POST)
    @ApiMethod(name = "findAfterScriptList",desc = "通过查询参数查询后置脚本列表")
    @ApiParam(name = "afterScriptQuery",desc = "afterScriptQuery",required = true)
    public Result<List<AfterScript>> findAfterScriptList(@RequestBody @Valid @NotNull AfterScriptQuery afterScriptQuery){
        List<AfterScript> afterScriptList = afterScriptService.findAfterScriptList(afterScriptQuery);

        return Result.ok(afterScriptList);
    }

    @RequestMapping(path = "/findAfterScriptPage",method = RequestMethod.POST)
    @ApiMethod(name = "findAfterScriptPage",desc = "通过查询参数按分页查询后置脚本")
    @ApiParam(name = "afterScriptQuery",desc = "afterScriptQuery",required = true)
    public Result<Pagination<AfterScript>> findAfterScriptPage(@RequestBody @Valid @NotNull AfterScriptQuery afterScriptQuery){
        Pagination<AfterScript> pagination = afterScriptService.findAfterScriptPage(afterScriptQuery);

        return Result.ok(pagination);
    }

}
