package io.tiklab.teston.test.apix.http.perf.execute.service;


import io.tiklab.teston.test.apix.http.perf.execute.model.ApiPerfTestRequest;
import io.tiklab.teston.test.apix.http.perf.execute.model.ApiPerfTestResponse;

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


    /**
     * 获取当前执行的状态 0：未开始，1：进行中
     * @return
     */
    Integer status();


    /**
     * 停止性能测试
     * @param
     * @return
     */
    void stop(ApiPerfTestRequest apiPerfTestRequest);

}
