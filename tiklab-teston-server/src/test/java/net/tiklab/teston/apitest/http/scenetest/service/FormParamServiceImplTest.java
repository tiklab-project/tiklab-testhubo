package net.tiklab.teston.apitest.http.scenetest.service;

import net.tiklab.teston.test.api.http.unit.cases.model.FormParam;
import net.tiklab.postin.client.mock.JMockit;
import net.tiklab.teston.config.TestConfig;
import net.tiklab.teston.test.api.http.unit.cases.service.FormParamService;
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
public class FormParamServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(FormParamServiceImplTest.class);

    @Autowired
    FormParamService formParamService;

    static String id;

    @Test
    public void test01ForSaveFormParam() {
        FormParam formParam = JMockit.mock(FormParam.class);

        id = formParamService.createFormParam(formParam);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateFormParam(){
        FormParam formParam = JMockit.mock(FormParam.class);
        formParam.setId(id);

        formParamService.updateFormParam(formParam);
    }

    @Test
    public void test03ForFindFormParam() {
        FormParam formParam = formParamService.findFormParam(id);

        assertNotNull(formParam);
    }

    @Test
    public void test04ForFindAllFormParam() {
        List<FormParam> formParamList = formParamService.findAllFormParam();

        assertNotNull(formParamList);
    }

    @Test
    public void test05ForDeleteFormParam(){
        formParamService.deleteFormParam(id);
    }
}