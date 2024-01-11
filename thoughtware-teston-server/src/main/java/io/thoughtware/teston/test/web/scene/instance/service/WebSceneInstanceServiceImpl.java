package io.thoughtware.teston.test.web.scene.instance.service;

import com.alibaba.fastjson.JSONObject;
import io.thoughtware.teston.common.MagicValue;
import io.thoughtware.teston.instance.model.Instance;
import io.thoughtware.teston.instance.model.InstanceQuery;
import io.thoughtware.teston.instance.service.InstanceService;
import io.thoughtware.teston.test.apix.http.scene.cases.model.ApiSceneCase;
import io.thoughtware.teston.test.common.ifjudgment.model.IfJudgmentInstance;
import io.thoughtware.teston.test.common.ifjudgment.service.IfJudgmentInstanceService;
import io.thoughtware.teston.test.common.stepcommon.model.StepCommonInstance;
import io.thoughtware.teston.test.common.stepcommon.model.StepCommonInstanceQuery;
import io.thoughtware.teston.test.common.stepcommon.service.StepCommonInstanceService;
import io.thoughtware.teston.test.web.scene.cases.model.WebSceneCase;
import io.thoughtware.teston.test.web.scene.cases.service.WebSceneCaseService;
import io.thoughtware.teston.test.web.scene.instance.dao.WebSceneInstanceDao;
import io.thoughtware.teston.test.web.scene.instance.entity.WebSceneInstanceEntity;
import io.thoughtware.toolkit.beans.BeanMapper;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.core.page.PaginationBuilder;
import io.thoughtware.toolkit.join.JoinTemplate;
import io.thoughtware.teston.test.web.scene.instance.model.WebSceneInstanceQuery;
import io.thoughtware.teston.test.web.scene.execute.model.WebSceneTestResponse;
import io.thoughtware.teston.test.web.scene.instance.model.WebSceneInstance;
import io.thoughtware.teston.test.web.scene.instance.model.WebSceneInstanceStep;
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
* web场景实例 服务
*/
@Service
public class WebSceneInstanceServiceImpl implements WebSceneInstanceService {

    @Autowired
    WebSceneInstanceDao webSceneInstanceDao;

    @Autowired
    JoinTemplate joinTemplate;
    
    @Autowired
    WebSceneInstanceStepService webSceneInstanceStepService;

    @Autowired
    StepCommonInstanceService stepCommonInstanceService;

    @Autowired
    IfJudgmentInstanceService ifJudgmentInstanceService;

    @Autowired
    WebSceneCaseService webSceneCaseService;

    @Autowired
    InstanceService instanceService;

    @Override
    public String createWebSceneInstance(@NotNull @Valid WebSceneInstance webSceneInstance) {
        WebSceneInstanceEntity webSceneInstanceEntity = BeanMapper.map(webSceneInstance, WebSceneInstanceEntity.class);

        webSceneInstanceEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));

        return webSceneInstanceDao.createWebSceneInstance(webSceneInstanceEntity);
    }

    @Override
    public void updateWebSceneInstance(@NotNull @Valid WebSceneInstance webSceneInstance) {
        WebSceneInstanceEntity webSceneInstanceEntity = BeanMapper.map(webSceneInstance, WebSceneInstanceEntity.class);

        webSceneInstanceDao.updateWebSceneInstance(webSceneInstanceEntity);
    }

    @Override
    public void deleteWebSceneInstance(@NotNull String id) {
        webSceneInstanceDao.deleteWebSceneInstance(id);
    }

    @Override
    public WebSceneInstance findOne(String id) {
        WebSceneInstanceEntity webSceneInstanceEntity = webSceneInstanceDao.findWebSceneInstance(id);

        WebSceneInstance webSceneInstance = BeanMapper.map(webSceneInstanceEntity, WebSceneInstance.class);
        return webSceneInstance;
    }

    @Override
    public List<WebSceneInstance> findList(List<String> idList) {
        List<WebSceneInstanceEntity> webSceneInstanceEntityList =  webSceneInstanceDao.findWebSceneInstanceList(idList);

        List<WebSceneInstance> webSceneInstanceList =  BeanMapper.mapList(webSceneInstanceEntityList, WebSceneInstance.class);
        return webSceneInstanceList;
    }

    @Override
    public WebSceneInstance findWebSceneInstance(@NotNull String id) {
        WebSceneInstance webSceneInstance = findOne(id);

        StepCommonInstanceQuery stepCommonInstanceQuery = new StepCommonInstanceQuery();
        stepCommonInstanceQuery.setInstanceId(id);
        stepCommonInstanceQuery.setCaseType(MagicValue.CASE_TYPE_WEB);
        List<StepCommonInstance> stepCommonInstanceList = stepCommonInstanceService.findStepCommonInstanceList(stepCommonInstanceQuery);
        webSceneInstance.setStepList(stepCommonInstanceList);

        joinTemplate.joinQuery(webSceneInstance);
        return webSceneInstance;
    }

    @Override
    public List<WebSceneInstance> findAllWebSceneInstance() {
        List<WebSceneInstanceEntity> webSceneInstanceEntities =  webSceneInstanceDao.findAllWebSceneInstance();

        List<WebSceneInstance> webSceneInstanceList =  BeanMapper.mapList(webSceneInstanceEntities, WebSceneInstance.class);

        joinTemplate.joinQuery(webSceneInstanceList);
        return webSceneInstanceList;
    }

    @Override
    public List<WebSceneInstance> findWebSceneInstanceList(WebSceneInstanceQuery webSceneInstanceQuery) {
        List<WebSceneInstanceEntity> webSceneInstanceEntities = webSceneInstanceDao.findWebSceneInstanceList(webSceneInstanceQuery);

        List<WebSceneInstance> webSceneInstanceList = BeanMapper.mapList(webSceneInstanceEntities, WebSceneInstance.class);

        joinTemplate.joinQuery(webSceneInstanceList);

        return webSceneInstanceList;
    }

    @Override
    public Pagination<WebSceneInstance> findWebSceneInstancePage(WebSceneInstanceQuery webSceneInstanceQuery) {
        Pagination<WebSceneInstanceEntity>  pagination = webSceneInstanceDao.findWebSceneInstancePage(webSceneInstanceQuery);

        List<WebSceneInstance> webSceneInstanceList = BeanMapper.mapList(pagination.getDataList(), WebSceneInstance.class);

        joinTemplate.joinQuery(webSceneInstanceList);

        return PaginationBuilder.build(pagination,webSceneInstanceList);
    }



    @Override
    public String saveWebSceneInstanceToSql(WebSceneInstance webSceneInstance, WebSceneTestResponse webSceneTestResponse) {
        String webSceneId = webSceneInstance.getWebSceneId();
        String webSceneInstanceId= createWebSceneInstance(webSceneInstance);

        createInstance(webSceneInstance,webSceneInstanceId,webSceneId);

        //保存单个步骤
        if(CollectionUtils.isNotEmpty(webSceneTestResponse.getStepCommonInstanceList())){
            List<StepCommonInstance> stepCommonInstanceList = webSceneTestResponse.getStepCommonInstanceList();

            createStepInstance(stepCommonInstanceList,webSceneInstanceId);
        }

        return webSceneInstanceId;
    }

    /**
     * 创建公共实例
     * @param webSceneInstance
     * @param webSceneInstanceId
     * @param webSceneId
     */
    private void createInstance(WebSceneInstance webSceneInstance,String webSceneInstanceId,String webSceneId){
        // 创建公共实例
        Instance instance = new Instance();
        instance.setId(webSceneInstanceId);

        instance.setBelongId(webSceneId);
        instance.setType(MagicValue.CASE_TYPE_WEB);

        WebSceneCase webSceneCase = webSceneCaseService.findWebSceneCase(webSceneId);
        instance.setName(webSceneCase.getTestCase().getName());
        instance.setRepositoryId(webSceneCase.getTestCase().getRepositoryId());

        InstanceQuery instanceQuery = new InstanceQuery();
        instanceQuery.setBelongId(webSceneId);
        List<Instance> instanceList = instanceService.findInstanceList(instanceQuery);
        if(instanceList!=null&& !instanceList.isEmpty()){
            Integer executeNumber = instanceList.get(0).getExecuteNumber();
            instance.setExecuteNumber(++executeNumber);
        }else {
            instance.setExecuteNumber(1);
        }

        JSONObject instanceMap = new JSONObject();
        instanceMap.put("result",webSceneInstance.getResult().toString());
        instanceMap.put("stepNum",webSceneInstance.getStepNum().toString());
        instanceMap.put("passNum",webSceneInstance.getPassNum().toString());
        instanceMap.put("passRate",webSceneInstance.getPassRate());
        instanceMap.put("failNum",webSceneInstance.getFailNum().toString());
        instanceMap.put("totalDuration",webSceneInstance.getTotalDuration().toString());
        instance.setContent(instanceMap.toString());

        instanceService.createInstance(instance);
    }

    /**
     * 保存单个步骤
     */
    @Override
    public void createStepInstance(List<StepCommonInstance> stepCommonInstanceList,String webSceneInstanceId){
        for(StepCommonInstance stepCommonInstance:stepCommonInstanceList){
            //公共的历史创建
            stepCommonInstance.setInstanceId(webSceneInstanceId);
            String stepInstanceId = stepCommonInstanceService.createStepCommonInstance(stepCommonInstance);

            //web步骤历史创建
            if(Objects.equals(stepCommonInstance.getType(), MagicValue.CASE_TYPE_WEB)){
                WebSceneInstanceStep webSceneInstanceStep = stepCommonInstance.getWebSceneInstanceStep();
                webSceneInstanceStep.setWebSceneInstanceId(webSceneInstanceId);
                webSceneInstanceStep.setId(stepInstanceId);
                webSceneInstanceStepService.createWebSceneInstanceStep(webSceneInstanceStep);
            }

            //if判断历史创建
            if(Objects.equals(stepCommonInstance.getType(), MagicValue.CASE_TYPE_IF)){
                IfJudgmentInstance ifJudgmentInstance = stepCommonInstance.getIfJudgmentInstance();
                ifJudgmentInstance.setStepInstanceId(webSceneInstanceId);
                ifJudgmentInstance.setId(stepInstanceId);
                ifJudgmentInstanceService.createIfJudgmentInstance(ifJudgmentInstance);
            }
        }
    }

}