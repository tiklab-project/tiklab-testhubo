package io.tiklab.testhubo.statistics.model;

import io.tiklab.core.BaseModel;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.toolkit.join.annotation.Join;

import java.util.Date;

/**
 * 获取系统统计信息 模型
 */
@ApiModel
@Join
public class CaseStatisticsModel extends BaseModel {

    private String repositoryId;

    @ApiProperty(name="startTime",desc="开始时间")
    private Date startTime;

    @ApiProperty(name="endTime",desc="结束时间")
    private Date endTime;

    public String getRepositoryId() {
        return repositoryId;
    }

    public void setRepositoryId(String repositoryId) {
        this.repositoryId = repositoryId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
