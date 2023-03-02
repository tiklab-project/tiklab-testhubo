package net.tiklab.teston.apix.http.unit.instance.service;

import net.tiklab.core.page.Pagination;
import net.tiklab.join.annotation.FindAll;
import net.tiklab.join.annotation.FindList;
import net.tiklab.join.annotation.FindOne;
import net.tiklab.join.annotation.JoinProvider;
import net.tiklab.teston.apix.http.unit.instance.model.ApiUnitInstanceBind;
import net.tiklab.teston.apix.http.unit.instance.model.ApiUnitInstanceBindQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 接口单元公共实例 服务模型
*/
@JoinProvider(model = ApiUnitInstanceBind.class)
public interface ApiUnitInstanceBindService {

    /**
    * 创建接口单元公共实例
    * @param apiUnitInstanceBind
    * @return
    */
    String createApiUnitInstanceBind(@NotNull @Valid ApiUnitInstanceBind apiUnitInstanceBind);

    /**
    * 更新
    * @param apiUnitInstanceBind
    */
    void updateApiUnitInstanceBind(@NotNull @Valid ApiUnitInstanceBind apiUnitInstanceBind);

    /**
    * 删除接口单元公共实例
    * @param id
    */
    void deleteApiUnitInstanceBind(@NotNull String id);

    @FindOne
    ApiUnitInstanceBind findOne(@NotNull String id);

    @FindList
    List<ApiUnitInstanceBind> findList(List<String> idList);

    /**
    * 查找接口单元公共实例
    * @param id
    * @return
    */

    ApiUnitInstanceBind findApiUnitInstanceBind(@NotNull String id);

    /**
    * 查找所有接口单元公共实例
    * @return
    */
    @FindAll
    List<ApiUnitInstanceBind> findAllApiUnitInstanceBind();

    /**
    * 查询接口单元公共实例列表
    * @param apiUnitInstanceBindQuery
    * @return
    */
    List<ApiUnitInstanceBind> findApiUnitInstanceBindList(ApiUnitInstanceBindQuery apiUnitInstanceBindQuery);

    /**
    * 按分页查询接口单元公共实例
    * @param apiUnitInstanceBindQuery
    * @return
    */
    Pagination<ApiUnitInstanceBind> findApiUnitInstanceBindPage(ApiUnitInstanceBindQuery apiUnitInstanceBindQuery);

}