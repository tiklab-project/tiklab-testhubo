package net.tiklab.teston.test.apix.http.perf.cases.model;

import net.tiklab.beans.annotation.Mapper;
import net.tiklab.beans.annotation.Mapping;
import net.tiklab.beans.annotation.Mappings;
import net.tiklab.core.BaseModel;
import net.tiklab.join.annotation.Join;
import net.tiklab.join.annotation.JoinQuery;
import net.tiklab.postin.annotation.ApiModel;
import net.tiklab.postin.annotation.ApiProperty;
import net.tiklab.teston.test.test.model.TestCase;

/**
 * 接口性能 模型
 */
@ApiModel
@Mapper(targetAlias = "ApiPerfCaseEntity")
@Join
public class ApiPerfCase extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private String id;

    @ApiProperty(name="testCase",desc="用例")
    @Mappings({
            @Mapping(source = "testCase.id",target = "testCaseId")
    })
    @JoinQuery(key = "id")
    private TestCase testCase;

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
