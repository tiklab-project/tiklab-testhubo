package net.tiklab.teston.test.appscene.execute.service;

import net.tiklab.rpc.client.router.lookup.FixedLookup;
import net.tiklab.teston.common.RpcClientUtil;
import net.tiklab.teston.support.agentconfig.model.AgentConfig;
import net.tiklab.teston.support.agentconfig.service.AgentConfigService;
import net.tiklab.teston.support.environment.service.AppEnvService;
import net.tiklab.teston.test.appscene.cases.model.AppSceneStep;
import net.tiklab.teston.test.appscene.cases.model.AppSceneStepQuery;
import net.tiklab.teston.test.appscene.execute.model.AppSceneTestRequest;
import net.tiklab.teston.test.appscene.execute.model.AppSceneTestResponse;
import net.tiklab.teston.test.appscene.instance.model.AppSceneInstance;
import net.tiklab.teston.test.appscene.instance.model.AppSceneInstanceQuery;
import net.tiklab.teston.test.appscene.instance.service.AppSceneInstanceService;
import net.tiklab.teston.test.appscene.cases.service.AppSceneStepService;
import net.tiklab.testonagent.apptest.scenecase.service.AppSceneTestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class AppSceneTestDispatchServiceImpl implements AppSceneTestDispatchService {

    private static Logger logger = LoggerFactory.getLogger(AppSceneTestDispatchServiceImpl.class);

    @Autowired
    AppSceneStepService appSceneStepService;

    @Autowired
    AppSceneInstanceService appSceneInstanceService;

    @Autowired
    AgentConfigService agentConfigService;

    @Autowired
    AppEnvService appEnvService;

    @Autowired
    RpcClientUtil rpcClientUtil;

    AppSceneTestService appSceneTestService(String agentId){

        String id=null;

        if(ObjectUtils.isEmpty(agentId)){
            id= "111111";
        }else {
            id=agentId;
        }

        AgentConfig agentConfig = agentConfigService.findAgentConfig(id);

        return rpcClientUtil.rpcClient().getBean(AppSceneTestService.class, new FixedLookup(agentConfig.getUrl()));
    }


    @Override
    public AppSceneTestResponse execute(AppSceneTestRequest appSceneTestRequest) {
        String appSceneId = appSceneTestRequest.getAppSceneId();

        //先查询所有场景步骤
        AppSceneStepQuery appSceneStepQuery = new AppSceneStepQuery();
        appSceneStepQuery.setAppSceneId(appSceneId);
        List<AppSceneStep> appSceneStepList = appSceneStepService.findAppSceneStepList(appSceneStepQuery);

        appSceneTestRequest.setAppSceneStepList(appSceneStepList);


        AppSceneTestResponse appSceneTestResponse = appSceneTestService(appSceneTestRequest.getCurrentAgentId()).execute(appSceneTestRequest);

       //测试计划中设置了值
        if(appSceneTestRequest.getExeType()==null){
            //errorMessage 是启动系统失败
            if(ObjectUtils.isEmpty(appSceneTestResponse.getErrMsg())){
                saveToSQL(appSceneTestResponse,appSceneId);
            }
        }

        return appSceneTestResponse;
    }

    /**
     * 历史记录 存入数据库
     * @param appSceneTestResponse
     * @param appSceneId
     */
    private void saveToSQL(AppSceneTestResponse appSceneTestResponse, String appSceneId) {
        //保存历史总详情
        AppSceneInstance appSceneInstance = appSceneTestResponse.getAppSceneInstance();
        appSceneInstance.setAppSceneId(appSceneId);

        //设置次数
        AppSceneInstanceQuery appSceneInstanceQuery = new AppSceneInstanceQuery();
        appSceneInstanceQuery.setAppSceneId(appSceneId);
        List<AppSceneInstance> appSceneInstanceList = appSceneInstanceService.findAppSceneInstanceList(appSceneInstanceQuery);
        if(appSceneInstanceList!=null&&appSceneInstanceList.size()>0){
            Integer executeNumber = appSceneInstanceList.get(0).getExecuteNumber();

            appSceneInstance.setExecuteNumber(++executeNumber);
        }else {
            appSceneInstance.setExecuteNumber(1);
        }

        appSceneInstanceService.saveAppSceneInstanceToSql(appSceneInstance,appSceneTestResponse);
    }
}