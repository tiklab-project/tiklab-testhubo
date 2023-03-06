package net.tiklab.teston.test.apix.http.unit.cases.service;

import net.tiklab.core.page.Pagination;
import net.tiklab.teston.test.apix.http.unit.cases.model.ResponseHeader;
import net.tiklab.teston.test.apix.http.unit.cases.model.ResponseHeaderQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 响应头 服务接口
*/
public interface ResponseHeaderService {

    /**
    * 创建响应头
    * @param responseHeader
    * @return
    */
    String createResponseHeader(@NotNull @Valid ResponseHeader responseHeader);

    /**
    * 更新响应头
    * @param responseHeader
    */
    void updateResponseHeader(@NotNull @Valid ResponseHeader responseHeader);

    /**
    * 删除响应头
    * @param id
    */
    void deleteResponseHeader(@NotNull String id);

    ResponseHeader findOne(@NotNull String id);

    List<ResponseHeader> findList(List<String> idList);

    /**
    * 查找响应头
    * @param id
    * @return
    */
    ResponseHeader findResponseHeader(@NotNull String id);

    /**
    * 查找所有响应头
    * @return
    */
    List<ResponseHeader> findAllResponseHeader();

    /**
    * 根据查询参数查询响应头列表
    * @param responseHeaderQuery
    * @return
    */
    List<ResponseHeader> findResponseHeaderList(ResponseHeaderQuery responseHeaderQuery);

    /**
    * 根据查询参数按分页查询响应头
    * @param responseHeaderQuery
    * @return
    */
    Pagination<ResponseHeader> findResponseHeaderPage(ResponseHeaderQuery responseHeaderQuery);

}