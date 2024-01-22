package io.thoughtware.teston.test.app.scene.cases.service;

import io.thoughtware.teston.test.app.scene.cases.model.AppSceneStep;
import io.thoughtware.teston.test.app.scene.cases.model.AppSceneStepQuery;
import io.thoughtware.teston.test.common.stepcommon.model.StepCommon;
import io.thoughtware.teston.test.common.stepcommon.service.StepCommonService;
import io.thoughtware.teston.common.MagicValue;
import io.thoughtware.teston.test.app.scene.cases.dao.AppSceneStepDao;
import io.thoughtware.teston.test.app.scene.cases.entity.AppSceneStepEntity;
import io.thoughtware.toolkit.beans.BeanMapper;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.core.page.PaginationBuilder;
import io.thoughtware.toolkit.join.JoinTemplate;
import io.thoughtware.teston.test.common.stepassert.model.StepAssertCommon;
import io.thoughtware.teston.test.common.stepassert.model.StepAssertCommonQuery;
import io.thoughtware.teston.test.common.stepassert.service.StepAssertCommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* app场景下步骤 服务
*/
@Service
public class AppSceneStepServiceImpl implements AppSceneStepService {

    @Autowired
    AppSceneStepDao appSceneStepDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    StepAssertCommonService stepAssertCommonService;

    @Autowired
    StepCommonService stepCommonService;

    @Override
    public String createAppSceneStep(@NotNull @Valid AppSceneStep appSceneStep) {
        //公共步骤 创建
        StepCommon stepCommon = new StepCommon();
        stepCommon.setCaseId(appSceneStep.getAppSceneId());
        stepCommon.setType(MagicValue.CASE_TYPE_APP);
        String stepId = stepCommonService.createStepCommon(stepCommon);

        AppSceneStepEntity appSceneStepEntity = BeanMapper.map(appSceneStep, AppSceneStepEntity.class);
        appSceneStepEntity.setId(stepId);
        appSceneStepDao.createAppSceneStep(appSceneStepEntity);

        return stepId;
    }

    @Override
    public void updateAppSceneStep(@NotNull @Valid AppSceneStep appSceneStep) {
        AppSceneStepEntity appSceneStepEntity = BeanMapper.map(appSceneStep, AppSceneStepEntity.class);

        appSceneStepDao.updateAppSceneStep(appSceneStepEntity);
    }

    @Override
    public void deleteAppSceneStep(@NotNull String id) {
        appSceneStepDao.deleteAppSceneStep(id);
    }

    @Override
    public AppSceneStep findOne(String id) {
        AppSceneStepEntity appSceneStepEntity = appSceneStepDao.findAppSceneStep(id);

        AppSceneStep appSceneStep = BeanMapper.map(appSceneStepEntity, AppSceneStep.class);
        return appSceneStep;
    }

    @Override
    public List<AppSceneStep> findList(List<String> idList) {
        List<AppSceneStepEntity> appSceneStepEntityList =  appSceneStepDao.findAppSceneStepList(idList);

        List<AppSceneStep> appSceneStepList =  BeanMapper.mapList(appSceneStepEntityList,AppSceneStep.class);
        return appSceneStepList;
    }

    @Override
    public AppSceneStep findAppSceneStep(@NotNull String id) {
        AppSceneStep appSceneStep = findOne(id);

        joinTemplate.joinQuery(appSceneStep);

        return appSceneStep;
    }



    @Override
    public List<AppSceneStep> findAllAppSceneStep() {
        List<AppSceneStepEntity> appSceneStepEntityList =  appSceneStepDao.findAllAppSceneStep();

        List<AppSceneStep> appSceneStepList =  BeanMapper.mapList(appSceneStepEntityList,AppSceneStep.class);

        joinTemplate.joinQuery(appSceneStepList);

        return appSceneStepList;
    }

    @Override
    public List<AppSceneStep> findAppSceneStepList(AppSceneStepQuery appSceneStepQuery) {
        List<AppSceneStepEntity> appSceneStepEntityList = appSceneStepDao.findAppSceneStepList(appSceneStepQuery);
        List<AppSceneStep> appSceneStepList = BeanMapper.mapList(appSceneStepEntityList,AppSceneStep.class);
        joinTemplate.joinQuery(appSceneStepList);

        for(AppSceneStep appSceneStep: appSceneStepList){
            StepAssertCommonQuery stepAssertCommonQuery = new StepAssertCommonQuery();
            stepAssertCommonQuery.setStepId(appSceneStep.getId());
            List<StepAssertCommon> stepAssertCommonList = stepAssertCommonService.findStepAssertCommonList(stepAssertCommonQuery);
            appSceneStep.setStepAssertCommonList(stepAssertCommonList);
        }

        return appSceneStepList;
    }

    @Override
    public Pagination<AppSceneStep> findAppSceneStepPage(AppSceneStepQuery appSceneStepQuery) {
        Pagination<AppSceneStepEntity>  pagination = appSceneStepDao.findAppSceneStepPage(appSceneStepQuery);

        List<AppSceneStep> appSceneStepList = BeanMapper.mapList(pagination.getDataList(),AppSceneStep.class);

        joinTemplate.joinQuery(appSceneStepList);

        return PaginationBuilder.build(pagination,appSceneStepList);
    }

}