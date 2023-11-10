package io.tiklab.teston.test.common.service;

import io.tiklab.join.annotation.FindOne;
import io.tiklab.join.annotation.JoinProvider;
import io.tiklab.teston.test.common.model.StepAssertCommonQuery;
import io.tiklab.teston.test.common.model.StepAssertCommon;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 步骤公共断言 服务接口
*/
@JoinProvider(model = StepAssertCommon.class)
public interface StepAssertCommonService {

    /**
    * 创建
    * @param stepAssertCommon
    * @return
    */
    String createStepAssertCommon(@NotNull @Valid StepAssertCommon stepAssertCommon);

    /**
    * 更新
    * @param stepAssertCommon
    */
    void updateStepAssertCommon(@NotNull @Valid StepAssertCommon stepAssertCommon);

    /**
    * 删除
    * @param id
    */
    void deleteStepAssertCommon(@NotNull String id);

    @FindOne
    StepAssertCommon findOne(@NotNull String id);


    /**
    * 根据id查找
    * @param id
    * @return
    */
    StepAssertCommon findStepAssertCommon(@NotNull String id);


    List<StepAssertCommon> findStepAssertCommonList(StepAssertCommonQuery stepAssertCommonQuery);



}