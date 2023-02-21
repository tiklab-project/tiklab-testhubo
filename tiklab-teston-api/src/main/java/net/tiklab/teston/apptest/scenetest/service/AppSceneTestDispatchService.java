package net.tiklab.teston.apptest.scenetest.service;

import net.tiklab.teston.apptest.scenetest.model.AppSceneTestRequest;
import net.tiklab.teston.apptest.scenetest.model.AppSceneTestResponse;

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
