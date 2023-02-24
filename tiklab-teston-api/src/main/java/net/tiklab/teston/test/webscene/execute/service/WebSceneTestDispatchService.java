package net.tiklab.teston.test.webscene.execute.service;

import net.tiklab.teston.test.webscene.execute.model.WebSceneTestRequest;
import net.tiklab.teston.test.webscene.execute.model.WebSceneTestResponse;

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
