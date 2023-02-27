package net.tiklab.teston.web.perf.cases.service;


import net.tiklab.core.page.Pagination;
import net.tiklab.teston.web.perf.cases.model.WebPerfStepQuery;
import net.tiklab.teston.web.perf.cases.model.WebPerfStep;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* WebPerfStepService
*/
public interface WebPerfStepService {

    /**
    * 创建
    * @param webPerfStep
    * @return
    */
    String createWebPerfStep(@NotNull @Valid WebPerfStep webPerfStep);

    /**
    * 更新
    * @param webPerfStep
    */
    void updateWebPerfStep(@NotNull @Valid WebPerfStep webPerfStep);

    /**
    * 删除
    * @param id
    */
    void deleteWebPerfStep(@NotNull String id);

    WebPerfStep findOne(@NotNull String id);

    List<WebPerfStep> findList(List<String> idList);

    /**
    * 查找
    * @param id
    * @return
    */
    WebPerfStep findWebPerfStep(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<WebPerfStep> findAllWebPerfStep();

    /**
    * 查询列表
    * @param webPerfStepQuery
    * @return
    */
    List<WebPerfStep> findWebPerfStepList(WebPerfStepQuery webPerfStepQuery);

    /**
    * 按分页查询
    * @param webPerfStepQuery
    * @return
    */
    Pagination<WebPerfStep> findWebPerfStepPage(WebPerfStepQuery webPerfStepQuery);

    /**
     * 绑定web场景
     * @param webPerfStepList
     */
    void bindWebScene(List<WebPerfStep> webPerfStepList);

}