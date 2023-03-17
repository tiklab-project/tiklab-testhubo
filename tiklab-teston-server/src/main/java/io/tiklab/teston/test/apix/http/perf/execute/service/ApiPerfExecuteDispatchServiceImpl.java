package io.tiklab.teston.test.apix.http.perf.execute.service;

import io.tiklab.rpc.client.router.lookup.FixedLookup;
import io.tiklab.teston.agent.api.http.perf.ApiPerfTestService;
import io.tiklab.teston.test.apix.http.perf.cases.model.ApiPerfCase;
import io.tiklab.teston.test.apix.http.perf.cases.model.ApiPerfStep;
import io.tiklab.teston.test.apix.http.perf.cases.model.ApiPerfStepQuery;
import io.tiklab.teston.test.apix.http.perf.cases.service.ApiPerfCaseService;
import io.tiklab.teston.test.apix.http.perf.cases.service.ApiPerfStepService;
import io.tiklab.teston.test.apix.http.perf.execute.model.ApiPerfTestRequest;
import io.tiklab.teston.test.apix.http.perf.execute.model.ApiPerfTestResponse;
import io.tiklab.teston.test.apix.http.perf.instance.model.ApiPerfInstance;
import io.tiklab.teston.test.apix.http.perf.instance.model.ApiPerfInstanceQuery;
import io.tiklab.teston.test.apix.http.perf.instance.service.ApiPerfInstanceService;
import io.tiklab.teston.test.apix.http.scene.cases.model.ApiSceneCase;
import io.tiklab.teston.test.apix.http.scene.execute.model.ApiSceneTestRequest;
import io.tiklab.teston.test.apix.http.scene.execute.service.ApiSceneExecuteDispatchService;
import io.tiklab.teston.test.apix.http.scene.instance.model.ApiSceneInstance;
import io.tiklab.teston.test.apix.http.unit.execute.model.ApiUnitTestRequest;
import io.tiklab.teston.support.utils.RpcClientApixUtil;
import io.tiklab.teston.support.utils.TestApixUtil;


import io.tiklab.teston.support.agentconfig.model.AgentConfig;
import io.tiklab.teston.support.agentconfig.model.AgentConfigQuery;
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
 * api性能测试执行调度 服务
 */
@Service
public class ApiPerfExecuteDispatchServiceImpl implements ApiPerfExecuteDispatchService {

    public static final Logger logger = LoggerFactory.getLogger(ApiPerfExecuteDispatchServiceImpl.class);

    @Autowired
    ApiPerfCaseService apiPerfCaseService;

    @Autowired
    ApiPerfStepService apiPerfStepService;

    @Autowired
    ApiPerfInstanceService apiPerfInstanceService;

    @Autowired
    ApiSceneExecuteDispatchService apiSceneExecuteDispatchService;

    @Autowired
    AgentConfigService agentConfigService;

    @Autowired
    RpcClientApixUtil rpcClientApixUtil;

    @Autowired
    TestApixUtil testApixUtil;

    @Autowired
    ApiPerfTestService apiPerfTestService;

    /**
     * rpc 远程调用
     */
    ApiPerfTestService apiPerfTestServiceRPC(String agentUrl){
        return rpcClientApixUtil.rpcClient().getBean(ApiPerfTestService.class, new FixedLookup(agentUrl));
    }

    /**
     *  环境中获取是否是内嵌agent
     */
    @Value("${teston-agent.embbed.enable:false}")
    Boolean enable;

    private List<AgentConfig> agentConfigList;
    //执行次数
    private Integer executeCount;

    /**
     * 执行的状态：0：未执行，1：正在进行，2：结束
     */
    public Integer status=0;

    /**
     *  执行后  性能测试历史Id用于实时保存的历史
     */
    private String apiPerfInstanceId;

    @Override
    public void execute(ApiPerfTestRequest apiPerfTestRequest) {
        //开始执行
        status=1;
        apiPerfInstanceId=null;

        //查询当前压力测试
        String apiPerfId = apiPerfTestRequest.getApiPerfCase().getId();
        ApiPerfCase apiPerfCase = apiPerfCaseService.findApiPerfCase(apiPerfId);

        //执行的数据 处理
        List<ApiSceneTestRequest> apiSceneTestRequestList = processApiPerfTestData(apiPerfTestRequest);

        //构造数据
        apiPerfTestRequest.setApiPerfCase(apiPerfCase);
        apiPerfTestRequest.setApiSceneTestRequestList(apiSceneTestRequestList);

        //执行次数
        executeCount = apiPerfCase.getExecuteCount();

        agentConfigList = agentConfigService.findAgentConfigList(new AgentConfigQuery());
        //agent数量
        int agentSize = agentConfigList.size();

        //执行方式,循环或随机
        Integer executeType = apiPerfCase.getExecuteType();

        //先分配好各个agent所需的次数
        List<Integer> distributionList = new ArrayList<>();

        //执行方式循环
        if(executeType==1){
            distributionList = testApixUtil.loop(executeCount, agentSize);
        }

        if(executeType==2){
            distributionList = testApixUtil.random(executeCount, agentSize);
        }

        for(int i = 0; i<agentSize;i++){
            //
            apiPerfTestRequest.setExeNum(distributionList.get(i));

            //获取agentId，agentList index 从0开始
            AgentConfig agentConfig = agentConfigList.get(i);
            String agentUrl = agentConfig.getUrl();

            //执行测试
            if(enable){
                apiPerfTestService.execute(apiPerfTestRequest);
            }else {
                apiPerfTestServiceRPC(agentUrl).execute(apiPerfTestRequest);
            }
        }

    }



    @Override
    public ApiPerfTestResponse exeResult(ApiPerfTestRequest apiPerfTestRequest) {
        ApiPerfTestResponse apiPerfTestResponse = new ApiPerfTestResponse();

        ArrayList<ApiSceneInstance> arrayList = new ArrayList<>();

        for(int i = 0;i<agentConfigList.size();i++){

            ApiPerfTestResponse response = null;

            if(enable){
                response = apiPerfTestService.exeResult();
            }else {
                response = apiPerfTestServiceRPC(agentConfigList.get(i).getUrl()).exeResult();
            }


            //获取每个agent的实例
            if(CollectionUtils.isNotEmpty(response.getApiSceneInstanceList())){
                arrayList.addAll(response.getApiSceneInstanceList());
            }
        }


        //把所有实例设置到apiPerfTestResponse
        apiPerfTestResponse.setApiSceneInstanceList(arrayList);

        if(apiPerfTestResponse.getApiSceneInstanceList().size()==executeCount){
            status=2;

        }else {
            status=1;
        }
        apiPerfTestResponse.setStatus(status);

        ApiPerfInstance apiPerfInstance = processPerfTestData(apiPerfTestResponse);
        apiPerfTestResponse.setApiPerfInstance(apiPerfInstance);

        //测试计划中设置了值
        if(apiPerfTestRequest.getExeType()==null){
            //请求一次获取一次结果，存入数据库
            String apiPerfId = apiPerfTestRequest.getApiPerfCase().getId();
            apiPerfInstance.setApiPerfId(apiPerfId);

            //判断是否存在
            if(apiPerfInstanceId==null){
                //设置执行次数
                ApiPerfInstanceQuery apiPerfInstanceQuery = new ApiPerfInstanceQuery();
                apiPerfInstanceQuery.setApiPerfId(apiPerfId);
                List<ApiPerfInstance> apiPerfInstanceList = apiPerfInstanceService.findApiPerfInstanceList(apiPerfInstanceQuery);
                if(apiPerfInstanceList!=null&&apiPerfInstanceList.size()>0){
                    Integer executeNumber = apiPerfInstanceList.get(0).getExecuteNumber();

                    apiPerfInstance.setExecuteNumber(++executeNumber);
                }else {
                    apiPerfInstance.setExecuteNumber(1);
                }

                apiPerfInstanceId = apiPerfInstanceService.createApiPerfInstance(apiPerfInstance);
            }else {
                apiPerfInstance.setId(apiPerfInstanceId);
                apiPerfInstanceService.updateApiPerfInstance(apiPerfInstance);
            }
        }


        return apiPerfTestResponse;
    }

    /**
     * 数据构造
     * @param apiPerfTestRequest
     */
    private List<ApiSceneTestRequest> processApiPerfTestData(ApiPerfTestRequest apiPerfTestRequest) {
        String apiPerfId = apiPerfTestRequest.getApiPerfCase().getId();
        //查询所有场景
        ApiPerfStepQuery apiPerfStepQuery = new ApiPerfStepQuery();
        apiPerfStepQuery.setApiPerfId(apiPerfId);
        List<ApiPerfStep> apiPerfStepList = apiPerfStepService.findApiPerfStepList(apiPerfStepQuery);

        List<ApiSceneTestRequest> apiSceneTestRequestList = new ArrayList<>();

        //循环所有场景构造数据
        if(CollectionUtils.isNotEmpty(apiPerfStepList)){
            for(ApiPerfStep apiPerfStep:apiPerfStepList){
                ApiSceneTestRequest apiSceneTestRequest = new ApiSceneTestRequest();

                ApiSceneCase apiSceneCase = new ApiSceneCase();
                apiSceneCase.setId(apiPerfStep.getApiScene().getId());
                apiSceneTestRequest.setApiSceneCase(apiSceneCase);
                apiSceneTestRequest.setApiEnv(apiPerfTestRequest.getApiEnv());

                List<ApiUnitTestRequest> apiUnitTestRequestList = apiSceneExecuteDispatchService.processApiSceneTestData(apiSceneTestRequest);
                apiSceneTestRequest.setApiUnitTestRequestList(apiUnitTestRequestList);


                apiSceneTestRequestList.add(apiSceneTestRequest);
            }
        }

        return apiSceneTestRequestList;

    }


    /**
     * 处理测试数据
     * @param apiPerfTestResponse
     * @return
     */
    private ApiPerfInstance processPerfTestData(ApiPerfTestResponse apiPerfTestResponse) {
        ApiPerfInstance apiPerfInstance = new ApiPerfInstance();

        int size = apiPerfTestResponse.getApiSceneInstanceList().size();
        apiPerfInstance.setTotal(size);

        int passCount = 0;
        for (ApiSceneInstance apiSceneInstance : apiPerfTestResponse.getApiSceneInstanceList()) {
            if(apiSceneInstance.getResult().equals(1)){
                passCount++;
            }
        }

        String passRate = testApixUtil.processRate(passCount, size);
        apiPerfInstance.setPassRate(passRate);
        apiPerfInstance.setPassNum(passCount);
        apiPerfInstance.setFailNum(size-passCount);
        String errorRate = testApixUtil.processRate(size - passCount, size);
        apiPerfInstance.setErrorRate(errorRate);

        if(size==passCount){
            apiPerfInstance.setResult(1);
        }else {
            apiPerfInstance.setResult(0);
        }

        return apiPerfInstance;
    }





    @Override
    public void stop(ApiPerfTestRequest apiPerfTestRequest) {

    }

}
