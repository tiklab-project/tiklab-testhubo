package io.tiklab.testhubo.test.apix.http.unit.cases.service;

import io.tiklab.testhubo.test.apix.http.unit.cases.dao.RawResponseDao;
import io.tiklab.testhubo.test.apix.http.unit.cases.entity.RawResponseEntity;
import io.tiklab.testhubo.test.apix.http.unit.cases.model.RawResponseUnit;
import io.tiklab.testhubo.test.apix.http.unit.cases.model.RawResponseUnitQuery;
import io.tiklab.core.page.PaginationBuilder;


import io.tiklab.core.page.Pagination;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.toolkit.join.JoinTemplate;
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
    public String createRawResponse(@NotNull @Valid RawResponseUnit rawResponseUnit) {
        RawResponseEntity rawResponseEntity = BeanMapper.map(rawResponseUnit, RawResponseEntity.class);

        return rawResponseDao.createRawResponse(rawResponseEntity);
    }

    @Override
    public void updateRawResponse(@NotNull @Valid RawResponseUnit rawResponseUnit) {
        RawResponseEntity rawResponseEntity = BeanMapper.map(rawResponseUnit, RawResponseEntity.class);

        rawResponseDao.updateRawResponse(rawResponseEntity);
    }

    @Override
    public void deleteRawResponse(@NotNull String id) {
        rawResponseDao.deleteRawResponse(id);
    }

    @Override
    public RawResponseUnit findOne(String id) {
        RawResponseEntity rawResponseEntity = rawResponseDao.findRawResponse(id);

        RawResponseUnit rawResponseUnit = BeanMapper.map(rawResponseEntity, RawResponseUnit.class);
        return rawResponseUnit;
    }

    @Override
    public List<RawResponseUnit> findList(List<String> idList) {
        List<RawResponseEntity> rawResponseEntityList =  rawResponseDao.findRawResponseList(idList);

        List<RawResponseUnit> rawResponseUnitList =  BeanMapper.mapList(rawResponseEntityList, RawResponseUnit.class);
        return rawResponseUnitList;
    }

    @Override
    public RawResponseUnit findRawResponse(@NotNull String id) {
        RawResponseUnit rawResponseUnit = findOne(id);

        joinTemplate.joinQuery(rawResponseUnit);
        return rawResponseUnit;
    }

    @Override
    public List<RawResponseUnit> findAllRawResponse() {
        List<RawResponseEntity> rawResponseEntityList =  rawResponseDao.findAllRawResponse();

        List<RawResponseUnit> rawResponseUnitList =  BeanMapper.mapList(rawResponseEntityList, RawResponseUnit.class);

        joinTemplate.joinQuery(rawResponseUnitList);
        return rawResponseUnitList;
    }

    @Override
    public List<RawResponseUnit> findRawResponseList(RawResponseUnitQuery rawResponseUnitQuery) {
        List<RawResponseEntity> rawResponseEntityList = rawResponseDao.findRawResponseList(rawResponseUnitQuery);

        List<RawResponseUnit> rawResponseUnitList = BeanMapper.mapList(rawResponseEntityList, RawResponseUnit.class);

        joinTemplate.joinQuery(rawResponseUnitList);

        return rawResponseUnitList;
    }

    @Override
    public Pagination<RawResponseUnit> findRawResponsePage(RawResponseUnitQuery rawResponseUnitQuery) {

        Pagination<RawResponseEntity>  pagination = rawResponseDao.findRawResponsePage(rawResponseUnitQuery);

        List<RawResponseUnit> rawResponseUnitList = BeanMapper.mapList(pagination.getDataList(), RawResponseUnit.class);

        joinTemplate.joinQuery(rawResponseUnitList);

        return PaginationBuilder.build(pagination, rawResponseUnitList);
    }
}