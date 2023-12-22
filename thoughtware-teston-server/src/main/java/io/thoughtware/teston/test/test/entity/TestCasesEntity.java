package io.thoughtware.teston.test.test.entity;


import io.thoughtware.dal.jpa.annotation.*;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 测试用例 实体
 */
@Entity
@Table(name="teston_testcase")
public class TestCasesEntity implements Serializable {

    @Id
    @Column(name = "id",length = 32)
    private String id;

    // 用例名
    @Column(name = "name",length = 64,notNull = true)
    private String name;

    // 所属模块
    @Column(name = "category_id",length = 32)
    private String categoryId;

    // 所属仓库
    @Column(name = "repository_id",length = 32,notNull = true)
    private String repositoryId;

    // 测试类型
    @Column(name = "test_type",length = 64,notNull = true)
    private String testType;

    // 用例类型，例：api-unit,api-scene
    @Column(name = "case_type",length = 64,notNull = true)
    private String caseType;

    // 创建人
    @Column(name = "create_user",length = 64)
    private String createUser;

    // 更新人
    @Column(name = "update_user",length = 64)
    private String updateUser;

    // 创建时间
    @Column(name = "create_time",length = 64)
    private Timestamp createTime;

    // 更新时间
    @Column(name = "update_time",length = 64)
    private Timestamp updateTime;

    // 排序
    @Column(name = "sort")
    private Integer sort;

    @Column(name = "workitem_id",length = 64)
    private String workItemId ;

    // 负责人
    @Column(name = "director",length = 32)
    private String director;

    // 状态
    @Column(name = "status",length = 8)
    private Integer status;

    // 优先级
    @Column(name = "priority_level",length = 8)
    private Integer priorityLevel;

    // 描述
    @Column(name = "description",length = 128)
    private String desc;


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

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getRepositoryId() {
        return repositoryId;
    }

    public void setRepositoryId(String repositoryId) {
        this.repositoryId = repositoryId;
    }

    public String getTestType() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }

    public String getCaseType() {
        return caseType;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
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

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getWorkItemId() {
        return workItemId;
    }

    public void setWorkItemId(String workItemId) {
        this.workItemId = workItemId;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPriorityLevel() {
        return priorityLevel;
    }

    public void setPriorityLevel(Integer priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

}
