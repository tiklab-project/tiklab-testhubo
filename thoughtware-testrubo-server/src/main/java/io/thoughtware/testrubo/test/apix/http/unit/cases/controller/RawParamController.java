package io.thoughtware.testrubo.test.apix.http.unit.cases.controller;

import io.thoughtware.testrubo.test.apix.http.unit.cases.model.RawParamUnit;
import io.thoughtware.testrubo.test.apix.http.unit.cases.model.RawParamUnitQuery;
import io.thoughtware.testrubo.test.apix.http.unit.cases.service.RawParamService;
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
 * raw类型 控制器
 */
@RestController
@RequestMapping("/rawParam")
@Api(name = "RawParamController",desc = "接口用例步骤RawParamController")
public class RawParamController {

    private static Logger logger = LoggerFactory.getLogger(RawParamController.class);

    @Autowired
    private RawParamService rawParamService;

    @RequestMapping(path="/createRawParam",method = RequestMethod.POST)
    @ApiMethod(name = "createRawParam",desc = "创建raw")
    @ApiParam(name = "rawParam",desc = "rawParam",required = true)
    public Result<String> createRawParam(@RequestBody @NotNull @Valid RawParamUnit rawParamUnit){
        String id = rawParamService.createRawParam(rawParamUnit);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateRawParam",method = RequestMethod.POST)
    @ApiMethod(name = "updateRawParam",desc = "更新raw")
    @ApiParam(name = "rawParam",desc = "rawParam",required = true)
    public Result<Void> updateRawParam(@RequestBody @NotNull @Valid RawParamUnit rawParamUnit){
        rawParamService.updateRawParam(rawParamUnit);

        return Result.ok();
    }

    @RequestMapping(path="/deleteRawParam",method = RequestMethod.POST)
    @ApiMethod(name = "deleteRawParam",desc = "删除raw")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteRawParam(@NotNull String id){
        rawParamService.deleteRawParam(id);

        return Result.ok();
    }

    @RequestMapping(path="/findRawParam",method = RequestMethod.POST)
    @ApiMethod(name = "findRawParam",desc = "根据id查找raw")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<RawParamUnit> findRawParam(@NotNull String id){
        RawParamUnit rawParamUnit = rawParamService.findRawParam(id);

        return Result.ok(rawParamUnit);
    }

    @RequestMapping(path="/findAllRawParam",method = RequestMethod.POST)
    @ApiMethod(name = "findAllRawParam",desc = "查找所有raw")
    public Result<List<RawParamUnit>> findAllRawParam(){
        List<RawParamUnit> rawParamUnitList = rawParamService.findAllRawParam();

        return Result.ok(rawParamUnitList);
    }

    @RequestMapping(path = "/findRawParamList",method = RequestMethod.POST)
    @ApiMethod(name = "findRawParamList",desc = "根据查询参数查询raw列表")
    @ApiParam(name = "rawParamQuery",desc = "rawParamQuery",required = true)
    public Result<List<RawParamUnit>> findRawParamList(@RequestBody @Valid @NotNull RawParamUnitQuery rawParamUnitQuery){
        List<RawParamUnit> rawParamUnitList = rawParamService.findRawParamList(rawParamUnitQuery);

        return Result.ok(rawParamUnitList);
    }

    @RequestMapping(path = "/findRawParamPage",method = RequestMethod.POST)
    @ApiMethod(name = "findRawParamPage",desc = "根据查询参数按分页查询raw")
    @ApiParam(name = "rawParamQuery",desc = "rawParamQuery",required = true)
    public Result<Pagination<RawParamUnit>> findRawParamPage(@RequestBody @Valid @NotNull RawParamUnitQuery rawParamUnitQuery){
        Pagination<RawParamUnit> pagination = rawParamService.findRawParamPage(rawParamUnitQuery);

        return Result.ok(pagination);
    }

}
