package net.tiklab.teston.testcase.service;

import net.tiklab.teston.test.api.http.scene.instance.model.ApiSceneInstance;
import net.tiklab.postin.client.mock.JMockit;
import net.tiklab.teston.test.api.http.scene.instance.service.ApiSceneInstanceService;
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
public class TestInstanceCollentServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(TestInstanceCollentServiceImplTest.class);

    @Autowired
    ApiSceneInstanceService apiSceneInstanceService;

    static String id;

    @Test
    public void test01ForSaveTestInstanceCollent() {
        ApiSceneInstance testInstanceCollent = JMockit.mock(ApiSceneInstance.class);

        id = apiSceneInstanceService.createApiSceneInstance(testInstanceCollent);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateTestInstanceCollent(){
        ApiSceneInstance testInstanceCollent = JMockit.mock(ApiSceneInstance.class);
        testInstanceCollent.setId(id);

        apiSceneInstanceService.updateApiSceneInstance(testInstanceCollent);
    }

    @Test
    public void test03ForFindTestInstanceCollent() {
        ApiSceneInstance testInstanceCollent = apiSceneInstanceService.findApiSceneInstance(id);

        assertNotNull(testInstanceCollent);
    }

    @Test
    public void test04ForFindAllTestInstanceCollent() {
        List<ApiSceneInstance> testInstanceCollentList = apiSceneInstanceService.findAllApiSceneInstance();

        assertNotNull(testInstanceCollentList);
    }

    @Test
    public void test05ForDeleteTestInstanceCollent(){
        apiSceneInstanceService.deleteApiSceneInstance(id);
    }
}