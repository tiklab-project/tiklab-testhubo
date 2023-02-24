package net.tiklab.teston.test.webperf.execute.service;

import net.tiklab.rpc.client.router.lookup.FixedLookup;
import net.tiklab.teston.common.RpcClientUtil;
import net.tiklab.teston.common.TestUtil;
import net.tiklab.teston.support.agentconfig.model.AgentConfig;
import net.tiklab.teston.support.agentconfig.model.AgentConfigQuery;
import net.tiklab.teston.support.agentconfig.service.AgentConfigService;
import net.tiklab.teston.test.webperf.cases.model.WebPerfCase;
import net.tiklab.teston.test.webperf.cases.model.WebPerfStep;
import net.tiklab.teston.test.webperf.cases.model.WebPerfStepQuery;
import net.tiklab.teston.test.webperf.execute.model.WebPerfTestRequest;
import net.tiklab.teston.test.webperf.execute.model.WebPerfTestResponse;
import net.tiklab.teston.test.webperf.instance.model.WebPerfInstance;
import net.tiklab.teston.test.webperf.instance.model.WebPerfInstanceQuery;
import net.tiklab.teston.test.webperf.cases.service.WebPerfCaseService;
import net.tiklab.teston.test.webperf.instance.service.WebPerfInstanceService;
import net.tiklab.teston.test.webperf.cases.service.WebPerfStepService;
import net.tiklab.teston.test.webscene.instance.model.WebSceneInstance;
import net.tiklab.teston.test.webscene.cases.model.WebSceneStep;
import net.tiklab.teston.test.webscene.cases.model.WebSceneStepQuery;
import net.tiklab.teston.test.webscene.execute.model.WebSceneTestRequest;
import net.tiklab.teston.test.webscene.cases.service.WebSceneStepService;
import net.tiklab.testonagent.webtest.perfcase.service.WebPerfTestService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WebPerfTestDispatchServiceImpl implements WebPerfTestDispatchService {

    public static final Logger logger = LoggerFactory.getLogger(WebPerfTestDispatchServiceImpl.class);

    @Autowired
    WebSceneStepService webSceneStepService;

    @Autowired
    WebPerfCaseService webPerfCaseService;

    @Autowired
    WebPerfStepService webPerfStepService;

    @Autowired
    WebPerfInstanceService webPerfInstanceService;

    @Autowired
    AgentConfigService agentConfigService;

    @Autowired
    RpcClientUtil rpcClientUtil;

    @Autowired
    TestUtil testUtil;

    WebPerfTestService webPerfTestService(String agentId){
        AgentConfig agentConfig = agentConfigService.findAgentConfig(agentId);

        return rpcClientUtil.rpcClient().getBean(WebPerfTestService.class, new FixedLookup(agentConfig.getUrl()));
    }

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
    private String webPerfInstanceId;

    @Override
    public void execute(WebPerfTestRequest webPerfTestRequest) {
        //开始执行
        status=1;
        webPerfInstanceId=null;


        String webPerfId = webPerfTestRequest.getWebPerfCase().getId();
        //查询c
        WebPerfCase webPerfCase = webPerfCaseService.findWebPerfCase(webPerfId);
        webPerfTestRequest.setWebPerfCase(webPerfCase);

        //查询所有场景
        WebPerfStepQuery webPerfStepQuery = new WebPerfStepQuery();
        webPerfStepQuery.setWebPerfId(webPerfId);
        List<WebPerfStep> webPerfStepList = webPerfStepService.findWebPerfStepList(webPerfStepQuery);

        List<WebSceneTestRequest> webSceneTestRequestList = processExeData(webPerfStepList);
        webPerfTestRequest.setWebSceneTestRequestList(webSceneTestRequestList);

        //执行次数
        executeCount = webPerfCase.getExecuteCount();

        agentConfigList = agentConfigService.findAgentConfigList(new AgentConfigQuery());
        //agent数量
        int agentSize = agentConfigList.size();
        //执行方式
        Integer executeType = webPerfCase.getExecuteType();

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

        for(int i = 0; i<agentSize;i++){
            //
            webPerfTestRequest.setExeNum(distributionList.get(i));

            //获取agentId，agentList index 从0开始
            AgentConfig agentConfig = agentConfigList.get(i);

            //执行压力测试
            webPerfTestService(agentConfig.getId()).execute(webPerfTestRequest);
        }

    }

    @Override
    public WebPerfTestResponse exeResult(WebPerfTestRequest webPerfTestRequest) {
        WebPerfTestResponse webPerfTestResponse = new WebPerfTestResponse();

        //用于存放agent返回来的实例
        ArrayList<WebSceneInstance> arrayList = new ArrayList<>();

        for(int i = 0;i<agentConfigList.size();i++){
            WebPerfTestResponse response = webPerfTestService(agentConfigList.get(i).getId()).exeResult(webPerfTestRequest);

            if(CollectionUtils.isNotEmpty(response.getWebSceneInstanceList())){
                arrayList.addAll(response.getWebSceneInstanceList());
            }
        }

        webPerfTestResponse.setWebSceneInstanceList(arrayList);

        if(webPerfTestResponse.getWebSceneInstanceList().size()==executeCount){
            status=2;
        }else {
            status=1;
        }
        webPerfTestResponse.setStatus(status);

        WebPerfInstance webPerfInstance = processPerfTestData(webPerfTestResponse);
        webPerfTestResponse.setWebPerfInstance(webPerfInstance);

        //测试计划中设置了值
        if(webPerfTestRequest.getExeType()==null){
            //请求一次获取一次结果，存入数据库
            String webPerfId = webPerfTestRequest.getWebPerfCase().getId();
            webPerfInstance.setWebPerfId(webPerfId);

            if(webPerfInstanceId==null){
                WebPerfInstanceQuery webPerfInstanceQuery = new WebPerfInstanceQuery();
                webPerfInstanceQuery.setWebPerfId(webPerfId);
                List<WebPerfInstance> webPerfInstanceList = webPerfInstanceService.findWebPerfInstanceList(webPerfInstanceQuery);
                if(webPerfInstanceList!=null&&webPerfInstanceList.size()>0){
                    Integer executeNumber = webPerfInstanceList.get(0).getExecuteNumber();

                    webPerfInstance.setExecuteNumber(++executeNumber);
                }else {
                    webPerfInstance.setExecuteNumber(1);
                }

                webPerfInstanceId = webPerfInstanceService.createWebPerfInstance(webPerfInstance);
            }else {
                webPerfInstance.setId(webPerfInstanceId);
                webPerfInstanceService.updateWebPerfInstance(webPerfInstance);
            }

        }

        return webPerfTestResponse;
    }


    /**
     * 构造执行数据
     * @param webPerfStepList
     * @return
     */
    private  List<WebSceneTestRequest> processExeData(List<WebPerfStep> webPerfStepList) {
        List<WebSceneTestRequest> webSceneTestRequestList = new ArrayList<>();

        //循环压力测试绑定的场景
        if(CollectionUtils.isNotEmpty(webPerfStepList)){
            for(WebPerfStep webPerfStep : webPerfStepList){
                //构造测试请求数据
                WebSceneTestRequest webSceneTestRequest = new WebSceneTestRequest();

                //查询每个场景下的步骤
                WebSceneStepQuery webSceneStepQuery = new WebSceneStepQuery();
                webSceneStepQuery.setWebSceneId(webPerfStep.getWebScene().getId());
                List<WebSceneStep> webSceneStepList = webSceneStepService.findWebSceneStepList(webSceneStepQuery);


                webSceneTestRequest.setWebSceneStepList(webSceneStepList);

                //设置webDriver
//                webSceneTestRequest.setWebDriver(SeleniumDriverContext.webPerfDriver());

                webSceneTestRequestList.add(webSceneTestRequest);
            }

        }

        return webSceneTestRequestList;
    }


    /**
     * 处理结果数据
     * @param webPerfTestResponse
     * @return
     */
    private WebPerfInstance processPerfTestData(WebPerfTestResponse webPerfTestResponse) {
        WebPerfInstance webPerfInstance = new WebPerfInstance();

        int size = webPerfTestResponse.getWebSceneInstanceList().size();
        webPerfInstance.setTotal(size);

        int passNum = 0;
        for(WebSceneInstance webSceneInstance : webPerfTestResponse.getWebSceneInstanceList()){
            if(webSceneInstance.getResult().equals(1)){
                passNum++;
            }
        }

        String passRate= testUtil.processRate(passNum, size);

        webPerfInstance.setPassRate(passRate);
        webPerfInstance.setPassNum(passNum);

        webPerfInstance.setFailNum(size-passNum);
        String errorRate = testUtil.processRate(size - passNum, size);
        webPerfInstance.setErrorRate(errorRate);

        if(size==passNum){
            webPerfInstance.setResult(1);
        }else {
            webPerfInstance.setResult(0);
        }


        return webPerfInstance;
    }


}
