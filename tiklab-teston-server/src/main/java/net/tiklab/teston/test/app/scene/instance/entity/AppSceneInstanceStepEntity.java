package net.tiklab.teston.test.app.scene.instance.entity;

import net.tiklab.dal.jpa.annotation.*;

/**
 * app场景步骤实例 实体
 */
@Entity
@Table(name="teston_app_scene_instance_step")
public class AppSceneInstanceStepEntity {

    @Id
    @GeneratorValue
    @Column(name = "id",length = 32)
    private String id;

    // 所属场景实例
    @Column(name = "app_scene_instance_id")
    private String appSceneInstanceId;

    // 定位器
    @Column(name = "location")
    private String location;

    // 定位值
    @Column(name = "location_value")
    private String locationValue;

    // 方法
    @Column(name = "action_type")
    private String actionType;

    // 步骤动作
    @Column(name = "step_action")
    private String stepAction;

    // 参数
    @Column(name = "parameter")
    private String parameter;

    // 结果
    @Column(name = "result")
    private Integer result;

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

}
