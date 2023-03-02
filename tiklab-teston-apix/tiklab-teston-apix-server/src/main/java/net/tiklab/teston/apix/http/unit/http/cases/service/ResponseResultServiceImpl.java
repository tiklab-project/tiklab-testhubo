package net.tiklab.teston.apix.http.unit.http.cases.service;

import net.tiklab.core.page.PaginationBuilder;


import net.tiklab.core.page.Pagination;
import net.tiklab.beans.BeanMapper;
import net.tiklab.join.JoinTemplate;
import net.tiklab.teston.apix.http.unit.cases.model.ResponseResult;
import net.tiklab.teston.apix.http.unit.cases.model.ResponseResultQuery;
import net.tiklab.teston.apix.http.unit.cases.service.ResponseResultService;
import net.tiklab.teston.apix.http.unit.http.cases.dao.ResponseResultDao;
import net.tiklab.teston.apix.http.unit.http.cases.entity.ResponseResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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