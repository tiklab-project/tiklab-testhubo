package net.tiklab.teston.webtest.perftest.model;

import net.tiklab.postin.annotation.ApiModel;
import net.tiklab.postin.annotation.ApiProperty;
import net.tiklab.beans.annotation.Mapper;
import net.tiklab.core.BaseModel;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

@ApiModel
@Mapper(targetAlias = "WebPerfInstanceEntity")
public class WebPerfInstance extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private String id;

    @ApiProperty(name="webPerfId",desc="性能测试表id")
    private java.lang.String webPerfId;

    @ApiProperty(name="passRate",desc="通过率")
    private java.lang.String passRate;

    @ApiProperty(name="passNum",desc="通过数")
    private Integer passNum;

    @ApiProperty(name="errorRate",desc="错误率")
    private  java.lang.String  errorRate;

    @ApiProperty(name="failNum",desc="错误数")
    private Integer failNum;

    @ApiProperty(name="total",desc="测试总次数")
    private Integer total;

    @ApiProperty(name="result",desc="结果")
    private Integer result;

    @ApiProperty(name="createTime",desc="createTime")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private java.sql.Timestamp createTime;

    @ApiProperty(name="executeNumber",desc="executeNumber")
    private java.lang.Integer executeNumber;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWebPerfId() {
        return webPerfId;
    }

    public void setWebPerfId(String webPerfId) {
        this.webPerfId = webPerfId;
    }

    public String getPassRate() {
        return passRate;
    }

    public void setPassRate(String passRate) {
        this.passRate = passRate;
    }

    public Integer getPassNum() {
        return passNum;
    }

    public void setPassNum(Integer passNum) {
        this.passNum = passNum;
    }

    public String getErrorRate() {
        return errorRate;
    }

    public void setErrorRate(String errorRate) {
        this.errorRate = errorRate;
    }

    public Integer getFailNum() {
        return failNum;
    }

    public void setFailNum(Integer failNum) {
        this.failNum = failNum;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }


    public Integer getExecuteNumber() {
        return executeNumber;
    }

    public void setExecuteNumber(Integer executeNumber) {
        this.executeNumber = executeNumber;
    }
}
