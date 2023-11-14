package io.tiklab.teston.test.app.scene.instance.model;

import io.tiklab.beans.annotation.Mapper;
import io.tiklab.core.BaseModel;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.teston.test.common.model.StepAssertCommon;

import java.util.List;

/**
 * app场景步骤实例 模型
 */
@ApiModel
@Mapper
public class AppSceneInstanceStep extends BaseModel {

    @ApiProperty(name="id",desc="id")
    private String id;

    @ApiProperty(name="appSceneInstanceId",desc="app场景实例id")
    private String appSceneInstanceId;

    @ApiProperty(name="location",desc="location")
    private String location;

    @ApiProperty(name="locationValue",desc="locationValue")
    private String locationValue;

    @ApiProperty(name="actionType",desc="actionType")
    private String actionType;

    @ApiProperty(name="stepAction",desc="stepAction")
    private String stepAction;

    @ApiProperty(name="parameter",desc="parameter")
    private String parameter;

    @ApiProperty(name="result",desc="结果")
    private Integer result;

    private String preScript;
    private String afterScript;
    private List<StepAssertCommon> stepAssertCommonList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getAppSceneInstanceId() {
        return appSceneInstanceId;
    }

    public void setAppSceneInstanceId(String appSceneInstanceId) {
        this.appSceneInstanceId = appSceneInstanceId;
    }
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    public String getLocationValue() {
        return locationValue;
    }

    public void setLocationValue(String locationValue) {
        this.locationValue = locationValue;
    }
    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }
    public String getStepAction() {
        return stepAction;
    }

    public void setStepAction(String stepAction) {
        this.stepAction = stepAction;
    }
    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }
    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String getPreScript() {
        return preScript;
    }

    public void setPreScript(String preScript) {
        this.preScript = preScript;
    }

    public String getAfterScript() {
        return afterScript;
    }

    public void setAfterScript(String afterScript) {
        this.afterScript = afterScript;
    }

    public List<StepAssertCommon> getStepAssertCommonList() {
        return stepAssertCommonList;
    }

    public void setStepAssertCommonList(List<StepAssertCommon> stepAssertCommonList) {
        this.stepAssertCommonList = stepAssertCommonList;
    }
}
