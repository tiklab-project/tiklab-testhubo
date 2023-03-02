package net.tiklab.teston.apix.http.unit.http.cases.controller;

import net.tiklab.postin.annotation.Api;
import net.tiklab.core.page.Pagination;
import net.tiklab.core.Result;
import net.tiklab.postin.annotation.ApiMethod;
import net.tiklab.postin.annotation.ApiParam;
import net.tiklab.teston.apix.http.unit.cases.model.QueryParam;
import net.tiklab.teston.apix.http.unit.cases.model.QueryParamQuery;
import net.tiklab.teston.apix.http.unit.cases.service.QueryParamService;
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
    @ApiParam(name = "queryParam",desc = "queryParam",required = true)
    public Result<String> createQueryParam(@RequestBody @NotNull @Valid QueryParam queryParam){
        String id = queryParamService.createQueryParam(queryParam);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateQueryParam",method = RequestMethod.POST)
    @ApiMethod(name = "updateQueryParam",desc = "更新query")
    @ApiParam(name = "queryParam",desc = "queryParam",required = true)
    public Result<Void> updateQueryParam(@RequestBody @NotNull @Valid QueryParam queryParam){
        queryParamService.updateQueryParam(queryParam);

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
    public Result<QueryParam> findQueryParam(@NotNull String id){
        QueryParam queryParam = queryParamService.findQueryParam(id);

        return Result.ok(queryParam);
    }

    @RequestMapping(path="/findAllQueryParam",method = RequestMethod.POST)
    @ApiMethod(name = "findAllQueryParam",desc = "查找所有query")
    public Result<List<QueryParam>> findAllQueryParam(){
        List<QueryParam> queryParamList = queryParamService.findAllQueryParam();

        return Result.ok(queryParamList);
    }

    @RequestMapping(path = "/findQueryParamList",method = RequestMethod.POST)
    @ApiMethod(name = "findQueryParamList",desc = "根据查询参数查询query列表")
    @ApiParam(name = "queryParamQuery",desc = "queryParamQuery",required = true)
    public Result<List<QueryParam>> findQueryParamList(@RequestBody @Valid @NotNull QueryParamQuery queryParamQuery){
        List<QueryParam> queryParamList = queryParamService.findQueryParamList(queryParamQuery);

        return Result.ok(queryParamList);
    }

    @RequestMapping(path = "/findQueryParamPage",method = RequestMethod.POST)
    @ApiMethod(name = "findQueryParamPage",desc = "根据查询参数按分页查询query")
    @ApiParam(name = "queryParamQuery",desc = "queryParamQuery",required = true)
    public Result<Pagination<QueryParam>> findQueryParamPage(@RequestBody @Valid @NotNull QueryParamQuery queryParamQuery){
        Pagination<QueryParam> pagination = queryParamService.findQueryParamPage(queryParamQuery);

        return Result.ok(pagination);
    }

}
