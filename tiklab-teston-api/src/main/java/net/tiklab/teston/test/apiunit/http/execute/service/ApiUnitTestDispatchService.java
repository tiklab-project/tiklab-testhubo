package net.tiklab.teston.test.apiunit.http.execute.service;

import net.tiklab.teston.test.apiunit.http.instance.model.ApiUnitInstance;
import net.tiklab.teston.test.apiunit.http.execute.model.ApiUnitTestRequest;

/**
 * api单元用例测试
 */
public interface ApiUnitTestDispatchService {

    /**
     * 执行
     * @param apiUnitTestRequest
     * @return
     */
    ApiUnitInstance execute(ApiUnitTestRequest apiUnitTestRequest);



}
