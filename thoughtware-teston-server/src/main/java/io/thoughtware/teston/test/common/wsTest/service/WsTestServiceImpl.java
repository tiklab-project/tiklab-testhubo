package io.thoughtware.teston.test.common.wsTest.service;

import com.alibaba.fastjson.JSONObject;
import io.thoughtware.teston.common.MagicValue;
import io.thoughtware.teston.test.apix.http.perf.execute.service.ApiPerfExecuteDispatchService;
import io.thoughtware.teston.test.common.wstest.WsTestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.concurrent.CompletableFuture;

@Service
public class WsTestServiceImpl implements WsTestService {
    private static Logger logger = LoggerFactory.getLogger(WsTestServiceImpl.class);

    @Autowired
    WsTestCommonFn wsTestCommonFn;

    @Autowired
    ApiPerfExecuteDispatchService apiPerfExecuteDispatchService;

    /**
     * 发送信息
     * @param agentId
     * @param params
     * @param futureId
     */
    @Override
    public void sendMessageExe(String agentId, JSONObject params, String futureId) {
        WebSocketSession session = wsTestCommonFn.getSessionFromAgentSessionMap(agentId, WebSocketServiceImpl.agentSessionMap);

        // 如果 futureId 不为 null 且不为空，则使用 CompletableFuture
        CompletableFuture<JSONObject> future = null;
        if (futureId != null && !futureId.isEmpty()) {
            future = new CompletableFuture<>();
            WebSocketServiceImpl.futureMap.put(futureId, future);
        }

        try {
            session.sendMessage(new TextMessage(params.toJSONString()));
        } catch (Exception e) {
            logger.error(String.valueOf(e));
            if (future != null) {
                future.completeExceptionally(e);
                WebSocketServiceImpl.futureMap.remove(futureId);
            }
        }
    }

    @Override
    public void processDiffCaseAgentMessage(String type,JSONObject jsonMsg){
        if(type.equals(MagicValue.CASE_TYPE_API_PERFORM)){
            apiPerfExecuteDispatchService.getAgentTestData(jsonMsg);
        }

    }


}
