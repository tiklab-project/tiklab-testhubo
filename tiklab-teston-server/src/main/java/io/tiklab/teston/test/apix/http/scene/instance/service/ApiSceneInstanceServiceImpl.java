package io.tiklab.teston.test.apix.http.scene.instance.service;

import io.tiklab.beans.BeanMapper;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.join.JoinTemplate;

import io.tiklab.teston.common.MagicValue;
import io.tiklab.teston.test.apix.http.scene.execute.model.ApiSceneTestResponse;
import io.tiklab.teston.test.apix.http.scene.instance.entity.ApiSceneInstanceEntity;
import io.tiklab.teston.test.apix.http.scene.instance.model.ApiSceneInstance;
import io.tiklab.teston.test.apix.http.scene.instance.model.ApiSceneInstanceQuery;
import io.tiklab.teston.test.apix.http.scene.instance.model.ApiSceneStepInstanceBind;
import io.tiklab.teston.test.apix.http.scene.instance.model.ApiSceneStepInstanceBindQuery;
import io.tiklab.teston.test.apix.http.unit.cases.model.ApiUnitCase;
import io.tiklab.teston.test.apix.http.unit.cases.service.ApiUnitCaseService;
import io.tiklab.teston.test.apix.http.unit.instance.model.ApiUnitInstance;
import io.tiklab.teston.test.apix.http.unit.instance.service.ApiUnitInstanceService;
import io.tiklab.teston.test.common.ifjudgment.model.IfJudgmentInstance;
import io.tiklab.teston.test.common.ifjudgment.service.IfJudgmentInstanceService;
import io.tiklab.teston.test.common.stepcommon.model.StepCommonInstance;
import io.tiklab.teston.test.common.stepcommon.model.StepCommonInstanceQuery;
import io.tiklab.teston.test.common.stepcommon.service.StepCommonInstanceService;
import io.tiklab.teston.test.test.service.TestCaseService;
import io.tiklab.teston.test.apix.http.scene.instance.dao.ApiSceneInstanceDao;
import io.tiklab.eam.common.context.LoginContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
* 接口场景实例 服务
*/
@Service
public class ApiSceneInstanceServiceImpl implements ApiSceneInstanceService {

    @Autowired
    ApiSceneInstanceDao apiSceneInstanceDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    ApiUnitInstanceService apiUnitInstanceService;

    @Autowired
    StepCommonInstanceService stepCommonInstanceService;

    @Autowired
    IfJudgmentInstanceService ifJudgmentInstanceService;

    @Override
    public String createApiSceneInstance(@NotNull @Valid ApiSceneInstance scenInstance) {
        ApiSceneInstanceEntity apiSceneInstanceEntity = BeanMapper.map(scenInstance, ApiSceneInstanceEntity.class);

        apiSceneInstanceEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));
        String userId = LoginContext.getLoginId();
        apiSceneInstanceEntity.setCreateUser(userId);

        return apiSceneInstanceDao.createTestInstance(apiSceneInstanceEntity);
    }

    @Override
    public void updateApiSceneInstance(@NotNull @Valid ApiSceneInstance scenInstance) {
        ApiSceneInstanceEntity testInstanceEntity = BeanMapper.map(scenInstance, ApiSceneInstanceEntity.class);

        apiSceneInstanceDao.updateTestInstance(testInstanceEntity);
    }

    @Override
    public void deleteApiSceneInstance(@NotNull String id) {
        apiSceneInstanceDao.deleteTestInstance(id);
    }

    @Override
    public ApiSceneInstance findOne(String id) {
        ApiSceneInstanceEntity testInstanceEntity = apiSceneInstanceDao.findTestInstance(id);

        ApiSceneInstance testInstance = BeanMapper.map(testInstanceEntity, ApiSceneInstance.class);
        return testInstance;
    }

    @Override
    public List<ApiSceneInstance> findList(List<String> idList) {
        List<ApiSceneInstanceEntity> testInstanceEntityList =  apiSceneInstanceDao.findTestInstanceList(idList);

        List<ApiSceneInstance> testInstanceList =  BeanMapper.mapList(testInstanceEntityList, ApiSceneInstance.class);
        return testInstanceList;
    }

    @Override
    public ApiSceneInstance findApiSceneInstance(@NotNull String id) {
        ApiSceneInstance apiSceneInstance = findOne(id);

        StepCommonInstanceQuery stepCommonInstanceQuery = new StepCommonInstanceQuery();
        stepCommonInstanceQuery.setInstanceId(id);
        stepCommonInstanceQuery.setCaseType(MagicValue.CASE_TYPE_API_SCENE);
        List<StepCommonInstance> stepCommonInstanceList = stepCommonInstanceService.findStepCommonInstanceList(stepCommonInstanceQuery);
        apiSceneInstance.setStepCommonInstanceList(stepCommonInstanceList);

        joinTemplate.joinQuery(apiSceneInstance);
        return apiSceneInstance;
    }

    @Override
    public List<ApiSceneInstance> findAllApiSceneInstance() {
        List<ApiSceneInstanceEntity> testInstanceEntities =  apiSceneInstanceDao.findAllTestInstance();

        List<ApiSceneInstance> testInstanceList =  BeanMapper.mapList(testInstanceEntities, ApiSceneInstance.class);

        joinTemplate.joinQuery(testInstanceList);
        return testInstanceList;
    }

    @Override
    public List<ApiSceneInstance> findApiSceneInstanceList(ApiSceneInstanceQuery apiSceneInstanceQuery) {
        List<ApiSceneInstanceEntity> testInstanceEntities = apiSceneInstanceDao.findTestInstanceList(apiSceneInstanceQuery);

        List<ApiSceneInstance> testInstanceList = BeanMapper.mapList(testInstanceEntities, ApiSceneInstance.class);

        joinTemplate.joinQuery(testInstanceList);

        return testInstanceList;
    }

    @Override
    public Pagination<ApiSceneInstance> findApiSceneInstancePage(ApiSceneInstanceQuery apiSceneInstanceQuery) {
        Pagination<ApiSceneInstanceEntity>  pagination = apiSceneInstanceDao.findTestInstancePage(apiSceneInstanceQuery);

        List<ApiSceneInstance> testInstanceList = BeanMapper.mapList(pagination.getDataList(), ApiSceneInstance.class);

        joinTemplate.joinQuery(testInstanceList);

        return PaginationBuilder.build(pagination,testInstanceList);
    }

    @Override
    public String saveApiSceneInstanceToSql(ApiSceneInstance apiSceneInstance, ApiSceneTestResponse apiSceneTestResponse) {
        String apiSceneInstanceId = createApiSceneInstance(apiSceneInstance);

        //所有ApiUnitInstance设置apiSceneInstanceId创建
        List<StepCommonInstance> stepCommonInstanceList = apiSceneTestResponse.getStepCommonInstanceList();
        stepCommonInstanceList.forEach(stepCommonInstance -> {
            //公共的历史创建
            stepCommonInstance.setInstanceId(apiSceneInstanceId);
            String stepInstanceId = stepCommonInstanceService.createStepCommonInstance(stepCommonInstance);

            if(stepCommonInstance.getApiUnitInstance() != null){
                ApiUnitInstance apiUnitInstance = stepCommonInstance.getApiUnitInstance();
                apiUnitInstance.setId(stepInstanceId);
                apiUnitInstanceService.saveApiUnitInstanceToSql(apiUnitInstance);
            }

            //if判断历史创建
            if(stepCommonInstance.getIfJudgmentInstance()!=null){
                IfJudgmentInstance ifJudgmentInstance = stepCommonInstance.getIfJudgmentInstance();
                ifJudgmentInstance.setStepInstanceId(apiSceneInstanceId);
                ifJudgmentInstance.setId(stepInstanceId);
                ifJudgmentInstanceService.createIfJudgmentInstance(ifJudgmentInstance);
            }
        });

        return apiSceneInstanceId;
    }


}