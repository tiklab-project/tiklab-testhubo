package io.thoughtware.testrubo.test.apix.http.perf.cases.entity;

import io.thoughtware.dal.jpa.annotation.*;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 接口性能测试数据 实体
 */
@Entity @Table(name="teston_api_perf_testdata")
public class ApiPerfTestDataEntity implements Serializable {
    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id",length = 32)
    private String id;

    // 所属用例
    @Column(name = "name",length = 64)
    private String name;


    // 所属用例
    @Column(name = "step_id",length = 32)
    private String stepId;

    // 测试数据
    @Column(name = "test_data")
    private String testData;

    // 类型
    @Column(name = "type")
    private String type;

    // 创建时间
    @Column(name = "create_time")
    private Timestamp createTime;

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

    public String getStepId() {
        return stepId;
    }

    public void setStepId(String stepId) {
        this.stepId = stepId;
    }

    public String getTestData() {
        return testData;
    }

    public void setTestData(String testData) {
        this.testData = testData;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}
