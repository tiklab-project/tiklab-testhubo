package net.tiklab.teston.test.apix.http.unit.cases.service;

import net.tiklab.core.page.Pagination;
import net.tiklab.teston.test.apix.http.unit.cases.model.ResponseResult;
import net.tiklab.teston.test.apix.http.unit.cases.model.ResponseResultQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 响应结果 服务接口
*/
public interface ResponseResultService {

    /**
    * 创建响应结果
    * @param responseResult
    * @return
    */
    String createResponseResult(@NotNull @Valid ResponseResult responseResult);

    /**
    * 更新响应结果
    * @param responseResult
    */
    void updateResponseResult(@NotNull @Valid ResponseResult responseResult);

    /**
    * 删除响应结果
    * @param id
    */
    void deleteResponseResult(@NotNull String id);

    ResponseResult findOne(@NotNull String id);

    List<ResponseResult> findList(List<String> idList);

    /**
    * 查找响应结果
    * @param id
    * @return
    */
    ResponseResult findResponseResult(@NotNull String id);

    /**
    * 查找所有响应结果
    * @return
    */
    List<ResponseResult> findAllResponseResult();

    /**
    * 根据查询参数查询响应结果列表
    * @param responseResultQuery
    * @return
    */
    List<ResponseResult> findResponseResultList(ResponseResultQuery responseResultQuery);

    /**
    * 根据查询参数按分页查询响应结果
    * @param responseResultQuery
    * @return
    */
    Pagination<ResponseResult> findResponseResultPage(ResponseResultQuery responseResultQuery);

}