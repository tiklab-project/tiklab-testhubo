package io.thoughtware.testrubo.test.apix.http.unit.cases.service;

import io.thoughtware.testrubo.test.apix.http.unit.cases.dao.ResponseResultDao;
import io.thoughtware.testrubo.test.apix.http.unit.cases.model.ResponseResultUnit;
import io.thoughtware.testrubo.test.apix.http.unit.cases.model.ResponseResultUnitQuery;
import io.thoughtware.core.page.PaginationBuilder;


import io.thoughtware.core.page.Pagination;
import io.thoughtware.toolkit.beans.BeanMapper;
import io.thoughtware.toolkit.join.JoinTemplate;
import io.thoughtware.testrubo.test.apix.http.unit.cases.entity.ResponseResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

/**
* 响应结果 服务
*/
@Service
public class ResponseResultServiceImpl implements ResponseResultService {

    @Autowired
    ResponseResultDao responseResultDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createResponseResult(@NotNull @Valid ResponseResultUnit responseResultUnit) {
        ResponseResultEntity responseResultEntity = BeanMapper.map(responseResultUnit, ResponseResultEntity.class);
        responseResultEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));
        return responseResultDao.createResponseResult(responseResultEntity);
    }

    @Override
    public void updateResponseResult(@NotNull @Valid ResponseResultUnit responseResultUnit) {
        ResponseResultEntity responseResultEntity = BeanMapper.map(responseResultUnit, ResponseResultEntity.class);

        ResponseResultUnit isExist = findResponseResult(responseResultUnit.getId());
        if(isExist==null){
            createResponseResult(responseResultUnit);
        }
        responseResultDao.updateResponseResult(responseResultEntity);
    }

    @Override
    public void deleteResponseResult(@NotNull String id) {
        responseResultDao.deleteResponseResult(id);
    }

    @Override
    public ResponseResultUnit findOne(String id) {
        ResponseResultEntity responseResultEntity = responseResultDao.findResponseResult(id);

        ResponseResultUnit responseResultUnit = BeanMapper.map(responseResultEntity, ResponseResultUnit.class);
        return responseResultUnit;
    }

    @Override
    public List<ResponseResultUnit> findList(List<String> idList) {
        List<ResponseResultEntity> responseResultEntityList =  responseResultDao.findResponseResultList(idList);

        List<ResponseResultUnit> responseResultUnitList =  BeanMapper.mapList(responseResultEntityList, ResponseResultUnit.class);
        return responseResultUnitList;
    }

    @Override
    public ResponseResultUnit findResponseResult(@NotNull String id) {
        ResponseResultUnit responseResultUnit = findOne(id);

        joinTemplate.joinQuery(responseResultUnit);
        return responseResultUnit;
    }

    @Override
    public List<ResponseResultUnit> findAllResponseResult() {
        List<ResponseResultEntity> responseResultEntityList =  responseResultDao.findAllResponseResult();

        List<ResponseResultUnit> responseResultUnitList =  BeanMapper.mapList(responseResultEntityList, ResponseResultUnit.class);

        joinTemplate.joinQuery(responseResultUnitList);
        return responseResultUnitList;
    }

    @Override
    public List<ResponseResultUnit> findResponseResultList(ResponseResultUnitQuery responseResultUnitQuery) {
        List<ResponseResultEntity> responseResultEntityList = responseResultDao.findResponseResultList(responseResultUnitQuery);

        List<ResponseResultUnit> responseResultUnitList = BeanMapper.mapList(responseResultEntityList, ResponseResultUnit.class);

        joinTemplate.joinQuery(responseResultUnitList);

        return responseResultUnitList;
    }

    @Override
    public Pagination<ResponseResultUnit> findResponseResultPage(ResponseResultUnitQuery responseResultUnitQuery) {

        Pagination<ResponseResultEntity>  pagination = responseResultDao.findResponseResultPage(responseResultUnitQuery);

        List<ResponseResultUnit> responseResultUnitList = BeanMapper.mapList(pagination.getDataList(), ResponseResultUnit.class);

        joinTemplate.joinQuery(responseResultUnitList);

        return PaginationBuilder.build(pagination, responseResultUnitList);
    }
}