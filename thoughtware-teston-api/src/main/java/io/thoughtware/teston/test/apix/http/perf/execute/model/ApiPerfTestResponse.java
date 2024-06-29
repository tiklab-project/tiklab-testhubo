package io.thoughtware.teston.test.apix.http.perf.execute.model;

import io.thoughtware.teston.test.apix.http.perf.instance.model.ApiPerfInstance;
import io.thoughtware.teston.test.apix.http.perf.instance.model.ApiPerfStepInstance;
import io.thoughtware.teston.test.apix.http.perf.instance.model.ApiPerfStepTestInstance;
import io.thoughtware.core.BaseModel;
import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;
import io.thoughtware.teston.test.apix.http.perf.instance.model.ApiPerfStepUnitCalc;

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

    @ApiProperty(name="apiPerfStepInstance",desc="所有接口的数据总和")
    private ApiPerfStepInstance apiPerfStepInstance;
    @ApiProperty(name="apiPerfStepInstance",desc="接口性能下的所有的接口详细数据")
    private List<ApiPerfStepUnitCalc> apiPerfStepUnitCalcList;

    public ApiPerfStepInstance getApiPerfStepInstance() {
        return apiPerfStepInstance;
    }

    public void setApiPerfStepInstance(ApiPerfStepInstance apiPerfStepInstance) {
        this.apiPerfStepInstance = apiPerfStepInstance;
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

    public List<ApiPerfStepUnitCalc> getApiPerfStepUnitCalcList() {
        return apiPerfStepUnitCalcList;
    }

    public void setApiPerfStepUnitCalcList(List<ApiPerfStepUnitCalc> apiPerfStepUnitCalcList) {
        this.apiPerfStepUnitCalcList = apiPerfStepUnitCalcList;
    }
}
