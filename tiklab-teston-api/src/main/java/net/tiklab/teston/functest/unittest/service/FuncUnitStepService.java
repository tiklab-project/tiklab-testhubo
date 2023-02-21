package net.tiklab.teston.functest.unittest.service;


import net.tiklab.core.page.Pagination;
import net.tiklab.teston.functest.unittest.model.FuncUnitStep;
import net.tiklab.teston.functest.unittest.model.FuncUnitStepQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* FuncUnitStepService
*/
public interface FuncUnitStepService {

    /**
    * 创建
    * @param funcUnitStep
    * @return
    */
    String createFuncUnitStep(@NotNull @Valid FuncUnitStep funcUnitStep);

    /**
    * 更新
    * @param funcUnitStep
    */
    void updateFuncUnitStep(@NotNull @Valid FuncUnitStep funcUnitStep);

    /**
    * 删除
    * @param id
    */
    void deleteFuncUnitStep(@NotNull String id);

    FuncUnitStep findOne(@NotNull String id);

    List<FuncUnitStep> findList(List<String> idList);

    /**
    * 查找
    * @param id
    * @return
    */
    FuncUnitStep findFuncUnitStep(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<FuncUnitStep> findAllFuncUnitStep();

    /**
    * 查询列表
    * @param funcUnitStepQuery
    * @return
    */
    List<FuncUnitStep> findFuncUnitStepList(FuncUnitStepQuery funcUnitStepQuery);

    /**
    * 按分页查询
    * @param funcUnitStepQuery
    * @return
    */
    Pagination<FuncUnitStep> findFuncUnitStepPage(FuncUnitStepQuery funcUnitStepQuery);

}