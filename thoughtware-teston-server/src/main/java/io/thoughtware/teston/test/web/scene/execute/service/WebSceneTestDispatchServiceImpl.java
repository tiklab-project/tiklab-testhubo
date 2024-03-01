package io.thoughtware.teston.test.web.scene.execute.service;

import com.alibaba.fastjson.JSONObject;
import io.thoughtware.teston.instance.model.Instance;
import io.thoughtware.teston.instance.service.InstanceService;
import io.thoughtware.teston.support.agentconfig.model.AgentConfig;
import io.thoughtware.teston.support.agentconfig.model.AgentConfigQuery;
import io.thoughtware.teston.support.agentconfig.service.AgentConfigService;
import io.thoughtware.teston.test.common.stepcommon.model.StepCommon;
import io.thoughtware.teston.test.common.stepcommon.model.StepCommonInstance;
import io.thoughtware.teston.test.common.stepcommon.model.StepCommonQuery;
import io.thoughtware.teston.test.common.stepcommon.service.StepCommonService;
import io.thoughtware.teston.test.web.scene.cases.model.WebSceneCase;
import io.thoughtware.teston.test.web.scene.cases.service.WebSceneCaseService;
import io.thoughtware.teston.test.web.utils.RpcClientWebUtil;
import io.thoughtware.core.exception.ApplicationException;
import io.thoughtware.rpc.client.router.lookup.FixedLookup;
import io.thoughtware.teston.agent.web.scene.WebSceneTestService;
import io.thoughtware.teston.common.MagicValue;
import io.thoughtware.teston.support.variable.service.VariableService;
import io.thoughtware.teston.test.web.scene.cases.service.WebSceneStepService;
import io.thoughtware.teston.test.web.scene.execute.model.WebSceneTestRequest;
import io.thoughtware.teston.test.web.scene.execute.model.WebSceneTestResponse;
import io.thoughtware.teston.test.web.scene.instance.model.WebSceneInstance;
import io.thoughtware.teston.test.web.scene.instance.service.WebSceneInstanceService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * web场景测试调度 服务
 */
@Service
public class WebSceneTestDispatchServiceImpl implements WebSceneTestDispatchService {

    private static Logger logger = LoggerFactory.getLogger(WebSceneTestDispatchServiceImpl.class);

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

    @Autowired
    InstanceService instanceService;

    @Autowired
    WebSceneCaseService webSceneCaseService;

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

    @Override
    public void execute(WebSceneTestRequest webSceneTestRequest) {
        String webSceneId = webSceneTestRequest.getWebSceneId();
        String webSceneInstanceId = createInitInstance(webSceneId);

        //执行
        try {
            executeStart(webSceneTestRequest);

            try {
                Thread.sleep(2000);
                //执行完，保存最终结果
                WebSceneTestResponse webSceneTestResponse = result(webSceneTestRequest);
                createStepInstanceList(webSceneTestResponse,webSceneInstanceId);
                Thread.sleep(2000);

                logger.info("------------------------------------------数据清理------------------------------------------");

                //清理数据
                if(enable){
                    webSceneTestService.cleanUpData(webSceneId);
                }else {
                    String agentUrl = agentConfigService.getAgent();
                    webSceneTestServiceRPC(agentUrl).cleanUpData(webSceneId);
                }
            }catch (Exception e){
                throw new ApplicationException(e);
            }
        } catch (Exception e){
            updateStatus(webSceneInstanceId,MagicValue.TEST_STATUS_FAIL);
            throw new ApplicationException(e);
        }
    }

    @Override
    public void executeStart(WebSceneTestRequest webSceneTestRequest){
        String webSceneId = webSceneTestRequest.getWebSceneId();

        //设置变量数据
        JSONObject variable = variableService.getVariable(webSceneId);
        webSceneTestRequest.setVariableJson(variable);

        //先查询所有的场景步骤
        StepCommonQuery stepCommonQuery = new StepCommonQuery();
        stepCommonQuery.setCaseId(webSceneId);
        stepCommonQuery.setCaseType(MagicValue.CASE_TYPE_WEB);
        List<StepCommon> stepCommonList = stepCommonService.findStepCommonList(stepCommonQuery);
        //设置步骤数据
        webSceneTestRequest.setStepCommonList(stepCommonList);

        //根据环境配置是否为内嵌
        //如果不是内嵌走rpc
        if(enable) {
            //调用执行方法返回结果数据
            webSceneTestService.execute(webSceneTestRequest);
        }else {
            String agentUrl = agentConfigService.getAgent();
            webSceneTestServiceRPC(agentUrl).execute(webSceneTestRequest);
        }
    }

    /**
     * 开始执行创建初始历史
     * @param webSceneId
     * @return
     */
    private String createInitInstance(String webSceneId){
        int stepNum = stepCommonService.findStepNum(webSceneId);
        WebSceneInstance webSceneInstance = new WebSceneInstance();
        webSceneInstance.setWebSceneId(webSceneId);
        webSceneInstance.setStepNum(stepNum);
        webSceneInstance.setFailNum(0);
        webSceneInstance.setPassNum(0);
        webSceneInstance.setPassRate("0.00%");
        webSceneInstance.setTotalDuration(0.0);
        String webSceneInstanceId = webSceneInstanceService.createWebSceneInstance(webSceneInstance);

        WebSceneCase webSceneCase = webSceneCaseService.findWebSceneCase(webSceneId);
        // 创建公共实例
        Instance instance = new Instance();
        instance.setId(webSceneInstanceId);
        instance.setBelongId(webSceneId);
        instance.setType(MagicValue.CASE_TYPE_WEB);
        instance.setName(webSceneCase.getTestCase().getName());
        instance.setRepositoryId(webSceneCase.getTestCase().getRepositoryId());

        //获取当前执行次数
        int executeNum = instanceService.getRecentExecuteNum(webSceneId);
        instance.setExecuteNumber(executeNum);

        //设置状态,开始执行
        instance.setStatus(MagicValue.TEST_STATUS_START);

        JSONObject instanceMap = new JSONObject();
        instanceMap.put("stepNum",webSceneInstance.getStepNum().toString());
        instanceMap.put("passNum",webSceneInstance.getPassNum().toString());
        instanceMap.put("passRate",webSceneInstance.getPassRate());
        instanceMap.put("failNum",webSceneInstance.getFailNum().toString());
        instanceMap.put("totalDuration",webSceneInstance.getTotalDuration().toString());
        instance.setContent(instanceMap.toString());

        instanceService.createInstance(instance);

        return webSceneInstanceId;
    }


    @Override
    public WebSceneTestResponse result(WebSceneTestRequest webSceneTestRequest) {

        //获取测试结果
        WebSceneTestResponse webSceneTestResponse = getResult(webSceneTestRequest);

        //status为0执行结束，存入历史
        updateInstance(webSceneTestResponse,webSceneTestRequest.getWebSceneId());
        return webSceneTestResponse;
    }

    @Override
    public WebSceneTestResponse getResult(WebSceneTestRequest webSceneTestRequest){
        WebSceneTestResponse webSceneTestResponse = new WebSceneTestResponse();

        //根据环境配置是否为内嵌
        //如果不是内嵌走rpc
        try{
            if(enable) {
                //调用执行方法返回结果数据
                webSceneTestResponse=  webSceneTestService.result(webSceneTestRequest);
            }else {
                String agentUrl = agentConfigService.getAgent();
                webSceneTestResponse =  webSceneTestServiceRPC(agentUrl).result(webSceneTestRequest);
            }
        }catch (Exception e){
            webSceneTestResponse.setStatus(0);
            throw new ApplicationException(e);
        }

        return webSceneTestResponse;
    }


    /**
     * 更新历史
     * @param webSceneTestResponse
     * @param webSceneId
     */
    private void updateInstance(WebSceneTestResponse webSceneTestResponse, String webSceneId){
        String instanceId = instanceService.getRecentExecuteInstanceId(webSceneId);
        if (webSceneTestResponse.getWebSceneInstance()!=null&&instanceId!=null) {
            WebSceneInstance webSceneInstance = webSceneTestResponse.getWebSceneInstance();
            webSceneInstance.setWebSceneId(webSceneId);
            webSceneInstance.setId(instanceId);
            webSceneInstanceService.updateWebSceneInstance(webSceneInstance);

            Instance instance = instanceService.findInstance(instanceId);
            JSONObject instanceMap = new JSONObject();
            instanceMap.put("stepNum",webSceneInstance.getStepNum().toString());
            instanceMap.put("passNum",webSceneInstance.getPassNum().toString());
            instanceMap.put("passRate",webSceneInstance.getPassRate());
            instanceMap.put("failNum",webSceneInstance.getFailNum().toString());
            instanceMap.put("totalDuration",webSceneInstance.getTotalDuration().toString());
            instance.setContent(instanceMap.toString());

            //agent返回的status为0 代表结束
            Integer status = webSceneTestResponse.getStatus();
            if(status==0){
                //根据result设置成功还是失败
                if(webSceneInstance.getResult()==1){
                    instance.setStatus(MagicValue.TEST_STATUS_SUCCESS);
                }else {
                    instance.setStatus(MagicValue.TEST_STATUS_FAIL);
                }
            }

            instanceService.updateInstance(instance);

            webSceneInstance.setInstance(instance);
        }else {
            updateStatus(instanceId,MagicValue.TEST_STATUS_FAIL);
        }
    }

    private void createStepInstanceList(WebSceneTestResponse webSceneTestResponse,String instanceId){
        //保存单个步骤
        if(CollectionUtils.isNotEmpty(webSceneTestResponse.getStepCommonInstanceList())){
            List<StepCommonInstance> stepCommonInstanceList = webSceneTestResponse.getStepCommonInstanceList();

            webSceneInstanceService.createStepInstance(stepCommonInstanceList,instanceId);
        }
    }


    /**
     * 状态更改
     * @param instanceId
     */
    private void updateStatus(String instanceId,String status){
        Instance instance = instanceService.findInstance(instanceId);
        instance.setStatus(status);
        instanceService.updateInstance(instance);
    }





}
