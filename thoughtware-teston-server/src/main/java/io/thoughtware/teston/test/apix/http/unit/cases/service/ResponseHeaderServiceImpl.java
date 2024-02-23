package io.thoughtware.teston.test.apix.http.unit.cases.service;

import io.thoughtware.teston.test.apix.http.unit.cases.dao.ResponseHeaderDao;
import io.thoughtware.teston.test.apix.http.unit.cases.model.ResponseHeaderUnit;
import io.thoughtware.teston.test.apix.http.unit.cases.model.ResponseHeaderUnitQuery;
import io.thoughtware.teston.test.apix.http.unit.cases.entity.ResponseHeaderEntity;
import io.thoughtware.core.page.PaginationBuilder;


import io.thoughtware.core.page.Pagination;
import io.thoughtware.toolkit.beans.BeanMapper;
import io.thoughtware.toolkit.join.JoinTemplate;
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
    public String createResponseHeader(@NotNull @Valid ResponseHeaderUnit responseHeaderUnit) {
        ResponseHeaderEntity responseHeaderEntity = BeanMapper.map(responseHeaderUnit, ResponseHeaderEntity.class);

        return responseHeaderDao.createResponseHeader(responseHeaderEntity);
    }

    @Override
    public void updateResponseHeader(@NotNull @Valid ResponseHeaderUnit responseHeaderUnit) {
        ResponseHeaderEntity responseHeaderEntity = BeanMapper.map(responseHeaderUnit, ResponseHeaderEntity.class);

        responseHeaderDao.updateResponseHeader(responseHeaderEntity);
    }

    @Override
    public void deleteResponseHeader(@NotNull String id) {
        responseHeaderDao.deleteResponseHeader(id);
    }

    @Override
    public void deleteAllResponseHeader( String caseId) {
        ResponseHeaderUnitQuery responseHeaderUnitQuery = new ResponseHeaderUnitQuery();
        responseHeaderUnitQuery.setApiUnitId(caseId);
        List<ResponseHeaderUnit> responseHeaderList = findResponseHeaderList(responseHeaderUnitQuery);
        for(ResponseHeaderUnit responseHeaderUnit : responseHeaderList){
            responseHeaderDao.deleteResponseHeader(responseHeaderUnit.getId());
        }
    }

    @Override
    public ResponseHeaderUnit findOne(String id) {
        ResponseHeaderEntity responseHeaderEntity = responseHeaderDao.findResponseHeader(id);

        ResponseHeaderUnit responseHeaderUnit = BeanMapper.map(responseHeaderEntity, ResponseHeaderUnit.class);
        return responseHeaderUnit;
    }

    @Override
    public List<ResponseHeaderUnit> findList(List<String> idList) {
        List<ResponseHeaderEntity> responseHeaderEntityList =  responseHeaderDao.findResponseHeaderList(idList);

        List<ResponseHeaderUnit> responseHeaderUnitList =  BeanMapper.mapList(responseHeaderEntityList, ResponseHeaderUnit.class);
        return responseHeaderUnitList;
    }

    @Override
    public ResponseHeaderUnit findResponseHeader(@NotNull String id) {
        ResponseHeaderUnit responseHeaderUnit = findOne(id);

        joinTemplate.joinQuery(responseHeaderUnit);
        return responseHeaderUnit;
    }

    @Override
    public List<ResponseHeaderUnit> findAllResponseHeader() {
        List<ResponseHeaderEntity> responseHeaderEntityList =  responseHeaderDao.findAllResponseHeader();

        List<ResponseHeaderUnit> responseHeaderUnitList =  BeanMapper.mapList(responseHeaderEntityList, ResponseHeaderUnit.class);

        joinTemplate.joinQuery(responseHeaderUnitList);
        return responseHeaderUnitList;
    }

    @Override
    public List<ResponseHeaderUnit> findResponseHeaderList(ResponseHeaderUnitQuery responseHeaderUnitQuery) {
        List<ResponseHeaderEntity> responseHeaderEntityList = responseHeaderDao.findResponseHeaderList(responseHeaderUnitQuery);

        List<ResponseHeaderUnit> responseHeaderUnitList = BeanMapper.mapList(responseHeaderEntityList, ResponseHeaderUnit.class);

        joinTemplate.joinQuery(responseHeaderUnitList);

        return responseHeaderUnitList;
    }

    @Override
    public Pagination<ResponseHeaderUnit> findResponseHeaderPage(ResponseHeaderUnitQuery responseHeaderUnitQuery) {

        Pagination<ResponseHeaderEntity>  pagination = responseHeaderDao.findResponseHeaderPage(responseHeaderUnitQuery);

        List<ResponseHeaderUnit> responseHeaderUnitList = BeanMapper.mapList(pagination.getDataList(), ResponseHeaderUnit.class);

        joinTemplate.joinQuery(responseHeaderUnitList);

        return PaginationBuilder.build(pagination, responseHeaderUnitList);
    }
}