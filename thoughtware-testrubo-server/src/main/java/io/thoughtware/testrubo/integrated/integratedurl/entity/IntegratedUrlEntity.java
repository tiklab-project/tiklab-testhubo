package io.thoughtware.testrubo.integrated.integratedurl.entity;


import io.thoughtware.dal.jpa.annotation.*;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * postinUrl配置 实体
 */
@Entity
@Table(name="teston_integrated_url")
public class IntegratedUrlEntity implements Serializable {

    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id",length = 32)
    private String id;

    // 地址
    @Column(name = "url",length = 256)
    private String url;

    // 项目名称
    @Column(name = "project_name")
    private String projectName;

    // 创建时间
    @Column(name = "create_time")
    private Timestamp createTime;

    @Column(name = "repository_id")
    private String repositoryId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
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
