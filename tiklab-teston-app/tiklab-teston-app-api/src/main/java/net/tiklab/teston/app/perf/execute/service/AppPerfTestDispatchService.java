package net.tiklab.teston.app.perf.execute.service;


import net.tiklab.teston.app.perf.execute.mode.AppPerfTestRequest;
import net.tiklab.teston.app.perf.execute.mode.AppPerfTestResponse;

/**
 * app性能测试调度 服务接口
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
