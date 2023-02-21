package net.tiklab.teston.apptest.perftest.service;

import net.tiklab.teston.apptest.perftest.model.AppPerfTestRequest;
import net.tiklab.teston.apptest.perftest.model.AppPerfTestResponse;

/**
 * app性能测试调度服务
 */
public interface AppPerfTestDispatchService {

    /**
     * 性能执行
     * @param
     * @return
     */
    void execute(AppPerfTestRequest appPerfTestRequest);

    /**
     * 获取性能测试结果
     * @param
     * @return
     */
    AppPerfTestResponse exeResult(AppPerfTestRequest appPerfTestRequest);

}
