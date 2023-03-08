package io.tiklab.teston.test.web.scene.execute.service;

import io.tiklab.rpc.client.router.lookup.FixedLookup;
import io.tiklab.teston.agent.web.scene.WebSceneTestService;
import io.tiklab.teston.support.agentconfig.model.AgentConfig;
import io.tiklab.teston.support.agentconfig.service.AgentConfigService;
import io.tiklab.teston.test.web.scene.instance.model.WebSceneInstanceQuery;
import io.tiklab.teston.test.web.scene.cases.model.WebSceneStep;
import io.tiklab.teston.test.web.scene.cases.model.WebSceneStepQuery;
import io.tiklab.teston.test.web.scene.cases.service.WebSceneStepService;
import io.tiklab.teston.test.web.scene.execute.model.WebSceneTestRequest;
import io.tiklab.teston.test.web.scene.execute.model.WebSceneTestResponse;
import io.tiklab.teston.test.web.scene.instance.model.WebSceneInstance;
import io.tiklab.teston.test.web.scene.instance.service.WebSceneInstanceService;
import io.tiklab.teston.test.web.scene.instance.service.WebSceneInstanceStepService;
import io.tiklab.teston.test.web.utils.RpcClientWebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * web场景测试调度 服务
 */
@Service
public class WebSceneTestDispatchServiceImpl implements WebSceneTestDispatchService {

    @Autowired
    WebSceneStepService webSceneStepService;

    @Autowired
    WebSceneInstanceService webSceneInstanceService;

    @Autowired
    WebSceneInstanceStepService webSceneInstanceStepService;

    @Autowired
    AgentConfigService agentConfigService;

    @Autowired
    RpcClientWebUtil rpcClientWebUtil;

    WebSceneTestService webSceneTestService(String agentConfigId){
        AgentConfig agentConfig = agentConfigService.findAgentConfig("111111");

        return rpcClientWebUtil.rpcClient().getBean(WebSceneTestService.class, new FixedLookup(agentConfig.getUrl()));
    }


    @Override
    public WebSceneTestResponse execute(WebSceneTestRequest webSceneTestRequest) {

        String webSceneId = webSceneTestRequest.getWebSceneId();

        //先查询所有的场景步骤
        WebSceneStepQuery webSceneStepQuery = new WebSceneStepQuery();
        webSceneStepQuery.setWebSceneId(webSceneId);
        List<WebSceneStep> webSceneStepList = webSceneStepService.findWebSceneStepList(webSceneStepQuery);

        //设置步骤数据
        webSceneTestRequest.setWebSceneStepList(webSceneStepList);

        //调用执行方法返回结果数据
        WebSceneTestResponse webSceneTestResponse = webSceneTestService(webSceneTestRequest.getAgentConfigId()).execute(webSceneTestRequest);

        if(webSceneTestRequest.getExeType()==null){
            //返回的数据存入数据库
            saveToSql(webSceneTestResponse,webSceneId);
        }



        return webSceneTestResponse;
    }

    /**
     * 历史记录 存入数据库
     * @param webSceneTestResponse
     * @param webSceneId
     */
    private void saveToSql(WebSceneTestResponse webSceneTestResponse, String webSceneId) {
        //保存历史总详情
        WebSceneInstance webSceneInstance = webSceneTestResponse.getWebSceneInstance();
        webSceneInstance.setWebSceneId(webSceneId);

        //设置次数
        WebSceneInstanceQuery webSceneInstanceQuery = new WebSceneInstanceQuery();
        webSceneInstanceQuery.setWebSceneId(webSceneId);
        List<WebSceneInstance> webSceneInstanceList = webSceneInstanceService.findWebSceneInstanceList(webSceneInstanceQuery);
        if(webSceneInstanceList!=null&&webSceneInstanceList.size()>0){
            Integer executeNumber = webSceneInstanceList.get(0).getExecuteNumber();

            webSceneInstance.setExecuteNumber(++executeNumber);
        }else {
            webSceneInstance.setExecuteNumber(1);
        }


        webSceneInstanceService.saveWebSceneInstanceToSql(webSceneInstance,webSceneTestResponse);
    }


}
