package io.tiklab.teston.test.web.perf.instance.service;

import io.tiklab.teston.test.web.perf.instance.model.WebPerfInstance;
import io.tiklab.teston.test.web.perf.instance.model.WebPerfInstanceQuery;
import io.tiklab.core.page.Pagination;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* web性能测试实例 服务接口
*/
public interface WebPerfInstanceService {

    /**
    * 创建web性能测试实例
    * @param webPerfInstance
    * @return
    */
    String createWebPerfInstance(@NotNull @Valid WebPerfInstance webPerfInstance);

    /**
    * 更新web性能测试实例
    * @param webPerfInstance
    */
    void updateWebPerfInstance(@NotNull @Valid WebPerfInstance webPerfInstance);

    /**
    * 删除web性能测试实例
    * @param id
    */
    void deleteWebPerfInstance(@NotNull String id);

    WebPerfInstance findOne(@NotNull String id);

    List<WebPerfInstance> findList(List<String> idList);

    /**
    * 根据id查找web性能测试实例
    * @param id
    * @return
    */
    WebPerfInstance findWebPerfInstance(@NotNull String id);

    /**
    * 查找所有web性能测试实例
    * @return
    */
    List<WebPerfInstance> findAllWebPerfInstance();

    /**
    * 根据查询参数查询web性能测试实例列表
    * @param webPerfInstanceQuery
    * @return
    */
    List<WebPerfInstance> findWebPerfInstanceList(WebPerfInstanceQuery webPerfInstanceQuery);

    /**
    * 根据查询参数按分页查询web性能测试实例
    * @param webPerfInstanceQuery
    * @return
    */
    Pagination<WebPerfInstance> findWebPerfInstancePage(WebPerfInstanceQuery webPerfInstanceQuery);

}