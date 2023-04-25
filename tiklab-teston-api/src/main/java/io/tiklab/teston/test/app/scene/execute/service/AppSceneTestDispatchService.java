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
    Integer execute(AppSceneTestRequest testRequest);

    /**
     * 获取当前执行的状态 0：未开始，1：进行中
     * @return
     */
    Integer status();

    /**
     * 获取当前的执行后的数据
     * @return
     */
    AppSceneTestResponse result(AppSceneTestRequest appSceneTestRequest);


}
