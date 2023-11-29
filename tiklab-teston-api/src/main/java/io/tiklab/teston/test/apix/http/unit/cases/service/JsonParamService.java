package io.tiklab.teston.test.apix.http.unit.cases.service;

import io.tiklab.teston.test.apix.http.unit.cases.model.JsonParamUnit;
import io.tiklab.teston.test.apix.http.unit.cases.model.JsonParamUnitQuery;
import io.tiklab.core.page.Pagination;
import io.tiklab.join.annotation.FindList;
import io.tiklab.join.annotation.FindOne;
import io.tiklab.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 请求中json  服务接口
*/
@JoinProvider(model = JsonParamUnit.class)
public interface JsonParamService {

    /**
    * 创建json
    * @param jsonParamUnit
    * @return
    */
    String createJsonParam(@NotNull @Valid JsonParamUnit jsonParamUnit);

    /**
    * 更新json
    * @param jsonParamUnit
    */
    void updateJsonParam(@NotNull @Valid JsonParamUnit jsonParamUnit);

    /**
    * 删除json
    * @param id
    */
    void deleteJsonParam(@NotNull String id);
    @FindOne
    JsonParamUnit findOne(@NotNull String id);
    @FindList
    List<JsonParamUnit> findList(List<String> idList);

    /**
    * 根据id查找json
    * @param id
    * @return
    */
    JsonParamUnit findJsonParam(@NotNull String id);

    /**
    * 查找所有json
    * @return
    */
    @FindOne
    List<JsonParamUnit> findAllJsonParam();

    /**
    * 根据查询参数查询json列表
    * @param jsonParamUnitQuery
    * @return
    */
    List<JsonParamUnit> findJsonParamList(JsonParamUnitQuery jsonParamUnitQuery);

    /**
    * 根据查询参数按分页查询json
    * @param jsonParamUnitQuery
    * @return
    */
    Pagination<JsonParamUnit> findJsonParamPage(JsonParamUnitQuery jsonParamUnitQuery);

}