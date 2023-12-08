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

    @ApiProperty(name = "errMsg")
    private String errMsg;

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

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
