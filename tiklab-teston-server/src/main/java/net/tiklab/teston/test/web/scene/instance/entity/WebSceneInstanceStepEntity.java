package net.tiklab.teston.test.web.scene.instance.entity;

import net.tiklab.dal.jpa.annotation.*;

@Entity
@Table(name="teston_web_scene_instance_step")
public class WebSceneInstanceStepEntity {

    @Id
    @GeneratorValue
    @Column(name = "id",length = 32)
    private String id;

    @Column(name = "web_scene_instance_id")
    private String webSceneInstanceId;

    @Column(name = "location")
    private String location;

    @Column(name = "location_value")
    private String locationValue;

    @Column(name = "action_type")
    private String actionType;

    @Column(name = "step_action")
    private String stepAction;

    @Column(name = "parameter")
    private String parameter;

    @Column(name = "result")
    private Integer result;

    @Column(name = "duration")
    private double duration;

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

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }
}
