package io.thoughtware.testhubo.task;


import io.thoughtware.testhubo.agent.ws.TestHuboAgentWsConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WsInitJob implements ApplicationRunner {
    private static final Logger logger = LoggerFactory.getLogger(WsInitJob.class);

    @Autowired
    TestHuboAgentWsConfiguration testOnAgentWsConfiguration;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        testOnAgentWsConfiguration.webSocketClient();
    }
}
