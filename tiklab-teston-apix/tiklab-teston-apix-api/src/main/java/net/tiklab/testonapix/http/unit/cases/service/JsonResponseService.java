package net.tiklab.testonapix.http.unit.cases.service;

import net.tiklab.core.page.Pagination;
import net.tiklab.join.annotation.FindAll;
import net.tiklab.join.annotation.FindList;
import net.tiklab.join.annotation.FindOne;
import net.tiklab.join.annotation.JoinProvider;
import net.tiklab.testonapix.http.unit.cases.model.JsonResponse;
import net.tiklab.testonapix.http.unit.cases.model.JsonResponseQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* JsonResponseService
*/
@JoinProvider(model = JsonResponse.class)
public interface JsonResponseService {

    /**
    * 创建
    * @param jsonResponse
    * @return
    */
    String createJsonResponse(@NotNull @Valid JsonResponse jsonResponse);

    /**
    * 更新
    * @param jsonResponse
    */
    void updateJsonResponse(@NotNull @Valid JsonResponse jsonResponse);

    /**
    * 删除
    * @param id
    */
    void deleteJsonResponse(@NotNull String id);

    @FindOne
    JsonResponse findOne(@NotNull String id);

    @FindList
    List<JsonResponse> findList(List<String> idList);

    /**
    * 查找
    * @param id
    * @return
    */
    JsonResponse findJsonResponse(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    @FindAll
    List<JsonResponse> findAllJsonResponse();

    /**
    * 查询列表
    * @param jsonResponseQuery
    * @return
    */
    List<JsonResponse> findJsonResponseList(JsonResponseQuery jsonResponseQuery);

    /**
    * 按分页查询
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