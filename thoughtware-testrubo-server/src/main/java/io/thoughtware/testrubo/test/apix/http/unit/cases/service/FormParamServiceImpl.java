package io.thoughtware.testrubo.test.apix.http.unit.cases.service;

import io.thoughtware.testrubo.test.apix.http.unit.cases.dao.FormParamDao;
import io.thoughtware.testrubo.test.apix.http.unit.cases.model.FormParamUnit;
import io.thoughtware.testrubo.test.apix.http.unit.cases.model.FormParamUnitQuery;
import io.thoughtware.core.page.PaginationBuilder;


import io.thoughtware.core.page.Pagination;
import io.thoughtware.toolkit.beans.BeanMapper;
import io.thoughtware.toolkit.join.JoinTemplate;
import io.thoughtware.testrubo.test.apix.http.unit.cases.entity.FormParamsEntity;
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
    public String createFormParam(@NotNull @Valid FormParamUnit formParamUnit) {
        FormParamsEntity formParamsEntity = BeanMapper.map(formParamUnit, FormParamsEntity.class);

        return formParamDao.createFormParam(formParamsEntity);
    }

    @Override
    public void updateFormParam(@NotNull @Valid FormParamUnit formParamUnit) {
        FormParamsEntity formParamsEntity = BeanMapper.map(formParamUnit, FormParamsEntity.class);

        formParamDao.updateFormParam(formParamsEntity);
    }

    @Override
    public void deleteFormParam(@NotNull String id) {
        formParamDao.deleteFormParam(id);
    }

    @Override
    public void deleteAllFormParam( String caseId) {
        FormParamUnitQuery formParamUnitQuery = new FormParamUnitQuery();
        formParamUnitQuery.setApiUnitId(caseId);
        List<FormParamUnit> formParamList = findFormParamList(formParamUnitQuery);
        for (FormParamUnit formParamUnit : formParamList) {
            formParamDao.deleteFormParam(formParamUnit.getId());
        }

    }

    @Override
    public FormParamUnit findOne(String id) {
        FormParamsEntity formParamsEntity = formParamDao.findFormParam(id);

        FormParamUnit formParamUnit = BeanMapper.map(formParamsEntity, FormParamUnit.class);
        return formParamUnit;
    }

    @Override
    public List<FormParamUnit> findList(List<String> idList) {
        List<FormParamsEntity> formParamsEntityList =  formParamDao.findFormParamList(idList);

        List<FormParamUnit> formParamUnitList =  BeanMapper.mapList(formParamsEntityList, FormParamUnit.class);
        return formParamUnitList;
    }

    @Override
    public FormParamUnit findFormParam(@NotNull String id) {
        FormParamUnit formParamUnit = findOne(id);

        joinTemplate.joinQuery(formParamUnit);
        return formParamUnit;
    }

    @Override
    public List<FormParamUnit> findAllFormParam() {
        List<FormParamsEntity> formParamsEntityList =  formParamDao.findAllFormParam();

        List<FormParamUnit> formParamUnitList =  BeanMapper.mapList(formParamsEntityList, FormParamUnit.class);

        joinTemplate.joinQuery(formParamUnitList);
        return formParamUnitList;
    }

    @Override
    public List<FormParamUnit> findFormParamList(FormParamUnitQuery formParamUnitQuery) {
        List<FormParamsEntity> formParamsEntityList = formParamDao.findFormParamList(formParamUnitQuery);

        List<FormParamUnit> formParamUnitList = BeanMapper.mapList(formParamsEntityList, FormParamUnit.class);

        joinTemplate.joinQuery(formParamUnitList);

        return formParamUnitList;
    }

    @Override
    public Pagination<FormParamUnit> findFormParamPage(FormParamUnitQuery formParamUnitQuery) {

        Pagination<FormParamsEntity>  pagination = formParamDao.findFormParamPage(formParamUnitQuery);

        List<FormParamUnit> formParamUnitList = BeanMapper.mapList(pagination.getDataList(), FormParamUnit.class);

        joinTemplate.joinQuery(formParamUnitList);

        return PaginationBuilder.build(pagination, formParamUnitList);
    }
}