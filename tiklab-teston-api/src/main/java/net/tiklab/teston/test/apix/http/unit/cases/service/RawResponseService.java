package net.tiklab.teston.test.apix.http.unit.cases.service;

import net.tiklab.core.page.Pagination;
import net.tiklab.teston.test.apix.http.unit.cases.model.RawResponse;
import net.tiklab.teston.test.apix.http.unit.cases.model.RawResponseQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 响应中raw 服务接口
*/
public interface RawResponseService {

    /**
    * 创建响应中raw
    * @param rawResponse
    * @return
    */
    String createRawResponse(@NotNull @Valid RawResponse rawResponse);

    /**
    * 更新响应中raw
    * @param rawResponse
    */
    void updateRawResponse(@NotNull @Valid RawResponse rawResponse);

    /**
    * 删除响应中raw
    * @param id
    */
    void deleteRawResponse(@NotNull String id);

    RawResponse findOne(@NotNull String id);

    List<RawResponse> findList(List<String> idList);

    /**
    * 查找响应中raw
    * @param id
    * @return
    */
    RawResponse findRawResponse(@NotNull String id);

    /**
    * 查找所有响应中raw
    * @return
    */
    List<RawResponse> findAllRawResponse();

    /**
    * 根据查询参数查询响应中raw列表
    * @param rawResponseQuery
    * @return
    */
    List<RawResponse> findRawResponseList(RawResponseQuery rawResponseQuery);

    /**
    * 根据查询参数按分页查询响应中raw
    * @param rawResponseQuery
    * @return
    */
    Pagination<RawResponse> findRawResponsePage(RawResponseQuery rawResponseQuery);

}