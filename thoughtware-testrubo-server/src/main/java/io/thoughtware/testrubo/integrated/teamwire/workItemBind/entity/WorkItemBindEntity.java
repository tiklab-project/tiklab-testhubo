package io.thoughtware.testrubo.integrated.teamwire.workItemBind.entity;


import io.thoughtware.dal.jpa.annotation.*;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 绑定的缺陷 实体
 */
@Entity
@Table(name="teston_workitem_bind")
public class WorkItemBindEntity implements Serializable {

    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id",length = 32)
    private String id;

    // 绑定的缺陷
    @Column(name = "workitem_id",length = 32)
    private String workItemId;

    // 用例
    @Column(name = "case_id")
    private String caseId;

    @Column(name = "repository_id")
    private String repositoryId;

    // 创建时间
    @Column(name = "create_time")
    private Timestamp createTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWorkItemId() {
        return workItemId;
    }

    public void setWorkItemId(String workItemId) {
        this.workItemId = workItemId;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getRepositoryId() {
        return repositoryId;
    }

    public void setRepositoryId(String repositoryId) {
        this.repositoryId = repositoryId;
    }
}
