package net.tiklab.testonapix.http.unit.cases.service;

import net.tiklab.core.page.Pagination;
import net.tiklab.join.annotation.FindList;
import net.tiklab.join.annotation.FindOne;
import net.tiklab.join.annotation.JoinProvider;
import net.tiklab.testonapix.http.unit.cases.model.JsonParam;
import net.tiklab.testonapix.http.unit.cases.model.JsonParamQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* JsonParamService
*/
@JoinProvider(model = JsonParam.class)
public interface JsonParamService {

    /**
    * 创建
    * @param jsonParam
    * @return
    */
    String createJsonParam(@NotNull @Valid JsonParam jsonParam);

    /**
    * 更新
    * @param jsonParam
    */
    void updateJsonParam(@NotNull @Valid JsonParam jsonParam);

    /**
    * 删除
    * @param id
    */
    void deleteJsonParam(@NotNull String id);
    @FindOne
    JsonParam findOne(@NotNull String id);
    @FindList
    List<JsonParam> findList(List<String> idList);

    /**
    * 查找
    * @param id
    * @return
    */
    JsonParam findJsonParam(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    @FindOne
    List<JsonParam> findAllJsonParam();

    /**
    * 查询列表
    * @param jsonParamQuery
    * @return
    */
    List<JsonParam> findJsonParamList(JsonParamQuery jsonParamQuery);

    /**
    * 按分页查询
    * @param jsonParamQuery
    * @return
    */
    Pagination<JsonParam> findJsonParamPage(JsonParamQuery jsonParamQuery);
    /**
     * 查询json树
     * @param jsonParamQuery
     * @return
     */
    List<JsonParam> findJsonParamListTree(JsonParamQuery jsonParamQuery);
}