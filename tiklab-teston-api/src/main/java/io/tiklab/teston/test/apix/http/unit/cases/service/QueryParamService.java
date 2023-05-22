package io.tiklab.teston.test.apix.http.unit.cases.service;

import io.tiklab.teston.test.apix.http.unit.cases.model.ApiUnitCase;
import io.tiklab.teston.test.apix.http.unit.cases.model.QueryParams;
import io.tiklab.teston.test.apix.http.unit.cases.model.QueryParamQuery;
import io.tiklab.core.page.Pagination;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* query 服务接口
*/
public interface QueryParamService {

    /**
    * 创建query
    * @param queryParams
    * @return
    */
    String createQueryParam(@NotNull @Valid QueryParams queryParams);

    /**
    * 更新query
    * @param queryParams
    */
    void updateQueryParam(@NotNull @Valid QueryParams queryParams);

    /**
    * 删除query
    * @param id
    */
    void deleteQueryParam(@NotNull String id);

    QueryParams findOne(@NotNull String id);

    List<QueryParams> findList(List<String> idList);

    /**
    * 根据id查找query
    * @param id
    * @return
    */
    QueryParams findQueryParam(@NotNull String id);

    /**
    * 查找所有query
    * @return
    */
    List<QueryParams> findAllQueryParam();

    /**
    * 根据查询参数查询query列表
    * @param queryParamQuery
    * @return
    */
    List<QueryParams> findQueryParamList(QueryParamQuery queryParamQuery);

    /**
    * 根据查询参数按分页查询query
    * @param queryParamQuery
    * @return
    */
    Pagination<QueryParams> findQueryParamPage(QueryParamQuery queryParamQuery);

    /**
     * 构造query参数
     * @param step
     * @return
     */
    String jointParam(ApiUnitCase step);

}