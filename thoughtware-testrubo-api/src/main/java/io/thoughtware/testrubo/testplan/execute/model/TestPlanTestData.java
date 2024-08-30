package io.thoughtware.testrubo.testplan.execute.model;

import io.thoughtware.core.BaseModel;
import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;

import java.util.List;

/**
 * 测试数据构造 模型
 */
@ApiModel
public class TestPlanTestData extends BaseModel{

    @ApiProperty(name="testPlanId",desc="当前测试计划Id")
    private String testPlanId;

    @ApiProperty(name="repositoryId",desc="仓库Id")
    private String repositoryId;

    @ApiProperty(name="apiEnv",desc="接口环境")
    private String apiEnv;

    @ApiProperty(name="webEnv",desc="WEB环境")
    private String webEnv;

    @ApiProperty(name="appEnv",desc="APP环境")
    private String appEnv;

    @ApiProperty(name="appEnvList",desc="APP环境list")
    private List<String> appEnvList;

    public String getTestPlanId() {
        return testPlanId;
    }

    public void setTestPlanId(String testPlanId) {
        this.testPlanId = testPlanId;
    }

    public String getRepositoryId() {
        return repositoryId;
    }

    public void setRepositoryId(String repositoryId) {
        this.repositoryId = repositoryId;
    }

    public String getApiEnv() {
        return apiEnv;
    }

    public void setApiEnv(String apiEnv) {
        this.apiEnv = apiEnv;
    }

    public String getWebEnv() {
        return webEnv;
    }

    public void setWebEnv(String webEnv) {
        this.webEnv = webEnv;
    }

    public String getAppEnv() {
        return appEnv;
    }

    public void setAppEnv(String appEnv) {
        this.appEnv = appEnv;
    }

    public List<String> getAppEnvList() {
        return appEnvList;
    }

    public void setAppEnvList(List<String> appEnvList) {
        this.appEnvList = appEnvList;
    }
}
