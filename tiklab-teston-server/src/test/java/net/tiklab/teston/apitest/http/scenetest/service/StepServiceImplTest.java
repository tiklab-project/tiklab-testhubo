package net.tiklab.teston.apitest.http.scenetest.service;

import net.tiklab.teston.test.api.http.unit.cases.model.ApiUnitCase;
import net.tiklab.postin.client.mock.JMockit;
import net.tiklab.teston.config.TestConfig;
import net.tiklab.teston.test.api.http.unit.cases.service.ApiUnitCaseService;
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
public class StepServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(StepServiceImplTest.class);

    @Autowired
    ApiUnitCaseService apiUnitCaseService;

    static String id;

    @Test
    public void test01ForSaveStep() {
        ApiUnitCase step = JMockit.mock(ApiUnitCase.class);

        id = apiUnitCaseService.createApiUnitCase(step);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateStep(){
        ApiUnitCase step = JMockit.mock(ApiUnitCase.class);
        step.setId(id);

        apiUnitCaseService.updateApiUnitCase(step);
    }

    @Test
    public void test03ForFindStep() {
        ApiUnitCase step = apiUnitCaseService.findApiUnitCase(id);

        assertNotNull(step);
    }

    @Test
    public void test04ForFindAllStep() {
        List<ApiUnitCase> stepList = apiUnitCaseService.findAllApiUnitCase();

        assertNotNull(stepList);
    }

    @Test
    public void test05ForDeleteStep(){
        apiUnitCaseService.deleteApiUnitCase(id);
    }
}