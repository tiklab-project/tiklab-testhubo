package net.tiklab.teston.apix.http.unit.http.cases.service;

import net.tiklab.core.page.PaginationBuilder;


import net.tiklab.core.page.Pagination;
import net.tiklab.beans.BeanMapper;
import net.tiklab.join.JoinTemplate;
import net.tiklab.teston.apix.http.unit.cases.model.RawResponse;
import net.tiklab.teston.apix.http.unit.cases.model.RawResponseQuery;
import net.tiklab.teston.apix.http.unit.cases.service.RawResponseService;
import net.tiklab.teston.apix.http.unit.http.cases.dao.RawResponseDao;
import net.tiklab.teston.apix.http.unit.http.cases.entity.RawResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 响应中raw 服务
*/
@Service
public class RawResponseServiceImpl implements RawResponseService {

    @Autowired
    RawResponseDao rawResponseDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createRawResponse(@NotNull @Valid RawResponse rawResponse) {
        RawResponseEntity rawResponseEntity = BeanMapper.map(rawResponse, RawResponseEntity.class);

        return rawResponseDao.createRawResponse(rawResponseEntity);
    }

    @Override
    public void updateRawResponse(@NotNull @Valid RawResponse rawResponse) {
        RawResponseEntity rawResponseEntity = BeanMapper.map(rawResponse, RawResponseEntity.class);

        rawResponseDao.updateRawResponse(rawResponseEntity);
    }

    @Override
    public void deleteRawResponse(@NotNull String id) {
        rawResponseDao.deleteRawResponse(id);
    }

    @Override
    public RawResponse findOne(String id) {
        RawResponseEntity rawResponseEntity = rawResponseDao.findRawResponse(id);

        RawResponse rawResponse = BeanMapper.map(rawResponseEntity, RawResponse.class);
        return rawResponse;
    }

    @Override
    public List<RawResponse> findList(List<String> idList) {
        List<RawResponseEntity> rawResponseEntityList =  rawResponseDao.findRawResponseList(idList);

        List<RawResponse> rawResponseList =  BeanMapper.mapList(rawResponseEntityList,RawResponse.class);
        return rawResponseList;
    }

    @Override
    public RawResponse findRawResponse(@NotNull String id) {
        RawResponse rawResponse = findOne(id);

        joinTemplate.joinQuery(rawResponse);
        return rawResponse;
    }

    @Override
    public List<RawResponse> findAllRawResponse() {
        List<RawResponseEntity> rawResponseEntityList =  rawResponseDao.findAllRawResponse();

        List<RawResponse> rawResponseList =  BeanMapper.mapList(rawResponseEntityList,RawResponse.class);

        joinTemplate.joinQuery(rawResponseList);
        return rawResponseList;
    }

    @Override
    public List<RawResponse> findRawResponseList(RawResponseQuery rawResponseQuery) {
        List<RawResponseEntity> rawResponseEntityList = rawResponseDao.findRawResponseList(rawResponseQuery);

        List<RawResponse> rawResponseList = BeanMapper.mapList(rawResponseEntityList,RawResponse.class);

        joinTemplate.joinQuery(rawResponseList);

        return rawResponseList;
    }

    @Override
    public Pagination<RawResponse> findRawResponsePage(RawResponseQuery rawResponseQuery) {

        Pagination<RawResponseEntity>  pagination = rawResponseDao.findRawResponsePage(rawResponseQuery);

        List<RawResponse> rawResponseList = BeanMapper.mapList(pagination.getDataList(),RawResponse.class);

        joinTemplate.joinQuery(rawResponseList);

        return PaginationBuilder.build(pagination,rawResponseList);
    }
}