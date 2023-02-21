package net.tiklab.teston.webtest.scenetest.service;

import net.tiklab.teston.webtest.scenetest.model.WebSceneTestRequest;
import net.tiklab.teston.webtest.scenetest.model.WebSceneTestResponse;

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
