package net.tiklab.teston.apptest.perftest.service;

import net.tiklab.rpc.client.router.lookup.FixedLookup;
import net.tiklab.teston.apptest.perftest.model.*;
import net.tiklab.teston.apptest.scenetest.model.*;
import net.tiklab.teston.apptest.scenetest.service.AppSceneStepService;
import net.tiklab.teston.common.RpcClientUtil;
import net.tiklab.teston.common.TestUtil;
import net.tiklab.teston.integration.agentconfig.model.AgentConfig;
import net.tiklab.teston.integration.agentconfig.model.AgentConfigQuery;
import net.tiklab.teston.integration.agentconfig.service.AgentConfigService;
import net.tiklab.testonagent.apptest.perfcase.service.AppPerfTestService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service
public class AppPerfTestDispatchServiceImpl implements AppPerfTestDispatchService{

    private static Logger logger = LoggerFactory.getLogger(AppPerfTestDispatchServiceImpl.class);


    @Autowired
    AppSceneStepService appSceneStepService;

    @Autowired
    AppPerfCaseService appPerfCaseService;

    @Autowired
    AppPerfStepService appPerfStepService;

    @Autowired
    AppPerfInstanceService appPerfInstanceService;

    @Autowired
    AgentConfigService agentConfigService;

    @Autowired
    RpcClientUtil rpcClientUtil;

    @Autowired
    TestUtil testUtil;

    AppPerfTestService appPerfTestService(String agentId){
        AgentConfig agentConfig = agentConfigService.findAgentConfig(agentId);

        return rpcClientUtil.rpcClient().getBean(AppPerfTestService.class, new FixedLookup(agentConfig.getUrl()));
    }

    private List<AgentConfig> agentConfigList;
    //执行次数
    private Integer executeCount;

    /**
     * 执行的状态：0：未执行，1：正在进行，2：结束
     */
    public Integer status=0;

    /**
     * appPerfInstanceId
     */
    private String appPerfInstanceId;

    @Override
    public void execute(AppPerfTestRequest appPerfTestRequest) {
        //执行时，初始化，防止前一次执行留有值
        appPerfInstanceId=null;

        //配置参数，先固定写走通用测试，后面再改成可配置的
        AppTestConfig appTestConfig = new AppTestConfig();
        appTestConfig.setPlatformName("Android");
        appTestConfig.setAppiumSever("192.168.10.16:4723");
        appTestConfig.setAppPackage("com.tencent.mobileqq");
        appTestConfig.setAppActivity("com.tencent.mobileqq.activity.SplashActivity");
        appTestConfig.setDeviceName("127.0.0.1:62001");

        //配置参数，先固定写走通用测试，后面再改成可配置的
        AppTestConfig appTestConfig1 = new AppTestConfig();
        appTestConfig1.setPlatformName("Android");
        appTestConfig1.setAppiumSever("192.168.10.16:4724");
        appTestConfig1.setAppPackage("com.tencent.mobileqq");
        appTestConfig1.setAppActivity("com.tencent.mobileqq.activity.SplashActivity");
        appTestConfig1.setDeviceName("127.0.0.1:62025");

        //配置参数，先固定写走通用测试，后面再改成可配置的
        AppTestConfig appTestConfig2 = new AppTestConfig();
        appTestConfig2.setPlatformName("Android");
        appTestConfig2.setAppiumSever("192.168.10.16:4725");
        appTestConfig2.setAppPackage("com.tencent.mobileqq");
        appTestConfig2.setAppActivity("com.tencent.mobileqq.activity.SplashActivity");
        appTestConfig2.setDeviceName("127.0.0.1:62026");


        ArrayList<AppTestConfig> appTestConfigList = new ArrayList<>();
        appTestConfigList.add(appTestConfig);
        appTestConfigList.add(appTestConfig1);
        appTestConfigList.add(appTestConfig2);

        appPerfTestRequest.setAppTestConfigList(appTestConfigList);

//        appPerfTestRequest.setAppTestConfig(appTestConfig);

        String appPerfId = appPerfTestRequest.getAppPerfId();

        //查询当前压力测试
        AppPerfCase appPerfCase = appPerfCaseService.findAppPerfCase(appPerfTestRequest.getAppPerfId());
        appPerfTestRequest.setAppPerfCase(appPerfCase);

        //先查询所有场景步骤
        AppPerfStepQuery appPerfStepQuery = new AppPerfStepQuery();
        appPerfStepQuery.setAppPerfId(appPerfId);
        List<AppPerfStep> appPerfStepList = appPerfStepService.findAppPerfStepList(appPerfStepQuery);

        //处理的请求参数
        List<AppSceneTestRequest> appSceneTestRequestList = processExeData(appPerfStepList,appTestConfig);

        appPerfTestRequest.setAppSceneTestRequestList(appSceneTestRequestList);

        //执行次数
        executeCount = appPerfCase.getExecuteCount();

        agentConfigList = agentConfigService.findAgentConfigList(new AgentConfigQuery());
        //agent数量
        int agentSize = agentConfigList.size();
        //执行方式
        Integer executeType = appPerfCase.getExecuteType();

        //先分配好各个agent所需的次数
        List<Integer> distributionList = new ArrayList<>();

        //执行方式循环
        if(executeType==1){
            logger.info("--------------------------------------loop-----------------------------------");
            distributionList = testUtil.loop(executeCount, agentSize);
        }

        if(executeType==2){
            distributionList = testUtil.random(executeCount, agentSize);
        }

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 999, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(10));

        threadPoolExecutor.setCorePoolSize(agentSize);



        for(int i = 0; i<agentSize;i++){

            threadPoolExecutor.setCorePoolSize(agentSize);

            //设置执行次数
            appPerfTestRequest.setExeNum(distributionList.get(i));

            appPerfTestRequest.setAppTestConfig(appTestConfigList.get(i));

            //获取agentId，agentList index 从0开始
            AgentConfig agentConfig = agentConfigList.get(i);
            appPerfTestRequest.setCurrentAgentId(agentConfig.getId());

            //执行测试
            appPerfTestService(agentConfig.getId()).execute(appPerfTestRequest);
        }

    }

    @Override
    public AppPerfTestResponse exeResult(AppPerfTestRequest appPerfTestRequest) {
        AppPerfTestResponse appPerfTestResponse = new AppPerfTestResponse();

        ArrayList<AppSceneInstance> arrayList = new ArrayList<>();

        for(int i = 0;i<agentConfigList.size();i++){
            AppPerfTestResponse response = appPerfTestService(agentConfigList.get(i).getId()).exeResult(appPerfTestRequest);

            if(CollectionUtils.isNotEmpty(response.getAppSceneInstanceList())){
                //获取每个agent的实例
                arrayList.addAll(response.getAppSceneInstanceList());
            }
        }

        appPerfTestResponse.setAppSceneInstanceList(arrayList);

        if(appPerfTestResponse.getAppSceneInstanceList().size()==executeCount){
            status=2;
        }else {
            status=1;
        }

        AppPerfInstance appPerfInstance = processPerfTestData(appPerfTestResponse);
        appPerfTestResponse.setAppPerfInstance(appPerfInstance);

        //测试计划中设置了值,
        if(appPerfTestRequest.getExeType()==null){
            String appPerfId = appPerfTestRequest.getAppPerfId();
            appPerfInstance.setAppPerfId(appPerfId);

            //判断是否存在
            if(appPerfInstanceId==null){
                //设置执行次数
                AppPerfInstanceQuery appPerfInstanceQuery = new AppPerfInstanceQuery();
                appPerfInstanceQuery.setAppPerfId(appPerfId);
                List<AppPerfInstance> appPerfInstanceList = appPerfInstanceService.findAppPerfInstanceList(appPerfInstanceQuery);
                if(appPerfInstanceList!=null&&appPerfInstanceList.size()>0){
                    Integer executeNumber = appPerfInstanceList.get(0).getExecuteNumber();

                    appPerfInstance.setExecuteNumber(++executeNumber);
                }else {
                    appPerfInstance.setExecuteNumber(1);
                }

                appPerfInstanceId = appPerfInstanceService.createAppPerfInstance(appPerfInstance);
            }else {
                appPerfInstance.setId(appPerfInstanceId);
                appPerfInstanceService.updateAppPerfInstance(appPerfInstance);
            }
        }



        return appPerfTestResponse;
    }


    /**
     * 处理执行结果的数据
     * @param appPerfTestResponse
     * @return
     */
    private AppPerfInstance processPerfTestData(AppPerfTestResponse appPerfTestResponse) {
        AppPerfInstance appPerfInstance = new AppPerfInstance();

        int size = appPerfTestResponse.getAppSceneInstanceList().size();
        appPerfInstance.setTotal(size);

        int passNum = 0;
        for(AppSceneInstance appSceneInstance : appPerfTestResponse.getAppSceneInstanceList()){
            if(appSceneInstance.getResult().equals(1)){
                passNum++;
            }
        }

        appPerfInstance.setPassNum(passNum);
        appPerfInstance.setPassRate(testUtil.processRate(passNum, size));

        appPerfInstance.setFailNum(size - passNum);
        appPerfInstance.setErrorRate(testUtil.processRate(size - passNum, size));


        if(size==passNum){
            appPerfInstance.setResult(1);
        }else {
            appPerfInstance.setResult(0);
        }


        return appPerfInstance;
    }



    /**
     * 构造执行数据
     * @param appPerfStepList
     * @param appTestConfig
     * @return
     */
    private List<AppSceneTestRequest> processExeData(List<AppPerfStep> appPerfStepList, AppTestConfig appTestConfig) {
        List<AppSceneTestRequest> appSceneTestRequestList = new ArrayList<>();

        if (CollectionUtils.isNotEmpty(appPerfStepList)){
            //循环压力测试的场景
            for(AppPerfStep appPerfStep : appPerfStepList){
                AppSceneTestRequest appSceneTestRequest = new AppSceneTestRequest();

                //查询当前场景下的步骤
                AppSceneStepQuery appSceneStepQuery = new AppSceneStepQuery();
                appSceneStepQuery.setAppSceneId(appPerfStep.getAppScene().getId());
                List<AppSceneStep> appSceneStepList = appSceneStepService.findAppSceneStepList(appSceneStepQuery);

                appSceneTestRequest.setAppSceneStepList(appSceneStepList);
                appSceneTestRequest.setAppTestConfig(appTestConfig);


                appSceneTestRequestList.add(appSceneTestRequest);
            }
        }

        return appSceneTestRequestList;
    }



}
