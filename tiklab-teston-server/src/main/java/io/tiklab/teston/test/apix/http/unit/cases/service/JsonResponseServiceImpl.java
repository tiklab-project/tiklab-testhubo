package io.tiklab.teston.test.apix.http.unit.cases.service;

import io.tiklab.teston.test.apix.http.unit.cases.dao.JsonResponseDao;
import io.tiklab.core.page.PaginationBuilder;


import io.tiklab.core.page.Pagination;
import io.tiklab.beans.BeanMapper;
import io.tiklab.join.JoinTemplate;
import io.tiklab.teston.test.apix.http.unit.cases.entity.JsonResponseEntity;
import io.tiklab.teston.test.apix.http.unit.cases.model.JsonResponse;
import io.tiklab.teston.test.apix.http.unit.cases.model.JsonResponseQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

/**
* 响应中json 服务
*/
@Service
public class JsonResponseServiceImpl implements JsonResponseService {

    @Autowired
    JsonResponseDao jsonResponseDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createJsonResponse(@NotNull @Valid JsonResponse jsonResponse) {
        JsonResponseEntity jsonResponseEntity = BeanMapper.map(jsonResponse, JsonResponseEntity.class);

        return jsonResponseDao.createJsonResponse(jsonResponseEntity);
    }

    @Override
    public void updateJsonResponse(@NotNull @Valid JsonResponse jsonResponse) {
        JsonResponseEntity jsonResponseEntity = BeanMapper.map(jsonResponse, JsonResponseEntity.class);

        jsonResponseDao.updateJsonResponse(jsonResponseEntity);
    }

    @Override
    public void deleteJsonResponse(@NotNull String id) {
        jsonResponseDao.deleteJsonResponse(id);
    }

    @Override
    public JsonResponse findOne(String id) {
        JsonResponseEntity jsonResponseEntity = jsonResponseDao.findJsonResponse(id);

        JsonResponse jsonResponse = BeanMapper.map(jsonResponseEntity, JsonResponse.class);
        return jsonResponse;
    }

    @Override
    public List<JsonResponse> findList(List<String> idList) {
        List<JsonResponseEntity> jsonResponseEntityList =  jsonResponseDao.findJsonResponseList(idList);

        List<JsonResponse> jsonResponseList =  BeanMapper.mapList(jsonResponseEntityList,JsonResponse.class);
        return jsonResponseList;
    }

    @Override
    public JsonResponse findJsonResponse(@NotNull String id) {
        JsonResponse jsonResponse = findOne(id);

        joinTemplate.joinQuery(jsonResponse);
        return jsonResponse;
    }

    @Override
    public List<JsonResponse> findAllJsonResponse() {
        List<JsonResponseEntity> jsonResponseEntityList =  jsonResponseDao.findAllJsonResponse();

        List<JsonResponse> jsonResponseList =  BeanMapper.mapList(jsonResponseEntityList,JsonResponse.class);

        joinTemplate.joinQuery(jsonResponseList);
        return jsonResponseList;
    }

    @Override
    public List<JsonResponse> findJsonResponseList(JsonResponseQuery jsonResponseQuery) {
        List<JsonResponseEntity> jsonResponseEntityList = jsonResponseDao.findJsonResponseList(jsonResponseQuery);

        List<JsonResponse> jsonResponseList = BeanMapper.mapList(jsonResponseEntityList,JsonResponse.class);

        joinTemplate.joinQuery(jsonResponseList);

        return jsonResponseList;
    }

    @Override
    public Pagination<JsonResponse> findJsonResponsePage(JsonResponseQuery jsonResponseQuery) {

        Pagination<JsonResponseEntity>  pagination = jsonResponseDao.findJsonResponsePage(jsonResponseQuery);

        List<JsonResponse> jsonResponseList = BeanMapper.mapList(pagination.getDataList(),JsonResponse.class);

        joinTemplate.joinQuery(jsonResponseList);

        return PaginationBuilder.build(pagination,jsonResponseList);
    }

    @Override
    public List<JsonResponse> findJsonResponseListTree(JsonResponseQuery jsonResponseQuery) {
        List<JsonResponse> matchJsonResponseList = this.findJsonResponseList(jsonResponseQuery);

        //查找第一级属性列表
        List<JsonResponse> topJsonResponseList = findTopJsonResponseList(matchJsonResponseList);

        //设置下级节点列表
        if(topJsonResponseList != null && topJsonResponseList.size() > 0){
            for(JsonResponse jsonResponse:topJsonResponseList){
                setChildren(matchJsonResponseList,jsonResponse);
            }
        }

        return topJsonResponseList;
    }

    /**
     * 查找第一级属性列表
     * @param matchJsonResponseList
     * @return
     */
    List<JsonResponse> findTopJsonResponseList(List<JsonResponse> matchJsonResponseList) {
        List<JsonResponse> jsonResponseList = matchJsonResponseList.stream()
                .filter(jsonResponse -> (jsonResponse.getParent() == null || jsonResponse.getParent().getId() == null))
                .collect(Collectors.toList());
        return jsonResponseList;
    }

    /**
     * 设置下级节点列表
     * @param matchJsonResponseList
     * @param parentJsonResponse
     */
    void setChildren(List<JsonResponse> matchJsonResponseList,JsonResponse parentJsonResponse){
        List<JsonResponse> childList = matchJsonResponseList.stream()
                .filter(jsonResponse -> (jsonResponse.getParent() != null && jsonResponse.getParent().getId() != null && jsonResponse.getParent().getId().equals(parentJsonResponse.getId())))
                .collect(Collectors.toList());

        if(childList != null && childList.size() > 0){
            parentJsonResponse.setChildren(childList);

            //设置下级节点列表
            for(JsonResponse jsonResponse:childList){
                setChildren(matchJsonResponseList,jsonResponse);
            }
        }
    }
}