package io.tiklab.teston.testplan.instance.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.tiklab.beans.annotation.Mapper;
import io.tiklab.core.BaseModel;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;

/**
 * 测试计划实例 模型
 */
@ApiModel
@Mapper(targetAlias = "TestPlanInstanceEntity")
public class TestPlanInstance extends BaseModel {

    @ApiProperty(name="id",desc="id")
    private java.lang.String id;

    @ApiProperty(name="testPlanId",desc="所属计划")
    private java.lang.String testPlanId;

    @ApiProperty(name="repositoryId",desc="所属仓库")
    private java.lang.String repositoryId;

    @ApiProperty(name="executeNumber",desc="执行次数")
    private java.lang.Integer executeNumber;

    @ApiProperty(name="result",desc="结果")
    private java.lang.Integer result;

    @ApiProperty(name="total",desc="测试总次数")
    private java.lang.Integer total;

    @ApiProperty(name="passNum",desc="通过数")
    private java.lang.Integer passNum;

    @ApiProperty(name="failNum",desc="错误数")
    private java.lang.Integer failNum;

    @ApiProperty(name="passRate",desc="通过率")
    private java.lang.String passRate;

    @ApiProperty(name="errorRate",desc="错误率")
    private java.lang.String errorRate;

    @ApiProperty(name="createTime",desc="创建时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private java.sql.Timestamp createTime;

    @ApiProperty(name="createUser",desc="执行人")
    private java.lang.String createUser;

    private TestPlanCaseInstanceBind bindCase;

    public java.lang.String getId() {
        return id;
    }

    public void setId(java.lang.String id) {
        this.id = id;
    }
    public java.lang.String getTestPlanId() {
        return testPlanId;
    }

    public void setTestPlanId(java.lang.String testPlanId) {
        this.testPlanId = testPlanId;
    }

    public String getRepositoryId() {
        return repositoryId;
    }

    public void setRepositoryId(String repositoryId) {
        this.repositoryId = repositoryId;
    }

    public Integer getExecuteNumber() {
        return executeNumber;
    }

    public void setExecuteNumber(Integer executeNumber) {
        this.executeNumber = executeNumber;
    }

    public java.lang.Integer getResult() {
        return result;
    }

    public void setResult(java.lang.Integer result) {
        this.result = result;
    }
    public java.lang.Integer getTotal() {
        return total;
    }

    public void setTotal(java.lang.Integer total) {
        this.total = total;
    }
    public java.lang.Integer getPassNum() {
        return passNum;
    }

    public void setPassNum(java.lang.Integer passNum) {
        this.passNum = passNum;
    }
    public java.lang.Integer getFailNum() {
        return failNum;
    }

    public void setFailNum(java.lang.Integer failNum) {
        this.failNum = failNum;
    }
    public java.lang.String getPassRate() {
        return passRate;
    }

    public void setPassRate(java.lang.String passRate) {
        this.passRate = passRate;
    }
    public java.lang.String getErrorRate() {
        return errorRate;
    }

    public void setErrorRate(java.lang.String errorRate) {
        this.errorRate = errorRate;
    }
    public java.sql.Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(java.sql.Timestamp createTime) {
        this.createTime = createTime;
    }
    public java.lang.String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(java.lang.String createUser) {
        this.createUser = createUser;
    }

    public TestPlanCaseInstanceBind getBindCase() {
        return bindCase;
    }

    public void setBindCase(TestPlanCaseInstanceBind bindCase) {
        this.bindCase = bindCase;
    }
}