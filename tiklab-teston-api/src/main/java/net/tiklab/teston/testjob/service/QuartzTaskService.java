package net.tiklab.teston.testjob.service;

import net.tiklab.core.page.Pagination;

import net.tiklab.teston.testjob.model.QuartzTask;
import net.tiklab.teston.testjob.model.QuartzTaskQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* QuartzTaskService
*/
public interface QuartzTaskService {

    /**
    * 创建
    * @param quartzTask
    * @return
    */
    String createQuartzTask(@NotNull @Valid QuartzTask quartzTask);

    /**
    * 更新
    * @param quartzTask
    */
    void updateQuartzTask(@NotNull @Valid QuartzTask quartzTask);

    /**
    * 删除
    * @param id
    */
    void deleteQuartzTask(@NotNull String id);

    QuartzTask findOne(@NotNull String id);

    List<QuartzTask> findList(List<String> idList);

    /**
    * 查找
    * @param id
    * @return
    */
    QuartzTask findQuartzTask(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<QuartzTask> findAllQuartzTask();

    /**
    * 查询列表
    * @param quartzTaskQuery
    * @return
    */
    List<QuartzTask> findQuartzTaskList(QuartzTaskQuery quartzTaskQuery);

    /**
    * 按分页查询
    * @param quartzTaskQuery
    * @return
    */
    Pagination<QuartzTask> findQuartzTaskPage(QuartzTaskQuery quartzTaskQuery);

    /**
     * 查询执行状态的定时任务
     * @param
     * @return
     */
    List<QuartzTask> getCron();
}