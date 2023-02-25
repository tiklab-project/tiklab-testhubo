package net.tiklab.teston.testcase.entity;


import net.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name="teston_testcase")
public class TestCaseEntity implements Serializable {

    @Id
    @Column(name = "id",length = 32)
    private String id;

    @Column(name = "name",length = 64,notNull = true)
    private String name;

    @Column(name = "category_id",length = 32)
    private String categoryId;

    @Column(name = "repository_id",length = 32,notNull = true)
    private String repositoryId;

    @Column(name = "test_type",length = 64,notNull = true)
    private String testType;

    @Column(name = "case_type",length = 64,notNull = true)
    private String caseType;

    @Column(name = "create_user",length = 64)
    private String createUser;

    @Column(name = "update_user",length = 64)
    private String updateUser;

    @Column(name = "create_time",length = 64)
    private Timestamp createTime;

    @Column(name = "update_time",length = 64)
    private Timestamp updateTime;

    @Column(name = "sort")
    private Integer sort;

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
}
