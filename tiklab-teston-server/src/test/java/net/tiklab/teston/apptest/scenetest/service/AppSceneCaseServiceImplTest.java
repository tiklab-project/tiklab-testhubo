package net.tiklab.teston.apptest.scenetest.service;

import net.tiklab.teston.test.appscene.cases.model.AppSceneCase;
import net.tiklab.postin.client.mock.JMockit;
import net.tiklab.teston.config.TestConfig;
import net.tiklab.teston.test.appscene.cases.service.AppSceneCaseService;
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
public class AppSceneCaseServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(AppSceneCaseServiceImplTest.class);

    @Autowired
    AppSceneCaseService appSceneCaseService;

    static String id;

    @Test
    public void test01ForSaveAppSceneCase() {
        AppSceneCase appSceneCase = JMockit.mock(AppSceneCase.class);

        id = appSceneCaseService.createAppSceneCase(appSceneCase);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateAppSceneCase(){
        AppSceneCase appSceneCase = JMockit.mock(AppSceneCase.class);
        appSceneCase.setId(id);

        appSceneCaseService.updateAppSceneCase(appSceneCase);
    }

    @Test
    public void test03ForFindAppSceneCase() {
        AppSceneCase appSceneCase = appSceneCaseService.findAppSceneCase(id);

        assertNotNull(appSceneCase);
    }

    @Test
    public void test04ForFindAllAppSceneCase() {
        List<AppSceneCase> appSceneCaseList = appSceneCaseService.findAllAppSceneCase();

        assertNotNull(appSceneCaseList);
    }

    @Test
    public void test05ForDeleteAppSceneCase(){
        appSceneCaseService.deleteAppSceneCase(id);
    }
}