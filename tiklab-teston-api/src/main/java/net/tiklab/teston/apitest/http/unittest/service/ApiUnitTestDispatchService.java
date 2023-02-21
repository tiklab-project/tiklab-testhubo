package net.tiklab.teston.apitest.http.unittest.service;

import net.tiklab.teston.apitest.http.unittest.model.ApiUnitInstance;
import net.tiklab.teston.apitest.http.unittest.model.ApiUnitTestRequest;

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
