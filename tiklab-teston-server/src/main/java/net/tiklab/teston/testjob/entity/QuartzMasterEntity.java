package net.tiklab.teston.testjob.entity;


import net.tiklab.dal.jpa.annotation.Column;
import net.tiklab.dal.jpa.annotation.GeneratorValue;
import net.tiklab.dal.jpa.annotation.Id;
import net.tiklab.dal.jpa.annotation.Table;import net.tiklab.dal.jpa.annotation.Entity;

import java.io.Serializable;
import java.util.Date;

@Entity @Table(name="teston_quartz_master")
public class QuartzMasterEntity implements Serializable {

    @Id
    @GeneratorValue
    @Column(name = "id",length = 32)
    private String id;

    //名称
    @Column(name = "name",length = 64,notNull = true)
    private String name;

    //定时任务类全路径
    @Column(name = "task_Class_url",length = 32)
    private String taskClassUrl;

    //定时任务描述
    @Column(name = "description",length = 32)
    private String description;


    //循环次数
    @Column(name = "cycle_index")
    private Integer cycleIndex;

    //类型 0指定时间  1为循环
    @Column(name = "type")
    private Integer type;

    //环境id
    @Column(name = "apiEnv_id",length = 32)
    private String apiEnvId;

    //用例库的id
    @Column(name = "repository_id",length = 32,notNull = true)
    private String repositoryId;

    // 0暂停  1.进行中  2.已结束
    @Column(name = "state")
    private Integer state;

    //定时任务类型  web app  api
    @Column(name = "quartz_type")
    private String   quartzType;

    //执行周期   day week
    @Column(name = "period")
    private String   period;

    //按照周轮询  周几
    @Column(name = "weeks")
    private String   weeks;

    //排序
    @Column(name = "sort")
    private Integer sort;

    //创建时间
    @Column(name = "create_time")
    private Date createTime;

    //创建时间
    @Column(name = "update_time")
    private Date updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTaskClassUrl() {
        return taskClassUrl;
    }

    public void setTaskClassUrl(String taskClassUrl) {
        this.taskClassUrl = taskClassUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getCycleIndex() {
        return cycleIndex;
    }

    public void setCycleIndex(Integer cycleIndex) {
        this.cycleIndex = cycleIndex;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getApiEnvId() {
        return apiEnvId;
    }

    public void setApiEnvId(String apiEnvId) {
        this.apiEnvId = apiEnvId;
    }

    public String getRepositoryId() {
        return repositoryId;
    }

    public void setRepositoryId(String repositoryId) {
        this.repositoryId = repositoryId;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getWeeks() {
        return weeks;
    }

    public void setWeeks(String weeks) {
        this.weeks = weeks;
    }

    public String getQuartzType() {
        return quartzType;
    }

    public void setQuartzType(String quartzType) {
        this.quartzType = quartzType;
    }
}
