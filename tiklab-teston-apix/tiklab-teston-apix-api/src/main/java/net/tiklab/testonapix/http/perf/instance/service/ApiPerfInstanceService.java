package net.tiklab.testonapix.http.perf.instance.service;

import net.tiklab.core.page.Pagination;
import net.tiklab.testonapix.http.perf.instance.model.ApiPerfInstance;
import net.tiklab.testonapix.http.perf.instance.model.ApiPerfInstanceQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* ApiPerfInstanceService
*/
public interface ApiPerfInstanceService {

    /**
    * 创建
    * @param perfInstance
    * @return
    */
    String createApiPerfInstance(@NotNull @Valid ApiPerfInstance perfInstance);

    /**
    * 更新
    * @param perfInstance
    */
    void updateApiPerfInstance(@NotNull @Valid ApiPerfInstance perfInstance);

    /**
    * 删除
    * @param id
    */
    void deleteApiPerfInstance(@NotNull String id);

    ApiPerfInstance findOne(@NotNull String id);

    List<ApiPerfInstance> findList(List<String> idList);

    /**
    * 查找
    * @param id
    * @return
    */
    ApiPerfInstance findApiPerfInstance(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<ApiPerfInstance> findAllApiPerfInstance();

    /**
    * 查询列表
    * @param apiPerfInstanceQuery
    * @return
    */
    List<ApiPerfInstance> findApiPerfInstanceList(ApiPerfInstanceQuery apiPerfInstanceQuery);

    /**
    * 按分页查询
    * @param apiPerfInstanceQuery
    * @return
    */
    Pagination<ApiPerfInstance> findApiPerfInstancePage(ApiPerfInstanceQuery apiPerfInstanceQuery);

}