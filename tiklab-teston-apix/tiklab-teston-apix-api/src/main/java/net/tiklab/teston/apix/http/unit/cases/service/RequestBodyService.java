package net.tiklab.teston.apix.http.unit.cases.service;

import net.tiklab.core.page.Pagination;
import net.tiklab.teston.apix.http.unit.cases.model.RequestBody;
import net.tiklab.teston.apix.http.unit.cases.model.RequestBodyQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 请求体 服务接口
*/
public interface RequestBodyService {

    /**
    * 创建请求体
    * @param requestBody
    * @return
    */
    String createRequestBody(@NotNull @Valid RequestBody requestBody);

    /**
    * 更新请求体
    * @param requestBody
    */
    void updateRequestBody(@NotNull @Valid RequestBody requestBody);

    /**
    * 删除请求体
    * @param id
    */
    void deleteRequestBody(@NotNull String id);

    RequestBody findOne(@NotNull String id);

    List<RequestBody> findList(List<String> idList);

    /**
    * 查找请求体
    * @param id
    * @return
    */
    RequestBody findRequestBody(@NotNull String id);

    /**
    * 查找所有请求体
    * @return
    */
    List<RequestBody> findAllRequestBody();

    /**
    * 根据查询参数查询请求体列表
    * @param requestBodyQuery
    * @return
    */
    List<RequestBody> findRequestBodyList(RequestBodyQuery requestBodyQuery);

    /**
    * 根据查询参数按分页查询请求体
    * @param requestBodyQuery
    * @return
    */
    Pagination<RequestBody> findRequestBodyPage(RequestBodyQuery requestBodyQuery);

}