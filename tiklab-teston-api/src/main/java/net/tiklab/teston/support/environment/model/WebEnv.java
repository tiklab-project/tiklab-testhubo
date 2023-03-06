package net.tiklab.teston.support.environment.model;

import net.tiklab.postin.annotation.ApiModel;
import net.tiklab.postin.annotation.ApiProperty;
import net.tiklab.beans.annotation.Mapper;
import net.tiklab.core.BaseModel;

/**
 * web环境 模型
 */
@ApiModel
@Mapper(targetAlias = "WebEnvEntity")
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
