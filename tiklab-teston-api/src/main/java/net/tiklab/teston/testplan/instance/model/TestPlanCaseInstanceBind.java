package net.tiklab.teston.testplan.instance.model;

import net.tiklab.beans.annotation.Mapper;
import net.tiklab.core.BaseModel;
import net.tiklab.postin.annotation.ApiModel;
import net.tiklab.postin.annotation.ApiProperty;

/**
 * 测试计划下用例的实例的中间层 模型
 */
@ApiModel
@Mapper(targetAlias = "TestPlanCaseInstanceBindEntity")
public class TestPlanCaseInstanceBind extends BaseModel {

    @ApiProperty(name="id",desc="id")
    private java.lang.String id;

    @ApiProperty(name="testPlanInstanceId",desc="所属测试计划实例")
    private java.lang.String testPlanInstanceId;

    @ApiProperty(name="caseInstanceId",desc="用例实例id")
    private java.lang.String caseInstanceId;

    @ApiProperty(name="name",desc="名称")
    private java.lang.String name;

    @ApiProperty(name="testType",desc="测试类型")
    private java.lang.String testType;

    @ApiProperty(name="caseType",desc="用例类型")
    private java.lang.String caseType;

    @ApiProperty(name="result",desc="结果")
    private java.lang.Integer result;

    public java.lang.String getId() {
        return id;
    }

    public void setId(java.lang.String id) {
        this.id = id;
    }
    public java.lang.String getTestPlanInstanceId() {
        return testPlanInstanceId;
    }

    public void setTestPlanInstanceId(java.lang.String testPlanInstanceId) {
        this.testPlanInstanceId = testPlanInstanceId;
    }
    public java.lang.String getCaseInstanceId() {
        return caseInstanceId;
    }

    public void setCaseInstanceId(java.lang.String caseInstanceId) {
        this.caseInstanceId = caseInstanceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public java.lang.String getTestType() {
        return testType;
    }

    public void setTestType(java.lang.String testType) {
        this.testType = testType;
    }
    public java.lang.String getCaseType() {
        return caseType;
    }

    public void setCaseType(java.lang.String caseType) {
        this.caseType = caseType;
    }
    public java.lang.Integer getResult() {
        return result;
    }

    public void setResult(java.lang.Integer result) {
        this.result = result;
    }
}
