package net.tiklab.teston.webtest.scenetest.service;

import net.tiklab.teston.config.TestConfig;
import net.tiklab.teston.test.webscene.instance.model.WebSceneInstanceStep;
import net.tiklab.postin.client.mock.JMockit;
import net.tiklab.teston.test.webscene.instance.service.WebSceneInstanceStepService;
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
public class WebSceneInstanceStepServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(WebSceneInstanceStepServiceImplTest.class);

    @Autowired
    WebSceneInstanceStepService webSceneInstanceStepService;

    static String id;

    @Test
    public void test01ForSaveWebSceneInstanceStep() {
        WebSceneInstanceStep webSceneInstanceStep = JMockit.mock(WebSceneInstanceStep.class);

        id = webSceneInstanceStepService.createWebSceneInstanceStep(webSceneInstanceStep);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateWebSceneInstanceStep(){
        WebSceneInstanceStep webSceneInstanceStep = JMockit.mock(WebSceneInstanceStep.class);
        webSceneInstanceStep.setId(id);

        webSceneInstanceStepService.updateWebSceneInstanceStep(webSceneInstanceStep);
    }

    @Test
    public void test03ForFindWebSceneInstanceStep() {
        WebSceneInstanceStep webSceneInstanceStep = webSceneInstanceStepService.findWebSceneInstanceStep(id);

        assertNotNull(webSceneInstanceStep);
    }

    @Test
    public void test04ForFindAllWebSceneInstanceStep() {
        List<WebSceneInstanceStep> webSceneInstanceStepList = webSceneInstanceStepService.findAllWebSceneInstanceStep();

        assertNotNull(webSceneInstanceStepList);
    }

    @Test
    public void test05ForDeleteWebSceneInstanceStep(){
        webSceneInstanceStepService.deleteWebSceneInstanceStep(id);
    }
}