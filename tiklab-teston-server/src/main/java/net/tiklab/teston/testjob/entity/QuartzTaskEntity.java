package net.tiklab.teston.testjob.entity;


import net.tiklab.dal.jpa.annotation.Column;
import net.tiklab.dal.jpa.annotation.GeneratorValue;
import net.tiklab.dal.jpa.annotation.Id;
import net.tiklab.dal.jpa.annotation.Table;import net.tiklab.dal.jpa.annotation.Entity;

import java.io.Serializable;
import java.util.Date;

@Entity @Table(name="teston_quartz_task")
public class QuartzTaskEntity implements Serializable {

    @Id
    @GeneratorValue
    @Column(name = "id",length = 32)
    private String id;

    //定时任务表达式
    @Column(name = "cron_expression",length = 32,notNull = true)
    private String cronExpression;


    //结束时间
    @Column(name = "end_Time")
    private Date endTime;


    //执行时间
    @Column(name = "execution_time")
    private String executionTime;

   //定时器主表id
    @Column(name = "quartz_master_id",notNull = true)
    private String quartzMasterId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }


    public String getQuartzMasterId() {
        return quartzMasterId;
    }

    public void setQuartzMasterId(String quartzMasterId) {
        this.quartzMasterId = quartzMasterId;
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
