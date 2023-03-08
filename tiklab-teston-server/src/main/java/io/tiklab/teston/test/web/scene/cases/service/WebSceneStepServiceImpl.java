package io.tiklab.teston.test.web.scene.cases.service;

import io.tiklab.teston.test.web.scene.cases.dao.WebSceneStepDao;
import io.tiklab.teston.test.web.scene.cases.entity.WebSceneStepEntity;
import io.tiklab.beans.BeanMapper;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.join.JoinTemplate;
import io.tiklab.teston.test.web.scene.cases.model.WebSceneStep;
import io.tiklab.teston.test.web.scene.cases.model.WebSceneStepQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
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

    @Override
    public String createWebSceneStep(@NotNull @Valid WebSceneStep webSceneStep) {
        WebSceneStepEntity webSceneStepEntity = BeanMapper.map(webSceneStep, WebSceneStepEntity.class);

        webSceneStepEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));

        return webSceneStepDao.createWebSceneStep(webSceneStepEntity);
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

        joinTemplate.joinQuery(webSceneStepList);

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