package net.tiklab.teston.testcase.service;

import net.tiklab.teston.test.api.http.unit.instance.model.ApiUnitInstance;
import net.tiklab.postin.client.mock.JMockit;
import net.tiklab.teston.test.api.http.unit.instance.service.ApiUnitInstanceService;
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
public class TestInstanceServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(TestInstanceServiceImplTest.class);

    @Autowired
    ApiUnitInstanceService apiUnitInstanceService;

    static String id;

    @Test
    public void test01ForSaveApiUnitInstance() {
        ApiUnitInstance apiUnitInstance = JMockit.mock(ApiUnitInstance.class);

        id = apiUnitInstanceService.createApiUnitInstance(apiUnitInstance);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateApiUnitInstance(){
        ApiUnitInstance apiUnitInstance = JMockit.mock(ApiUnitInstance.class);
        apiUnitInstance.setId(id);

        apiUnitInstanceService.updateApiUnitInstance(apiUnitInstance);
    }

    @Test
    public void test03ForFindApiUnitInstance() {
        ApiUnitInstance apiUnitInstance = apiUnitInstanceService.findApiUnitInstance(id);

        assertNotNull(apiUnitInstance);
    }

    @Test
    public void test04ForFindAllApiUnitInstance() {
        List<ApiUnitInstance> apiUnitInstanceList = apiUnitInstanceService.findAllApiUnitInstance();

        assertNotNull(apiUnitInstanceList);
    }

    @Test
    public void test05ForDeleteApiUnitInstance(){
        apiUnitInstanceService.deleteApiUnitInstance(id);
    }
}