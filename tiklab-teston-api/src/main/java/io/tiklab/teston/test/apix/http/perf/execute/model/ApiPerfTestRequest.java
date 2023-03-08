package io.tiklab.teston.test.apix.http.perf.execute.model;

import io.tiklab.teston.test.apix.http.perf.cases.model.ApiPerfCase;
import io.tiklab.core.BaseModel;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.teston.test.apix.http.scene.execute.model.ApiSceneTestRequest;

import java.util.List;

/**
 *接口性能测试请求参数 模型
 */
@ApiModel
public class ApiPerfTestRequest extends BaseModel {

    @ApiProperty(name="apiPerfCase",desc="性能测试用例")
    private ApiPerfCase apiPerfCase;

    @ApiProperty(name="apiEnv",desc="测试基准地址")
    private String apiEnv;

    @ApiProperty(name="apiSceneTestRequestList",desc="api 场景测试请求列表")
    private List<ApiSceneTestRequest> apiSceneTestRequestList;

    @ApiProperty(name="exeNum",desc="执行次数")
    private Integer exeNum;

    @ApiProperty(name="exeType",desc="当前执行的类型，用于测试计划中")
    private String exeType;

    public ApiPerfCase getApiPerfCase() {
        return apiPerfCase;
    }

    public void setApiPerfCase(ApiPerfCase apiPerfCase) {
        this.apiPerfCase = apiPerfCase;
    }


    public String getApiEnv() {
        return apiEnv;
    }

    public void setApiEnv(String apiEnv) {
        this.apiEnv = apiEnv;
    }

    public List<ApiSceneTestRequest> getApiSceneTestRequestList() {
        return apiSceneTestRequestList;
    }

    public void setApiSceneTestRequestList(List<ApiSceneTestRequest> apiSceneTestRequestList) {
        this.apiSceneTestRequestList = apiSceneTestRequestList;
    }

    public Integer getExeNum() {
        return exeNum;
    }

    public void setExeNum(Integer exeNum) {
        this.exeNum = exeNum;
    }

    public String getExeType() {
        return exeType;
    }

    public void setExeType(String exeType) {
        this.exeType = exeType;
    }
}
