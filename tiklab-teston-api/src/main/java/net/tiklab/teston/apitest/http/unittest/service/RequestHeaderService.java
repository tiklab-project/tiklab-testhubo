package net.tiklab.teston.apitest.http.unittest.service;

import net.tiklab.core.page.Pagination;

import net.tiklab.teston.apitest.http.unittest.model.RequestHeader;
import net.tiklab.teston.apitest.http.unittest.model.RequestHeaderQuery;
import net.tiklab.teston.apitest.http.unittest.model.ApiUnitCase;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
* RequestHeaderService
*/
public interface RequestHeaderService {

    /**
    * 创建
    * @param requestHeader
    * @return
    */
    String createRequestHeader(@NotNull @Valid RequestHeader requestHeader);

    /**
    * 更新
    * @param requestHeader
    */
    void updateRequestHeader(@NotNull @Valid RequestHeader requestHeader);

    /**
    * 删除
    * @param id
    */
    void deleteRequestHeader(@NotNull String id);

    RequestHeader findOne(@NotNull String id);

    List<RequestHeader> findList(List<String> idList);

    /**
    * 查找
    * @param id
    * @return
    */
    RequestHeader findRequestHeader(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<RequestHeader> findAllRequestHeader();

    /**
    * 查询列表
    * @param requestHeaderQuery
    * @return
    */
    List<RequestHeader> findRequestHeaderList(RequestHeaderQuery requestHeaderQuery);

    /**
    * 按分页查询
    * @param requestHeaderQuery
    * @return
    */
    Pagination<RequestHeader> findRequestHeaderPage(RequestHeaderQuery requestHeaderQuery);

    //========
    /**
     *请求头拼接
     * @param step 用例步骤
     *
     */
    Map<String, String> jointHeader(ApiUnitCase step);

}