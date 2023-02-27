package net.tiklab.teston.web.perf.instance.service;

import net.tiklab.core.page.Pagination;
import net.tiklab.teston.web.perf.instance.model.WebPerfInstance;
import net.tiklab.teston.web.perf.instance.model.WebPerfInstanceQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* WebPerfInstanceService
*/
public interface WebPerfInstanceService {

    /**
    * 创建
    * @param webPerfInstance
    * @return
    */
    String createWebPerfInstance(@NotNull @Valid WebPerfInstance webPerfInstance);

    /**
    * 更新
    * @param webPerfInstance
    */
    void updateWebPerfInstance(@NotNull @Valid WebPerfInstance webPerfInstance);

    /**
    * 删除
    * @param id
    */
    void deleteWebPerfInstance(@NotNull String id);

    WebPerfInstance findOne(@NotNull String id);

    List<WebPerfInstance> findList(List<String> idList);

    /**
    * 查找
    * @param id
    * @return
    */
    WebPerfInstance findWebPerfInstance(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<WebPerfInstance> findAllWebPerfInstance();

    /**
    * 查询列表
    * @param webPerfInstanceQuery
    * @return
    */
    List<WebPerfInstance> findWebPerfInstanceList(WebPerfInstanceQuery webPerfInstanceQuery);

    /**
    * 按分页查询
    * @param webPerfInstanceQuery
    * @return
    */
    Pagination<WebPerfInstance> findWebPerfInstancePage(WebPerfInstanceQuery webPerfInstanceQuery);

}