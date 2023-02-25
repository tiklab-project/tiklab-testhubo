package net.tiklab.teston.apptest.perftest.service;

import net.tiklab.teston.test.app.perf.cases.model.AppPerfCase;
import net.tiklab.postin.client.mock.JMockit;
import net.tiklab.teston.config.TestConfig;
import net.tiklab.teston.test.app.perf.cases.service.AppPerfCaseService;
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
public class AppPerfCaseServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(AppPerfCaseServiceImplTest.class);

    @Autowired
    AppPerfCaseService appPerfCaseService;

    static String id;

    @Test
    public void test01ForSaveAppPerfCase() {
        AppPerfCase appPerfCase = JMockit.mock(AppPerfCase.class);

        id = appPerfCaseService.createAppPerfCase(appPerfCase);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateAppPerfCase(){
        AppPerfCase appPerfCase = JMockit.mock(AppPerfCase.class);
        appPerfCase.setId(id);

        appPerfCaseService.updateAppPerfCase(appPerfCase);
    }

    @Test
    public void test03ForFindAppPerfCase() {
        AppPerfCase appPerfCase = appPerfCaseService.findAppPerfCase(id);

        assertNotNull(appPerfCase);
    }

    @Test
    public void test04ForFindAllAppPerfCase() {
        List<AppPerfCase> appPerfCaseList = appPerfCaseService.findAllAppPerfCase();

        assertNotNull(appPerfCaseList);
    }

    @Test
    public void test05ForDeleteAppPerfCase(){
        appPerfCaseService.deleteAppPerfCase(id);
    }
}