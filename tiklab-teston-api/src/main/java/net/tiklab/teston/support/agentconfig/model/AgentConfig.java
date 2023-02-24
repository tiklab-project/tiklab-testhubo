package net.tiklab.teston.support.agentconfig.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import net.tiklab.beans.annotation.Mapper;
import net.tiklab.core.BaseModel;
import net.tiklab.postin.annotation.ApiModel;
import net.tiklab.postin.annotation.ApiProperty;

@ApiModel
@Mapper(targetAlias = "AgentConfigEntity")
public class AgentConfig extends BaseModel {

    @ApiProperty(name="id",desc="id")
    private java.lang.String id;

    @ApiProperty(name="repositoryId",desc="repositoryId")
    private java.lang.String repositoryId;

    @ApiProperty(name="name",desc="name")
    private java.lang.String name;

    @ApiProperty(name="url",desc="url")
    private java.lang.String url;

    @ApiProperty(name="status",desc="status")
    private java.lang.Integer status;

    @ApiProperty(name="number",desc="number")
    private java.lang.Integer number;

    @ApiProperty(name="createTime",desc="createTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private java.sql.Timestamp createTime;

    public java.lang.String getId() {
        return id;
    }

    public void setId(java.lang.String id) {
        this.id = id;
    }
    public java.lang.String getRepositoryId() {
        return repositoryId;
    }

    public void setRepositoryId(java.lang.String repositoryId) {
        this.repositoryId = repositoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public java.lang.String getUrl() {
        return url;
    }

    public void setUrl(java.lang.String url) {
        this.url = url;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public java.lang.Integer getNumber() {
        return number;
    }

    public void setNumber(java.lang.Integer number) {
        this.number = number;
    }
    public java.sql.Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(java.sql.Timestamp createTime) {
        this.createTime = createTime;
    }
}
