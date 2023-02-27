package net.tiklab.teston.apix.http.unit.http.cases.service;

import com.alibaba.fastjson.JSONObject;
import net.tiklab.beans.BeanMapper;
import net.tiklab.core.page.Pagination;
import net.tiklab.core.page.PaginationBuilder;
import net.tiklab.join.JoinTemplate;

import net.tiklab.teston.apix.http.unit.cases.model.*;
import net.tiklab.teston.apix.http.unit.cases.service.*;
import net.tiklab.teston.apix.http.unit.http.cases.dao.ApiUnitCaseDao;
import net.tiklab.teston.apix.http.unit.http.cases.entity.ApiUnitCaseEntity;
import net.tiklab.teston.apix.http.unit.instance.service.ApiUnitInstanceService;
import net.tiklab.teston.manager.testcase.model.TestCase;
import net.tiklab.teston.manager.testcase.model.TestCaseQuery;
import net.tiklab.teston.manager.testcase.service.TestCaseService;
import net.tiklab.teston.apix.http.unit.http.instance.dao.ApiUnitInstanceDao;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* StepServiceImpl
*/
@Service
public class ApiUnitCaseServiceImpl implements ApiUnitCaseService {

    @Autowired
    ApiUnitCaseDao apiApiUnitCaseDao;

    @Autowired
    JoinTemplate joinTemplate;
    
    @Autowired
    TestCaseService testCaseService;

    @Autowired
    RequestHeaderService requestHeaderService;

    @Autowired
    RequestBodyService requestBodyService;

    @Autowired
    QueryParamService queryParamService;

    @Autowired
    RawParamService rawParamService;

    @Autowired
    FormParamService formParamService;

    @Autowired
    FormUrlencodedService formUrlencodedService;

    @Autowired
    JsonParamService jsonParamService;

    @Autowired
    PreScriptService preScriptService;

    @Autowired
    ResponseResultServiceImpl responseResultService;

    @Autowired
    ApiUnitInstanceDao testInstanceDao;

    @Autowired
    ApiUnitInstanceService apiUnitInstanceService;


    @Override
    public String createApiUnitCase(@NotNull @Valid ApiUnitCase apiUnitCase) {
        //添加apiUnit 返回id
        ApiUnitCaseEntity apiUnitCaseEntity = BeanMapper.map(apiUnitCase, ApiUnitCaseEntity.class);
        String id = apiApiUnitCaseDao.createApiUnitCase(apiUnitCaseEntity);

        //把testCaseId 设置成与 apiUnitId 一样
        apiUnitCaseEntity.setTestCaseId(id);
        apiUnitCaseEntity.setId(id);
        apiApiUnitCaseDao.updateApiUnitCase(apiUnitCaseEntity);

        //初始化请求响应中的类型
        RequestBody requestBody = new RequestBody();
        requestBody.setId(id);
        requestBody.setApiUnitId(id);
        requestBody.setBodyType("none");
        requestBodyService.createRequestBody(requestBody);

        ResponseResult responseResult = new ResponseResult();
        responseResult.setId(id);
        responseResult.setApiUnitId(id);
        responseResult.setResultType("json");
        responseResultService.createResponseResult(responseResult);


        //添加testCase
        TestCase testCase = apiUnitCase.getTestCase();
        testCase.setId(id);
        testCaseService.createTestCase(testCase);

        return id;
    }

    @Override
    public void updateApiUnitCase(@NotNull @Valid ApiUnitCase apiUnitCase) {
        ApiUnitCaseEntity apiUnitCaseEntity = BeanMapper.map(apiUnitCase, ApiUnitCaseEntity.class);

        apiUnitCaseEntity.setTestCaseId(apiUnitCase.getId());
        apiApiUnitCaseDao.updateApiUnitCase(apiUnitCaseEntity);

        testCaseService.updateTestCase(apiUnitCase.getTestCase());
    }

    @Override
    public void deleteApiUnitCase(@NotNull String id) {
        //删除下面instance所有子表
//        apiUnitInstanceService.deleteApiUnitInstanceByApiUnitId(id);

        apiApiUnitCaseDao.deleteApiUnitCase(id);

        testCaseService.deleteTestCase(id);
    }

    @Override
    public ApiUnitCase findOne(String id) {
        ApiUnitCaseEntity apiUnitCase = apiApiUnitCaseDao.findApiUnitCase(id);

        ApiUnitCase apiUnit = BeanMapper.map(apiUnitCase, ApiUnitCase.class);
        return apiUnit;
    }

    @Override
    public List<ApiUnitCase> findList(List<String> idList) {
        List<ApiUnitCaseEntity> apiUnitCaseList =  apiApiUnitCaseDao.findApiUnitCaseList(idList);

        List<ApiUnitCase> apiUnitList =  BeanMapper.mapList(apiUnitCaseList, ApiUnitCase.class);
        return apiUnitList;
    }

    @Override
    public ApiUnitCase findApiUnitCase(@NotNull String id) {
        ApiUnitCase apiUnitCase = findOne(id);

        joinTemplate.joinQuery(apiUnitCase);

        TestCase testCase = testCaseService.findTestCase(id);
        apiUnitCase.setTestCase(testCase);

        return apiUnitCase;
    }

    @Override
    public List<ApiUnitCase> findAllApiUnitCase() {
        List<ApiUnitCaseEntity> apiUnitCaseList =  apiApiUnitCaseDao.findAllApiUnitCase();


        List<ApiUnitCase> apiUnitList =  BeanMapper.mapList(apiUnitCaseList, ApiUnitCase.class);

        joinTemplate.joinQuery(apiUnitList);
        return apiUnitList;
    }

    @Override
    public List<ApiUnitCase> findApiUnitCaseList(ApiUnitCaseQuery apiUnitCaseQuery) {
        List<ApiUnitCaseEntity> apiUnitCaseList = apiApiUnitCaseDao.findApiUnitCaseList(apiUnitCaseQuery);

        List<ApiUnitCase> testCases = BeanMapper.mapList(apiUnitCaseList, ApiUnitCase.class);
       // addApiUnitCase(testCases);
        joinTemplate.joinQuery(testCases);

        return testCases;
    }


    @Override
    public List<ApiUnitCase> findApiUnitCaseListByTestCase(TestCaseQuery testCaseQuery) {

        List<TestCase> testCaseList = testCaseService.findTestCaseList(testCaseQuery);

        List<ApiUnitCase> apiUnitCaseList=new ArrayList<>();

        if(!ObjectUtils.isEmpty(testCaseList)){
            for (TestCase testCase:testCaseList){
                List<ApiUnitCase> caseList = findApiUnitCaseList(new ApiUnitCaseQuery().setTestCaseId(testCase.getId()));
               if (!ObjectUtils.isEmpty(caseList)){
                   apiUnitCaseList.add(caseList.get(0));
               }
            }
        }

        return apiUnitCaseList;
    }



    @Override
    public Pagination<ApiUnitCase> findApiUnitCasePage(ApiUnitCaseQuery testCaseQuery) {
        Pagination<ApiUnitCaseEntity>  pagination = apiApiUnitCaseDao.findApiUnitCasePage(testCaseQuery);

        List<ApiUnitCase> apiUnitCaseList = BeanMapper.mapList(pagination.getDataList(), ApiUnitCase.class);
        //addApiUnitCase(apiUnitCaseList);
        joinTemplate.joinQuery(apiUnitCaseList);

        return PaginationBuilder.build(pagination,apiUnitCaseList);
    }




//    public void addApiUnitCase(List<ApiUnitCase> caseList){
//        if (CollectionUtils.isNotEmpty(caseList)){
//            //将测试结果放测试步骤方法里面
//            for (ApiUnitCase apiUnitCase:caseList){
//                ApiUnitInstanceQuery testInstanceQuery = new ApiUnitInstanceQuery();
//                testInstanceQuery.setId(apiUnitCase.getId());
//                List<ApiUnitInstanceEntity> testInstanceList = testInstanceDao.findTestStepInstanceList(testInstanceQuery);
//                if (CollectionUtils.isNotEmpty(testInstanceList)){
//                    List<ApiUnitInstance> testInstances = BeanMapper.mapList(testInstanceList, ApiUnitInstance.class);
//                    //创建时间最近的
//                    ApiUnitInstance testInstance = testInstances.get(testInstances.size() - 1);
////                    apiUnitCase.setTestResult(testInstance.getResult());  //添加最近的测试步骤的测试结果
//                }
//            }
//        }
//    }
    
    
    



    @Override
    public ApiUnitCaseExt findApiUnitCaseExt(ApiUnitCase apiUnitCase) {

        //封装请求头数据
        Map headerMap = requestHeaderService.jointHeader(apiUnitCase);
        //封装param参数
        String query = queryParamService.jointParam(apiUnitCase);
        //封装mediaType
        Map mediaType = jointMediaType(apiUnitCase);
        //封装请求体数据
        String body = jointParamBody(apiUnitCase);
        //调用前置脚本
        Object preScript = findPreScript(apiUnitCase.getId());

        ApiUnitCaseExt apiUnitCaseExt = new ApiUnitCaseExt();
        apiUnitCaseExt.setHeaderMap(headerMap);
        apiUnitCaseExt.setQuery(query);
        apiUnitCaseExt.setMediaTypeMap(mediaType);
        apiUnitCaseExt.setBody(body);
        apiUnitCaseExt.setPreScript(preScript);

        return apiUnitCaseExt;
    }


    //获取contentType
    Map jointMediaType(ApiUnitCase apiUnitCase){
        RequestBody bodyType = requestBodyService.findRequestBody(apiUnitCase.getId());

        //获取raw下面的mediaType
        RawParam rawParam = rawParamService.findRawParam(apiUnitCase.getId());
        String rawMediaType = null;
        if(!ObjectUtils.isEmpty(rawParam)){
            rawMediaType= rawParam.getType();
        }

        Map mediaType = new HashMap();
        if (!ObjectUtils.isEmpty(bodyType)){
            if(bodyType.getBodyType().equals("raw")){
                mediaType.put("mediaType",rawMediaType);
            }else {
                mediaType.put("mediaType",bodyType.getBodyType());
            }
        }
        return mediaType;
    }


    /**
     *请求体参数拼接
     * @param apiUnitCase 用例步骤
     *
     */
    private String jointParamBody(ApiUnitCase apiUnitCase){
        RequestBody bodyType = requestBodyService.findRequestBody(apiUnitCase.getId());
       
//        HashMap<String, Object> map = new HashMap<>();
        String bodyStr = "";

        if (!ObjectUtils.isEmpty(bodyType)){
            switch (bodyType.getBodyType()){
                case "formdata":
                    return getFormData(apiUnitCase,bodyStr);

                case "formUrlencoded":
                    return getFormUrlencoded(apiUnitCase,bodyStr);

                case "json":
                    return getJson(apiUnitCase,bodyStr);

                case "raw":
                    return getRaw(apiUnitCase,bodyStr);

            }
        }

        return null;
    }


    //获取formData
    private String getFormData (ApiUnitCase apiUnitCase,String bodyStr){
        FormParamQuery formParamQuery = new FormParamQuery();
        formParamQuery.setApiUnitId(apiUnitCase.getId());
        List<FormParam> formParamList = formParamService.findFormParamList(formParamQuery);

        if (CollectionUtils.isNotEmpty(formParamList)){
            for (FormParam formParam:formParamList){
                bodyStr = bodyStr + formParam.getParamName() + "=" + formParam.getValue() + "&";
            }
        }

        return bodyStr;
    }

    //获取FormUrlDataMap
    private String getFormUrlencoded (ApiUnitCase apiUnitCase, String bodyStr){
        FormUrlencodedQuery formUrlencodedQuery = new FormUrlencodedQuery();
        formUrlencodedQuery.setApiUnitId(apiUnitCase.getId());
        List<FormUrlencoded> formUrlencodedList = formUrlencodedService.findFormUrlencodedList(formUrlencodedQuery);
        if (CollectionUtils.isNotEmpty(formUrlencodedList)){
            for (FormUrlencoded formUrlencoded:formUrlencodedList){
                bodyStr = bodyStr + formUrlencoded.getParamName() + "=" + formUrlencoded.getValue() + "&";
            }
        }

        return bodyStr;
    }

    //获取JsonDataMap
    private String getJson(ApiUnitCase apiUnitCase, String bodyStr){
        JsonParamQuery jsonParamQuery = new JsonParamQuery();
        jsonParamQuery.setApiUnitId(apiUnitCase.getId());
        List<JsonParam> jsonParamListTree = jsonParamService.findJsonParamListTree(jsonParamQuery);

        if (CollectionUtils.isNotEmpty(jsonParamListTree)){
            JSONObject jsonObject = new JSONObject();
            for (JsonParam jsonParam:jsonParamListTree){
                //第一级的
                getJsonTree(jsonParam,jsonObject);
            }

            bodyStr=jsonObject.toJSONString();
        }

        return bodyStr;
    }

    //递归json
    private void getJsonTree(JsonParam jsonParam, JSONObject jsonObject){
        if (ObjectUtils.isEmpty(jsonParam.getParent())){
            String key = jsonParam.getParamName();
            String value = jsonParam.getValue();

            jsonObject.put(key,value);
        }else {
            getJsonTree(jsonParam.getParent(),jsonObject);
        }
    }

    //获取RawDataMap
    private String getRaw(ApiUnitCase apiUnitCase, String bodyStr){

        //通过测试步骤方法的id查询
        RawParam rawParam = rawParamService.findRawParam(apiUnitCase.getId());
        if(!ObjectUtils.isEmpty(rawParam)){
            String raw = rawParam.getRaw();

            if(rawParam.getType().equals("json")){
                bodyStr = raw;
            }

            if(rawParam.getType().equals("xml")){
                //解析xml
            }

            if(rawParam.getType().equals("text")){
                //解析text
            }


        }

        return bodyStr;

    }



    /**
     * 前置脚本处理
     * @param caseId
     * @return
     */
    Object  findPreScript(String caseId)   {
        PreScriptQuery preScriptQuery = new PreScriptQuery();
        preScriptQuery.setApiUnitId(caseId);
        List<PreScript> preScriptList = preScriptService.findPreScriptList(preScriptQuery);

        if (CollectionUtils.isNotEmpty(preScriptList)){
            String scriptex = preScriptList.get(0).getScriptex();
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("javascript");
            try {
                String jsFileName = "tiklab-teston-ce-server/src/main/resources/static/dk.js";
                FileInputStream fip = new FileInputStream(jsFileName);
                InputStreamReader reader = new InputStreamReader(fip, "UTF-8");
                engine.eval(reader);
                if (engine instanceof Invocable){
                    Invocable invocable = (Invocable) engine;
                    Object execute = invocable.invokeFunction("execute", scriptex);
                    //当断言为请求头时
                    /*
                    ScriptObjectMirror header=(ScriptObjectMirror) engine.get("header");
                    if (!ObjectUtils.isEmpty(header)){
                        String key =(String) header.get("key");
                        String value = (String) header.get("value");
                        headerMap.put(key,value);
                    }
                     */

                }

            } catch (Exception e) {
                e.getMessage();
            }
        }
        return null;
    }




}