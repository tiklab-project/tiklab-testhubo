package net.tiklab.teston.test.app.perf.instance.mode;

import com.fasterxml.jackson.annotation.JsonFormat;
import net.tiklab.beans.annotation.Mapper;
import net.tiklab.core.BaseModel;
import net.tiklab.postin.annotation.ApiModel;
import net.tiklab.postin.annotation.ApiProperty;

import java.sql.Timestamp;

/**
 * app性能实例 模型
 */
@ApiModel
@Mapper(targetAlias = "AppPerfInstanceEntity")
public class AppPerfInstance extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private String id;

    @ApiProperty(name="appPerfId",desc="性能测试id")
    private String appPerfId;

    @ApiProperty(name="passRate",desc="通过率")
    private String passRate;

    @ApiProperty(name="passNum",desc="通过数")
    private Integer passNum;

    @ApiProperty(name="errorRate",desc="错误率")
    private  String  errorRate;

    @ApiProperty(name="failNum",desc="错误数")
    private Integer failNum;

    @ApiProperty(name="total",desc="测试总次数")
    private Integer total;

    @ApiProperty(name="createTime",desc="创建时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Timestamp createTime;


    @ApiProperty(name="result",desc="结果")
    private Integer result;

    @ApiProperty(name="executeNumber",desc="执行次数")
    private Integer executeNumber;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getAppPerfId() {
        return appPerfId;
    }

    public void setAppPerfId(String appPerfId) {
        this.appPerfId = appPerfId;
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
