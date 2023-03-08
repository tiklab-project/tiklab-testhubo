package io.tiklab.teston.test.apix.http.unit.cases.service;

import io.tiklab.teston.test.apix.http.unit.cases.dao.ResponseHeaderDao;
import io.tiklab.teston.test.apix.http.unit.cases.entity.ResponseHeaderEntity;
import io.tiklab.core.page.PaginationBuilder;


import io.tiklab.core.page.Pagination;
import io.tiklab.beans.BeanMapper;
import io.tiklab.join.JoinTemplate;
import io.tiklab.teston.test.apix.http.unit.cases.model.ResponseHeader;
import io.tiklab.teston.test.apix.http.unit.cases.model.ResponseHeaderQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 响应头 服务
*/
@Service
public class ResponseHeaderServiceImpl implements ResponseHeaderService {

    @Autowired
    ResponseHeaderDao responseHeaderDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createResponseHeader(@NotNull @Valid ResponseHeader responseHeader) {
        ResponseHeaderEntity responseHeaderEntity = BeanMapper.map(responseHeader, ResponseHeaderEntity.class);

        return responseHeaderDao.createResponseHeader(responseHeaderEntity);
    }

    @Override
    public void updateResponseHeader(@NotNull @Valid ResponseHeader responseHeader) {
        ResponseHeaderEntity responseHeaderEntity = BeanMapper.map(responseHeader, ResponseHeaderEntity.class);

        responseHeaderDao.updateResponseHeader(responseHeaderEntity);
    }

    @Override
    public void deleteResponseHeader(@NotNull String id) {
        responseHeaderDao.deleteResponseHeader(id);
    }

    @Override
    public ResponseHeader findOne(String id) {
        ResponseHeaderEntity responseHeaderEntity = responseHeaderDao.findResponseHeader(id);

        ResponseHeader responseHeader = BeanMapper.map(responseHeaderEntity, ResponseHeader.class);
        return responseHeader;
    }

    @Override
    public List<ResponseHeader> findList(List<String> idList) {
        List<ResponseHeaderEntity> responseHeaderEntityList =  responseHeaderDao.findResponseHeaderList(idList);

        List<ResponseHeader> responseHeaderList =  BeanMapper.mapList(responseHeaderEntityList,ResponseHeader.class);
        return responseHeaderList;
    }

    @Override
    public ResponseHeader findResponseHeader(@NotNull String id) {
        ResponseHeader responseHeader = findOne(id);

        joinTemplate.joinQuery(responseHeader);
        return responseHeader;
    }

    @Override
    public List<ResponseHeader> findAllResponseHeader() {
        List<ResponseHeaderEntity> responseHeaderEntityList =  responseHeaderDao.findAllResponseHeader();

        List<ResponseHeader> responseHeaderList =  BeanMapper.mapList(responseHeaderEntityList,ResponseHeader.class);

        joinTemplate.joinQuery(responseHeaderList);
        return responseHeaderList;
    }

    @Override
    public List<ResponseHeader> findResponseHeaderList(ResponseHeaderQuery responseHeaderQuery) {
        List<ResponseHeaderEntity> responseHeaderEntityList = responseHeaderDao.findResponseHeaderList(responseHeaderQuery);

        List<ResponseHeader> responseHeaderList = BeanMapper.mapList(responseHeaderEntityList,ResponseHeader.class);

        joinTemplate.joinQuery(responseHeaderList);

        return responseHeaderList;
    }

    @Override
    public Pagination<ResponseHeader> findResponseHeaderPage(ResponseHeaderQuery responseHeaderQuery) {

        Pagination<ResponseHeaderEntity>  pagination = responseHeaderDao.findResponseHeaderPage(responseHeaderQuery);

        List<ResponseHeader> responseHeaderList = BeanMapper.mapList(pagination.getDataList(),ResponseHeader.class);

        joinTemplate.joinQuery(responseHeaderList);

        return PaginationBuilder.build(pagination,responseHeaderList);
    }
}