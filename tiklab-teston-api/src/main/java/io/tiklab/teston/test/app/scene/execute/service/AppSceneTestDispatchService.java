package io.tiklab.teston.test.app.scene.execute.service;

import io.tiklab.teston.test.app.scene.execute.model.AppSceneTestRequest;
import io.tiklab.teston.test.app.scene.execute.model.AppSceneTestResponse;

/**
 * app场景测试调度 服务接口
 */
public interface AppSceneTestDispatchService {

    /**
     * 执行
     * @param testRequest
     * @return
     */
    AppSceneTestResponse execute(AppSceneTestRequest testRequest);



}
