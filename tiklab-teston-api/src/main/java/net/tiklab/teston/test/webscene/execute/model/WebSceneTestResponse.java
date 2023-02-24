package net.tiklab.teston.test.webscene.execute.model;

import net.tiklab.core.BaseModel;
import net.tiklab.postin.annotation.ApiModel;
import net.tiklab.postin.annotation.ApiProperty;
import net.tiklab.teston.test.webscene.instance.model.WebSceneInstance;
import net.tiklab.teston.test.webscene.instance.model.WebSceneInstanceStep;

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
