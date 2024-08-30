package io.thoughtware.testrubo.test.apix.http.unit.cases.service;

import io.thoughtware.testrubo.test.apix.http.unit.cases.model.RequestBodyUnit;
import io.thoughtware.testrubo.test.apix.http.unit.cases.model.RequestBodyUnitQuery;
import io.thoughtware.core.page.Pagination;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 请求体 服务接口
*/
public interface RequestBodyService {

    /**
    * 创建请求体
    * @param requestBodyUnit
    * @return
    */
    String createRequestBody(@NotNull @Valid RequestBodyUnit requestBodyUnit);

    /**
    * 更新请求体
    * @param requestBodyUnit
    */
    void updateRequestBody(@NotNull @Valid RequestBodyUnit requestBodyUnit);

    /**
    * 删除请求体
    * @param id
    */
    void deleteRequestBody(@NotNull String id);

    RequestBodyUnit findOne(@NotNull String id);

    List<RequestBodyUnit> findList(List<String> idList);

    /**
    * 查找请求体
    * @param id
    * @return
    */
    RequestBodyUnit findRequestBody(@NotNull String id);

    /**
    * 查找所有请求体
    * @return
    */
    List<RequestBodyUnit> findAllRequestBody();

    /**
    * 根据查询参数查询请求体列表
    * @param requestBodyUnitQuery
    * @return
    */
    List<RequestBodyUnit> findRequestBodyList(RequestBodyUnitQuery requestBodyUnitQuery);

    /**
    * 根据查询参数按分页查询请求体
    * @param requestBodyUnitQuery
    * @return
    */
    Pagination<RequestBodyUnit> findRequestBodyPage(RequestBodyUnitQuery requestBodyUnitQuery);

}