package io.thoughtware.teston.test.web.scene.cases.service;

import io.thoughtware.teston.test.common.stepcommon.model.StepCommon;
import io.thoughtware.teston.test.common.stepcommon.service.StepCommonService;
import io.thoughtware.teston.common.MagicValue;
import io.thoughtware.teston.test.common.stepassert.model.StepAssertCommon;
import io.thoughtware.teston.test.common.stepassert.model.StepAssertCommonQuery;
import io.thoughtware.teston.test.common.stepassert.service.StepAssertCommonService;
import io.thoughtware.teston.test.web.scene.cases.dao.WebSceneStepDao;
import io.thoughtware.teston.test.web.scene.cases.entity.WebSceneStepEntity;
import io.thoughtware.beans.BeanMapper;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.core.page.PaginationBuilder;
import io.thoughtware.join.JoinTemplate;
import io.thoughtware.teston.test.web.scene.cases.model.WebSceneStep;
import io.thoughtware.teston.test.web.scene.cases.model.WebSceneStepQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;


/**
* WebSceneStepServiceImpl
*/
@Service
public class WebSceneStepServiceImpl implements WebSceneStepService {

    @Autowired
    WebSceneStepDao webSceneStepDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    StepAssertCommonService stepAssertCommonService;

    @Autowired
    StepCommonService stepCommonService;


    @Override
    public String createWebSceneStep(@NotNull @Valid WebSceneStep webSceneStep) {

        //公共步骤 创建
        StepCommon stepCommon = new StepCommon();
        stepCommon.setCaseId(webSceneStep.getWebSceneId());
        stepCommon.setType(MagicValue.CASE_TYPE_WEB);
        String stepId = stepCommonService.createStepCommon(stepCommon);

        // 创建 webSceneStep
        WebSceneStepEntity webSceneStepEntity = BeanMapper.map(webSceneStep, WebSceneStepEntity.class);
        webSceneStepEntity.setId(stepId);
        webSceneStepDao.createWebSceneStep(webSceneStepEntity);

        return stepId;
    }

    @Override
    public void updateWebSceneStep(@NotNull @Valid WebSceneStep webSceneStep) {
        WebSceneStepEntity webSceneStepEntity = BeanMapper.map(webSceneStep, WebSceneStepEntity.class);
        webSceneStepDao.updateWebSceneStep(webSceneStepEntity);
    }

    @Override
    public void deleteWebSceneStep(@NotNull String id) {
        webSceneStepDao.deleteWebSceneStep(id);
    }

    @Override
    public WebSceneStep findOne(String id) {
        WebSceneStepEntity webSceneStepEntity = webSceneStepDao.findWebSceneStep(id);

        WebSceneStep webSceneStep = BeanMapper.map(webSceneStepEntity, WebSceneStep.class);
        return webSceneStep;
    }

    @Override
    public List<WebSceneStep> findList(List<String> idList) {
        List<WebSceneStepEntity> webSceneStepEntityList =  webSceneStepDao.findWebSceneStepList(idList);

        List<WebSceneStep> webSceneStepList =  BeanMapper.mapList(webSceneStepEntityList,WebSceneStep.class);
        return webSceneStepList;
    }

    @Override
    public WebSceneStep findWebSceneStep(@NotNull String id) {
        WebSceneStep webSceneStep = findOne(id);

        joinTemplate.joinQuery(webSceneStep);

        return webSceneStep;
    }

    @Override
    public List<WebSceneStep> findAllWebSceneStep() {
        List<WebSceneStepEntity> webSceneStepEntityList =  webSceneStepDao.findAllWebSceneStep();

        List<WebSceneStep> webSceneStepList =  BeanMapper.mapList(webSceneStepEntityList,WebSceneStep.class);

        joinTemplate.joinQuery(webSceneStepList);

        return webSceneStepList;
    }

    @Override
    public List<WebSceneStep> findWebSceneStepList(WebSceneStepQuery webSceneStepQuery) {
        List<WebSceneStepEntity> webSceneStepEntityList = webSceneStepDao.findWebSceneStepList(webSceneStepQuery);
        List<WebSceneStep> webSceneStepList = BeanMapper.mapList(webSceneStepEntityList,WebSceneStep.class);

        for(WebSceneStep webSceneStep:webSceneStepList){
            StepAssertCommonQuery stepAssertCommonQuery = new StepAssertCommonQuery();
            stepAssertCommonQuery.setStepId(webSceneStep.getId());
            List<StepAssertCommon> stepAssertCommonList = stepAssertCommonService.findStepAssertCommonList(stepAssertCommonQuery);
            webSceneStep.setStepAssertCommonList(stepAssertCommonList);
        }

        return webSceneStepList;
    }

    @Override
    public Pagination<WebSceneStep> findWebSceneStepPage(WebSceneStepQuery webSceneStepQuery) {
        Pagination<WebSceneStepEntity>  pagination = webSceneStepDao.findWebSceneStepPage(webSceneStepQuery);

        List<WebSceneStep> webSceneStepList = BeanMapper.mapList(pagination.getDataList(),WebSceneStep.class);

        joinTemplate.joinQuery(webSceneStepList);

        return PaginationBuilder.build(pagination,webSceneStepList);
    }


}