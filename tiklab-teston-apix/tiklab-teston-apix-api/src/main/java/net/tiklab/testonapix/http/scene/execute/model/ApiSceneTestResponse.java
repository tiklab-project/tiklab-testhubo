package net.tiklab.testonapix.http.scene.execute.model;

import net.tiklab.core.BaseModel;
import net.tiklab.postin.annotation.ApiModel;
import net.tiklab.testonapix.http.scene.instance.model.ApiSceneInstance;
import net.tiklab.testonapix.http.unit.instance.model.ApiUnitInstance;

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
