package io.thoughtware.teston.test.app.scene.execute.model;

import io.thoughtware.teston.test.app.scene.instance.model.AppSceneInstance;
import io.thoughtware.core.BaseModel;
import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;
import io.thoughtware.teston.test.common.stepcommon.model.StepCommonInstance;

import java.util.List;

/**
 * app场景测试下返回的数据封装 模型
 */
@ApiModel
public class AppSceneTestResponse extends BaseModel {
    @ApiProperty(name="stepCommonInstanceList",desc="测试结果的list ")
    private List<StepCommonInstance> stepCommonInstanceList;

    @ApiProperty(name="webSceneInstance",desc="测试结果的list ")
    private AppSceneInstance appSceneInstance;

    @ApiProperty(name = "status")
    private Integer status;

    public List<StepCommonInstance> getStepCommonInstanceList() {
        return stepCommonInstanceList;
    }

    public void setStepCommonInstanceList(List<StepCommonInstance> stepCommonInstanceList) {
        this.stepCommonInstanceList = stepCommonInstanceList;
    }

    public AppSceneInstance getAppSceneInstance() {
        return appSceneInstance;
    }

    public void setAppSceneInstance(AppSceneInstance appSceneInstance) {
        this.appSceneInstance = appSceneInstance;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
