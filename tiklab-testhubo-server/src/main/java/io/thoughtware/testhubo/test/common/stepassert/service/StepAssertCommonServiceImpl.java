package io.thoughtware.testhubo.test.common.stepassert.service;

import io.thoughtware.testhubo.common.MagicValue;
import io.thoughtware.testhubo.test.common.stepassert.dao.StepAssertCommonDao;
import io.thoughtware.toolkit.beans.BeanMapper;
import io.thoughtware.toolkit.join.JoinTemplate;
import io.thoughtware.testhubo.test.common.stepassert.model.ElementAssert;
import io.thoughtware.testhubo.test.common.stepassert.model.StepAssertCommon;
import io.thoughtware.testhubo.test.common.stepassert.model.StepAssertCommonQuery;
import io.thoughtware.testhubo.test.common.stepassert.model.VariableAssert;
import io.thoughtware.testhubo.test.common.stepassert.entity.StepAssertCommonEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

/**
* 功能用例下步骤 服务
*/
@Service
public class StepAssertCommonServiceImpl implements StepAssertCommonService {

    @Autowired
    StepAssertCommonDao stepAssertCommonDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    VariableAssertService variableAssertService;

    @Autowired
    ElementAssertService elementAssertService;


    @Override
    public String createStepAssertCommon(@NotNull @Valid StepAssertCommon stepAssertCommon) {
        StepAssertCommonEntity stepAssertCommonEntity = BeanMapper.map(stepAssertCommon, StepAssertCommonEntity.class);
        stepAssertCommonEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));
        String id = stepAssertCommonDao.createStepAssertCommon(stepAssertCommonEntity);

        if(Objects.equals(stepAssertCommon.getType(), MagicValue.ASSERT_TYPE_VARIABLE)){
            VariableAssert variableAssert = stepAssertCommon.getVariableAssert();
            variableAssert.setAssertId(id);
            variableAssert.setId(id);
            variableAssertService.createVariableAssert(variableAssert);
        }

        if(Objects.equals(stepAssertCommon.getType(), MagicValue.ASSERT_TYPE_ELEMENT)){
            ElementAssert elementAssert = stepAssertCommon.getElementAssert();
            elementAssert.setAssertId(id);
            elementAssert.setId(id);
            elementAssertService.createElementAssert(elementAssert);
        }

        return id;
    }

    @Override
    public void updateStepAssertCommon(@NotNull @Valid StepAssertCommon stepAssertCommon) {
        StepAssertCommonEntity stepAssertCommonEntity = BeanMapper.map(stepAssertCommon, StepAssertCommonEntity.class);
        stepAssertCommonDao.updateStepAssertCommon(stepAssertCommonEntity);

        if(Objects.equals(stepAssertCommon.getType(), MagicValue.ASSERT_TYPE_VARIABLE)){
            VariableAssert variableAssert = stepAssertCommon.getVariableAssert();
            variableAssertService.updateVariableAssert(variableAssert);
        }

        if(Objects.equals(stepAssertCommon.getType(), MagicValue.ASSERT_TYPE_ELEMENT)){
            ElementAssert elementAssert = stepAssertCommon.getElementAssert();
            elementAssertService.updateElementAssert(elementAssert);
        }

    }

    @Override
    public void deleteStepAssertCommon(@NotNull String id) {
        StepAssertCommonEntity stepAssertCommon = stepAssertCommonDao.findStepAssertCommon(id);

        if(Objects.equals(stepAssertCommon.getType(), MagicValue.ASSERT_TYPE_VARIABLE)){
            variableAssertService.deleteVariableAssert(id);
        }

        if(Objects.equals(stepAssertCommon.getType(), MagicValue.ASSERT_TYPE_ELEMENT)){
            elementAssertService.deleteElementAssert(id);
        }

        stepAssertCommonDao.deleteStepAssertCommon(id);
    }

    @Override
    public StepAssertCommon findOne(String id) {
        StepAssertCommonEntity stepAssertCommonEntity = stepAssertCommonDao.findStepAssertCommon(id);

        StepAssertCommon stepAssertCommon = BeanMapper.map(stepAssertCommonEntity, StepAssertCommon.class);
        return stepAssertCommon;
    }

    @Override
    public StepAssertCommon findStepAssertCommon(@NotNull String id) {
        StepAssertCommon stepAssertCommon = findOne(id);
        joinTemplate.joinQuery(stepAssertCommon);

        if(Objects.equals(stepAssertCommon.getType(), MagicValue.ASSERT_TYPE_VARIABLE)){
            VariableAssert variableAssert = variableAssertService.findVariableAssert(id);
            stepAssertCommon.setVariableAssert(variableAssert);
        }

        if(Objects.equals(stepAssertCommon.getType(), MagicValue.ASSERT_TYPE_ELEMENT)){
            ElementAssert elementAssert = elementAssertService.findElementAssert(id);
            stepAssertCommon.setElementAssert(elementAssert);
        }

        return stepAssertCommon;
    }

    @Override
    public List<StepAssertCommon> findStepAssertCommonList(StepAssertCommonQuery stepAssertCommonQuery) {
        List<StepAssertCommonEntity> stepAssertCommonEntityList = stepAssertCommonDao.findStepAssertCommonList(stepAssertCommonQuery);
        List<StepAssertCommon> stepAssertCommonList = BeanMapper.mapList(stepAssertCommonEntityList, StepAssertCommon.class);
        joinTemplate.joinQuery(stepAssertCommonList);

        for (StepAssertCommon stepAssertCommon:stepAssertCommonList){
            if(Objects.equals(stepAssertCommon.getType(), MagicValue.ASSERT_TYPE_VARIABLE)){
                VariableAssert variableAssert = variableAssertService.findVariableAssert(stepAssertCommon.getId());
                stepAssertCommon.setVariableAssert(variableAssert);
            }

            if(Objects.equals(stepAssertCommon.getType(), MagicValue.ASSERT_TYPE_ELEMENT)){
                ElementAssert elementAssert = elementAssertService.findElementAssert(stepAssertCommon.getId());
                stepAssertCommon.setElementAssert(elementAssert);
            }
        }

        return stepAssertCommonList;
    }


}