package net.tiklab.teston.manager.support.environment.model;

import net.tiklab.postin.annotation.ApiModel;
import net.tiklab.postin.annotation.ApiProperty;
import net.tiklab.beans.annotation.Mapper;
import net.tiklab.core.BaseModel;

import javax.validation.constraints.NotNull;

@ApiModel
@Mapper(targetAlias = "ApiEnvEntity")
public class ApiEnv extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private java.lang.String id;

    @NotNull
    @ApiProperty(name="name",desc="测试环境名称",required = true)
    private java.lang.String name;

    @NotNull
    @ApiProperty(name="preUrl",desc="前置url",required = true)
    private java.lang.String preUrl;

    @ApiProperty(name="repositoryId",desc="用例库")
    private String repositoryId;


    public java.lang.String getId() {
        return id;
    }

    public void setId(java.lang.String id) {
        this.id = id;
    }
    public java.lang.String getName() {
        return name;
    }

    public void setName(java.lang.String name) {
        this.name = name;
    }

    public String getPreUrl() {
        return preUrl;
    }

    public void setPreUrl(String preUrl) {
        this.preUrl = preUrl;
    }

    public String getRepositoryId() {
        return repositoryId;
    }

    public void setRepositoryId(String repositoryId) {
        this.repositoryId = repositoryId;
    }
}