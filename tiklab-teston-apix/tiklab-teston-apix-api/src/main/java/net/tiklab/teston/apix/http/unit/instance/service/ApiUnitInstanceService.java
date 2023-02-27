package net.tiklab.teston.apix.http.unit.instance.service;

import net.tiklab.core.page.Pagination;
import net.tiklab.join.annotation.FindAll;
import net.tiklab.join.annotation.FindList;
import net.tiklab.join.annotation.FindOne;
import net.tiklab.join.annotation.JoinProvider;
import net.tiklab.teston.apix.http.unit.instance.model.ApiUnitInstance;
import net.tiklab.teston.apix.http.unit.instance.model.ApiUnitInstanceQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* ApiUnitStepInstanceService
*/
@JoinProvider(model = ApiUnitInstance.class)
public interface ApiUnitInstanceService {

    /**
    * 创建
    * @param testInstance
    * @return
    */
    String createApiUnitInstance(@NotNull @Valid ApiUnitInstance testInstance);

    String createApiUnitInstanceWithNest(@NotNull @Valid List<ApiUnitInstance> testInstances);

    /**
    * 更新
    * @param testInstance
    */
    void updateApiUnitInstance(@NotNull @Valid ApiUnitInstance testInstance);

    /**
    * 删除
    * @param id
    */
    void deleteApiUnitInstance(@NotNull String id);

    /**
     * 通过apiUnitId删除
     * @param apiUnitId
     */
    void deleteApiUnitInstanceByApiUnitId(@NotNull String apiUnitId);

    @FindOne
    ApiUnitInstance findOne(@NotNull String id);

    @FindList
    List<ApiUnitInstance> findList(List<String> idList);

    /**
    * 查找
    * @param id
    * @return
    */
    ApiUnitInstance findApiUnitInstance(@NotNull String id);

    ApiUnitInstance findApiUnitInstanceWithNest(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    @FindAll
    List<ApiUnitInstance> findAllApiUnitInstance();

    /**
    * 查询列表
    * @param testInstanceQuery
    * @return
    */
    List<ApiUnitInstance> findApiUnitInstanceList(ApiUnitInstanceQuery testInstanceQuery);

    /**
    * 按分页查询
    * @param testInstanceQuery
    * @return
    */
    Pagination<ApiUnitInstance> findApiUnitInstancePage(ApiUnitInstanceQuery testInstanceQuery);


    /**
     * 保存接口单元用例历史到数据库
     * @param apiUnitInstance
     */
    String saveApiUnitInstanceToSql(ApiUnitInstance apiUnitInstance);

}