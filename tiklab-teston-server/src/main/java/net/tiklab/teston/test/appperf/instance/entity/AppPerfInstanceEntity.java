package net.tiklab.teston.test.appperf.instance.entity;

import net.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name="teston_app_perf_instance")
public class AppPerfInstanceEntity implements Serializable {
    @Id
    @GeneratorValue
    @Column(name = "id",length = 32)
    private String id;

    @Column(name = "app_perf_id")
    private String appPerfId;

    @Column(name = "total")
    private Integer total;

    @Column(name = "pass_num")
    private Integer passNum;

    @Column(name = "fail_num")
    private Integer failNum;

    @Column(name = "pass_rate")
    private String passRate;

    @Column(name = "error_rate")
    private String errorRate;

    @Column(name = "create_time")
    private Timestamp createTime;

    @Column(name = "result")
    private Integer result;

    @Column(name = "execute_number")
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

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPassNum() {
        return passNum;
    }

    public void setPassNum(Integer passNum) {
        this.passNum = passNum;
    }

    public Integer getFailNum() {
        return failNum;
    }

    public void setFailNum(Integer failNum) {
        this.failNum = failNum;
    }

    public String getPassRate() {
        return passRate;
    }

    public void setPassRate(String passRate) {
        this.passRate = passRate;
    }

    public String getErrorRate() {
        return errorRate;
    }

    public void setErrorRate(String errorRate) {
        this.errorRate = errorRate;
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
