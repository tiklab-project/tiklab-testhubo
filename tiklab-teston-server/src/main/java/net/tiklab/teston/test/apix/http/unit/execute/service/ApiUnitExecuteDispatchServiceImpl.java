package net.tiklab.teston.test.apix.http.unit.execute.service;

import net.tiklab.rpc.client.router.lookup.FixedLookup;
import net.tiklab.teston.agent.api.http.unit.ApiUnitTestService;
import net.tiklab.teston.test.apix.http.unit.cases.model.ApiUnitCase;
import net.tiklab.teston.test.apix.http.unit.cases.model.ApiUnitCaseExt;
import net.tiklab.teston.test.apix.http.unit.cases.service.ApiUnitCaseService;
import net.tiklab.teston.test.apix.http.unit.cases.service.AssertService;
import net.tiklab.teston.test.apix.http.unit.execute.model.ApiUnitTestRequest;
import net.tiklab.teston.test.apix.http.unit.instance.model.ApiUnitInstance;
import net.tiklab.teston.test.apix.http.unit.instance.model.ApiUnitInstanceBind;
import net.tiklab.teston.test.apix.http.unit.instance.model.ApiUnitInstanceQuery;
import net.tiklab.teston.test.apix.http.unit.instance.service.ApiUnitInstanceBindService;
import net.tiklab.teston.test.apix.http.unit.instance.service.ApiUnitInstanceService;
import net.tiklab.teston.test.apix.http.unit.instance.service.AssertInstanceService;
import net.tiklab.teston.support.utils.RpcClientApixUtil;
import net.tiklab.teston.support.agentconfig.model.AgentConfig;
import net.tiklab.teston.support.agentconfig.service.AgentConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 接口单元测试调度 服务
 */
@Service
public class ApiUnitExecuteDispatchServiceImpl implements ApiUnitExecuteDispatchService {

    @Autowired
    ApiUnitCaseService apiUnitCaseService;

    @Autowired
    ApiUnitInstanceService apiUnitInstanceService;


    @Autowired
    AssertService assertService;

    @Autowired
    AssertInstanceService assertInstanceService;

    @Autowired
    ApiUnitInstanceBindService apiUnitInstanceBindService;

    @Autowired
    AgentConfigService agentConfigService;

    @Autowired
    RpcClientApixUtil rpcClientApixUtil;


    ApiUnitTestService apiUnitTestService(){
//        agentConfigService.findAgentConfigList()
        AgentConfig agentConfig = agentConfigService.findAgentConfig("111111");

        return rpcClientApixUtil.rpcClient().getBean(ApiUnitTestService.class, new FixedLookup(agentConfig.getUrl()));
    }

    @Override
    public ApiUnitInstance execute(ApiUnitTestRequest apiUnitTestRequest) {
        String apiUnitId = apiUnitTestRequest.getApiUnitCase().getId();
        ApiUnitCase apiUnitCase = apiUnitCaseService.findApiUnitCase(apiUnitId);

        //数据准备
        ApiUnitCaseExt apiUnitCaseExt = apiUnitCaseService.findApiUnitCaseExt(apiUnitCase);

        apiUnitTestRequest.setApiUnitCase(apiUnitCase);
        apiUnitTestRequest.setApiUnitCaseExt(apiUnitCaseExt);

        //执行
        ApiUnitInstance apiUnitInstance = apiUnitTestService().execute(apiUnitTestRequest);


        //测试计划中设置了执行类型，其他没设置
        if(apiUnitTestRequest.getExeType()==null){
            saveInstance(apiUnitInstance,apiUnitId);
        }


        return apiUnitInstance;
    }


    private void saveInstance(ApiUnitInstance apiUnitInstance, String apiUnitId){
        ApiUnitInstanceQuery apiUnitInstanceQuery = new ApiUnitInstanceQuery();
        apiUnitInstanceQuery.setApiUnitId(apiUnitId);
        List<ApiUnitInstance> apiUnitInstanceList = apiUnitInstanceService.findApiUnitInstanceList(apiUnitInstanceQuery);
        if(apiUnitInstanceList!=null&&apiUnitInstanceList.size()>0){
            Integer executeNumber = apiUnitInstanceList.get(0).getExecuteNumber();

            apiUnitInstance.setExecuteNumber(++executeNumber);
        }else {
            apiUnitInstance.setExecuteNumber(1);
        }


        //执行后数据保存
        String apiUnitInstanceId = apiUnitInstanceService.saveApiUnitInstanceToSql(apiUnitInstance);

        //关联表，相当于中间表
        ApiUnitInstanceBind apiUnitInstanceBind = new ApiUnitInstanceBind();
        apiUnitInstanceBind.setId(apiUnitInstanceId);
        apiUnitInstanceBind.setApiUnitInstance(new ApiUnitInstance().setId(apiUnitInstanceId));
        apiUnitInstanceBind.setApiUnitId(apiUnitId);
        apiUnitInstanceBindService.createApiUnitInstanceBind(apiUnitInstanceBind);

    }



}
