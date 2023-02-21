package net.tiklab.teston.apitest.http.unittest.service;

import net.tiklab.core.page.Pagination;
import net.tiklab.teston.apitest.http.unittest.model.RequestInstance;
import net.tiklab.teston.apitest.http.unittest.model.RequestInstanceQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* RequestInstanceService
*/
public interface RequestInstanceService {

    /**
    * 创建
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
    * 删除
    * @param id
    */
    void deleteRequestInstance(@NotNull String id);

    RequestInstance findOne(@NotNull String id);

    List<RequestInstance> findList(List<String> idList);

    /**
    * 查找
    * @param id
    * @return
    */
    RequestInstance findRequestInstance(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<RequestInstance> findAllRequestInstance();

    /**
    * 查询列表
    * @param requestInstanceQuery
    * @return
    */
    List<RequestInstance> findRequestInstanceList(RequestInstanceQuery requestInstanceQuery);

    /**
    * 按分页查询
    * @param requestInstanceQuery
    * @return
    */
    Pagination<RequestInstance> findRequestInstancePage(RequestInstanceQuery requestInstanceQuery);

}