package net.tiklab.teston.apitest.http.unittest.service;

import net.tiklab.core.page.PaginationBuilder;
import net.tiklab.teston.apitest.http.unittest.dao.RequestBodyDao;
import net.tiklab.teston.apitest.http.unittest.entity.RequestBodyEntity;
import net.tiklab.teston.apitest.http.unittest.model.RequestBody;
import net.tiklab.teston.apitest.http.unittest.model.RequestBodyQuery;

import net.tiklab.core.page.Pagination;
import net.tiklab.beans.BeanMapper;
import net.tiklab.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* RequestBodyServiceImpl
*/
@Service
public class RequestBodyServiceImpl implements RequestBodyService {

    @Autowired
    RequestBodyDao requestBodyDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createRequestBody(@NotNull @Valid RequestBody requestBody) {
        RequestBodyEntity requestBodyEntity = BeanMapper.map(requestBody, RequestBodyEntity.class);

        return requestBodyDao.createRequestBody(requestBodyEntity);
    }

    @Override
    public void updateRequestBody(@NotNull @Valid RequestBody requestBody) {
        RequestBodyEntity requestBodyEntity = BeanMapper.map(requestBody, RequestBodyEntity.class);

        requestBodyDao.updateRequestBody(requestBodyEntity);
    }

    @Override
    public void deleteRequestBody(@NotNull String id) {
        requestBodyDao.deleteRequestBody(id);
    }

    @Override
    public RequestBody findOne(String id) {
        RequestBodyEntity requestBodyEntity = requestBodyDao.findRequestBody(id);

        RequestBody requestBody = BeanMapper.map(requestBodyEntity, RequestBody.class);
        return requestBody;
    }

    @Override
    public List<RequestBody> findList(List<String> idList) {
        List<RequestBodyEntity> requestBodyEntityList =  requestBodyDao.findRequestBodyList(idList);

        List<RequestBody> requestBodyList =  BeanMapper.mapList(requestBodyEntityList, RequestBody.class);
        return requestBodyList;
    }

    @Override
    public RequestBody findRequestBody(@NotNull String id) {
        RequestBody requestBody = findOne(id);

        joinTemplate.joinQuery(requestBody);
        return requestBody;
    }

    @Override
    public List<RequestBody> findAllRequestBody() {
        List<RequestBodyEntity> requestBodyEntityList =  requestBodyDao.findAllRequestBody();

        List<RequestBody> requestBodyList =  BeanMapper.mapList(requestBodyEntityList, RequestBody.class);

        joinTemplate.joinQuery(requestBodyList);
        return requestBodyList;
    }

    @Override
    public List<RequestBody> findRequestBodyList(RequestBodyQuery requestBodyQuery) {
        List<RequestBodyEntity> requestBodyEntityList = requestBodyDao.findRequestBodyList(requestBodyQuery);

        List<RequestBody> requestBodyList = BeanMapper.mapList(requestBodyEntityList, RequestBody.class);

        joinTemplate.joinQuery(requestBodyList);

        return requestBodyList;
    }

    @Override
    public Pagination<RequestBody> findRequestBodyPage(RequestBodyQuery requestBodyQuery) {

        Pagination<RequestBodyEntity>  pagination = requestBodyDao.findRequestBodyPage(requestBodyQuery);

        List<RequestBody> requestBodyList = BeanMapper.mapList(pagination.getDataList(), RequestBody.class);

        joinTemplate.joinQuery(requestBodyList);

        return PaginationBuilder.build(pagination,requestBodyList);
    }

    //----------------
    /**
     *请求体参数拼接
     * @param step 用例步骤
     * @param requestInstance 测试结果po
     */
//    public Map jointParamBody(Step step, RequestInstance requestInstance){
//        RequestBodyEx requestBody = requestBodyService.findRequestBody(step.getId());
//        HashMap<String, Object> map = new HashMap<>();
//        if (!ObjectUtils.isEmpty(requestBody)){
//            String request=null;
//            //请求体为raw格式
//            if ("raw".equals(requestBody.getBodyType())){
//                //通过测试步骤方法的id查询
//                RawParamQuery rawParamQuery = new RawParamQuery();
//                rawParamQuery.setStepId(step.getId());
//                List<RawParamEntity> rawParamList = rawParamDao.findRawParamList(rawParamQuery);
//                if(CollectionUtils.isNotEmpty(rawParamList)){
//                    String raw = rawParamList.get(0).getRaw();
//                    map.put("raw",raw);
//                    //给用例测试报告-请求表添加请求体raw数据
//                    request = JSON.toJSONString(rawParamList);
//                }
//            }
//            //请求体为form格式
//            if ("form".equals(requestBody.getBodyType())){
//                //通过测试步骤方法的id查询
//                FormParamQuery formParamQuery = new FormParamQuery();
//                formParamQuery.setStepId(step.getId());
//                List<FormParamEntity> formParamList = formParamDao.findFormParamList(formParamQuery);
//                if (CollectionUtils.isNotEmpty(formParamList)){
//                    for (FormParamEntity formParam:formParamList){
//                        map.put(formParam.getParamName(),formParam.getEg());
//                    }
//                    //给用例测试报告-请求表添加请求体form数据
//                    request =JSON.toJSONString(formParamList);
//                }
//
//            }
//            //请求体为json
//            if ("json".equals(requestBody.getBodyType())){
//                //通过测试步骤方法的id查询
//                JsonParamQuery jsonParamQuery = new JsonParamQuery();
//                jsonParamQuery.setStepId(step.getId());
//                List<JsonParam> jsonParamListTree = jsonParamService.findJsonParamListTree(jsonParamQuery);
//                if (CollectionUtils.isNotEmpty(jsonParamListTree)){
//                    for (JsonParam jsonParam:jsonParamListTree){
//                        //第一级的
//                        if (ObjectUtils.isEmpty(jsonParam.getParent())){
//                            map.put(jsonParam.getParamName(), jsonParam.getEg());
//                        }
//                        if (!ObjectUtils.isEmpty(jsonParam.getParent())){
//                            map.put(jsonParam.getParamName(),jsonParam.getChildren());
//                        }
//                    }
//                    //给用例测试报告-请求表添加请求体json数据
//                    request =JSON.toJSONString(jsonParamListTree);
//                }
//                requestInstance.setRequestParam(request);
//            }
//        }
//        return map;
//    }
}