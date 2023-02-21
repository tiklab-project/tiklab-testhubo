package net.tiklab.teston.webtest.scenetest.service;

import net.tiklab.teston.config.TestConfig;
import net.tiklab.teston.webtest.scenetest.model.WebSceneStep;
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
public class WebSceneStepServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(WebSceneStepServiceImplTest.class);

    @Autowired
    WebSceneStepService webSceneStepService;

    static String id;

    @Test
    public void test01ForSaveWebSceneStep() {
        WebSceneStep webSceneStep = JMockit.mock(WebSceneStep.class);

        id = webSceneStepService.createWebSceneStep(webSceneStep);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateWebSceneStep(){
        WebSceneStep webSceneStep = JMockit.mock(WebSceneStep.class);
        webSceneStep.setId(id);

        webSceneStepService.updateWebSceneStep(webSceneStep);
    }

    @Test
    public void test03ForFindWebSceneStep() {
        WebSceneStep webSceneStep = webSceneStepService.findWebSceneStep(id);

        assertNotNull(webSceneStep);
    }

    @Test
    public void test04ForFindAllWebSceneStep() {
        List<WebSceneStep> webSceneStepList = webSceneStepService.findAllWebSceneStep();

        assertNotNull(webSceneStepList);
    }

    @Test
    public void test05ForDeleteWebSceneStep(){
        webSceneStepService.deleteWebSceneStep(id);
    }
}