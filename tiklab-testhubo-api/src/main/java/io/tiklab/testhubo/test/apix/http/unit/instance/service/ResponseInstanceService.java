package io.tiklab.testhubo.test.apix.http.unit.instance.service;

import io.tiklab.testhubo.test.apix.http.unit.instance.model.ResponseInstance;
import io.tiklab.core.page.Pagination;
import io.tiklab.testhubo.test.apix.http.unit.instance.model.ResponseInstanceQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 响应数据实例 服务接口
*/
public interface ResponseInstanceService {

    /**
    * 创建响应数据实例
    * @param responseInstance
    * @return
    */
    String createResponseInstance(@NotNull @Valid ResponseInstance responseInstance);

    /**
    * 更新响应数据实例
    * @param responseInstance
    */
    void updateResponseInstance(@NotNull @Valid ResponseInstance responseInstance);

    /**
    * 删除响应数据实例
    * @param id
    */
    void deleteResponseInstance(@NotNull String id);

    ResponseInstance findOne(@NotNull String id);

    List<ResponseInstance> findList(List<String> idList);

    /**
    * 根据id查找响应数据实例
    * @param id
    * @return
    */
    ResponseInstance findResponseInstance(@NotNull String id);

    /**
    * 查找所有响应数据实例
    * @return
    */
    List<ResponseInstance> findAllResponseInstance();

    /**
    * 根据查询参数查询响应数据实例列表
    * @param responseInstanceQuery
    * @return
    */
    List<ResponseInstance> findResponseInstanceList(ResponseInstanceQuery responseInstanceQuery);

    /**
    * 根据查询参数按分页查询响应数据实例
    * @param responseInstanceQuery
    * @return
    */
    Pagination<ResponseInstance> findResponseInstancePage(ResponseInstanceQuery responseInstanceQuery);

}