package net.tiklab.teston.apix.http.unit.cases.controller;

import net.tiklab.postin.annotation.Api;
import net.tiklab.core.page.Pagination;
import net.tiklab.core.Result;
import net.tiklab.postin.annotation.ApiMethod;
import net.tiklab.postin.annotation.ApiParam;
import net.tiklab.teston.apix.http.unit.cases.model.RawParam;
import net.tiklab.teston.apix.http.unit.cases.model.RawParamQuery;
import net.tiklab.teston.apix.http.unit.cases.service.RawParamService;
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
    public Result<String> createRawParam(@RequestBody @NotNull @Valid RawParam rawParam){
        String id = rawParamService.createRawParam(rawParam);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateRawParam",method = RequestMethod.POST)
    @ApiMethod(name = "updateRawParam",desc = "更新raw")
    @ApiParam(name = "rawParam",desc = "rawParam",required = true)
    public Result<Void> updateRawParam(@RequestBody @NotNull @Valid RawParam rawParam){
        rawParamService.updateRawParam(rawParam);

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
    public Result<RawParam> findRawParam(@NotNull String id){
        RawParam rawParam = rawParamService.findRawParam(id);

        return Result.ok(rawParam);
    }

    @RequestMapping(path="/findAllRawParam",method = RequestMethod.POST)
    @ApiMethod(name = "findAllRawParam",desc = "查找所有raw")
    public Result<List<RawParam>> findAllRawParam(){
        List<RawParam> rawParamList = rawParamService.findAllRawParam();

        return Result.ok(rawParamList);
    }

    @RequestMapping(path = "/findRawParamList",method = RequestMethod.POST)
    @ApiMethod(name = "findRawParamList",desc = "根据查询参数查询raw列表")
    @ApiParam(name = "rawParamQuery",desc = "rawParamQuery",required = true)
    public Result<List<RawParam>> findRawParamList(@RequestBody @Valid @NotNull RawParamQuery rawParamQuery){
        List<RawParam> rawParamList = rawParamService.findRawParamList(rawParamQuery);

        return Result.ok(rawParamList);
    }

    @RequestMapping(path = "/findRawParamPage",method = RequestMethod.POST)
    @ApiMethod(name = "findRawParamPage",desc = "根据查询参数按分页查询raw")
    @ApiParam(name = "rawParamQuery",desc = "rawParamQuery",required = true)
    public Result<Pagination<RawParam>> findRawParamPage(@RequestBody @Valid @NotNull RawParamQuery rawParamQuery){
        Pagination<RawParam> pagination = rawParamService.findRawParamPage(rawParamQuery);

        return Result.ok(pagination);
    }

}
