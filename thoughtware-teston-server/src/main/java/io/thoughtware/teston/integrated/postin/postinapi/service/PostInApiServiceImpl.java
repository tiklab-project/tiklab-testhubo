package io.thoughtware.teston.integrated.postin.postinapi.service;

import io.thoughtware.teston.common.RestTemplateUtils;
import io.thoughtware.teston.integrated.integratedurl.model.IntegratedUrlQuery;
import io.thoughtware.teston.integrated.integratedurl.service.IntegratedUrlService;
import io.thoughtware.teston.integrated.postin.postinapi.model.*;
import io.thoughtware.teston.integrated.postin.workspaceBind.model.WorkspaceBind;
import io.thoughtware.teston.integrated.postin.workspaceBind.model.WorkspaceBindQuery;
import io.thoughtware.teston.integrated.postin.workspaceBind.service.WorkspaceBindService;
import io.thoughtware.teston.test.apix.http.unit.cases.model.*;
import io.thoughtware.teston.test.apix.http.unit.cases.service.*;
import io.thoughtware.teston.test.test.model.TestCase;
import io.thoughtware.teston.test.test.service.TestCaseService;
import io.thoughtware.core.exception.ApplicationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Service
public class PostInApiServiceImpl implements PostInApiService {

    @Autowired
    IntegratedUrlService integratedUrlService;

    @Autowired
    WorkspaceBindService workspaceBindService;

    @Autowired
    RequestHeaderService testOnRequestHeaderService;

    @Autowired
    TestCaseService testCaseService;

    @Autowired
    ApiUnitCaseService apiUnitCaseService;

    @Autowired
    QueryParamService testOnQueryService;

    @Autowired
    RequestBodyService testOnRequestBodyService;

    @Autowired
    FormParamService testOndFormParam;

    @Autowired
    FormUrlencodedService testOnFormUrlEncoded;

    @Autowired
    RawParamService testOnRawService;

    @Autowired
    PreScriptService testOnPreScriptService;

    @Autowired
    AfterScriptService testOnAfterScriptService;

    @Autowired
    RestTemplateUtils restTemplateUtils;


    @Override
    public List<Apix> findPostInApiList(String repositoryId) {
        //查询出仓库绑定的空间
        WorkspaceBindQuery workspaceBindQuery = new WorkspaceBindQuery();
        workspaceBindQuery.setRepositoryId(repositoryId);
        List<WorkspaceBind> workspaceBindList = workspaceBindService.findWorkspaceBindList(workspaceBindQuery);

        //存放接口
        ArrayList<Apix> apiList = new ArrayList<>();

        //请求的apix接口
        String postInUrl = getPostInUrl(repositoryId);
        String apxUrl = postInUrl+"/api/apx/findApixList";
        if(workspaceBindList!=null&&workspaceBindList.size()>0){
            for(WorkspaceBind workspaceBind:workspaceBindList){
                //通过空间id查询下面所有的接口
                ApixQuery apixQuery = new ApixQuery();
                apixQuery.setWorkspaceId(workspaceBind.getWorkspace().getId());
                apixQuery.setProtocolType("http");
                List<Apix> apixList = restTemplateUtils.requestPostList(apxUrl, apixQuery, Apix.class);

                if(apixList==null&&apixList.size()==0){
                    continue;
                }

                apiList.addAll(apixList);
            }
        }

        return apiList;
    }

    @Override
    public void createPostInApiToCase(PostInApiToCase postInApiToCase) {
        String postInUrl = getPostInUrl(postInApiToCase.getRepositoryId());
        //请求的apix接口
        String apxUrl = postInUrl+"/api/httpApi/findHttpApi";

        if(postInApiToCase.getApiList() != null){
            for(String apiId:postInApiToCase.getApiList()){


                MultiValueMap<String, Object> formData = new LinkedMultiValueMap<>();
                formData.add("apiId", apiId);
                formData.add("protocolType", "http");
                HttpApi httpApi = restTemplateUtils.requestPost(apxUrl, formData, HttpApi.class);

                ApiUnitCase apiUnitCase = new ApiUnitCase();
                String apiUnitCaseId = null;
                try {
                    //apix -> testcase  中间公共表
                    Apix apix = httpApi.getApix();
                    TestCase testCase = new TestCase();
                    testCase.setName(apix.getNode().getName());
                    testCase.setCaseType("api-unit");
                    testCase.setTestType("api");
                    testCase.setDesc(apix.getDesc());
                    testCase.setRepositoryId(postInApiToCase.getRepositoryId());
                    apiUnitCase.setTestCase(testCase);

                    apiUnitCase.setPath(apix.getPath());
                    apiUnitCase.setMethodType(httpApi.getMethodType());

                    apiUnitCaseId = apiUnitCaseService.createApiUnitCase(apiUnitCase);
                }catch (Exception e) {
                    throw new ApplicationException("Error in converting apix to testcase", e);
                }


                //请求头转换
                if(apiUnitCaseId!=null&&httpApi.getHeaderList()!=null){
                    convertHeader(apiUnitCaseId,httpApi);
                }

                //query转换
                if(apiUnitCaseId!=null&&httpApi.getQueryList()!=null){
                    convertQuery(apiUnitCaseId,httpApi);
                }


                ApiRequest request = httpApi.getRequest();
                if(apiUnitCaseId!=null&&request!=null){
                    try{
                        RequestBodyUnit testOnRequestBody = new RequestBodyUnit();
                        testOnRequestBody.setBodyType(request.getBodyType());
                        testOnRequestBody.setId(apiUnitCaseId);
                        testOnRequestBody.setApiUnitId(apiUnitCaseId);
                        testOnRequestBodyService.updateRequestBody(testOnRequestBody);
                    }catch (Exception e){
                        throw new ApplicationException("Error in converting request to testcase", e);
                    }


                    //请求体
                    if(request.getBodyType()!=null){
                        switch (request.getBodyType()){
                            case "formdata":
                                if(httpApi.getFormList()!=null){
                                    convertFormData(apiUnitCaseId,httpApi);
                                }
                                break;
                            case "formUrlencoded":
                                if(httpApi.getUrlencodedList()!=null){
                                    convertFormUrlEncoded(apiUnitCaseId,httpApi);
                                }
                                break;
                            case "raw":
                                if(httpApi.getRawParam()!=null){
                                    convertRaw(apiUnitCaseId,httpApi);
                                }
                                break;
                        }
                    }


                    //前置
                    if(request.getAfterScript()!=null){
                        convertPreScript(apiUnitCaseId,request);
                    }

                    //后置
                    if(request.getAfterScript()!=null){
                        convertAfterScript(apiUnitCaseId,request);
                    }
                }


            }
        }
    }


    /**
     * 请求头转换
     * @param apiUnitCaseId
     * @param httpApi
     */
    private void convertHeader(String apiUnitCaseId, HttpApi httpApi){
        try {
            for( RequestHeader postInHeader :httpApi.getHeaderList()){
                RequestHeaderUnit testOnHeader = new RequestHeaderUnit();
                testOnHeader.setHeaderName(postInHeader.getHeaderName());
                testOnHeader.setRequired(postInHeader.getRequired());
                testOnHeader.setValue(postInHeader.getValue());
                testOnHeader.setDesc(postInHeader.getDesc());
                testOnHeader.setSort(postInHeader.getSort());
                testOnHeader.setApiUnit(new ApiUnitCase().setId(apiUnitCaseId));
                testOnRequestHeaderService.createRequestHeader(testOnHeader);
            }
        }catch (Exception e){
            throw new ApplicationException("Error in converting Header",e);
        }
    }

    /**
     * query转换
     * @param apiUnitCaseId
     * @param httpApi
     */
    private void convertQuery(String apiUnitCaseId, HttpApi httpApi) {
        try {
            for(QueryParam postInQuery:httpApi.getQueryList()){
                QueryParamUnit testOnQuery = new QueryParamUnit();
                testOnQuery.setParamName(postInQuery.getParamName());
                testOnQuery.setValue(postInQuery.getValue());
                testOnQuery.setDesc((postInQuery.getDesc()));
                testOnQuery.setSort(postInQuery.getSort());
                testOnQuery.setApiUnit(new ApiUnitCase().setId(apiUnitCaseId));
                testOnQueryService.createQueryParam(testOnQuery);
            }
        }catch (Exception e){
            throw new ApplicationException("Error in converting Query",e);
        }
    }

    /**
     * formdata转换
     * @param apiUnitCaseId
     * @param httpApi
     */
    private void convertFormData(String apiUnitCaseId, HttpApi httpApi) {
        try {
            for(FormParam postInFormParam:httpApi.getFormList()){
                FormParamUnit testOnFormParam = new FormParamUnit();
                testOnFormParam.setParamName(postInFormParam.getParamName());
                testOnFormParam.setValue(postInFormParam.getValue());
                testOnFormParam.setDesc((postInFormParam.getDesc()));
                testOnFormParam.setSort(postInFormParam.getSort());
                testOnFormParam.setApiUnit(new ApiUnitCase().setId(apiUnitCaseId));
                testOndFormParam.createFormParam(testOnFormParam);
            }
        }catch (Exception e){
            throw new ApplicationException("Error in converting FormData",e);
        }
    }

    /**
     *  转换FormUrl
     * @param apiUnitCaseId
     * @param httpApi
     */
    private void convertFormUrlEncoded(String apiUnitCaseId, HttpApi httpApi) {
        try {
            for(FormUrlencoded postInFormUrl:httpApi.getUrlencodedList()){
                FormUrlEncodedUnit testOnFormUrl = new FormUrlEncodedUnit();
                testOnFormUrl.setParamName(postInFormUrl.getParamName());
                testOnFormUrl.setValue(postInFormUrl.getValue());
                testOnFormUrl.setDesc((postInFormUrl.getDesc()));
                testOnFormUrl.setSort(postInFormUrl.getSort());
                testOnFormUrl.setApiUnit(new ApiUnitCase().setId(apiUnitCaseId));
                testOnFormUrlEncoded.createFormUrlencoded(testOnFormUrl);
            }
        }catch (Exception e){
            throw new ApplicationException("Error in converting FormUrlEncoded",e);
        }

    }

    /**
     * 转换raw
     * @param apiUnitCaseId
     * @param httpApi
     */
    private void convertRaw(String apiUnitCaseId, HttpApi httpApi) {
        try {
            RawParam postInRaw = httpApi.getRawParam();
            RawParamUnit testOnRaw = new RawParamUnit();
            testOnRaw.setRaw(postInRaw.getRaw());
            testOnRaw.setType(postInRaw.getType());
            testOnRaw.setApiUnit(new ApiUnitCase().setId(apiUnitCaseId));
            testOnRaw.setId(apiUnitCaseId);
            testOnRawService.createRawParam(testOnRaw);
        }catch (Exception e){
            throw  new ApplicationException("Error in converting RawParam", e);
        }
    }

    /**
     * 转换前置
     * @param apiUnitCaseId
     * @param requestData
     */
    private void convertPreScript(String apiUnitCaseId, ApiRequest requestData) {
        try {
            String postInPre = requestData.getPreScript();
            PreScriptUnit testOnPre = new PreScriptUnit();
            testOnPre.setScriptex(postInPre);
            testOnPre.setApiUnitId(apiUnitCaseId);
            testOnPre.setId(apiUnitCaseId);
            testOnPreScriptService.createPreScript(testOnPre);
        }catch (Exception e){
            throw new ApplicationException("Error in converting pre script", e);
        }

    }

    /**
     * 转换后置
     * @param apiUnitCaseId
     * @param requestData
     */
    private void convertAfterScript(String apiUnitCaseId, ApiRequest requestData) {
        try {
            String postInAfter = requestData.getAfterScript();
            AfterScriptUnit testOnAfter = new AfterScriptUnit();
            testOnAfter.setScriptex(postInAfter);
            testOnAfter.setApiUnitId(apiUnitCaseId);
            testOnAfter.setId(apiUnitCaseId);
            testOnAfterScriptService.createAfterScript(testOnAfter);
        }catch (Exception e){
            throw new ApplicationException("Error in converting after script", e);
        }
    }

    private String getPostInUrl(String repositoryId){
        IntegratedUrlQuery integratedUrlQuery = new IntegratedUrlQuery();
        integratedUrlQuery.setRepositoryId(repositoryId);
        integratedUrlQuery.setProjectName("postin");
        String productUrl = integratedUrlService.getProductUrl(integratedUrlQuery);

        return productUrl;
    }

}
