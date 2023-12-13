package io.thoughtware.teston.test.apix.http.scene.execute.service;

import io.thoughtware.teston.test.apix.http.scene.execute.model.ApiSceneTestRequest;
import io.thoughtware.teston.test.apix.http.scene.execute.model.ApiSceneTestResponse;

/**
 * 接口场景测试调度 服务接口
 */
public interface ApiSceneExecuteDispatchService {

    /**
     * 测试
     * @return
     */
    ApiSceneTestResponse execute(ApiSceneTestRequest apiSceneTestRequest);

}