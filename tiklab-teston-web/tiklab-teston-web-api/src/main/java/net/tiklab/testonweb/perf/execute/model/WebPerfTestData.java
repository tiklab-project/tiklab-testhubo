package net.tiklab.testonweb.perf.execute.model;

import net.tiklab.core.BaseModel;
import net.tiklab.postin.annotation.ApiModel;
import net.tiklab.postin.annotation.ApiProperty;
import net.tiklab.testonweb.scene.execute.model.WebSceneTestRequest;

import java.util.List;

@ApiModel
public class WebPerfTestData extends BaseModel {

    @ApiProperty(name="webSceneTestRequestList",desc="压力测试下绑定的场景列表请求参数")
    private List<WebSceneTestRequest> webSceneTestRequestList;

    public List<WebSceneTestRequest> getWebSceneTestRequestList() {
        return webSceneTestRequestList;
    }

    public void setWebSceneTestRequestList(List<WebSceneTestRequest> webSceneTestRequestList) {
        this.webSceneTestRequestList = webSceneTestRequestList;
    }
}
