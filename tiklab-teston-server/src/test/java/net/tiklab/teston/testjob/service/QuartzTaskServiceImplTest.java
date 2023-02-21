package net.tiklab.teston.testjob.service;

import net.tiklab.teston.testjob.model.QuartzTask;
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
public class QuartzTaskServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(QuartzTaskServiceImplTest.class);

    @Autowired
    QuartzTaskService quartzTaskService;

    static String id;

    @Test
    public void test01ForSaveQuartzTask() {
        QuartzTask quartzTask = JMockit.mock(QuartzTask.class);

        id = quartzTaskService.createQuartzTask(quartzTask);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateQuartzTask(){
        QuartzTask quartzTask = JMockit.mock(QuartzTask.class);
        quartzTask.setId(id);

        quartzTaskService.updateQuartzTask(quartzTask);
    }

    @Test
    public void test03ForFindQuartzTask() {
        QuartzTask quartzTask = quartzTaskService.findQuartzTask(id);

        assertNotNull(quartzTask);
    }

    @Test
    public void test04ForFindAllQuartzTask() {
        List<QuartzTask> quartzTaskList = quartzTaskService.findAllQuartzTask();

        assertNotNull(quartzTaskList);
    }

    @Test
    public void test05ForDeleteQuartzTask(){
        quartzTaskService.deleteQuartzTask(id);
    }
}