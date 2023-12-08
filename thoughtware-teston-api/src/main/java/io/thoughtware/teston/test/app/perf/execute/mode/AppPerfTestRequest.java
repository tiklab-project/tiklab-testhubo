package io.thoughtware.teston.test.app.perf.execute.mode;

import io.thoughtware.teston.test.app.scene.execute.model.AppSceneTestRequest;
import io.thoughtware.teston.test.app.scene.execute.model.AppTestConfig;
import io.thoughtware.teston.test.app.perf.cases.model.AppPerfCase;
import io.thoughtware.core.BaseModel;
import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;

import java.util.List;

/**
 * app性能测试数据封装 模型
 */
@ApiModel
public class AppPerfTestRequest extends BaseModel {

    @ApiProperty(name = "appPerfId", desc = "appPerfId")
    private String appPerfId;

    @ApiProperty(name = "appPerfTestCase", desc = "压力测试用例")
    private AppPerfCase appPerfCase;

    @ApiProperty(name = "appTestConfig", desc = "app测试配置")
    private AppTestConfig appTestConfig;

    @ApiProperty(name = "appTestConfig", desc = "app测试配置列表")
    private List<AppTestConfig> appTestConfigList;

    @ApiProperty(name="appSceneTestRequestList",desc="压力测试下绑定的场景列表")
    private List<AppSceneTestRequest> appSceneTestRequestList;

    @ApiProperty(name="exeNum",desc="执行次数")
    private Integer exeNum;

    @ApiProperty(name="currentAgentId",desc="当前agent")
    private String currentAgentId;

    @ApiProperty(name="exeType",desc="只用于测试计划中")
    private String exeType;

    public Integer getExeNum() {
        return exeNum;
    }

    public void setExeNum(Integer exeNum) {
        this.exeNum = exeNum;
    }

    public String getCurrentAgentId() {
        return currentAgentId;
    }

    public void setCurrentAgentId(String currentAgentId) {
        this.currentAgentId = currentAgentId;
    }

    public List<AppTestConfig> getAppTestConfigList() {
        return appTestConfigList;
    }

    public void setAppTestConfigList(List<AppTestConfig> appTestConfigList) {
        this.appTestConfigList = appTestConfigList;
    }

    public String getAppPerfId() {
        return appPerfId;
    }

    public void setAppPerfId(String appPerfId) {
        this.appPerfId = appPerfId;
    }

    public AppPerfCase getAppPerfCase() {
        return appPerfCase;
    }

    public void setAppPerfCase(AppPerfCase appPerfCase) {
        this.appPerfCase = appPerfCase;
    }

    public AppTestConfig getAppTestConfig() {
        return appTestConfig;
    }

    public void setAppTestConfig(AppTestConfig appTestConfig) {
        this.appTestConfig = appTestConfig;
    }

    public List<AppSceneTestRequest> getAppSceneTestRequestList() {
        return appSceneTestRequestList;
    }

    public void setAppSceneTestRequestList(List<AppSceneTestRequest> appSceneTestRequestList) {
        this.appSceneTestRequestList = appSceneTestRequestList;
    }

    public String getExeType() {
        return exeType;
    }

    public void setExeType(String exeType) {
        this.exeType = exeType;
    }
}
