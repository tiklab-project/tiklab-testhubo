package net.tiklab.teston.support.agentconfig.service;

import net.tiklab.postin.client.mock.JMockit;
import net.tiklab.teston.config.TestConfig;
import net.tiklab.teston.support.agentconfig.model.AgentConfig;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {TestConfig.class})
@Transactional
@Rollback(false)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AgentConfigServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(AgentConfigServiceImplTest.class);

    @Autowired
    AgentConfigService agentConfigService;

    static String id;

    @Test
    public void test01ForSaveAgentConfig() {
        AgentConfig agentConfig = JMockit.mock(AgentConfig.class);

        id = agentConfigService.createAgentConfig(agentConfig);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateAgentConfig(){
        AgentConfig agentConfig = JMockit.mock(AgentConfig.class);
        agentConfig.setId(id);

        agentConfigService.updateAgentConfig(agentConfig);
    }

    @Test
    public void test03ForFindAgentConfig() {
        AgentConfig agentConfig = agentConfigService.findAgentConfig(id);

        assertNotNull(agentConfig);
    }

    @Test
    public void test04ForFindAllAgentConfig() {
        List<AgentConfig> agentConfigList = agentConfigService.findAllAgentConfig();

        assertNotNull(agentConfigList);
    }

    @Test
    public void test05ForDeleteAgentConfig(){
        agentConfigService.deleteAgentConfig(id);
    }
}