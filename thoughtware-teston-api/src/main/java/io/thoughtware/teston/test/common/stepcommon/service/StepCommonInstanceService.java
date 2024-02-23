package io.thoughtware.teston.test.common.stepcommon.service;

import io.thoughtware.teston.test.common.stepcommon.model.StepCommonInstance;
import io.thoughtware.toolkit.join.annotation.FindOne;
import io.thoughtware.toolkit.join.annotation.JoinProvider;
import io.thoughtware.teston.test.common.stepcommon.model.StepCommonInstanceQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 步骤公共历史 服务接口
*/
@JoinProvider(model = StepCommonInstance.class)
public interface StepCommonInstanceService {

    /**
    * 创建步骤公共历史
    * @param stepCommonInstance
    * @return
    */
    String createStepCommonInstance(@NotNull @Valid StepCommonInstance stepCommonInstance);

    /**
    * 更新步骤公共历史
    * @param stepCommonInstance
    */
    void updateStepCommonInstance(@NotNull @Valid StepCommonInstance stepCommonInstance);

    /**
    * 删除步骤公共历史
    * @param id
    */
    void deleteStepCommonInstance(@NotNull String id,String caseType);

    void deleteAllStepCommonInstance( String instanceId);

    @FindOne
    StepCommonInstance findOne(@NotNull String id);

    /**
    * 根据id查找步骤公共历史
    * @param id
    * @return
    */
    StepCommonInstance findStepCommonInstance(@NotNull String id);


    /**
    * 根据查询参数查询步骤公共历史列表
    * @param stepCommonInstanceQuery
    * @return
    */
    List<StepCommonInstance> findStepCommonInstanceList(StepCommonInstanceQuery stepCommonInstanceQuery);


}