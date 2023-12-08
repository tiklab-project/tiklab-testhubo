package io.thoughtware.teston.support.environment.model;

import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;
import io.thoughtware.beans.annotation.Mapper;
import io.thoughtware.core.BaseModel;

/**
 * web环境 模型
 */
@ApiModel
@Mapper
public class WebEnv extends BaseModel {

    @ApiProperty(name="id",desc="id")
    private java.lang.String id;

    @ApiProperty(name = "repositoryId",desc= "仓库id")
    private String repositoryId;

    @ApiProperty(name="name",desc="名称")
    private java.lang.String name;

    @ApiProperty(name="webDriver",desc="如: chrome, firefox")
    private java.lang.String webDriver;

    public java.lang.String getId() {
        return id;
    }

    public void setId(java.lang.String id) {
        this.id = id;
    }

    public String getRepositoryId() {
        return repositoryId;
    }

    public void setRepositoryId(String repositoryId) {
        this.repositoryId = repositoryId;
    }

    public java.lang.String getName() {
        return name;
    }

    public void setName(java.lang.String name) {
        this.name = name;
    }

    public String getWebDriver() {
        return webDriver;
    }

    public void setWebDriver(String webDriver) {
        this.webDriver = webDriver;
    }
}
