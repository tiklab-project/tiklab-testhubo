package net.tiklab.teston.apptest.perftest.controller;

import com.alibaba.fastjson.JSONObject;
import net.tiklab.core.Result;
import net.tiklab.postin.client.mock.JMockit;
import net.tiklab.teston.test.app.perf.cases.model.AppPerfStep;
import net.tiklab.teston.config.TestConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import java.util.Map;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {TestConfig.class})
@WebAppConfiguration
@Transactional
@Rollback(false)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AppPerfStepControllerTest {

    private static Logger logger = LoggerFactory.getLogger(AppPerfStepControllerTest.class);

    static String id;

    protected MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext wac;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();  //初始化MockMvc对象
    }

    @Test
    public void test01ForSaveAppPerfStep() {
        AppPerfStep appPerfStep = JMockit.mock(AppPerfStep.class);

        Map paramMap  = JSONObject.parseObject(JSONObject.toJSONString(appPerfStep));
        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.setAll(paramMap);
        try {
            MvcResult mvcResult = mockMvc.perform(
                                post("/appPerfStep/createAppPerfStep")
                                .params(multiValueMap)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                    )
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn();

            Result result = (Result) mvcResult.getModelAndView().getModel().get("result");
            assertNotNull(result);
            assertEquals(result.getCode(),0);
            id = String.valueOf(result.getData());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void test02ForUpdateAppPerfStep(){
        AppPerfStep appPerfStep = JMockit.mock(AppPerfStep.class);
        appPerfStep.setId(id);

        Map paramMap  = JSONObject.parseObject(JSONObject.toJSONString(appPerfStep));
        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.setAll(paramMap);
        try {
            MvcResult mvcResult = mockMvc.perform(
                                post("/appPerfStep/updateAppPerfStep")
                                .params(multiValueMap)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                    )
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn();

            Result result = (Result) mvcResult.getModelAndView().getModel().get("result");
            assertNotNull(result);
            assertEquals(result.getCode(),0);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void test03ForFindAppPerfStep() {
        try {
            MvcResult mvcResult = mockMvc.perform(
                    post("/appPerfStep/findAppPerfStep")
                            .param("id",id)
                            .accept(MediaType.APPLICATION_JSON)
                            .contentType(MediaType.APPLICATION_JSON)
            )
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn();

            Result result = (Result) mvcResult.getModelAndView().getModel().get("result");
            assertNotNull(result);
            assertEquals(result.getCode(),0);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void test04ForFindAllAppPerfStep() {
        try {
            MvcResult mvcResult = mockMvc.perform(
                    post("/appPerfStep/findAllAppPerfStep")
                            .accept(MediaType.APPLICATION_JSON)
                            .contentType(MediaType.APPLICATION_JSON)
            )
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn();

            Result result = (Result) mvcResult.getModelAndView().getModel().get("result");
            assertNotNull(result);
            assertEquals(result.getCode(),0);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void test05ForDeleteAppPerfStep(){
        try {
            MvcResult mvcResult = mockMvc.perform(
                    post("/appPerfStep/deleteAppPerfStep")
                            .param("id",id)
                            .accept(MediaType.APPLICATION_JSON)
                            .contentType(MediaType.APPLICATION_JSON)
            )
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn();

            Result result = (Result) mvcResult.getModelAndView().getModel().get("result");
            assertNotNull(result);
            assertEquals(result.getCode(),0);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}