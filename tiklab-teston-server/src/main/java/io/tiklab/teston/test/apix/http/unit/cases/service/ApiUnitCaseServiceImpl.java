package io.tiklab.teston.test.apix.http.unit.cases.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.tiklab.core.exception.ApplicationException;
import io.tiklab.teston.support.variable.model.Variable;
import io.tiklab.teston.support.variable.model.VariableQuery;
import io.tiklab.teston.support.variable.service.VariableService;
import io.tiklab.teston.test.apix.http.unit.cases.dao.ApiUnitCaseDao;
import io.tiklab.teston.test.apix.http.unit.cases.model.*;
import io.tiklab.beans.BeanMapper;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.join.JoinTemplate;
import io.tiklab.teston.test.apix.http.unit.cases.entity.ApiUnitCaseEntity;
import io.tiklab.teston.test.apix.http.unit.instance.dao.ApiUnitInstanceDao;
import io.tiklab.teston.test.apix.http.unit.instance.service.ApiUnitInstanceService;
import io.tiklab.teston.test.test.model.TestCase;
import io.tiklab.teston.test.test.model.TestCaseQuery;
import io.tiklab.teston.test.test.service.TestCaseService;
import org.apache.commons.collections.CollectionUtils;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.script.Bindings;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* 接口单元 服务
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
    VariableService variableService;

    @Autowired
    PreScriptService preScriptService;

    @Autowired
    AfterScriptService afterScriptService;

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

        //初始化响应结果
        ResponseResult responseResult = new ResponseResult();
        responseResult.setId(id);
        responseResult.setApiUnitId(id);
        responseResult.setHttpCode(200);
        responseResult.setName("成功");
        responseResult.setDataType("json");
        responseResult.setJsonText("{\"type\": \"object\",\"properties\": {}}");
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
            for (TestCase testCase : testCaseList){
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
    public ApiUnitCaseDataConstruction findApiUnitCaseExt(ApiUnitCase apiUnitCase) {

        //封装请求头数据
        Map headerMap = requestHeaderService.jointHeader(apiUnitCase);
        //封装param参数
        String query = queryParamService.jointParam(apiUnitCase);
        //封装mediaType
        Map mediaType = jointMediaType(apiUnitCase);
        //封装请求体数据
        String body = bodyConstruction(apiUnitCase);
        //获取前置脚本
        String preScript = getPreScript(apiUnitCase);
        //获取后置脚本
        String afterScript = getAfterScript(apiUnitCase.getId());


        ApiUnitCaseDataConstruction apiUnitCaseDataConstruction = new ApiUnitCaseDataConstruction();
        apiUnitCaseDataConstruction.setHeaderMap(headerMap);
        apiUnitCaseDataConstruction.setQuery(query);
        apiUnitCaseDataConstruction.setMediaTypeMap(mediaType);
        apiUnitCaseDataConstruction.setBody(body);
        apiUnitCaseDataConstruction.setPreScript(preScript);
        apiUnitCaseDataConstruction.setAfterScript(afterScript);


        return apiUnitCaseDataConstruction;
    }


    /**
     * 获取请求体
     * 获取contentType
     */
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
                switch (bodyType.getBodyType()){
                    case "formdata":
                        mediaType.put("mediaType","application/form-data");
                        break;
                    case "formUrlencoded":
                        mediaType.put("mediaType","application/x-www-form-urlencoded");
                        break;
                    case "json":
                        mediaType.put("mediaType","application/json");
                        break;
                    case "text":
                        mediaType.put("mediaType","text/plain");
                        break;
                    case "xml":
                        mediaType.put("mediaType","application/xml");
                        break;
                    default:
                        mediaType.put("mediaType","application/octet-stream");
                        break;
                }
            }
        }
        return mediaType;
    }


    /**
     *请求体参数拼接
     * @param apiUnitCase 用例步骤
     *
     */
    private String bodyConstruction(ApiUnitCase apiUnitCase){
        RequestBody bodyType = requestBodyService.findRequestBody(apiUnitCase.getId());
       
//        HashMap<String, Object> map = new HashMap<>();
        String bodyStr = "";

        if (!ObjectUtils.isEmpty(bodyType)){
            switch (bodyType.getBodyType()){
                case "formdata":
                    return getFormData(apiUnitCase,bodyStr);

                case "formUrlencoded":
                    return getFormUrlencoded(apiUnitCase,bodyStr);

//                case "json":
//                    return getJson(apiUnitCase,bodyStr);

                case "raw":
                    return getRaw(apiUnitCase,bodyStr);

            }
        }

        return null;
    }


    /**
     *  获取formData
     */
    private String getFormData (ApiUnitCase apiUnitCase,String bodyStr){
        FormParamQuery formParamQuery = new FormParamQuery();
        formParamQuery.setApiUnitId(apiUnitCase.getId());
        List<FormParam> formParamList = formParamService.findFormParamList(formParamQuery);

        if (CollectionUtils.isNotEmpty(formParamList)){
            for (FormParam formParam : formParamList){
                bodyStr = bodyStr + formParam.getParamName() + "=" + formParam.getValue() + "&";
            }
        }

        return bodyStr;
    }

    /**
     * 获取FormUrlDataMap
     */
    private String getFormUrlencoded (ApiUnitCase apiUnitCase, String bodyStr){
        FormUrlencodedQuery formUrlencodedQuery = new FormUrlencodedQuery();
        formUrlencodedQuery.setApiUnitId(apiUnitCase.getId());
        List<FormUrlEncoded> formUrlEncodedList = formUrlencodedService.findFormUrlencodedList(formUrlencodedQuery);
        if (CollectionUtils.isNotEmpty(formUrlEncodedList)){
            for (FormUrlEncoded formUrlencoded: formUrlEncodedList){
                bodyStr = bodyStr + formUrlencoded.getParamName() + "=" + formUrlencoded.getValue() + "&";
            }
        }

        return bodyStr;
    }

    /**
     * 获取JsonDataMap
     */
    private String getJson(ApiUnitCase apiUnitCase, String bodyStr){

        return bodyStr;
    }


    //获取RawDataMap
    private String getRaw(ApiUnitCase apiUnitCase, String bodyStr){

        //通过测试步骤方法的id查询
        RawParam rawParam = rawParamService.findRawParam(apiUnitCase.getId());
        if(!ObjectUtils.isEmpty(rawParam)){
            String raw = rawParam.getRaw();
            bodyStr = raw;

//            if(rawParam.getType().equals("application/json")){
//                bodyStr = raw;
//            }
//
//            if(rawParam.getType().equals("xml")){
//                //解析xml
//            }
//
//            if(rawParam.getType().equals("text")){
//                //解析text
//            }


        }

        return bodyStr;

    }



    /**
     * 获取前置脚本
     */
    private String getPreScript(ApiUnitCase apiUnitCase){
        PreScript preScript = preScriptService.findPreScript(apiUnitCase.getId());
        if (preScript == null||preScript.getScriptex()==null) {
            return null;
        }
        return preScript.getScriptex();
    }


    /**
     * 获取脚本
     */
    private String getAfterScript(String caseId) {
        AfterScript afterScript = afterScriptService.findAfterScript(caseId);
        if (afterScript == null||afterScript.getScriptex()==null) {
            return null;
        }

        return afterScript.getScriptex();
    }


}