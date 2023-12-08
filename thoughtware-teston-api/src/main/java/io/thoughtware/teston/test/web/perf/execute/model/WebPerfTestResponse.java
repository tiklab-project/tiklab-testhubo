package io.thoughtware.teston.test.web.perf.execute.model;

import io.thoughtware.core.BaseModel;
import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;
import io.thoughtware.teston.test.web.perf.instance.model.WebPerfInstance;
import io.thoughtware.teston.test.web.scene.instance.model.WebSceneInstance;

import java.util.List;

/**
 * 用于性能测试的返回结果的结构封装 模型
 */
@ApiModel
public class WebPerfTestResponse extends BaseModel {

    @ApiProperty(name="webPerfInstance",desc="测试实例")
    private WebPerfInstance webPerfInstance;

    @ApiProperty(name="webSceneInstanceList",desc="测试场景实例")
    private List<WebSceneInstance> webSceneInstanceList;

    @ApiProperty(name="status",desc="结果类型")
    private Integer status;

    public WebPerfInstance getWebPerfInstance() {
        return webPerfInstance;
    }

    public void setWebPerfInstance(WebPerfInstance webPerfInstance) {
        this.webPerfInstance = webPerfInstance;
    }

    public List<WebSceneInstance> getWebSceneInstanceList() {
        return webSceneInstanceList;
    }

    public void setWebSceneInstanceList(List<WebSceneInstance> webSceneInstanceList) {
        this.webSceneInstanceList = webSceneInstanceList;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
