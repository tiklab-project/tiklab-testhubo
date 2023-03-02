package net.tiklab.teston.app.scene.instance.service;

import net.tiklab.core.page.Pagination;
import net.tiklab.join.annotation.FindAll;
import net.tiklab.join.annotation.FindList;
import net.tiklab.join.annotation.FindOne;
import net.tiklab.join.annotation.JoinProvider;
import net.tiklab.teston.app.scene.execute.model.AppSceneTestResponse;
import net.tiklab.teston.app.scene.instance.model.AppSceneInstance;
import net.tiklab.teston.app.scene.instance.model.AppSceneInstanceQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* app场景测试历史实例 服务接口
*/
@JoinProvider(model = AppSceneInstance.class)
public interface AppSceneInstanceService {

    /**
    * 创建app场景测试历史实例
    * @param scenInstance
    * @return
    */
    String createAppSceneInstance(@NotNull @Valid AppSceneInstance scenInstance);

    /**
    * 更新app场景测试历史实例
    * @param scenInstance
    */
    void updateAppSceneInstance(@NotNull @Valid AppSceneInstance scenInstance);

    /**
    * 删除app场景测试历史实例
    * @param id
    */
    void deleteAppSceneInstance(@NotNull String id);

    @FindOne
    AppSceneInstance findOne(@NotNull String id);

    @FindList
    List<AppSceneInstance> findList(List<String> idList);

    /**
    * 根据id查找app场景测试历史实例
    * @param id
    * @return
    */
    AppSceneInstance findAppSceneInstance(@NotNull String id);

    /**
    * 查找所有app场景测试历史实例
    * @return
    */
    @FindAll
    List<AppSceneInstance> findAllAppSceneInstance();

    /**
    * 根据查询参数查询app场景测试历史实例列表
    * @param scenInstanceQuery
    * @return
    */
    List<AppSceneInstance> findAppSceneInstanceList(AppSceneInstanceQuery scenInstanceQuery);

    /**
    * 根据查询参数按分页查询app场景测试历史实例
    * @param scenInstanceQuery
    * @return
    */
    Pagination<AppSceneInstance> findAppSceneInstancePage(AppSceneInstanceQuery scenInstanceQuery);


    /**
     * 保存app场景实例
     * @param appSceneInstance
     * @param appSceneTestResponse
     * @return
     */
    String saveAppSceneInstanceToSql(AppSceneInstance appSceneInstance, AppSceneTestResponse appSceneTestResponse);

}