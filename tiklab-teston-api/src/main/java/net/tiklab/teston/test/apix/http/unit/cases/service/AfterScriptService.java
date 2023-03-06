package net.tiklab.teston.test.apix.http.unit.cases.service;

import net.tiklab.core.page.Pagination;
import net.tiklab.teston.test.apix.http.unit.cases.model.AfterScript;
import net.tiklab.teston.test.apix.http.unit.cases.model.AfterScriptQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 后置脚本 服务接口
*/
public interface AfterScriptService {

    /**
    * 创建后置脚本
    * @param afterScript
    * @return
    */
    String createAfterScript(@NotNull @Valid AfterScript afterScript);

    /**
    * 更新后置脚本
    * @param afterScript
    */
    void updateAfterScript(@NotNull @Valid AfterScript afterScript);

    /**
    * 删除后置脚本
    * @param id
    */
    void deleteAfterScript(@NotNull String id);

    AfterScript findOne(@NotNull String id);

    List<AfterScript> findList(List<String> idList);

    /**
    * 根据id查找后置脚本
    * @param id
    * @return
    */
    AfterScript findAfterScript(@NotNull String id);

    /**
    * 查找所有后置脚本
    * @return
    */
    List<AfterScript> findAllAfterScript();

    /**
    * 通过查询参数查询后置脚本列表
    * @param afterScriptQuery
    * @return
    */
    List<AfterScript> findAfterScriptList(AfterScriptQuery afterScriptQuery);

    /**
    * 通过查询参数按分页查询后置脚本
    * @param afterScriptQuery
    * @return
    */
    Pagination<AfterScript> findAfterScriptPage(AfterScriptQuery afterScriptQuery);

}