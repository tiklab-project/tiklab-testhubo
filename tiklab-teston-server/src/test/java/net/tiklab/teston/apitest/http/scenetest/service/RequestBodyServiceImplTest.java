package net.tiklab.teston.apitest.http.scenetest.service;

import net.tiklab.postin.client.mock.JMockit;
import net.tiklab.teston.config.TestConfig;
import net.tiklab.teston.test.api.http.unit.cases.model.RequestBody;
import net.tiklab.teston.test.api.http.unit.cases.service.RequestBodyService;
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
public class RequestBodyServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(RequestBodyServiceImplTest.class);

    @Autowired
    RequestBodyService requestBodyService;

    static String id;

    @Test
    public void test01ForSaveRequestBody() {
        RequestBody requestBody = JMockit.mock(RequestBody.class);

        id = requestBodyService.createRequestBody(requestBody);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateRequestBody(){
        RequestBody requestBody = JMockit.mock(RequestBody.class);
        requestBody.setId(id);

        requestBodyService.updateRequestBody(requestBody);
    }

    @Test
    public void test03ForFindRequestBody() {
        RequestBody requestBody = requestBodyService.findRequestBody(id);

        assertNotNull(requestBody);
    }

    @Test
    public void test04ForFindAllRequestBody() {
        List<RequestBody> requestBodyList = requestBodyService.findAllRequestBody();

        assertNotNull(requestBodyList);
    }

    @Test
    public void test05ForDeleteRequestBody(){
        requestBodyService.deleteRequestBody(id);
    }
}