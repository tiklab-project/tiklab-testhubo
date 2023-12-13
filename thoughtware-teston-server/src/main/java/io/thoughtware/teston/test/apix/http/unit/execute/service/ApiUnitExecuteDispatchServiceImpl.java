package io.thoughtware.teston.test.apix.http.unit.execute.service;

import com.alibaba.fastjson.JSONObject;
import io.thoughtware.core.exception.ApplicationException;
import io.thoughtware.rpc.client.router.lookup.FixedLookup;
import io.thoughtware.teston.agent.api.http.unit.ApiUnitTestService;
import io.thoughtware.teston.support.agentconfig.model.AgentConfigQuery;
import io.thoughtware.teston.support.variable.service.VariableService;
import io.thoughtware.teston.test.apix.http.unit.cases.model.ApiUnitCase;
import io.thoughtware.teston.test.apix.http.unit.cases.model.ApiUnitCaseDataConstruction;
import io.thoughtware.teston.test.apix.http.unit.cases.service.ApiUnitCaseService;
import io.thoughtware.teston.test.apix.http.unit.cases.service.AssertService;
import io.thoughtware.teston.test.apix.http.unit.execute.model.ApiUnitTestRequest;
import io.thoughtware.teston.test.apix.http.unit.instance.model.ApiUnitInstance;
import io.thoughtware.teston.test.apix.http.unit.instance.model.ApiUnitInstanceBind;
import io.thoughtware.teston.test.apix.http.unit.instance.model.ApiUnitInstanceBindQuery;
import io.thoughtware.teston.test.apix.http.unit.instance.service.ApiUnitInstanceBindService;
import io.thoughtware.teston.test.apix.http.unit.instance.service.ApiUnitInstanceService;
import io.thoughtware.teston.test.apix.http.unit.instance.service.AssertInstanceService;
import io.thoughtware.teston.support.utils.RpcClientApixUtil;
import io.thoughtware.teston.support.agentconfig.model.AgentConfig;
import io.thoughtware.teston.support.agentconfig.service.AgentConfigService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.util.List;

/**
 * 接口单元测试调度 服务
 */
@Service
public class ApiUnitExecuteDispatchServiceImpl implements ApiUnitExecuteDispatchService {

    private static Logger logger = LoggerFactory.getLogger(ApiUnitExecuteDispatchServiceImpl.class);


    @Autowired
    ApiUnitCaseService apiUnitCaseService;

    @Autowired
    ApiUnitInstanceService apiUnitInstanceService;

    @Autowired
    VariableService variableService;

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

    @Autowired
    ApiUnitTestService apiUnitTestService;

    /**
     *  环境中获取是否是内嵌agent
     */
    @Value("${teston-agent.embbed.enable:false}")
    Boolean enable;

    /**
     * rpc 调用
     */
    ApiUnitTestService apiUnitTestServiceRpc(String agentUrl){
        return rpcClientApixUtil.rpcClient().getBean(ApiUnitTestService.class, new FixedLookup(agentUrl));
    }

    @Override
    public ApiUnitInstance execute(ApiUnitTestRequest apiUnitTestRequest) {
        String apiUnitId = apiUnitTestRequest.getApiUnitCase().getId();
        //准备测试的数据
        ApiUnitTestRequest processData = setApiUnitTestRequestData(apiUnitId, apiUnitTestRequest.getApiEnv());

        ApiUnitInstance apiUnitInstance = null;
        logger.info("api-----");
        //根据环境配置是否为内嵌
        //如果不是内嵌走rpc
        try {
            if(enable){
                logger.info("api-enable----");
                apiUnitInstance = apiUnitTestService.execute(processData);
                logger.info("api-enable----end");
            }else {
                logger.info("api-not-enable----");
                List<AgentConfig> agentConfigList = agentConfigService.findAgentConfigList(new AgentConfigQuery());
                if( CollectionUtils.isNotEmpty(agentConfigList)){
                    AgentConfig agentConfig = agentConfigList.get(0);
                    apiUnitInstance = apiUnitTestServiceRpc(agentConfig.getUrl()).execute(processData);
                }
                logger.info("api-not-enable----end");
            }
        }catch (Exception e){
            logger.info("api-enable----error");
            throw new ApplicationException(e);
        }

        if(apiUnitInstance==null){
            return null;
        }

        //测试计划中设置了执行类型，其他没设置
        if(apiUnitTestRequest.getExeType()==null){
            saveInstance(apiUnitInstance,apiUnitId);
        }


        return apiUnitInstance;
    }


    private void saveInstance(ApiUnitInstance apiUnitInstance, String apiUnitId){
        //apiUnit 历史实例中间表,获取上次创建的实例
        ApiUnitInstanceBindQuery apiUnitInstanceBindQuery = new ApiUnitInstanceBindQuery();
        apiUnitInstanceBindQuery.setApiUnitId(apiUnitId);
        List<ApiUnitInstanceBind> apiUnitInstanceBindList = apiUnitInstanceBindService.findApiUnitInstanceBindList(apiUnitInstanceBindQuery);
        if(apiUnitInstanceBindList!=null&&apiUnitInstanceBindList.size()>0){
            String instanceId = apiUnitInstanceBindList.get(0).getId();
            //前一次的历史实例
            ApiUnitInstance preApiUnitInstance = apiUnitInstanceService.findApiUnitInstance(instanceId);
            //拿到执行的次数
            Integer executeNumber =preApiUnitInstance.getExecuteNumber();
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


    /**
     * 设置测试数据
     * @param apiUnitId
     * @param apiEnv
     * @return
     */

    public ApiUnitTestRequest setApiUnitTestRequestData(String apiUnitId,String apiEnv){
        ApiUnitTestRequest apiUnitTestRequest = new ApiUnitTestRequest();

        ApiUnitCase apiUnitCase = apiUnitCaseService.findApiUnitCase(apiUnitId);
        ApiUnitCaseDataConstruction apiUnitCaseDataConstruction = apiUnitCaseService.findApiUnitCaseExt(apiUnitCase);
        JSONObject variable = variableService.getVariable(apiUnitCase.getTestCase().getRepositoryId());

        apiUnitTestRequest.setVariableJson(variable);
        apiUnitTestRequest.setApiUnitCase(apiUnitCase);
        apiUnitTestRequest.setApiUnitCaseExt(apiUnitCaseDataConstruction);
        apiUnitTestRequest.setApiEnv(apiEnv);

        return apiUnitTestRequest;
    }

}