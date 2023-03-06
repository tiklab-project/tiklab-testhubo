package net.tiklab.teston.test.web.perf.cases.model;

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
 * web性能用例 模型
 */
@ApiModel
@Mapper(targetAlias = "WebPerfCaseEntity")
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
