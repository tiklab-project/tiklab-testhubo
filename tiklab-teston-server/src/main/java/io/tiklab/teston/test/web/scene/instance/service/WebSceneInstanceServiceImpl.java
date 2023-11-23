package io.tiklab.teston.test.web.scene.instance.service;

import io.tiklab.teston.common.MagicValue;
import io.tiklab.teston.test.common.ifjudgment.model.IfJudgmentInstance;
import io.tiklab.teston.test.common.ifjudgment.service.IfJudgmentInstanceService;
import io.tiklab.teston.test.common.stepcommon.model.StepCommonInstance;
import io.tiklab.teston.test.common.stepcommon.model.StepCommonInstanceQuery;
import io.tiklab.teston.test.common.stepcommon.service.StepCommonInstanceService;
import io.tiklab.teston.test.web.scene.instance.dao.WebSceneInstanceDao;
import io.tiklab.teston.test.web.scene.instance.entity.WebSceneInstanceEntity;
import io.tiklab.beans.BeanMapper;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.join.JoinTemplate;
import io.tiklab.teston.test.web.scene.instance.model.WebSceneInstanceQuery;
import io.tiklab.teston.test.web.scene.execute.model.WebSceneTestResponse;
import io.tiklab.teston.test.web.scene.instance.model.WebSceneInstance;
import io.tiklab.teston.test.web.scene.instance.model.WebSceneInstanceStep;
import io.tiklab.teston.test.web.scene.instance.model.WebSceneInstanceStepQuery;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
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

        String webSceneInstanceId= createWebSceneInstance(webSceneInstance);

        //保存单个步骤
        if(CollectionUtils.isNotEmpty(webSceneTestResponse.getStepCommonInstanceList())){

            for(StepCommonInstance stepCommonInstance:webSceneTestResponse.getStepCommonInstanceList()){
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

        return webSceneInstanceId;
    }

}