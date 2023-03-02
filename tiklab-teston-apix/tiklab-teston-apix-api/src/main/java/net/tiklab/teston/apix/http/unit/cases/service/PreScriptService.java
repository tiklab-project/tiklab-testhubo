package net.tiklab.teston.apix.http.unit.cases.service;

import net.tiklab.core.page.Pagination;
import net.tiklab.teston.apix.http.unit.cases.model.PreScript;
import net.tiklab.teston.apix.http.unit.cases.model.PreScriptQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 前置脚本 服务接口
*/
public interface PreScriptService {

    /**
    * 创建前置脚本
    * @param preScript
    * @return
    */
    String createPreScript(@NotNull @Valid PreScript preScript);

    /**
    * 更新前置脚本
    * @param preScript
    */
    void updatePreScript(@NotNull @Valid PreScript preScript);

    /**
    * 删除前置脚本
    * @param id
    */
    void deletePreScript(@NotNull String id);

    PreScript findOne(@NotNull String id);

    List<PreScript> findList(List<String> idList);

    /**
    * 根据id查找前置脚本
    * @param id
    * @return
    */
    PreScript findPreScript(@NotNull String id);

    /**
    * 查找所有前置脚本
    * @return
    */
    List<PreScript> findAllPreScript();

    /**
    * 根据查询参数查询前置脚本列表
    * @param preScriptQuery
    * @return
    */
    List<PreScript> findPreScriptList(PreScriptQuery preScriptQuery);

    /**
    * 根据查询参数按分页查询前置脚本
    * @param preScriptQuery
    * @return
    */
    Pagination<PreScript> findPreScriptPage(PreScriptQuery preScriptQuery);

}