package io.tiklab.teston.test.common.stepassert.service;

import io.tiklab.join.annotation.FindOne;
import io.tiklab.join.annotation.JoinProvider;
import io.tiklab.teston.test.common.stepassert.model.VariableAssert;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
* 变量断言 服务接口
*/
@JoinProvider(model = VariableAssert.class)
public interface VariableAssertService {

    /**
    * 创建
    * @param variableAssert
    * @return
    */
    String createVariableAssert(@NotNull @Valid VariableAssert variableAssert);

    /**
    * 更新
    * @param variableAssert
    */
    void updateVariableAssert(@NotNull @Valid VariableAssert variableAssert);

    /**
    * 删除
    * @param id
    */
    void deleteVariableAssert(@NotNull String id);

    @FindOne
    VariableAssert findOne(@NotNull String id);


    /**
    * 根据id查找
    * @param id
    * @return
    */
    VariableAssert findVariableAssert(@NotNull String id);


}