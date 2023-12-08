package io.thoughtware.teston.testplan.cases.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.thoughtware.teston.repository.model.Repository;
import io.thoughtware.beans.annotation.Mapper;
import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;
import io.thoughtware.beans.annotation.Mapping;
import io.thoughtware.beans.annotation.Mappings;
import io.thoughtware.core.BaseModel;
import io.thoughtware.join.annotation.Join;
import io.thoughtware.join.annotation.JoinQuery;
import io.thoughtware.user.user.model.User;

import java.sql.Timestamp;

/**
 * 测试计划 模型
 */
@ApiModel
@Mapper
@Join
public class TestPlan extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private java.lang.String id;

    @ApiProperty(name="name",desc="测试计划名称")
    private java.lang.String name;

    @ApiProperty(name="startTime",desc="开始时间")
    private java.util.Date startTime;

    @ApiProperty(name="endTime",desc="结束时间")
    private java.util.Date endTime;

    @ApiProperty(name="state",desc="状态  0 未开始  1进行中  2结束")
    private java.lang.Integer state;

    @ApiProperty(name="principal",desc="负责人")
    @Mappings({
            @Mapping(source = "principal.id",target = "principals")
    })
    @JoinQuery(key = "id")
    private User principal;

    @ApiProperty(name="repository",desc="所属仓库")
    @Mappings({
            @Mapping(source = "repository.id",target = "repositoryId")
    })
    @JoinQuery(key = "id")
    private Repository repository;

    @ApiProperty(name="desc",desc="描述")
    private java.lang.String desc;

    @ApiProperty(name="createTime",desc="创建时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Timestamp createTime;

    @ApiProperty(name="updateTime",desc="更新时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Timestamp updateTime;

    @ApiProperty(name="sort",desc="排序")
    private java.lang.Integer sort;

    @ApiProperty(name="testCaseNum",desc="用例数量")
    private Integer testCaseNum;

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
    public java.util.Date getStartTime() {
        return startTime;
    }

    public void setStartTime(java.util.Date startTime) {
        this.startTime = startTime;
    }
    public java.util.Date getEndTime() {
        return endTime;
    }

    public void setEndTime(java.util.Date endTime) {
        this.endTime = endTime;
    }
    public java.lang.Integer getState() {
        return state;
    }

    public void setState(java.lang.Integer state) {
        this.state = state;
    }

    public User getPrincipal() {
        return principal;
    }

    public void setPrincipal(User principal) {
        this.principal = principal;
    }

    public java.lang.String getDesc() {
        return desc;
    }

    public void setDesc(java.lang.String desc) {
        this.desc = desc;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public java.lang.Integer getSort() {
        return sort;
    }

    public void setSort(java.lang.Integer sort) {
        this.sort = sort;
    }

    public Integer getTestCaseNum() {
        return testCaseNum;
    }

    public void setTestCaseNum(Integer testCaseNum) {
        this.testCaseNum = testCaseNum;
    }

    public Repository getRepository() {
        return repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }
}
