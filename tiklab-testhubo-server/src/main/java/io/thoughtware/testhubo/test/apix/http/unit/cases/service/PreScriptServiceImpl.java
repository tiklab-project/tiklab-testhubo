package io.thoughtware.testhubo.test.apix.http.unit.cases.service;

import io.thoughtware.testhubo.test.apix.http.unit.cases.dao.PreScriptDao;
import io.thoughtware.testhubo.test.apix.http.unit.cases.model.PreScriptUnit;
import io.thoughtware.testhubo.test.apix.http.unit.cases.model.PreScriptUnitQuery;
import io.thoughtware.testhubo.test.apix.http.unit.cases.entity.PreScriptEntity;
import io.thoughtware.core.page.PaginationBuilder;


import io.thoughtware.core.page.Pagination;
import io.thoughtware.toolkit.beans.BeanMapper;
import io.thoughtware.toolkit.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 前置脚本 服务
*/
@Service
public class PreScriptServiceImpl implements PreScriptService {

    @Autowired
    PreScriptDao preScriptDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createPreScript(@NotNull @Valid PreScriptUnit preScriptUnit) {
        PreScriptEntity preScriptEntity = BeanMapper.map(preScriptUnit, PreScriptEntity.class);

        return preScriptDao.createPreScript(preScriptEntity);
    }

    @Override
    public void updatePreScript(@NotNull @Valid PreScriptUnit preScriptUnit) {
        PreScriptEntity preScriptEntity = BeanMapper.map(preScriptUnit, PreScriptEntity.class);

        preScriptDao.updatePreScript(preScriptEntity);
    }

    @Override
    public void deletePreScript(@NotNull String id) {
        preScriptDao.deletePreScript(id);
    }


    @Override
    public PreScriptUnit findOne(String id) {
        PreScriptEntity preScriptEntity = preScriptDao.findPreScript(id);

        PreScriptUnit preScriptUnit = BeanMapper.map(preScriptEntity, PreScriptUnit.class);
        return preScriptUnit;
    }

    @Override
    public List<PreScriptUnit> findList(List<String> idList) {
        List<PreScriptEntity> preScriptEntityList =  preScriptDao.findPreScriptList(idList);

        List<PreScriptUnit> preScriptUnitList =  BeanMapper.mapList(preScriptEntityList, PreScriptUnit.class);
        return preScriptUnitList;
    }

    @Override
    public PreScriptUnit findPreScript(@NotNull String id) {
        PreScriptUnit preScriptUnit = findOne(id);

        joinTemplate.joinQuery(preScriptUnit);
        return preScriptUnit;
    }

    @Override
    public List<PreScriptUnit> findAllPreScript() {
        List<PreScriptEntity> preScriptEntityList =  preScriptDao.findAllPreScript();

        List<PreScriptUnit> preScriptUnitList =  BeanMapper.mapList(preScriptEntityList, PreScriptUnit.class);

        joinTemplate.joinQuery(preScriptUnitList);
        return preScriptUnitList;
    }

    @Override
    public List<PreScriptUnit> findPreScriptList(PreScriptUnitQuery preScriptUnitQuery) {
        List<PreScriptEntity> preScriptEntityList = preScriptDao.findPreScriptList(preScriptUnitQuery);

        List<PreScriptUnit> preScriptUnitList = BeanMapper.mapList(preScriptEntityList, PreScriptUnit.class);

        joinTemplate.joinQuery(preScriptUnitList);

        return preScriptUnitList;
    }

    @Override
    public Pagination<PreScriptUnit> findPreScriptPage(PreScriptUnitQuery preScriptUnitQuery) {

        Pagination<PreScriptEntity>  pagination = preScriptDao.findPreScriptPage(preScriptUnitQuery);

        List<PreScriptUnit> preScriptUnitList = BeanMapper.mapList(pagination.getDataList(), PreScriptUnit.class);

        joinTemplate.joinQuery(preScriptUnitList);

        return PaginationBuilder.build(pagination, preScriptUnitList);
    }
}