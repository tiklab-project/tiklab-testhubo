package io.tiklab.teston.test.apix.http.unit.cases.service;

import io.tiklab.teston.test.apix.http.unit.cases.dao.RequestBodyDao;
import io.tiklab.teston.test.apix.http.unit.cases.entity.RequestBodyEntity;
import io.tiklab.core.page.PaginationBuilder;


import io.tiklab.core.page.Pagination;
import io.tiklab.beans.BeanMapper;
import io.tiklab.join.JoinTemplate;
import io.tiklab.teston.test.apix.http.unit.cases.model.*;
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
    JsonParamService jsonParamService;

    @Autowired
    RawParamService rawParamService;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createRequestBody(@NotNull @Valid RequestBodyUnit requestBodyUnit) {
        RequestBodyEntity requestBodyEntity = BeanMapper.map(requestBodyUnit, RequestBodyEntity.class);

        return requestBodyDao.createRequestBody(requestBodyEntity);
    }

    @Override
    public void updateRequestBody(@NotNull @Valid RequestBodyUnit requestBodyUnit) {
        RequestBodyEntity requestBodyEntity = BeanMapper.map(requestBodyUnit, RequestBodyEntity.class);

        if("json".equals(requestBodyUnit.getBodyType())){
            //切换请求体，如果是json，没有找到，就会自动生成一个。
            String apiUnitId = requestBodyUnit.getApiUnitId();
            JsonParamUnit isExsit = jsonParamService.findJsonParam(apiUnitId);
            if(isExsit==null){
                JsonParamUnit jsonParamUnit = new JsonParamUnit();
                jsonParamUnit.setId(apiUnitId);
                jsonParamUnit.setApiUnitId(apiUnitId);
                jsonParamUnit.setSchemaText("{\"type\": \"object\",\"properties\": {}}");
                jsonParamService.createJsonParam(jsonParamUnit);
            }
        }

        if("raw".equals(requestBodyUnit.getBodyType())){
            String apiUnitId = requestBodyUnit.getApiUnitId();
            RawParamUnit rawParamUnitIsExist = rawParamService.findRawParam(apiUnitId);
            if(rawParamUnitIsExist ==null){
                RawParamUnit rawParamUnit = new RawParamUnit();
                rawParamUnit.setApiUnit(new ApiUnitCase().setId(apiUnitId));
                rawParamUnit.setId(apiUnitId);
                rawParamUnit.setType("application/json");
                rawParamUnit.setRaw("");
                rawParamService.createRawParam(rawParamUnit);
            }
        }


        requestBodyDao.updateRequestBody(requestBodyEntity);
    }

    @Override
    public void deleteRequestBody(@NotNull String id) {
        requestBodyDao.deleteRequestBody(id);
    }

    @Override
    public RequestBodyUnit findOne(String id) {
        RequestBodyEntity requestBodyEntity = requestBodyDao.findRequestBody(id);

        RequestBodyUnit requestBodyUnit = BeanMapper.map(requestBodyEntity, RequestBodyUnit.class);
        return requestBodyUnit;
    }

    @Override
    public List<RequestBodyUnit> findList(List<String> idList) {
        List<RequestBodyEntity> requestBodyEntityList =  requestBodyDao.findRequestBodyList(idList);

        List<RequestBodyUnit> requestBodyUnitList =  BeanMapper.mapList(requestBodyEntityList, RequestBodyUnit.class);
        return requestBodyUnitList;
    }

    @Override
    public RequestBodyUnit findRequestBody(@NotNull String id) {
        RequestBodyUnit requestBodyUnit = findOne(id);

        joinTemplate.joinQuery(requestBodyUnit);
        return requestBodyUnit;
    }

    @Override
    public List<RequestBodyUnit> findAllRequestBody() {
        List<RequestBodyEntity> requestBodyEntityList =  requestBodyDao.findAllRequestBody();

        List<RequestBodyUnit> requestBodyUnitList =  BeanMapper.mapList(requestBodyEntityList, RequestBodyUnit.class);

        joinTemplate.joinQuery(requestBodyUnitList);
        return requestBodyUnitList;
    }

    @Override
    public List<RequestBodyUnit> findRequestBodyList(RequestBodyUnitQuery requestBodyUnitQuery) {
        List<RequestBodyEntity> requestBodyEntityList = requestBodyDao.findRequestBodyList(requestBodyUnitQuery);

        List<RequestBodyUnit> requestBodyUnitList = BeanMapper.mapList(requestBodyEntityList, RequestBodyUnit.class);

        joinTemplate.joinQuery(requestBodyUnitList);

        return requestBodyUnitList;
    }

    @Override
    public Pagination<RequestBodyUnit> findRequestBodyPage(RequestBodyUnitQuery requestBodyUnitQuery) {

        Pagination<RequestBodyEntity>  pagination = requestBodyDao.findRequestBodyPage(requestBodyUnitQuery);

        List<RequestBodyUnit> requestBodyUnitList = BeanMapper.mapList(pagination.getDataList(), RequestBodyUnit.class);

        joinTemplate.joinQuery(requestBodyUnitList);

        return PaginationBuilder.build(pagination, requestBodyUnitList);
    }


}