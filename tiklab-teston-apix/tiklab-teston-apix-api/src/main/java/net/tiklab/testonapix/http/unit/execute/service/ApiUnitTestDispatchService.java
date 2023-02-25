package net.tiklab.testonapix.http.unit.execute.service;


import net.tiklab.testonapix.http.unit.execute.model.ApiUnitTestRequest;
import net.tiklab.testonapix.http.unit.instance.model.ApiUnitInstance;

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
