package io.tiklab.teston.testplan.cases.model;

import io.tiklab.teston.test.test.model.TestCases;
import io.tiklab.beans.annotation.Mapper;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.beans.annotation.Mapping;
import io.tiklab.beans.annotation.Mappings;
import io.tiklab.core.BaseModel;
import io.tiklab.join.annotation.Join;
import io.tiklab.join.annotation.JoinQuery;

/**
 * 测试计划绑定的用例 模型
 */
@ApiModel
@Mapper
@Join
public class TestPlanCase extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private java.lang.String id;

    @ApiProperty(name="repositoryId",desc="repositoryId")
    private String repositoryId;

    @ApiProperty(name="testPlan",desc="测试计划")
    @Mappings({
            @Mapping(source = "testPlan.id",target = "testPlanId")
    })
    @JoinQuery(key = "id")
    private TestPlan testPlan;

    @ApiProperty(name="testCase",desc="用例id")
    @Mappings({
            @Mapping(source = "testCase.id",target = "testCaseId")
    })
    @JoinQuery(key = "id")
    private TestCases testCase;

    @ApiProperty(name="status",desc="状态:0 失败  1 通过  2未执行")
    private java.lang.Integer status;

    @ApiProperty(name="sort",desc="排序")
    private java.lang.Integer sort;

    public java.lang.String getId() {
        return id;
    }

    public void setId(java.lang.String id) {
        this.id = id;
    }

    public String getRepositoryId() {
        return repositoryId;
    }

    public void setRepositoryId(String repositoryId) {
        this.repositoryId = repositoryId;
    }

    public TestPlan getTestPlan() {
        return testPlan;
    }

    public void setTestPlan(TestPlan testPlan) {
        this.testPlan = testPlan;
    }

    public TestCases getTestCase() {
        return testCase;
    }

    public void setTestCase(TestCases testCase) {
        this.testCase = testCase;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public java.lang.Integer getSort() {
        return sort;
    }

    public void setSort(java.lang.Integer sort) {
        this.sort = sort;
    }
}
