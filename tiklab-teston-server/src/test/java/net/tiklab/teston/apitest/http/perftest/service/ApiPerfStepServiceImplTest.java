package net.tiklab.teston.apitest.http.perftest.service;

import net.tiklab.teston.test.api.http.perf.cases.model.ApiPerfStep;
import net.tiklab.postin.client.mock.JMockit;
import net.tiklab.teston.config.TestConfig;
import net.tiklab.teston.test.api.http.perf.cases.service.ApiPerfStepService;
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
public class ApiPerfStepServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(ApiPerfStepServiceImplTest.class);

    @Autowired
    ApiPerfStepService apiPerfStepService;

    static String id;

    @Test
    public void test01ForSaveApiPerfStep() {
        ApiPerfStep apiPerfStep = JMockit.mock(ApiPerfStep.class);

        id = apiPerfStepService.createApiPerfStep(apiPerfStep);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateApiPerfStep(){
        ApiPerfStep apiPerfStep = JMockit.mock(ApiPerfStep.class);
        apiPerfStep.setId(id);

        apiPerfStepService.updateApiPerfStep(apiPerfStep);
    }

    @Test
    public void test03ForFindApiPerfStep() {
        ApiPerfStep apiPerfStep = apiPerfStepService.findApiPerfStep(id);

        assertNotNull(apiPerfStep);
    }

    @Test
    public void test04ForFindAllApiPerfStep() {
        List<ApiPerfStep> apiPerfStepList = apiPerfStepService.findAllApiPerfStep();

        assertNotNull(apiPerfStepList);
    }

    @Test
    public void test05ForDeleteApiPerfStep(){
        apiPerfStepService.deleteApiPerfStep(id);
    }
}