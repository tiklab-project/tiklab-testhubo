package io.tiklab.teston.test.web.scene.execute.service;

import com.alibaba.fastjson.JSONObject;
import io.tiklab.core.exception.ApplicationException;
import io.tiklab.rpc.client.router.lookup.FixedLookup;
import io.tiklab.teston.agent.web.scene.WebSceneTestService;
import io.tiklab.teston.support.agentconfig.model.AgentConfig;
import io.tiklab.teston.support.agentconfig.model.AgentConfigQuery;
import io.tiklab.teston.support.agentconfig.service.AgentConfigService;
import io.tiklab.teston.support.variable.service.VariableService;
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
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
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
    AgentConfigService agentConfigService;

    @Autowired
    RpcClientWebUtil rpcClientWebUtil;

    @Autowired
    WebSceneTestService webSceneTestService;

    @Autowired
    VariableService variableService;

    /**
     *  环境中获取是否是内嵌agent
     */
    @Value("${teston-agent.embbed.enable:false}")
    Boolean enable;

    /**
     * rpc 远程调用
     */
    WebSceneTestService webSceneTestServiceRPC(String agentUrl){
        return rpcClientWebUtil.rpcClient().getBean(WebSceneTestService.class, new FixedLookup(agentUrl));
    }


    private AgentConfig agentConfig = null;

    //状态  0未开始，1进行中
    private Integer status;

    @Override
    public Integer execute(WebSceneTestRequest webSceneTestRequest) {
        status=1;

        //异步执行
        executeStart(webSceneTestRequest);

        return 1;
    }

    private void executeStart(WebSceneTestRequest webSceneTestRequest){
        String webSceneId = webSceneTestRequest.getWebSceneId();

        //先查询所有的场景步骤
        WebSceneStepQuery webSceneStepQuery = new WebSceneStepQuery();
        webSceneStepQuery.setWebSceneId(webSceneId);
        List<WebSceneStep> webSceneStepList = webSceneStepService.findWebSceneStepList(webSceneStepQuery);

        JSONObject variable = variableService.getVariable(webSceneId);
        webSceneTestRequest.setVariableJson(variable);


        //设置步骤数据
        webSceneTestRequest.setWebSceneStepList(webSceneStepList);

        //根据环境配置是否为内嵌
        //如果不是内嵌走rpc
        if(enable) {
            //调用执行方法返回结果数据
            webSceneTestService.execute(webSceneTestRequest);
        }else {
            List<AgentConfig> agentConfigList = agentConfigService.findAgentConfigList(new AgentConfigQuery());
            if(CollectionUtils.isNotEmpty(agentConfigList)){
                agentConfig = agentConfigList.get(0);

                webSceneTestServiceRPC(agentConfig.getUrl()).execute(webSceneTestRequest);
            }
        }
    }


    @Override
    public Integer status() {

        //根据环境配置是否为内嵌
        //如果不是内嵌走rpc
        if(enable) {
            //调用执行方法返回结果数据
            status = webSceneTestService.status();
        }else {
            List<AgentConfig> agentConfigList = agentConfigService.findAgentConfigList(new AgentConfigQuery());
            if(CollectionUtils.isNotEmpty(agentConfigList)) {
                agentConfig = agentConfigList.get(0);
                status = webSceneTestServiceRPC(agentConfig.getUrl()).status();
            }
        }


        return status;
    }

    @Override
    public WebSceneTestResponse result(WebSceneTestRequest webSceneTestRequest) {

        WebSceneTestResponse webSceneTestResponse;

        //根据环境配置是否为内嵌
        //如果不是内嵌走rpc
        try{
            if(enable) {
                //调用执行方法返回结果数据
                webSceneTestResponse = webSceneTestService.result();
            }else {
                webSceneTestResponse = webSceneTestServiceRPC(agentConfig.getUrl()).result();
            }
        }catch (Exception e){
            throw new ApplicationException(e);
        }


        //测试计划中设置了执行类型，其他没设置
//        if(webSceneTestRequest.getExeType()==null){
//            //返回的数据存入数据库
        //status为0执行结束，存入历史
        if(status==0){
            saveToSql(webSceneTestResponse,webSceneTestRequest.getWebSceneId());
        }

//        }

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
