package io.thoughtware.teston.support.agentconfig.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.thoughtware.toolkit.beans.annotation.Mapper;
import io.thoughtware.core.BaseModel;
import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;

import java.sql.Timestamp;

/**
 * agent配置 模型
 */
@ApiModel
@Mapper
public class AgentConfig extends BaseModel {

    @ApiProperty(name="id",desc="id")
    private java.lang.String id;

    @ApiProperty(name="name",desc="名称")
    private java.lang.String name;

    @ApiProperty(name="address",desc="地址")
    private java.lang.String address;

    @ApiProperty(name="status",desc="状态")
    private String status;

    @ApiProperty(name="updateTime",desc="更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private java.sql.Timestamp updateTime;

    public java.lang.String getId() {
        return id;
    }

    public void setId(java.lang.String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }
}
