package io.thoughtware.testrubo.testplan.quartz.model;

import io.thoughtware.core.BaseModel;
import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;
import io.thoughtware.toolkit.beans.annotation.Mapper;
import io.thoughtware.toolkit.join.annotation.Join;


/**
 * 定时任务的时间 模型
 */
@ApiModel
@Mapper
@Join
public class QuartzTimePlan extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private String id;

    @ApiProperty(name="quartzPlanId",desc="quartzPlanId")
    private String quartzPlanId;

    @ApiProperty(name="time",desc="time")
    private String time;

    @ApiProperty(name="cron",desc="cron")
    private String cron;

    @ApiProperty(name="exeType",desc="exeType")
    private Integer exeType;

    @ApiProperty(name="week",desc="week")
    private Integer week;

    @ApiProperty(name="showTime",desc="showTime")
    private String showTime;

    private String repositoryId;
    private String testPlanId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuartzPlanId() {
        return quartzPlanId;
    }

    public void setQuartzPlanId(String quartzPlanId) {
        this.quartzPlanId = quartzPlanId;
    }

    public String getCron() {
        return cron;
    }

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public Integer getExeType() {
        return exeType;
    }

    public void setExeType(Integer exeType) {
        this.exeType = exeType;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public String getRepositoryId() {
        return repositoryId;
    }

    public void setRepositoryId(String repositoryId) {
        this.repositoryId = repositoryId;
    }

    public String getTestPlanId() {
        return testPlanId;
    }

    public void setTestPlanId(String testPlanId) {
        this.testPlanId = testPlanId;
    }
}
