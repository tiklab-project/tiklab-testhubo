package io.tiklab.teston.integratedpostin.workspaceBind.entity;


import io.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 绑定的空间 实体
 */
@Entity
@Table(name="teston_workspace_bind")
public class WorkspaceBindEntity implements Serializable {

    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id",length = 32)
    private String id;

    // 绑定的空间
    @Column(name = "workspace_id",length = 32)
    private String workspaceId;

    // 仓库
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

    public String getWorkspaceId() {
        return workspaceId;
    }

    public void setWorkspaceId(String workspaceId) {
        this.workspaceId = workspaceId;
    }

    public String getRepositoryId() {
        return repositoryId;
    }

    public void setRepositoryId(String repositoryId) {
        this.repositoryId = repositoryId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}
