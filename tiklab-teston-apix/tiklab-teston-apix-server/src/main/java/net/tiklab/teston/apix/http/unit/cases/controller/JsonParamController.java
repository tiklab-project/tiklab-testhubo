package net.tiklab.teston.apix.http.unit.cases.controller;

import net.tiklab.postin.annotation.Api;
import net.tiklab.core.page.Pagination;
import net.tiklab.core.Result;
import net.tiklab.postin.annotation.ApiMethod;
import net.tiklab.postin.annotation.ApiParam;
import net.tiklab.teston.apix.http.unit.cases.model.JsonParam;
import net.tiklab.teston.apix.http.unit.cases.model.JsonParamQuery;
import net.tiklab.teston.apix.http.unit.cases.service.JsonParamService;
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
 * 响应中 json 控制器
 */
@RestController
@RequestMapping("/jsonParam")
@Api(name = "JsonParamController",desc = "接口用例步骤json")
public class JsonParamController {

    private static Logger logger = LoggerFactory.getLogger(JsonParamController.class);

    @Autowired
    private JsonParamService jsonParamService;

    @RequestMapping(path="/createJsonParam",method = RequestMethod.POST)
    @ApiMethod(name = "createJsonParam",desc = "创建json")
    @ApiParam(name = "jsonParam",desc = "jsonParam",required = true)
    public Result<String> createJsonParam(@RequestBody @NotNull @Valid JsonParam jsonParam){
        String id = jsonParamService.createJsonParam(jsonParam);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateJsonParam",method = RequestMethod.POST)
    @ApiMethod(name = "updateJsonParam",desc = "更新json")
    @ApiParam(name = "jsonParam",desc = "jsonParam",required = true)
    public Result<Void> updateJsonParam(@RequestBody @NotNull @Valid JsonParam jsonParam){
        jsonParamService.updateJsonParam(jsonParam);

        return Result.ok();
    }

    @RequestMapping(path="/deleteJsonParam",method = RequestMethod.POST)
    @ApiMethod(name = "deleteJsonParam",desc = "删除json")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteJsonParam(@NotNull String id){
        jsonParamService.deleteJsonParam(id);

        return Result.ok();
    }

    @RequestMapping(path="/findJsonParam",method = RequestMethod.POST)
    @ApiMethod(name = "findJsonParam",desc = "根据id查找json")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<JsonParam> findJsonParam(@NotNull String id){
        JsonParam jsonParam = jsonParamService.findJsonParam(id);

        return Result.ok(jsonParam);
    }

    @RequestMapping(path="/findAllJsonParam",method = RequestMethod.POST)
    @ApiMethod(name = "findAllJsonParam",desc = "查找所有json")
    public Result<List<JsonParam>> findAllJsonParam(){
        List<JsonParam> jsonParamList = jsonParamService.findAllJsonParam();

        return Result.ok(jsonParamList);
    }

    @RequestMapping(path = "/findJsonParamList",method = RequestMethod.POST)
    @ApiMethod(name = "findJsonParamList",desc = "findJsonParamList")
    @ApiParam(name = "jsonParamQuery",desc = "jsonParamQuery",required = true)
    public Result<List<JsonParam>> findJsonParamList(@RequestBody @Valid @NotNull JsonParamQuery jsonParamQuery){
        List<JsonParam> jsonParamList = jsonParamService.findJsonParamList(jsonParamQuery);

        return Result.ok(jsonParamList);
    }
    @RequestMapping(path = "/findJsonParamListTree",method = RequestMethod.POST)
    @ApiMethod(name = "findJsonParamListTree",desc = "根据查询参数查询json列表")
    @ApiParam(name = "jsonParamQuery",desc = "查询对象",required = true)
    public Result<List<JsonParam>> findJsonParamListTree(@RequestBody @Valid @NotNull JsonParamQuery jsonParamQuery){
        List<JsonParam> jsonParamList = jsonParamService.findJsonParamListTree(jsonParamQuery);

        return Result.ok(jsonParamList);
    }

    @RequestMapping(path = "/findJsonParamPage",method = RequestMethod.POST)
    @ApiMethod(name = "findJsonParamPage",desc = "根据查询参数按分页查询json")
    @ApiParam(name = "jsonParamQuery",desc = "jsonParamQuery",required = true)
    public Result<Pagination<JsonParam>> findJsonParamPage(@RequestBody @Valid @NotNull JsonParamQuery jsonParamQuery){
        Pagination<JsonParam> pagination = jsonParamService.findJsonParamPage(jsonParamQuery);

        return Result.ok(pagination);
    }

}
