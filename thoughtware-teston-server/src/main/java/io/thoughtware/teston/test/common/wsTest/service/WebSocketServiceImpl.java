package io.thoughtware.teston.test.common.wsTest.service;

import com.alibaba.fastjson.JSONObject;

import io.thoughtware.teston.test.common.wstest.WsTestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;


@Service
public class WebSocketServiceImpl implements  WebSocketHandler {

    private static Logger logger = LoggerFactory.getLogger(WebSocketServiceImpl.class);

    static WsTestCommonFn wsTestCommonFn;
    public  WsTestCommonFn getWsTestCommonFn() {
        return wsTestCommonFn;
    }
    @Autowired
    public  void setWsTestCommonFn(WsTestCommonFn wsTestCommonFn) {
        WebSocketServiceImpl.wsTestCommonFn = wsTestCommonFn;
    }

    static WsTestService wsTestService;
    public WsTestService getWsTestService() {
        return wsTestService;
    }
    @Autowired
    public void setWsTestService(WsTestService wsTestService) {
        WebSocketServiceImpl.wsTestService = wsTestService;
    }



    public static Map<String, CompletableFuture<JSONObject>> futureMap = new ConcurrentHashMap<>();

    /**
     * agentSessionMap:使用线程安全map存储用户连接webscoket信息
     */
    public final static Map<String, WebSocketSession> agentSessionMap = new ConcurrentHashMap<>();

    /**
     * 建立websocket连接时调用该方法
     * 内存中存储当前agentId
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        JSONObject agentInfo = wsTestCommonFn.getAgentInfoFromQuery(session);
        String agentId = agentInfo.getString("agentId");
        logger.info("agentId -- {} connect server success", agentId);
        agentSessionMap.put(agentId, session);

        if(!"agent-default_localhost".equals(agentId)){
            wsTestCommonFn.connectInitAgent(agentInfo,agentId);
        }
    }


    /**
     * 接收到客户端发来的信息进行处理
     */
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        String agentId = wsTestCommonFn.getAgentIdFromAgentSessionMap(session, agentSessionMap);
        if(agentId==null){return;}

        String msg = message.getPayload().toString();
        JSONObject jsonMsg = JSONObject.parseObject(msg);


        if(jsonMsg.containsKey("type")){
            String type = jsonMsg.getString("type");

            //接收到不同的用例消息并处理
            wsTestService.processDiffCaseAgentMessage(type,jsonMsg);

            // 从Map中取出CompletableFuture实例并设置结果
            String futureId = agentId + "_" + type;
            CompletableFuture<JSONObject> future = futureMap.remove(futureId);
            if (future != null) {
                future.complete(jsonMsg);
            }
        }

    }


    /**
     * 关闭websocket时调用该方法
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        String agentId = wsTestCommonFn.getAgentIdFromAgentSessionMap(session, agentSessionMap);

        if (agentId != null) {
            wsTestCommonFn.updateAgentStatus(agentId);
            agentSessionMap.remove(agentId);
            logger.info("AgentId -- {}  closed!", agentId);
        } else {
            logger.error("关闭时，获取AgentId为空");
        }
    }

    /**
     * 传输过程出现异常时，调用该方法
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable e) throws Exception {
        String agentId = wsTestCommonFn.getAgentIdFromAgentSessionMap(session, agentSessionMap);

        WebSocketMessage<String> message = new TextMessage("AgentId -- " + agentId + " 异常信息：" + e.getMessage());
        session.sendMessage(message);
    }

    /**
     * 用于确定 WebSocket 处理器是否支持处理部分消息
     */
    @Override
    public boolean supportsPartialMessages() {
        return false;
    }


}
