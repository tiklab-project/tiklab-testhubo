package net.tiklab.teston.repository.service;

import net.tiklab.teston.test.api.http.scene.cases.model.ApiSceneCase;
import net.tiklab.postin.client.mock.JMockit;
import net.tiklab.teston.config.TestConfig;
import net.tiklab.teston.test.api.http.scene.cases.service.ApiSceneCaseService;
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
public class TestCaseServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(TestCaseServiceImplTest.class);

    @Autowired
    ApiSceneCaseService testCaseService;

    static String id;

    @Test
    public void test01ForSaveTestCase() {
        ApiSceneCase testCase = JMockit.mock(ApiSceneCase.class);

        id = testCaseService.createApiSceneCase(testCase);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateTestCase(){
        ApiSceneCase testCase = JMockit.mock(ApiSceneCase.class);
        testCase.setId(id);

        testCaseService.updateApiSceneCase(testCase);
    }

    @Test
    public void test03ForFindTestCase() {
        ApiSceneCase testCase = testCaseService.findApiSceneCase(id);

        assertNotNull(testCase);
    }

    @Test
    public void test04ForFindAllTestCase() {
        List<ApiSceneCase> testCaseList = testCaseService.findAllApiSceneCase();

        assertNotNull(testCaseList);
    }

    @Test
    public void test05ForDeleteTestCase(){
        testCaseService.deleteApiSceneCase(id);
    }
}