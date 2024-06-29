package io.thoughtware.teston.test.apix.http.perf.instance.model;

import io.thoughtware.core.BaseModel;
import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.teston.test.apix.http.scene.execute.model.ApiSceneTestResponse;
import io.thoughtware.teston.test.apix.http.unit.instance.model.ApiUnitInstance;
import io.thoughtware.toolkit.beans.annotation.Mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 接口性能关联的用例历史实例 模型
 */
@ApiModel
@Mapper
public class ApiPerfStepInstance extends BaseModel {
    private String id;
    private String apiPerfInstanceId;
    private int totalRequests;
    private int successfulRequests;
    private int failedRequests;
    private double avgElapsedTime;
    private double maxElapsedTime;
    private double minElapsedTime;
    private double totalElapsedTime;
    private double tps;
    private double errorRate;
    private double percentile90;
    private double percentile95;
    private double percentile99;
    private List<Double> elapsedTimes;

    public ApiPerfStepInstance() {
        this.successfulRequests = 0;
        this.failedRequests = 0;
        this.totalElapsedTime = 0.0;
        this.maxElapsedTime = Double.MIN_VALUE;
        this.minElapsedTime = Double.MAX_VALUE;
        this.avgElapsedTime = 0.0;
        this.tps = 0.0;
        this.errorRate = 0.0;
        this.percentile90 = 0.0;
        this.percentile95 = 0.0;
        this.percentile99 = 0.0;
        this.elapsedTimes = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApiPerfInstanceId() {
        return apiPerfInstanceId;
    }

    public void setApiPerfInstanceId(String apiPerfInstanceId) {
        this.apiPerfInstanceId = apiPerfInstanceId;
    }

    public int getTotalRequests() {
        return totalRequests;
    }

    public void setTotalRequests(int totalRequests) {
        this.totalRequests = totalRequests;
    }

    public int getSuccessfulRequests() {
        return successfulRequests;
    }

    public void setSuccessfulRequests(int successfulRequests) {
        this.successfulRequests = successfulRequests;
    }

    public int getFailedRequests() {
        return failedRequests;
    }

    public void setFailedRequests(int failedRequests) {
        this.failedRequests = failedRequests;
    }

    public double getAvgElapsedTime() {
        return avgElapsedTime;
    }

    public void setAvgElapsedTime(double avgElapsedTime) {
        this.avgElapsedTime = avgElapsedTime;
    }

    public double getMaxElapsedTime() {
        return maxElapsedTime;
    }

    public void setMaxElapsedTime(double maxElapsedTime) {
        this.maxElapsedTime = maxElapsedTime;
    }

    public double getMinElapsedTime() {
        return minElapsedTime;
    }

    public void setMinElapsedTime(double minElapsedTime) {
        this.minElapsedTime = minElapsedTime;
    }

    public double getTotalElapsedTime() {
        return totalElapsedTime;
    }

    public void setTotalElapsedTime(double totalElapsedTime) {
        this.totalElapsedTime = totalElapsedTime;
    }

    public double getTps() {
        return tps;
    }

    public void setTps(double tps) {
        this.tps = tps;
    }

    public double getErrorRate() {
        return errorRate;
    }

    public void setErrorRate(double errorRate) {
        this.errorRate = errorRate;
    }

    public double getPercentile90() {
        return percentile90;
    }

    public void setPercentile90(double percentile90) {
        this.percentile90 = percentile90;
    }

    public double getPercentile95() {
        return percentile95;
    }

    public void setPercentile95(double percentile95) {
        this.percentile95 = percentile95;
    }

    public double getPercentile99() {
        return percentile99;
    }

    public void setPercentile99(double percentile99) {
        this.percentile99 = percentile99;
    }

    public List<Double> getElapsedTimes() {
        return elapsedTimes;
    }

    public void setElapsedTimes(List<Double> elapsedTimes) {
        this.elapsedTimes = elapsedTimes;
    }

    // 计算 平均耗时
    public void calculateAvgElapsedTime() {
        int totalRequests = successfulRequests + failedRequests;
        if (totalRequests > 0) {
            this.avgElapsedTime = this.totalElapsedTime / totalRequests;
        }
    }

    // 计算 TPS
    public void calculateTps(double durationInSeconds) {
        if (durationInSeconds > 0) {
            this.tps = this.successfulRequests / durationInSeconds;
        }
    }

    // 计算 错误率
    public void calculateErrorRate() {
        int totalRequests = successfulRequests + failedRequests;
        if (totalRequests > 0) {
            this.errorRate = (double) this.failedRequests / totalRequests;
        }
    }

    // 计算 百分位响应时间
    public void calculatePercentiles() {
        Collections.sort(elapsedTimes);
        int size = elapsedTimes.size();
        if (size > 0) {
            this.percentile90 = elapsedTimes.get((int) (size * 0.9) - 1);
            this.percentile95 = elapsedTimes.get((int) (size * 0.95) - 1);
            this.percentile99 = elapsedTimes.get((int) (size * 0.99) - 1);
        }
    }

    public void addElapsedTime(double elapsedTime) {
        this.elapsedTimes.add(elapsedTime);
    }
}
