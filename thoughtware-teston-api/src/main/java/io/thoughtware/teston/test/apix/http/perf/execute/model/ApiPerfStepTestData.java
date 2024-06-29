package io.thoughtware.teston.test.apix.http.perf.execute.model;

import io.thoughtware.core.BaseModel;
import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;
import io.thoughtware.teston.test.apix.http.perf.cases.model.ApiPerfStep;
import io.thoughtware.teston.test.apix.http.scene.execute.model.ApiSceneTestRequest;
import io.thoughtware.teston.test.apix.http.unit.execute.model.ApiUnitTestRequest;

/**
 *接口性能测试请求参数 模型
 */
@ApiModel
public class ApiPerfStepTestData extends BaseModel {

    private ApiPerfStep apiPerfStep;

    @ApiProperty(name="apiSceneTestRequestList",desc="api场景测试请求")
    private ApiSceneTestRequest apiSceneTestRequest;

    @ApiProperty(name="apiUnitTestRequest",desc="api单元测试")
    private ApiUnitTestRequest apiUnitTestRequest;

    public ApiPerfStep getApiPerfStep() {
        return apiPerfStep;
    }

    public void setApiPerfStep(ApiPerfStep apiPerfStep) {
        this.apiPerfStep = apiPerfStep;
    }

    public ApiSceneTestRequest getApiSceneTestRequest() {
        return apiSceneTestRequest;
    }

    public void setApiSceneTestRequest(ApiSceneTestRequest apiSceneTestRequest) {
        this.apiSceneTestRequest = apiSceneTestRequest;
    }

    public ApiUnitTestRequest getApiUnitTestRequest() {
        return apiUnitTestRequest;
    }

    public void setApiUnitTestRequest(ApiUnitTestRequest apiUnitTestRequest) {
        this.apiUnitTestRequest = apiUnitTestRequest;
    }
}
