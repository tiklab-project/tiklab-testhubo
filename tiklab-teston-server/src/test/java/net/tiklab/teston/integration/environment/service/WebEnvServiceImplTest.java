package net.tiklab.teston.integration.environment.service;

import net.tiklab.teston.config.TestConfig;
import net.tiklab.teston.integration.environment.model.WebEnv;
import net.tiklab.postin.client.mock.JMockit;
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
public class WebEnvServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(WebEnvServiceImplTest.class);

    @Autowired
    WebEnvService webEnvService;

    static String id;

    @Test
    public void test01ForSaveWebEnv() {
        WebEnv webEnv = JMockit.mock(WebEnv.class);

        id = webEnvService.createWebEnv(webEnv);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateWebEnv(){
        WebEnv webEnv = JMockit.mock(WebEnv.class);
        webEnv.setId(id);

        webEnvService.updateWebEnv(webEnv);
    }

    @Test
    public void test03ForFindWebEnv() {
        WebEnv webEnv = webEnvService.findWebEnv(id);

        assertNotNull(webEnv);
    }

    @Test
    public void test04ForFindAllWebEnv() {
        List<WebEnv> webEnvList = webEnvService.findAllWebEnv();

        assertNotNull(webEnvList);
    }

    @Test
    public void test05ForDeleteWebEnv(){
        webEnvService.deleteWebEnv(id);
    }
}