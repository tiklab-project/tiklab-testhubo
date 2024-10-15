package io.tiklab.testhubo.test.app.scene.execute.model;

import com.alibaba.fastjson.JSONObject;
import io.tiklab.testhubo.support.environment.model.AppEnv;
import io.tiklab.testhubo.test.common.stepcommon.model.StepCommon;
import io.tiklab.testhubo.test.app.scene.cases.model.AppSceneStep;
import io.tiklab.core.BaseModel;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;

import java.util.List;

/**
 * app场景测试数据封装 模型
 */
@ApiModel
public class AppSceneTestRequest extends BaseModel {

    @ApiProperty(name="appSceneId",desc="appSceneId")
    private String appSceneId;

    @ApiProperty(name = "appSceneStep", desc = "app场景用例")
    private AppSceneStep appSceneStep;

    @ApiProperty(name = "appEnv", desc = "app场景测试配置")
    private AppEnv appEnv;

    @ApiProperty(name = "appEnvId", desc = "app环境id")
    private String appEnvId;

    @ApiProperty(name = "stepCommonList", desc = "app场景测试数据")
    private List<StepCommon> stepCommonList;

    @ApiProperty(name="currentAgentId",desc="当前agent")
    private String currentAgentId;

    @ApiProperty(name="exeType",desc="当前执行的类型，用于测试计划中")
    private String exeType;

    @ApiProperty(name="variableJson",desc="环境变量")
    private JSONObject variableJson;


    public String getCurrentAgentId() {
        return currentAgentId;
    }

    public void setCurrentAgentId(String currentAgentId) {
        this.currentAgentId = currentAgentId;
    }

    public String getAppSceneId() {
        return appSceneId;
    }

    public void setAppSceneId(String appSceneId) {
        this.appSceneId = appSceneId;
    }

    public AppSceneStep getAppSceneStep() {
        return appSceneStep;
    }

    public void setAppSceneStep(AppSceneStep appSceneStep) {
        this.appSceneStep = appSceneStep;
    }

    public List<StepCommon> getStepCommonList() {
        return stepCommonList;
    }

    public void setStepCommonList(List<StepCommon> stepCommonList) {
        this.stepCommonList = stepCommonList;
    }

    public String getExeType() {
        return exeType;
    }

    public void setExeType(String exeType) {
        this.exeType = exeType;
    }

    public JSONObject getVariableJson() {
        return variableJson;
    }

    public void setVariableJson(JSONObject variableJson) {
        this.variableJson = variableJson;
    }

    public AppEnv getAppEnv() {
        return appEnv;
    }

    public void setAppEnv(AppEnv appEnv) {
        this.appEnv = appEnv;
    }

    public String getAppEnvId() {
        return appEnvId;
    }

    public void setAppEnvId(String appEnvId) {
        this.appEnvId = appEnvId;
    }
}
