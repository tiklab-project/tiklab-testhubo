package io.thoughtware.teston.test.web.scene.execute.service;

import io.thoughtware.teston.test.web.scene.execute.model.WebSceneTestRequest;
import io.thoughtware.teston.test.web.scene.execute.model.WebSceneTestResponse;

/**
 * web场景测试调度服务
 */
public interface WebSceneTestDispatchService {

    /**
     * 执行
     * @param webSceneTestRequest
     * @return
     */
    void execute(WebSceneTestRequest webSceneTestRequest);

    /**
     * 获取当前的执行后的数据
     * @return
     */
    WebSceneTestResponse result(WebSceneTestRequest webSceneTestRequest);

}
