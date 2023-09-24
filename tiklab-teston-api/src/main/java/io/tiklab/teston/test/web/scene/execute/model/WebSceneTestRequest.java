package io.tiklab.teston.test.web.scene.execute.model;

import com.alibaba.fastjson.JSONObject;
import io.tiklab.teston.test.web.scene.cases.model.WebSceneStep;
import io.tiklab.core.BaseModel;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;

import java.util.List;


@ApiModel
public class WebSceneTestRequest extends BaseModel {

    @ApiProperty(name="repositoryId",desc="repositoryId")
    private String repositoryId;

    @ApiProperty(name="webSceneId",desc="web场景id")
    private String webSceneId;

    @ApiProperty(name="agentConfigId",desc="agentConfigId")
    private String agentConfigId;

    @ApiProperty(name="webSceneStep",desc="web场景步骤")
    private WebSceneStep webSceneStep;

    @ApiProperty(name="exeType",desc="当前执行的类型，用于测试计划中")
    private String exeType;

    @ApiProperty(name="variableJson",desc="环境变量")
    private JSONObject variableJson;


    //web场景用例测试配置
//    private WebSceneTestConfig testConfig;


    @ApiProperty(name="webSceneStepList",desc="web场景中步骤测试数据")
    private List<WebSceneStep> webSceneStepList;

    public String getRepositoryId() {
        return repositoryId;
    }

    public void setRepositoryId(String repositoryId) {
        this.repositoryId = repositoryId;
    }

    private String webDriver;

    public String getWebSceneId() {
        return webSceneId;
    }

    public void setWebSceneId(String webSceneId) {
        this.webSceneId = webSceneId;
    }

    public WebSceneStep getWebSceneStep() {
        return webSceneStep;
    }

    public void setWebSceneStep(WebSceneStep webSceneStep) {
        this.webSceneStep = webSceneStep;
    }

    public String getAgentConfigId() {
        return agentConfigId;
    }

    public void setAgentConfigId(String agentConfigId) {
        this.agentConfigId = agentConfigId;
    }

    public List<WebSceneStep> getWebSceneStepList() {
        return webSceneStepList;
    }

    public void setWebSceneStepList(List<WebSceneStep> webSceneStepList) {
        this.webSceneStepList = webSceneStepList;
    }

    public String getWebDriver() {
        return webDriver;
    }

    public void setWebDriver(String webDriver) {
        this.webDriver = webDriver;
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
