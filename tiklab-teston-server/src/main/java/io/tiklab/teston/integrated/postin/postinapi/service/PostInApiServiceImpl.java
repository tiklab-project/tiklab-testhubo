package io.tiklab.teston.integrated.postin.postinapi.service;

import io.tiklab.core.exception.ApplicationException;
import io.tiklab.eam.common.context.LoginContext;
import io.tiklab.postin.api.apix.model.Apix;
import io.tiklab.postin.api.apix.model.ApixQuery;
import io.tiklab.postin.api.apix.service.ApixService;
import io.tiklab.postin.api.http.definition.model.*;
import io.tiklab.postin.api.http.definition.model.RawParam;
import io.tiklab.postin.api.http.definition.model.RequestHeader;
import io.tiklab.postin.api.http.definition.service.HttpApiService;
import io.tiklab.rpc.client.router.lookup.FixedLookup;
import io.tiklab.teston.integrated.postin.postinapi.model.PostInApiToCase;
import io.tiklab.teston.integrated.postin.integratedurl.model.IntegratedUrl;
import io.tiklab.teston.integrated.postin.integratedurl.model.IntegratedUrlQuery;
import io.tiklab.teston.integrated.postin.integratedurl.service.PostinUrlService;
import io.tiklab.teston.integrated.postin.workspaceBind.model.WorkspaceBind;
import io.tiklab.teston.integrated.postin.workspaceBind.model.WorkspaceBindQuery;
import io.tiklab.teston.integrated.postin.workspaceBind.service.WorkspaceBindService;
import io.tiklab.teston.support.utils.RpcClientApixUtil;
import io.tiklab.teston.test.apix.http.unit.cases.model.*;
import io.tiklab.teston.test.apix.http.unit.cases.model.FormParam;
import io.tiklab.teston.test.apix.http.unit.cases.service.*;
import io.tiklab.teston.test.test.model.TestCase;
import io.tiklab.teston.test.test.service.TestCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Service
public class PostInApiServiceImpl implements PostInApiService {

    @Autowired
    RpcClientApixUtil rpcClientApixUtil;

    @Autowired
    PostinUrlService postinUrlService;

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

    /**mei
     * rpc 调用
     */
    HttpApiService httpApiServiceRpc(){
        IntegratedUrlQuery integratedUrlQuery = new IntegratedUrlQuery();
        integratedUrlQuery.setUserId(LoginContext.getLoginId());
        integratedUrlQuery.setProjectName("postin");
        List<IntegratedUrl> integratedUrlList = postinUrlService.findPostinUrlList(integratedUrlQuery);
        return rpcClientApixUtil.rpcClient().getBean(HttpApiService.class, new FixedLookup(integratedUrlList.get(0).getUrl()));
    }

    /**
     * rpc 调用
     */
    ApixService apixServiceRpc(){
        IntegratedUrlQuery integratedUrlQuery = new IntegratedUrlQuery();
        integratedUrlQuery.setUserId(LoginContext.getLoginId());
        integratedUrlQuery.setProjectName("postin");
        List<IntegratedUrl> integratedUrlList = postinUrlService.findPostinUrlList(integratedUrlQuery);
        return rpcClientApixUtil.rpcClient().getBean(ApixService.class, new FixedLookup(integratedUrlList.get(0).getUrl()));
    }

    @Override
    public List<Apix> findPostInApiList(String repositoryId) {
        //查询出仓库绑定的空间
        WorkspaceBindQuery workspaceBindQuery = new WorkspaceBindQuery();
        workspaceBindQuery.setRepositoryId(repositoryId);
        List<WorkspaceBind> workspaceBindList = workspaceBindService.findWorkspaceBindList(workspaceBindQuery);

        //存放接口
        ArrayList<Apix> apiList = new ArrayList<>();

        if(workspaceBindList!=null&&workspaceBindList.size()>0){
            for(WorkspaceBind workspaceBind:workspaceBindList){
                //通过空间id查询下面所有的接口
                ApixQuery apixQuery = new ApixQuery();
                apixQuery.setWorkspaceId(workspaceBind.getWorkspace().getId());
                List<Apix> apixList = apixServiceRpc().findApixList(apixQuery);

                if(apixList==null&&apixList.size()==0){
                    continue;
                }

                //通过接口中间层id查询对应接口的数据
                for(Apix apix:apixList){
                    HttpApi httpApi = httpApiServiceRpc().findHttpApi(apix.getId());
                    apix.setHttpApi(httpApi);
                }

                apiList.addAll(apixList);
            }
        }

        return apiList;
    }

    @Override
    public void createPostInApiToCase(PostInApiToCase postInApiToCase) {
        if(postInApiToCase.getApiList() != null){
            for(String apiId:postInApiToCase.getApiList()){

                HttpApi httpApi = httpApiServiceRpc().findHttpApi(apiId);
                ApiUnitCase apiUnitCase = new ApiUnitCase();

                String apiUnitCaseId = null;
                try {
                    //apix -> testcase  中间公共表
                    Apix apix = apixServiceRpc().findApix(apiId);
                    TestCase testCase = new TestCase();
                    testCase.setName(apix.getName());
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
                        RequestBody testOnRequestBody = new RequestBody();
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
                io.tiklab.teston.test.apix.http.unit.cases.model.RequestHeader testOnHeader = new io.tiklab.teston.test.apix.http.unit.cases.model.RequestHeader();
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
                QueryParams testOnQuery = new QueryParams();
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
            for(io.tiklab.postin.api.http.definition.model.FormParam postInFormParam:httpApi.getFormList()){
                FormParam testOnFormParam = new FormParam();
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
                FormUrlEncoded testOnFormUrl = new FormUrlEncoded();
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
            io.tiklab.teston.test.apix.http.unit.cases.model.RawParam testOnRaw = new io.tiklab.teston.test.apix.http.unit.cases.model.RawParam();
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
            PreScript testOnPre = new PreScript();
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
            AfterScript testOnAfter = new AfterScript();
            testOnAfter.setScriptex(postInAfter);
            testOnAfter.setApiUnitId(apiUnitCaseId);
            testOnAfter.setId(apiUnitCaseId);
            testOnAfterScriptService.createAfterScript(testOnAfter);
        }catch (Exception e){
            throw new ApplicationException("Error in converting after script", e);
        }

    }

}
