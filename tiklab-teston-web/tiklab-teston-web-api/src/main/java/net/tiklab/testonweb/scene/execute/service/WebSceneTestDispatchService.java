package net.tiklab.testonweb.scene.execute.service;

import net.tiklab.testonweb.scene.execute.model.WebSceneTestRequest;
import net.tiklab.testonweb.scene.execute.model.WebSceneTestResponse;

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
