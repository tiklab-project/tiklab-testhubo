package net.tiklab.teston.apix.http.unit.http.cases.service;

import net.tiklab.core.page.PaginationBuilder;


import net.tiklab.core.page.Pagination;
import net.tiklab.beans.BeanMapper;
import net.tiklab.join.JoinTemplate;
import net.tiklab.teston.apix.http.unit.cases.model.RequestBody;
import net.tiklab.teston.apix.http.unit.cases.model.RequestBodyQuery;
import net.tiklab.teston.apix.http.unit.cases.service.RequestBodyService;
import net.tiklab.teston.apix.http.unit.http.cases.dao.RequestBodyDao;
import net.tiklab.teston.apix.http.unit.http.cases.entity.RequestBodyEntity;
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