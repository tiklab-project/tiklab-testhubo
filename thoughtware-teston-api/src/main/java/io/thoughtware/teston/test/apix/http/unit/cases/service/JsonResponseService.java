package io.thoughtware.teston.test.apix.http.unit.cases.service;

import io.thoughtware.teston.test.apix.http.unit.cases.model.JsonResponseUnit;
import io.thoughtware.teston.test.apix.http.unit.cases.model.JsonResponseUnitQuery;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.toolkit.join.annotation.FindAll;
import io.thoughtware.toolkit.join.annotation.FindList;
import io.thoughtware.toolkit.join.annotation.FindOne;
import io.thoughtware.toolkit.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 响应中json 服务接口
*/
@JoinProvider(model = JsonResponseUnit.class)
public interface JsonResponseService {

    /**
    * 创建响应中json
    * @param jsonResponseUnit
    * @return
    */
    String createJsonResponse(@NotNull @Valid JsonResponseUnit jsonResponseUnit);

    /**
    * 更新响应中json
    * @param jsonResponseUnit
    */
    void updateJsonResponse(@NotNull @Valid JsonResponseUnit jsonResponseUnit);

    /**
    * 删除响应中json
    * @param id
    */
    void deleteJsonResponse(@NotNull String id);

    @FindOne
    JsonResponseUnit findOne(@NotNull String id);

    @FindList
    List<JsonResponseUnit> findList(List<String> idList);

    /**
    * 根据id查找响应中json
    * @param id
    * @return
    */
    JsonResponseUnit findJsonResponse(@NotNull String id);

    /**
    * 查找所有响应中json
    * @return
    */
    @FindAll
    List<JsonResponseUnit> findAllJsonResponse();

    /**
    * 根据查询参数查找查询响应中json列表
    * @param jsonResponseUnitQuery
    * @return
    */
    List<JsonResponseUnit> findJsonResponseList(JsonResponseUnitQuery jsonResponseUnitQuery);

    /**
    * 根据查询参数查找按分页查询响应中json
    * @param jsonResponseUnitQuery
    * @return
    */
    Pagination<JsonResponseUnit> findJsonResponsePage(JsonResponseUnitQuery jsonResponseUnitQuery);

    /**
     * 查询json返回结果树
     * @param jsonResponseUnitQuery
     * @return
     */
    List<JsonResponseUnit> findJsonResponseListTree(JsonResponseUnitQuery jsonResponseUnitQuery);
}