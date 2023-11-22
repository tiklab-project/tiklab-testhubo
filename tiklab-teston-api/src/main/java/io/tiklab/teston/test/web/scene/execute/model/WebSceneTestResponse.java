package io.tiklab.teston.test.web.scene.execute.model;

import io.tiklab.core.BaseModel;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.teston.test.common.stepcommon.model.StepCommonInstance;
import io.tiklab.teston.test.web.scene.instance.model.WebSceneInstance;

import java.util.List;

@ApiModel
public class WebSceneTestResponse extends BaseModel {

    @ApiProperty(name="stepCommonInstanceList",desc="测试结果的list ")
    private List<StepCommonInstance> stepCommonInstanceList;

    @ApiProperty(name="webSceneInstance",desc="测试结果的list ")
    private WebSceneInstance webSceneInstance;

    public List<StepCommonInstance> getStepCommonInstanceList() {
        return stepCommonInstanceList;
    }

    public void setStepCommonInstanceList(List<StepCommonInstance> stepCommonInstanceList) {
        this.stepCommonInstanceList = stepCommonInstanceList;
    }

    public WebSceneInstance getWebSceneInstance() {
        return webSceneInstance;
    }

    public void setWebSceneInstance(WebSceneInstance webSceneInstance) {
        this.webSceneInstance = webSceneInstance;
    }
}
