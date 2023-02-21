package net.tiklab.teston.testjob.service;

import net.tiklab.core.page.Pagination;

import net.tiklab.teston.testjob.model.QuartzTestcase;
import net.tiklab.teston.testjob.model.QuartzTestcaseQuery;
import net.tiklab.teston.apitest.http.scenetest.model.ApiSceneCase;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* QuartzTestcaseService
*/
public interface QuartzTestcaseService {

    /**
    * 创建
    * @param quartzTestcase
    * @return
    */
    String createQuartzTestcase(@NotNull @Valid List<QuartzTestcase> quartzTestcase);

    /**
    * 更新
    * @param quartzTestcase
    */
    void updateQuartzTestcase(@NotNull @Valid List<QuartzTestcase> quartzTestcase);

    /**
    * 删除
    * @param id
    */
    void deleteQuartzTestcase(@NotNull String id);

    QuartzTestcase findOne(@NotNull String id);

    List<QuartzTestcase> findList(List<String> idList);

    /**
    * 查找
    * @param id
    * @return
    */
    QuartzTestcase findQuartzTestcase(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<QuartzTestcase> findAllQuartzTestcase();

    /**
    * 查询列表
    * @param quartzTestcaseQuery
    * @return
    */
    List<QuartzTestcase> findQuartzTestcaseList(QuartzTestcaseQuery quartzTestcaseQuery);

    /**
    * 按分页查询
    * @param quartzTestcaseQuery
    * @return
    */
    Pagination<QuartzTestcase> findQuartzTestcasePage(QuartzTestcaseQuery quartzTestcaseQuery);
    /**
     * 查询用例库的用例
     * @param quartzTestcaseQuery
     * @return
     */
    List<ApiSceneCase> findRepositoryTestcaseList(QuartzTestcaseQuery quartzTestcaseQuery);
}