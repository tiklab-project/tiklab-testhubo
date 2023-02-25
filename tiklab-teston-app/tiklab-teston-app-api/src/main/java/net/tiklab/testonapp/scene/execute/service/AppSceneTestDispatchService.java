package net.tiklab.testonapp.scene.execute.service;

import net.tiklab.testonapp.scene.execute.model.AppSceneTestRequest;
import net.tiklab.testonapp.scene.execute.model.AppSceneTestResponse;

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
