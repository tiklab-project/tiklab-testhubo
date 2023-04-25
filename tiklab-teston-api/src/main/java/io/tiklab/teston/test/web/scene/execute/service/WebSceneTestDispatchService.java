package io.tiklab.teston.test.web.scene.execute.service;

import io.tiklab.teston.test.web.scene.execute.model.WebSceneTestRequest;
import io.tiklab.teston.test.web.scene.execute.model.WebSceneTestResponse;

/**
 * web场景测试调度服务
 */
public interface WebSceneTestDispatchService {

    /**
     * 执行
     * @param webSceneTestRequest
     * @return
     */
    Integer execute(WebSceneTestRequest webSceneTestRequest);

    /**
     * 获取当前执行的状态 0：未开始，1：进行中
     * @return
     */
    Integer status();

    /**
     * 获取当前的执行后的数据
     * @return
     */
    WebSceneTestResponse result(WebSceneTestRequest webSceneTestRequest);

}
