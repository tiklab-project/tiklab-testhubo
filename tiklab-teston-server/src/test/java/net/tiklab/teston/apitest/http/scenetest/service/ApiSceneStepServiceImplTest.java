package net.tiklab.teston.apitest.http.scenetest.service;

import net.tiklab.teston.apitest.http.scenetest.model.ApiSceneStep;
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
public class ApiSceneStepServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(ApiSceneStepServiceImplTest.class);

    @Autowired
    ApiSceneStepService apiSceneStepService;

    static String id;

    @Test
    public void test01ForSaveApiSceneStep() {
        ApiSceneStep apiSceneStep = JMockit.mock(ApiSceneStep.class);

        id = apiSceneStepService.createApiSceneStep(apiSceneStep);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateApiSceneStep(){
        ApiSceneStep apiSceneStep = JMockit.mock(ApiSceneStep.class);
        apiSceneStep.setId(id);

        apiSceneStepService.updateApiSceneStep(apiSceneStep);
    }

    @Test
    public void test03ForFindApiSceneStep() {
        ApiSceneStep apiSceneStep = apiSceneStepService.findApiSceneStep(id);

        assertNotNull(apiSceneStep);
    }

    @Test
    public void test04ForFindAllApiSceneStep() {
        List<ApiSceneStep> apiSceneStepList = apiSceneStepService.findAllApiSceneStep();

        assertNotNull(apiSceneStepList);
    }

    @Test
    public void test05ForDeleteApiSceneStep(){
        apiSceneStepService.deleteApiSceneStep(id);
    }
}