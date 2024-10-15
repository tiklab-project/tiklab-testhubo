package io.tiklab.testhubo.test.common.stepassert.service;

import io.tiklab.toolkit.join.annotation.JoinProvider;
import io.tiklab.testhubo.test.common.stepassert.model.ElementAssert;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
* 元素断言 服务接口
*/
@JoinProvider(model = ElementAssert.class)
public interface ElementAssertService {

    /**
    * 创建
    * @param elementAssert
    * @return
    */
    String createElementAssert(@NotNull @Valid ElementAssert elementAssert);

    /**
    * 更新
    * @param elementAssert
    */
    void updateElementAssert(@NotNull @Valid ElementAssert elementAssert);

    /**
    * 删除
    * @param id
    */
    void deleteElementAssert(@NotNull String id);

    ElementAssert findOne(@NotNull String id);


    /**
    * 根据id查找
    * @param id
    * @return
    */
    ElementAssert findElementAssert(@NotNull String id);


}