package io.tiklab.teston.test.app.scene.execute.model;

import com.alibaba.fastjson.JSONObject;
import io.tiklab.teston.test.app.scene.cases.model.AppSceneStep;
import io.tiklab.core.BaseModel;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.teston.test.common.stepcommon.model.StepCommon;

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

    @ApiProperty(name = "appTestConfig", desc = "app场景测试配置")
    private AppTestConfig appTestConfig;

    @ApiProperty(name = "appTestConfig", desc = "app场景测试配置")
    private List<AppTestConfig> appTestConfigList;

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

    public List<AppTestConfig> getAppTestConfigList() {
        return appTestConfigList;
    }

    public void setAppTestConfigList(List<AppTestConfig> appTestConfigList) {
        this.appTestConfigList = appTestConfigList;
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

    public AppTestConfig getAppTestConfig() {
        return appTestConfig;
    }

    public void setAppTestConfig(AppTestConfig appTestConfig) {
        this.appTestConfig = appTestConfig;
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
}
