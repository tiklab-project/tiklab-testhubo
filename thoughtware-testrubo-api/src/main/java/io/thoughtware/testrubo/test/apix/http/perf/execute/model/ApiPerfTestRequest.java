package io.thoughtware.testrubo.test.apix.http.perf.execute.model;

import io.thoughtware.core.BaseModel;
import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;

import java.util.List;

/**
 *接口性能测试请求参数 模型
 */
@ApiModel
public class ApiPerfTestRequest extends BaseModel {

    @ApiProperty(name="apiPerfId",desc="性能测试用例")
    private String apiPerfId;

    @ApiProperty(name="apiEnv",desc="测试基准地址")
    private String apiEnv;

    @ApiProperty(name="agentId",desc="选择的agent")
    private String agentId;

    @ApiProperty(name="apiSceneTestRequestList",desc="api 场景测试请求列表")
    private List<ApiPerfStepTestData> apiPerfStepTestData;

    @ApiProperty(name="exeNum",desc="执行次数")
    private Integer exeNum;

    public String getApiPerfId() {
        return apiPerfId;
    }

    public void setApiPerfId(String apiPerfId) {
        this.apiPerfId = apiPerfId;
    }

    public String getApiEnv() {
        return apiEnv;
    }

    public void setApiEnv(String apiEnv) {
        this.apiEnv = apiEnv;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public List<ApiPerfStepTestData> getApiPerfStepTestData() {
        return apiPerfStepTestData;
    }

    public void setApiPerfStepTestData(List<ApiPerfStepTestData> apiPerfStepTestData) {
        this.apiPerfStepTestData = apiPerfStepTestData;
    }

    public Integer getExeNum() {
        return exeNum;
    }

    public void setExeNum(Integer exeNum) {
        this.exeNum = exeNum;
    }


}
