package net.tiklab.testonapix.http.unit.http.cases.controller;

import net.tiklab.postin.annotation.Api;
import net.tiklab.core.page.Pagination;
import net.tiklab.core.Result;
import net.tiklab.postin.annotation.ApiMethod;
import net.tiklab.postin.annotation.ApiParam;
import net.tiklab.testonapix.http.unit.cases.model.RawParam;
import net.tiklab.testonapix.http.unit.cases.model.RawParamQuery;
import net.tiklab.testonapix.http.unit.cases.service.RawParamService;
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
 * RawParamController
 */
@RestController
@RequestMapping("/rawParam")
@Api(name = "RawParamController",desc = "接口用例步骤RawParamController")
public class RawParamController {

    private static Logger logger = LoggerFactory.getLogger(RawParamController.class);

    @Autowired
    private RawParamService rawParamService;

    @RequestMapping(path="/createRawParam",method = RequestMethod.POST)
    @ApiMethod(name = "createRawParam",desc = "createRawParam")
    @ApiParam(name = "rawParam",desc = "rawParam",required = true)
    public Result<String> createRawParam(@RequestBody @NotNull @Valid RawParam rawParam){
        String id = rawParamService.createRawParam(rawParam);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateRawParam",method = RequestMethod.POST)
    @ApiMethod(name = "updateRawParam",desc = "updateRawParam")
    @ApiParam(name = "rawParam",desc = "rawParam",required = true)
    public Result<Void> updateRawParam(@RequestBody @NotNull @Valid RawParam rawParam){
        rawParamService.updateRawParam(rawParam);

        return Result.ok();
    }

    @RequestMapping(path="/deleteRawParam",method = RequestMethod.POST)
    @ApiMethod(name = "deleteRawParam",desc = "deleteRawParam")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteRawParam(@NotNull String id){
        rawParamService.deleteRawParam(id);

        return Result.ok();
    }

    @RequestMapping(path="/findRawParam",method = RequestMethod.POST)
    @ApiMethod(name = "findRawParam",desc = "findRawParam")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<RawParam> findRawParam(@NotNull String id){
        RawParam rawParam = rawParamService.findRawParam(id);

        return Result.ok(rawParam);
    }

    @RequestMapping(path="/findAllRawParam",method = RequestMethod.POST)
    @ApiMethod(name = "findAllRawParam",desc = "findAllRawParam")
    public Result<List<RawParam>> findAllRawParam(){
        List<RawParam> rawParamList = rawParamService.findAllRawParam();

        return Result.ok(rawParamList);
    }

    @RequestMapping(path = "/findRawParamList",method = RequestMethod.POST)
    @ApiMethod(name = "findRawParamList",desc = "findRawParamList")
    @ApiParam(name = "rawParamQuery",desc = "rawParamQuery",required = true)
    public Result<List<RawParam>> findRawParamList(@RequestBody @Valid @NotNull RawParamQuery rawParamQuery){
        List<RawParam> rawParamList = rawParamService.findRawParamList(rawParamQuery);

        return Result.ok(rawParamList);
    }

    @RequestMapping(path = "/findRawParamPage",method = RequestMethod.POST)
    @ApiMethod(name = "findRawParamPage",desc = "findRawParamPage")
    @ApiParam(name = "rawParamQuery",desc = "rawParamQuery",required = true)
    public Result<Pagination<RawParam>> findRawParamPage(@RequestBody @Valid @NotNull RawParamQuery rawParamQuery){
        Pagination<RawParam> pagination = rawParamService.findRawParamPage(rawParamQuery);

        return Result.ok(pagination);
    }

}
