package io.tiklab.teston.test.web.scene.execute.service;

import io.tiklab.teston.test.web.scene.execute.model.WebSceneTestRequest;
import io.tiklab.teston.test.web.scene.execute.model.WebSceneTestResponse;

/**
 * web场景测试调度服务
 */
public interface WebSceneTestDispatchService {

    /**
     * 执行
     * @param testRequest
     * @return
     */
    WebSceneTestResponse execute(WebSceneTestRequest testRequest);

}
