package net.tiklab.teston.test.apiunit.http.cases.service;

import net.tiklab.core.page.Pagination;

import net.tiklab.teston.test.apiunit.http.cases.model.ResponseResult;
import net.tiklab.teston.test.apiunit.http.cases.model.ResponseResultQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* ResponseResultService
*/
public interface ResponseResultService {

    /**
    * 创建
    * @param responseResult
    * @return
    */
    String createResponseResult(@NotNull @Valid ResponseResult responseResult);

    /**
    * 更新
    * @param responseResult
    */
    void updateResponseResult(@NotNull @Valid ResponseResult responseResult);

    /**
    * 删除
    * @param id
    */
    void deleteResponseResult(@NotNull String id);

    ResponseResult findOne(@NotNull String id);

    List<ResponseResult> findList(List<String> idList);

    /**
    * 查找
    * @param id
    * @return
    */
    ResponseResult findResponseResult(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<ResponseResult> findAllResponseResult();

    /**
    * 查询列表
    * @param responseResultQuery
    * @return
    */
    List<ResponseResult> findResponseResultList(ResponseResultQuery responseResultQuery);

    /**
    * 按分页查询
    * @param responseResultQuery
    * @return
    */
    Pagination<ResponseResult> findResponseResultPage(ResponseResultQuery responseResultQuery);

}