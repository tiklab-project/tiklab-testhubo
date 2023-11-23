package io.tiklab.teston.test.func.service;

import io.tiklab.beans.BeanMapper;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.join.JoinTemplate;
import io.tiklab.teston.common.MagicValue;
import io.tiklab.teston.test.common.stepcommon.model.StepCommon;
import io.tiklab.teston.test.common.stepcommon.service.StepCommonService;
import io.tiklab.teston.test.func.dao.FuncUnitStepDao;
import io.tiklab.teston.test.func.entity.FuncUnitStepEntity;
import io.tiklab.teston.test.func.model.FuncUnitStep;
import io.tiklab.teston.test.func.model.FuncUnitStepQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

/**
* 功能用例下步骤 服务
*/
@Service
public class FuncUnitStepServiceImpl implements FuncUnitStepService {

    @Autowired
    FuncUnitStepDao funcUnitStepDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    StepCommonService stepCommonService;

    @Override
    public String createFuncUnitStep(@NotNull @Valid FuncUnitStep funcUnitStep) {

        //公共步骤 创建
        StepCommon stepCommon = new StepCommon();
        stepCommon.setCaseId(funcUnitStep.getFuncUnitId());
        stepCommon.setType(MagicValue.CASE_TYPE_FUNCTION);
        String stepId = stepCommonService.createStepCommon(stepCommon);

        FuncUnitStepEntity funcUnitStepEntity = BeanMapper.map(funcUnitStep, FuncUnitStepEntity.class);
        funcUnitStepEntity.setId(stepId);
        funcUnitStepDao.createFuncUnitStep(funcUnitStepEntity);

        return stepId;
    }

    @Override
    public void updateFuncUnitStep(@NotNull @Valid FuncUnitStep funcUnitStep) {
        FuncUnitStepEntity funcUnitStepEntity = BeanMapper.map(funcUnitStep, FuncUnitStepEntity.class);
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