package io.tiklab.teston.test.web.scene.execute.model;

import io.tiklab.core.BaseModel;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.teston.test.web.scene.instance.model.WebSceneInstance;
import io.tiklab.teston.test.web.scene.instance.model.WebSceneInstanceStep;

import java.util.List;

@ApiModel
public class WebSceneTestResponse extends BaseModel {

    @ApiProperty(name="webUnitResultList",desc="测试结果的list ")
    private List<WebSceneInstanceStep> webUnitResultList;

    @ApiProperty(name="webSceneInstance",desc="测试结果的list ")
    private WebSceneInstance webSceneInstance;

    public List<WebSceneInstanceStep> getWebUnitResultList() {
        return webUnitResultList;
    }

    public void setWebUnitResultList(List<WebSceneInstanceStep> webUnitResultList) {
        this.webUnitResultList = webUnitResultList;
    }

    public WebSceneInstance getWebSceneInstance() {
        return webSceneInstance;
    }

    public void setWebSceneInstance(WebSceneInstance webSceneInstance) {
        this.webSceneInstance = webSceneInstance;
    }
}
