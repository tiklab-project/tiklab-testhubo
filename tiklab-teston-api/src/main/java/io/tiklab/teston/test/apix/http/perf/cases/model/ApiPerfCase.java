package io.tiklab.teston.test.apix.http.perf.cases.model;

import io.tiklab.beans.annotation.Mapper;
import io.tiklab.beans.annotation.Mapping;
import io.tiklab.beans.annotation.Mappings;
import io.tiklab.core.BaseModel;
import io.tiklab.join.annotation.Join;
import io.tiklab.join.annotation.JoinQuery;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.teston.test.test.model.TestCases;

/**
 * 接口性能 模型
 */
@ApiModel
@Mapper
@Join
public class ApiPerfCase extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private String id;

    @ApiProperty(name="testCases",desc="用例")
    @Mappings({
            @Mapping(source = "testCase.id",target = "testCaseId")
    })
    @JoinQuery(key = "id")
    private TestCases testCase;

    @ApiProperty(name="threadCount",desc="线程数")
    private Integer threadCount;

    @ApiProperty(name="executeCount",desc="执行数")
    private Integer executeCount;

    @ApiProperty(name="executeType",desc="1:循环 2:随机")
    private Integer executeType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TestCases getTestCase() {
        return testCase;
    }

    public void setTestCase(TestCases testCase) {
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
