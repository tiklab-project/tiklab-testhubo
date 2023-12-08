package io.thoughtware.teston.test.apix.http.unit.cases.controller;

import io.thoughtware.teston.test.apix.http.unit.cases.model.JsonParamUnit;
import io.thoughtware.teston.test.apix.http.unit.cases.model.JsonParamUnitQuery;
import io.thoughtware.teston.test.apix.http.unit.cases.service.JsonParamService;
import io.thoughtware.postin.annotation.Api;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.core.Result;
import io.thoughtware.postin.annotation.ApiMethod;
import io.thoughtware.postin.annotation.ApiParam;
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
    public Result<String> createJsonParam(@RequestBody @NotNull @Valid JsonParamUnit jsonParamUnit){
        String id = jsonParamService.createJsonParam(jsonParamUnit);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateJsonParam",method = RequestMethod.POST)
    @ApiMethod(name = "updateJsonParam",desc = "更新json")
    @ApiParam(name = "jsonParam",desc = "jsonParam",required = true)
    public Result<Void> updateJsonParam(@RequestBody @NotNull @Valid JsonParamUnit jsonParamUnit){
        jsonParamService.updateJsonParam(jsonParamUnit);

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
    public Result<JsonParamUnit> findJsonParam(@NotNull String id){
        JsonParamUnit jsonParamUnit = jsonParamService.findJsonParam(id);

        return Result.ok(jsonParamUnit);
    }

    @RequestMapping(path="/findAllJsonParam",method = RequestMethod.POST)
    @ApiMethod(name = "findAllJsonParam",desc = "查找所有json")
    public Result<List<JsonParamUnit>> findAllJsonParam(){
        List<JsonParamUnit> jsonParamUnitList = jsonParamService.findAllJsonParam();

        return Result.ok(jsonParamUnitList);
    }

    @RequestMapping(path = "/findJsonParamList",method = RequestMethod.POST)
    @ApiMethod(name = "findJsonParamList",desc = "findJsonParamList")
    @ApiParam(name = "jsonParamQuery",desc = "jsonParamQuery",required = true)
    public Result<List<JsonParamUnit>> findJsonParamList(@RequestBody @Valid @NotNull JsonParamUnitQuery jsonParamUnitQuery){
        List<JsonParamUnit> jsonParamUnitList = jsonParamService.findJsonParamList(jsonParamUnitQuery);

        return Result.ok(jsonParamUnitList);
    }


    @RequestMapping(path = "/findJsonParamPage",method = RequestMethod.POST)
    @ApiMethod(name = "findJsonParamPage",desc = "根据查询参数按分页查询json")
    @ApiParam(name = "jsonParamQuery",desc = "jsonParamQuery",required = true)
    public Result<Pagination<JsonParamUnit>> findJsonParamPage(@RequestBody @Valid @NotNull JsonParamUnitQuery jsonParamUnitQuery){
        Pagination<JsonParamUnit> pagination = jsonParamService.findJsonParamPage(jsonParamUnitQuery);

        return Result.ok(pagination);
    }

}
