package io.thoughtware.teston.test.web.perf.cases.service;


import io.thoughtware.core.page.Pagination;
import io.thoughtware.teston.test.web.perf.cases.model.WebPerfStepQuery;
import io.thoughtware.teston.test.web.perf.cases.model.WebPerfStep;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* web性能用例下绑定的场景 服务接口
*/
public interface WebPerfStepService {

    /**
    * 创建web性能用例下绑定的场景
    * @param webPerfStep
    * @return
    */
    String createWebPerfStep(@NotNull @Valid WebPerfStep webPerfStep);

    /**
    * 更新web性能用例下绑定的场景
    * @param webPerfStep
    */
    void updateWebPerfStep(@NotNull @Valid WebPerfStep webPerfStep);

    /**
    * 删除web性能用例下绑定的场景
    * @param id
    */
    void deleteWebPerfStep(@NotNull String id);

    WebPerfStep findOne(@NotNull String id);

    List<WebPerfStep> findList(List<String> idList);

    /**
    * 根据id查找web性能用例下绑定的场景
    * @param id
    * @return
    */
    WebPerfStep findWebPerfStep(@NotNull String id);

    /**
    * 查找所有web性能用例下绑定的场景
    * @return
    */
    List<WebPerfStep> findAllWebPerfStep();

    /**
    * 根据查询参数查询web性能用例下绑定的场景列表
    * @param webPerfStepQuery
    * @return
    */
    List<WebPerfStep> findWebPerfStepList(WebPerfStepQuery webPerfStepQuery);

    /**
    * 根据查询参数按分页查询web性能用例下绑定的场景
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