package net.tiklab.teston.testplan.service;

import net.tiklab.teston.testplan.model.TestPlanDetail;
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
public class TestPlanDetailServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(TestPlanDetailServiceImplTest.class);

    @Autowired
    TestPlanDetailService testPlanDetailService;

    static String id;

    @Test
    public void test01ForSaveTestPlanDetail() {
        TestPlanDetail testPlanDetail = JMockit.mock(TestPlanDetail.class);

        id = testPlanDetailService.createTestPlanDetail(testPlanDetail);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateTestPlanDetail(){
        TestPlanDetail testPlanDetail = JMockit.mock(TestPlanDetail.class);
        testPlanDetail.setId(id);

        testPlanDetailService.updateTestPlanDetail(testPlanDetail);
    }

    @Test
    public void test03ForFindTestPlanDetail() {
        TestPlanDetail testPlanDetail = testPlanDetailService.findTestPlanDetail(id);

        assertNotNull(testPlanDetail);
    }

    @Test
    public void test04ForFindAllTestPlanDetail() {
        List<TestPlanDetail> testPlanDetailList = testPlanDetailService.findAllTestPlanDetail();

        assertNotNull(testPlanDetailList);
    }

    @Test
    public void test05ForDeleteTestPlanDetail(){
        testPlanDetailService.deleteTestPlanDetail(id);
    }
}