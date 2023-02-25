package net.tiklab.teston.test.web.scene.instance.service;

import net.tiklab.core.page.Pagination;
import net.tiklab.teston.test.web.scene.instance.model.WebSceneInstanceQuery;
import net.tiklab.teston.test.web.scene.instance.model.WebSceneInstance;
import net.tiklab.teston.test.web.scene.execute.model.WebSceneTestResponse;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* TestInstanceService
*/
public interface WebSceneInstanceService {

    /**
    * 创建
    * @param scenInstance
    * @return
    */
    String createWebSceneInstance(@NotNull @Valid WebSceneInstance scenInstance);

    /**
    * 更新
    * @param scenInstance
    */
    void updateWebSceneInstance(@NotNull @Valid WebSceneInstance scenInstance);

    /**
    * 删除
    * @param id
    */
    void deleteWebSceneInstance(@NotNull String id);

    WebSceneInstance findOne(@NotNull String id);

    List<WebSceneInstance> findList(List<String> idList);

    /**
    * 查找
    * @param id
    * @return
    */
    WebSceneInstance findWebSceneInstance(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<WebSceneInstance> findAllWebSceneInstance();

    /**
    * 查询列表
    * @param scenInstanceQuery
    * @return
    */
    List<WebSceneInstance> findWebSceneInstanceList(WebSceneInstanceQuery scenInstanceQuery);

    /**
    * 按分页查询
    * @param scenInstanceQuery
    * @return
    */
    Pagination<WebSceneInstance> findWebSceneInstancePage(WebSceneInstanceQuery scenInstanceQuery);

    /**
     * 保存web场景历史到数据库
     * @param webSceneInstance
     * @param webSceneTestResponse
     * @return
     */
    String saveWebSceneInstanceToSql(WebSceneInstance webSceneInstance, WebSceneTestResponse webSceneTestResponse);

}