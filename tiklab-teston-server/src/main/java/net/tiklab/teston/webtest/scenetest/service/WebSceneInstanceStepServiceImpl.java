package net.tiklab.teston.webtest.scenetest.service;

import net.tiklab.core.page.Pagination;
import net.tiklab.core.page.PaginationBuilder;
import net.tiklab.teston.webtest.scenetest.dao.WebSceneInstanceStepDao;
import net.tiklab.teston.webtest.scenetest.entity.WebSceneInstanceStepEntity;
import net.tiklab.teston.webtest.scenetest.model.WebSceneInstanceStep;
import net.tiklab.teston.webtest.scenetest.model.WebSceneInstanceStepQuery;

import net.tiklab.beans.BeanMapper;
import net.tiklab.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* WebSceneInstanceStepServiceImpl
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