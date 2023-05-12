package io.tiklab.teston.support.environment.entity;


import io.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;

/**
 * web环境 实体
 */
@Entity @Table(name="teston_env_web")
public class WebEnvEntity implements Serializable {

    @Id
     @GeneratorValue(length = 12)
    @Column(name = "id",length = 32)
    private String id;

    // 仓库id
    @Column(name = "repository_id",length = 32)
    private String repositoryId;

    // 名称
    @Column(name = "name",length = 128)
    private String name;

    // webDriver 如: chrome, firefox
    @Column(name = "web_driver",length = 32)
    private String webDriver;

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

    public String getWebDriver() {
        return webDriver;
    }

    public void setWebDriver(String webDriver) {
        this.webDriver = webDriver;
    }

    public String getRepositoryId() {
        return repositoryId;
    }

    public void setRepositoryId(String repositoryId) {
        this.repositoryId = repositoryId;
    }
}
