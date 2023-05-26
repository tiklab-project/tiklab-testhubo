package io.tiklab.teston.integrated.teamwire.workItem.model;


import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.tiklab.core.BaseModel;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;

import java.sql.Date;

/**
 * postinUrl配置 模型
 */
@ApiModel
public class WorkItemTestOn extends BaseModel {

    @ApiProperty(name="id",desc="id")
    private String id;

    @ApiProperty(name="projectName",desc="项目名称")
    private String name;

    @ApiProperty(name="projectId",desc="项目名称")
    private String projectId;

    @ApiProperty(name = "planBeginTime", desc = "计划开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date planBeginTime;

    @ApiProperty(name = "planEndTime", desc = "计划结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date planEndTime;

    @ApiProperty(name="caseId",desc="获取当前用例id")
    private String caseId;

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

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public Date getPlanBeginTime() {
        return planBeginTime;
    }

    public void setPlanBeginTime(Date planBeginTime) {
        this.planBeginTime = planBeginTime;
    }

    public Date getPlanEndTime() {
        return planEndTime;
    }

    public void setPlanEndTime(Date planEndTime) {
        this.planEndTime = planEndTime;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }
}
