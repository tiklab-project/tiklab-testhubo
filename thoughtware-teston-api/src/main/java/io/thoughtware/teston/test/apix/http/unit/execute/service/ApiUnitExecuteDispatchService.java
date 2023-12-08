package io.thoughtware.teston.test.apix.http.unit.execute.service;


import io.thoughtware.teston.test.apix.http.unit.instance.model.ApiUnitInstance;
import io.thoughtware.teston.test.apix.http.unit.execute.model.ApiUnitTestRequest;

/**
 * api单元用例测试 服务接口
 */
public interface ApiUnitExecuteDispatchService {

    /**
     * 执行
     * @param apiUnitTestRequest
     * @return
     */
    ApiUnitInstance execute(ApiUnitTestRequest apiUnitTestRequest);



}
