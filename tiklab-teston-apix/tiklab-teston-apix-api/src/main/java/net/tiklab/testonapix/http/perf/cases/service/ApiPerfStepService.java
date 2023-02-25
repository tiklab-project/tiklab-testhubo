package net.tiklab.testonapix.http.perf.cases.service;

import net.tiklab.core.page.Pagination;
import net.tiklab.testonapix.http.perf.cases.model.ApiPerfStep;
import net.tiklab.testonapix.http.perf.cases.model.ApiPerfStepQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* ApiPerfStepService
*/
public interface ApiPerfStepService {

    /**
    * 创建
    * @param apiPerfStep
    * @return
    */
    String createApiPerfStep(@NotNull @Valid ApiPerfStep apiPerfStep);

    /**
    * 更新
    * @param apiPerfStep
    */
    void updateApiPerfStep(@NotNull @Valid ApiPerfStep apiPerfStep);

    /**
    * 删除
    * @param id
    */
    void deleteApiPerfStep(@NotNull String id);

    ApiPerfStep findOne(@NotNull String id);

    List<ApiPerfStep> findList(List<String> idList);

    /**
    * 查找
    * @param id
    * @return
    */
    ApiPerfStep findApiPerfStep(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<ApiPerfStep> findAllApiPerfStep();

    /**
    * 查询列表
    * @param apiPerfStepQuery
    * @return
    */
    List<ApiPerfStep> findApiPerfStepList(ApiPerfStepQuery apiPerfStepQuery);

    /**
    * 按分页查询
    * @param apiPerfStepQuery
    * @return
    */
    Pagination<ApiPerfStep> findApiPerfStepPage(ApiPerfStepQuery apiPerfStepQuery);


    /**
     * 绑定Scene
     * @param apiSceneStepList
     * @return
     */
    void bindApiScene(List<ApiPerfStep> apiSceneStepList);



}