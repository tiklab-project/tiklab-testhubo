package io.tiklab.teston.test.web.scene.cases.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.tiklab.beans.annotation.Mapper;
import io.tiklab.core.BaseModel;
import io.tiklab.join.annotation.Join;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.teston.test.common.stepassert.model.StepAssertCommon;
import io.tiklab.teston.test.common.stepcommon.model.StepCommon;

import java.sql.Timestamp;
import java.util.List;

/**
 * web场景步骤 模型
 */
@ApiModel
@Join
@Mapper
public class WebSceneStep extends BaseModel {

    @ApiProperty(name="id",desc="id")
    private String id;

    @ApiProperty(name="webSceneId",desc="场景")
    private String webSceneId;

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

    @ApiProperty(name="preScript",desc="前置脚本")
    private String preScript;

    @ApiProperty(name="afterScript",desc="后置脚本")
    private String afterScript;

    @ApiProperty(name="stepAssertCommon",desc="断言")
    private List<StepAssertCommon> stepAssertCommonList;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWebSceneId() {
        return webSceneId;
    }

    public void setWebSceneId(String webSceneId) {
        this.webSceneId = webSceneId;
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
