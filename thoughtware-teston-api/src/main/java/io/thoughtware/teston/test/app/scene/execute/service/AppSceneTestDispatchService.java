package io.thoughtware.teston.test.app.scene.execute.service;

import io.thoughtware.teston.test.app.scene.execute.model.AppSceneTestRequest;
import io.thoughtware.teston.test.app.scene.execute.model.AppSceneTestResponse;

/**
 * app场景测试调度 服务接口
 */
public interface AppSceneTestDispatchService {

    /**
     * 执行
     * @param testRequest
     * @return
     */
    void execute(AppSceneTestRequest testRequest);

    /**
     * 获取当前的执行后的数据
     * @return
     */
    AppSceneTestResponse result(AppSceneTestRequest appSceneTestRequest);




    /**
     * 执行
     * 公共方法，testplan也需要用
     * @param appSceneTestRequest
     */
    void executeStart(AppSceneTestRequest appSceneTestRequest);

    /**
     * 获取当前的执行后的数据
     * 公共方法，testplan也需要用
     * @param appSceneTestRequest
     * @return
     */
    AppSceneTestResponse getResult(AppSceneTestRequest appSceneTestRequest);

    /**
     * 清理数据
     * @param appSceneId
     */
    void cleanUpData(String appSceneId);
}
