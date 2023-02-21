package net.tiklab.teston.apitest.http.scenetest.service;

import net.tiklab.rpc.client.router.lookup.FixedLookup;
import net.tiklab.teston.apitest.http.scenetest.model.*;
import net.tiklab.teston.apitest.http.unittest.model.*;
import net.tiklab.teston.apitest.http.unittest.service.ApiUnitCaseService;
import net.tiklab.teston.apitest.http.unittest.service.ApiUnitInstanceService;
import net.tiklab.teston.apitest.http.unittest.service.RequestInstanceService;
import net.tiklab.teston.apitest.http.unittest.service.ResponseInstanceService;
import net.tiklab.teston.common.RpcClientUtil;
import net.tiklab.teston.integration.agentconfig.model.AgentConfig;
import net.tiklab.teston.integration.agentconfig.service.AgentConfigService;
import net.tiklab.testonagent.apitest.http.scenecase.service.ApiSceneTestService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApiSceneTestDispatchServiceImpl implements ApiSceneTestDispatchService {

    static Logger logger = LoggerFactory.getLogger(ApiSceneTestDispatchServiceImpl.class);

    @Autowired
    ApiUnitCaseService apiUnitCaseService;

    @Autowired
    ApiSceneStepService apiSceneStepService;

    @Autowired
    ApiSceneInstanceService apiSceneInstanceService;

    @Autowired
    AgentConfigService agentConfigService;

    @Autowired
    RpcClientUtil rpcClientUtil;

    ApiSceneTestService apiSceneTestService(){
        AgentConfig agentConfig = agentConfigService.findAgentConfig("111111");

        return rpcClientUtil.rpcClient().getBean(ApiSceneTestService.class, new FixedLookup(agentConfig.getUrl()));
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

        //执行测试步骤返回数据
        ApiSceneTestResponse apiSceneTestResponse = apiSceneTestService().execute(apiSceneTestRequest);

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
                ApiUnitCaseExt apiUnitCaseExt = apiUnitCaseService.findApiUnitCaseExt(apiUnitCase);
                apiUnitTestRequest.setApiUnitCaseExt(apiUnitCaseExt);

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



