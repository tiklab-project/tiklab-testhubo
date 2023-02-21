package net.tiklab.teston.testjob.model;

import net.tiklab.postin.annotation.ApiModel;
import net.tiklab.postin.annotation.ApiProperty;
import net.tiklab.beans.annotation.Mapper;
import net.tiklab.beans.annotation.Mapping;
import net.tiklab.beans.annotation.Mappings;
import net.tiklab.core.BaseModel;
import net.tiklab.join.annotation.Join;
import net.tiklab.join.annotation.JoinQuery;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

@ApiModel
@Join
@Mapper(targetAlias ="QuartzTaskEntity")
public class QuartzTask extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private java.lang.String id;

    @NotNull
    @ApiProperty(name="cronExpression",desc=" 定时任务表达式",required = true)
    private java.lang.String cronExpression;

    @ApiProperty(name="endTime",desc="定时任务时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date endTime;

    @NotNull
    @ApiProperty(name="method",desc="定时任务主表",required = true)
    @Mappings({
            @Mapping(source = "quartzMaster.id",target = "quartzMasterId")
    })
    @JoinQuery(key = "id")
    private QuartzMaster quartzMaster;

    @NotNull
    @ApiProperty(name="endTime",desc="执行时间",required = true)
    private String executionTime;

    public java.lang.String getId() {
        return id;
    }

    public void setId(java.lang.String id) {
        this.id = id;
    }
    public java.lang.String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(java.lang.String cronExpression) {
        this.cronExpression = cronExpression;
    }


    public QuartzMaster getQuartzMaster() {
        return quartzMaster;
    }

    public void setQuartzMaster(QuartzMaster quartzMaster) {
        this.quartzMaster = quartzMaster;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(String executionTime) {
        this.executionTime = executionTime;
    }
}
