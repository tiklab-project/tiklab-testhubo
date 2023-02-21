package net.tiklab.teston.integration.environment.service;

import net.tiklab.teston.config.TestConfig;
import net.tiklab.teston.integration.environment.model.AppEnv;
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
public class AppEnvServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(AppEnvServiceImplTest.class);

    @Autowired
    AppEnvService appEnvService;

    static String id;

    @Test
    public void test01ForSaveAppEnv() {
        AppEnv appEnv = JMockit.mock(AppEnv.class);

        id = appEnvService.createAppEnv(appEnv);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateAppEnv(){
        AppEnv appEnv = JMockit.mock(AppEnv.class);
        appEnv.setId(id);

        appEnvService.updateAppEnv(appEnv);
    }

    @Test
    public void test03ForFindAppEnv() {
        AppEnv appEnv = appEnvService.findAppEnv(id);

        assertNotNull(appEnv);
    }

    @Test
    public void test04ForFindAllAppEnv() {
        List<AppEnv> appEnvList = appEnvService.findAllAppEnv();

        assertNotNull(appEnvList);
    }

    @Test
    public void test05ForDeleteAppEnv(){
        appEnvService.deleteAppEnv(id);
    }
}