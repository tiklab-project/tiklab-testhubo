package net.tiklab.testonapp.scene.instance.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import net.tiklab.beans.annotation.Mapper;
import net.tiklab.core.BaseModel;
import net.tiklab.join.annotation.Join;
import net.tiklab.postin.annotation.ApiModel;
import net.tiklab.postin.annotation.ApiProperty;

import java.sql.Timestamp;
import java.util.List;

@ApiModel
@Mapper(targetAlias = "AppSceneInstanceEntity")
@Join
public class AppSceneInstance extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private String id;

    @ApiProperty(name="appSceneId",desc="appSceneId")
    private String appSceneId;

    @ApiProperty(name="result",desc="测试结果 成功:1 , 失败:0")
    private Integer result;

    @ApiProperty(name = "stepNum", desc = "测试步骤数量")
    private Integer stepNum;

    @ApiProperty(name="passNum",desc="通过数量")
    private Integer passNum;

    @ApiProperty(name="failNum",desc="未通过数量")
    private Integer failNum;

    @ApiProperty(name="passRate",desc="通过率")
    private String passRate;

    @ApiProperty(name="stepList",desc="存放app测试步骤结果")
    private List<AppSceneInstanceStep> stepList;

    @ApiProperty(name="createTime",desc="createTime")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Timestamp createTime;

    @ApiProperty(name="executeNumber",desc="executeNumber")
    private Integer executeNumber;

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

    public List<AppSceneInstanceStep> getStepList() {
        return stepList;
    }

    public void setStepList(List<AppSceneInstanceStep> stepList) {
        this.stepList = stepList;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Integer getExecuteNumber() {
        return executeNumber;
    }

    public void setExecuteNumber(Integer executeNumber) {
        this.executeNumber = executeNumber;
    }
}
