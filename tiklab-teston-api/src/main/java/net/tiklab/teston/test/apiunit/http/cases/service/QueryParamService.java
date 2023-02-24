package net.tiklab.teston.test.apiunit.http.cases.service;

import net.tiklab.core.page.Pagination;

import net.tiklab.teston.test.apiunit.http.cases.model.QueryParam;
import net.tiklab.teston.test.apiunit.http.cases.model.QueryParamQuery;
import net.tiklab.teston.test.apiunit.http.cases.model.ApiUnitCase;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* QueryParamService
*/
public interface QueryParamService {

    /**
    * 创建
    * @param queryParam
    * @return
    */
    String createQueryParam(@NotNull @Valid QueryParam queryParam);

    /**
    * 更新
    * @param queryParam
    */
    void updateQueryParam(@NotNull @Valid QueryParam queryParam);

    /**
    * 删除
    * @param id
    */
    void deleteQueryParam(@NotNull String id);

    QueryParam findOne(@NotNull String id);

    List<QueryParam> findList(List<String> idList);

    /**
    * 查找
    * @param id
    * @return
    */
    QueryParam findQueryParam(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<QueryParam> findAllQueryParam();

    /**
    * 查询列表
    * @param queryParamQuery
    * @return
    */
    List<QueryParam> findQueryParamList(QueryParamQuery queryParamQuery);

    /**
    * 按分页查询
    * @param queryParamQuery
    * @return
    */
    Pagination<QueryParam> findQueryParamPage(QueryParamQuery queryParamQuery);

    //==========
    String jointParam(ApiUnitCase step);

}