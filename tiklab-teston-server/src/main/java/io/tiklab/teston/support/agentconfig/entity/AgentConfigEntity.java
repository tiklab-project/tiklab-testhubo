package io.tiklab.teston.support.agentconfig.entity;


import io.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * agent配置 实体
 */
@Entity
@Table(name="teston_agent_config")
public class AgentConfigEntity implements Serializable {

    @Id
    @GeneratorValue
    @Column(name = "id",length = 32)
    private String id;

    // 所属仓库
    @Column(name = "repository_id",length = 32)
    private String repositoryId;

    // 名称
    @Column(name = "name",length = 64)
    private String name;

    // 地址
    @Column(name = "url",length = 256)
    private String url;

    // 状态
    @Column(name = "status",length = 8)
    private Integer status;

    // 数量
    @Column(name = "number",length = 8)
    private Integer number;

    // 创建时间
    @Column(name = "create_time")
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}
