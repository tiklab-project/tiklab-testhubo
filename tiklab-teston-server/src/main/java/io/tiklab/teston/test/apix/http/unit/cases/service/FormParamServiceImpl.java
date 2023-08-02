package io.tiklab.teston.test.apix.http.unit.cases.service;

import io.tiklab.teston.test.apix.http.unit.cases.dao.FormParamDao;
import io.tiklab.core.page.PaginationBuilder;


import io.tiklab.core.page.Pagination;
import io.tiklab.beans.BeanMapper;
import io.tiklab.join.JoinTemplate;
import io.tiklab.teston.test.apix.http.unit.cases.entity.FormParamsEntity;
import io.tiklab.teston.test.apix.http.unit.cases.model.FormParam;
import io.tiklab.teston.test.apix.http.unit.cases.model.FormParamQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* form-data 服务
*/
@Service
public class FormParamServiceImpl implements FormParamService {

    @Autowired
    FormParamDao formParamDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createFormParam(@NotNull @Valid FormParam formParam) {
        FormParamsEntity formParamsEntity = BeanMapper.map(formParam, FormParamsEntity.class);

        return formParamDao.createFormParam(formParamsEntity);
    }

    @Override
    public void updateFormParam(@NotNull @Valid FormParam formParam) {
        FormParamsEntity formParamsEntity = BeanMapper.map(formParam, FormParamsEntity.class);

        formParamDao.updateFormParam(formParamsEntity);
    }

    @Override
    public void deleteFormParam(@NotNull String id) {
        formParamDao.deleteFormParam(id);
    }

    @Override
    public FormParam findOne(String id) {
        FormParamsEntity formParamsEntity = formParamDao.findFormParam(id);

        FormParam formParam = BeanMapper.map(formParamsEntity, FormParam.class);
        return formParam;
    }

    @Override
    public List<FormParam> findList(List<String> idList) {
        List<FormParamsEntity> formParamsEntityList =  formParamDao.findFormParamList(idList);

        List<FormParam> formParamList =  BeanMapper.mapList(formParamsEntityList, FormParam.class);
        return formParamList;
    }

    @Override
    public FormParam findFormParam(@NotNull String id) {
        FormParam formParam = findOne(id);

        joinTemplate.joinQuery(formParam);
        return formParam;
    }

    @Override
    public List<FormParam> findAllFormParam() {
        List<FormParamsEntity> formParamsEntityList =  formParamDao.findAllFormParam();

        List<FormParam> formParamList =  BeanMapper.mapList(formParamsEntityList, FormParam.class);

        joinTemplate.joinQuery(formParamList);
        return formParamList;
    }

    @Override
    public List<FormParam> findFormParamList(FormParamQuery formParamQuery) {
        List<FormParamsEntity> formParamsEntityList = formParamDao.findFormParamList(formParamQuery);

        List<FormParam> formParamList = BeanMapper.mapList(formParamsEntityList, FormParam.class);

        joinTemplate.joinQuery(formParamList);

        return formParamList;
    }

    @Override
    public Pagination<FormParam> findFormParamPage(FormParamQuery formParamQuery) {

        Pagination<FormParamsEntity>  pagination = formParamDao.findFormParamPage(formParamQuery);

        List<FormParam> formParamList = BeanMapper.mapList(pagination.getDataList(), FormParam.class);

        joinTemplate.joinQuery(formParamList);

        return PaginationBuilder.build(pagination, formParamList);
    }
}