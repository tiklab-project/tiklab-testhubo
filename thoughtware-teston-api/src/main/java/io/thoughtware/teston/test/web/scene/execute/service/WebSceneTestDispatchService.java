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


    void executeStart(WebSceneTestRequest webSceneTestRequest);

    /**
     * 获取当前的执行后的数据
     * @return
     */
    WebSceneTestResponse result(WebSceneTestRequest webSceneTestRequest);

    WebSceneTestResponse getResult(WebSceneTestRequest webSceneTestRequest);

    /**
     *  创建web实例
     * @param belongId
     * @return
     */
    String createInitWebInstance(String belongId);


    void updateWebInstance(WebSceneTestResponse webSceneTestResponse, String webSceneId);



    /**
     * 清楚数据
     * @param webSceneId
     */
    void cleanUpData(String webSceneId);

}
