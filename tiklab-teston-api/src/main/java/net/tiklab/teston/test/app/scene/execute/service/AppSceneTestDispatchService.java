package net.tiklab.teston.test.app.scene.execute.service;

import net.tiklab.teston.test.app.scene.execute.model.AppSceneTestRequest;
import net.tiklab.teston.test.app.scene.execute.model.AppSceneTestResponse;

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
