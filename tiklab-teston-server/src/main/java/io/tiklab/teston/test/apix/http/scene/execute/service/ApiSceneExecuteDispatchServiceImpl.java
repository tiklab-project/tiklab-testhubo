package io.tiklab.teston.test.apix.http.scene.execute.service;

import io.tiklab.rpc.client.router.lookup.FixedLookup;

import io.tiklab.teston.agent.api.http.scene.ApiSceneTestService;
import io.tiklab.teston.support.agentconfig.model.AgentConfigQuery;
import io.tiklab.teston.test.apix.http.scene.cases.model.ApiSceneStep;
import io.tiklab.teston.test.apix.http.scene.cases.model.ApiSceneStepQuery;
import io.tiklab.teston.test.apix.http.scene.cases.service.ApiSceneStepService;
import io.tiklab.teston.test.apix.http.scene.execute.model.ApiSceneTestRequest;
import io.tiklab.teston.test.apix.http.scene.execute.model.ApiSceneTestResponse;
import io.tiklab.teston.test.apix.http.scene.instance.model.ApiSceneInstance;
import io.tiklab.teston.test.apix.http.scene.instance.model.ApiSceneInstanceQuery;
import io.tiklab.teston.test.apix.http.scene.instance.service.ApiSceneInstanceService;
import io.tiklab.teston.test.apix.http.unit.cases.model.ApiUnitCase;
import io.tiklab.teston.test.apix.http.unit.cases.model.ApiUnitCaseDataConstruction;
import io.tiklab.teston.test.apix.http.unit.cases.service.ApiUnitCaseService;
import io.tiklab.teston.test.apix.http.unit.execute.model.ApiUnitTestRequest;
import io.tiklab.teston.support.utils.RpcClientApixUtil;
import io.tiklab.teston.support.agentconfig.model.AgentConfig;
import io.tiklab.teston.support.agentconfig.service.AgentConfigService;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 接口场景测试调度 服务
 */
@Service
public class ApiSceneExecuteDispatchServiceImpl implements ApiSceneExecuteDispatchService {

    static Logger logger = LoggerFactory.getLogger(ApiSceneExecuteDispatchServiceImpl.class);

    @Autowired
    ApiUnitCaseService apiUnitCaseService;

    @Autowired
    ApiSceneStepService apiSceneStepService;

    @Autowired
    ApiSceneInstanceService apiSceneInstanceService;

    @Autowired
    AgentConfigService agentConfigService;

    @Autowired
    RpcClientApixUtil rpcClientApixUtil;

    @Autowired
    ApiSceneTestService apiSceneTestService;

    /**
     *  环境中获取是否是内嵌agent
     */
    @Value("${teston-agent.embbed.enable:false}")
    Boolean enable;

    /**
     * rpc 调用
     */
    ApiSceneTestService apiSceneTestServiceRPC(String agentUrl){
        return rpcClientApixUtil.rpcClient().getBean(ApiSceneTestService.class, new FixedLookup(agentUrl));
    }

    /**
     * 测试用例执行
     * @param apiSceneTestRequest
     * @return
     */
    @Override
    public ApiSceneTestResponse execute(ApiSceneTestRequest apiSceneTestRequest) {

        //数据构造
        List<ApiUnitTestRequest> apiUnitTestRequestList = processApiSceneTestData(apiSceneTestRequest);
        apiSceneTestRequest.setApiUnitTestRequestList(apiUnitTestRequestList);


        ApiSceneTestResponse apiSceneTestResponse = null;
        //根据环境配置是否为内嵌
        //如果不是内嵌走rpc
        if(enable){
            //执行测试步骤返回数据
            apiSceneTestResponse = apiSceneTestService.execute(apiSceneTestRequest);
        }else {
            List<AgentConfig> agentConfigList = agentConfigService.findAgentConfigList(new AgentConfigQuery());
            if(CollectionUtils.isNotEmpty(agentConfigList)){
                AgentConfig agentConfig = agentConfigList.get(0);

                apiSceneTestResponse = apiSceneTestServiceRPC(agentConfig.getUrl()).execute(apiSceneTestRequest);
            }
        }

        //测试计划中设置了执行类型，其他没设置
        if(apiSceneTestRequest.getExeType()==null){
            //保存实例，存入数据库
            String apiSceneId = apiSceneTestRequest.getApiSceneCase().getId();
            saveInstance( apiSceneTestResponse,apiSceneId);
        }

        return apiSceneTestResponse;
    }


    /**
     * 数据构造
     */
    @Override
    public List<ApiUnitTestRequest> processApiSceneTestData(ApiSceneTestRequest apiSceneTestRequest){
        String apiSceneId = apiSceneTestRequest.getApiSceneCase().getId();
        //查询测试步骤
        ApiSceneStepQuery apiSceneStepQuery = new ApiSceneStepQuery();
        apiSceneStepQuery.setApiSceneId(apiSceneId);
        List<ApiSceneStep> apiSceneStepList = apiSceneStepService.findApiSceneStepList(apiSceneStepQuery);

        List<ApiUnitTestRequest> apiUnitTestRequestList = new ArrayList<>();

        if(CollectionUtils.isNotEmpty(apiSceneStepList)){
            for(ApiSceneStep apiSceneStep :apiSceneStepList){
                //设置apiUnitTestRequest参数
                ApiUnitTestRequest apiUnitTestRequest = new ApiUnitTestRequest();

                ApiUnitCase apiUnitCase = apiUnitCaseService.findApiUnitCase(apiSceneStep.getApiUnit().getId());
                apiUnitTestRequest.setApiUnitCase(apiUnitCase);

                //参数设置
                ApiUnitCaseDataConstruction apiUnitCaseDataConstruction = apiUnitCaseService.findApiUnitCaseExt(apiUnitCase);
                apiUnitTestRequest.setApiUnitCaseExt(apiUnitCaseDataConstruction);

                //前置地址
                apiUnitTestRequest.setApiEnv(apiSceneTestRequest.getApiEnv());

                apiUnitTestRequestList.add(apiUnitTestRequest);
            }
        }


        return apiUnitTestRequestList;
    }


    /**
     * 保存历史
     * @return
     * @param apiSceneTestResponse
     * @param apiSceneId
     */
    private void saveInstance(ApiSceneTestResponse apiSceneTestResponse, String apiSceneId){
        ApiSceneInstance apiSceneInstance = apiSceneTestResponse.getApiSceneInstance();
        apiSceneInstance.setApiSceneId(apiSceneId);

        //设置次数
        ApiSceneInstanceQuery apiSceneInstanceQuery = new ApiSceneInstanceQuery();
        apiSceneInstanceQuery.setApiSceneId(apiSceneId);
        List<ApiSceneInstance> apiSceneInstanceList = apiSceneInstanceService.findApiSceneInstanceList(apiSceneInstanceQuery);
        if(apiSceneInstanceList!=null&&apiSceneInstanceList.size()>0){
            Integer executeNumber = apiSceneInstanceList.get(0).getExecuteNumber();

            apiSceneInstance.setExecuteNumber(++executeNumber);
        }else {
            apiSceneInstance.setExecuteNumber(1);
        }

        apiSceneInstanceService.saveApiSceneInstanceToSql(apiSceneInstance,apiSceneTestResponse);
    }




}



