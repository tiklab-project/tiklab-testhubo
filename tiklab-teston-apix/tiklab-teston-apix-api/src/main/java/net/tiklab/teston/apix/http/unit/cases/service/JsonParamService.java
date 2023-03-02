package net.tiklab.teston.apix.http.unit.cases.service;

import net.tiklab.core.page.Pagination;
import net.tiklab.join.annotation.FindList;
import net.tiklab.join.annotation.FindOne;
import net.tiklab.join.annotation.JoinProvider;
import net.tiklab.teston.apix.http.unit.cases.model.JsonParam;
import net.tiklab.teston.apix.http.unit.cases.model.JsonParamQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 请求中json  服务接口
*/
@JoinProvider(model = JsonParam.class)
public interface JsonParamService {

    /**
    * 创建json
    * @param jsonParam
    * @return
    */
    String createJsonParam(@NotNull @Valid JsonParam jsonParam);

    /**
    * 更新json
    * @param jsonParam
    */
    void updateJsonParam(@NotNull @Valid JsonParam jsonParam);

    /**
    * 删除json
    * @param id
    */
    void deleteJsonParam(@NotNull String id);
    @FindOne
    JsonParam findOne(@NotNull String id);
    @FindList
    List<JsonParam> findList(List<String> idList);

    /**
    * 根据id查找json
    * @param id
    * @return
    */
    JsonParam findJsonParam(@NotNull String id);

    /**
    * 查找所有json
    * @return
    */
    @FindOne
    List<JsonParam> findAllJsonParam();

    /**
    * 根据查询参数查询json列表
    * @param jsonParamQuery
    * @return
    */
    List<JsonParam> findJsonParamList(JsonParamQuery jsonParamQuery);

    /**
    * 根据查询参数按分页查询json
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