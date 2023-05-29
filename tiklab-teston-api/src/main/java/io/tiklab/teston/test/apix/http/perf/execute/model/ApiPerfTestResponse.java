package io.tiklab.teston.test.apix.http.perf.execute.model;

import io.tiklab.core.BaseModel;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.teston.test.apix.http.perf.instance.model.ApiPerfInstance;
import io.tiklab.teston.test.apix.http.scene.instance.model.ApiSceneInstance;

import java.util.List;

/**
 * 用于性能测试的返回结果的结构封装 模型
 */
@ApiModel
public class ApiPerfTestResponse extends BaseModel {

    @ApiProperty(name="apiPerfInstance",desc="api 压力测试实例")
    private ApiPerfInstance apiPerfInstance;

    @ApiProperty(name="apiSceneInstanceList",desc="api 场景测试实例列表")
    private List<ApiSceneInstance> apiSceneInstanceList;

    @ApiProperty(name="status",desc="状态： 0:未开始 1:进行中 2:结束")
    private Integer status;

    public ApiPerfInstance getApiPerfInstance() {
        return apiPerfInstance;
    }

    public void setApiPerfInstance(ApiPerfInstance apiPerfInstance) {
        this.apiPerfInstance = apiPerfInstance;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<ApiSceneInstance> getApiSceneInstanceList() {
        return apiSceneInstanceList;
    }

    public void setApiSceneInstanceList(List<ApiSceneInstance> apiSceneInstanceList) {
        this.apiSceneInstanceList = apiSceneInstanceList;
    }
}