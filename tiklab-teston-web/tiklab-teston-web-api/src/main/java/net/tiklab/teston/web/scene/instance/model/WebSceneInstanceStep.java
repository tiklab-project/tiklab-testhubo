package net.tiklab.teston.web.scene.instance.model;

import net.tiklab.beans.annotation.Mapper;
import net.tiklab.core.BaseModel;
import net.tiklab.postin.annotation.ApiModel;
import net.tiklab.postin.annotation.ApiProperty;

/**
 * web场景下步骤 模型
 */
@ApiModel
@Mapper(targetAlias = "WebSceneInstanceStepEntity")
public class WebSceneInstanceStep extends BaseModel {

    @ApiProperty(name="id",desc="id")
    private String id;

    @ApiProperty(name="webSceneInstanceId",desc="所属场景实例")
    private String webSceneInstanceId;

    @ApiProperty(name="location",desc="定位器")
    private String location;

    @ApiProperty(name="locationValue",desc="定位值")
    private String locationValue;

    @ApiProperty(name="actionType",desc="方法")
    private String actionType;

    @ApiProperty(name="stepAction",desc="步骤动作")
    private String stepAction;

    @ApiProperty(name="parameter",desc="参数")
    private String parameter;

    @ApiProperty(name="result",desc="结果")
    private Integer result;

    @ApiProperty(name="duration",desc="耗时")
    private Double duration;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWebSceneInstanceId() {
        return webSceneInstanceId;
    }

    public void setWebSceneInstanceId(String webSceneInstanceId) {
        this.webSceneInstanceId = webSceneInstanceId;
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

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }
}
