package io.tiklab.testhubo.test.func.service;

import io.tiklab.testhubo.test.test.model.TestCaseQuery;
import io.tiklab.testhubo.test.func.model.FuncUnitCase;
import io.tiklab.testhubo.test.func.model.FuncUnitCaseQuery;
import io.tiklab.core.page.Pagination;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 功能用例 服务
*/
public interface FuncUnitCaseService {

    /**
    * 创建功能用例
    * @param unitCase
    * @return
    */
    String createFuncUnitCase(@NotNull @Valid FuncUnitCase unitCase);

    /**
    * 更新功能用例
    * @param unitCase
    */
    void updateFuncUnitCase(@NotNull @Valid FuncUnitCase unitCase);

    /**
    * 删除功能用例
    * @param id
    */
    void deleteFuncUnitCase(@NotNull String id);

    FuncUnitCase findOne(@NotNull String id);

    List<FuncUnitCase> findList(List<String> idList);

    /**
    * 根据id查找功能用例
    * @param id
    * @return
    */
    FuncUnitCase findFuncUnitCase(@NotNull String id);

    /**
    * 查找所有功能用例
    * @return
    */
    List<FuncUnitCase> findAllFuncUnitCase();

    /**
    * 根据查询参数查询列表功能用例
    * @param functionTestCaseQuery
    * @return
    */
    List<FuncUnitCase> findFuncUnitCaseList(FuncUnitCaseQuery functionTestCaseQuery);

    /**
    * 根据查询参数按分页查询功能用例
    * @param functionTestCaseQuery
    * @return
    */
    Pagination<FuncUnitCase> findFuncUnitCasePage(FuncUnitCaseQuery functionTestCaseQuery);

    /**
     * 通过testCaseQuery查询功能用例
     * @param testCaseQuery
     * @return
     */
    List<FuncUnitCase> findFuncUnitCaseListByTestCase(TestCaseQuery testCaseQuery);


}