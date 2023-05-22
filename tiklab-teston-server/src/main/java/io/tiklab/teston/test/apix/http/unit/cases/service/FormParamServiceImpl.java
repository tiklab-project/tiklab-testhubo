package io.tiklab.teston.test.apix.http.unit.cases.service;

import io.tiklab.teston.test.apix.http.unit.cases.dao.FormParamDao;
import io.tiklab.core.page.PaginationBuilder;


import io.tiklab.core.page.Pagination;
import io.tiklab.beans.BeanMapper;
import io.tiklab.join.JoinTemplate;
import io.tiklab.teston.test.apix.http.unit.cases.entity.FormParamsEntity;
import io.tiklab.teston.test.apix.http.unit.cases.model.FormParams;
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
    public String createFormParam(@NotNull @Valid FormParams formParams) {
        FormParamsEntity formParamsEntity = BeanMapper.map(formParams, FormParamsEntity.class);

        return formParamDao.createFormParam(formParamsEntity);
    }

    @Override
    public void updateFormParam(@NotNull @Valid FormParams formParams) {
        FormParamsEntity formParamsEntity = BeanMapper.map(formParams, FormParamsEntity.class);

        formParamDao.updateFormParam(formParamsEntity);
    }

    @Override
    public void deleteFormParam(@NotNull String id) {
        formParamDao.deleteFormParam(id);
    }

    @Override
    public FormParams findOne(String id) {
        FormParamsEntity formParamsEntity = formParamDao.findFormParam(id);

        FormParams formParams = BeanMapper.map(formParamsEntity, FormParams.class);
        return formParams;
    }

    @Override
    public List<FormParams> findList(List<String> idList) {
        List<FormParamsEntity> formParamsEntityList =  formParamDao.findFormParamList(idList);

        List<FormParams> formParamsList =  BeanMapper.mapList(formParamsEntityList, FormParams.class);
        return formParamsList;
    }

    @Override
    public FormParams findFormParam(@NotNull String id) {
        FormParams formParams = findOne(id);

        joinTemplate.joinQuery(formParams);
        return formParams;
    }

    @Override
    public List<FormParams> findAllFormParam() {
        List<FormParamsEntity> formParamsEntityList =  formParamDao.findAllFormParam();

        List<FormParams> formParamsList =  BeanMapper.mapList(formParamsEntityList, FormParams.class);

        joinTemplate.joinQuery(formParamsList);
        return formParamsList;
    }

    @Override
    public List<FormParams> findFormParamList(FormParamQuery formParamQuery) {
        List<FormParamsEntity> formParamsEntityList = formParamDao.findFormParamList(formParamQuery);

        List<FormParams> formParamsList = BeanMapper.mapList(formParamsEntityList, FormParams.class);

        joinTemplate.joinQuery(formParamsList);

        return formParamsList;
    }

    @Override
    public Pagination<FormParams> findFormParamPage(FormParamQuery formParamQuery) {

        Pagination<FormParamsEntity>  pagination = formParamDao.findFormParamPage(formParamQuery);

        List<FormParams> formParamsList = BeanMapper.mapList(pagination.getDataList(), FormParams.class);

        joinTemplate.joinQuery(formParamsList);

        return PaginationBuilder.build(pagination, formParamsList);
    }
}