package net.tiklab.teston.test.appperf.cases.model;

import net.tiklab.postin.annotation.ApiModel;
import net.tiklab.postin.annotation.ApiProperty;
import net.tiklab.beans.annotation.Mapper;
import net.tiklab.beans.annotation.Mapping;
import net.tiklab.beans.annotation.Mappings;
import net.tiklab.core.BaseModel;
import net.tiklab.join.annotation.Join;
import net.tiklab.join.annotation.JoinQuery;
import net.tiklab.teston.test.testcase.model.TestCase;


@ApiModel
@Mapper(targetAlias = "AppPerfCaseEntity")
@Join
public class AppPerfCase extends BaseModel {

    @ApiProperty(name="id",desc="id")
    private java.lang.String id;

    @ApiProperty(name="threadCount",desc="threadCount")
    private java.lang.Integer threadCount;

    @ApiProperty(name="executeCount",desc="executeCount")
    private java.lang.Integer executeCount;

    @ApiProperty(name="executeType",desc="executeType")
    private Integer executeType;

    @ApiProperty(name="testCase",desc="用例",eg="@selectOne")
    @Mappings({
            @Mapping(source = "testCase.id",target = "testCaseId")
    })
    @JoinQuery(key = "id")
    private TestCase testCase;

    public java.lang.String getId() {
        return id;
    }

    public void setId(java.lang.String id) {
        this.id = id;
    }
    public java.lang.Integer getThreadCount() {
        return threadCount;
    }

    public void setThreadCount(java.lang.Integer threadCount) {
        this.threadCount = threadCount;
    }
    public java.lang.Integer getExecuteCount() {
        return executeCount;
    }

    public void setExecuteCount(java.lang.Integer executeCount) {
        this.executeCount = executeCount;
    }

    public Integer getExecuteType() {
        return executeType;
    }

    public void setExecuteType(Integer executeType) {
        this.executeType = executeType;
    }

    public TestCase getTestCase() {
        return testCase;
    }

    public void setTestCase(TestCase testCase) {
        this.testCase = testCase;
    }
}
