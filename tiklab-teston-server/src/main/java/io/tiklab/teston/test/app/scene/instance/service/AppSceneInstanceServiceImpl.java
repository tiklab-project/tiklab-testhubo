package io.tiklab.teston.test.app.scene.instance.service;

import io.tiklab.teston.common.MagicValue;
import io.tiklab.teston.test.app.scene.instance.dao.AppSceneInstanceDao;
import io.tiklab.teston.test.app.scene.instance.entity.AppSceneInstanceEntity;
import io.tiklab.beans.BeanMapper;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.join.JoinTemplate;
import io.tiklab.teston.test.app.scene.execute.model.AppSceneTestResponse;
import io.tiklab.teston.test.app.scene.instance.model.AppSceneInstance;
import io.tiklab.teston.test.app.scene.instance.model.AppSceneInstanceQuery;
import io.tiklab.teston.test.app.scene.instance.model.AppSceneInstanceStep;
import io.tiklab.teston.test.app.scene.instance.model.AppSceneInstanceStepQuery;
import io.tiklab.teston.test.common.ifjudgment.model.IfJudgmentInstance;
import io.tiklab.teston.test.common.ifjudgment.service.IfJudgmentInstanceService;
import io.tiklab.teston.test.common.stepcommon.model.StepCommonInstance;
import io.tiklab.teston.test.common.stepcommon.model.StepCommonInstanceQuery;
import io.tiklab.teston.test.common.stepcommon.service.StepCommonInstanceService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

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

    @Autowired
    StepCommonInstanceService stepCommonInstanceService;

    @Autowired
    IfJudgmentInstanceService ifJudgmentInstanceService;

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
        StepCommonInstanceQuery stepCommonInstanceQuery = new StepCommonInstanceQuery();
        stepCommonInstanceQuery.setInstanceId(id);
        stepCommonInstanceQuery.setCaseType(MagicValue.CASE_TYPE_APP);
        List<StepCommonInstance> stepCommonInstanceList = stepCommonInstanceService.findStepCommonInstanceList(stepCommonInstanceQuery);
        appSceneInstance.setStepList(stepCommonInstanceList);

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
        if(CollectionUtils.isNotEmpty(appSceneTestResponse.getStepCommonInstanceList())){
            for(StepCommonInstance stepCommonInstance:appSceneTestResponse.getStepCommonInstanceList()){
                //公共的历史创建
                stepCommonInstance.setInstanceId(appSceneInstanceId);
                String stepInstanceId = stepCommonInstanceService.createStepCommonInstance(stepCommonInstance);

                //步骤历史创建
                if(Objects.equals(stepCommonInstance.getType(), MagicValue.CASE_TYPE_APP)){
                    AppSceneInstanceStep appSceneInstanceStep = stepCommonInstance.getAppSceneInstanceStep();
                    appSceneInstanceStep.setAppSceneInstanceId(appSceneInstanceId);
                    appSceneInstanceStep.setId(stepInstanceId);
                    appSceneInstanceStepService.createAppSceneInstanceStep(appSceneInstanceStep);
                }

                //if判断历史创建
                if(Objects.equals(stepCommonInstance.getType(), MagicValue.CASE_TYPE_IF)){
                    IfJudgmentInstance ifJudgmentInstance = stepCommonInstance.getIfJudgmentInstance();
                    ifJudgmentInstance.setStepInstanceId(appSceneInstanceId);
                    ifJudgmentInstance.setId(stepInstanceId);
                    ifJudgmentInstanceService.createIfJudgmentInstance(ifJudgmentInstance);
                }
            }
        }

        return appSceneInstanceId;
    }


}