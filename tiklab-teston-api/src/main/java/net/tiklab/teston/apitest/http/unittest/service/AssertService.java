package net.tiklab.teston.apitest.http.unittest.service;

import net.tiklab.core.page.Pagination;

import net.tiklab.teston.apitest.http.unittest.model.AssertCase;
import net.tiklab.teston.apitest.http.unittest.model.AssertCaseQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* AssertCaseService
*/
public interface AssertService {

    /**
    * 创建
    * @param assertCase
    * @return
    */
    String createAssertCase(@NotNull @Valid AssertCase assertCase);

    /**
    * 更新
    * @param assertCase
    */
    void updateAssertCase(@NotNull @Valid AssertCase assertCase);

    /**
    * 删除
    * @param id
    */
    void deleteAssertCase(@NotNull String id);

    AssertCase findOne(@NotNull String id);

    List<AssertCase> findList(List<String> idList);

    /**
    * 查找
    * @param id
    * @return
    */
    AssertCase findAssertCase(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<AssertCase> findAllAssertCase();

    /**
    * 查询列表
    * @param assertCaseQuery
    * @return
    */
    List<AssertCase> findAssertCaseList(AssertCaseQuery assertCaseQuery);

    /**
    * 按分页查询
    * @param assertCaseQuery
    * @return
    */
    Pagination<AssertCase> findAssertCasePage(AssertCaseQuery assertCaseQuery);

}