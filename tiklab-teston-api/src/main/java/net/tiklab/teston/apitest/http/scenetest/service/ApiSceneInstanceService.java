package net.tiklab.teston.apitest.http.scenetest.service;

import net.tiklab.core.page.Pagination;
import net.tiklab.join.annotation.FindAll;
import net.tiklab.join.annotation.FindList;
import net.tiklab.join.annotation.FindOne;
import net.tiklab.join.annotation.JoinProvider;
import net.tiklab.teston.apitest.http.scenetest.model.ApiSceneInstance;
import net.tiklab.teston.apitest.http.scenetest.model.ApiSceneInstanceQuery;
import net.tiklab.teston.apitest.http.scenetest.model.ApiSceneTestResponse;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* TestInstanceService
*/
@JoinProvider(model = ApiSceneInstance.class)
public interface ApiSceneInstanceService {

    /**
    * 创建
    * @param scenInstance
    * @return
    */
    String createApiSceneInstance(@NotNull @Valid ApiSceneInstance scenInstance);

    /**
    * 更新
    * @param scenInstance
    */
    void updateApiSceneInstance(@NotNull @Valid ApiSceneInstance scenInstance);

    /**
    * 删除
    * @param id
    */
    void deleteApiSceneInstance(@NotNull String id);

    @FindOne
    ApiSceneInstance findOne(@NotNull String id);

    @FindList
    List<ApiSceneInstance> findList(List<String> idList);

    /**
    * 查找
    * @param id
    * @return
    */
    ApiSceneInstance findApiSceneInstance(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    @FindAll
    List<ApiSceneInstance> findAllApiSceneInstance();

    /**
    * 查询列表
    * @param scenInstanceQuery
    * @return
    */
    List<ApiSceneInstance> findApiSceneInstanceList(ApiSceneInstanceQuery scenInstanceQuery);

    /**
    * 按分页查询
    * @param scenInstanceQuery
    * @return
    */
    Pagination<ApiSceneInstance> findApiSceneInstancePage(ApiSceneInstanceQuery scenInstanceQuery);


    /**
     * 保存接口场景用例历史到数据库
     * @param apiSceneInstance
     * @param apiSceneTestResponse
     * @return
     */
    String saveApiSceneInstanceToSql(ApiSceneInstance apiSceneInstance, ApiSceneTestResponse apiSceneTestResponse);

}