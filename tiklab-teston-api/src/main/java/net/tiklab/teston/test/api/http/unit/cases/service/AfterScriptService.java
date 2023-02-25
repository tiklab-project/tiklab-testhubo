package net.tiklab.teston.test.api.http.unit.cases.service;

import net.tiklab.core.page.Pagination;

import net.tiklab.teston.test.api.http.unit.cases.model.AfterScriptQuery;
import net.tiklab.teston.test.api.http.unit.cases.model.AfterScript;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* AfterScriptService
*/
public interface AfterScriptService {

    /**
    * 创建
    * @param afterScript
    * @return
    */
    String createAfterScript(@NotNull @Valid AfterScript afterScript);

    /**
    * 更新
    * @param afterScript
    */
    void updateAfterScript(@NotNull @Valid AfterScript afterScript);

    /**
    * 删除
    * @param id
    */
    void deleteAfterScript(@NotNull String id);

    AfterScript findOne(@NotNull String id);

    List<AfterScript> findList(List<String> idList);

    /**
    * 查找
    * @param id
    * @return
    */
    AfterScript findAfterScript(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<AfterScript> findAllAfterScript();

    /**
    * 查询列表
    * @param afterScriptQuery
    * @return
    */
    List<AfterScript> findAfterScriptList(AfterScriptQuery afterScriptQuery);

    /**
    * 按分页查询
    * @param afterScriptQuery
    * @return
    */
    Pagination<AfterScript> findAfterScriptPage(AfterScriptQuery afterScriptQuery);

}