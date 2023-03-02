package net.tiklab.teston.app.scene.instance.service;

import net.tiklab.beans.BeanMapper;
import net.tiklab.core.page.Pagination;
import net.tiklab.core.page.PaginationBuilder;
import net.tiklab.join.JoinTemplate;
import net.tiklab.teston.app.scene.instance.dao.AppSceneInstanceDao;
import net.tiklab.teston.app.scene.instance.entity.AppSceneInstanceEntity;
import net.tiklab.teston.app.scene.execute.model.AppSceneTestResponse;
import net.tiklab.teston.app.scene.instance.model.AppSceneInstance;
import net.tiklab.teston.app.scene.instance.model.AppSceneInstanceQuery;
import net.tiklab.teston.app.scene.instance.model.AppSceneInstanceStep;
import net.tiklab.teston.app.scene.instance.model.AppSceneInstanceStepQuery;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

/**
* app场景测试历史实例 服务
*/
@Service
public class AppSceneInstanceServiceImpl implements AppSceneInstanceService {

    @Autowired
    AppSceneInstanceDao appSceneInstanceDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    AppSceneInstanceStepService appSceneInstanceStepService;

    @Override
    public String createAppSceneInstance(@NotNull @Valid AppSceneInstance appSceneInstance) {
        AppSceneInstanceEntity appSceneInstanceEntity = BeanMapper.map(appSceneInstance, AppSceneInstanceEntity.class);

        appSceneInstanceEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));

        return appSceneInstanceDao.createAppSceneInstance(appSceneInstanceEntity);
    }

    @Override
    public void updateAppSceneInstance(@NotNull @Valid AppSceneInstance appSceneInstance) {
        AppSceneInstanceEntity appSceneInstanceEntity = BeanMapper.map(appSceneInstance, AppSceneInstanceEntity.class);

        appSceneInstanceDao.updateAppSceneInstance(appSceneInstanceEntity);
    }

    @Override
    public void deleteAppSceneInstance(@NotNull String id) {
        appSceneInstanceDao.deleteAppSceneInstance(id);
    }

    @Override
    public AppSceneInstance findOne(String id) {
        AppSceneInstanceEntity appSceneInstanceEntity = appSceneInstanceDao.findAppSceneInstance(id);

        AppSceneInstance appSceneInstance = BeanMapper.map(appSceneInstanceEntity, AppSceneInstance.class);
        return appSceneInstance;
    }

    @Override
    public List<AppSceneInstance> findList(List<String> idList) {
        List<AppSceneInstanceEntity> appSceneInstanceEntityList =  appSceneInstanceDao.findAppSceneInstanceList(idList);

        List<AppSceneInstance> appSceneInstanceList =  BeanMapper.mapList(appSceneInstanceEntityList, AppSceneInstance.class);
        return appSceneInstanceList;
    }

    @Override
    public AppSceneInstance findAppSceneInstance(@NotNull String id) {
        AppSceneInstance appSceneInstance = findOne(id);

        //历史回显把步骤列表带上
        List<AppSceneInstanceStep> appSceneInstanceStepList = appSceneInstanceStepService.findAppSceneInstanceStepList(new AppSceneInstanceStepQuery().setAppSceneInstanceId(id));
        appSceneInstance.setStepList(appSceneInstanceStepList);

        joinTemplate.joinQuery(appSceneInstance);
        return appSceneInstance;
    }

    @Override
    public List<AppSceneInstance> findAllAppSceneInstance() {
        List<AppSceneInstanceEntity> appSceneInstanceEntities =  appSceneInstanceDao.findAllAppSceneInstance();

        List<AppSceneInstance> appSceneInstanceList =  BeanMapper.mapList(appSceneInstanceEntities, AppSceneInstance.class);

        joinTemplate.joinQuery(appSceneInstanceList);
        return appSceneInstanceList;
    }

    @Override
    public List<AppSceneInstance> findAppSceneInstanceList(AppSceneInstanceQuery appSceneInstanceQuery) {
        List<AppSceneInstanceEntity> appSceneInstanceEntities = appSceneInstanceDao.findAppSceneInstanceList(appSceneInstanceQuery);

        List<AppSceneInstance> appSceneInstanceList = BeanMapper.mapList(appSceneInstanceEntities, AppSceneInstance.class);

        joinTemplate.joinQuery(appSceneInstanceList);

        return appSceneInstanceList;
    }

    @Override
    public Pagination<AppSceneInstance> findAppSceneInstancePage(AppSceneInstanceQuery appSceneInstanceQuery) {
        Pagination<AppSceneInstanceEntity>  pagination = appSceneInstanceDao.findAppSceneInstancePage(appSceneInstanceQuery);

        List<AppSceneInstance> appSceneInstanceList = BeanMapper.mapList(pagination.getDataList(), AppSceneInstance.class);

        joinTemplate.joinQuery(appSceneInstanceList);

        return PaginationBuilder.build(pagination,appSceneInstanceList);
    }

    @Override
    public String saveAppSceneInstanceToSql(AppSceneInstance appSceneInstance, AppSceneTestResponse appSceneTestResponse) {

        String appSceneInstanceId = createAppSceneInstance(appSceneInstance);

        //保存单个步骤
        if(CollectionUtils.isNotEmpty(appSceneTestResponse.getAppSceneInstanceStepList())){
            for(AppSceneInstanceStep appSceneInstanceStep: appSceneTestResponse.getAppSceneInstanceStepList()){
                appSceneInstanceStep.setAppSceneInstanceId(appSceneInstanceId);

                appSceneInstanceStepService.createAppSceneInstanceStep(appSceneInstanceStep);
            }
        }

        return appSceneInstanceId;
    }


}