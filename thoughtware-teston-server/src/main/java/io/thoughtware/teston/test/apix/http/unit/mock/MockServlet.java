package io.thoughtware.teston.test.apix.http.unit.mock;

import io.thoughtware.teston.test.apix.http.unit.cases.model.*;
import io.thoughtware.core.exception.ApplicationException;
import io.thoughtware.teston.test.apix.http.unit.cases.model.*;
import io.thoughtware.teston.test.apix.http.unit.cases.service.ApiUnitCaseService;
import io.thoughtware.teston.test.apix.http.unit.cases.service.ResponseHeaderService;
import io.thoughtware.teston.test.apix.http.unit.cases.service.ResponseResultService;
import io.thoughtware.teston.test.test.model.TestCase;
import io.thoughtware.teston.test.test.service.TestCaseService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * mock地址拦截
 */
@WebServlet(name = "mockx",urlPatterns = "/mockx/*")
public class MockServlet extends HttpServlet {

    @Autowired
    ApiUnitCaseService apiUnitCaseService;

    @Autowired
    ResponseResultService responseResultService;

    @Autowired
    ResponseHeaderService responseHeaderService;

    @Autowired
    TestCaseService testCaseService;

    /**
     * get类型请求
     * @param request
     * @param response
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        sendMock(request,response);
    }

    /**
     * post类型请求
     * @param req
     * @param resp
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        sendMock(req, resp);
    }


    private void sendMock(HttpServletRequest request, HttpServletResponse response){
        //浏览器用utf8来解析返回的数据
        response.setContentType("text/html;charset=utf-8");
        //servlet用UTF-8转码，而不是用默认的ISO8859
        response.setCharacterEncoding("utf-8");

        //解析请求地址
        String[] parts = request.getRequestURI().split("/");
        String repositoryId = parts[2];
        //构建接口路径
        String path = "";
        for(int i=3; i<parts.length; i++) {
            path += "/" + parts[i];
        }

        String apiId = getApiId(repositoryId, path);

        setResponse(response,apiId);
    }


    /**
     * 获取接口id
     * @param repositoryId
     * @param path
     */
    private String getApiId(String repositoryId, String path){
        ApiUnitCaseQuery apiUnitCaseQuery = new ApiUnitCaseQuery();
        apiUnitCaseQuery.setPath(path);
        List<ApiUnitCase> apiUnitCaseList = apiUnitCaseService.findApiUnitCaseList(apiUnitCaseQuery);

        ArrayList<ApiUnitCase> arrayList = new ArrayList<>();

        if(apiUnitCaseList!=null&&apiUnitCaseList.size()>0){
            for(ApiUnitCase apiUnitCase:apiUnitCaseList){
                TestCase testCase = testCaseService.findTestCase(apiUnitCase.getId());
                if(Objects.equals(testCase.getRepositoryId(), repositoryId)){
                    arrayList.add(apiUnitCase);
                }
            }
        }

        if(arrayList.size()>1){
            throw new ApplicationException("当前项目下存在多个相同接口路径: "+path+" ，产生冲突");
        }else {
            return arrayList.get(0).getId();
        }
    }

    private void setResponse(HttpServletResponse response, String apiId){
        ResponseResultUnit responseResultUnit = responseResultService.findResponseResult(apiId);

        String bodyData = processApiBody(responseResultUnit);
        try {
            ServletOutputStream servletOutputStream = response.getOutputStream();
            servletOutputStream.write(bodyData.getBytes("UTF-8"));

            response.setStatus(responseResultUnit.getHttpCode());

            ResponseHeaderUnitQuery responseHeaderUnitQuery = new ResponseHeaderUnitQuery();
            responseHeaderUnitQuery.setApiUnitId(apiId);
            List<ResponseHeaderUnit> responseHeaderUnitList = responseHeaderService.findResponseHeaderList(responseHeaderUnitQuery);
            if(responseHeaderUnitList !=null){
                for(ResponseHeaderUnit responseHeaderUnit : responseHeaderUnitList){
                    response.setHeader(responseHeaderUnit.getHeaderName(), responseHeaderUnit.getValue());
                }
            }
        }catch (Exception e){
            throw new ApplicationException("set response body error :"+e);
        }
    }

    /**
     * 处理 接口定义 中响应体
     */
    private String processApiBody(ResponseResultUnit responseResultUnit){
        String jsonMockData = null;
        JsonGenerator jsonGenerator = new JsonGenerator();
        if("json".equals(responseResultUnit.getDataType())){
            String jsonText = responseResultUnit.getJsonText();
            jsonMockData = jsonGenerator.generateJson(jsonText);
        }else {
            String rawText = responseResultUnit.getRawText();
            jsonMockData =jsonGenerator.generateRaw(rawText);
        }
        return jsonMockData;
    }



}
