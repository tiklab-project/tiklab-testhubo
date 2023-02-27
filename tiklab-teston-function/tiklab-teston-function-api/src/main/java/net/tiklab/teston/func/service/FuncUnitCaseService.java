package net.tiklab.teston.func.service;

import net.tiklab.core.page.Pagination;
import net.tiklab.teston.func.model.FuncUnitCaseQuery;
import net.tiklab.teston.manager.testcase.model.TestCaseQuery;
import net.tiklab.teston.func.model.FuncUnitCase;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* FunctionalStepService
*/
public interface FuncUnitCaseService {

    /**
    * 创建
    * @param unitCase
    * @return
    */
    String createFuncUnitCase(@NotNull @Valid FuncUnitCase unitCase);

    /**
    * 更新
    * @param unitCase
    */
    void updateFuncUnitCase(@NotNull @Valid FuncUnitCase unitCase);

    /**
    * 删除
    * @param id
    */
    void deleteFuncUnitCase(@NotNull String id);

    FuncUnitCase findOne(@NotNull String id);

    List<FuncUnitCase> findList(List<String> idList);

    /**
    * 查找
    * @param id
    * @return
    */
    FuncUnitCase findFuncUnitCase(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<FuncUnitCase> findAllFuncUnitCase();

    /**
    * 查询列表
    * @param functionTestCaseQuery
    * @return
    */
    List<FuncUnitCase> findFuncUnitCaseList(FuncUnitCaseQuery functionTestCaseQuery);

    /**
    * 按分页查询
    * @param functionTestCaseQuery
    * @return
    */
    Pagination<FuncUnitCase> findFuncUnitCasePage(FuncUnitCaseQuery functionTestCaseQuery);

    /**
     * 通过testCaseQuery查询
     * @param testCaseQuery
     * @return
     */
    List<FuncUnitCase> findFuncUnitCaseListByTestCase(TestCaseQuery testCaseQuery);


}