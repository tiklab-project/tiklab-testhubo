package net.tiklab.teston.test.api.http.scene.instance.service;


import net.tiklab.core.page.Pagination;
import net.tiklab.join.annotation.FindList;
import net.tiklab.join.annotation.FindOne;
import net.tiklab.join.annotation.JoinProvider;
import net.tiklab.teston.test.api.http.scene.instance.model.ApiSceneStepInstanceBind;
import net.tiklab.teston.test.api.http.scene.instance.model.ApiSceneStepInstanceBindQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* ApiSceneStepInstanceBindService
*/
@JoinProvider(model = ApiSceneStepInstanceBind.class)
public interface ApiSceneStepInstanceBindService {

    /**
    * 创建
    * @param apiSceneStepInstanceBind
    * @return
    */
    String createApiSceneStepInstanceBind(@NotNull @Valid ApiSceneStepInstanceBind apiSceneStepInstanceBind);

    /**
    * 更新
    * @param apiSceneStepInstanceBind
    */
    void updateApiSceneStepInstanceBind(@NotNull @Valid ApiSceneStepInstanceBind apiSceneStepInstanceBind);

    /**
    * 删除
    * @param id
    */
    void deleteApiSceneStepInstanceBind(@NotNull String id);

    @FindOne
    ApiSceneStepInstanceBind findOne(@NotNull String id);

    @FindList
    List<ApiSceneStepInstanceBind> findList(List<String> idList);

    /**
    * 查找
    * @param id
    * @return
    */
    ApiSceneStepInstanceBind findApiSceneStepInstanceBind(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    @FindList
    List<ApiSceneStepInstanceBind> findAllApiSceneStepInstanceBind();

    /**
    * 查询列表
    * @param apiSceneStepInstanceBindQuery
    * @return
    */
    List<ApiSceneStepInstanceBind> findApiSceneStepInstanceBindList(ApiSceneStepInstanceBindQuery apiSceneStepInstanceBindQuery);

    /**
    * 按分页查询
    * @param apiSceneStepInstanceBindQuery
    * @return
    */
    Pagination<ApiSceneStepInstanceBind> findApiSceneStepInstanceBindPage(ApiSceneStepInstanceBindQuery apiSceneStepInstanceBindQuery);

}