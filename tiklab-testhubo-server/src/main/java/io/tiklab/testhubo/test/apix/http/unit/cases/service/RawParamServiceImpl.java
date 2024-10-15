package io.tiklab.testhubo.test.apix.http.unit.cases.service;

import io.tiklab.testhubo.test.apix.http.unit.cases.dao.RawParamDao;
import io.tiklab.testhubo.test.apix.http.unit.cases.entity.RawParamEntity;
import io.tiklab.testhubo.test.apix.http.unit.cases.model.RawParamUnit;
import io.tiklab.testhubo.test.apix.http.unit.cases.model.RawParamUnitQuery;
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
* raw类型 服务
*/
@Service
public class RawParamServiceImpl implements RawParamService {

    @Autowired
    RawParamDao rawParamDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createRawParam(@NotNull @Valid RawParamUnit rawParamUnit) {
        RawParamEntity rawParamEntity = BeanMapper.map(rawParamUnit, RawParamEntity.class);

        return rawParamDao.createRawParam(rawParamEntity);
    }

    @Override
    public void updateRawParam(@NotNull @Valid RawParamUnit rawParamUnit) {
        RawParamEntity rawParamEntity = BeanMapper.map(rawParamUnit, RawParamEntity.class);

        rawParamDao.updateRawParam(rawParamEntity);
    }

    @Override
    public void deleteRawParam(@NotNull String id) {
        rawParamDao.deleteRawParam(id);
    }

    @Override
    public void deleteAllRawParam(String caseId) {
        RawParamUnitQuery rawParamUnitQuery = new RawParamUnitQuery();
        rawParamUnitQuery.setApiUnitId(caseId);
        List<RawParamUnit> rawParamList = findRawParamList(rawParamUnitQuery);
        for(RawParamUnit rawParamUnit: rawParamList){
            rawParamDao.deleteRawParam(rawParamUnit.getId());
        }

    }


    @Override
    public RawParamUnit findOne(String id) {
        RawParamEntity rawParamEntity = rawParamDao.findRawParam(id);

        RawParamUnit rawParamUnit = BeanMapper.map(rawParamEntity, RawParamUnit.class);
        return rawParamUnit;
    }

    @Override
    public List<RawParamUnit> findList(List<String> idList) {
        List<RawParamEntity> rawParamEntityList =  rawParamDao.findRawParamList(idList);

        List<RawParamUnit> rawParamUnitList =  BeanMapper.mapList(rawParamEntityList, RawParamUnit.class);
        return rawParamUnitList;
    }

    @Override
    public RawParamUnit findRawParam(@NotNull String id) {
        RawParamUnit rawParamUnit = findOne(id);

        joinTemplate.joinQuery(rawParamUnit);
        return rawParamUnit;
    }

    @Override
    public List<RawParamUnit> findAllRawParam() {
        List<RawParamEntity> rawParamEntityList =  rawParamDao.findAllRawParam();

        List<RawParamUnit> rawParamUnitList =  BeanMapper.mapList(rawParamEntityList, RawParamUnit.class);

        joinTemplate.joinQuery(rawParamUnitList);
        return rawParamUnitList;
    }

    @Override
    public List<RawParamUnit> findRawParamList(RawParamUnitQuery rawParamUnitQuery) {
        List<RawParamEntity> rawParamEntityList = rawParamDao.findRawParamList(rawParamUnitQuery);

        List<RawParamUnit> rawParamUnitList = BeanMapper.mapList(rawParamEntityList, RawParamUnit.class);

        joinTemplate.joinQuery(rawParamUnitList);

        return rawParamUnitList;
    }

    @Override
    public Pagination<RawParamUnit> findRawParamPage(RawParamUnitQuery rawParamUnitQuery) {

        Pagination<RawParamEntity>  pagination = rawParamDao.findRawParamPage(rawParamUnitQuery);

        List<RawParamUnit> rawParamUnitList = BeanMapper.mapList(pagination.getDataList(), RawParamUnit.class);

        joinTemplate.joinQuery(rawParamUnitList);


        return PaginationBuilder.build(pagination, rawParamUnitList);
    }
}