package io.tiklab.testhubo.test.app.scene.instance.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.tiklab.testhubo.instance.model.Instance;
import io.tiklab.testhubo.testplan.instance.model.TestPlanCaseInstanceBind;
import io.tiklab.toolkit.beans.annotation.Mapper;
import io.tiklab.core.BaseModel;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.testhubo.test.common.stepcommon.model.StepCommonInstance;

import java.sql.Timestamp;
import java.util.List;

/**
 * app场景实例 模型
 */
@ApiModel
@Mapper(targetName = "io.tiklab.testhubo.app.scene.instance.entity.AppSceneInstanceEntity")
public class AppSceneInstance extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private String id;

    @ApiProperty(name="appSceneId",desc="app场景id")
    private String appSceneId;

    @ApiProperty(name="result",desc="测试结果 成功:1 , 失败:0")
    private Integer result;

    @ApiProperty(name="status",desc="测试结果 成功:success , 失败:fail")
    private String status;

    @ApiProperty(name = "stepNum", desc = "测试步骤数量")
    private Integer stepNum;

    @ApiProperty(name="passNum",desc="通过数量")
    private Integer passNum;

    @ApiProperty(name="failNum",desc="未通过数量")
    private Integer failNum;

    @ApiProperty(name="passRate",desc="通过率")
    private String passRate;

    @ApiProperty(name = "elapsedTime")
    private Integer elapsedTime;

    @ApiProperty(name="stepList",desc="存放app测试步骤结果")
    private List<StepCommonInstance> stepList;

    @ApiProperty(name="createTime",desc="创建时间")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Timestamp createTime;

    private Instance instance;
    private TestPlanCaseInstanceBind testPlanCaseInstanceBind;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppSceneId() {
        return appSceneId;
    }

    public void setAppSceneId(String appSceneId) {
        this.appSceneId = appSceneId;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getStepNum() {
        return stepNum;
    }

    public void setStepNum(Integer stepNum) {
        this.stepNum = stepNum;
    }

    public Integer getPassNum() {
        return passNum;
    }

    public void setPassNum(Integer passNum) {
        this.passNum = passNum;
    }

    public Integer getFailNum() {
        return failNum;
    }

    public void setFailNum(Integer failNum) {
        this.failNum = failNum;
    }

    public String getPassRate() {
        return passRate;
    }

    public void setPassRate(String passRate) {
        this.passRate = passRate;
    }

    public Integer getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(Integer elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public List<StepCommonInstance> getStepList() {
        return stepList;
    }

    public void setStepList(List<StepCommonInstance> stepList) {
        this.stepList = stepList;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Instance getInstance() {
        return instance;
    }

    public void setInstance(Instance instance) {
        this.instance = instance;
    }

    public TestPlanCaseInstanceBind getTestPlanCaseInstanceBind() {
        return testPlanCaseInstanceBind;
    }

    public void setTestPlanCaseInstanceBind(TestPlanCaseInstanceBind testPlanCaseInstanceBind) {
        this.testPlanCaseInstanceBind = testPlanCaseInstanceBind;
    }
}
