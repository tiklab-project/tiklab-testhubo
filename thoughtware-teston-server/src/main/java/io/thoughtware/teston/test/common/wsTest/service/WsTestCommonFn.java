package io.thoughtware.teston.test.common.wsTest.service;

import com.alibaba.fastjson.JSONObject;
import io.thoughtware.teston.support.agentconfig.model.AgentConfig;
import io.thoughtware.teston.support.agentconfig.service.AgentConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class WsTestCommonFn {

    @Autowired
    AgentConfigService agentConfigService;

    /**
     * 拼接获取agentId
     * @param session
     * @return
     */
    public JSONObject getAgentInfoFromQuery(WebSocketSession session) {
        URI uri = session.getUri();
        String query = uri.getQuery();

        // 使用Map.Entry来存储key-value对
        Map<String, String> queryParams = Arrays.stream(query.split("&"))
                .filter(param -> param.contains("="))
                .map(param -> param.split("=", 2))
                .collect(Collectors.toMap(
                        keyValue -> keyValue[0],
                        keyValue -> keyValue.length > 1 ? keyValue[1] : "",
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new
                ));

        // 从Map中获取name和address
        String name = queryParams.getOrDefault("name", "");
        String address = queryParams.getOrDefault("address", "");

        // 组合代理 ID
        String agentId = String.join("_", name.trim(), address.trim());

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", name);
        jsonObject.put("address",address);
        jsonObject.put("status","online");
        jsonObject.put("agentId",agentId);

        return jsonObject;
    }

    /**
     * 从AgentSessionMap中获取AgentId
     * @param session
     * @param agentSessionMap
     * @return
     */
    public String getAgentIdFromAgentSessionMap(WebSocketSession session, Map<String, WebSocketSession> agentSessionMap){
        String agentId = agentSessionMap.entrySet().stream()
                .filter(entry -> entry.getValue() == session)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse("");
        return agentId;
    }

    /**
     * 从AgentSessionMap中获取Session
     * @param agentId 代理ID
     * @param agentSessionMap 代理会话映射
     * @return 匹配的WebSocketSession，如果没有找到则返回null
     */
    public WebSocketSession getSessionFromAgentSessionMap(String agentId, Map<String, WebSocketSession> agentSessionMap){
        return agentSessionMap.entrySet().stream()
                .filter(entry -> entry.getKey().equals(agentId))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElse(null);
    }

    /**
     * 连接上如果数据库存在就更新，不存在就创建
     * @param agentObj
     * @param agentId
     */
    public void connectInitAgent( JSONObject agentObj,String agentId){
        String name = agentObj.getString("name");
        String status = agentObj.getString("status");
        String address = agentObj.getString("address");

        //不等于默认agent
        if(!"agent-default".equals(name)){
            AgentConfig agent = agentConfigService.findAgentConfig(agentId);

            if(agent==null){
                AgentConfig agentConfig = new AgentConfig();
                agentConfig.setId(agentId);
                agentConfig.setName(name);
                agentConfig.setStatus(status);
                agentConfig.setAddress(address);
                agentConfigService.createAgentConfig(agentConfig);
            }else {
                AgentConfig agentConfig = new AgentConfig();
                agentConfig.setId(agentId);
                agentConfig.setStatus(status);
                agentConfigService.updateAgentConfig(agentConfig);
            }
        }
    }

    /**
     * 更新agent状态
     * @param agentId
     */
    public void updateAgentStatus(String agentId){
        AgentConfig agentConfig = new AgentConfig();
        agentConfig.setId(agentId);
        agentConfig.setStatus("offline");
        agentConfigService.updateAgentConfig(agentConfig);
    }


}
