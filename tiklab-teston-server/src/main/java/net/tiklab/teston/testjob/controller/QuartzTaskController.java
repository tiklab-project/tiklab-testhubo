package net.tiklab.teston.testjob.controller;

import net.tiklab.postin.annotation.Api;
import net.tiklab.teston.testjob.model.QuartzTask;
import net.tiklab.teston.testjob.model.QuartzTaskQuery;
import net.tiklab.teston.testjob.service.QuartzTaskService;
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
 * QuartzTaskController
 */
@RestController
@RequestMapping("/quartzTask")
@Api(name = "QuartzTaskController",desc = "定时任务时间管理")
public class QuartzTaskController {

    private static Logger logger = LoggerFactory.getLogger(QuartzTaskController.class);

    @Autowired
    private QuartzTaskService quartzTaskService;

    @RequestMapping(path="/createQuartzTask",method = RequestMethod.POST)
    @ApiMethod(name = "createQuartzTask",desc = "创建定时时间")
    @ApiParam(name = "quartzTask",desc = "quartzTask",required = true)
    public Result<String> createQuartzTask(@RequestBody @NotNull @Valid QuartzTask quartzTask){
        String id = quartzTaskService.createQuartzTask(quartzTask);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateQuartzTask",method = RequestMethod.POST)
    @ApiMethod(name = "updateQuartzTask",desc = "修改定时任务时间")
    @ApiParam(name = "quartzTask",desc = "quartzTask",required = true)
    public Result<Void> updateQuartzTask(@RequestBody @NotNull @Valid QuartzTask quartzTask){
        quartzTaskService.updateQuartzTask(quartzTask);

        return Result.ok();
    }

    @RequestMapping(path="/deleteQuartzTask",method = RequestMethod.POST)
    @ApiMethod(name = "deleteQuartzTask",desc = "deleteQuartzTask")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteQuartzTask(@NotNull String id){
        quartzTaskService.deleteQuartzTask(id);

        return Result.ok();
    }

    @RequestMapping(path="/findQuartzTask",method = RequestMethod.POST)
    @ApiMethod(name = "findQuartzTask",desc = "findQuartzTask")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<QuartzTask> findQuartzTask(@NotNull String id){
        QuartzTask quartzTask = quartzTaskService.findQuartzTask(id);

        return Result.ok(quartzTask);
    }

    @RequestMapping(path="/findAllQuartzTask",method = RequestMethod.POST)
    @ApiMethod(name = "findAllQuartzTask",desc = "findAllQuartzTask")
    public Result<List<QuartzTask>> findAllQuartzTask(){
        List<QuartzTask> quartzTaskList = quartzTaskService.findAllQuartzTask();

        return Result.ok(quartzTaskList);
    }

    @RequestMapping(path = "/findQuartzTaskList",method = RequestMethod.POST)
    @ApiMethod(name = "findQuartzTaskList",desc = "findQuartzTaskList")
    @ApiParam(name = "quartzTaskQuery",desc = "quartzTaskQuery",required = true)
    public Result<List<QuartzTask>> findQuartzTaskList(@RequestBody @Valid @NotNull QuartzTaskQuery quartzTaskQuery){
        List<QuartzTask> quartzTaskList = quartzTaskService.findQuartzTaskList(quartzTaskQuery);

        return Result.ok(quartzTaskList);
    }

    @RequestMapping(path = "/findQuartzTaskPage",method = RequestMethod.POST)
    @ApiMethod(name = "findQuartzTaskPage",desc = "findQuartzTaskPage")
    @ApiParam(name = "quartzTaskQuery",desc = "quartzTaskQuery",required = true)
    public Result<Pagination<QuartzTask>> findQuartzTaskPage(@RequestBody @Valid @NotNull QuartzTaskQuery quartzTaskQuery){
        Pagination<QuartzTask> pagination = quartzTaskService.findQuartzTaskPage(quartzTaskQuery);

        return Result.ok(pagination);
    }

}
