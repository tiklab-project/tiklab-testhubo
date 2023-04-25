package io.tiklab.teston.test.apix.http.unit.execute.service;

import io.tiklab.core.exception.ApplicationException;
import io.tiklab.rpc.client.router.lookup.FixedLookup;
import io.tiklab.teston.agent.api.http.unit.ApiUnitTestService;
import io.tiklab.teston.support.agentconfig.model.AgentConfigQuery;
import io.tiklab.teston.test.apix.http.unit.cases.model.ApiUnitCase;
import io.tiklab.teston.test.apix.http.unit.cases.model.ApiUnitCaseDataConstruction;
import io.tiklab.teston.test.apix.http.unit.cases.service.ApiUnitCaseService;
import io.tiklab.teston.test.apix.http.unit.cases.service.AssertService;
import io.tiklab.teston.test.apix.http.unit.execute.model.ApiUnitTestRequest;
import io.tiklab.teston.test.apix.http.unit.instance.model.ApiUnitInstance;
import io.tiklab.teston.test.apix.http.unit.instance.model.ApiUnitInstanceBind;
import io.tiklab.teston.test.apix.http.unit.instance.model.ApiUnitInstanceBindQuery;
import io.tiklab.teston.test.apix.http.unit.instance.service.ApiUnitInstanceBindService;
import io.tiklab.teston.test.apix.http.unit.instance.service.ApiUnitInstanceService;
import io.tiklab.teston.test.apix.http.unit.instance.service.AssertInstanceService;
import io.tiklab.teston.support.utils.RpcClientApixUtil;
import io.tiklab.teston.support.agentconfig.model.AgentConfig;
import io.tiklab.teston.support.agentconfig.service.AgentConfigService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.script.Bindings;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

/**
 * 接口单元测试调度 服务
 */
@Service
public class ApiUnitExecuteDispatchServiceImpl implements ApiUnitExecuteDispatchService {

    @Autowired
    ApiUnitCaseService apiUnitCaseService;

    @Autowired
    ApiUnitInstanceService apiUnitInstanceService;


    @Autowired
    AssertService assertService;

    @Autowired
    AssertInstanceService assertInstanceService;

    @Autowired
    ApiUnitInstanceBindService apiUnitInstanceBindService;

    @Autowired
    AgentConfigService agentConfigService;

    @Autowired
    RpcClientApixUtil rpcClientApixUtil;

    @Autowired
    ApiUnitTestService apiUnitTestService;

    /**
     *  环境中获取是否是内嵌agent
     */
    @Value("${teston-agent.embbed.enable:false}")
    Boolean enable;

    /**
     * rpc 调用
     */
    ApiUnitTestService apiUnitTestServiceRpc(String agentUrl){
        return rpcClientApixUtil.rpcClient().getBean(ApiUnitTestService.class, new FixedLookup(agentUrl));
    }

    @Override
    public ApiUnitInstance execute(ApiUnitTestRequest apiUnitTestRequest) {
        String apiUnitId = apiUnitTestRequest.getApiUnitCase().getId();
        ApiUnitCase apiUnitCase = apiUnitCaseService.findApiUnitCase(apiUnitId);

        //数据准备
        ApiUnitCaseDataConstruction apiUnitCaseDataConstruction = apiUnitCaseService.findApiUnitCaseExt(apiUnitCase);

        apiUnitTestRequest.setApiUnitCase(apiUnitCase);
        apiUnitTestRequest.setApiUnitCaseExt(apiUnitCaseDataConstruction);

        ApiUnitInstance apiUnitInstance = null;

        //根据环境配置是否为内嵌
        //如果不是内嵌走rpc
        try {
            if(enable){
                apiUnitInstance = apiUnitTestService.execute(apiUnitTestRequest);
            }else {
                List<AgentConfig> agentConfigList = agentConfigService.findAgentConfigList(new AgentConfigQuery());
                if( CollectionUtils.isNotEmpty(agentConfigList)){
                    AgentConfig agentConfig = agentConfigList.get(0);
                    apiUnitInstance = apiUnitTestServiceRpc(agentConfig.getUrl()).execute(apiUnitTestRequest);
                }
            }
        }catch (Exception e){
            throw new ApplicationException("agent错误");
        }


        //后置
        if(apiUnitTestRequest.getApiUnitCaseExt().getAfterScript()!=null){
            Map afterScript = actionAfterScript(apiUnitTestRequest.getApiUnitCaseExt().getAfterScript());

            if(afterScript!=null&&afterScript.get("preUrl")!=null){
                Object preUrl = afterScript.get("preUrl");

                if((Boolean) preUrl){
                    apiUnitInstance.setAfterScript(apiUnitTestRequest.getApiEnv());
                }
            }
        }



        //测试计划中设置了执行类型，其他没设置
        if(apiUnitTestRequest.getExeType()==null){
            saveInstance(apiUnitInstance,apiUnitId);
        }


        return apiUnitInstance;
    }


    private void saveInstance(ApiUnitInstance apiUnitInstance, String apiUnitId){
        //apiUnit 历史实例中间表,获取上次创建的实例
        ApiUnitInstanceBindQuery apiUnitInstanceBindQuery = new ApiUnitInstanceBindQuery();
        apiUnitInstanceBindQuery.setApiUnitId(apiUnitId);
        List<ApiUnitInstanceBind> apiUnitInstanceBindList = apiUnitInstanceBindService.findApiUnitInstanceBindList(apiUnitInstanceBindQuery);
        if(apiUnitInstanceBindList!=null&&apiUnitInstanceBindList.size()>0){
            String instanceId = apiUnitInstanceBindList.get(0).getId();
            //前一次的历史实例
            ApiUnitInstance preApiUnitInstance = apiUnitInstanceService.findApiUnitInstance(instanceId);
            //拿到执行的次数
            Integer executeNumber =preApiUnitInstance.getExecuteNumber();
            apiUnitInstance.setExecuteNumber(++executeNumber);
        }else {
            apiUnitInstance.setExecuteNumber(1);
        }


        //执行后数据保存
        String apiUnitInstanceId = apiUnitInstanceService.saveApiUnitInstanceToSql(apiUnitInstance);

        //关联表，相当于中间表
        ApiUnitInstanceBind apiUnitInstanceBind = new ApiUnitInstanceBind();
        apiUnitInstanceBind.setId(apiUnitInstanceId);
        apiUnitInstanceBind.setApiUnitInstance(new ApiUnitInstance().setId(apiUnitInstanceId));
        apiUnitInstanceBind.setApiUnitId(apiUnitId);
        apiUnitInstanceBindService.createApiUnitInstanceBind(apiUnitInstanceBind);

    }



    private Map<String, Object> actionAfterScript(String script){

        if (script!=null){

            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("JavaScript");

            URL resourceUrl = getClass().getClassLoader().getResource("static/script.js");
            String path = resourceUrl.getPath();
            try (FileInputStream fip = new FileInputStream(path);
                 InputStreamReader reader = new InputStreamReader(fip, StandardCharsets.UTF_8)) {

                // 读取 JavaScript 文件中的代码
                engine.eval(reader);

                // 在解释器中添加一个名为"dk"的全局对象
                engine.put("to", engine.get("to"));

                // 执行 JavaScript 函数
                engine.eval(script);

                // 调用 getData 方法
                Invocable invocable = (Invocable) engine;
                Object result = invocable.invokeMethod(engine.get("to"), "getPreUrl");
                // 将 JavaScript 对象转换为 Java Map
                Bindings bindings = engine.createBindings();
                bindings.put("result", result);
                Map<String, Object> resultMap = (Map<String, Object>) engine.eval("JSON.parse(JSON.stringify(result))", bindings);

                return resultMap;

            } catch (Exception e) {
                throw new ApplicationException(e);
            }
        }
        return null;
    }


}
