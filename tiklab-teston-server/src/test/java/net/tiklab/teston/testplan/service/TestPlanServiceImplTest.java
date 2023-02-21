package net.tiklab.teston.testplan.service;

import net.tiklab.teston.testplan.model.TestPlan;
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
public class TestPlanServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(TestPlanServiceImplTest.class);

    @Autowired
    TestPlanService testPlanService;

    static String id;

    @Test
    public void test01ForSaveTestPlan() {
        TestPlan testPlan = JMockit.mock(TestPlan.class);

        id = testPlanService.createTestPlan(testPlan);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateTestPlan(){
        TestPlan testPlan = JMockit.mock(TestPlan.class);
        testPlan.setId(id);

        testPlanService.updateTestPlan(testPlan);
    }

    @Test
    public void test03ForFindTestPlan() {
        TestPlan testPlan = testPlanService.findTestPlan(id);

        assertNotNull(testPlan);
    }

    @Test
    public void test04ForFindAllTestPlan() {
        List<TestPlan> testPlanList = testPlanService.findAllTestPlan();

        assertNotNull(testPlanList);
    }

    @Test
    public void test05ForDeleteTestPlan(){
        testPlanService.deleteTestPlan(id);
    }
}