package net.tiklab.teston.test.apix.http.unit.cases.service;

import net.tiklab.core.page.Pagination;
import net.tiklab.join.annotation.FindAll;
import net.tiklab.join.annotation.FindList;
import net.tiklab.join.annotation.FindOne;
import net.tiklab.join.annotation.JoinProvider;
import net.tiklab.teston.test.apix.http.unit.cases.model.JsonResponse;
import net.tiklab.teston.test.apix.http.unit.cases.model.JsonResponseQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 响应中json 服务接口
*/
@JoinProvider(model = JsonResponse.class)
public interface JsonResponseService {

    /**
    * 创建响应中json
    * @param jsonResponse
    * @return
    */
    String createJsonResponse(@NotNull @Valid JsonResponse jsonResponse);

    /**
    * 更新响应中json
    * @param jsonResponse
    */
    void updateJsonResponse(@NotNull @Valid JsonResponse jsonResponse);

    /**
    * 删除响应中json
    * @param id
    */
    void deleteJsonResponse(@NotNull String id);

    @FindOne
    JsonResponse findOne(@NotNull String id);

    @FindList
    List<JsonResponse> findList(List<String> idList);

    /**
    * 根据id查找响应中json
    * @param id
    * @return
    */
    JsonResponse findJsonResponse(@NotNull String id);

    /**
    * 查找所有响应中json
    * @return
    */
    @FindAll
    List<JsonResponse> findAllJsonResponse();

    /**
    * 根据查询参数查找查询响应中json列表
    * @param jsonResponseQuery
    * @return
    */
    List<JsonResponse> findJsonResponseList(JsonResponseQuery jsonResponseQuery);

    /**
    * 根据查询参数查找按分页查询响应中json
    * @param jsonResponseQuery
    * @return
    */
    Pagination<JsonResponse> findJsonResponsePage(JsonResponseQuery jsonResponseQuery);

    /**
     * 查询json返回结果树
     * @param jsonResponseQuery
     * @return
     */
    List<JsonResponse> findJsonResponseListTree(JsonResponseQuery jsonResponseQuery);
}