package io.thoughtware.testhubo.test.apix.http.unit.cases.service;



import io.thoughtware.testhubo.test.apix.http.unit.cases.dao.FormUrlencodedDao;
import io.thoughtware.testhubo.test.apix.http.unit.cases.model.FormUrlEncodedUnit;
import io.thoughtware.testhubo.test.apix.http.unit.cases.model.FormUrlencodedUnitQuery;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.core.page.PaginationBuilder;
import io.thoughtware.toolkit.beans.BeanMapper;
import io.thoughtware.toolkit.join.JoinTemplate;
import io.thoughtware.testhubo.test.apix.http.unit.cases.entity.FormUrlEncodedEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* form-urlencoded 服务
*/
@Service
public class FormUrlencodedServiceImpl implements FormUrlencodedService {

    @Autowired
    FormUrlencodedDao formUrlencodedDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createFormUrlencoded(@NotNull @Valid FormUrlEncodedUnit formUrlencodedUnit) {
        FormUrlEncodedEntity formUrlencodedEntity = BeanMapper.map(formUrlencodedUnit, FormUrlEncodedEntity.class);

        return formUrlencodedDao.createFormUrlencoded(formUrlencodedEntity);
    }

    @Override
    public void updateFormUrlencoded(@NotNull @Valid FormUrlEncodedUnit formUrlencodedUnit) {
        FormUrlEncodedEntity formUrlencodedEntity = BeanMapper.map(formUrlencodedUnit, FormUrlEncodedEntity.class);

        formUrlencodedDao.updateFormUrlencoded(formUrlencodedEntity);
    }

    @Override
    public void deleteFormUrlencoded(@NotNull String id) {
        formUrlencodedDao.deleteFormUrlencoded(id);
    }

    @Override
    public void deleteAllFormUrlencoded( String caseId) {
        FormUrlencodedUnitQuery formUrlencodedUnitQuery = new FormUrlencodedUnitQuery();
        formUrlencodedUnitQuery.setApiUnitId(caseId);
        List<FormUrlEncodedUnit> formUrlencodedList = findFormUrlencodedList(formUrlencodedUnitQuery);
        for(FormUrlEncodedUnit formUrlencodedUnit : formUrlencodedList){
            formUrlencodedDao.deleteFormUrlencoded(formUrlencodedUnit.getId());
        }
    }

    @Override
    public FormUrlEncodedUnit findOne(String id) {
        FormUrlEncodedEntity formUrlencodedEntity = formUrlencodedDao.findFormUrlencoded(id);

        FormUrlEncodedUnit formUrlencodedUnit = BeanMapper.map(formUrlencodedEntity, FormUrlEncodedUnit.class);
        return formUrlencodedUnit;
    }

    @Override
    public List<FormUrlEncodedUnit> findList(List<String> idList) {
        List<FormUrlEncodedEntity> formUrlEncodedEntityList =  formUrlencodedDao.findFormUrlencodedList(idList);

        List<FormUrlEncodedUnit> formUrlEncodedUnitList =  BeanMapper.mapList(formUrlEncodedEntityList, FormUrlEncodedUnit.class);
        return formUrlEncodedUnitList;
    }

    @Override
    public FormUrlEncodedUnit findFormUrlencoded(@NotNull String id) {
        FormUrlEncodedUnit formUrlencodedUnit = findOne(id);

        joinTemplate.joinQuery(formUrlencodedUnit);

        return formUrlencodedUnit;
    }

    @Override
    public List<FormUrlEncodedUnit> findAllFormUrlencoded() {
        List<FormUrlEncodedEntity> formUrlEncodedEntityList =  formUrlencodedDao.findAllFormUrlencoded();

        List<FormUrlEncodedUnit> formUrlEncodedUnitList =  BeanMapper.mapList(formUrlEncodedEntityList, FormUrlEncodedUnit.class);

        joinTemplate.joinQuery(formUrlEncodedUnitList);

        return formUrlEncodedUnitList;
    }

    @Override
    public List<FormUrlEncodedUnit> findFormUrlencodedList(FormUrlencodedUnitQuery formUrlencodedUnitQuery) {
        List<FormUrlEncodedEntity> formUrlEncodedEntityList = formUrlencodedDao.findFormUrlencodedList(formUrlencodedUnitQuery);

        List<FormUrlEncodedUnit> formUrlEncodedUnitList = BeanMapper.mapList(formUrlEncodedEntityList, FormUrlEncodedUnit.class);

        joinTemplate.joinQuery(formUrlEncodedUnitList);

        return formUrlEncodedUnitList;
    }

    @Override
    public Pagination<FormUrlEncodedUnit> findFormUrlencodedPage(FormUrlencodedUnitQuery formUrlencodedUnitQuery) {
        Pagination<FormUrlEncodedEntity>  pagination = formUrlencodedDao.findFormUrlencodedPage(formUrlencodedUnitQuery);

        List<FormUrlEncodedUnit> formUrlEncodedUnitList = BeanMapper.mapList(pagination.getDataList(), FormUrlEncodedUnit.class);

        joinTemplate.joinQuery(formUrlEncodedUnitList);

        return PaginationBuilder.build(pagination, formUrlEncodedUnitList);
    }
}