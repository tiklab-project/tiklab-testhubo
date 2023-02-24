package net.tiklab.teston.test.webperf.cases.controller;

import net.tiklab.postin.annotation.Api;
import net.tiklab.postin.annotation.ApiMethod;
import net.tiklab.postin.annotation.ApiParam;
import net.tiklab.core.Result;
import net.tiklab.core.page.Pagination;
import net.tiklab.teston.test.testcase.model.TestCaseQuery;
import net.tiklab.teston.test.webperf.cases.model.WebPerfCase;
import net.tiklab.teston.test.webperf.cases.model.WebPerfCaseQuery;
import net.tiklab.teston.test.webperf.cases.service.WebPerfCaseService;
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
 * WebPerfCaseController
 */
@RestController
@RequestMapping("/webPerfCase")
@Api(name = "WebPerfCaseController",desc = "web性能测试用例")
public class WebPerfCaseController {

    private static Logger logger = LoggerFactory.getLogger(WebPerfCaseController.class);

    @Autowired
    WebPerfCaseService webPerfCaseService;

    @RequestMapping(path = "/createWebPerfCase", method = RequestMethod.POST)
    @ApiMethod(name = "createWebPerfCase", desc = "创建性能测试")
    @ApiParam(name = "webPerfCase", desc = "webPerfCase", required = true)
    public Result<String> createWebPerfCase(@RequestBody @NotNull @Valid WebPerfCase webPerfCase) {
        String id = webPerfCaseService.createWebPerfCase(webPerfCase);

        return Result.ok(id);
    }

    @RequestMapping(path = "/updateWebPerfCase", method = RequestMethod.POST)
    @ApiMethod(name = "updateWebPerfCase", desc = "修改性能测试")
    @ApiParam(name = "webPerfCase", desc = "webPerfCase", required = true)
    public Result<Void> updateWebPerfCase(@RequestBody @NotNull @Valid WebPerfCase webPerfCase) {
        webPerfCaseService.updateWebPerfCase(webPerfCase);

        return Result.ok();
    }

    @RequestMapping(path = "/deleteWebPerfCase", method = RequestMethod.POST)
    @ApiMethod(name = "deleteWebPerfCase", desc = "删除性能测试")
    @ApiParam(name = "id", desc = "id", required = true)
    public Result<Void> deleteWebPerfCase(@NotNull String id) {
        webPerfCaseService.deleteWebPerfCase(id);

        return Result.ok();
    }

    @RequestMapping(path = "/findWebPerfCase", method = RequestMethod.POST)
    @ApiMethod(name = "findWebPerfCase", desc = "通过id查询性能测试")
    @ApiParam(name = "id", desc = "id", required = true)
    public Result<WebPerfCase> findWebPerfCase(@NotNull String id) {
        WebPerfCase webPerfCase = webPerfCaseService.findWebPerfCase(id);

        return Result.ok(webPerfCase);
    }

    @RequestMapping(path = "/findAllWebPerfCase", method = RequestMethod.POST)
    @ApiMethod(name = "findAllWebPerfCase", desc = "查询所有性能测试")
    public Result<List<WebPerfCase>> findAllWebPerfCase() {
        List<WebPerfCase> webPerfCaseList = webPerfCaseService.findAllWebPerfCase();

        return Result.ok(webPerfCaseList);
    }

    @RequestMapping(path = "/findWebPerfCaseList", method = RequestMethod.POST)
    @ApiMethod(name = "findWebPerfCaseList", desc = "通过查询对象查询性能测试")
    @ApiParam(name = "webPerfCaseQuery", desc = "webPerfCaseQuery", required = true)
    public Result<List<WebPerfCase>> findWebPerfCaseList(@RequestBody @Valid @NotNull WebPerfCaseQuery webPerfCaseQuery) {
        List<WebPerfCase> webPerfCaseList = webPerfCaseService.findWebPerfCaseList(webPerfCaseQuery);

        return Result.ok(webPerfCaseList);
    }

    @RequestMapping(path = "/findWebPerfCasePage", method = RequestMethod.POST)
    @ApiMethod(name = "findWebPerfCasePage", desc = "通过查询对象分页查询性能测试")
    @ApiParam(name = "webPerfCaseQuery", desc = "webPerfCaseQuery", required = true)
    public Result<Pagination<WebPerfCase>> findWebPerfCasePage(@RequestBody @Valid @NotNull WebPerfCaseQuery webPerfCaseQuery) {
        Pagination<WebPerfCase> pagination = webPerfCaseService.findWebPerfCasePage(webPerfCaseQuery);

        return Result.ok(pagination);
    }

    @RequestMapping(path = "/findWebPerfCaseListByTestCase",method = RequestMethod.POST)
    @ApiMethod(name = "findWebPerfCaseListByTestCase",desc = "通过中间层testcase查询下面的场景用例")
    @ApiParam(name = "testCaseQuery",desc = "testCaseQuery",required = true)
    public Result<List<WebPerfCase>> findWebPerfCaseListByTestCase(@RequestBody @Valid @NotNull TestCaseQuery testCaseQuery){
        List<WebPerfCase> webPerfCaseList = webPerfCaseService.findWebPerfCaseListByTestCase(testCaseQuery);

        return  Result.ok(webPerfCaseList);
    }

}
