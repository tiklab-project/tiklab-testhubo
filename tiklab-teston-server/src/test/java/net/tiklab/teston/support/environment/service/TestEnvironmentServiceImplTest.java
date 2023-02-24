package net.tiklab.teston.support.environment.service;

import net.tiklab.teston.support.environment.model.ApiEnv;
import net.tiklab.postin.client.mock.JMockit;
import net.tiklab.teston.config.TestConfig;
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
public class TestEnvironmentServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(TestEnvironmentServiceImplTest.class);

    @Autowired
    ApiEnvService apiEnvService;

    static String id;

    @Test
    public void test01ForSaveApiEnv() {
        ApiEnv apiEnv = JMockit.mock(ApiEnv.class);

        id = apiEnvService.createApiEnv(apiEnv);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateApiEnv(){
        ApiEnv apiEnv = JMockit.mock(ApiEnv.class);
        apiEnv.setId(id);

        apiEnvService.updateApiEnv(apiEnv);
    }

    @Test
    public void test03ForFindApiEnv() {
        ApiEnv apiEnv = apiEnvService.findApiEnv(id);

        assertNotNull(apiEnv);
    }

    @Test
    public void test04ForFindAllApiEnv() {
        List<ApiEnv> apiEnvList = apiEnvService.findAllApiEnv();

        assertNotNull(apiEnvList);
    }

    @Test
    public void test05ForDeleteApiEnv(){
        apiEnvService.deleteApiEnv(id);
    }
}