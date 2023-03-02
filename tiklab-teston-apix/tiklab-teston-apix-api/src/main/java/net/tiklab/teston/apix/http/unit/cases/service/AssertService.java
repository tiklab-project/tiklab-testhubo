package net.tiklab.teston.apix.http.unit.cases.service;

import net.tiklab.core.page.Pagination;
import net.tiklab.teston.apix.http.unit.cases.model.AssertCase;
import net.tiklab.teston.apix.http.unit.cases.model.AssertCaseQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 断言 服务接口
*/
public interface AssertService {

    /**
    * 创建断言
    * @param assertCase
    * @return
    */
    String createAssertCase(@NotNull @Valid AssertCase assertCase);

    /**
    * 更新断言
    * @param assertCase
    */
    void updateAssertCase(@NotNull @Valid AssertCase assertCase);

    /**
    * 删除断言
    * @param id
    */
    void deleteAssertCase(@NotNull String id);

    AssertCase findOne(@NotNull String id);

    List<AssertCase> findList(List<String> idList);

    /**
    * 通过id查找断言
    * @param id
    * @return
    */
    AssertCase findAssertCase(@NotNull String id);

    /**
    * 查找所有断言
    * @return
    */
    List<AssertCase> findAllAssertCase();

    /**
    * 查询断言列表
    * @param assertCaseQuery
    * @return
    */
    List<AssertCase> findAssertCaseList(AssertCaseQuery assertCaseQuery);

    /**
    * 按分页查询断言
    * @param assertCaseQuery
    * @return
    */
    Pagination<AssertCase> findAssertCasePage(AssertCaseQuery assertCaseQuery);

}