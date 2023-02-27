package net.tiklab.teston.apix.http.unit.execute.service;


import net.tiklab.teston.apix.http.unit.execute.model.ApiUnitTestRequest;
import net.tiklab.teston.apix.http.unit.instance.model.ApiUnitInstance;

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
