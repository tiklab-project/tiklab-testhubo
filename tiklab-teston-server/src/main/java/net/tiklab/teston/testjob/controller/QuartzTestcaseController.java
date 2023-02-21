package net.tiklab.teston.testjob.controller;

import net.tiklab.postin.annotation.Api;
import net.tiklab.teston.testjob.model.QuartzTestcase;
import net.tiklab.teston.testjob.model.QuartzTestcaseQuery;
import net.tiklab.teston.testjob.service.QuartzTestcaseService;
import net.tiklab.core.page.Pagination;
import net.tiklab.core.Result;
import net.tiklab.postin.annotation.ApiMethod;
import net.tiklab.postin.annotation.ApiParam;
import net.tiklab.teston.apitest.http.scenetest.model.ApiSceneCase;
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
 * QuartzTestcaseController
 */
@RestController
@RequestMapping("/quartzTestcase")
@Api(name = "QuartzTestcaseController",desc = "定时任务用例管理")
public class QuartzTestcaseController {

    private static Logger logger = LoggerFactory.getLogger(QuartzTestcaseController.class);

    @Autowired
    private QuartzTestcaseService quartzTestcaseService;

    @RequestMapping(path="/createQuartzTestcase",method = RequestMethod.POST)
    @ApiMethod(name = "createQuartzTestcase",desc = "添加定时任务用例")
    @ApiParam(name = "quartzTestcase",desc = "quartzTestcase",required = true)
    public Result<String> createQuartzTestcase(@RequestBody @NotNull @Valid List<QuartzTestcase>  quartzTestcase){
        String id = quartzTestcaseService.createQuartzTestcase(quartzTestcase);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateQuartzTestcase",method = RequestMethod.POST)
    @ApiMethod(name = "updateQuartzTestcase",desc = "定时任务用例修改")
    @ApiParam(name = "quartzTestcase",desc = "quartzTestcase",required = true)
    public Result<Void> updateQuartzTestcase(@RequestBody @NotNull @Valid List<QuartzTestcase> quartzTestcase){
        quartzTestcaseService.updateQuartzTestcase(quartzTestcase);

        return Result.ok();
    }

    @RequestMapping(path="/deleteQuartzTestcase",method = RequestMethod.POST)
    @ApiMethod(name = "deleteQuartzTestcase",desc = "deleteQuartzTestcase")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteQuartzTestcase(@NotNull String id){
        quartzTestcaseService.deleteQuartzTestcase(id);

        return Result.ok();
    }

    @RequestMapping(path="/findQuartzTestcase",method = RequestMethod.POST)
    @ApiMethod(name = "findQuartzTestcase",desc = "findQuartzTestcase")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<QuartzTestcase> findQuartzTestcase(@NotNull String id){
        QuartzTestcase quartzTestcase = quartzTestcaseService.findQuartzTestcase(id);

        return Result.ok(quartzTestcase);
    }

    @RequestMapping(path="/findAllQuartzTestcase",method = RequestMethod.POST)
    @ApiMethod(name = "findAllQuartzTestcase",desc = "findAllQuartzTestcase")
    public Result<List<QuartzTestcase>> findAllQuartzTestcase(){
        List<QuartzTestcase> quartzTestcaseList = quartzTestcaseService.findAllQuartzTestcase();

        return Result.ok(quartzTestcaseList);
    }

    @RequestMapping(path = "/findQuartzTestcaseList",method = RequestMethod.POST)
    @ApiMethod(name = "findQuartzTestcaseList",desc = "findQuartzTestcaseList")
    @ApiParam(name = "quartzTestcaseQuery",desc = "quartzTestcaseQuery",required = true)
    public Result<List<QuartzTestcase>> findQuartzTestcaseList(@RequestBody @Valid @NotNull QuartzTestcaseQuery quartzTestcaseQuery){
        List<QuartzTestcase> quartzTestcaseList = quartzTestcaseService.findQuartzTestcaseList(quartzTestcaseQuery);

        return Result.ok(quartzTestcaseList);
    }

    @RequestMapping(path = "/findQuartzTestcasePage",method = RequestMethod.POST)
    @ApiMethod(name = "findQuartzTestcasePage",desc = "findQuartzTestcasePage")
    @ApiParam(name = "quartzTestcaseQuery",desc = "quartzTestcaseQuery",required = true)
    public Result<Pagination<QuartzTestcase>> findQuartzTestcasePage(@RequestBody @Valid @NotNull QuartzTestcaseQuery quartzTestcaseQuery){
        Pagination<QuartzTestcase> pagination = quartzTestcaseService.findQuartzTestcasePage(quartzTestcaseQuery);

        return Result.ok(pagination);
    }


    @RequestMapping(path = "/findRepositoryTestcaseList",method = RequestMethod.POST)
    @ApiMethod(name = "findQuartzTestcaseList",desc = "查询用例库的用例")
    @ApiParam(name = "quartzTestcaseQuery",desc = "quartzTestcaseQuery",required = true)
    public Result<List<ApiSceneCase>> findRepositoryTestcaseList(@RequestBody @Valid @NotNull QuartzTestcaseQuery quartzTestcaseQuery){
        List<ApiSceneCase> quartzTestcaseList = quartzTestcaseService.findRepositoryTestcaseList(quartzTestcaseQuery);

        return Result.ok(quartzTestcaseList);
    }
}
