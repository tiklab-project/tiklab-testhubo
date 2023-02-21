package net.tiklab.teston.apitest.http.perftest.model;

import net.tiklab.core.BaseModel;
import net.tiklab.postin.annotation.ApiModel;
import net.tiklab.postin.annotation.ApiProperty;
import net.tiklab.teston.apitest.http.scenetest.model.ApiSceneInstance;

import java.util.List;

/**
 * 这个只用性能测试的返回结果的结构封装
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
