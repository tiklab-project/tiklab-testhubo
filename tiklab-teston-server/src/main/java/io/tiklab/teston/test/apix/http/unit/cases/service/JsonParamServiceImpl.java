package io.tiklab.teston.test.apix.http.unit.cases.service;

import io.tiklab.teston.test.apix.http.unit.cases.dao.JsonParamDao;
import io.tiklab.teston.test.apix.http.unit.cases.entity.JsonParamEntity;
import io.tiklab.core.page.PaginationBuilder;


import io.tiklab.core.page.Pagination;
import io.tiklab.beans.BeanMapper;
import io.tiklab.join.JoinTemplate;
import io.tiklab.teston.test.apix.http.unit.cases.model.JsonParam;
import io.tiklab.teston.test.apix.http.unit.cases.model.JsonParamQuery;
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
    public String createJsonParam(@NotNull @Valid JsonParam jsonParam) {
        JsonParamEntity jsonParamEntity = BeanMapper.map(jsonParam, JsonParamEntity.class);

        return jsonParamDao.createJsonParam(jsonParamEntity);
    }

    @Override
    public void updateJsonParam(@NotNull @Valid JsonParam jsonParam) {
        JsonParamEntity jsonParamEntity = BeanMapper.map(jsonParam, JsonParamEntity.class);

        jsonParamDao.updateJsonParam(jsonParamEntity);
    }

    @Override
    public void deleteJsonParam(@NotNull String id) {
        jsonParamDao.deleteJsonParam(id);
    }

    @Override
    public JsonParam findOne(String id) {
        JsonParamEntity jsonParamEntity = jsonParamDao.findJsonParam(id);

        JsonParam jsonParam = BeanMapper.map(jsonParamEntity, JsonParam.class);
        return jsonParam;
    }

    @Override
    public List<JsonParam> findList(List<String> idList) {
        List<JsonParamEntity> jsonParamEntityList =  jsonParamDao.findJsonParamList(idList);

        List<JsonParam> jsonParamList =  BeanMapper.mapList(jsonParamEntityList,JsonParam.class);
        return jsonParamList;
    }

    @Override
    public JsonParam findJsonParam(@NotNull String id) {
        JsonParam jsonParam = findOne(id);

        joinTemplate.joinQuery(jsonParam);
        return jsonParam;
    }

    @Override
    public List<JsonParam> findAllJsonParam() {
        List<JsonParamEntity> jsonParamEntityList =  jsonParamDao.findAllJsonParam();

        List<JsonParam> jsonParamList =  BeanMapper.mapList(jsonParamEntityList,JsonParam.class);

        joinTemplate.joinQuery(jsonParamList);
        return jsonParamList;
    }

    @Override
    public List<JsonParam> findJsonParamList(JsonParamQuery jsonParamQuery) {
        List<JsonParamEntity> jsonParamEntityList = jsonParamDao.findJsonParamList(jsonParamQuery);

        List<JsonParam> jsonParamList = BeanMapper.mapList(jsonParamEntityList,JsonParam.class);

        joinTemplate.joinQuery(jsonParamList);

        return jsonParamList;
    }

    @Override
    public Pagination<JsonParam> findJsonParamPage(JsonParamQuery jsonParamQuery) {

        Pagination<JsonParamEntity>  pagination = jsonParamDao.findJsonParamPage(jsonParamQuery);

        List<JsonParam> jsonParamList = BeanMapper.mapList(pagination.getDataList(),JsonParam.class);

        joinTemplate.joinQuery(jsonParamList);

        return PaginationBuilder.build(pagination,jsonParamList);
    }



}