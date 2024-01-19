package io.thoughtware.teston.test.web.scene.execute.service;

import com.alibaba.fastjson.JSONObject;
import io.thoughtware.teston.support.agentconfig.model.AgentConfig;
import io.thoughtware.teston.support.agentconfig.model.AgentConfigQuery;
import io.thoughtware.teston.support.agentconfig.service.AgentConfigService;
import io.thoughtware.teston.test.common.stepcommon.model.StepCommon;
import io.thoughtware.teston.test.common.stepcommon.model.StepCommonQuery;
import io.thoughtware.teston.test.common.stepcommon.service.StepCommonService;
import io.thoughtware.teston.test.web.utils.RpcClientWebUtil;
import io.thoughtware.core.exception.ApplicationException;
import io.thoughtware.rpc.client.router.lookup.FixedLookup;
import io.thoughtware.teston.agent.web.scene.WebSceneTestService;
import io.thoughtware.teston.common.MagicValue;
import io.thoughtware.teston.support.variable.service.VariableService;
import io.thoughtware.teston.test.web.scene.instance.model.WebSceneInstanceQuery;
import io.thoughtware.teston.test.web.scene.cases.service.WebSceneStepService;
import io.thoughtware.teston.test.web.scene.execute.model.WebSceneTestRequest;
import io.thoughtware.teston.test.web.scene.execute.model.WebSceneTestResponse;
import io.thoughtware.teston.test.web.scene.instance.model.WebSceneInstance;
import io.thoughtware.teston.test.web.scene.instance.service.WebSceneInstanceService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Autowired
    StepCommonService stepCommonService;

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

        //执行
        try {
            executeStart(webSceneTestRequest);
        } catch (Exception e){
            status = 0;
            throw new ApplicationException(e);
        }
        return 1;
    }

    private void executeStart(WebSceneTestRequest webSceneTestRequest){
        String webSceneId = webSceneTestRequest.getWebSceneId();

        //先查询所有的场景步骤
        StepCommonQuery stepCommonQuery = new StepCommonQuery();
        stepCommonQuery.setCaseId(webSceneId);
        stepCommonQuery.setCaseType(MagicValue.CASE_TYPE_WEB);
        List<StepCommon> stepCommonList = stepCommonService.findStepCommonList(stepCommonQuery);
        //设置步骤数据
        webSceneTestRequest.setStepCommonList(stepCommonList);

        //设置变量数据
        JSONObject variable = variableService.getVariable(webSceneId);
        webSceneTestRequest.setVariableJson(variable);


        //根据环境配置是否为内嵌
        //如果不是内嵌走rpc
        try {
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
        }catch (Exception e){
            status = 0;
            throw new ApplicationException(e);
        }
    }


    @Override
    public Integer status() {

        //根据环境配置是否为内嵌
        //如果不是内嵌走rpc
        try {
            if(enable) {
                //调用执行方法返回结果数据
                status = webSceneTestService.status();
            }else {
                List<AgentConfig> agentConfigList = agentConfigService.findAgentConfigList(new AgentConfigQuery());
                if(CollectionUtils.isNotEmpty(agentConfigList)) {
                    agentConfig = agentConfigList.get(0);
                    status = webSceneTestServiceRPC(agentConfig.getUrl()).status();
                }else {
                    throw new ApplicationException("不是内嵌agent，请到设置中配置agent");
                }
            }

        }catch (Exception e){
            status = 0;
            throw new ApplicationException(e);
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
            status=0;
            throw new ApplicationException(e);
        }


        //测试计划中设置了执行类型，其他没设置
        if(webSceneTestRequest.getExeType()==null){
//            //返回的数据存入数据库
        //status为0执行结束，存入历史
            if(status==0){
                saveToSql(webSceneTestResponse,webSceneTestRequest.getWebSceneId());
            }

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

        webSceneInstanceService.saveWebSceneInstanceToSql(webSceneInstance,webSceneTestResponse);
    }


}
