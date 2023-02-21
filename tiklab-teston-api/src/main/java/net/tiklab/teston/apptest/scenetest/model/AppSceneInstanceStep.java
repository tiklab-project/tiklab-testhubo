package net.tiklab.teston.apptest.scenetest.model;

import net.tiklab.postin.annotation.ApiModel;
import net.tiklab.postin.annotation.ApiProperty;
import net.tiklab.beans.annotation.Mapper;
import net.tiklab.core.BaseModel;


@ApiModel
@Mapper(targetAlias = "AppSceneInstanceStepEntity")
public class AppSceneInstanceStep extends BaseModel {

    @ApiProperty(name="id",desc="id")
    private java.lang.String id;

    @ApiProperty(name="appSceneInstanceId",desc="appSceneInstanceId")
    private java.lang.String appSceneInstanceId;

    @ApiProperty(name="location",desc="location")
    private java.lang.String location;

    @ApiProperty(name="locationValue",desc="locationValue")
    private java.lang.String locationValue;

    @ApiProperty(name="actionType",desc="actionType")
    private java.lang.String actionType;

    @ApiProperty(name="stepAction",desc="stepAction")
    private java.lang.String stepAction;

    @ApiProperty(name="parameter",desc="parameter")
    private java.lang.String parameter;

    @ApiProperty(name="result",desc="result")
    private java.lang.Integer result;

    public java.lang.String getId() {
        return id;
    }

    public void setId(java.lang.String id) {
        this.id = id;
    }
    public java.lang.String getAppSceneInstanceId() {
        return appSceneInstanceId;
    }

    public void setAppSceneInstanceId(java.lang.String appSceneInstanceId) {
        this.appSceneInstanceId = appSceneInstanceId;
    }
    public java.lang.String getLocation() {
        return location;
    }

    public void setLocation(java.lang.String location) {
        this.location = location;
    }
    public java.lang.String getLocationValue() {
        return locationValue;
    }

    public void setLocationValue(java.lang.String locationValue) {
        this.locationValue = locationValue;
    }
    public java.lang.String getActionType() {
        return actionType;
    }

    public void setActionType(java.lang.String actionType) {
        this.actionType = actionType;
    }
    public java.lang.String getStepAction() {
        return stepAction;
    }

    public void setStepAction(java.lang.String stepAction) {
        this.stepAction = stepAction;
    }
    public java.lang.String getParameter() {
        return parameter;
    }

    public void setParameter(java.lang.String parameter) {
        this.parameter = parameter;
    }
    public java.lang.Integer getResult() {
        return result;
    }

    public void setResult(java.lang.Integer result) {
        this.result = result;
    }
}
