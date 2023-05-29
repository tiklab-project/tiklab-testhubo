package io.tiklab.teston.test.web.perf.execute.service;

import io.tiklab.teston.test.web.perf.execute.model.WebPerfTestRequest;
import io.tiklab.teston.test.web.perf.execute.model.WebPerfTestResponse;

/**
 * web性能测试调度 服务接口
 */
public interface WebPerfTestDispatchService {

    /**
     * 执行
     * @param
     * @return
     */
    void execute(WebPerfTestRequest webPerfTestRequest);

    /**
     * 获取性能测试结果
     * @param
     * @return
     */
    WebPerfTestResponse exeResult(WebPerfTestRequest webPerfTestRequest);

}