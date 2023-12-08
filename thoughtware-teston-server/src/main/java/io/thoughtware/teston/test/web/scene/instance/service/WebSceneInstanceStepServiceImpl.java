package io.thoughtware.teston.test.web.scene.instance.service;

import io.thoughtware.beans.BeanMapper;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.core.page.PaginationBuilder;
import io.thoughtware.join.JoinTemplate;
import io.thoughtware.teston.test.web.scene.instance.dao.WebSceneInstanceStepDao;
import io.thoughtware.teston.test.web.scene.instance.entity.WebSceneInstanceStepEntity;
import io.thoughtware.teston.test.web.scene.instance.model.WebSceneInstanceStep;
import io.thoughtware.teston.test.web.scene.instance.model.WebSceneInstanceStepQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* web场景下步骤实例 服务
*/
@Service
public class WebSceneInstanceStepServiceImpl implements WebSceneInstanceStepService {

    @Autowired
    WebSceneInstanceStepDao webSceneInstanceStepDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createWebSceneInstanceStep(@NotNull @Valid WebSceneInstanceStep webSceneInstanceStep) {
        WebSceneInstanceStepEntity webSceneInstanceStepEntity = BeanMapper.map(webSceneInstanceStep, WebSceneInstanceStepEntity.class);

        return webSceneInstanceStepDao.createWebSceneInstanceStep(webSceneInstanceStepEntity);
    }

    @Override
    public void updateWebSceneInstanceStep(@NotNull @Valid WebSceneInstanceStep webSceneInstanceStep) {
        WebSceneInstanceStepEntity webSceneInstanceStepEntity = BeanMapper.map(webSceneInstanceStep, WebSceneInstanceStepEntity.class);

        webSceneInstanceStepDao.updateWebSceneInstanceStep(webSceneInstanceStepEntity);
    }

    @Override
    public void deleteWebSceneInstanceStep(@NotNull String id) {
        webSceneInstanceStepDao.deleteWebSceneInstanceStep(id);
    }

    @Override
    public WebSceneInstanceStep findOne(String id) {
        WebSceneInstanceStepEntity webSceneInstanceStepEntity = webSceneInstanceStepDao.findWebSceneInstanceStep(id);

        WebSceneInstanceStep webSceneInstanceStep = BeanMapper.map(webSceneInstanceStepEntity, WebSceneInstanceStep.class);
        return webSceneInstanceStep;
    }

    @Override
    public List<WebSceneInstanceStep> findList(List<String> idList) {
        List<WebSceneInstanceStepEntity> webSceneInstanceStepEntityList =  webSceneInstanceStepDao.findWebSceneInstanceStepList(idList);

        List<WebSceneInstanceStep> webSceneInstanceStepList =  BeanMapper.mapList(webSceneInstanceStepEntityList,WebSceneInstanceStep.class);
        return webSceneInstanceStepList;
    }

    @Override
    public WebSceneInstanceStep findWebSceneInstanceStep(@NotNull String id) {
        WebSceneInstanceStep webSceneInstanceStep = findOne(id);

        joinTemplate.joinQuery(webSceneInstanceStep);

        return webSceneInstanceStep;
    }

    @Override
    public List<WebSceneInstanceStep> findAllWebSceneInstanceStep() {
        List<WebSceneInstanceStepEntity> webSceneInstanceStepEntityList =  webSceneInstanceStepDao.findAllWebSceneInstanceStep();

        List<WebSceneInstanceStep> webSceneInstanceStepList =  BeanMapper.mapList(webSceneInstanceStepEntityList,WebSceneInstanceStep.class);

        joinTemplate.joinQuery(webSceneInstanceStepList);

        return webSceneInstanceStepList;
    }

    @Override
    public List<WebSceneInstanceStep> findWebSceneInstanceStepList(WebSceneInstanceStepQuery webSceneInstanceStepQuery) {
        List<WebSceneInstanceStepEntity> webSceneInstanceStepEntityList = webSceneInstanceStepDao.findWebSceneInstanceStepList(webSceneInstanceStepQuery);

        List<WebSceneInstanceStep> webSceneInstanceStepList = BeanMapper.mapList(webSceneInstanceStepEntityList,WebSceneInstanceStep.class);

        joinTemplate.joinQuery(webSceneInstanceStepList);

        return webSceneInstanceStepList;
    }

    @Override
    public Pagination<WebSceneInstanceStep> findWebSceneInstanceStepPage(WebSceneInstanceStepQuery webSceneInstanceStepQuery) {
        Pagination<WebSceneInstanceStepEntity>  pagination = webSceneInstanceStepDao.findWebSceneInstanceStepPage(webSceneInstanceStepQuery);

        List<WebSceneInstanceStep> webSceneInstanceStepList = BeanMapper.mapList(pagination.getDataList(),WebSceneInstanceStep.class);

        joinTemplate.joinQuery(webSceneInstanceStepList);

        return PaginationBuilder.build(pagination,webSceneInstanceStepList);
    }
}