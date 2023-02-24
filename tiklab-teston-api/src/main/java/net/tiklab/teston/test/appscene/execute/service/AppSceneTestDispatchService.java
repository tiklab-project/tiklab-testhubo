package net.tiklab.teston.test.appscene.execute.service;

import net.tiklab.teston.test.appscene.execute.model.AppSceneTestRequest;
import net.tiklab.teston.test.appscene.execute.model.AppSceneTestResponse;

/**
 * app场景测试调度服务
 */
public interface AppSceneTestDispatchService {

    /**
     * 执行
     * @param testRequest
     * @return
     */
    AppSceneTestResponse execute(AppSceneTestRequest testRequest);



}
