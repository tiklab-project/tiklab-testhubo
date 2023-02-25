package net.tiklab.teston.test.app.perf.cases.entity;

import net.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;

@Entity @Table(name="teston_app_perfcase")
public class AppPerfCaseEntity implements Serializable {
    @Id
    @GeneratorValue
    @Column(name = "id",length = 32)
    private String id;

    @Column(name = "thread_count")
    private Integer threadCount;

    @Column(name = "execute_count")
    private Integer executeCount;

    @Column(name = "execute_type")
    private Integer executeType;

    @Column(name = "testcase_id")
    private String testCaseId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getThreadCount() {
        return threadCount;
    }

    public void setThreadCount(Integer threadCount) {
        this.threadCount = threadCount;
    }

    public Integer getExecuteCount() {
        return executeCount;
    }

    public void setExecuteCount(Integer executeCount) {
        this.executeCount = executeCount;
    }

    public Integer getExecuteType() {
        return executeType;
    }

    public void setExecuteType(Integer executeType) {
        this.executeType = executeType;
    }

    public String getTestCaseId() {
        return testCaseId;
    }

    public void setTestCaseId(String testCaseId) {
        this.testCaseId = testCaseId;
    }
}
