package net.tiklab.teston.test.apiunit.http.cases.service;

import net.tiklab.core.page.Pagination;

import net.tiklab.teston.test.apiunit.http.cases.model.PreScript;
import net.tiklab.teston.test.apiunit.http.cases.model.PreScriptQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* PreScriptService
*/
public interface PreScriptService {

    /**
    * 创建
    * @param preScript
    * @return
    */
    String createPreScript(@NotNull @Valid PreScript preScript);

    /**
    * 更新
    * @param preScript
    */
    void updatePreScript(@NotNull @Valid PreScript preScript);

    /**
    * 删除
    * @param id
    */
    void deletePreScript(@NotNull String id);

    PreScript findOne(@NotNull String id);

    List<PreScript> findList(List<String> idList);

    /**
    * 查找
    * @param id
    * @return
    */
    PreScript findPreScript(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<PreScript> findAllPreScript();

    /**
    * 查询列表
    * @param preScriptQuery
    * @return
    */
    List<PreScript> findPreScriptList(PreScriptQuery preScriptQuery);

    /**
    * 按分页查询
    * @param preScriptQuery
    * @return
    */
    Pagination<PreScript> findPreScriptPage(PreScriptQuery preScriptQuery);

}