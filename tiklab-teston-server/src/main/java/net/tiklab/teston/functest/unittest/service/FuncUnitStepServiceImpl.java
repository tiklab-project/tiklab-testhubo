package net.tiklab.teston.functest.unittest.service;

import net.tiklab.core.page.Pagination;
import net.tiklab.core.page.PaginationBuilder;
import net.tiklab.teston.functest.unittest.dao.FuncUnitStepDao;
import net.tiklab.teston.functest.unittest.entity.FuncUnitStepEntity;
import net.tiklab.teston.functest.unittest.model.FuncUnitStep;
import net.tiklab.teston.functest.unittest.model.FuncUnitStepQuery;

import net.tiklab.beans.BeanMapper;
import net.tiklab.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

/**
* FuncUnitStepServiceImpl
*/
@Service
public class FuncUnitStepServiceImpl implements FuncUnitStepService {

    @Autowired
    FuncUnitStepDao funcUnitStepDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createFuncUnitStep(@NotNull @Valid FuncUnitStep funcUnitStep) {
        FuncUnitStepEntity funcUnitStepEntity = BeanMapper.map(funcUnitStep, FuncUnitStepEntity.class);

        funcUnitStepEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));

        return funcUnitStepDao.createFuncUnitStep(funcUnitStepEntity);
    }

    @Override
    public void updateFuncUnitStep(@NotNull @Valid FuncUnitStep funcUnitStep) {
        FuncUnitStepEntity funcUnitStepEntity = BeanMapper.map(funcUnitStep, FuncUnitStepEntity.class);

        funcUnitStepEntity.setUpdateTime(new Timestamp(System.currentTimeMillis()));

        funcUnitStepDao.updateFuncUnitStep(funcUnitStepEntity);
    }

    @Override
    public void deleteFuncUnitStep(@NotNull String id) {
        funcUnitStepDao.deleteFuncUnitStep(id);
    }

    @Override
    public FuncUnitStep findOne(String id) {
        FuncUnitStepEntity funcUnitStepEntity = funcUnitStepDao.findFuncUnitStep(id);

        FuncUnitStep funcUnitStep = BeanMapper.map(funcUnitStepEntity, FuncUnitStep.class);
        return funcUnitStep;
    }

    @Override
    public List<FuncUnitStep> findList(List<String> idList) {
        List<FuncUnitStepEntity> funcUnitStepEntityList =  funcUnitStepDao.findFuncUnitStepList(idList);

        List<FuncUnitStep> funcUnitStepList =  BeanMapper.mapList(funcUnitStepEntityList,FuncUnitStep.class);
        return funcUnitStepList;
    }

    @Override
    public FuncUnitStep findFuncUnitStep(@NotNull String id) {
        FuncUnitStep funcUnitStep = findOne(id);

        joinTemplate.joinQuery(funcUnitStep);

        return funcUnitStep;
    }

    @Override
    public List<FuncUnitStep> findAllFuncUnitStep() {
        List<FuncUnitStepEntity> funcUnitStepEntityList =  funcUnitStepDao.findAllFuncUnitStep();

        List<FuncUnitStep> funcUnitStepList =  BeanMapper.mapList(funcUnitStepEntityList,FuncUnitStep.class);

        joinTemplate.joinQuery(funcUnitStepList);

        return funcUnitStepList;
    }

    @Override
    public List<FuncUnitStep> findFuncUnitStepList(FuncUnitStepQuery funcUnitStepQuery) {
        List<FuncUnitStepEntity> funcUnitStepEntityList = funcUnitStepDao.findFuncUnitStepList(funcUnitStepQuery);

        List<FuncUnitStep> funcUnitStepList = BeanMapper.mapList(funcUnitStepEntityList,FuncUnitStep.class);

        joinTemplate.joinQuery(funcUnitStepList);

        return funcUnitStepList;
    }

    @Override
    public Pagination<FuncUnitStep> findFuncUnitStepPage(FuncUnitStepQuery funcUnitStepQuery) {
        Pagination<FuncUnitStepEntity>  pagination = funcUnitStepDao.findFuncUnitStepPage(funcUnitStepQuery);

        List<FuncUnitStep> funcUnitStepList = BeanMapper.mapList(pagination.getDataList(),FuncUnitStep.class);

        joinTemplate.joinQuery(funcUnitStepList);

        return PaginationBuilder.build(pagination,funcUnitStepList);
    }
}