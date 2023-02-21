package net.tiklab.teston.apitest.http.unittest.service;

import net.tiklab.postin.client.mock.JMockit;
import net.tiklab.teston.apitest.http.unittest.model.ApiUnitInstanceBind;

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
public class ApiUnitInstanceBindServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(ApiUnitInstanceBindServiceImplTest.class);

    @Autowired
    ApiUnitInstanceBindService apiUnitInstanceBindService;

    static String id;

    @Test
    public void test01ForSaveApiUnitInstanceBind() {
        ApiUnitInstanceBind apiUnitInstanceBind = JMockit.mock(ApiUnitInstanceBind.class);

        id = apiUnitInstanceBindService.createApiUnitInstanceBind(apiUnitInstanceBind);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateApiUnitInstanceBind(){
        ApiUnitInstanceBind apiUnitInstanceBind = JMockit.mock(ApiUnitInstanceBind.class);
        apiUnitInstanceBind.setId(id);

        apiUnitInstanceBindService.updateApiUnitInstanceBind(apiUnitInstanceBind);
    }

    @Test
    public void test03ForFindApiUnitInstanceBind() {
        ApiUnitInstanceBind apiUnitInstanceBind = apiUnitInstanceBindService.findApiUnitInstanceBind(id);

        assertNotNull(apiUnitInstanceBind);
    }

    @Test
    public void test04ForFindAllApiUnitInstanceBind() {
        List<ApiUnitInstanceBind> apiUnitInstanceBindList = apiUnitInstanceBindService.findAllApiUnitInstanceBind();

        assertNotNull(apiUnitInstanceBindList);
    }

    @Test
    public void test05ForDeleteApiUnitInstanceBind(){
        apiUnitInstanceBindService.deleteApiUnitInstanceBind(id);
    }
}