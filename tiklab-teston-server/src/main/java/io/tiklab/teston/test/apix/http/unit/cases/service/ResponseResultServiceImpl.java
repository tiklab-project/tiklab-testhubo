package io.tiklab.teston.test.apix.http.unit.cases.service;

import io.tiklab.teston.test.apix.http.unit.cases.dao.ResponseResultDao;
import io.tiklab.core.page.PaginationBuilder;


import io.tiklab.core.page.Pagination;
import io.tiklab.beans.BeanMapper;
import io.tiklab.join.JoinTemplate;
import io.tiklab.teston.test.apix.http.unit.cases.entity.ResponseResultEntity;
import io.tiklab.teston.test.apix.http.unit.cases.model.ResponseResult;
import io.tiklab.teston.test.apix.http.unit.cases.model.ResponseResultQuery;
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
    public String createResponseResult(@NotNull @Valid ResponseResult responseResult) {
        ResponseResultEntity responseResultEntity = BeanMapper.map(responseResult, ResponseResultEntity.class);
        responseResultEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));
        return responseResultDao.createResponseResult(responseResultEntity);
    }

    @Override
    public void updateResponseResult(@NotNull @Valid ResponseResult responseResult) {
        ResponseResultEntity responseResultEntity = BeanMapper.map(responseResult, ResponseResultEntity.class);

        responseResultDao.updateResponseResult(responseResultEntity);
    }

    @Override
    public void deleteResponseResult(@NotNull String id) {
        responseResultDao.deleteResponseResult(id);
    }

    @Override
    public ResponseResult findOne(String id) {
        ResponseResultEntity responseResultEntity = responseResultDao.findResponseResult(id);

        ResponseResult responseResult = BeanMapper.map(responseResultEntity, ResponseResult.class);
        return responseResult;
    }

    @Override
    public List<ResponseResult> findList(List<String> idList) {
        List<ResponseResultEntity> responseResultEntityList =  responseResultDao.findResponseResultList(idList);

        List<ResponseResult> responseResultList =  BeanMapper.mapList(responseResultEntityList,ResponseResult.class);
        return responseResultList;
    }

    @Override
    public ResponseResult findResponseResult(@NotNull String id) {
        ResponseResult responseResult = findOne(id);

        joinTemplate.joinQuery(responseResult);
        return responseResult;
    }

    @Override
    public List<ResponseResult> findAllResponseResult() {
        List<ResponseResultEntity> responseResultEntityList =  responseResultDao.findAllResponseResult();

        List<ResponseResult> responseResultList =  BeanMapper.mapList(responseResultEntityList,ResponseResult.class);

        joinTemplate.joinQuery(responseResultList);
        return responseResultList;
    }

    @Override
    public List<ResponseResult> findResponseResultList(ResponseResultQuery responseResultQuery) {
        List<ResponseResultEntity> responseResultEntityList = responseResultDao.findResponseResultList(responseResultQuery);

        List<ResponseResult> responseResultList = BeanMapper.mapList(responseResultEntityList,ResponseResult.class);

        joinTemplate.joinQuery(responseResultList);

        return responseResultList;
    }

    @Override
    public Pagination<ResponseResult> findResponseResultPage(ResponseResultQuery responseResultQuery) {

        Pagination<ResponseResultEntity>  pagination = responseResultDao.findResponseResultPage(responseResultQuery);

        List<ResponseResult> responseResultList = BeanMapper.mapList(pagination.getDataList(),ResponseResult.class);

        joinTemplate.joinQuery(responseResultList);

        return PaginationBuilder.build(pagination,responseResultList);
    }
}