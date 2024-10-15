package io.tiklab.testhubo.instance.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.toolkit.join.annotation.FindAll;
import io.tiklab.toolkit.join.annotation.FindList;
import io.tiklab.toolkit.join.annotation.FindOne;
import io.tiklab.toolkit.join.annotation.JoinProvider;
import io.tiklab.testhubo.instance.model.Instance;
import io.tiklab.testhubo.instance.model.InstanceQuery;

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
    void deleteInstance(@NotNull String id,String caseType);

    /**
     * 通过caseId删除
     * @param caseId
     */
    void deleteAllInstance(String caseId);

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
     * 查找最近一次的实例
     * @param belongId
     * @return
     */
    Instance findRecentInstance(String belongId);

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



    /**
     * 获取最近一次执行历史id
     * @param belongId
     * @return
     */
    String getRecentExecuteInstanceId(String belongId);

    /**
     * 查询单个用例下的历史数量
     * @param belongId
     * @return
     */
    int findInstanceNum(String belongId);

    /**
     * 查询仓库下的历史数量
     * @param repositoryId
     * @return
     */
    int findInstanceNumByRepositoryId(String repositoryId);

}