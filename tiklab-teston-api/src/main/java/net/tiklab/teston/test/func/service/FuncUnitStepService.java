package net.tiklab.teston.test.func.service;


import net.tiklab.core.page.Pagination;
import net.tiklab.teston.test.func.model.FuncUnitStep;
import net.tiklab.teston.test.func.model.FuncUnitStepQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 功能用例下步骤 服务接口
*/
public interface FuncUnitStepService {

    /**
    * 创建功能用例下步骤
    * @param funcUnitStep
    * @return
    */
    String createFuncUnitStep(@NotNull @Valid FuncUnitStep funcUnitStep);

    /**
    * 更新功能用例下步骤
    * @param funcUnitStep
    */
    void updateFuncUnitStep(@NotNull @Valid FuncUnitStep funcUnitStep);

    /**
    * 删除功能用例下步骤
    * @param id
    */
    void deleteFuncUnitStep(@NotNull String id);

    FuncUnitStep findOne(@NotNull String id);

    List<FuncUnitStep> findList(List<String> idList);

    /**
    * 根据id查找功能用例下步骤
    * @param id
    * @return
    */
    FuncUnitStep findFuncUnitStep(@NotNull String id);

    /**
    * 查找所有功能用例下步骤
    * @return
    */
    List<FuncUnitStep> findAllFuncUnitStep();

    /**
    * 根据查询参数查询功能用例下步骤列表
    * @param funcUnitStepQuery
    * @return
    */
    List<FuncUnitStep> findFuncUnitStepList(FuncUnitStepQuery funcUnitStepQuery);

    /**
    * 根据查询参数按分页查询功能用例下步骤
    * @param funcUnitStepQuery
    * @return
    */
    Pagination<FuncUnitStep> findFuncUnitStepPage(FuncUnitStepQuery funcUnitStepQuery);

}