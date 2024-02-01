package io.thoughtware.teston.test.app.scene.instance.service;

import com.alibaba.fastjson.JSONObject;
import io.thoughtware.teston.instance.model.Instance;
import io.thoughtware.teston.instance.model.InstanceQuery;
import io.thoughtware.teston.instance.service.InstanceService;
import io.thoughtware.teston.test.app.scene.cases.model.AppSceneCase;
import io.thoughtware.teston.test.app.scene.cases.service.AppSceneCaseService;
import io.thoughtware.teston.test.app.scene.execute.model.AppSceneTestResponse;
import io.thoughtware.teston.test.app.scene.instance.model.AppSceneInstance;
import io.thoughtware.teston.test.app.scene.instance.model.AppSceneInstanceQuery;
import io.thoughtware.teston.test.app.scene.instance.model.AppSceneInstanceStep;
import io.thoughtware.teston.test.common.ifjudgment.model.IfJudgmentInstance;
import io.thoughtware.teston.test.common.ifjudgment.service.IfJudgmentInstanceService;
import io.thoughtware.teston.test.common.stepcommon.model.StepCommonInstance;
import io.thoughtware.teston.test.common.stepcommon.model.StepCommonInstanceQuery;
import io.thoughtware.teston.test.common.stepcommon.service.StepCommonInstanceService;
import io.thoughtware.teston.common.MagicValue;
import io.thoughtware.teston.test.app.scene.instance.dao.AppSceneInstanceDao;
import io.thoughtware.teston.test.app.scene.instance.entity.AppSceneInstanceEntity;
import io.thoughtware.toolkit.beans.BeanMapper;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.core.page.PaginationBuilder;
import io.thoughtware.toolkit.join.JoinTemplate;
import io.thoughtware.teston.test.web.scene.cases.model.WebSceneCase;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.HashMap;
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

    @Autowired
    InstanceService instanceService;

    @Autowired
    AppSceneCaseService appSceneCaseService;

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
        String appSceneId = appSceneInstance.getAppSceneId();
        String appSceneInstanceId = createAppSceneInstance(appSceneInstance);

        // 创建公共实例
        Instance instance = new Instance();
        instance.setId(appSceneInstanceId);

        instance.setBelongId(appSceneId);
        instance.setType(MagicValue.CASE_TYPE_APP);

        AppSceneCase appSceneCase = appSceneCaseService.findAppSceneCase(appSceneId);
        instance.setName(appSceneCase.getTestCase().getName());
        instance.setRepositoryId(appSceneCase.getTestCase().getRepositoryId());

        //获取当前执行次数
        int executeNum = instanceService.getRecentExecuteNum(appSceneId);
        instance.setExecuteNumber(executeNum);

        JSONObject instanceMap = new JSONObject();
        instanceMap.put("result",appSceneInstance.getResult().toString());
        instanceMap.put("stepNum",appSceneInstance.getStepNum().toString());
        instanceMap.put("passNum",appSceneInstance.getPassNum().toString());
        instanceMap.put("passRate",appSceneInstance.getPassRate());
        instanceMap.put("failNum",appSceneInstance.getFailNum().toString());
        instance.setContent(instanceMap.toString());

        instanceService.createInstance(instance);


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