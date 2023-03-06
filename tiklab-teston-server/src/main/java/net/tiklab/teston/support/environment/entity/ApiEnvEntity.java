package net.tiklab.teston.support.environment.entity;


import net.tiklab.dal.jpa.annotation.Column;
import net.tiklab.dal.jpa.annotation.GeneratorValue;
import net.tiklab.dal.jpa.annotation.Id;
import net.tiklab.dal.jpa.annotation.Table;import net.tiklab.dal.jpa.annotation.Entity;

import java.io.Serializable;

/**
 * 接口环境 实体
 */
@Entity @Table(name="teston_env_api")
public class ApiEnvEntity implements Serializable {

    @Id
    @GeneratorValue
    @Column(name = "id",length = 32)
    private String id;

    // 环境名称
    @Column(name = "name",length = 128)
    private String name;

    // 前置url
    @Column(name = "pre_url",length = 128)
    private String preUrl;

    // 所属仓库
    @Column(name = "repository_id",length = 32)
    private String repositoryId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPreUrl() {
        return preUrl;
    }

    public void setPreUrl(String preUrl) {
        this.preUrl = preUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRepositoryId() {
        return repositoryId;
    }

    public void setRepositoryId(String repositoryId) {
        this.repositoryId = repositoryId;
    }

}
