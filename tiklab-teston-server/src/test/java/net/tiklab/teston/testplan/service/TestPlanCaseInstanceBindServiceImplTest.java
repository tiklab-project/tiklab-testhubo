package net.tiklab.teston.testplan.service;

import net.tiklab.postin.client.mock.JMockit;
import net.tiklab.teston.testplan.model.TestPlanCaseInstanceBind;
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
public class TestPlanCaseInstanceBindServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(TestPlanCaseInstanceBindServiceImplTest.class);

    @Autowired
    TestPlanCaseInstanceBindService testPlanCaseInstanceBindService;

    static String id;

    @Test
    public void test01ForSaveTestPlanCaseInstanceBind() {
        TestPlanCaseInstanceBind testPlanCaseInstanceBind = JMockit.mock(TestPlanCaseInstanceBind.class);

        id = testPlanCaseInstanceBindService.createTestPlanCaseInstanceBind(testPlanCaseInstanceBind);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateTestPlanCaseInstanceBind(){
        TestPlanCaseInstanceBind testPlanCaseInstanceBind = JMockit.mock(TestPlanCaseInstanceBind.class);
        testPlanCaseInstanceBind.setId(id);

        testPlanCaseInstanceBindService.updateTestPlanCaseInstanceBind(testPlanCaseInstanceBind);
    }

    @Test
    public void test03ForFindTestPlanCaseInstanceBind() {
        TestPlanCaseInstanceBind testPlanCaseInstanceBind = testPlanCaseInstanceBindService.findTestPlanCaseInstanceBind(id);

        assertNotNull(testPlanCaseInstanceBind);
    }

    @Test
    public void test04ForFindAllTestPlanCaseInstanceBind() {
        List<TestPlanCaseInstanceBind> testPlanCaseInstanceBindList = testPlanCaseInstanceBindService.findAllTestPlanCaseInstanceBind();

        assertNotNull(testPlanCaseInstanceBindList);
    }

    @Test
    public void test05ForDeleteTestPlanCaseInstanceBind(){
        testPlanCaseInstanceBindService.deleteTestPlanCaseInstanceBind(id);
    }
}