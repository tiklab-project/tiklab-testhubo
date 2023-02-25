package net.tiklab.teston.apptest.perftest.service;

import net.tiklab.teston.test.app.perf.cases.model.AppPerfStep;
import net.tiklab.postin.client.mock.JMockit;
import net.tiklab.teston.config.TestConfig;
import net.tiklab.teston.test.app.perf.cases.service.AppPerfStepService;
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
public class AppPerfStepServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(AppPerfStepServiceImplTest.class);

    @Autowired
    AppPerfStepService appPerfStepService;

    static String id;

    @Test
    public void test01ForSaveAppPerfStep() {
        AppPerfStep appPerfStep = JMockit.mock(AppPerfStep.class);

        id = appPerfStepService.createAppPerfStep(appPerfStep);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateAppPerfStep(){
        AppPerfStep appPerfStep = JMockit.mock(AppPerfStep.class);
        appPerfStep.setId(id);

        appPerfStepService.updateAppPerfStep(appPerfStep);
    }

    @Test
    public void test03ForFindAppPerfStep() {
        AppPerfStep appPerfStep = appPerfStepService.findAppPerfStep(id);

        assertNotNull(appPerfStep);
    }

    @Test
    public void test04ForFindAllAppPerfStep() {
        List<AppPerfStep> appPerfStepList = appPerfStepService.findAllAppPerfStep();

        assertNotNull(appPerfStepList);
    }

    @Test
    public void test05ForDeleteAppPerfStep(){
        appPerfStepService.deleteAppPerfStep(id);
    }
}