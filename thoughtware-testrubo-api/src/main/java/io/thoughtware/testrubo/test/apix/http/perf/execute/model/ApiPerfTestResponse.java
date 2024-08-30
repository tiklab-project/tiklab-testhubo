package io.thoughtware.testrubo.test.apix.http.perf.execute.model;

import io.thoughtware.testrubo.test.apix.http.perf.instance.model.ApiPerfInstance;
import io.thoughtware.testrubo.test.apix.http.perf.instance.model.ApiPerfStepInstance;
import io.thoughtware.core.BaseModel;
import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;

import java.util.List;

/**
 * 用于性能测试的返回结果的结构封装 模型
 */
@ApiModel
public class ApiPerfTestResponse extends BaseModel {

    @ApiProperty(name="apiPerfInstance",desc="api 压力测试实例")
    private ApiPerfInstance apiPerfInstance;

    @ApiProperty(name="apiSceneInstanceList",desc="api 场景测试实例列表")
    private List<ApiPerfStepInstance> apiPerfStepInstanceList;


    // 添加设置初始状态的方法
    public void setInitialState() {
        this.apiPerfInstance = new ApiPerfInstance();
    }


    public ApiPerfInstance getApiPerfInstance() {
        return apiPerfInstance;
    }

    public void setApiPerfInstance(ApiPerfInstance apiPerfInstance) {
        this.apiPerfInstance = apiPerfInstance;
    }

    public List<ApiPerfStepInstance> getApiPerfStepInstanceList() {
        return apiPerfStepInstanceList;
    }

    public void setApiPerfStepInstanceList(List<ApiPerfStepInstance> apiPerfStepInstanceList) {
        this.apiPerfStepInstanceList = apiPerfStepInstanceList;
    }


}
