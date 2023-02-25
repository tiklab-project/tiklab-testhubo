package net.tiklab.testonapp.scene.cases.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import net.tiklab.beans.annotation.Mapper;
import net.tiklab.core.BaseModel;
import net.tiklab.join.annotation.Join;
import net.tiklab.postin.annotation.ApiModel;
import net.tiklab.postin.annotation.ApiProperty;

import java.sql.Timestamp;


@ApiModel
@Mapper(targetAlias = "AppSceneStepEntity")
@Join
public class AppSceneStep extends BaseModel {

    @ApiProperty(name="id",desc="id")
    private String id;


    @ApiProperty(name="appSceneId",desc="场景")
    private String appSceneId;

    @ApiProperty(name="name",desc="名称")
    private String name;

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

    @ApiProperty(name="expectedResult",desc="预期值")
    private String expectedResult;

    @ApiProperty(name="createTime",desc="createTime")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Timestamp createTime;

    @ApiProperty(name="sort",desc="排序")
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

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
