package io.tiklab.teston.test.app.scene.cases.entity;


import io.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * app场景下步骤 实体
 */
@Entity
@Table(name="teston_app_scene_step")
public class AppSceneStepEntity implements Serializable {

    @Id
     @GeneratorValue(length = 12)
    @Column(name = "id",length = 32)
    private String id;

    // 场景
    @Column(name = "app_scene_id",length = 32)
    private String appSceneId;

    // 名称
    @Column(name = "name",length = 256)
    private String name;

    // 定位器
    @Column(name = "location",length = 32)
    private String location;

    // 定位值
    @Column(name = "location_value",length = 256)
    private String locationValue;

    // 方法
    @Column(name = "action_type",length = 256)
    private String actionType;

    // 步骤动作
    @Column(name = "step_action",length = 64)
    private String stepAction;

    // 参数
    @Column(name = "parameter",length = 256)
    private String parameter;

    // 预期值
    @Column(name = "expected_result",length = 128)
    private String expectedResult;

    // 创建时间
    @Column(name = "create_time")
    private Timestamp createTime;

    // 排序
    @Column(name = "sort")
    private Integer sort;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppSceneId() {
        return appSceneId;
    }

    public void setAppSceneId(String appSceneId) {
        this.appSceneId = appSceneId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getExpectedResult() {
        return expectedResult;
    }

    public void setExpectedResult(String expectedResult) {
        this.expectedResult = expectedResult;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
