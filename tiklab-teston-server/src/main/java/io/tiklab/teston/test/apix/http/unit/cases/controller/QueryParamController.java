package io.tiklab.teston.test.apix.http.unit.cases.controller;

import io.tiklab.postin.annotation.Api;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.Result;
import io.tiklab.postin.annotation.ApiMethod;
import io.tiklab.postin.annotation.ApiParam;
import io.tiklab.teston.test.apix.http.unit.cases.model.QueryParams;
import io.tiklab.teston.test.apix.http.unit.cases.model.QueryParamQuery;
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
    public Result<String> createQueryParam(@RequestBody @NotNull @Valid QueryParams queryParams){
        String id = queryParamService.createQueryParam(queryParams);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateQueryParam",method = RequestMethod.POST)
    @ApiMethod(name = "updateQueryParam",desc = "更新query")
    @ApiParam(name = "queryParams",desc = "queryParams",required = true)
    public Result<Void> updateQueryParam(@RequestBody @NotNull @Valid QueryParams queryParams){
        queryParamService.updateQueryParam(queryParams);

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
    public Result<QueryParams> findQueryParam(@NotNull String id){
        QueryParams queryParams = queryParamService.findQueryParam(id);

        return Result.ok(queryParams);
    }

    @RequestMapping(path="/findAllQueryParam",method = RequestMethod.POST)
    @ApiMethod(name = "findAllQueryParam",desc = "查找所有query")
    public Result<List<QueryParams>> findAllQueryParam(){
        List<QueryParams> queryParamsList = queryParamService.findAllQueryParam();

        return Result.ok(queryParamsList);
    }

    @RequestMapping(path = "/findQueryParamList",method = RequestMethod.POST)
    @ApiMethod(name = "findQueryParamList",desc = "根据查询参数查询query列表")
    @ApiParam(name = "queryParamQuery",desc = "queryParamQuery",required = true)
    public Result<List<QueryParams>> findQueryParamList(@RequestBody @Valid @NotNull QueryParamQuery queryParamQuery){
        List<QueryParams> queryParamsList = queryParamService.findQueryParamList(queryParamQuery);

        return Result.ok(queryParamsList);
    }

    @RequestMapping(path = "/findQueryParamPage",method = RequestMethod.POST)
    @ApiMethod(name = "findQueryParamPage",desc = "根据查询参数按分页查询query")
    @ApiParam(name = "queryParamQuery",desc = "queryParamQuery",required = true)
    public Result<Pagination<QueryParams>> findQueryParamPage(@RequestBody @Valid @NotNull QueryParamQuery queryParamQuery){
        Pagination<QueryParams> pagination = queryParamService.findQueryParamPage(queryParamQuery);

        return Result.ok(pagination);
    }

}
