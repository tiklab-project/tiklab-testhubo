package io.thoughtware.teston.test.apix.http.perf.execute.service;


import com.alibaba.fastjson.JSONObject;
import io.thoughtware.teston.test.apix.http.perf.execute.model.ApiPerfTestRequest;
import io.thoughtware.teston.test.apix.http.perf.execute.model.ApiPerfTestResponse;

/**
 * api性能测试调度服务
 */
public interface ApiPerfExecuteDispatchService {

    /**
     * 性能执行
     * @param
     * @param apiPerfTestRequest
     * @return
     */
    void execute(ApiPerfTestRequest apiPerfTestRequest);

    /**
     * 获取性能测试结果
     * @param
     * @return
     */
    ApiPerfTestResponse result(ApiPerfTestRequest apiPerfAgentTestRequest);

    void executeStart(ApiPerfTestRequest apiPerfTestRequest);

    ApiPerfTestResponse getResult(ApiPerfTestRequest apiPerfTestRequest);

    /**
     * 清理数据
     */
    void cleanUpData(String apiPerfId);

    /**
     * agent定时上报的数据
     * @param message
     */
    void getAgentTestData(JSONObject message);

}
