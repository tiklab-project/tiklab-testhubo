package net.tiklab.teston.webtest.perftest.service;

import net.tiklab.teston.config.TestConfig;
import net.tiklab.teston.test.webperf.cases.model.WebPerfStep;
import net.tiklab.postin.client.mock.JMockit;
import net.tiklab.teston.test.webperf.cases.service.WebPerfStepService;
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
public class WebPerfStepServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(WebPerfStepServiceImplTest.class);

    @Autowired
    WebPerfStepService webPerfStepService;

    static String id;

    @Test
    public void test01ForSaveWebPerfStep() {
        WebPerfStep webPerfStep = JMockit.mock(WebPerfStep.class);

        id = webPerfStepService.createWebPerfStep(webPerfStep);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateWebPerfStep(){
        WebPerfStep webPerfStep = JMockit.mock(WebPerfStep.class);
        webPerfStep.setId(id);

        webPerfStepService.updateWebPerfStep(webPerfStep);
    }

    @Test
    public void test03ForFindWebPerfStep() {
        WebPerfStep webPerfStep = webPerfStepService.findWebPerfStep(id);

        assertNotNull(webPerfStep);
    }

    @Test
    public void test04ForFindAllWebPerfStep() {
        List<WebPerfStep> webPerfStepList = webPerfStepService.findAllWebPerfStep();

        assertNotNull(webPerfStepList);
    }

    @Test
    public void test05ForDeleteWebPerfStep(){
        webPerfStepService.deleteWebPerfStep(id);
    }
}