package io.tiklab.teston.test.apix.http.unit.cases.controller;

import io.tiklab.postin.annotation.Api;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.Result;
import io.tiklab.postin.annotation.ApiMethod;
import io.tiklab.postin.annotation.ApiParam;
import io.tiklab.teston.test.apix.http.unit.cases.model.QueryParamUnit;
import io.tiklab.teston.test.apix.http.unit.cases.model.QueryParamUnitQuery;
import io.tiklab.teston.test.apix.http.unit.cases.service.QueryParamService;
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
 * query 控制器
 */
@RestController
@RequestMapping("/queryParam")
@Api(name = "QueryParamController",desc = "接口用例步骤QueryParamController")
public class QueryParamController {

    private static Logger logger = LoggerFactory.getLogger(QueryParamController.class);

    @Autowired
    private QueryParamService queryParamService;

    @RequestMapping(path="/createQueryParam",method = RequestMethod.POST)
    @ApiMethod(name = "createQueryParam",desc = "创建query")
    @ApiParam(name = "queryParams",desc = "queryParams",required = true)
    public Result<String> createQueryParam(@RequestBody @NotNull @Valid QueryParamUnit queryParamUnit){
        String id = queryParamService.createQueryParam(queryParamUnit);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateQueryParam",method = RequestMethod.POST)
    @ApiMethod(name = "updateQueryParam",desc = "更新query")
    @ApiParam(name = "queryParams",desc = "queryParams",required = true)
    public Result<Void> updateQueryParam(@RequestBody @NotNull @Valid QueryParamUnit queryParamUnit){
        queryParamService.updateQueryParam(queryParamUnit);

        return Result.ok();
    }

    @RequestMapping(path="/deleteQueryParam",method = RequestMethod.POST)
    @ApiMethod(name = "deleteQueryParam",desc = "删除query")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteQueryParam(@NotNull String id){
        queryParamService.deleteQueryParam(id);

        return Result.ok();
    }

    @RequestMapping(path="/findQueryParam",method = RequestMethod.POST)
    @ApiMethod(name = "findQueryParam",desc = "根据id查找query")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<QueryParamUnit> findQueryParam(@NotNull String id){
        QueryParamUnit queryParamUnit = queryParamService.findQueryParam(id);

        return Result.ok(queryParamUnit);
    }

    @RequestMapping(path="/findAllQueryParam",method = RequestMethod.POST)
    @ApiMethod(name = "findAllQueryParam",desc = "查找所有query")
    public Result<List<QueryParamUnit>> findAllQueryParam(){
        List<QueryParamUnit> queryParamUnitList = queryParamService.findAllQueryParam();

        return Result.ok(queryParamUnitList);
    }

    @RequestMapping(path = "/findQueryParamList",method = RequestMethod.POST)
    @ApiMethod(name = "findQueryParamList",desc = "根据查询参数查询query列表")
    @ApiParam(name = "queryParamQuery",desc = "queryParamQuery",required = true)
    public Result<List<QueryParamUnit>> findQueryParamList(@RequestBody @Valid @NotNull QueryParamUnitQuery queryParamUnitQuery){
        List<QueryParamUnit> queryParamUnitList = queryParamService.findQueryParamList(queryParamUnitQuery);

        return Result.ok(queryParamUnitList);
    }

    @RequestMapping(path = "/findQueryParamPage",method = RequestMethod.POST)
    @ApiMethod(name = "findQueryParamPage",desc = "根据查询参数按分页查询query")
    @ApiParam(name = "queryParamQuery",desc = "queryParamQuery",required = true)
    public Result<Pagination<QueryParamUnit>> findQueryParamPage(@RequestBody @Valid @NotNull QueryParamUnitQuery queryParamUnitQuery){
        Pagination<QueryParamUnit> pagination = queryParamService.findQueryParamPage(queryParamUnitQuery);

        return Result.ok(pagination);
    }

}
