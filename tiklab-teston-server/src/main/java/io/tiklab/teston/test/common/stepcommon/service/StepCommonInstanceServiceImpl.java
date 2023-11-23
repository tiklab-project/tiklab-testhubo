package io.tiklab.teston.test.common.stepcommon.service;

import io.tiklab.beans.BeanMapper;
import io.tiklab.join.JoinTemplate;
import io.tiklab.teston.common.MagicValue;
import io.tiklab.teston.test.apix.http.unit.instance.model.ApiUnitInstance;
import io.tiklab.teston.test.app.scene.instance.model.AppSceneInstanceStep;
import io.tiklab.teston.test.common.ifjudgment.model.IfJudgmentInstance;
import io.tiklab.teston.test.common.stepcommon.service.StepCommonInstanceService;
import io.tiklab.teston.test.apix.http.scene.instance.service.ApiSceneInstanceService;
import io.tiklab.teston.test.apix.http.unit.instance.service.ApiUnitInstanceService;
import io.tiklab.teston.test.app.scene.instance.service.AppSceneInstanceStepService;
import io.tiklab.teston.test.common.ifjudgment.service.IfJudgmentInstanceService;
import io.tiklab.teston.test.common.stepcommon.dao.StepCommonInstanceDao;
import io.tiklab.teston.test.common.stepcommon.entity.StepCommonInstanceEntity;
import io.tiklab.teston.test.common.stepcommon.model.StepCommonInstance;
import io.tiklab.teston.test.common.stepcommon.model.StepCommonInstanceQuery;
import io.tiklab.teston.test.web.scene.instance.model.WebSceneInstanceStep;
import io.tiklab.teston.test.web.scene.instance.service.WebSceneInstanceStepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 功能用例下步骤 服务
*/
@Service
public class StepCommonInstanceServiceImpl implements StepCommonInstanceService {

    @Autowired
    StepCommonInstanceDao stepCommonInstanceDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    ApiUnitInstanceService apiUnitInstanceService;
    @Autowired
    ApiSceneInstanceService apiSceneInstanceService;
    @Autowired
    AppSceneInstanceStepService appSceneInstanceStepService;
    @Autowired
    WebSceneInstanceStepService webSceneInstanceStepService;
    @Autowired
    IfJudgmentInstanceService ifJudgmentInstanceService;

    @Override
    public String createStepCommonInstance(@NotNull @Valid StepCommonInstance stepCommonInstance) {

        StepCommonInstanceEntity stepCommonInstanceEntity = BeanMapper.map(stepCommonInstance, StepCommonInstanceEntity.class);

        String id = stepCommonInstanceDao.createStepCommonInstance(stepCommonInstanceEntity);

        return id;
    }

    @Override
    public void updateStepCommonInstance(@NotNull @Valid StepCommonInstance stepCommonInstance) {

        StepCommonInstanceEntity entity = BeanMapper.map(stepCommonInstance, StepCommonInstanceEntity.class);
        stepCommonInstanceDao.updateStepCommonInstance(entity);

    }

    @Override
    public void deleteStepCommonInstance(@NotNull String id,String caseType) {
        stepCommonInstanceDao.deleteStepCommonInstance(id);

    }

    @Override
    public StepCommonInstance findOne(String id) {
        StepCommonInstanceEntity stepCommonInstanceEntity = stepCommonInstanceDao.findStepCommonInstance(id);

        StepCommonInstance stepCommonInstance = BeanMapper.map(stepCommonInstanceEntity, StepCommonInstance.class);
        return stepCommonInstance;
    }

    @Override
    public StepCommonInstance findStepCommonInstance(@NotNull String id) {
        StepCommonInstance stepCommonInstance = findOne(id);
        joinTemplate.joinQuery(stepCommonInstance);

        return stepCommonInstance;
    }

    @Override
    public List<StepCommonInstance> findStepCommonInstanceList(StepCommonInstanceQuery stepCommonInstanceQuery) {
        List<StepCommonInstanceEntity> stepCommonInstanceEntityList = stepCommonInstanceDao.findStepCommonInstanceList(stepCommonInstanceQuery);
        List<StepCommonInstance> stepCommonInstanceList = BeanMapper.mapList(stepCommonInstanceEntityList, StepCommonInstance.class);
        joinTemplate.joinQuery(stepCommonInstanceList);

        String caseType = stepCommonInstanceQuery.getCaseType();
        if(caseType!=null){
            for(StepCommonInstance stepCommonInstance:stepCommonInstanceList){
                switch (caseType){
                    case MagicValue.CASE_TYPE_API_SCENE:
                        ApiUnitInstance apiUnitInstance = apiUnitInstanceService.findApiUnitInstance(stepCommonInstance.getId());
                        stepCommonInstance.setApiUnitInstance(apiUnitInstance);
                    case MagicValue.CASE_TYPE_APP:
                        AppSceneInstanceStep appSceneInstanceStep = appSceneInstanceStepService.findAppSceneInstanceStep(stepCommonInstance.getId());
                        stepCommonInstance.setAppSceneInstanceStep(appSceneInstanceStep);
                        break;
                    case MagicValue.CASE_TYPE_WEB:
                        WebSceneInstanceStep webSceneInstanceStep = webSceneInstanceStepService.findWebSceneInstanceStep(stepCommonInstance.getId());
                        stepCommonInstance.setWebSceneInstanceStep(webSceneInstanceStep);
                        break;
                }

                IfJudgmentInstance ifJudgmentInstance = ifJudgmentInstanceService.findIfJudgmentInstance(stepCommonInstance.getId());
                stepCommonInstance.setIfJudgmentInstance(ifJudgmentInstance);
            }
        }

        return stepCommonInstanceList;
    }


}