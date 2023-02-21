package net.tiklab.teston.webtest.perftest.service;

import net.tiklab.teston.webtest.perftest.model.WebPerfTestRequest;
import net.tiklab.teston.webtest.perftest.model.WebPerfTestResponse;

/**
 * web性能测试调度服务
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
