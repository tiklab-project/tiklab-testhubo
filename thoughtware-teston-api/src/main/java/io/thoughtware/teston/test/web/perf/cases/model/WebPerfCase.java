package io.thoughtware.teston.test.web.perf.cases.model;

import io.thoughtware.beans.annotation.Mapper;
import io.thoughtware.beans.annotation.Mapping;
import io.thoughtware.beans.annotation.Mappings;
import io.thoughtware.core.BaseModel;
import io.thoughtware.join.annotation.Join;
import io.thoughtware.join.annotation.JoinQuery;
import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;
import io.thoughtware.teston.test.test.model.TestCase;

/**
 * web性能用例 模型
 */
@ApiModel
@Mapper
@Join
public class WebPerfCase extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private String id;

    @ApiProperty(name="testCase",desc="所属用例",eg="@selectOne")
    @Mappings({
            @Mapping(source = "testCase.id",target = "testCaseId")
    })
    @JoinQuery(key = "id")
    private TestCase testCase;

    @ApiProperty(name="threadCount",desc="线程数")
    private Integer threadCount;

    @ApiProperty(name="executeType",desc="0:执行次数 1:执行时间")
    private Integer executeType;

    @ApiProperty(name="executeCount",desc="执行次数")
    private Integer executeCount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TestCase getTestCase() {
        return testCase;
    }

    public void setTestCase(TestCase testCase) {
        this.testCase = testCase;
    }

    public Integer getThreadCount() {
        return threadCount;
    }

    public void setThreadCount(Integer threadCount) {
        this.threadCount = threadCount;
    }

    public Integer getExecuteType() {
        return executeType;
    }

    public void setExecuteType(Integer executeType) {
        this.executeType = executeType;
    }

    public Integer getExecuteCount() {
        return executeCount;
    }

    public void setExecuteCount(Integer executeCount) {
        this.executeCount = executeCount;
    }
}
