package net.tiklab.teston.testjob.controller;

import net.tiklab.postin.annotation.Api;
import net.tiklab.teston.testjob.model.QuartzMaster;
import net.tiklab.teston.testjob.model.QuartzMasterQuery;
import net.tiklab.teston.testjob.service.QuartzMasterService;
import net.tiklab.core.page.Pagination;
import net.tiklab.core.Result;
import net.tiklab.postin.annotation.ApiMethod;
import net.tiklab.postin.annotation.ApiParam;
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
 * QuartzMasterController
 */
@RestController
@RequestMapping("/quartzMaster")
@Api(name = "QuartzMasterController",desc = "定时任务主表")
public class QuartzMasterController {

    private static Logger logger = LoggerFactory.getLogger(QuartzMasterController.class);

    @Autowired
    private QuartzMasterService quartzMasterService;

    @RequestMapping(path="/createQuartzMaster",method = RequestMethod.POST)
    @ApiMethod(name = "createQuartzMaster",desc = "创建定时任务")
    @ApiParam(name = "quartzMaster",desc = "quartzMaster",required = true)
    public Result<String> createQuartzMaster(@RequestBody @NotNull @Valid QuartzMaster quartzMaster){
        String id = quartzMasterService.createQuartzMaster(quartzMaster);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateQuartzMaster",method = RequestMethod.POST)
    @ApiMethod(name = "updateQuartzMaster",desc = "修改定时任务")
    @ApiParam(name = "quartzMaster",desc = "quartzMaster",required = true)
    public Result<Void> updateQuartzMaster(@RequestBody @NotNull @Valid QuartzMaster quartzMaster){
        quartzMasterService.updateQuartzMaster(quartzMaster);

        return Result.ok();
    }

    @RequestMapping(path="/deleteQuartzMaster",method = RequestMethod.POST)
    @ApiMethod(name = "deleteQuartzMaster",desc = "deleteQuartzMaster")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteQuartzMaster(@NotNull String id){
        quartzMasterService.deleteQuartzMaster(id);

        return Result.ok();
    }

    @RequestMapping(path="/findQuartzMaster",method = RequestMethod.POST)
    @ApiMethod(name = "findQuartzMaster",desc = "通过id查询定时任务详情")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<QuartzMaster> findQuartzMaster(@NotNull String id){
        QuartzMaster quartzMaster = quartzMasterService.findQuartzMaster(id);

        return Result.ok(quartzMaster);
    }

    @RequestMapping(path="/findAllQuartzMaster",method = RequestMethod.POST)
    @ApiMethod(name = "findAllQuartzMaster",desc = "findAllQuartzMaster")
    public Result<List<QuartzMaster>> findAllQuartzMaster(){
        List<QuartzMaster> quartzMasterList = quartzMasterService.findAllQuartzMaster();

        return Result.ok(quartzMasterList);
    }

    @RequestMapping(path = "/findQuartzMasterList",method = RequestMethod.POST)
    @ApiMethod(name = "findQuartzMasterList",desc = "findQuartzMasterList")
    @ApiParam(name = "quartzMasterQuery",desc = "quartzMasterQuery",required = true)
    public Result<List<QuartzMaster>> findQuartzMasterList(@RequestBody @Valid @NotNull QuartzMasterQuery quartzMasterQuery){
        List<QuartzMaster> quartzMasterList = quartzMasterService.findQuartzMasterList(quartzMasterQuery);

        return Result.ok(quartzMasterList);
    }

    @RequestMapping(path = "/findQuartzMasterPage",method = RequestMethod.POST)
    @ApiMethod(name = "findQuartzMasterPage",desc = "通过条件分页查询定时任务列表")
    @ApiParam(name = "quartzMasterQuery",desc = "quartzMasterQuery",required = true)
    public Result<Pagination<QuartzMaster>> findQuartzMasterPage(@RequestBody @Valid @NotNull QuartzMasterQuery quartzMasterQuery){
        Pagination<QuartzMaster> pagination = quartzMasterService.findQuartzMasterPage(quartzMasterQuery);

        return Result.ok(pagination);
    }

}
