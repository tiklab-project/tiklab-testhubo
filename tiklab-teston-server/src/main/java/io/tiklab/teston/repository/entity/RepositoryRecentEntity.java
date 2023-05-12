package io.tiklab.teston.repository.entity;


import io.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 最近访问仓库 实体
 */
@Entity @Table(name="teston_repository_recent")
public class RepositoryRecentEntity implements Serializable {

    @Id
     @GeneratorValue(length = 12)
    @Column(name = "id",length = 32)
    private String id;

    // 所属仓库
    @Column(name = "repository_id",length = 64,notNull = true)
    private String repositoryId;

    // 所属人
    @Column(name = "user_id",length = 32)
    private String userId;

    // 更新时间
    @Column(name = "update_time",length = 4)
    private Timestamp updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRepositoryId() {
        return repositoryId;
    }

    public void setRepositoryId(String repositoryId) {
        this.repositoryId = repositoryId;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }
}
