package net.tiklab.teston.test.apix.http.unit.cases.service;

import net.tiklab.core.page.Pagination;
import net.tiklab.teston.test.apix.http.unit.cases.model.ApiUnitCase;
import net.tiklab.teston.test.apix.http.unit.cases.model.RequestHeader;
import net.tiklab.teston.test.apix.http.unit.cases.model.RequestHeaderQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
* 请求头 服务接口
*/
public interface RequestHeaderService {

    /**
    * 创建请求头
    * @param requestHeader
    * @return
    */
    String createRequestHeader(@NotNull @Valid RequestHeader requestHeader);

    /**
    * 更新请求头
    * @param requestHeader
    */
    void updateRequestHeader(@NotNull @Valid RequestHeader requestHeader);

    /**
    * 删除请求头
    * @param id
    */
    void deleteRequestHeader(@NotNull String id);

    RequestHeader findOne(@NotNull String id);

    List<RequestHeader> findList(List<String> idList);

    /**
    * 查找请求头
    * @param id
    * @return
    */
    RequestHeader findRequestHeader(@NotNull String id);

    /**
    * 查找所有请求头
    * @return
    */
    List<RequestHeader> findAllRequestHeader();

    /**
    * 根据参数查询请求头列表
    * @param requestHeaderQuery
    * @return
    */
    List<RequestHeader> findRequestHeaderList(RequestHeaderQuery requestHeaderQuery);

    /**
    * 根据参数按分页查询请求头
    * @param requestHeaderQuery
    * @return
    */
    Pagination<RequestHeader> findRequestHeaderPage(RequestHeaderQuery requestHeaderQuery);

    /**
     *请求头拼接
     * @param step 用例步骤
     *
     */
    Map<String, String> jointHeader(ApiUnitCase step);

}