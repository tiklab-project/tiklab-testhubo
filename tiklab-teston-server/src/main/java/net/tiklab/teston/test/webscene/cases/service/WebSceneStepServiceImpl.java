package net.tiklab.teston.test.webscene.cases.service;

import net.tiklab.core.page.Pagination;
import net.tiklab.core.page.PaginationBuilder;
import net.tiklab.teston.test.webscene.cases.service.WebSceneStepService;
import net.tiklab.teston.test.webscene.cases.dao.WebSceneStepDao;
import net.tiklab.teston.test.webscene.cases.entity.WebSceneStepEntity;
import net.tiklab.teston.test.webscene.cases.model.WebSceneStep;
import net.tiklab.teston.test.webscene.cases.model.WebSceneStepQuery;

import net.tiklab.beans.BeanMapper;
import net.tiklab.join.JoinTemplate;
import org.apache.commons.collections.CollectionUtils;
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

    @Override
    public void bindWebUnit(List<WebSceneStep> webSceneStepList) {
        if(CollectionUtils.isNotEmpty(webSceneStepList)){
            for (WebSceneStep webSceneStep : webSceneStepList) {
                createWebSceneStep(webSceneStep);
            }
        }
    }

}