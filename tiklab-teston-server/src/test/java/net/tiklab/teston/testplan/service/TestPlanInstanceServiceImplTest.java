package net.tiklab.teston.testplan.service;

import net.tiklab.postin.client.mock.JMockit;
import net.tiklab.teston.testplan.model.TestPlanInstance;
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
public class TestPlanInstanceServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(TestPlanInstanceServiceImplTest.class);

    @Autowired
    TestPlanInstanceService testPlanInstanceService;

    static String id;

    @Test
    public void test01ForSaveTestPlanInstance() {
        TestPlanInstance testPlanInstance = JMockit.mock(TestPlanInstance.class);

        id = testPlanInstanceService.createTestPlanInstance(testPlanInstance);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateTestPlanInstance(){
        TestPlanInstance testPlanInstance = JMockit.mock(TestPlanInstance.class);
        testPlanInstance.setId(id);

        testPlanInstanceService.updateTestPlanInstance(testPlanInstance);
    }

    @Test
    public void test03ForFindTestPlanInstance() {
        TestPlanInstance testPlanInstance = testPlanInstanceService.findTestPlanInstance(id);

        assertNotNull(testPlanInstance);
    }

    @Test
    public void test04ForFindAllTestPlanInstance() {
        List<TestPlanInstance> testPlanInstanceList = testPlanInstanceService.findAllTestPlanInstance();

        assertNotNull(testPlanInstanceList);
    }

    @Test
    public void test05ForDeleteTestPlanInstance(){
        testPlanInstanceService.deleteTestPlanInstance(id);
    }
}