package net.tiklab.teston.testjob.service;

import net.tiklab.teston.testjob.model.QuartzMaster;
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
public class QuartzMasterServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(QuartzMasterServiceImplTest.class);

    @Autowired
    QuartzMasterService quartzMasterService;

    static String id;

    @Test
    public void test01ForSaveQuartzMaster() {
        QuartzMaster quartzMaster = JMockit.mock(QuartzMaster.class);

        id = quartzMasterService.createQuartzMaster(quartzMaster);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateQuartzMaster(){
        QuartzMaster quartzMaster = JMockit.mock(QuartzMaster.class);
        quartzMaster.setId(id);

        quartzMasterService.updateQuartzMaster(quartzMaster);
    }

    @Test
    public void test03ForFindQuartzMaster() {
        QuartzMaster quartzMaster = quartzMasterService.findQuartzMaster(id);

        assertNotNull(quartzMaster);
    }

    @Test
    public void test04ForFindAllQuartzMaster() {
        List<QuartzMaster> quartzMasterList = quartzMasterService.findAllQuartzMaster();

        assertNotNull(quartzMasterList);
    }

    @Test
    public void test05ForDeleteQuartzMaster(){
        quartzMasterService.deleteQuartzMaster(id);
    }
}