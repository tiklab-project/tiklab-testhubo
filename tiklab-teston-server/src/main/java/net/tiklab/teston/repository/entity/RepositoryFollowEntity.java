package net.tiklab.teston.repository.entity;


import net.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 仓库关注 实体
 */
@Entity @Table(name="teston_repository_follow")
public class RepositoryFollowEntity implements Serializable {

    @Id
    @GeneratorValue
    @Column(name = "id",length = 32)
    private String id;

    // 仓库
    @Column(name = "repository_id",length = 32,notNull = true)
    private String repositoryId;

    // 所属人
    @Column(name = "user_id",length = 32)
    private String userId;

    // 创建时间
    @Column(name = "create_time",length = 4)
    private Timestamp createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRepositoryId() {
        return repositoryId;
    }

    public void setRepositoryId(String repositoryId) {
        this.repositoryId = repositoryId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}
