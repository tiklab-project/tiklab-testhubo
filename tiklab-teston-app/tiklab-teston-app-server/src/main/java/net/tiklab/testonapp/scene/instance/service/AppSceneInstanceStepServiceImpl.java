package net.tiklab.testonapp.scene.instance.service;

import net.tiklab.beans.BeanMapper;
import net.tiklab.core.page.Pagination;
import net.tiklab.core.page.PaginationBuilder;
import net.tiklab.join.JoinTemplate;
import net.tiklab.teston.test.app.scene.instance.dao.AppSceneInstanceStepDao;
import net.tiklab.teston.test.app.scene.instance.entity.AppSceneInstanceStepEntity;
import net.tiklab.teston.test.app.scene.instance.model.AppSceneInstanceStep;
import net.tiklab.teston.test.app.scene.instance.model.AppSceneInstanceStepQuery;
import net.tiklab.teston.test.app.scene.instance.service.AppSceneInstanceStepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* AppSceneInstanceStepServiceImpl
*/
@Service
public class AppSceneInstanceStepServiceImpl implements AppSceneInstanceStepService {

    @Autowired
    AppSceneInstanceStepDao appSceneInstanceStepDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createAppSceneInstanceStep(@NotNull @Valid AppSceneInstanceStep appSceneInstanceStep) {
        AppSceneInstanceStepEntity appSceneInstanceStepEntity = BeanMapper.map(appSceneInstanceStep, AppSceneInstanceStepEntity.class);

        return appSceneInstanceStepDao.createAppSceneInstanceStep(appSceneInstanceStepEntity);
    }

    @Override
    public void updateAppSceneInstanceStep(@NotNull @Valid AppSceneInstanceStep appSceneInstanceStep) {
        AppSceneInstanceStepEntity appSceneInstanceStepEntity = BeanMapper.map(appSceneInstanceStep, AppSceneInstanceStepEntity.class);

        appSceneInstanceStepDao.updateAppSceneInstanceStep(appSceneInstanceStepEntity);
    }

    @Override
    public void deleteAppSceneInstanceStep(@NotNull String id) {
        appSceneInstanceStepDao.deleteAppSceneInstanceStep(id);
    }

    @Override
    public AppSceneInstanceStep findOne(String id) {
        AppSceneInstanceStepEntity appSceneInstanceStepEntity = appSceneInstanceStepDao.findAppSceneInstanceStep(id);

        AppSceneInstanceStep appSceneInstanceStep = BeanMapper.map(appSceneInstanceStepEntity, AppSceneInstanceStep.class);
        return appSceneInstanceStep;
    }

    @Override
    public List<AppSceneInstanceStep> findList(List<String> idList) {
        List<AppSceneInstanceStepEntity> appSceneInstanceStepEntityList =  appSceneInstanceStepDao.findAppSceneInstanceStepList(idList);

        List<AppSceneInstanceStep> appSceneInstanceStepList =  BeanMapper.mapList(appSceneInstanceStepEntityList,AppSceneInstanceStep.class);
        return appSceneInstanceStepList;
    }

    @Override
    public AppSceneInstanceStep findAppSceneInstanceStep(@NotNull String id) {
        AppSceneInstanceStep appSceneInstanceStep = findOne(id);

        joinTemplate.joinQuery(appSceneInstanceStep);

        return appSceneInstanceStep;
    }

    @Override
    public List<AppSceneInstanceStep> findAllAppSceneInstanceStep() {
        List<AppSceneInstanceStepEntity> appSceneInstanceStepEntityList =  appSceneInstanceStepDao.findAllAppSceneInstanceStep();

        List<AppSceneInstanceStep> appSceneInstanceStepList =  BeanMapper.mapList(appSceneInstanceStepEntityList,AppSceneInstanceStep.class);

        joinTemplate.joinQuery(appSceneInstanceStepList);

        return appSceneInstanceStepList;
    }

    @Override
    public List<AppSceneInstanceStep> findAppSceneInstanceStepList(AppSceneInstanceStepQuery appSceneInstanceStepQuery) {
        List<AppSceneInstanceStepEntity> appSceneInstanceStepEntityList = appSceneInstanceStepDao.findAppSceneInstanceStepList(appSceneInstanceStepQuery);

        List<AppSceneInstanceStep> appSceneInstanceStepList = BeanMapper.mapList(appSceneInstanceStepEntityList,AppSceneInstanceStep.class);

        joinTemplate.joinQuery(appSceneInstanceStepList);

        return appSceneInstanceStepList;
    }

    @Override
    public Pagination<AppSceneInstanceStep> findAppSceneInstanceStepPage(AppSceneInstanceStepQuery appSceneInstanceStepQuery) {
        Pagination<AppSceneInstanceStepEntity>  pagination = appSceneInstanceStepDao.findAppSceneInstanceStepPage(appSceneInstanceStepQuery);

        List<AppSceneInstanceStep> appSceneInstanceStepList = BeanMapper.mapList(pagination.getDataList(),AppSceneInstanceStep.class);

        joinTemplate.joinQuery(appSceneInstanceStepList);

        return PaginationBuilder.build(pagination,appSceneInstanceStepList);
    }
}