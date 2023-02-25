package net.tiklab.testonapix.http.scene.execute.model;

import net.tiklab.core.BaseModel;
import net.tiklab.postin.annotation.ApiModel;
import net.tiklab.postin.annotation.ApiProperty;
import net.tiklab.testonapix.http.scene.cases.model.ApiSceneCase;
import net.tiklab.testonapix.http.unit.execute.model.ApiUnitTestRequest;

import java.util.List;

@ApiModel
public class ApiSceneTestRequest extends BaseModel {

    @ApiProperty(name="apiSceneCase",desc="测试用例id")
    private ApiSceneCase apiSceneCase;

    @ApiProperty(name="apiEnv",desc="测试基准地址")
    private String apiEnv;

    @ApiProperty(name="exeType",desc="当前执行的类型，用于测试计划中")
    private String exeType;

    private List<ApiUnitTestRequest> apiUnitTestRequestList;

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

    public List<ApiUnitTestRequest> getApiUnitTestRequestList() {
        return apiUnitTestRequestList;
    }

    public void setApiUnitTestRequestList(List<ApiUnitTestRequest> apiUnitTestRequestList) {
        this.apiUnitTestRequestList = apiUnitTestRequestList;
    }

    public String getExeType() {
        return exeType;
    }

    public void setExeType(String exeType) {
        this.exeType = exeType;
    }
}
