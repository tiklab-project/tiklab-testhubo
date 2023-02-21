package net.tiklab.teston.apitest.http.scenetest.service;

import net.tiklab.postin.client.mock.JMockit;
import net.tiklab.teston.apitest.http.scenetest.model.ApiSceneStepInstanceBind;
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
public class ApiSceneStepInstanceBindServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(ApiSceneStepInstanceBindServiceImplTest.class);

    @Autowired
    ApiSceneStepInstanceBindService apiSceneStepInstanceBindService;

    static String id;

    @Test
    public void test01ForSaveApiSceneStepInstanceBind() {
        ApiSceneStepInstanceBind apiSceneStepInstanceBind = JMockit.mock(ApiSceneStepInstanceBind.class);

        id = apiSceneStepInstanceBindService.createApiSceneStepInstanceBind(apiSceneStepInstanceBind);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateApiSceneStepInstanceBind(){
        ApiSceneStepInstanceBind apiSceneStepInstanceBind = JMockit.mock(ApiSceneStepInstanceBind.class);
        apiSceneStepInstanceBind.setId(id);

        apiSceneStepInstanceBindService.updateApiSceneStepInstanceBind(apiSceneStepInstanceBind);
    }

    @Test
    public void test03ForFindApiSceneStepInstanceBind() {
        ApiSceneStepInstanceBind apiSceneStepInstanceBind = apiSceneStepInstanceBindService.findApiSceneStepInstanceBind(id);

        assertNotNull(apiSceneStepInstanceBind);
    }

    @Test
    public void test04ForFindAllApiSceneStepInstanceBind() {
        List<ApiSceneStepInstanceBind> apiSceneStepInstanceBindList = apiSceneStepInstanceBindService.findAllApiSceneStepInstanceBind();

        assertNotNull(apiSceneStepInstanceBindList);
    }

    @Test
    public void test05ForDeleteApiSceneStepInstanceBind(){
        apiSceneStepInstanceBindService.deleteApiSceneStepInstanceBind(id);
    }
}