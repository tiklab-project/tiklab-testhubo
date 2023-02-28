package net.tiklab.teston.apix.http.scene.execute.service;

import net.tiklab.teston.apix.http.scene.execute.model.ApiSceneTestRequest;
import net.tiklab.teston.apix.http.scene.execute.model.ApiSceneTestResponse;
import net.tiklab.teston.apix.http.unit.execute.model.ApiUnitTestRequest;

import java.util.List;

/**
 * api场景测试调度服务
 */
public interface ApiSceneTestDispatchService {

    /**
     * 测试
     * @return
     */
    ApiSceneTestResponse execute(ApiSceneTestRequest apiSceneTestRequest);

    /**
     * 数据构造
     * @param apiSceneTestRequest
     * @return
     */
    List<ApiUnitTestRequest> processApiSceneTestData(ApiSceneTestRequest apiSceneTestRequest);

}