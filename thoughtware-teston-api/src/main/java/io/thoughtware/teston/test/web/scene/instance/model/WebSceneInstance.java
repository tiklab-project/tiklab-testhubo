package io.thoughtware.teston.test.web.scene.instance.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.thoughtware.teston.instance.model.Instance;
import io.thoughtware.teston.test.common.stepcommon.model.StepCommonInstance;
import io.thoughtware.toolkit.beans.annotation.Mapper;
import io.thoughtware.core.BaseModel;
import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;

import java.sql.Timestamp;
import java.util.List;

/**
 * web场景实例 模型
 */
@ApiModel
@Mapper
public class WebSceneInstance extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private String id;

    @ApiProperty(name="webSceneId",desc="webSceneId")
    private String webSceneId;

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

    @ApiProperty(name="totalDuration",desc="耗时")
    private Double totalDuration;

    @ApiProperty(name="stepList",desc="存放webUI测试步骤结果")
    private List<StepCommonInstance> stepList;

    private Instance instance;

    @ApiProperty(name="createTime",desc="创建时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Timestamp createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWebSceneId() {
        return webSceneId;
    }

    public void setWebSceneId(String webSceneId) {
        this.webSceneId = webSceneId;
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

    public Double getTotalDuration() {
        return totalDuration;
    }

    public void setTotalDuration(Double totalDuration) {
        this.totalDuration = totalDuration;
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

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public Instance getInstance() {
        return instance;
    }

    public void setInstance(Instance instance) {
        this.instance = instance;
    }
}
