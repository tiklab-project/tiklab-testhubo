package io.thoughtware.teston.test.apix.http.unit.execute.model;

import com.alibaba.fastjson.JSONObject;
import io.thoughtware.teston.test.apix.http.unit.cases.model.ApiUnitCase;
import io.thoughtware.teston.test.apix.http.unit.cases.model.ApiUnitCaseDataConstruction;
import io.thoughtware.core.BaseModel;
import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;

/**
 * 接口单元测试执行数据构造 模型
 */
@ApiModel
public class ApiUnitTestRequest extends BaseModel {

    @ApiProperty(name="apiUnitCase",desc="测试用例")
    private ApiUnitCase apiUnitCase;

    @ApiProperty(name="apiEnv",desc="测试基准地址")
    private String apiEnv;

    @ApiProperty(name="apiUnitCaseDataConstruction",desc="接口数据")
    private ApiUnitCaseDataConstruction apiUnitCaseDataConstruction;

    @ApiProperty(name="variableJson",desc="环境变量")
    private JSONObject variableJson;

    @ApiProperty(name="exeType",desc="当前执行的类型，用于测试计划中")
    private String exeType;

    public ApiUnitCase getApiUnitCase() {
        return apiUnitCase;
    }

    public void setApiUnitCase(ApiUnitCase apiUnitCase) {
        this.apiUnitCase = apiUnitCase;
    }

    public String getApiEnv() {
        return apiEnv;
    }

    public void setApiEnv(String apiEnv) {
        this.apiEnv = apiEnv;
    }

    public ApiUnitCaseDataConstruction getApiUnitCaseDataConstruction() {
        return apiUnitCaseDataConstruction;
    }

    public void setApiUnitCaseDataConstruction(ApiUnitCaseDataConstruction apiUnitCaseDataConstruction) {
        this.apiUnitCaseDataConstruction = apiUnitCaseDataConstruction;
    }

    public JSONObject getVariableJson() {
        return variableJson;
    }

    public void setVariableJson(JSONObject variableJson) {
        this.variableJson = variableJson;
    }

    public String getExeType() {
        return exeType;
    }

    public void setExeType(String exeType) {
        this.exeType = exeType;
    }
}
