package net.tiklab.teston.apptest.scenetest.service;

import net.tiklab.teston.apptest.scenetest.model.AppSceneStep;
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
public class AppSceneStepServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(AppSceneStepServiceImplTest.class);

    @Autowired
    AppSceneStepService appSceneStepService;

    static String id;

    @Test
    public void test01ForSaveAppSceneStep() {
        AppSceneStep appSceneStep = JMockit.mock(AppSceneStep.class);

        id = appSceneStepService.createAppSceneStep(appSceneStep);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateAppSceneStep(){
        AppSceneStep appSceneStep = JMockit.mock(AppSceneStep.class);
        appSceneStep.setId(id);

        appSceneStepService.updateAppSceneStep(appSceneStep);
    }

    @Test
    public void test03ForFindAppSceneStep() {
        AppSceneStep appSceneStep = appSceneStepService.findAppSceneStep(id);

        assertNotNull(appSceneStep);
    }

    @Test
    public void test04ForFindAllAppSceneStep() {
        List<AppSceneStep> appSceneStepList = appSceneStepService.findAllAppSceneStep();

        assertNotNull(appSceneStepList);
    }

    @Test
    public void test05ForDeleteAppSceneStep(){
        appSceneStepService.deleteAppSceneStep(id);
    }
}