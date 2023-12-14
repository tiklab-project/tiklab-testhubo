package io.thoughtware.teston.testplan.instance.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.thoughtware.beans.annotation.Mapper;
import io.thoughtware.beans.annotation.Mapping;
import io.thoughtware.beans.annotation.Mappings;
import io.thoughtware.core.BaseModel;
import io.thoughtware.join.annotation.Join;
import io.thoughtware.join.annotation.JoinQuery;
import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;
import io.thoughtware.teston.testplan.cases.model.TestPlan;

/**
 * 测试计划实例 模型
 */
@ApiModel
@Mapper
@Join
public class TestPlanInstance extends BaseModel {

    @ApiProperty(name="id",desc="id")
    private java.lang.String id;

    @ApiProperty(name="testPlan",desc="所属模块")
    @Mappings({
            @Mapping(source = "testPlan.id",target = "testPlanId")
    })
    @JoinQuery(key = "id")
    private TestPlan testPlan;

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

    public TestPlan getTestPlan() {
        return testPlan;
    }

    public void setTestPlan(TestPlan testPlan) {
        this.testPlan = testPlan;
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
