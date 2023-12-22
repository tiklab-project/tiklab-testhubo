package io.thoughtware.teston.testplan.cases.service;


import io.thoughtware.dal.jpa.annotation.Column;
import io.thoughtware.dal.jpa.annotation.Entity;
import io.thoughtware.dal.jpa.annotation.Id;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 测试计划中查询用例返回的 实体
 */
@Entity
public class PlanCaseEntity implements Serializable {

    @Id
    @Column(name = "id",length = 32)
    private String id;

    // 用例名
    @Column(name = "name",length = 64,notNull = true)
    private String name;

    // 所属模块
    @Column(name = "category_id",length = 32)
    private String categoryId;

    // 用例类型，例：api-unit,api-scene
    @Column(name = "case_type",length = 64,notNull = true)
    private String caseType;

    // 创建人
    @Column(name = "create_user",length = 64)
    private String createUser;

    @Column(name = "create_time",length = 64)
    private Timestamp createTime;

    //用于plan 绑定的case，返回的id做映射
    @Column(name = "plan_case_id")
    private String planCaseId;

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

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getPlanCaseId() {
        return planCaseId;
    }

    public void setPlanCaseId(String planCaseId) {
        this.planCaseId = planCaseId;
    }
}
