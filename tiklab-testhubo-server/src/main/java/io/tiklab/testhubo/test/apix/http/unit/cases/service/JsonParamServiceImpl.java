package io.tiklab.testhubo.test.apix.http.unit.cases.service;

import io.tiklab.testhubo.test.apix.http.unit.cases.dao.JsonParamDao;
import io.tiklab.testhubo.test.apix.http.unit.cases.model.JsonParamUnit;
import io.tiklab.testhubo.test.apix.http.unit.cases.model.JsonParamUnitQuery;
import io.tiklab.testhubo.test.apix.http.unit.cases.entity.JsonParamEntity;
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
* 请求中json  服务
*/
@Service
public class JsonParamServiceImpl implements JsonParamService {

    @Autowired
    JsonParamDao jsonParamDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createJsonParam(@NotNull @Valid JsonParamUnit jsonParamUnit) {
        JsonParamEntity jsonParamEntity = BeanMapper.map(jsonParamUnit, JsonParamEntity.class);

        return jsonParamDao.createJsonParam(jsonParamEntity);
    }

    @Override
    public void updateJsonParam(@NotNull @Valid JsonParamUnit jsonParamUnit) {
        JsonParamEntity jsonParamEntity = BeanMapper.map(jsonParamUnit, JsonParamEntity.class);

        jsonParamDao.updateJsonParam(jsonParamEntity);
    }

    @Override
    public void deleteJsonParam(@NotNull String id) {
        jsonParamDao.deleteJsonParam(id);
    }

    @Override
    public void deleteAllJsonParam( String caseId) {
        JsonParamUnitQuery jsonParamUnitQuery = new JsonParamUnitQuery();
        jsonParamUnitQuery.setApiUnitId(caseId);
        List<JsonParamUnit> jsonParamList = findJsonParamList(jsonParamUnitQuery);
        for (JsonParamUnit jsonParamUnit : jsonParamList) {
            deleteJsonParam(jsonParamUnit.getId());
        }
    }


    @Override
    public JsonParamUnit findOne(String id) {
        JsonParamEntity jsonParamEntity = jsonParamDao.findJsonParam(id);

        JsonParamUnit jsonParamUnit = BeanMapper.map(jsonParamEntity, JsonParamUnit.class);
        return jsonParamUnit;
    }

    @Override
    public List<JsonParamUnit> findList(List<String> idList) {
        List<JsonParamEntity> jsonParamEntityList =  jsonParamDao.findJsonParamList(idList);

        List<JsonParamUnit> jsonParamUnitList =  BeanMapper.mapList(jsonParamEntityList, JsonParamUnit.class);
        return jsonParamUnitList;
    }

    @Override
    public JsonParamUnit findJsonParam(@NotNull String id) {
        JsonParamUnit jsonParamUnit = findOne(id);

        joinTemplate.joinQuery(jsonParamUnit);
        return jsonParamUnit;
    }

    @Override
    public List<JsonParamUnit> findAllJsonParam() {
        List<JsonParamEntity> jsonParamEntityList =  jsonParamDao.findAllJsonParam();

        List<JsonParamUnit> jsonParamUnitList =  BeanMapper.mapList(jsonParamEntityList, JsonParamUnit.class);

        joinTemplate.joinQuery(jsonParamUnitList);
        return jsonParamUnitList;
    }

    @Override
    public List<JsonParamUnit> findJsonParamList(JsonParamUnitQuery jsonParamUnitQuery) {
        List<JsonParamEntity> jsonParamEntityList = jsonParamDao.findJsonParamList(jsonParamUnitQuery);

        List<JsonParamUnit> jsonParamUnitList = BeanMapper.mapList(jsonParamEntityList, JsonParamUnit.class);

        joinTemplate.joinQuery(jsonParamUnitList);

        return jsonParamUnitList;
    }

    @Override
    public Pagination<JsonParamUnit> findJsonParamPage(JsonParamUnitQuery jsonParamUnitQuery) {

        Pagination<JsonParamEntity>  pagination = jsonParamDao.findJsonParamPage(jsonParamUnitQuery);

        List<JsonParamUnit> jsonParamUnitList = BeanMapper.mapList(pagination.getDataList(), JsonParamUnit.class);

        joinTemplate.joinQuery(jsonParamUnitList);

        return PaginationBuilder.build(pagination, jsonParamUnitList);
    }



}