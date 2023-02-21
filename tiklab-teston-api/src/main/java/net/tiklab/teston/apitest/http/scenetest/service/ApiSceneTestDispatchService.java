package net.tiklab.teston.apitest.http.scenetest.service;

import net.tiklab.teston.apitest.http.scenetest.model.ApiSceneTestRequest;
import net.tiklab.teston.apitest.http.scenetest.model.ApiSceneTestResponse;
import net.tiklab.teston.apitest.http.unittest.model.ApiUnitTestRequest;

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
