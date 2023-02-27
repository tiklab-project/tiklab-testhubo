package net.tiklab.teston.apix.http.unit.cases.service;

import net.tiklab.core.page.Pagination;
import net.tiklab.teston.apix.http.unit.cases.model.RawResponse;
import net.tiklab.teston.apix.http.unit.cases.model.RawResponseQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* RawResponseService
*/
public interface RawResponseService {

    /**
    * 创建
    * @param rawResponse
    * @return
    */
    String createRawResponse(@NotNull @Valid RawResponse rawResponse);

    /**
    * 更新
    * @param rawResponse
    */
    void updateRawResponse(@NotNull @Valid RawResponse rawResponse);

    /**
    * 删除
    * @param id
    */
    void deleteRawResponse(@NotNull String id);

    RawResponse findOne(@NotNull String id);

    List<RawResponse> findList(List<String> idList);

    /**
    * 查找
    * @param id
    * @return
    */
    RawResponse findRawResponse(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<RawResponse> findAllRawResponse();

    /**
    * 查询列表
    * @param rawResponseQuery
    * @return
    */
    List<RawResponse> findRawResponseList(RawResponseQuery rawResponseQuery);

    /**
    * 按分页查询
    * @param rawResponseQuery
    * @return
    */
    Pagination<RawResponse> findRawResponsePage(RawResponseQuery rawResponseQuery);

}