package io.tiklab.teston.test.apix.http.perf.instance.entity;

import io.tiklab.dal.jpa.annotation.Column;
import io.tiklab.dal.jpa.annotation.GeneratorValue;
import io.tiklab.dal.jpa.annotation.Id;
import io.tiklab.dal.jpa.annotation.Table;import io.tiklab.dal.jpa.annotation.Entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 接口性能历史实例 实体
 */
@Entity
@Table(name="teston_api_perf_instance")
public class ApiPerfInstanceEntity implements Serializable {
    @Id
    @GeneratorValue
    @Column(name = "id",length = 32)
    private String id;

    // 所属接口性能用例
    @Column(name = "api_perf_id")
    private String apiPerfId;

    // 测试总次数
    @Column(name = "total")
    private Integer total;

    // 通过数
    @Column(name = "pass_num")
    private Integer passNum;

    // 错误数
    @Column(name = "fail_num")
    private Integer failNum;

    // 通过数
    @Column(name = "pass_rate")
    private String passRate;

    // 错误率
    @Column(name = "error_rate")
    private String errorRate;

    // 创建时间
    @Column(name = "create_time")
    private Timestamp createTime;

    // 结果
    @Column(name = "result")
    private String result;

    // 执行次数
    @Column(name = "execute_number")
    private Integer executeNumber;


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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApiPerfId() {
        return apiPerfId;
    }

    public void setApiPerfId(String apiPerfId) {
        this.apiPerfId = apiPerfId;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Integer getExecuteNumber() {
        return executeNumber;
    }

    public void setExecuteNumber(Integer executeNumber) {
        this.executeNumber = executeNumber;
    }
}
