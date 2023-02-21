package net.tiklab.teston.apitest.http.scenetest.model;

import net.tiklab.core.BaseModel;
import net.tiklab.postin.annotation.ApiModel;
import net.tiklab.teston.apitest.http.unittest.model.ApiUnitInstance;

import java.util.List;

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
