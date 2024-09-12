package io.thoughtware.testhubo.test.common.ifjudgment.service;

import io.thoughtware.testhubo.test.common.ifjudgment.entity.IfJudgmentEntity;
import io.thoughtware.testhubo.test.common.ifjudgment.model.IfJudgment;
import io.thoughtware.testhubo.test.common.ifjudgment.model.IfJudgmentQuery;
import io.thoughtware.testhubo.test.common.ifjudgment.model.IfVariable;
import io.thoughtware.testhubo.test.common.ifjudgment.model.IfVariableQuery;
import io.thoughtware.testhubo.test.common.stepcommon.model.StepCommon;
import io.thoughtware.testhubo.test.common.stepcommon.service.StepCommonService;
import io.thoughtware.toolkit.beans.BeanMapper;
import io.thoughtware.toolkit.join.JoinTemplate;
import io.thoughtware.testhubo.common.MagicValue;
import io.thoughtware.testhubo.test.common.ifjudgment.dao.IfJudgmentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 功能用例下步骤 服务
*/
@Service
public class IfJudgmentServiceImpl implements IfJudgmentService {

    @Autowired
    IfJudgmentDao ifJudgmentDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    StepCommonService stepCommonService;

    @Autowired
    IfVariableService ifVariableService;

    @Override
    public String createIfJudgment(@NotNull @Valid IfJudgment ifJudgment) {
        //公共步骤 创建
        StepCommon stepCommon = new StepCommon();
        stepCommon.setCaseId(ifJudgment.getCaseId());
        stepCommon.setType(MagicValue.CASE_TYPE_IF);
        String stepId = stepCommonService.createStepCommon(stepCommon);

        IfJudgmentEntity ifJudgmentEntity = BeanMapper.map(ifJudgment, IfJudgmentEntity.class);
        ifJudgmentEntity.setId(stepId);
        ifJudgmentDao.createIfJudgment(ifJudgmentEntity);

        return stepId;
    }

    @Override
    public void updateIfJudgment(@NotNull @Valid IfJudgment ifJudgment) {
        IfJudgmentEntity ifJudgmentEntity = BeanMapper.map(ifJudgment, IfJudgmentEntity.class);
        ifJudgmentDao.updateIfJudgment(ifJudgmentEntity);

    }

    @Override
    public void deleteIfJudgment(@NotNull String id) {

        ifJudgmentDao.deleteIfJudgment(id);
    }

    @Override
    public IfJudgment findOne(String id) {
        IfJudgmentEntity ifJudgmentEntity = ifJudgmentDao.findIfJudgment(id);

        IfJudgment ifJudgment = BeanMapper.map(ifJudgmentEntity, IfJudgment.class);
        return ifJudgment;
    }

    @Override
    public IfJudgment findIfJudgment(@NotNull String id) {
        IfJudgment ifJudgment = findOne(id);
        joinTemplate.joinQuery(ifJudgment);

        return ifJudgment;
    }

    @Override
    public List<IfJudgment> findIfJudgmentList(IfJudgmentQuery ifJudgmentQuery) {
        List<IfJudgmentEntity> ifJudgmentEntityList = ifJudgmentDao.findIfJudgmentList(ifJudgmentQuery);
        List<IfJudgment> ifJudgmentList = BeanMapper.mapList(ifJudgmentEntityList, IfJudgment.class);
        joinTemplate.joinQuery(ifJudgmentList);

        return ifJudgmentList;
    }

    @Override
    public IfJudgment findIfAddVariable(String id) {
        IfJudgment ifJudgment = findOne(id);

        if(ifJudgment==null){
            return null;
        }

        IfVariableQuery ifVariableQuery = new IfVariableQuery();
        ifVariableQuery.setStepId(id);
        List<IfVariable> ifVariableList = ifVariableService.findIfVariableList(ifVariableQuery);

        ifJudgment.setIfVariableList(ifVariableList);
        return ifJudgment;
    }


}