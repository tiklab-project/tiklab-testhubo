package net.tiklab.teston.apptest.perftest.model;

import net.tiklab.core.BaseModel;
import net.tiklab.postin.annotation.ApiModel;
import net.tiklab.postin.annotation.ApiProperty;
import net.tiklab.teston.apptest.scenetest.model.AppSceneTestRequest;

import java.util.List;

@ApiModel
public class AppPerfTestData extends BaseModel {
    @ApiProperty(name="appSceneTestRequestList",desc="压力测试下绑定的场景列表请求参数")
    private List<AppSceneTestRequest> appSceneTestRequestList;


    public List<AppSceneTestRequest> getAppSceneTestRequestList() {
        return appSceneTestRequestList;
    }

    public void setAppSceneTestRequestList(List<AppSceneTestRequest> appSceneTestRequestList) {
        this.appSceneTestRequestList = appSceneTestRequestList;
    }
}
