package io.tiklab.testhubo.test.apix.http.scene.execute.model;

import com.alibaba.fastjson.JSONObject;
import io.tiklab.testhubo.test.common.stepcommon.model.StepCommon;
import io.tiklab.testhubo.test.apix.http.scene.cases.model.ApiSceneCase;
import io.tiklab.core.BaseModel;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;

import java.util.List;

/**
 * 接口场景发送请求数据 模型
 */
@ApiModel
public class ApiSceneTestRequest extends BaseModel {

    @ApiProperty(name="repositoryId",desc="仓库id")
    private String repositoryId;

    @ApiProperty(name="apiSceneCase",desc="测试用例id")
    private ApiSceneCase apiSceneCase;

    @ApiProperty(name="apiEnv",desc="测试基准地址")
    private String apiEnv;

    @ApiProperty(name="variableJson",desc="环境变量")
    private JSONObject variableJson;

    @ApiProperty(name="exeType",desc="当前执行的类型，用于测试计划中")
    private String exeType;

    private List<StepCommon> stepCommonList;

    public ApiSceneCase getApiSceneCase() {
        return apiSceneCase;
    }

    public void setApiSceneCase(ApiSceneCase apiSceneCase) {
        this.apiSceneCase = apiSceneCase;
    }

    public String getApiEnv() {
        return apiEnv;
    }

    public void setApiEnv(String apiEnv) {
        this.apiEnv = apiEnv;
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

    public String getRepositoryId() {
        return repositoryId;
    }

    public void setRepositoryId(String repositoryId) {
        this.repositoryId = repositoryId;
    }
}
