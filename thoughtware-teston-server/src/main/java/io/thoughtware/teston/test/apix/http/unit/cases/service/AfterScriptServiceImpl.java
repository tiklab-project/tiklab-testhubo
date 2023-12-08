package io.thoughtware.teston.test.apix.http.unit.cases.service;

import io.thoughtware.teston.test.apix.http.unit.cases.dao.AfterScriptDao;
import io.thoughtware.teston.test.apix.http.unit.cases.model.AfterScriptUnit;
import io.thoughtware.teston.test.apix.http.unit.cases.model.AfterScriptUnitQuery;
import io.thoughtware.core.page.PaginationBuilder;


import io.thoughtware.core.page.Pagination;
import io.thoughtware.beans.BeanMapper;
import io.thoughtware.join.JoinTemplate;
import io.thoughtware.teston.test.apix.http.unit.cases.entity.AfterScriptEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 后置脚本 服务
*/
@Service
public class AfterScriptServiceImpl implements AfterScriptService {

    @Autowired
    AfterScriptDao afterScriptDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createAfterScript(@NotNull @Valid AfterScriptUnit afterScriptUnit) {
        AfterScriptEntity afterScriptEntity = BeanMapper.map(afterScriptUnit, AfterScriptEntity.class);

        return afterScriptDao.createAfterScript(afterScriptEntity);
    }

    @Override
    public void updateAfterScript(@NotNull @Valid AfterScriptUnit afterScriptUnit) {
        AfterScriptEntity afterScriptEntity = BeanMapper.map(afterScriptUnit, AfterScriptEntity.class);

        afterScriptDao.updateAfterScript(afterScriptEntity);
    }

    @Override
    public void deleteAfterScript(@NotNull String id) {
        afterScriptDao.deleteAfterScript(id);
    }

    @Override
    public AfterScriptUnit findOne(String id) {
        AfterScriptEntity afterScriptEntity = afterScriptDao.findAfterScript(id);

        AfterScriptUnit afterScriptUnit = BeanMapper.map(afterScriptEntity, AfterScriptUnit.class);
        return afterScriptUnit;
    }

    @Override
    public List<AfterScriptUnit> findList(List<String> idList) {
        List<AfterScriptEntity> afterScriptEntityList =  afterScriptDao.findAfterScriptList(idList);

        List<AfterScriptUnit> afterScriptUnitList =  BeanMapper.mapList(afterScriptEntityList, AfterScriptUnit.class);
        return afterScriptUnitList;
    }

    @Override
    public AfterScriptUnit findAfterScript(@NotNull String id) {
        AfterScriptUnit afterScriptUnit = findOne(id);

        joinTemplate.joinQuery(afterScriptUnit);
        return afterScriptUnit;
    }

    @Override
    public List<AfterScriptUnit> findAllAfterScript() {
        List<AfterScriptEntity> afterScriptEntityList =  afterScriptDao.findAllAfterScript();

        List<AfterScriptUnit> afterScriptUnitList =  BeanMapper.mapList(afterScriptEntityList, AfterScriptUnit.class);

        joinTemplate.joinQuery(afterScriptUnitList);
        return afterScriptUnitList;
    }

    @Override
    public List<AfterScriptUnit> findAfterScriptList(AfterScriptUnitQuery afterScriptUnitQuery) {
        List<AfterScriptEntity> afterScriptEntityList = afterScriptDao.findAfterScriptList(afterScriptUnitQuery);

        List<AfterScriptUnit> afterScriptUnitList = BeanMapper.mapList(afterScriptEntityList, AfterScriptUnit.class);

        joinTemplate.joinQuery(afterScriptUnitList);

        return afterScriptUnitList;
    }

    @Override
    public Pagination<AfterScriptUnit> findAfterScriptPage(AfterScriptUnitQuery afterScriptUnitQuery) {

        Pagination<AfterScriptEntity>  pagination = afterScriptDao.findAfterScriptPage(afterScriptUnitQuery);

        List<AfterScriptUnit> afterScriptUnitList = BeanMapper.mapList(pagination.getDataList(), AfterScriptUnit.class);

        joinTemplate.joinQuery(afterScriptUnitList);

        return PaginationBuilder.build(pagination, afterScriptUnitList);
    }
}