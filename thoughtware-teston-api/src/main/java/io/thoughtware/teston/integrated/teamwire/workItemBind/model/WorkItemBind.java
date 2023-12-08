package io.thoughtware.teston.integrated.teamwire.workItemBind.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.thoughtware.beans.annotation.Mapper;
import io.thoughtware.beans.annotation.Mapping;
import io.thoughtware.beans.annotation.Mappings;
import io.thoughtware.core.BaseModel;
import io.thoughtware.join.annotation.Join;
import io.thoughtware.join.annotation.JoinQuery;
import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;
import io.thoughtware.teston.integrated.teamwire.workItem.model.WorkItemTestOn;

import java.sql.Timestamp;

/**
 * postinUrl配置 模型
 */
@ApiModel
@Mapper
@Join
public class WorkItemBind extends BaseModel {

    @ApiProperty(name="id",desc="id")
    private String id;

    @ApiProperty(name="workItemId",desc="workItemId")
    @Mappings({
            @Mapping(source = "workItem.id",target = "workItemId")
    })
    @JoinQuery(key = "id")
    private WorkItemTestOn workItem;

    @ApiProperty(name="caseId",desc="用例id")
    private String caseId;

    @ApiProperty(name="createTime",desc="创建时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Timestamp createTime;

    @ApiProperty(name="projectUrl",desc="teamwire服务端地址")
    private String projectUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public WorkItemTestOn getWorkItem() {
        return workItem;
    }

    public void setWorkItem(WorkItemTestOn workItem) {
        this.workItem = workItem;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getProjectUrl() {
        return projectUrl;
    }

    public void setProjectUrl(String projectUrl) {
        this.projectUrl = projectUrl;
    }
}
