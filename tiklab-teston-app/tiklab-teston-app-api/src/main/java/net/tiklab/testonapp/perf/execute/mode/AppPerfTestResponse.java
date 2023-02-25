package net.tiklab.testonapp.perf.execute.mode;

import net.tiklab.core.BaseModel;
import net.tiklab.postin.annotation.ApiModel;
import net.tiklab.postin.annotation.ApiProperty;
import net.tiklab.testonapp.perf.instance.mode.AppPerfInstance;
import net.tiklab.testonapp.scene.instance.model.AppSceneInstance;

import java.util.List;

/**
 * 这个只用性能测试的返回结果的结构封装
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
