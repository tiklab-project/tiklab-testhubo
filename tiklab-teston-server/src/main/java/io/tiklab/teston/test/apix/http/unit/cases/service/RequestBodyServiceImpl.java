package io.tiklab.teston.test.apix.http.unit.cases.service;

import io.tiklab.teston.test.apix.http.unit.cases.dao.RequestBodyDao;
import io.tiklab.teston.test.apix.http.unit.cases.entity.RequestBodyEntity;
import io.tiklab.core.page.PaginationBuilder;


import io.tiklab.core.page.Pagination;
import io.tiklab.beans.BeanMapper;
import io.tiklab.join.JoinTemplate;
import io.tiklab.teston.test.apix.http.unit.cases.model.RequestBody;
import io.tiklab.teston.test.apix.http.unit.cases.model.RequestBodyQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 请求体 服务
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


}