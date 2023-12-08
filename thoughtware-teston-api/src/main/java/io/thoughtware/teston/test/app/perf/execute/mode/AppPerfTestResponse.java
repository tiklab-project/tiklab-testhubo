package io.thoughtware.teston.test.app.perf.execute.mode;

import io.thoughtware.teston.test.app.perf.instance.mode.AppPerfInstance;
import io.thoughtware.teston.test.app.scene.instance.model.AppSceneInstance;
import io.thoughtware.core.BaseModel;
import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;

import java.util.List;

/**
 * 用于性能测试的返回结果的结构封装 模型
 */
@ApiModel
public class AppPerfTestResponse extends BaseModel {

    @ApiProperty(name="appPerfInstance",desc="测试实例")
    private AppPerfInstance appPerfInstance;

    @ApiProperty(name="appSceneInstanceList",desc="测试场景实例")
    private List<AppSceneInstance> appSceneInstanceList;

    @ApiProperty(name="resultType",desc="结果类型")
    private Integer status;


    public AppPerfInstance getAppPerfInstance() {
        return appPerfInstance;
    }

    public void setAppPerfInstance(AppPerfInstance appPerfInstance) {
        this.appPerfInstance = appPerfInstance;
    }

    public List<AppSceneInstance> getAppSceneInstanceList() {
        return appSceneInstanceList;
    }

    public void setAppSceneInstanceList(List<AppSceneInstance> appSceneInstanceList) {
        this.appSceneInstanceList = appSceneInstanceList;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
