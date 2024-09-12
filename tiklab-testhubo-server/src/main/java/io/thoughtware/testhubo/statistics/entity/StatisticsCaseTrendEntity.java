package io.thoughtware.testhubo.statistics.entity;


import io.thoughtware.dal.jpa.annotation.*;

import java.io.Serializable;

/**
 * 用例趋势统计 实体
 */
@Entity
@Table(name="teston_statistic_case_trend")
public class StatisticsCaseTrendEntity implements Serializable {

    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id")
    private String id;

    @Column(name = "repository_id")
    private String repositoryId;

    @Column(name = "record_time")
    private String recordTime;

    @Column(name = "not_started")
    private Integer notStarted;

    @Column(name = "in_progress")
    private Integer inProgress;

    @Column(name = "completed")
    private Integer completed;

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

    public Integer getNotStarted() {
        return notStarted;
    }

    public void setNotStarted(Integer notStarted) {
        this.notStarted = notStarted;
    }

    public Integer getInProgress() {
        return inProgress;
    }

    public void setInProgress(Integer inProgress) {
        this.inProgress = inProgress;
    }

    public Integer getCompleted() {
        return completed;
    }

    public void setCompleted(Integer completed) {
        this.completed = completed;
    }
}
