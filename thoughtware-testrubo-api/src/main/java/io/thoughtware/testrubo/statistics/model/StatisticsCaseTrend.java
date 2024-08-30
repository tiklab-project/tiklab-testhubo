package io.thoughtware.testrubo.statistics.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.thoughtware.core.BaseModel;
import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.toolkit.beans.annotation.Mapper;
import io.thoughtware.toolkit.join.annotation.Join;

/**
 * 用例趋势统计 模型
 */
@ApiModel
@Mapper(targetName = "io.thoughtware.testrubo.statistics.entity.StatisticsCaseTrendEntity")
public class StatisticsCaseTrend extends BaseModel {

    private String id;
    private String repositoryId;

    private String recordTime;

    private String notStarted;

    private Integer inProgress;

    private String completed;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRepositoryId() {
        return repositoryId;
    }

    public void setRepositoryId(String repositoryId) {
        this.repositoryId = repositoryId;
    }

    public String getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(String recordTime) {
        this.recordTime = recordTime;
    }

    public String getNotStarted() {
        return notStarted;
    }

    public void setNotStarted(String notStarted) {
        this.notStarted = notStarted;
    }

    public Integer getInProgress() {
        return inProgress;
    }

    public void setInProgress(Integer inProgress) {
        this.inProgress = inProgress;
    }

    public String getCompleted() {
        return completed;
    }

    public void setCompleted(String completed) {
        this.completed = completed;
    }
}
