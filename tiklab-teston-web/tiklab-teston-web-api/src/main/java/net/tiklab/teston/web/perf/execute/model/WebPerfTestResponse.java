package net.tiklab.teston.web.perf.execute.model;

import net.tiklab.core.BaseModel;
import net.tiklab.postin.annotation.ApiModel;
import net.tiklab.postin.annotation.ApiProperty;
import net.tiklab.teston.web.perf.instance.model.WebPerfInstance;
import net.tiklab.teston.web.scene.instance.model.WebSceneInstance;

import java.util.List;

/**
 * 这个只用性能测试的返回结果的结构封装
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
