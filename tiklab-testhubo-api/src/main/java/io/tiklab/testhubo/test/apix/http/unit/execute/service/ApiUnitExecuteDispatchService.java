package io.tiklab.testhubo.test.apix.http.unit.execute.service;


import io.tiklab.testhubo.test.apix.http.unit.instance.model.ApiUnitInstance;
import io.tiklab.testhubo.test.apix.http.unit.execute.model.ApiUnitTestRequest;

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

    ApiUnitInstance executeStart(ApiUnitTestRequest apiUnitTestRequest);

}
