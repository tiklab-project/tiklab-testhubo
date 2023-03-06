package net.tiklab.teston.test.app.scene.execute.model;

import net.tiklab.core.BaseModel;
import net.tiklab.postin.annotation.ApiModel;
import net.tiklab.postin.annotation.ApiProperty;
import net.tiklab.teston.test.app.scene.instance.model.AppSceneInstanceStep;
import net.tiklab.teston.test.app.scene.instance.model.AppSceneInstance;

import java.util.List;

/**
 * app场景测试下返回的数据封装 模型
 */
@ApiModel
public class AppSceneTestResponse extends BaseModel {
    @ApiProperty(name="webUnitResultList",desc="测试结果的list ")
    private List<AppSceneInstanceStep> appSceneInstanceStepList;

    @ApiProperty(name="webSceneInstance",desc="测试结果的list ")
    private AppSceneInstance appSceneInstance;

    @ApiProperty(name = "errMsg")
    private String errMsg;

    public List<AppSceneInstanceStep> getAppSceneInstanceStepList() {
        return appSceneInstanceStepList;
    }

    public void setAppSceneInstanceStepList(List<AppSceneInstanceStep> appSceneInstanceStepList) {
        this.appSceneInstanceStepList = appSceneInstanceStepList;
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
