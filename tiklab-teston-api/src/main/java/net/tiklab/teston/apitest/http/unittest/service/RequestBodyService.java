package net.tiklab.teston.apitest.http.unittest.service;

import net.tiklab.core.page.Pagination;

import net.tiklab.teston.apitest.http.unittest.model.RequestBody;
import net.tiklab.teston.apitest.http.unittest.model.RequestBodyQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* RequestBodyService
*/
public interface RequestBodyService {

    /**
    * 创建
    * @param requestBody
    * @return
    */
    String createRequestBody(@NotNull @Valid RequestBody requestBody);

    /**
    * 更新
    * @param requestBody
    */
    void updateRequestBody(@NotNull @Valid RequestBody requestBody);

    /**
    * 删除
    * @param id
    */
    void deleteRequestBody(@NotNull String id);

    RequestBody findOne(@NotNull String id);

    List<RequestBody> findList(List<String> idList);

    /**
    * 查找
    * @param id
    * @return
    */
    RequestBody findRequestBody(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<RequestBody> findAllRequestBody();

    /**
    * 查询列表
    * @param requestBodyQuery
    * @return
    */
    List<RequestBody> findRequestBodyList(RequestBodyQuery requestBodyQuery);

    /**
    * 按分页查询
    * @param requestBodyQuery
    * @return
    */
    Pagination<RequestBody> findRequestBodyPage(RequestBodyQuery requestBodyQuery);

}