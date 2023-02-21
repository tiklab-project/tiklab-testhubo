package net.tiklab.teston.apitest.http.unittest.service;

import net.tiklab.core.page.Pagination;

import net.tiklab.teston.apitest.http.unittest.model.ResponseHeader;
import net.tiklab.teston.apitest.http.unittest.model.ResponseHeaderQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* ResponseHeaderService
*/
public interface ResponseHeaderService {

    /**
    * 创建
    * @param responseHeader
    * @return
    */
    String createResponseHeader(@NotNull @Valid ResponseHeader responseHeader);

    /**
    * 更新
    * @param responseHeader
    */
    void updateResponseHeader(@NotNull @Valid ResponseHeader responseHeader);

    /**
    * 删除
    * @param id
    */
    void deleteResponseHeader(@NotNull String id);

    ResponseHeader findOne(@NotNull String id);

    List<ResponseHeader> findList(List<String> idList);

    /**
    * 查找
    * @param id
    * @return
    */
    ResponseHeader findResponseHeader(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<ResponseHeader> findAllResponseHeader();

    /**
    * 查询列表
    * @param responseHeaderQuery
    * @return
    */
    List<ResponseHeader> findResponseHeaderList(ResponseHeaderQuery responseHeaderQuery);

    /**
    * 按分页查询
    * @param responseHeaderQuery
    * @return
    */
    Pagination<ResponseHeader> findResponseHeaderPage(ResponseHeaderQuery responseHeaderQuery);

}