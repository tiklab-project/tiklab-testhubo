package io.tiklab.teston.test.web.perf.cases.entity;

import io.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;

/**
 * web性能用例 实体
 */
@Entity @Table(name="teston_web_perfcase")
public class WebPerfCaseEntity implements Serializable {
    @Id
     @GeneratorValue(length = 12)
    @Column(name = "id",length = 32)
    private String id;

    // 线程数
    @Column(name = "thread_count")
    private Integer threadCount;

    // 执行次数
    @Column(name = "execute_count")
    private Integer executeCount;

    // 执行类型 0:执行次数 1:执行时间
    @Column(name = "execute_type")
    private Integer executeType;

    // 所属用例
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

    public String getTestCaseId() {
        return testCaseId;
    }

    public void setTestCaseId(String testCaseId) {
        this.testCaseId = testCaseId;
    }

    public Integer getExecuteType() {
        return executeType;
    }

    public void setExecuteType(Integer executeType) {
        this.executeType = executeType;
    }
}
