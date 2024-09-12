package io.thoughtware.testhubo.test.apix.http.perf.instance.model;

import io.thoughtware.core.BaseModel;
import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.testhubo.test.apix.http.perf.cases.model.ApiPerfStep;
import io.thoughtware.testhubo.test.apix.http.scene.execute.model.ApiSceneTestResponse;
import io.thoughtware.testhubo.test.apix.http.unit.instance.model.ApiUnitInstance;
import io.thoughtware.toolkit.beans.annotation.Mapper;

import java.util.List;

/**
 * 正在测试的数据 模型
 */
@ApiModel
@Mapper
public class ApiPerfStepTestInstance extends BaseModel {

    private ApiPerfStep apiPerfStep;
    private List<ApiUnitInstance> apiUnitInstanceList;
    private List<ApiSceneTestResponse> apiSceneTestResponseList;


    public ApiPerfStep getApiPerfStep() {
        return apiPerfStep;
    }

    public void setApiPerfStep(ApiPerfStep apiPerfStep) {
        this.apiPerfStep = apiPerfStep;
    }

    public List<ApiUnitInstance> getApiUnitInstanceList() {
        return apiUnitInstanceList;
    }

    public void setApiUnitInstanceList(List<ApiUnitInstance> apiUnitInstanceList) {
        this.apiUnitInstanceList = apiUnitInstanceList;
    }

    public List<ApiSceneTestResponse> getApiSceneTestResponseList() {
        return apiSceneTestResponseList;
    }

    public void setApiSceneTestResponseList(List<ApiSceneTestResponse> apiSceneTestResponseList) {
        this.apiSceneTestResponseList = apiSceneTestResponseList;
    }
}
