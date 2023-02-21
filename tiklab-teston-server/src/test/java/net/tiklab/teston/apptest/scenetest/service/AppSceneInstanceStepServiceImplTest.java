package net.tiklab.teston.apptest.scenetest.service;

import net.tiklab.teston.apptest.scenetest.model.AppSceneInstanceStep;
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
public class AppSceneInstanceStepServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(AppSceneInstanceStepServiceImplTest.class);

    @Autowired
    AppSceneInstanceStepService appSceneInstanceStepService;

    static String id;

    @Test
    public void test01ForSaveAppSceneInstanceStep() {
        AppSceneInstanceStep appSceneInstanceStep = JMockit.mock(AppSceneInstanceStep.class);

        id = appSceneInstanceStepService.createAppSceneInstanceStep(appSceneInstanceStep);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateAppSceneInstanceStep(){
        AppSceneInstanceStep appSceneInstanceStep = JMockit.mock(AppSceneInstanceStep.class);
        appSceneInstanceStep.setId(id);

        appSceneInstanceStepService.updateAppSceneInstanceStep(appSceneInstanceStep);
    }

    @Test
    public void test03ForFindAppSceneInstanceStep() {
        AppSceneInstanceStep appSceneInstanceStep = appSceneInstanceStepService.findAppSceneInstanceStep(id);

        assertNotNull(appSceneInstanceStep);
    }

    @Test
    public void test04ForFindAllAppSceneInstanceStep() {
        List<AppSceneInstanceStep> appSceneInstanceStepList = appSceneInstanceStepService.findAllAppSceneInstanceStep();

        assertNotNull(appSceneInstanceStepList);
    }

    @Test
    public void test05ForDeleteAppSceneInstanceStep(){
        appSceneInstanceStepService.deleteAppSceneInstanceStep(id);
    }
}