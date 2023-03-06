package net.tiklab.teston.test.web.scene.instance.service;

import net.tiklab.core.page.Pagination;
import net.tiklab.teston.test.web.scene.execute.model.WebSceneTestResponse;
import net.tiklab.teston.test.web.scene.instance.model.WebSceneInstance;
import net.tiklab.teston.test.web.scene.instance.model.WebSceneInstanceQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* web场景实例 服务接口
*/
public interface WebSceneInstanceService {

    /**
    * 创建web场景实例
    * @param scenInstance
    * @return
    */
    String createWebSceneInstance(@NotNull @Valid WebSceneInstance scenInstance);

    /**
    * 更新web场景实例
    * @param scenInstance
    */
    void updateWebSceneInstance(@NotNull @Valid WebSceneInstance scenInstance);

    /**
    * 删除web场景实例
    * @param id
    */
    void deleteWebSceneInstance(@NotNull String id);

    WebSceneInstance findOne(@NotNull String id);

    List<WebSceneInstance> findList(List<String> idList);

    /**
    * 根据id查找web场景实例
    * @param id
    * @return
    */
    WebSceneInstance findWebSceneInstance(@NotNull String id);

    /**
    * 查找所有web场景实例
    * @return
    */
    List<WebSceneInstance> findAllWebSceneInstance();

    /**
    * 根据查询参数查询web场景实例列表
    * @param scenInstanceQuery
    * @return
    */
    List<WebSceneInstance> findWebSceneInstanceList(WebSceneInstanceQuery scenInstanceQuery);

    /**
    * 根据查询参数按分页查询web场景实例
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