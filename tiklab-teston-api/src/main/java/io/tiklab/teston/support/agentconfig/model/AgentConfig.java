package io.tiklab.teston.support.agentconfig.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.tiklab.beans.annotation.Mapper;
import io.tiklab.core.BaseModel;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;

/**
 * agent配置 模型
 */
@ApiModel
@Mapper
public class AgentConfig extends BaseModel {

    @ApiProperty(name="id",desc="id")
    private java.lang.String id;

    @ApiProperty(name="repositoryId",desc="所属仓库")
    private java.lang.String repositoryId;


    @ApiProperty(name="name",desc="名称")
    private java.lang.String name;

    @ApiProperty(name="url",desc="地址")
    private java.lang.String url;

    @ApiProperty(name="status",desc="状态")
    private java.lang.Integer status;

    @ApiProperty(name="number",desc="number")
    private java.lang.Integer number;

    @ApiProperty(name="createTime",desc="创建时间")
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
