package io.thoughtware.teston.test.web.scene.execute.model;

import io.thoughtware.teston.test.common.stepcommon.model.StepCommonInstance;
import io.thoughtware.teston.test.web.scene.instance.model.WebSceneInstance;
import io.thoughtware.core.BaseModel;
import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;

import java.util.List;

@ApiModel
public class WebSceneTestResponse extends BaseModel {

    @ApiProperty(name="stepCommonInstanceList",desc="测试结果的list ")
    private List<StepCommonInstance> stepCommonInstanceList;

    @ApiProperty(name="webSceneInstance",desc="测试结果的list ")
    private WebSceneInstance webSceneInstance;

    private Integer status;

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
