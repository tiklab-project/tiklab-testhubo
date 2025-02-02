package io.tiklab.testhubo.test.apix.http.unit.cases.service;

import io.tiklab.testhubo.test.apix.http.unit.cases.dao.AssertCaseDao;
import io.tiklab.testhubo.test.apix.http.unit.cases.model.AssertUnit;
import io.tiklab.testhubo.test.apix.http.unit.cases.model.AssertUnitQuery;
import io.tiklab.testhubo.test.apix.http.unit.cases.entity.AssertCaseEntity;
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
* 断言 服务
*/
@Service
public class AssertCaseServiceImpl implements AssertService {

    @Autowired
    AssertCaseDao assertCaseDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createAssertCase(@NotNull @Valid AssertUnit assertUnit) {
        AssertCaseEntity assertCaseEntity = BeanMapper.map(assertUnit, AssertCaseEntity.class);

        return assertCaseDao.createAssertCase(assertCaseEntity);
    }

    @Override
    public void updateAssertCase(@NotNull @Valid AssertUnit assertUnit) {
        AssertCaseEntity assertCaseEntity = BeanMapper.map(assertUnit, AssertCaseEntity.class);

        assertCaseDao.updateAssertCase(assertCaseEntity);
    }

    @Override
    public void deleteAssertCase(@NotNull String id) {
        assertCaseDao.deleteAssertCase(id);
    }

    @Override
    public void deleteAllAssertCase( String caseId) {
        AssertUnitQuery assertUnitQuery = new AssertUnitQuery();
        assertUnitQuery.setApiUnitId(caseId);
        List<AssertUnit> assertCaseList = findAssertCaseList(assertUnitQuery);
        for (AssertUnit assertCase : assertCaseList) {
            assertCaseDao.deleteAssertCase(assertCase.getId());
        }

    }

    @Override
    public AssertUnit findOne(String id) {
        AssertCaseEntity assertCaseEntity = assertCaseDao.findAssertCase(id);

        AssertUnit assertUnit = BeanMapper.map(assertCaseEntity, AssertUnit.class);
        return assertUnit;
    }

    @Override
    public List<AssertUnit> findList(List<String> idList) {
        List<AssertCaseEntity> assertCaseEntityList =  assertCaseDao.findAssertCaseList(idList);

        List<AssertUnit> assertUnitList =  BeanMapper.mapList(assertCaseEntityList, AssertUnit.class);
        return assertUnitList;
    }

    @Override
    public AssertUnit findAssertCase(@NotNull String id) {
        AssertUnit assertUnit = findOne(id);

        joinTemplate.joinQuery(assertUnit);
        return assertUnit;
    }

    @Override
    public List<AssertUnit> findAllAssertCase() {
        List<AssertCaseEntity> assertCaseEntityList =  assertCaseDao.findAllAssertCase();

        List<AssertUnit> assertUnitList =  BeanMapper.mapList(assertCaseEntityList, AssertUnit.class);

        joinTemplate.joinQuery(assertUnitList);
        return assertUnitList;
    }

    @Override
    public List<AssertUnit> findAssertCaseList(AssertUnitQuery assertUnitQuery) {
        List<AssertCaseEntity> assertCaseEntityList = assertCaseDao.findAssertCaseList(assertUnitQuery);

        List<AssertUnit> assertUnitList = BeanMapper.mapList(assertCaseEntityList, AssertUnit.class);

        joinTemplate.joinQuery(assertUnitList);

        return assertUnitList;
    }

    @Override
    public Pagination<AssertUnit> findAssertCasePage(AssertUnitQuery assertUnitQuery) {

        Pagination<AssertCaseEntity>  pagination = assertCaseDao.findAssertCasePage(assertUnitQuery);

        List<AssertUnit> assertUnitList = BeanMapper.mapList(pagination.getDataList(), AssertUnit.class);

        joinTemplate.joinQuery(assertUnitList);

        return PaginationBuilder.build(pagination, assertUnitList);

    }
}