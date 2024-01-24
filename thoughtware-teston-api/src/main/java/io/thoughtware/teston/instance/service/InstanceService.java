package io.thoughtware.teston.instance.service;

import io.thoughtware.core.page.Pagination;
import io.thoughtware.toolkit.join.annotation.FindAll;
import io.thoughtware.toolkit.join.annotation.FindList;
import io.thoughtware.toolkit.join.annotation.FindOne;
import io.thoughtware.toolkit.join.annotation.JoinProvider;
import io.thoughtware.teston.instance.model.Instance;
import io.thoughtware.teston.instance.model.InstanceQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 公共实例 服务模型
*/
@JoinProvider(model = Instance.class)
public interface InstanceService {

    /**
    * 创建公共实例
    * @param instance
    * @return
    */
    String createInstance(@NotNull @Valid Instance instance);

    /**
    * 更新
    * @param instance
    */
    void updateInstance(@NotNull @Valid Instance instance);

    /**
    * 删除公共实例
    * @param id
    */
    void deleteInstance(@NotNull String id);

    @FindOne
    Instance findOne(@NotNull String id);

    @FindList
    List<Instance> findList(List<String> idList);

    /**
    * 查找公共实例
    * @param id
    * @return
    */

    Instance findInstance(@NotNull String id);

    /**
    * 查找所有公共实例
    * @return
    */
    @FindAll
    List<Instance> findAllInstance();

    /**
    * 查询公共实例列表
    * @param instanceQuery
    * @return
    */
    List<Instance> findInstanceList(InstanceQuery instanceQuery);

    /**
    * 按分页查询公共实例
    * @param instanceQuery
    * @return
    */
    Pagination<Instance> findInstancePage(InstanceQuery instanceQuery);

    /**
     * 获取最近一次执行次数
     * @param belongId
     * @return
     */
    int getRecentExecuteNum(String belongId);


}