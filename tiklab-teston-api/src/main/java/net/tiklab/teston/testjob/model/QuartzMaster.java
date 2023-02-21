package net.tiklab.teston.testjob.model;

import net.tiklab.postin.annotation.ApiModel;
import net.tiklab.postin.annotation.ApiProperty;
import net.tiklab.beans.annotation.Mapper;
import net.tiklab.beans.annotation.Mapping;
import net.tiklab.beans.annotation.Mappings;
import net.tiklab.core.BaseModel;
import net.tiklab.join.annotation.Join;
import net.tiklab.join.annotation.JoinQuery;
import net.tiklab.teston.repository.model.Repository;
import net.tiklab.teston.integration.environment.model.ApiEnv;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ApiModel
@Mapper(targetAlias = "QuartzMasterEntity")
@Join
public class QuartzMaster extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private java.lang.String id;

    @ApiProperty(name="name",desc="名字")
    private java.lang.String name;

    @ApiProperty(name="taskClassUrl",desc="定时任务全路径")
    private java.lang.String taskClassUrl;

    @ApiProperty(name="description",desc="描述")
    private java.lang.String description;

    @ApiProperty(name="cycleIndex",desc="循环几次")
    private java.lang.Integer cycleIndex;

    @ApiProperty(name="type",desc="类型  0指定时间  1为循环")
    private java.lang.Integer type;

    @ApiProperty(name="period",desc="执行周期 day  week")
    private java.lang.String period;

    @ApiProperty(name="apiEnv",desc="测试当前环境")
    @Mappings({
            @Mapping(source = "apiEnv.id",target = "apiEnvId")
    })
    @JoinQuery(key = "id")
    private ApiEnv apiEnv;


    @ApiProperty(name="repository",desc="用例库id",eg="@selectOne")
    @Mappings({
            @Mapping(source = "repository.id",target = "repositoryId")
    })
    @JoinQuery(key = "id")
    private Repository repository;

    @ApiProperty(name="state",desc="状态 0暂停  1.进行中  2.已结束")
    private java.lang.Integer state;

    @ApiProperty(name="sort",desc="排序")
    private java.lang.Integer sort;

    @ApiProperty(name="createTime",desc="创建时间")
    private Date createTime;

    @ApiProperty(name="updateTime",desc="更新时间")
    private Date updateTime;

    @ApiProperty(name="weeks",desc="按周执行 周几")
    private java.lang.String weeks;

    @ApiProperty(name="caseNumber",desc="用例数")
    private java.lang.Integer caseNumber;

    @ApiProperty(name="executionTime",desc="执行时间，这个字段用于前端展示")
    private java.lang.String executionTimes;

    @ApiProperty(name="quartzTaskList",desc="定时任务时间")
    private List<QuartzTask> quartzTaskList=new ArrayList<>();

    @ApiProperty(name="weekList",desc="定时任务周几")
    private List<Integer> weekList=new ArrayList<>();

    @ApiProperty(name="quartzType",desc="定时任务类型  WEB APP  API")
    private String quartzType;

    @ApiProperty(name="userId",desc="创建人id")
    private String userId;


    public java.lang.String getId() {
        return id;
    }

    public void setId(java.lang.String id) {
        this.id = id;
    }
    public java.lang.String getName() {
        return name;
    }

    public void setName(java.lang.String name) {
        this.name = name;
    }
    public java.lang.String getTaskClassUrl() {
        return taskClassUrl;
    }

    public void setTaskClassUrl(java.lang.String taskClassUrl) {
        this.taskClassUrl = taskClassUrl;
    }
    public java.lang.String getDescription() {
        return description;
    }

    public void setDescription(java.lang.String description) {
        this.description = description;
    }
    public java.lang.Integer getCycleIndex() {
        return cycleIndex;
    }

    public void setCycleIndex(java.lang.Integer cycleIndex) {
        this.cycleIndex = cycleIndex;
    }
    public java.lang.Integer getType() {
        return type;
    }

    public void setType(java.lang.Integer type) {
        this.type = type;
    }
    public java.lang.Integer getState() {
        return state;
    }

    public void setState(java.lang.Integer state) {
        this.state = state;
    }
    public java.lang.Integer getSort() {
        return sort;
    }

    public void setSort(java.lang.Integer sort) {
        this.sort = sort;
    }
    public java.util.Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }
    public java.util.Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(java.util.Date updateTime) {
        this.updateTime = updateTime;
    }

    public List<QuartzTask> getQuartzTaskList() {
        return quartzTaskList;
    }

    public void setQuartzTaskList(List<QuartzTask> quartzTaskList) {
        this.quartzTaskList = quartzTaskList;
    }

    public ApiEnv getApiEnv() {
        return apiEnv;
    }

    public void setApiEnv(ApiEnv apiEnv) {
        this.apiEnv = apiEnv;
    }

    public Repository getRepository() {
        return repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    public Integer getCaseNumber() {
        return caseNumber;
    }

    public void setCaseNumber(Integer caseNumber) {
        this.caseNumber = caseNumber;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public List<Integer> getWeekList() {
        return weekList;
    }

    public void setWeekList(List<Integer> weekList) {
        this.weekList = weekList;
    }

    public String getExecutionTimes() {
        return executionTimes;
    }

    public void setExecutionTimes(String executionTimes) {
        this.executionTimes = executionTimes;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
