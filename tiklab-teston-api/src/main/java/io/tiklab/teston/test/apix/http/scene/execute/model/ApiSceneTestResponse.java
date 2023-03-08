package io.tiklab.teston.test.apix.http.scene.execute.model;

import io.tiklab.teston.test.apix.http.unit.instance.model.ApiUnitInstance;
import io.tiklab.core.BaseModel;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.teston.test.apix.http.scene.instance.model.ApiSceneInstance;

import java.util.List;

/**
 * 接口场景测试中返回的数据封装 模型
 */
@ApiModel
public class ApiSceneTestResponse extends BaseModel {

    private ApiSceneInstance apiSceneInstance;

    private List<ApiUnitInstance> apiUnitInstanceList;

    public ApiSceneInstance getApiSceneInstance() {
        return apiSceneInstance;
    }

    public void setApiSceneInstance(ApiSceneInstance apiSceneInstance) {
        this.apiSceneInstance = apiSceneInstance;
    }

    public List<ApiUnitInstance> getApiUnitInstanceList() {
        return apiUnitInstanceList;
    }

    public void setApiUnitInstanceList(List<ApiUnitInstance> apiUnitInstanceList) {
        this.apiUnitInstanceList = apiUnitInstanceList;
    }
}
