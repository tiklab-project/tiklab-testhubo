package net.tiklab.testonapp.scene.instance.service;

import net.tiklab.core.page.Pagination;
import net.tiklab.join.annotation.FindAll;
import net.tiklab.join.annotation.FindList;
import net.tiklab.join.annotation.FindOne;
import net.tiklab.join.annotation.JoinProvider;
import net.tiklab.testonapp.scene.execute.model.AppSceneTestResponse;
import net.tiklab.testonapp.scene.instance.model.AppSceneInstance;
import net.tiklab.testonapp.scene.instance.model.AppSceneInstanceQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* TestInstanceService
*/
@JoinProvider(model = AppSceneInstance.class)
public interface AppSceneInstanceService {

    /**
    * 创建
    * @param scenInstance
    * @return
    */
    String createAppSceneInstance(@NotNull @Valid AppSceneInstance scenInstance);

    /**
    * 更新
    * @param scenInstance
    */
    void updateAppSceneInstance(@NotNull @Valid AppSceneInstance scenInstance);

    /**
    * 删除
    * @param id
    */
    void deleteAppSceneInstance(@NotNull String id);

    @FindOne
    AppSceneInstance findOne(@NotNull String id);

    @FindList
    List<AppSceneInstance> findList(List<String> idList);

    /**
    * 查找
    * @param id
    * @return
    */
    AppSceneInstance findAppSceneInstance(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    @FindAll
    List<AppSceneInstance> findAllAppSceneInstance();

    /**
    * 查询列表
    * @param scenInstanceQuery
    * @return
    */
    List<AppSceneInstance> findAppSceneInstanceList(AppSceneInstanceQuery scenInstanceQuery);

    /**
    * 按分页查询
    * @param scenInstanceQuery
    * @return
    */
    Pagination<AppSceneInstance> findAppSceneInstancePage(AppSceneInstanceQuery scenInstanceQuery);


    String saveAppSceneInstanceToSql(AppSceneInstance appSceneInstance, AppSceneTestResponse appSceneTestResponse);

}