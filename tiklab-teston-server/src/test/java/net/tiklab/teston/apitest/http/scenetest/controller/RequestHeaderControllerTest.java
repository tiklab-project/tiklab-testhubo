package net.tiklab.teston.apitest.http.scenetest.controller;

import net.tiklab.utils.MapUtils;
import net.tiklab.core.Result;
import net.tiklab.postin.client.mock.JMockit;
import net.tiklab.teston.config.TestConfig;
import net.tiklab.teston.apitest.http.unittest.model.RequestHeader;
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
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

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
public class RequestHeaderControllerTest {

    private static Logger logger = LoggerFactory.getLogger(RequestHeaderControllerTest.class);

    static String id;

    protected MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext wac;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();  //初始化MockMvc对象
    }

    @Test
    public void test01ForSaveRequestHeader() {
        RequestHeader requestHeader = JMockit.mock(RequestHeader.class);

        MultiValueMap<String, String> multiValueMap = MapUtils.toMultiMap(requestHeader);

        try {
            MvcResult mvcResult = mockMvc.perform(
                                post("/requestHeader/createRequestHeader")
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
    public void test02ForUpdateRequestHeader(){
        RequestHeader requestHeader = JMockit.mock(RequestHeader.class);
        requestHeader.setId(id);

        MultiValueMap<String, String> multiValueMap = MapUtils.toMultiMap(requestHeader);

        try {
            MvcResult mvcResult = mockMvc.perform(
                                post("/requestHeader/updateRequestHeader")
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
    public void test03ForFindRequestHeader() {
        try {
            MvcResult mvcResult = mockMvc.perform(
                    post("/requestHeader/findRequestHeader")
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
    public void test04ForFindAllRequestHeader() {
        try {
            MvcResult mvcResult = mockMvc.perform(
                    post("/requestHeader/findAllRequestHeader")
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
    public void test05ForDeleteRequestHeader(){
        try {
            MvcResult mvcResult = mockMvc.perform(
                    post("/requestHeader/deleteRequestHeader")
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