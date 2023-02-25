package net.tiklab.testonapix.http.perf.execute.service;


import net.tiklab.testonapix.http.perf.execute.model.ApiPerfTestRequest;
import net.tiklab.testonapix.http.perf.execute.model.ApiPerfTestResponse;

/**
 * api性能测试调度服务
 */
public interface ApiPerfTestDispatchService {

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
    ApiPerfTestResponse exeResult(ApiPerfTestRequest apiPerfAgentTestRequest);



    /**
     * 停止性能测试
     * @param
     * @return
     */
    void stop(ApiPerfTestRequest apiPerfTestRequest);

}
