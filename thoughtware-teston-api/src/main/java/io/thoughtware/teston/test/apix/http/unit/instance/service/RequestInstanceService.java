package io.thoughtware.teston.test.apix.http.unit.instance.service;

import io.thoughtware.teston.test.apix.http.unit.instance.model.RequestInstance;
import io.thoughtware.teston.test.apix.http.unit.instance.model.RequestInstanceQuery;
import io.thoughtware.core.page.Pagination;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 请求数据实例 服务接口
*/
public interface RequestInstanceService {

    /**
    * 创建请求数据实例
    * @param requestInstance
    * @return
    */
    String createRequestInstance(@NotNull @Valid RequestInstance requestInstance);

    /**
    * 更新
    * @param requestInstance
    */
    void updateRequestInstance(@NotNull @Valid RequestInstance requestInstance);

    /**
    * 删除请求数据实例
    * @param id
    */
    void deleteRequestInstance(@NotNull String id);

    RequestInstance findOne(@NotNull String id);

    List<RequestInstance> findList(List<String> idList);

    /**
    * 查找请求数据实例
    * @param id
    * @return
    */
    RequestInstance findRequestInstance(@NotNull String id);

    /**
    * 查找所有请求数据实例
    * @return
    */
    List<RequestInstance> findAllRequestInstance();

    /**
    * 根据查询参数查询请求数据实例列表
    * @param requestInstanceQuery
    * @return
    */
    List<RequestInstance> findRequestInstanceList(RequestInstanceQuery requestInstanceQuery);

    /**
    * 根据查询参数按分页查询请求数据实例
    * @param requestInstanceQuery
    * @return
    */
    Pagination<RequestInstance> findRequestInstancePage(RequestInstanceQuery requestInstanceQuery);

}