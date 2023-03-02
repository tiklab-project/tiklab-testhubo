package net.tiklab.teston.app.scene.cases.service;

import net.tiklab.beans.BeanMapper;
import net.tiklab.core.page.Pagination;
import net.tiklab.core.page.PaginationBuilder;
import net.tiklab.join.JoinTemplate;
import net.tiklab.teston.app.scene.cases.entity.AppSceneStepEntity;
import net.tiklab.teston.app.scene.cases.dao.AppSceneStepDao;
import net.tiklab.teston.app.scene.cases.model.AppSceneStep;
import net.tiklab.teston.app.scene.cases.model.AppSceneStepQuery;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
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

    @Override
    public String createAppSceneStep(@NotNull @Valid AppSceneStep appSceneStep) {
        AppSceneStepEntity appSceneStepEntity = BeanMapper.map(appSceneStep, AppSceneStepEntity.class);

        appSceneStepEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));

        return appSceneStepDao.createAppSceneStep(appSceneStepEntity);
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