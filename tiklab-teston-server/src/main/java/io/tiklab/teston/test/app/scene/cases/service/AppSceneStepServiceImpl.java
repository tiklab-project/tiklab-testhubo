package io.tiklab.teston.test.app.scene.cases.service;

import io.tiklab.teston.test.app.scene.cases.dao.AppSceneStepDao;
import io.tiklab.teston.test.app.scene.cases.entity.AppSceneStepEntity;
import io.tiklab.beans.BeanMapper;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.join.JoinTemplate;
import io.tiklab.teston.test.app.scene.cases.model.AppSceneStep;
import io.tiklab.teston.test.app.scene.cases.model.AppSceneStepQuery;
import io.tiklab.teston.test.common.model.StepAssertCommon;
import io.tiklab.teston.test.common.model.StepAssertCommonQuery;
import io.tiklab.teston.test.common.service.StepAssertCommonService;
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

    @Autowired
    StepAssertCommonService stepAssertCommonService;

    @Override
    public String createAppSceneStep(@NotNull @Valid AppSceneStep appSceneStep) {
        AppSceneStepEntity appSceneStepEntity = BeanMapper.map(appSceneStep, AppSceneStepEntity.class);
        appSceneStepEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));

        List<AppSceneStepEntity> appSceneStepEntityList = findAppSceneStepEntity(appSceneStep.getAppSceneId());
        if(appSceneStepEntityList!=null && appSceneStepEntityList.size()>0){
            appSceneStepEntity.setSort(appSceneStepEntityList.size());
        }else {
            appSceneStepEntity.setSort(0);
        }

        return appSceneStepDao.createAppSceneStep(appSceneStepEntity);
    }

    @Override
    public void updateAppSceneStep(@NotNull @Valid AppSceneStep appSceneStep) {
        if(appSceneStep.getOldSort()!=null){
            Integer curSort = appSceneStep.getSort();
            Integer oldSort = appSceneStep.getOldSort();

            List<AppSceneStepEntity> appSceneStepEntityList = findAppSceneStepEntity(appSceneStep.getAppSceneId());
            if(curSort>oldSort){
                for(int i=oldSort+1;i<=curSort;i++){
                    AppSceneStepEntity appSceneStepEntity = appSceneStepEntityList.get(i);
                    appSceneStepEntity.setSort(appSceneStepEntity.getSort()-1);
                    appSceneStepDao.updateAppSceneStep(appSceneStepEntity);
                }
            }

            if(curSort<oldSort){
                for(int i=oldSort-1;i>=curSort;i--){
                    AppSceneStepEntity appSceneStepEntity = appSceneStepEntityList.get(i);
                    appSceneStepEntity.setSort(appSceneStepEntity.getSort()+1);
                    appSceneStepDao.updateAppSceneStep(appSceneStepEntity);
                }
            }
        }


        AppSceneStepEntity appSceneStepEntity = BeanMapper.map(appSceneStep, AppSceneStepEntity.class);

        appSceneStepDao.updateAppSceneStep(appSceneStepEntity);
    }

    @Override
    public void deleteAppSceneStep(@NotNull String id) {
        AppSceneStepEntity appSceneStep = appSceneStepDao.findAppSceneStep(id);
        if(appSceneStep== null){return;}
        Integer sort = appSceneStep.getSort();
        List<AppSceneStepEntity> appSceneStepEntityList = findAppSceneStepEntity(appSceneStep.getAppSceneId());
        for(AppSceneStepEntity appSceneStepEntity: appSceneStepEntityList){
            if(appSceneStepEntity.getSort()>sort){
                appSceneStepEntity.setSort(appSceneStepEntity.getSort()-1);
                appSceneStepDao.updateAppSceneStep(appSceneStepEntity);
            }

            if(appSceneStepEntity.getSort().equals(sort)){
                appSceneStepDao.deleteAppSceneStep(id);
            }
        }
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


    private List<AppSceneStepEntity> findAppSceneStepEntity(String appSceneId) {
        AppSceneStepQuery appSceneStepQuery = new AppSceneStepQuery();
        appSceneStepQuery.setAppSceneId(appSceneId);
        List<AppSceneStepEntity> appSceneStepList = appSceneStepDao.findAppSceneStepList(appSceneStepQuery);

        return appSceneStepList;
    }
}